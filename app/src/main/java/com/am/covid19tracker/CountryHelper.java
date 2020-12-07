package com.am.covid19tracker;

import java.io.Serializable;

public class CountryHelper implements Serializable {
    private String flag,countryname,totalcases,totalrecovered,totaldeaths,active,todaycases,todayrecovred,todaydeaths;


    public CountryHelper() {
    }

    public CountryHelper(String countryname, String totalcases, String totalrecovered, String totaldeaths, String active, String todaycases, String todayrecovred, String todaydeaths,String flag) {
        this.countryname = countryname;
        this.totalcases = totalcases;
        this.totalrecovered = totalrecovered;
        this.totaldeaths = totaldeaths;
        this.active = active;
        this.todaycases = todaycases;
        this.todayrecovred = todayrecovred;
        this.todaydeaths = todaydeaths;
        this.flag=flag;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getTotalcases() {
        return totalcases;
    }

    public void setTotalcases(String totalcases) {
        this.totalcases = totalcases;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(String totalrecovered) {
        this.totalrecovered = totalrecovered;
    }

    public String getTotaldeaths() {
        return totaldeaths;
    }

    public void setTotaldeaths(String totaldeaths) {
        this.totaldeaths = totaldeaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getTodayrecovred() {
        return todayrecovred;
    }

    public void setTodayrecovred(String todayrecovred) {
        this.todayrecovred = todayrecovred;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
