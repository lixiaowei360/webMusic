package com.webMusic.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hamcrest.core.Is;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


public class SongSheet {

    private String songSheetId;

    private String userId;

    private String songIntroduce;

    private String songClassifyIds;

    private String songPicture;

    private String commentsId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String songSheetName;

    private Long songSheetClick;

    private boolean iCollect;

    private UUser user;

    private SongClassify songClassify;

    private List<SongList> songLists;

    @JsonIgnore
    private MultipartFile picture;

    public String getSongSheetId() {
        return songSheetId;
    }

    public void setSongSheetId(String songSheetId) {
        this.songSheetId = songSheetId == null ? null : songSheetId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getSongIntroduce() {
        return songIntroduce;
    }

    public void setSongIntroduce(String songIntroduce) {
        this.songIntroduce = songIntroduce == null ? null : songIntroduce.trim();
    }

    public String getSongClassifyIds() {
        return songClassifyIds;
    }

    public void setSongClassifyIds(String songClassifyIds) {
        this.songClassifyIds = songClassifyIds == null ? null : songClassifyIds.trim();
    }

    public String getSongPicture() {
        return songPicture;
    }

    public void setSongPicture(String songPicture) {
        this.songPicture = songPicture == null ? null : songPicture.trim();
    }

    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId == null ? null : commentsId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public UUser getUser() {
        return user;
    }

    public void setUser(UUser user) {
        this.user = user;
    }

    public SongClassify getSongClassify() {
        return songClassify;
    }

    public void setSongClassify(SongClassify songClassify) {
        this.songClassify = songClassify;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public List<SongList> getSongLists() {
        return songLists;
    }

    public void setSongLists(List<SongList> songLists) {
        this.songLists = songLists;
    }

    public String getSongSheetName() {
        return songSheetName;
    }

    public void setSongSheetName(String songSheetName) {
        this.songSheetName = songSheetName;
    }

    public Long getSongSheetClick() {
        return songSheetClick;
    }

    public void setSongSheetClick(Long songSheetClick) {
        this.songSheetClick = songSheetClick;
    }

    public boolean getiCollect() {
        return iCollect;
    }

    public void setiCollect(boolean iCollect) {
        this.iCollect = iCollect;
    }
}