package com.webMusic.common.model;

import java.util.ArrayList;
import java.util.List;

public class SongClassifyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SongClassifyExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("pid like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("pid not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameIsNull() {
            addCriterion("song_classify_name is null");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameIsNotNull() {
            addCriterion("song_classify_name is not null");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameEqualTo(String value) {
            addCriterion("song_classify_name =", value, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameNotEqualTo(String value) {
            addCriterion("song_classify_name <>", value, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameGreaterThan(String value) {
            addCriterion("song_classify_name >", value, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameGreaterThanOrEqualTo(String value) {
            addCriterion("song_classify_name >=", value, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameLessThan(String value) {
            addCriterion("song_classify_name <", value, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameLessThanOrEqualTo(String value) {
            addCriterion("song_classify_name <=", value, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameLike(String value) {
            addCriterion("song_classify_name like", value, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameNotLike(String value) {
            addCriterion("song_classify_name not like", value, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameIn(List<String> values) {
            addCriterion("song_classify_name in", values, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameNotIn(List<String> values) {
            addCriterion("song_classify_name not in", values, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameBetween(String value1, String value2) {
            addCriterion("song_classify_name between", value1, value2, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andSongClassifyNameNotBetween(String value1, String value2) {
            addCriterion("song_classify_name not between", value1, value2, "songClassifyName");
            return (Criteria) this;
        }

        public Criteria andClassifySortIsNull() {
            addCriterion("classify_sort is null");
            return (Criteria) this;
        }

        public Criteria andClassifySortIsNotNull() {
            addCriterion("classify_sort is not null");
            return (Criteria) this;
        }

        public Criteria andClassifySortEqualTo(String value) {
            addCriterion("classify_sort =", value, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortNotEqualTo(String value) {
            addCriterion("classify_sort <>", value, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortGreaterThan(String value) {
            addCriterion("classify_sort >", value, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortGreaterThanOrEqualTo(String value) {
            addCriterion("classify_sort >=", value, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortLessThan(String value) {
            addCriterion("classify_sort <", value, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortLessThanOrEqualTo(String value) {
            addCriterion("classify_sort <=", value, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortLike(String value) {
            addCriterion("classify_sort like", value, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortNotLike(String value) {
            addCriterion("classify_sort not like", value, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortIn(List<String> values) {
            addCriterion("classify_sort in", values, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortNotIn(List<String> values) {
            addCriterion("classify_sort not in", values, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortBetween(String value1, String value2) {
            addCriterion("classify_sort between", value1, value2, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifySortNotBetween(String value1, String value2) {
            addCriterion("classify_sort not between", value1, value2, "classifySort");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeIsNull() {
            addCriterion("classify_home is null");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeIsNotNull() {
            addCriterion("classify_home is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeEqualTo(String value) {
            addCriterion("classify_home =", value, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeNotEqualTo(String value) {
            addCriterion("classify_home <>", value, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeGreaterThan(String value) {
            addCriterion("classify_home >", value, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeGreaterThanOrEqualTo(String value) {
            addCriterion("classify_home >=", value, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeLessThan(String value) {
            addCriterion("classify_home <", value, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeLessThanOrEqualTo(String value) {
            addCriterion("classify_home <=", value, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeLike(String value) {
            addCriterion("classify_home like", value, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeNotLike(String value) {
            addCriterion("classify_home not like", value, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeIn(List<String> values) {
            addCriterion("classify_home in", values, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeNotIn(List<String> values) {
            addCriterion("classify_home not in", values, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeBetween(String value1, String value2) {
            addCriterion("classify_home between", value1, value2, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andClassifyHomeNotBetween(String value1, String value2) {
            addCriterion("classify_home not between", value1, value2, "classifyHome");
            return (Criteria) this;
        }

        public Criteria andIsParentIsNull() {
            addCriterion("is_parent is null");
            return (Criteria) this;
        }

        public Criteria andIsParentIsNotNull() {
            addCriterion("is_parent is not null");
            return (Criteria) this;
        }

        public Criteria andIsParentEqualTo(Boolean value) {
            addCriterion("is_parent =", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentNotEqualTo(Boolean value) {
            addCriterion("is_parent <>", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentGreaterThan(Boolean value) {
            addCriterion("is_parent >", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_parent >=", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentLessThan(Boolean value) {
            addCriterion("is_parent <", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentLessThanOrEqualTo(Boolean value) {
            addCriterion("is_parent <=", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentIn(List<Boolean> values) {
            addCriterion("is_parent in", values, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentNotIn(List<Boolean> values) {
            addCriterion("is_parent not in", values, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentBetween(Boolean value1, Boolean value2) {
            addCriterion("is_parent between", value1, value2, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_parent not between", value1, value2, "isParent");
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