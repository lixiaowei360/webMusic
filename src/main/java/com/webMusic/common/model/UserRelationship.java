package com.webMusic.common.model;

public class UserRelationship {
    private String relationshipId;

    private Integer type;

    private String targetUserId;

    private String userId;

    private UUser user;
    public String getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(String relationshipId) {
        this.relationshipId = relationshipId == null ? null : relationshipId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId == null ? null : targetUserId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

	public UUser getUser() {
		return user;
	}

	public void setUser(UUser user) {
		this.user = user;
	}
    
}