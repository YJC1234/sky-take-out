package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品ids查套餐ids
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    /**
     * 批量添加套餐菜品关系
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id删除套餐菜品关系
     */
    void deleteBySetmealId(Long setmealId);

    /**
     * 根据套餐id查询套餐菜品关系
     */
    List<SetmealDish> getBySetmealId(Long id);

    void deleteBySetmealIds(List<Long> ids);
}
