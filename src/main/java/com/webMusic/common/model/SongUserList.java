package com.webMusic.common.model;

public class SongUserList {
    private String songListId;

    private String songUrl;

    private String songName;

    private String songClassifyId;

    private String userId;

    private String open;

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId == null ? null : songListId.trim();
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl == null ? null : songUrl.trim();
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName == null ? null : songName.trim();
    }

    public String getSongClassifyId() {
        return songClassifyId;
    }

    public void setSongClassifyId(String songClassifyId) {
        this.songClassifyId = songClassifyId == null ? null : songClassifyId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open == null ? null : open.trim();
    }
}