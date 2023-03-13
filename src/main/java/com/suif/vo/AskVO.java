package com.suif.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AskVO implements Serializable {

    private Integer id;


    private Integer campus;

    private String tags;

    private String avatar;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    private String categoryName;

}
