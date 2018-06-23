package com.webMusic.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SongListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SongListExample() {
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

        public Criteria andSongListIdIsNull() {
            addCriterion("song_list_id is null");
            return (Criteria) this;
        }

        public Criteria andSongListIdIsNotNull() {
            addCriterion("song_list_id is not null");
            return (Criteria) this;
        }

        public Criteria andSongListIdEqualTo(String value) {
            addCriterion("song_list_id =", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdNotEqualTo(String value) {
            addCriterion("song_list_id <>", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdGreaterThan(String value) {
            addCriterion("song_list_id >", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdGreaterThanOrEqualTo(String value) {
            addCriterion("song_list_id >=", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdLessThan(String value) {
            addCriterion("song_list_id <", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdLessThanOrEqualTo(String value) {
            addCriterion("song_list_id <=", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdLike(String value) {
            addCriterion("song_list_id like", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdNotLike(String value) {
            addCriterion("song_list_id not like", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdIn(List<String> values) {
            addCriterion("song_list_id in", values, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdNotIn(List<String> values) {
            addCriterion("song_list_id not in", values, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdBetween(String value1, String value2) {
            addCriterion("song_list_id between", value1, value2, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdNotBetween(String value1, String value2) {
            addCriterion("song_list_id not between", value1, value2, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongUrlIsNull() {
            addCriterion("song_url is null");
            return (Criteria) this;
        }

        public Criteria andSongUrlIsNotNull() {
            addCriterion("song_url is not null");
            return (Criteria) this;
        }

        public Criteria andSongUrlEqualTo(String value) {
            addCriterion("song_url =", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlNotEqualTo(String value) {
            addCriterion("song_url <>", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlGreaterThan(String value) {
            addCriterion("song_url >", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlGreaterThanOrEqualTo(String value) {
            addCriterion("song_url >=", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlLessThan(String value) {
            addCriterion("song_url <", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlLessThanOrEqualTo(String value) {
            addCriterion("song_url <=", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlLike(String value) {
            addCriterion("song_url like", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlNotLike(String value) {
            addCriterion("song_url not like", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlIn(List<String> values) {
            addCriterion("song_url in", values, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlNotIn(List<String> values) {
            addCriterion("song_url not in", values, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlBetween(String value1, String value2) {
            addCriterion("song_url between", value1, value2, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlNotBetween(String value1, String value2) {
            addCriterion("song_url not between", value1, value2, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongNameIsNull() {
            addCriterion("song_name is null");
            return (Criteria) this;
        }

        public Criteria andSongNameIsNotNull() {
            addCriterion("song_name is not null");
            return (Criteria) this;
        }

        public Criteria andSongNameEqualTo(String value) {
            addCriterion("song_name =", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotEqualTo(String value) {
            addCriterion("song_name <>", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameGreaterThan(String value) {
            addCriterion("song_name >", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameGreaterThanOrEqualTo(String value) {
            addCriterion("song_name >=", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameLessThan(String value) {
            addCriterion("song_name <", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameLessThanOrEqualTo(String value) {
            addCriterion("song_name <=", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameLike(String value) {
            addCriterion("song_name like", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotLike(String value) {
            addCriterion("song_name not like", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameIn(List<String> values) {
            addCriterion("song_name in", values, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotIn(List<String> values) {
            addCriterion("song_name not in", values, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameBetween(String value1, String value2) {
            addCriterion("song_name between", value1, value2, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotBetween(String value1, String value2) {
            addCriterion("song_name not between", value1, value2, "songName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdIsNull() {
            addCriterion("song_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdIsNotNull() {
            addCriterion("song_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdEqualTo(String value) {
            addCriterion("song_classify_id =", value, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdNotEqualTo(String value) {
            addCriterion("song_classify_id <>", value, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdGreaterThan(String value) {
            addCriterion("song_classify_id >", value, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdGreaterThanOrEqualTo(String value) {
            addCriterion("song_classify_id >=", value, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdLessThan(String value) {
            addCriterion("song_classify_id <", value, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdLessThanOrEqualTo(String value) {
            addCriterion("song_classify_id <=", value, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdLike(String value) {
            addCriterion("song_classify_id like", value, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdNotLike(String value) {
            addCriterion("song_classify_id not like", value, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdIn(List<String> values) {
            addCriterion("song_classify_id in", values, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdNotIn(List<String> values) {
            addCriterion("song_classify_id not in", values, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdBetween(String value1, String value2) {
            addCriterion("song_classify_id between", value1, value2, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongClassifyIdNotBetween(String value1, String value2) {
            addCriterion("song_classify_id not between", value1, value2, "songClassifyId");
            return (Criteria) this;
        }

        public Criteria andSongSingerIsNull() {
            addCriterion("song_singer is null");
            return (Criteria) this;
        }

        public Criteria andSongSingerIsNotNull() {
            addCriterion("song_singer is not null");
            return (Criteria) this;
        }

        public Criteria andSongSingerEqualTo(String value) {
            addCriterion("song_singer =", value, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerNotEqualTo(String value) {
            addCriterion("song_singer <>", value, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerGreaterThan(String value) {
            addCriterion("song_singer >", value, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerGreaterThanOrEqualTo(String value) {
            addCriterion("song_singer >=", value, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerLessThan(String value) {
            addCriterion("song_singer <", value, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerLessThanOrEqualTo(String value) {
            addCriterion("song_singer <=", value, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerLike(String value) {
            addCriterion("song_singer like", value, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerNotLike(String value) {
            addCriterion("song_singer not like", value, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerIn(List<String> values) {
            addCriterion("song_singer in", values, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerNotIn(List<String> values) {
            addCriterion("song_singer not in", values, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerBetween(String value1, String value2) {
            addCriterion("song_singer between", value1, value2, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongSingerNotBetween(String value1, String value2) {
            addCriterion("song_singer not between", value1, value2, "songSinger");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlIsNull() {
            addCriterion("song_picture_url is null");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlIsNotNull() {
            addCriterion("song_picture_url is not null");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlEqualTo(String value) {
            addCriterion("song_picture_url =", value, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlNotEqualTo(String value) {
            addCriterion("song_picture_url <>", value, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlGreaterThan(String value) {
            addCriterion("song_picture_url >", value, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlGreaterThanOrEqualTo(String value) {
            addCriterion("song_picture_url >=", value, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlLessThan(String value) {
            addCriterion("song_picture_url <", value, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlLessThanOrEqualTo(String value) {
            addCriterion("song_picture_url <=", value, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlLike(String value) {
            addCriterion("song_picture_url like", value, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlNotLike(String value) {
            addCriterion("song_picture_url not like", value, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlIn(List<String> values) {
            addCriterion("song_picture_url in", values, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlNotIn(List<String> values) {
            addCriterion("song_picture_url not in", values, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlBetween(String value1, String value2) {
            addCriterion("song_picture_url between", value1, value2, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongPictureUrlNotBetween(String value1, String value2) {
            addCriterion("song_picture_url not between", value1, value2, "songPictureUrl");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadIsNull() {
            addCriterion("song_open_download is null");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadIsNotNull() {
            addCriterion("song_open_download is not null");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadEqualTo(String value) {
            addCriterion("song_open_download =", value, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadNotEqualTo(String value) {
            addCriterion("song_open_download <>", value, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadGreaterThan(String value) {
            addCriterion("song_open_download >", value, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadGreaterThanOrEqualTo(String value) {
            addCriterion("song_open_download >=", value, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadLessThan(String value) {
            addCriterion("song_open_download <", value, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadLessThanOrEqualTo(String value) {
            addCriterion("song_open_download <=", value, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadLike(String value) {
            addCriterion("song_open_download like", value, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadNotLike(String value) {
            addCriterion("song_open_download not like", value, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadIn(List<String> values) {
            addCriterion("song_open_download in", values, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadNotIn(List<String> values) {
            addCriterion("song_open_download not in", values, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadBetween(String value1, String value2) {
            addCriterion("song_open_download between", value1, value2, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenDownloadNotBetween(String value1, String value2) {
            addCriterion("song_open_download not between", value1, value2, "songOpenDownload");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicIsNull() {
            addCriterion("song_open_public is null");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicIsNotNull() {
            addCriterion("song_open_public is not null");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicEqualTo(String value) {
            addCriterion("song_open_public =", value, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicNotEqualTo(String value) {
            addCriterion("song_open_public <>", value, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicGreaterThan(String value) {
            addCriterion("song_open_public >", value, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicGreaterThanOrEqualTo(String value) {
            addCriterion("song_open_public >=", value, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicLessThan(String value) {
            addCriterion("song_open_public <", value, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicLessThanOrEqualTo(String value) {
            addCriterion("song_open_public <=", value, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicLike(String value) {
            addCriterion("song_open_public like", value, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicNotLike(String value) {
            addCriterion("song_open_public not like", value, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicIn(List<String> values) {
            addCriterion("song_open_public in", values, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicNotIn(List<String> values) {
            addCriterion("song_open_public not in", values, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicBetween(String value1, String value2) {
            addCriterion("song_open_public between", value1, value2, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongOpenPublicNotBetween(String value1, String value2) {
            addCriterion("song_open_public not between", value1, value2, "songOpenPublic");
            return (Criteria) this;
        }

        public Criteria andSongClickIsNull() {
            addCriterion("song_click is null");
            return (Criteria) this;
        }

        public Criteria andSongClickIsNotNull() {
            addCriterion("song_click is not null");
            return (Criteria) this;
        }

        public Criteria andSongClickEqualTo(Long value) {
            addCriterion("song_click =", value, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongClickNotEqualTo(Long value) {
            addCriterion("song_click <>", value, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongClickGreaterThan(Long value) {
            addCriterion("song_click >", value, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongClickGreaterThanOrEqualTo(Long value) {
            addCriterion("song_click >=", value, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongClickLessThan(Long value) {
            addCriterion("song_click <", value, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongClickLessThanOrEqualTo(Long value) {
            addCriterion("song_click <=", value, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongClickIn(List<Long> values) {
            addCriterion("song_click in", values, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongClickNotIn(List<Long> values) {
            addCriterion("song_click not in", values, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongClickBetween(Long value1, Long value2) {
            addCriterion("song_click between", value1, value2, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongClickNotBetween(Long value1, Long value2) {
            addCriterion("song_click not between", value1, value2, "songClick");
            return (Criteria) this;
        }

        public Criteria andSongTimeIsNull() {
            addCriterion("song_time is null");
            return (Criteria) this;
        }

        public Criteria andSongTimeIsNotNull() {
            addCriterion("song_time is not null");
            return (Criteria) this;
        }

        public Criteria andSongTimeEqualTo(String value) {
            addCriterion("song_time =", value, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeNotEqualTo(String value) {
            addCriterion("song_time <>", value, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeGreaterThan(String value) {
            addCriterion("song_time >", value, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeGreaterThanOrEqualTo(String value) {
            addCriterion("song_time >=", value, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeLessThan(String value) {
            addCriterion("song_time <", value, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeLessThanOrEqualTo(String value) {
            addCriterion("song_time <=", value, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeLike(String value) {
            addCriterion("song_time like", value, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeNotLike(String value) {
            addCriterion("song_time not like", value, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeIn(List<String> values) {
            addCriterion("song_time in", values, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeNotIn(List<String> values) {
            addCriterion("song_time not in", values, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeBetween(String value1, String value2) {
            addCriterion("song_time between", value1, value2, "songTime");
            return (Criteria) this;
        }

        public Criteria andSongTimeNotBetween(String value1, String value2) {
            addCriterion("song_time not between", value1, value2, "songTime");
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

        public Criteria andReleaseTimeIsNull() {
            addCriterion("release_time is null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNotNull() {
            addCriterion("release_time is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeEqualTo(Date value) {
            addCriterion("release_time =", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotEqualTo(Date value) {
            addCriterion("release_time <>", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThan(Date value) {
            addCriterion("release_time >", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("release_time >=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThan(Date value) {
            addCriterion("release_time <", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("release_time <=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIn(List<Date> values) {
            addCriterion("release_time in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotIn(List<Date> values) {
            addCriterion("release_time not in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeBetween(Date value1, Date value2) {
            addCriterion("release_time between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("release_time not between", value1, value2, "releaseTime");
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