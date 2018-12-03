package com.itheima.domain;

import com.itheima.utils.DateUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class Orders {
    private String id;
    private String orderNum;
    private Date orderTime;
    private String orderTimeStr;
    private Integer orderStatus;//订单状态(0 未支付 1 已支付)
    private  String orderStatusStr;
    private Integer peopleCount;
    private Product product;
    private List<Traveller> travellers;
    private Member member;
    private Integer payType;//支付方式(0 支付宝 1 微信 2其它)
    private String payTypeStr;
    private String orderDesc;

    public String getOrderTimeStr() {
        orderTimeStr= DateUtils.Date2String(orderTime,"yyyy-MM-dd HH:mm");
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public String getOrderStatusStr() {
        if(orderStatus!=null){
            if(orderStatus==0){
                orderStatusStr="未支付";
            }
            if(orderStatus==1){
                orderStatusStr="已支付";
            }
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getPayTypeStr() {
        if(payType!=null){
            if(payType==0){
                payTypeStr="支付宝";
            }
            if(payType==1){
                payTypeStr="微信";
            }
            if(payType==2){
                payTypeStr="其他";
            }
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }
}
