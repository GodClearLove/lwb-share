package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article")
public class Article implements Serializable {
    private Integer id;
    private String title;
    @Column(name = "insert_img")
    private String insertImg;
    private String content;
    @Column(name = "pub_date")
    private Date pubDate;
    @Column(name = "guru_id")
    private Integer guruId;
    @Transient
    private Guru guru;
}
