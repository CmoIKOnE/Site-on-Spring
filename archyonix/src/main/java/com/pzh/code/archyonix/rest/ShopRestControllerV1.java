package com.pzh.code.archyonix.rest;

import com.pzh.code.archyonix.dto.ShopDto;
import com.pzh.code.archyonix.dto.ShoppingDto;
import com.pzh.code.archyonix.history.HistoryShopping;
import com.pzh.code.archyonix.model.db1.User;
import com.pzh.code.archyonix.model.db2.LuckPermsMG;
import com.pzh.code.archyonix.service.db1.HistoryShoppingService;
import com.pzh.code.archyonix.service.db1.ShopService;
import com.pzh.code.archyonix.service.db1.UserService;
import com.pzh.code.archyonix.service.db2.LuckPermsMGService;
import com.pzh.code.archyonix.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/shop/")
public class ShopRestControllerV1 {

    private final ShopService shopService;
    private final UserService userService;
    private final HistoryShoppingService historyShoppingService;

    private final LuckPermsMGService luckPermsMGService;

    @Autowired
    public ShopRestControllerV1(ShopService shopService, UserService userService, HistoryShoppingService historyShoppingService, LuckPermsMGService luckPermsMGService) {
        this.shopService = shopService;
        this.userService = userService;
        this.historyShoppingService = historyShoppingService;
        this.luckPermsMGService = luckPermsMGService;
    }

    @GetMapping(value = "{category}")
    public ResponseEntity<List<ShopDto>> getShopByCategory(@PathVariable(name = "category") String server){
        List<Shop> shop = shopService.findAllByServer(server);

        if(shop == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ShopDto> result = shop.stream().map(shop1 -> ShopDto.fromShop(shop1)).collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("shopping")
    public ResponseEntity<?> shopping(@RequestBody ShoppingDto requestDto){
        try {
            Map<Object, Object> response = new HashMap<>();
            int warn = 0;

            Long id = requestDto.getId();

            Shop shop = shopService.findById(id);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.findByUsername(username);

            if (user == null) {
                response.put("warn-username", "User: does not exist"); warn += 1;
            }
            if (shop == null) {
                response.put("warn-shop", "Shop: does not exist"); warn += 1;
            }

            assert shop != null;
            switch(shop.getServer()){
                case "Minigames":
                    if (luckPermsMGService.getPriority4MG(username) >= shop.getPriority()) {
                        response.put("warn-priority", luckPermsMGService.getPriority4MG(username) + " >= " + shop.getPriority()); warn += 1;
                    }
                case "SpaceX":
                        response.put("warn-priority", "SpaceX"); warn += 1;
            }

            if (shop.getPrice() > user.getRub()){
                response.put("warn-rub", "Rub: " + shop.getPrice() + " > " + user.getRub()); warn += 1;
            }

            if(warn >= 1) return ResponseEntity.badRequest().body(response);

            /* UPDATE USER RUB AFTER BUY */
            user.setRub(user.getRub() - shop.getPrice());
            userService.save(user);

            /* UPDATING DONATE IN LUCKPERMS */
            luckPermsMGService.update(user.getUsername(), shop.getSpecial_name());

            /* CREATING HISTORY */
            HistoryShopping hs = new HistoryShopping();
            hs.setUsername(user.getUsername());
            hs.setItem(shop.getServer() + " -> " + shop.getName() + " -> " + username);
            hs.setRub(shop.getPrice());
            historyShoppingService.register(hs);

            response.put("username", user.getUsername());
            response.put("rub", shop.getPrice());
            response.put("ostatok-rub", user.getRub());
            response.put("category", shop.getServer());
            response.put("shop", shop.getName());

            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid converter");
        }
    }
}
