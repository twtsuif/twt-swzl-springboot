package com.suif.service.impl;

import com.suif.entity.Category;
import com.suif.mapper.CategoryMapper;
import com.suif.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
