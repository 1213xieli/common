package com.xieli.framework.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MenuVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

	private String pid;  //父ID

	private String flag;  //唯一标识

	private String name;   //菜单名称

	private Integer sort; //排序

    private String type;  //菜单类型 （0菜单 1按钮）

	private Integer level;   //菜单所在层级

	private Integer isLeaf;  //是否叶子节点

	private String longId;   //包含所有父ID，‘:’隔开

	private Integer isDelete;  //是否删除

	private String description;

	private String serverFlag;   //所属系统标识

	private String creatorId;

	private Date createTime;

	private String lastUpdateId;

	private Date lastUpdateTime;

	private List<MenuVo> children = new ArrayList<>();

}
