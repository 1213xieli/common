package com.xieli.framework.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class Server implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

	private String flag;  //系统唯一标识

	private String name;  //系统名称

	private String corpid;   //企业微信ID

	private String corpsecret; //企业微信密钥

	private String publicid;   //微信公众号ID

	private String publicsecret; //微信公众号密钥

	private String url;   // 前端域名 http://xxx.zhengbang.com

	private String index;   //系统首页

	private String auth;    //验证用户是否具有系统权限地址

	private Integer type;  //系统类型：0内部系统，1外部系统

	private Integer isPrivate;   //是否私有 （是需要验证角色）

	private Integer actType;   //系统分类：1、综合；2养殖；3农牧、4供应链、5生化、6兽药、7金融

	private String icon;   //图标

	private Integer isDelete;  //是否删除

	private String description;

	private String creatorId;

	private Date createTime;

	private String lastUpdateId;

	private Date lastUpdateTime;

}
