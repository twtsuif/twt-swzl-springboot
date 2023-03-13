package com.suif.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suif.entity.Ask;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AskMapper extends BaseMapper<Ask> {
    void add(Long userId, Integer postId);

}
