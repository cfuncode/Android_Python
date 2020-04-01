package com.cjz.android_python.bean;

public class IP {
    private String ip;
    private String location;
    private String operator;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "IP{" +
                "ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
