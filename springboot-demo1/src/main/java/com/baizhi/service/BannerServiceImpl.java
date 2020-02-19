package com.baizhi.service;

import com.baizhi.dto.BannerDTO;
import com.baizhi.entity.Banner;
import com.baizhi.luceneDao.LuceneDao;
import com.baizhi.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    /*@Autowired
    BannerDTO bannerDTO;*/
    @Autowired
    BannerMapper bannerMapper;
    @Autowired
    LuceneDao luceneDao;

    @Override
    public BannerDTO queryByPage(Integer curPage, Integer pageSize) {
        BannerDTO bannerDTO = new BannerDTO();
        Integer count = bannerMapper.getCount();
        bannerDTO.setTotal(count);
        List<Banner> list = bannerMapper.queryAllByPage(curPage, pageSize);
        bannerDTO.setRows(list);
        return bannerDTO;
    }

    @Override
    public void insert(Banner banner) {
        bannerMapper.insert(banner);
        luceneDao.createIndex(banner);
    }

    @Override
    public void delete(Integer id) {
        bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Banner banner) {
        bannerMapper.updateByPrimaryKey(banner);
    }

    @Override
    public List<Banner> selectAll() {
        List<Banner> banners = bannerMapper.selectAll();
        /*for (Banner banner : banners) {
            String imgPath = banner.getImgPath();
            banner.setImgPath("D:/source/idea/springboot-demo1/src/main/webapp/upload/"+imgPath);
        }*/
        return banners;
    }

    @Override
    public List<Banner> queryByDesc(String description) {
        List<Banner> list = luceneDao.searcherIndex(description);
        return list;
    }

}
