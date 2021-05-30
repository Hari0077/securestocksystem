package com.example.securestocksystem;

public class Model {

    String id,productname,cprice,wprice,tstocks;

    public Model()
    {

    }
    public Model(String id,String productname,String cprice,String wprice,String tstocks)
    {
        this.id = id;
        this.productname = productname;
        this.cprice = cprice;
        this.wprice = wprice;
        this.tstocks = tstocks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCprice() {
        return cprice;
    }

    public void setCprice(String cprice) {
        this.cprice = cprice;
    }

    public String getWprice() {
        return wprice;
    }

    public void setWprice(String wprice) {
        this.wprice = wprice;
    }

    public String getTstocks() {
        return tstocks;
    }

    public void setTstocks(String tstocks) {
        this.tstocks = tstocks;
    }
}
