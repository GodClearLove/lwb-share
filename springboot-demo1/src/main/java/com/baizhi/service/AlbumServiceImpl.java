package com.baizhi.service;

import com.baizhi.dto.AlbumDTO;
import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;
    /*@Autowired
    AlbumDTO albumDTO;*/
    //注入不好用--
    @Override
    public AlbumDTO queryByPage(Integer curPage, Integer pageSize) {
        AlbumDTO albumDTO = new AlbumDTO();
        //PageHelper.startPage(curPage,pageSize);
        //List<Album> list = albumMapper.selectAll();
        List<Album> list = albumMapper.queryAllByPage(curPage, pageSize);
        Integer count = albumMapper.getCount();
        albumDTO.setTotal(count);
        albumDTO.setRows(list);
        return albumDTO;
    }

    @Override
    public Album queryOne(Integer id) {
        Album album = albumMapper.selectOne(new Album(id,null,null,null,null,null,null,null,null,null));
        return album;
    }

    @Override
    public void insert(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public void updateCount(Integer id,Integer count) {
        albumMapper.updateCount(id, count);
    }

    @Override
    public List<Album> queryAll() {
        List<Album> albums = albumMapper.queryAll();
        return albums;
    }
}
