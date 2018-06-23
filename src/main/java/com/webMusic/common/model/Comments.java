package com.webMusic.common.model;

import com.alibaba.druid.util.StringUtils;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comments {
	private Integer leve;//当前的评论的层级

    private String uniqueMarker;//唯一标记

    private Integer replyNo;//回复数目

    private String commentsId;

    private String fromUid;

    private String toUid;

    private String topicId;//主题id

    private String topicType;

    private Date createTime;

    private Integer fantasticNum;

    private String content;
    
    private Boolean isPublic;
    
    private UUser fromUser;
    
    private UUser toUser;
    
    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId == null ? null : commentsId.trim();
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid == null ? null : fromUid.trim();
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid == null ? null : toUid.trim();
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType == null ? null : topicType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFantasticNum() {
        return fantasticNum;
    }

    public void setFantasticNum(Integer fantasticNum) {
        this.fantasticNum = fantasticNum;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UUser getFromUser() {
		return fromUser;
	}

	public void setFromUser(UUser fromUser) {
		this.fromUser = fromUser;
	}

	public UUser getToUser() {
		return toUser;
	}

	public void setToUser(UUser toUser) {
		this.toUser = toUser;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getLeve() {
		return leve;
	}

	public void setLeve(Integer leve) {
		this.leve = leve;
	}

    public Integer getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(Integer replyNo) {
        this.replyNo = replyNo;
    }

    public String getUniqueMarker() {
        if(StringUtils.isEmpty(uniqueMarker)){
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
}