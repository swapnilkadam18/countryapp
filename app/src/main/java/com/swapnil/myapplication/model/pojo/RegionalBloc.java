package com.swapnil.myapplication.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class RegionalBloc {
    @SerializedName("acronym")
    @Expose
    private String acronym;
    @SerializedName("name")
    @Expose
    private String name;

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
