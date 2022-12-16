package com.pzh.code.archyonix.service.impl.db1;

import com.pzh.code.archyonix.history.HistoryShopping;
import com.pzh.code.archyonix.repository.db1.HistoryShoppingRepository;
import com.pzh.code.archyonix.service.db1.HistoryShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HistoryShoppingServiceImpl implements HistoryShoppingService {

    private final HistoryShoppingRepository historyShoppingRepository;

    @Autowired
    public HistoryShoppingServiceImpl(HistoryShoppingRepository historyShoppingRepository) {
        this.historyShoppingRepository = historyShoppingRepository;
    }

    @Override
    public HistoryShopping register(HistoryShopping historyShopping) {
        return historyShoppingRepository.save(historyShopping);
    }
}
