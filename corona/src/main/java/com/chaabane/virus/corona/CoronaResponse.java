package com.chaabane.virus.corona;

import java.util.List;

public class CoronaResponse {
    private List<LocationStats> list= null;
    private int totateff = 0;

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
                '}';
    }
}
