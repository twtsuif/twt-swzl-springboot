package com.suif.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * Shiro维护的用户对象
 */
@Data
public class ShiroUser implements Serializable {
    private Long id;
    private String username;
    private String avatar;
}
