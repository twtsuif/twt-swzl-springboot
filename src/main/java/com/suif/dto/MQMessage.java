package com.suif.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class MQMessage implements Serializable {
    private Integer campus;
    private Integer categoryId;
    private String tags;
    private Integer id;
}
