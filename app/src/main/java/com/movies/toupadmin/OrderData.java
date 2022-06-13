package com.movies.toupadmin;

public class OrderData {
    String account_no,fullname,order_no,phone,playerid,transctionid,bank,time,colorcode;

    public OrderData() {
    }


    public OrderData(String account_no, String fullname, String order_no, String phone, String playerid, String transctionid, String bank, String time, String colorcode) {
        this.account_no = account_no;
        this.fullname = fullname;
        this.order_no = order_no;
        this.phone = phone;
        this.playerid = playerid;
        this.transctionid = transctionid;
        this.bank = bank;
        this.time = time;
        this.colorcode = colorcode;
    }


    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public String getTransctionid() {
        return transctionid;
    }

    public void setTransctionid(String transctionid) {
        this.transctionid = transctionid;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
    }
}
