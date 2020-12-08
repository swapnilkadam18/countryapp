package com.swapnil.myapplication.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Translations {
    @SerializedName("br")
    @Expose
    private String br;
    @SerializedName("de")
    @Expose
    private String de;
    @SerializedName("es")
    @Expose
    private String es;
    @SerializedName("fa")
    @Expose
    private String fa;
    @SerializedName("fr")
    @Expose
    private String fr;
    @SerializedName("hr")
    @Expose
    private String hr;
    @SerializedName("it")
    @Expose
    private String it;
    @SerializedName("ja")
    @Expose
    private String ja;
    @SerializedName("nl")
    @Expose
    private String nl;
    @SerializedName("pt")
    @Expose
    private String pt;

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }
}
