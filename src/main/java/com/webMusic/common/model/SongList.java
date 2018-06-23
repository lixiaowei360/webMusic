package com.webMusic.common.model;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongList {
    private String uniqueMarker;

    private String songListId;

    private String songUrl;

    private String songName;

    private String songClassifyId;

    private String songLyric;

    private String songSinger;
    
    private String songPictureUrl;

    private String songOpenDownload;

    private String songOpenPublic;
    
    private Long songClick;
    
    private String songTime;

    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    private String orderBy;

    private String songSheetId; //歌单id仅用于前天展示不关联数据库

    @JsonIgnore
    private MultipartFile song;
    
    @JsonIgnore
    private MultipartFile picture;
    
    private SongClassify songClassify = new SongClassify();

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

    public String getSongLyric() {
        return songLyric;
    }

    public void setSongLyric(String songLyric) {
        this.songLyric = songLyric == null ? null : songLyric.trim();
    }

    public String getSongSinger() {
        return songSinger;
    }

    public void setSongSinger(String songSinger) {
        this.songSinger = songSinger == null ? null : songSinger.trim();
    }

	public SongClassify getSongClassify() {
		return songClassify;
	}

	public void setSongClassify(SongClassify songClassify) {
		this.songClassify = songClassify;
	}

	public MultipartFile getSong() {
		return song;
	}

	public void setSong(MultipartFile song) {
		this.song = song;
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public String getSongPictureUrl() {
		return songPictureUrl;
	}

	public void setSongPictureUrl(String songPictureUrl) {
		this.songPictureUrl = songPictureUrl;
	}

	public String getSongOpenDownload() {
		return songOpenDownload;
	}

	public void setSongOpenDownload(String songOpenDownload) {
		this.songOpenDownload = songOpenDownload;
	}

	public String getSongOpenPublic() {
		return songOpenPublic;
	}

	public void setSongOpenPublic(String songOpenPublic) {
		this.songOpenPublic = songOpenPublic;
	}

	public Long getSongClick() {
		return songClick;
	}

	public void setSongClick(Long songClick) {
		this.songClick = songClick;
	}

	public String getSongTime() {
		return songTime;
	}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setSongTime(String songTime) {
		this.songTime = songTime;
	}
    public String getUniqueMarker(){
        if(StringUtils.isEmpty(uniqueMarker)) {
            String str = UUID. randomUUID().toString().replaceAll("\\s*", "");
            str = str.replaceAll("-","");
            Pattern pattern = Pattern.compile("[\\d]");
            Matcher matcher = pattern.matcher(str);
            this.uniqueMarker = matcher.replaceAll("").trim();
        }
        return this.uniqueMarker;
    }
    public void setUniqueMarker(String uniqueMarker) {
        this.uniqueMarker = uniqueMarker;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSongSheetId() {
        return songSheetId;
    }

    public void setSongSheetId(String songSheetId) {
        this.songSheetId = songSheetId;
    }
}