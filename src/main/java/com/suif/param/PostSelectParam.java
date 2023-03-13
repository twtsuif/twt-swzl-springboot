package com.suif.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suif.entity.Post;
import lombok.Data;

@Data
public class PostSelectParam {

    private Integer campus;
    private Integer categoryId;
    private String date;


    private Integer pageCurrent=1;
    private Integer pageSize=5;

    public Page<Post> getPage(){
        return new Page<>(pageCurrent,pageSize);
    }
}
