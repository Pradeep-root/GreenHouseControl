package com.example.firenear.greenhousecontrol.model;

/**
 * Created by pradeep on 16/10/17.
 */

public class WebLink {

    private String title;
    private String basicInfo;
    private String url;

    public WebLink() {
        this.title = "";
        this.basicInfo = "";
        this.url = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
