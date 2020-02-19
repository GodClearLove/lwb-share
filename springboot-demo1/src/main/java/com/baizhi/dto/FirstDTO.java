package com.baizhi.dto;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstDTO {
    private List<Banner> header;
    private List<Album> album;
    private List<Article> article;

    public List<Banner> getHeader() {
        return header;
    }

    public void setHeader(List<Banner> header) {
        this.header = header;
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }
}
