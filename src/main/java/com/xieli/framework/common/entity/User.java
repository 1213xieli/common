package com.xieli.framework.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class User implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String number;  //用户名，唯一标识

	private Integer type;  //用户类型 系统用户=10,职员=20,客户=30,供应商=40,其他=50,认证管理员=60,审计管理员=70

	private String name;  //用户名

	private String description;  //描述

	private String password;  //加密以后的密码

	private Integer isdelete;  //是否是已经删除

	private Integer islocked;  //是否锁定(登入多次失败后锁定)

	private Integer forbidden;  //是否禁用用户

	private Date effectivedate;  //用户生效时间

	private Date invalidationdate;  //用户失效时间

	private String defaultlocale;  //缺省使用语言

	private String isregister;  //是否注册

	private Integer errcount;  //登录错误次数。记录错误次数，如果正确一次就恢复置0。

	private String groupid;  //所属用户组

	private String personid;  //对应的操作人，连接属性

	private String securityid;  //密码策略

	private Date pweffectivedate;  //密码生效日期

	private Date lockedtime;  //用户锁定时间

	private Integer isbizadmin;  //是否为业务管理员

	private int ischangedpw;  //是否修改过密码

	private String deforgunitid;  //缺省组织

	private String controlunitid;  //

	private String creatorid;  //创建者

	private Date createtime;  //创建时间

	private String lastupdateuserid;  //最后修改者

	private Date lastupdatetime;  //最后修改时间

	private String customerid;  //客户id

	private String supplierid;  //供应商id

	private String mainroleid;  //主角色

	private Integer agentuser;  //代理用户

	private Integer loginauthorway;  //登陆认证方式

	private String pwdhisstr;  //历史密码

	private String referid;  //引用id

	private String cell;  //手机号码

	private String backupemail;  //备用电子邮件

	private String homephone;  //家庭电话

	private String officephone;  //办公室电话

	private String email;  //电子邮件

	private String addecimal;

	private String namepinyin;

	private String nameshortpinyin;

	private String pinyin;

	private String shortpinyin;

	private int isactivate;

	private String uid;  //云平台id

	private Integer issyntocloud;  //是否同步到云平台

    private String hintQuestion;

    private String hintAnswer;

    private Integer userSex;

    private Integer userAge;

    private String cardNo;

    private String userDept;

    private String userOrg;

    private String userJob;

    private String parentId;

}