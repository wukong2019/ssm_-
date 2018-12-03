package com.itheima.domain;

import lombok.Data;

@Data
public class Traveller {
    private String id;
    private String name;
    private String sex;
    private String phoneNum;
    private Integer credentialsType;//证件类型 0身份证 1护照 2军官证
    private String credentialsTypeStr;
    private String credentialsNum;
    private Integer travellerType;//旅客类型(人群) 0 成人 1 儿童
    private String travellerTypeStr;

    public String getCredentialsTypeStr() {
        if(credentialsType!=null){
            if(credentialsType==0){
                credentialsTypeStr="身份证";
            }
            if(credentialsType==1){
                credentialsTypeStr="护照";
            }
            if(credentialsType==2){
                credentialsTypeStr="军官证";
            }
        }
        return credentialsTypeStr;
    }

    public void setCredentialsTypeStr(String credentialsTypeStr) {
        this.credentialsTypeStr = credentialsTypeStr;
    }

    public String getTravellerTypeStr() {
        if(travellerType!=null){
            if(travellerType==0){
                travellerTypeStr="成人";
            }
            if(travellerType==1){
                travellerTypeStr="儿童";
            }
        }
        return travellerTypeStr;
    }

    public void setTravellerTypeStr(String travellerTypeStr) {
        this.travellerTypeStr = travellerTypeStr;
    }
}
