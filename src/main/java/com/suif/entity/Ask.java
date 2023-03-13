package com.suif.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Ask implements Serializable {

    private static final long serialVersionUID=1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer campus;

    private Integer categoryId;

    private String tags;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime created;

    private Long userId;
}
