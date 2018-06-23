package com.webMusic.common.model;

import java.util.ArrayList;
import java.util.List;

public class SongUserListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SongUserListExample() {
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

        public Criteria andOpenIsNull() {
            addCriterion("open is null");
            return (Criteria) this;
        }

        public Criteria andOpenIsNotNull() {
            addCriterion("open is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEqualTo(String value) {
            addCriterion("open =", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotEqualTo(String value) {
            addCriterion("open <>", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThan(String value) {
            addCriterion("open >", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThanOrEqualTo(String value) {
            addCriterion("open >=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThan(String value) {
            addCriterion("open <", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThanOrEqualTo(String value) {
            addCriterion("open <=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLike(String value) {
            addCriterion("open like", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotLike(String value) {
            addCriterion("open not like", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenIn(List<String> values) {
            addCriterion("open in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotIn(List<String> values) {
            addCriterion("open not in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenBetween(String value1, String value2) {
            addCriterion("open between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotBetween(String value1, String value2) {
            addCriterion("open not between", value1, value2, "open");
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