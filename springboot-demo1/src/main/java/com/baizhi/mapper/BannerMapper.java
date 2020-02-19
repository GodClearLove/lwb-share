package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerMapper extends Mapper<Banner> {
    Integer getCount();
    List<Banner> queryAllByPage(@Param("curPage")Integer curPage, @Param("pageSize")Integer pageSize);
    List<Banner> queryAllByStu();
}
