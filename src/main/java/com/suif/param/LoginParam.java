package com.suif.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@ApiModel(value="用户名密码对象",description="LoginDto对象")
@Data
public class LoginParam implements Serializable {

    @ApiModelProperty(value="用户名",name="username",example="shixidong")
    @NotBlank(message = "昵称不能为空")
    private String username;


    @ApiModelProperty(value="密码",name="password",example="2499110977")
    @NotBlank(message = "密码不能为空")
    private String password;
}
