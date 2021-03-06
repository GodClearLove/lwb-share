package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String phone;
    private String password;
    private String salt;
    private String sign;
    @Column(name = "head_pic")
    private String headPic;
    private String name;
    private String dharma;
    private Integer sex;
    private String province;
    private String city;
    private Integer status;
    @Column(name = "reg_date")
    private Date regDate;
}
