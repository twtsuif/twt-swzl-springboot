package com.suif.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suif.param.AskParam;
import com.suif.entity.Ask;
import com.suif.entity.Category;
import com.suif.mapper.AskMapper;
import com.suif.mapper.CategoryMapper;
import com.suif.service.AskService;
import com.suif.utils.Result;
import com.suif.vo.AskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class AskServiceImpl extends ServiceImpl<AskMapper,Ask> implements AskService  {

    @Autowired
    private AskMapper askMapper;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Ask> listAsks(Page<Ask> page, Long userId) {

        LambdaQueryWrapper<Ask> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Ask::getUserId,userId);
        lambdaQueryWrapper.orderByDesc(Ask::getCreated);

        IPage<Ask> askIPage = askMapper.selectPage(page,lambdaQueryWrapper);

        List<Ask> records = askIPage.getRecords();

        return records;
    }

    @Override
    public void add(AskParam askParam) {
        Ask ask = new Ask();
        BeanUtil.copyProperties(askParam,ask);
        ask.setCreated(LocalDateTime.now());
        askMapper.insert(ask);
    }

    @Override
    public Result listPostsByUserId(Page<Ask> page, Long userId) {
        LambdaQueryWrapper<Ask> lqw = new LambdaQueryWrapper<>();

        lqw.orderByDesc(Ask::getCreated);
        lqw.eq(Ask::getUserId,userId);

        IPage<Ask> postIPage =askMapper.selectPage(page,lqw);

        List<Ask> records = postIPage.getRecords();

        return Result.success(copyList(records));
    }

    @Override
    public void deleteAsk(Integer id) {
        askMapper.deleteById(id);
    }


    private List<AskVO> copyList(List<Ask> records){
        List<AskVO> askVOList = new ArrayList<>();

        for (Ask record:records){
            AskVO askVo = new AskVO();
            BeanUtil.copyProperties(record,askVo);

            Category category = categoryMapper.selectById(record.getCategoryId());
            askVo.setCategoryName(category.getCategoryName());

            Integer upCategoryId =categoryMapper.getUpCategoryId(record.getCategoryId());
            String imageUrl = categoryMapper.getCategory(upCategoryId);

            askVo.setAvatar(imageUrl);

            askVOList.add(askVo);
        }

        return askVOList;
    }
}
