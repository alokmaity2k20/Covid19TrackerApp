package com.am.covid19tracker;

import java.io.Serializable;

public class StateHelper implements Serializable {
    private String statename,totalcases,totalrecovered,totaldeaths,active,todaycases,todayrecovred,todaydeaths,todayactive;

    public StateHelper() {
    }

    public StateHelper(String statename, String totalcases, String totalrecovered, String totaldeaths, String active, String todaycases, String todayrecovred, String todaydeaths, String todayactive) {
        this.statename = statename;
        this.totalcases = totalcases;
        this.totalrecovered = totalrecovered;
        this.totaldeaths = totaldeaths;
        this.active = active;
        this.todaycases = todaycases;
        this.todayrecovred = todayrecovred;
        this.todaydeaths = todaydeaths;
        this.todayactive = todayactive;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
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

    public String getTodayactive() {
        return todayactive;
    }

    public void setTodayactive(String todayactive) {
        this.todayactive = todayactive;
    }
}
