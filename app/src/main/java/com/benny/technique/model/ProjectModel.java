package com.benny.technique.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by benny .
 * Time on 2017/3/2 .
 * 描述：
 */

public class ProjectModel {

    private String projectName;
    private int id;

    public ProjectModel(String projectName, int id) {
        this.projectName = projectName;
        this.id = id;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

