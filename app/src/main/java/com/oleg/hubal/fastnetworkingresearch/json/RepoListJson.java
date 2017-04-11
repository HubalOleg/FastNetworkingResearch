package com.oleg.hubal.fastnetworkingresearch.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 11.04.2017.
 */

public class RepoListJson {
    @SerializedName("repos")
    @Expose
    public List<Repo> repos = null;

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }
}
