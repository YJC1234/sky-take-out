package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品ids查套餐ids
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);
}
