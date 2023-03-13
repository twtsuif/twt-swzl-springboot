package com.suif.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suif.param.AskParam;
import com.suif.entity.Ask;
import com.suif.utils.Result;

import java.util.List;

public interface AskService extends IService<Ask> {
    List<Ask> listAsks(Page<Ask> page, Long userId);

    void add(AskParam askParam);

    Result listPostsByUserId(Page<Ask> page, Long userId);

    void deleteAsk(Integer id);
}
