package com.webMusic.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SongSheetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SongSheetExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSongSheetIdIsNull() {
            addCriterion("song_sheet_id is null");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdIsNotNull() {
            addCriterion("song_sheet_id is not null");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdEqualTo(String value) {
            addCriterion("song_sheet_id =", value, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdNotEqualTo(String value) {
            addCriterion("song_sheet_id <>", value, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdGreaterThan(String value) {
            addCriterion("song_sheet_id >", value, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdGreaterThanOrEqualTo(String value) {
            addCriterion("song_sheet_id >=", value, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdLessThan(String value) {
            addCriterion("song_sheet_id <", value, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdLessThanOrEqualTo(String value) {
            addCriterion("song_sheet_id <=", value, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdLike(String value) {
            addCriterion("song_sheet_id like", value, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdNotLike(String value) {
            addCriterion("song_sheet_id not like", value, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdIn(List<String> values) {
            addCriterion("song_sheet_id in", values, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdNotIn(List<String> values) {
            addCriterion("song_sheet_id not in", values, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdBetween(String value1, String value2) {
            addCriterion("song_sheet_id between", value1, value2, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andSongSheetIdNotBetween(String value1, String value2) {
            addCriterion("song_sheet_id not between", value1, value2, "songSheetId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceIsNull() {
            addCriterion("song_introduce is null");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceIsNotNull() {
            addCriterion("song_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceEqualTo(String value) {
            addCriterion("song_introduce =", value, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceNotEqualTo(String value) {
            addCriterion("song_introduce <>", value, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceGreaterThan(String value) {
            addCriterion("song_introduce >", value, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("song_introduce >=", value, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceLessThan(String value) {
            addCriterion("song_introduce <", value, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceLessThanOrEqualTo(String value) {
            addCriterion("song_introduce <=", value, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceLike(String value) {
            addCriterion("song_introduce like", value, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceNotLike(String value) {
            addCriterion("song_introduce not like", value, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceIn(List<String> values) {
            addCriterion("song_introduce in", values, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceNotIn(List<String> values) {
            addCriterion("song_introduce not in", values, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceBetween(String value1, String value2) {
            addCriterion("song_introduce between", value1, value2, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongIntroduceNotBetween(String value1, String value2) {
            addCriterion("song_introduce not between", value1, value2, "songIntroduce");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsIsNull() {
            addCriterion("song_classify_ids is null");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsIsNotNull() {
            addCriterion("song_classify_ids is not null");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsEqualTo(String value) {
            addCriterion("song_classify_ids =", value, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsNotEqualTo(String value) {
            addCriterion("song_classify_ids <>", value, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsGreaterThan(String value) {
            addCriterion("song_classify_ids >", value, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsGreaterThanOrEqualTo(String value) {
            addCriterion("song_classify_ids >=", value, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsLessThan(String value) {
            addCriterion("song_classify_ids <", value, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsLessThanOrEqualTo(String value) {
            addCriterion("song_classify_ids <=", value, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsLike(String value) {
            addCriterion("song_classify_ids like", value, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsNotLike(String value) {
            addCriterion("song_classify_ids not like", value, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsIn(List<String> values) {
            addCriterion("song_classify_ids in", values, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsNotIn(List<String> values) {
            addCriterion("song_classify_ids not in", values, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsBetween(String value1, String value2) {
            addCriterion("song_classify_ids between", value1, value2, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdsNotBetween(String value1, String value2) {
            addCriterion("song_classify_ids not between", value1, value2, "songClassifyIds");
            return (Criteria) this;
        }

        public Criteria andSongPictureIsNull() {
            addCriterion("song_picture is null");
            return (Criteria) this;
        }

        public Criteria andSongPictureIsNotNull() {
            addCriterion("song_picture is not null");
            return (Criteria) this;
        }

        public Criteria andSongPictureEqualTo(String value) {
            addCriterion("song_picture =", value, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureNotEqualTo(String value) {
            addCriterion("song_picture <>", value, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureGreaterThan(String value) {
            addCriterion("song_picture >", value, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureGreaterThanOrEqualTo(String value) {
            addCriterion("song_picture >=", value, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureLessThan(String value) {
            addCriterion("song_picture <", value, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureLessThanOrEqualTo(String value) {
            addCriterion("song_picture <=", value, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureLike(String value) {
            addCriterion("song_picture like", value, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureNotLike(String value) {
            addCriterion("song_picture not like", value, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureIn(List<String> values) {
            addCriterion("song_picture in", values, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureNotIn(List<String> values) {
            addCriterion("song_picture not in", values, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureBetween(String value1, String value2) {
            addCriterion("song_picture between", value1, value2, "songPicture");
            return (Criteria) this;
        }

        public Criteria andSongPictureNotBetween(String value1, String value2) {
            addCriterion("song_picture not between", value1, value2, "songPicture");
            return (Criteria) this;
        }

        public Criteria andCommentsIdIsNull() {
            addCriterion("comments_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIdIsNotNull() {
            addCriterion("comments_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsIdEqualTo(String value) {
            addCriterion("comments_id =", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdNotEqualTo(String value) {
            addCriterion("comments_id <>", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdGreaterThan(String value) {
            addCriterion("comments_id >", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdGreaterThanOrEqualTo(String value) {
            addCriterion("comments_id >=", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdLessThan(String value) {
            addCriterion("comments_id <", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdLessThanOrEqualTo(String value) {
            addCriterion("comments_id <=", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdLike(String value) {
            addCriterion("comments_id like", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdNotLike(String value) {
            addCriterion("comments_id not like", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdIn(List<String> values) {
            addCriterion("comments_id in", values, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdNotIn(List<String> values) {
            addCriterion("comments_id not in", values, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdBetween(String value1, String value2) {
            addCriterion("comments_id between", value1, value2, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdNotBetween(String value1, String value2) {
            addCriterion("comments_id not between", value1, value2, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameIsNull() {
            addCriterion("song_sheet_name is null");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameIsNotNull() {
            addCriterion("song_sheet_name is not null");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameEqualTo(String value) {
            addCriterion("song_sheet_name =", value, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameNotEqualTo(String value) {
            addCriterion("song_sheet_name <>", value, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameGreaterThan(String value) {
            addCriterion("song_sheet_name >", value, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameGreaterThanOrEqualTo(String value) {
            addCriterion("song_sheet_name >=", value, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameLessThan(String value) {
            addCriterion("song_sheet_name <", value, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameLessThanOrEqualTo(String value) {
            addCriterion("song_sheet_name <=", value, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameLike(String value) {
            addCriterion("song_sheet_name like", value, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameNotLike(String value) {
            addCriterion("song_sheet_name not like", value, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameIn(List<String> values) {
            addCriterion("song_sheet_name in", values, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameNotIn(List<String> values) {
            addCriterion("song_sheet_name not in", values, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameBetween(String value1, String value2) {
            addCriterion("song_sheet_name between", value1, value2, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetNameNotBetween(String value1, String value2) {
            addCriterion("song_sheet_name not between", value1, value2, "songSheetName");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickIsNull() {
            addCriterion("song_sheet_click is null");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickIsNotNull() {
            addCriterion("song_sheet_click is not null");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickEqualTo(Long value) {
            addCriterion("song_sheet_click =", value, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickNotEqualTo(Long value) {
            addCriterion("song_sheet_click <>", value, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickGreaterThan(Long value) {
            addCriterion("song_sheet_click >", value, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickGreaterThanOrEqualTo(Long value) {
            addCriterion("song_sheet_click >=", value, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickLessThan(Long value) {
            addCriterion("song_sheet_click <", value, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickLessThanOrEqualTo(Long value) {
            addCriterion("song_sheet_click <=", value, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickIn(List<Long> values) {
            addCriterion("song_sheet_click in", values, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickNotIn(List<Long> values) {
            addCriterion("song_sheet_click not in", values, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickBetween(Long value1, Long value2) {
            addCriterion("song_sheet_click between", value1, value2, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andSongSheetClickNotBetween(Long value1, Long value2) {
            addCriterion("song_sheet_click not between", value1, value2, "songSheetClick");
            return (Criteria) this;
        }

        public Criteria andICollectIsNull() {
            addCriterion("i_collect is null");
            return (Criteria) this;
        }

        public Criteria andICollectIsNotNull() {
            addCriterion("i_collect is not null");
            return (Criteria) this;
        }

        public Criteria andICollectEqualTo(Boolean value) {
            addCriterion("i_collect =", value, "iCollect");
            return (Criteria) this;
        }

        public Criteria andICollectNotEqualTo(Boolean value) {
            addCriterion("i_collect <>", value, "iCollect");
            return (Criteria) this;
        }

        public Criteria andICollectGreaterThan(Boolean value) {
            addCriterion("i_collect >", value, "iCollect");
            return (Criteria) this;
        }

        public Criteria andICollectGreaterThanOrEqualTo(Boolean value) {
            addCriterion("i_collect >=", value, "iCollect");
            return (Criteria) this;
        }

        public Criteria andICollectLessThan(Boolean value) {
            addCriterion("i_collect <", value, "iCollect");
            return (Criteria) this;
        }

        public Criteria andICollectLessThanOrEqualTo(Boolean value) {
            addCriterion("i_collect <=", value, "iCollect");
            return (Criteria) this;
        }

        public Criteria andICollectIn(List<Boolean> values) {
            addCriterion("i_collect in", values, "iCollect");
            return (Criteria) this;
        }

        public Criteria andICollectNotIn(List<Boolean> values) {
            addCriterion("i_collect not in", values, "iCollect");
            return (Criteria) this;
        }

        public Criteria andICollectBetween(Boolean value1, Boolean value2) {
            addCriterion("i_collect between", value1, value2, "iCollect");
            return (Criteria) this;
        }

        public Criteria andICollectNotBetween(Boolean value1, Boolean value2) {
            addCriterion("i_collect not between", value1, value2, "iCollect");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}