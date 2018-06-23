package com.webMusic.common.model;

public class SongSheetList {
    private String id;

    private String songSheetId;

    private String songListId;

    private Boolean songOpne;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSongSheetId() {
        return songSheetId;
    }

    public void setSongSheetId(String songSheetId) {
        this.songSheetId = songSheetId == null ? null : songSheetId.trim();
    }

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId == null ? null : songListId.trim();
    }

    public Boolean getSongOpne() {
        return songOpne;
    }

    public void setSongOpne(Boolean songOpne) {
        this.songOpne = songOpne;
    }
}