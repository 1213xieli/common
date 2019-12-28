package com.xieli.framework.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AdminOrgVO {
	 private String id;

	 private String name;

	 private boolean leaf;

	 private String parentid;

	 private String number;

	 private String longnumber;

	 private String creatorText;

	 private String creatorid;

	 private Date createtime;

	 private String lastupdateuserid;

	 private Date lastupdatetime;

	 private String parentName;

	 private String label;

	 //子行政组织
	 private List<AdminOrgVO> children = new ArrayList<>();

}
