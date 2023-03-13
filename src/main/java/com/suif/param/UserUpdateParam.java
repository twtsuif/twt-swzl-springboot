package com.suif.param;

import lombok.Data;

@Data
public class UserUpdateParam {
    private String username;
    private String password;
    private String avatar;
}
