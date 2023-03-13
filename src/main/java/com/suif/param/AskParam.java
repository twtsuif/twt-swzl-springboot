package com.suif.param;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AskParam implements Serializable {

    @NotNull
    private Integer campus;

    @NotNull
    private Integer categoryId;

    @NotBlank
    private String tags;

    private Integer userId;
}
