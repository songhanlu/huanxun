package com.bdqn.huanxun.redis.redis_pojo;

/**
 * Created by hp on 2018/1/16.
 */
public class Area {
    private Integer areaID;
    private String name;
    private String type;
    private Integer level;
    private Integer parentID;

    public Integer getAreaID() {
        return areaID;
    }

    public void setAreaID(Integer areaID) {
        this.areaID = areaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaID=" + areaID +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", level=" + level +
                ", parentID=" + parentID +
                '}';
    }
}
