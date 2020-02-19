package com.baizhi.service;

import com.baizhi.dto.AlbumDTO;
import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    AlbumDTO queryByPage(Integer curPage, Integer pageSize);
    Album queryOne(Integer id);
    void insert(Album album);
    //修改专辑的章节数
    void updateCount(Integer id,Integer count);
    List<Album> queryAll();
}
