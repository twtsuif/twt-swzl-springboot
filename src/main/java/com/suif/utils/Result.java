package com.suif.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private int state;

    private String msg;

    private Object data;

    public static Result success(Object data) {
        return new Result(200,"操作成功",data);
    }

    public static Result fail(String msg){
        return new Result(400,msg,null);
    }
}
