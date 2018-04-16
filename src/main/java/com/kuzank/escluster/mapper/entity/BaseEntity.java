package com.kuzank.escluster.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -8668855393531379578L;

    private Integer id;         // 主键
    private String deleted;     // 是否删除 true or false

    private Integer createdBy;
    private Integer updatedBy;
    private Date createdAt;
    private Date updatedAt;

    private String description; // 备注
}
