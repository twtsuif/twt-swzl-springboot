package com.suif.param;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PostParam implements Serializable {
    @NotNull
    private Integer campus;

    @NotNull
    private Integer categoryId;

    private String tags;

    @NotBlank
    private String findLocation;

    @NotBlank
    private String nowLocation;

    @NotBlank
    private String description;

    @NotBlank
    private String images;

    @NotBlank
    private String contact;
}
