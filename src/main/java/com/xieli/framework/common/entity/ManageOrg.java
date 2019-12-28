package com.xieli.framework.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ManageOrg implements Serializable{
    private String fid;

    private String fnumber;

    private String fsimplename;

    private Integer fisleaf;

    private Integer flevel;

    private String flongnumber;

    private String fcreatorid;

    private Date fcreatetime;

    private String flastupdateuserid;

    private Date flastupdatetime;

    private String fparentid;

    private String fcontrolunitid;

    private String cfadminorgunitid;

    private Integer cfmanageorgunitlevel;

    private String fnameL1;

    private String fnameL2;

    private String fnameL3;

    private String fdescriptionL1;

    private String fdescriptionL2;

    private String fdescriptionL3;

    private String fdisplaynameL1;

    private String fdisplaynameL2;

    private String fdisplaynameL3;
}