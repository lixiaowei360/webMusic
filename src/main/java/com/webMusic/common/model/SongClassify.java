package com.webMusic.common.model;

import com.alibaba.fastjson.annotation.JSONField;

public class SongClassify {

	private Integer hierarchy ;//当前递归深度

	private String classifySort;

	private String classifyHome;

	@JSONField(name="id")
	private String songClassifyId;
    
	@JSONField(name="pId")
    private String pid;

	@JSONField(name="name")
    private String songClassifyName;

    private Boolean isParent;

    private String songSheetNo;

    public String getSongClassifyId() {
        return songClassifyId;
    }

    public void setSongClassifyId(String songClassifyId) {
        this.songClassifyId = songClassifyId == null ? null : songClassifyId.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getSongClassifyName() {
        return songClassifyName;
    }

    public void setSongClassifyName(String songClassifyName) {
        this.songClassifyName = songClassifyName == null ? null : songClassifyName.trim();
    }
    
	public String getClassifySort() {
		return classifySort;
	}

	public void setClassifySort(String classifySort) {
		this.classifySort = classifySort == null ? null : classifySort.trim();
	}

	public String getClassifyHome() {
		return classifyHome;
	}

	public void setClassifyHome(String classifyHome) {
		this.classifyHome = classifyHome == null ? null : classifyHome.trim();
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Integer getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getSongSheetNo() {
		return songSheetNo;
	}

	public void setSongSheetNo(String songSheetNo) {
		this.songSheetNo = songSheetNo;
	}
}