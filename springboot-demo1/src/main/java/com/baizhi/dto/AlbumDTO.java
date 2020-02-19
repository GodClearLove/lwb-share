package com.baizhi.dto;

import com.baizhi.entity.Album;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;
@Configuration
public class AlbumDTO implements Serializable {
    private Integer total;
    private List<Album> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Album> getRows() {
        return rows;
    }

    public void setRows(List<Album> rows) {
        this.rows = rows;
    }
}
