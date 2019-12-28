package com.xieli.framework.common.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author L.WL
 *
 */
@Getter
@Setter
public class OrgPosition {
    private String id;

    private String name;

    private String number;

    private Integer index;

    private String adminOrgUnitID;

    private String jobId;

    private String description;

    private String creatorid;

    private Date createtime;

    private String lastupdateuserid;

    private Date lastupdatetime;

    private String hrOrgUnitID;

    private String controlUnitId;

    private Integer deletedStatus;

    private Integer isRespPosition;

    private Integer fluCheckTime;

    private Integer sortCode;

    private Integer isCreateByJob;

    private Date effdt;

    private Date leffdt;

    private String parentId;

    private String standardPositionId;

    private String displayName;

}