package com.pzh.code.archyonix.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pzh.code.archyonix.model.db2.LuckPermsMG;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LuckPermsDto {
    private String uuid;
    private String username;
    private String primary_group;

    public LuckPermsMG toLuckPerms(){
        LuckPermsMG luckPermsMG = new LuckPermsMG();
        luckPermsMG.setUuid(uuid);
        luckPermsMG.setUsername(username);
        luckPermsMG.setPrimary_group(primary_group);

        return luckPermsMG;
    }

    public static LuckPermsDto fromLuckPerms(LuckPermsMG luckPermsMG) {
        LuckPermsDto luckPermsDto = new LuckPermsDto();
        luckPermsDto.setUuid(luckPermsMG.getUuid());
        luckPermsDto.setUsername(luckPermsMG.getUsername());
        luckPermsDto.setPrimary_group(luckPermsMG.getPrimary_group());

        return luckPermsDto;
    }
}
