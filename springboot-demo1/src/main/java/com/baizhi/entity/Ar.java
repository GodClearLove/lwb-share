package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ar")
public class Ar implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @Column(name = "a_id")
    private Integer aid;
    @Column(name = "r_id")
    private Integer rid;
}
