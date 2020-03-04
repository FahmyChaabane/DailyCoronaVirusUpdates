package com.chaabane.virus.corona;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

public class CoronaResponse {
    private List<LocationStats> list= null;
    private int totateff = 0;
    public String lastUpdate;

    public CoronaResponse() {
    }

    public CoronaResponse(List<LocationStats> list, int totateff, String lastUpdate) {
        this.list = list;
        this.totateff = totateff;
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<LocationStats> getList() {
        return list;
    }

    public void setList(List<LocationStats> list) {
        this.list = list;
    }

    public int getTotateff() {
        return totateff;
    }

    public void setTotateff(int totateff) {
        this.totateff = totateff;
    }

    @Override
    public String toString() {
        return "CoronaResponse{" +
                "list=" + list +
                ", totateff=" + totateff +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
