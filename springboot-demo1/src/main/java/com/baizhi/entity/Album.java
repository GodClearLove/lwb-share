package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
@ExcelTarget(value = "album")
public class Album implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号",needMerge = true)
    private Integer id;
    @Excel(name = "标题",needMerge = true)
    private String title;
    @Excel(name = "章节数",needMerge = true)
    private Integer count;
    @Column(name = "cover_img")
    @Excel(name = "图片",needMerge = true,type = 2)
    private String coverImg;
    @Excel(name = "评分",needMerge = true)
    private Integer score;
    @Excel(name = "作者",needMerge = true)
    private String author;
    @Excel(name = "播音",needMerge = true)
    private String broadcast;
    @Excel(name = "简介",width = 20,needMerge = true)
    private String brief;
    @Column(name = "pub_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Excel(name = "出版时间",format = "yyyy-MM-dd",width = 20,needMerge = true)
    private Date pubDate;
    @Transient
    @ExcelCollection(name="章节")
    private List<Chapter> children;
}
