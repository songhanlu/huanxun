package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/15.
 */
public class AgencyEmployee {
    private Integer agencyEmployeeID;
    private LoginUser loginUser;
    private Agency agency;
    private Integer visible;
    private String agencyEmployeeRole;
    private String aeName;
    private String aePhone;
    private String aeQQ;

    @Override
    public String toString() {
        return "AgencyEmployee{" +
                "agencyEmployeeID=" + agencyEmployeeID +
                ", loginUser=" + loginUser +
                ", agency=" + agency +
                ", visible=" + visible +
                ", agencyEmployeeRole='" + agencyEmployeeRole + '\'' +
                ", aeName='" + aeName + '\'' +
                ", aePhone='" + aePhone + '\'' +
                ", aeQQ='" + aeQQ + '\'' +
                '}';
    }

    public String getAeName() {
        return aeName;
    }

    public void setAeName(String aeName) {
        this.aeName = aeName;
    }

    public String getAePhone() {
        return aePhone;
    }

    public void setAePhone(String aePhone) {
        this.aePhone = aePhone;
    }

    public String getAeQQ() {
        return aeQQ;
    }

    public void setAeQQ(String aeQQ) {
        this.aeQQ = aeQQ;
    }

    public Integer getAgencyEmployeeID() {
        return agencyEmployeeID;
    }

    public void setAgencyEmployeeID(Integer agencyEmployeeID) {
        this.agencyEmployeeID = agencyEmployeeID;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getAgencyEmployeeRole() {
        return agencyEmployeeRole;
    }

    public void setAgencyEmployeeRole(String agencyEmployeeRole) {
        this.agencyEmployeeRole = agencyEmployeeRole;
    }
}
