package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/7.
 */
public class Agency {
    private Integer agencyID;
    private String agencyName;
    private LoginUser loginUser;
    private String contactPhone;

    private String contactEmail;
    private String contactQQ;

    private Integer stuNumber;
    private Integer visible;

    public Integer getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(Integer agencyID) {
        this.agencyID = agencyID;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }



    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactQQ() {
        return contactQQ;
    }

    public void setContactQQ(String contactQQ) {
        this.contactQQ = contactQQ;

    }

    public Integer getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(Integer stuNumber) {
        this.stuNumber = stuNumber;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "agencyID=" + agencyID +
                ", agencyName='" + agencyName + '\'' +
                ", loginUser=" + loginUser +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactQQ='" + contactQQ + '\'' +

                ", stuNumber=" + stuNumber +
                ", visible=" + visible +
                '}';
    }
}
