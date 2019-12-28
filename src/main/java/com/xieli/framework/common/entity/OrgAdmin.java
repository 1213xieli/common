package com.xieli.framework.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author L.WL
 *
 */
public class OrgAdmin {
    private String id;

    private String name;

    private String number;

    private String description;

    private String simplename;

    private Integer isgrouping;

    private Date effectdate;

    private Date invaliddate;

    private Integer isfreeze;

    private Integer iscompanyorgunit;

    private Integer isadminorgunit;

    private Integer issaleorgunit;

    private Integer ispurchaseorgunit;

    private Integer isstorageorgunit;

    private Integer isprofitorgunit;

    private Integer iscostorgunit;

    private Integer iscu;

    private Integer isunion;

    private Integer ishrorgunit;

    private String creatorid;

    private Date createtime;

    private String lastupdateuserid;

    private Date lastupdatetime;

    private String controlunitid;

    private Integer isleaf;

    private Integer level;

    private String longnumber;

    private String parentid;

    private Integer isentity;

    private Integer isvirtual;

    private String responpositionid;

    private String layertypeid;

    private Integer index;

    private String adminaddress;

    private Integer issealup;

    private Integer isstart;

    private Integer isousealup;

    private String displayname;

    private Date propertysealupdate;

    private String versionnumber;

    private String code;

    private Integer istransportorgunit;

    private Integer isqualityorgunit;

    private String sortcode;

    private Integer economictype;

    private Integer ischurchyard;

    private Integer isjuridicalcompany;

    private Integer isindependence;

    private Date effdt;

    private Date leffdt;

    private String delegatehrid;

    private String orgfunctionid;

    private String companyid;

    private String departmentid;

    private Integer isstartshr;

    private String orgtypestr;

    //子行政组织
  	private List<OrgAdmin> children = new ArrayList<OrgAdmin>();

	public List<OrgAdmin> getChildren() {
		return children;
	}

	public void setChildren(List<OrgAdmin> children) {
		this.children = children;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSimplename() {
        return simplename;
    }

    public void setSimplename(String simplename) {
        this.simplename = simplename;
    }

    public Integer getIsgrouping() {
        return isgrouping;
    }

    public void setIsgrouping(Integer isgrouping) {
        this.isgrouping = isgrouping;
    }

    public Date getEffectdate() {
        return effectdate;
    }

    public void setEffectdate(Date effectdate) {
        this.effectdate = effectdate;
    }

    public Date getInvaliddate() {
        return invaliddate;
    }

    public void setInvaliddate(Date invaliddate) {
        this.invaliddate = invaliddate;
    }

    public Integer getIsfreeze() {
        return isfreeze;
    }

    public void setIsfreeze(Integer isfreeze) {
        this.isfreeze = isfreeze;
    }

    public Integer getIscompanyorgunit() {
        return iscompanyorgunit;
    }

    public void setIscompanyorgunit(Integer iscompanyorgunit) {
        this.iscompanyorgunit = iscompanyorgunit;
    }

    public Integer getIsadminorgunit() {
        return isadminorgunit;
    }

    public void setIsadminorgunit(Integer isadminorgunit) {
        this.isadminorgunit = isadminorgunit;
    }

    public Integer getIssaleorgunit() {
        return issaleorgunit;
    }

    public void setIssaleorgunit(Integer issaleorgunit) {
        this.issaleorgunit = issaleorgunit;
    }

    public Integer getIspurchaseorgunit() {
        return ispurchaseorgunit;
    }

    public void setIspurchaseorgunit(Integer ispurchaseorgunit) {
        this.ispurchaseorgunit = ispurchaseorgunit;
    }

    public Integer getIsstorageorgunit() {
        return isstorageorgunit;
    }

    public void setIsstorageorgunit(Integer isstorageorgunit) {
        this.isstorageorgunit = isstorageorgunit;
    }

    public Integer getIsprofitorgunit() {
        return isprofitorgunit;
    }

    public void setIsprofitorgunit(Integer isprofitorgunit) {
        this.isprofitorgunit = isprofitorgunit;
    }

    public Integer getIscostorgunit() {
        return iscostorgunit;
    }

    public void setIscostorgunit(Integer iscostorgunit) {
        this.iscostorgunit = iscostorgunit;
    }

    public Integer getIscu() {
        return iscu;
    }

    public void setIscu(Integer iscu) {
        this.iscu = iscu;
    }

    public Integer getIsunion() {
        return isunion;
    }

    public void setIsunion(Integer isunion) {
        this.isunion = isunion;
    }

    public Integer getIshrorgunit() {
        return ishrorgunit;
    }

    public void setIshrorgunit(Integer ishrorgunit) {
        this.ishrorgunit = ishrorgunit;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLastupdateuserid() {
        return lastupdateuserid;
    }

    public void setLastupdateuserid(String lastupdateuserid) {
        this.lastupdateuserid = lastupdateuserid;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getControlunitid() {
        return controlunitid;
    }

    public void setControlunitid(String controlunitid) {
        this.controlunitid = controlunitid;
    }

    public Integer getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Integer isleaf) {
        this.isleaf = isleaf;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLongnumber() {
        return longnumber;
    }

    public void setLongnumber(String longnumber) {
        this.longnumber = longnumber;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Integer getIsentity() {
        return isentity;
    }

    public void setIsentity(Integer isentity) {
        this.isentity = isentity;
    }

    public Integer getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(Integer isvirtual) {
        this.isvirtual = isvirtual;
    }

    public String getResponpositionid() {
        return responpositionid;
    }

    public void setResponpositionid(String responpositionid) {
        this.responpositionid = responpositionid;
    }

    public String getLayertypeid() {
        return layertypeid;
    }

    public void setLayertypeid(String layertypeid) {
        this.layertypeid = layertypeid;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getAdminaddress() {
        return adminaddress;
    }

    public void setAdminaddress(String adminaddress) {
        this.adminaddress = adminaddress;
    }

    public Integer getIssealup() {
        return issealup;
    }

    public void setIssealup(Integer issealup) {
        this.issealup = issealup;
    }

    public Integer getIsstart() {
        return isstart;
    }

    public void setIsstart(Integer isstart) {
        this.isstart = isstart;
    }

    public Integer getIsousealup() {
        return isousealup;
    }

    public void setIsousealup(Integer isousealup) {
        this.isousealup = isousealup;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Date getPropertysealupdate() {
        return propertysealupdate;
    }

    public void setPropertysealupdate(Date propertysealupdate) {
        this.propertysealupdate = propertysealupdate;
    }

    public String getVersionnumber() {
        return versionnumber;
    }

    public void setVersionnumber(String versionnumber) {
        this.versionnumber = versionnumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIstransportorgunit() {
        return istransportorgunit;
    }

    public void setIstransportorgunit(Integer istransportorgunit) {
        this.istransportorgunit = istransportorgunit;
    }

    public Integer getIsqualityorgunit() {
        return isqualityorgunit;
    }

    public void setIsqualityorgunit(Integer isqualityorgunit) {
        this.isqualityorgunit = isqualityorgunit;
    }

    public String getSortcode() {
        return sortcode;
    }

    public void setSortcode(String sortcode) {
        this.sortcode = sortcode;
    }

    public Integer getEconomictype() {
        return economictype;
    }

    public void setEconomictype(Integer economictype) {
        this.economictype = economictype;
    }

    public Integer getIschurchyard() {
        return ischurchyard;
    }

    public void setIschurchyard(Integer ischurchyard) {
        this.ischurchyard = ischurchyard;
    }

    public Integer getIsjuridicalcompany() {
        return isjuridicalcompany;
    }

    public void setIsjuridicalcompany(Integer isjuridicalcompany) {
        this.isjuridicalcompany = isjuridicalcompany;
    }

    public Integer getIsindependence() {
        return isindependence;
    }

    public void setIsindependence(Integer isindependence) {
        this.isindependence = isindependence;
    }

    public Date getEffdt() {
        return effdt;
    }

    public void setEffdt(Date effdt) {
        this.effdt = effdt;
    }

    public Date getLeffdt() {
        return leffdt;
    }

    public void setLeffdt(Date leffdt) {
        this.leffdt = leffdt;
    }

    public String getDelegatehrid() {
        return delegatehrid;
    }

    public void setDelegatehrid(String delegatehrid) {
        this.delegatehrid = delegatehrid;
    }

    public String getOrgfunctionid() {
        return orgfunctionid;
    }

    public void setOrgfunctionid(String orgfunctionid) {
        this.orgfunctionid = orgfunctionid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public Integer getIsstartshr() {
        return isstartshr;
    }

    public void setIsstartshr(Integer isstartshr) {
        this.isstartshr = isstartshr;
    }

    public String getOrgtypestr() {
        return orgtypestr;
    }

    public void setOrgtypestr(String orgtypestr) {
        this.orgtypestr = orgtypestr;
    }
}