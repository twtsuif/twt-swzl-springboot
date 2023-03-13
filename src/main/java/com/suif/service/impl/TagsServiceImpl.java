package com.suif.service.impl;

import com.suif.entity.Tags;
import com.suif.mapper.TagsMapper;
import com.suif.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

}
