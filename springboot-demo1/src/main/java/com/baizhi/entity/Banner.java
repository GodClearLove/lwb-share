package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banner")
public class Banner implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号",isImportField = "true_st")
    private Integer id;
    @Excel(name = "标题",isImportField = "true_st")
    private String title;
    @Column(name = "img_path")
    @Excel(name = "图片",type = 2,width = 20,isImportField = "true_st",savePath="http://192.168.85.136/")
    private String imgPath;
    @Excel(name = "是否展示",replace = {"否_0","是_1"},isImportField = "true_st")
    private Integer status;
    @Column(name = "pub_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间",format = "yyyy-MM-dd",width = 20,isImportField = "true_st")
    private Date pubDate;
    @Excel(name = "描述",width = 30,isImportField = "true_st")
    private String description;
}
