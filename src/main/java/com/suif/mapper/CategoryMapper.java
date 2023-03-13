package com.suif.mapper;

import com.suif.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sxd
 * @since 2022-01-04
 */

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    String getCategory(Integer categoryId);

    Integer getUpCategoryId(Integer categoryId);
}
