package com.baizhi.service;

import com.baizhi.dto.BannerDTO;
import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    BannerDTO queryByPage(Integer curPage, Integer pageSize);
    void insert(Banner banner);
    void delete(Integer id);
    void update(Banner banner);
    List<Banner> selectAll();

    List<Banner> queryByDesc(String description);
}
