package com.xieli.framework.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

	private String flag;  //唯一标识

	private String name;   //权限名称

	private Integer isDefault;  //是否默认 0否 1是

	private Integer isDelete;  //是否删除

	private String description;

	private String serverFlag;   //所属系统标识

	private String creatorId;

	private Date createTime;

	private String lastUpdateId;

	private Date lastUpdateTime;

}
