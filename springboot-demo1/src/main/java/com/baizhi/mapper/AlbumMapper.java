package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumMapper extends Mapper<Album> {
    Integer getCount();
    List<Album> queryAllByPage(@Param("curPage")Integer curPage, @Param("pageSize")Integer pageSize);
    void updateCount(@Param("id") Integer id,@Param("count") Integer count);
    List<Album> queryAll();
    Album queryOneById(Integer id);
}
