package com.xieli.framework.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ManageOrgVo implements Serializable{
    private String fid;

    private String fnumber;

    private Integer fisleaf;

    private Integer flevel;

    private String flongnumber;

    private String fparentid;

    private String fcontrolunitid;

    private String cfadminorgunitid;

    private Integer cfmanageorgunitlevel;

    private String fnameL2;

    private String fdisplaynameL2;

    private List<ManageOrgVo> children = new ArrayList<>();

}