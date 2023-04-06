package com.example.mybestyoutube.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class YoutubeVideo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "categorie")
    private String categorie;

    public YoutubeVideo() {
    }

    public YoutubeVideo(long id, String title, String description, String url, String categorie) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.categorie = categorie;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
