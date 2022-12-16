package com.pzh.code.archyonix.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {
    public String currPassword;
    public String newPassword;
}
