package com.example.punch.model.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PunchRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public PunchRecordExample() {
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

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andPunchDayIsNull() {
            addCriterion("punch_day is null");
            return (Criteria) this;
        }

        public Criteria andPunchDayIsNotNull() {
            addCriterion("punch_day is not null");
            return (Criteria) this;
        }

        public Criteria andPunchDayEqualTo(Date value) {
            addCriterionForJDBCDate("punch_day =", value, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("punch_day <>", value, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchDayGreaterThan(Date value) {
            addCriterionForJDBCDate("punch_day >", value, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("punch_day >=", value, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchDayLessThan(Date value) {
            addCriterionForJDBCDate("punch_day <", value, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("punch_day <=", value, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchDayIn(List<Date> values) {
            addCriterionForJDBCDate("punch_day in", values, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("punch_day not in", values, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("punch_day between", value1, value2, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("punch_day not between", value1, value2, "punchDay");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeIsNull() {
            addCriterion("punch_on_time is null");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeIsNotNull() {
            addCriterion("punch_on_time is not null");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeEqualTo(Date value) {
            addCriterion("punch_on_time =", value, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeNotEqualTo(Date value) {
            addCriterion("punch_on_time <>", value, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeGreaterThan(Date value) {
            addCriterion("punch_on_time >", value, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("punch_on_time >=", value, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeLessThan(Date value) {
            addCriterion("punch_on_time <", value, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeLessThanOrEqualTo(Date value) {
            addCriterion("punch_on_time <=", value, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeIn(List<Date> values) {
            addCriterion("punch_on_time in", values, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeNotIn(List<Date> values) {
            addCriterion("punch_on_time not in", values, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeBetween(Date value1, Date value2) {
            addCriterion("punch_on_time between", value1, value2, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOnTimeNotBetween(Date value1, Date value2) {
            addCriterion("punch_on_time not between", value1, value2, "punchOnTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeIsNull() {
            addCriterion("punch_off_time is null");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeIsNotNull() {
            addCriterion("punch_off_time is not null");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeEqualTo(Date value) {
            addCriterion("punch_off_time =", value, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeNotEqualTo(Date value) {
            addCriterion("punch_off_time <>", value, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeGreaterThan(Date value) {
            addCriterion("punch_off_time >", value, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("punch_off_time >=", value, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeLessThan(Date value) {
            addCriterion("punch_off_time <", value, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeLessThanOrEqualTo(Date value) {
            addCriterion("punch_off_time <=", value, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeIn(List<Date> values) {
            addCriterion("punch_off_time in", values, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeNotIn(List<Date> values) {
            addCriterion("punch_off_time not in", values, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeBetween(Date value1, Date value2) {
            addCriterion("punch_off_time between", value1, value2, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchOffTimeNotBetween(Date value1, Date value2) {
            addCriterion("punch_off_time not between", value1, value2, "punchOffTime");
            return (Criteria) this;
        }

        public Criteria andPunchStatusIsNull() {
            addCriterion("punch_status is null");
            return (Criteria) this;
        }

        public Criteria andPunchStatusIsNotNull() {
            addCriterion("punch_status is not null");
            return (Criteria) this;
        }

        public Criteria andPunchStatusEqualTo(Byte value) {
            addCriterion("punch_status =", value, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchStatusNotEqualTo(Byte value) {
            addCriterion("punch_status <>", value, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchStatusGreaterThan(Byte value) {
            addCriterion("punch_status >", value, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("punch_status >=", value, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchStatusLessThan(Byte value) {
            addCriterion("punch_status <", value, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchStatusLessThanOrEqualTo(Byte value) {
            addCriterion("punch_status <=", value, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchStatusIn(List<Byte> values) {
            addCriterion("punch_status in", values, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchStatusNotIn(List<Byte> values) {
            addCriterion("punch_status not in", values, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchStatusBetween(Byte value1, Byte value2) {
            addCriterion("punch_status between", value1, value2, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("punch_status not between", value1, value2, "punchStatus");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrIsNull() {
            addCriterion("punch_on_addr is null");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrIsNotNull() {
            addCriterion("punch_on_addr is not null");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrEqualTo(String value) {
            addCriterion("punch_on_addr =", value, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrNotEqualTo(String value) {
            addCriterion("punch_on_addr <>", value, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrGreaterThan(String value) {
            addCriterion("punch_on_addr >", value, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrGreaterThanOrEqualTo(String value) {
            addCriterion("punch_on_addr >=", value, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrLessThan(String value) {
            addCriterion("punch_on_addr <", value, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrLessThanOrEqualTo(String value) {
            addCriterion("punch_on_addr <=", value, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrLike(String value) {
            addCriterion("punch_on_addr like", value, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrNotLike(String value) {
            addCriterion("punch_on_addr not like", value, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrIn(List<String> values) {
            addCriterion("punch_on_addr in", values, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrNotIn(List<String> values) {
            addCriterion("punch_on_addr not in", values, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrBetween(String value1, String value2) {
            addCriterion("punch_on_addr between", value1, value2, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOnAddrNotBetween(String value1, String value2) {
            addCriterion("punch_on_addr not between", value1, value2, "punchOnAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrIsNull() {
            addCriterion("punch_off_addr is null");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrIsNotNull() {
            addCriterion("punch_off_addr is not null");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrEqualTo(String value) {
            addCriterion("punch_off_addr =", value, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrNotEqualTo(String value) {
            addCriterion("punch_off_addr <>", value, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrGreaterThan(String value) {
            addCriterion("punch_off_addr >", value, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrGreaterThanOrEqualTo(String value) {
            addCriterion("punch_off_addr >=", value, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrLessThan(String value) {
            addCriterion("punch_off_addr <", value, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrLessThanOrEqualTo(String value) {
            addCriterion("punch_off_addr <=", value, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrLike(String value) {
            addCriterion("punch_off_addr like", value, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrNotLike(String value) {
            addCriterion("punch_off_addr not like", value, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrIn(List<String> values) {
            addCriterion("punch_off_addr in", values, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrNotIn(List<String> values) {
            addCriterion("punch_off_addr not in", values, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrBetween(String value1, String value2) {
            addCriterion("punch_off_addr between", value1, value2, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andPunchOffAddrNotBetween(String value1, String value2) {
            addCriterion("punch_off_addr not between", value1, value2, "punchOffAddr");
            return (Criteria) this;
        }

        public Criteria andOnRemarkIsNull() {
            addCriterion("on_remark is null");
            return (Criteria) this;
        }

        public Criteria andOnRemarkIsNotNull() {
            addCriterion("on_remark is not null");
            return (Criteria) this;
        }

        public Criteria andOnRemarkEqualTo(String value) {
            addCriterion("on_remark =", value, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkNotEqualTo(String value) {
            addCriterion("on_remark <>", value, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkGreaterThan(String value) {
            addCriterion("on_remark >", value, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("on_remark >=", value, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkLessThan(String value) {
            addCriterion("on_remark <", value, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkLessThanOrEqualTo(String value) {
            addCriterion("on_remark <=", value, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkLike(String value) {
            addCriterion("on_remark like", value, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkNotLike(String value) {
            addCriterion("on_remark not like", value, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkIn(List<String> values) {
            addCriterion("on_remark in", values, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkNotIn(List<String> values) {
            addCriterion("on_remark not in", values, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkBetween(String value1, String value2) {
            addCriterion("on_remark between", value1, value2, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOnRemarkNotBetween(String value1, String value2) {
            addCriterion("on_remark not between", value1, value2, "onRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkIsNull() {
            addCriterion("off_remark is null");
            return (Criteria) this;
        }

        public Criteria andOffRemarkIsNotNull() {
            addCriterion("off_remark is not null");
            return (Criteria) this;
        }

        public Criteria andOffRemarkEqualTo(String value) {
            addCriterion("off_remark =", value, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkNotEqualTo(String value) {
            addCriterion("off_remark <>", value, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkGreaterThan(String value) {
            addCriterion("off_remark >", value, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("off_remark >=", value, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkLessThan(String value) {
            addCriterion("off_remark <", value, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkLessThanOrEqualTo(String value) {
            addCriterion("off_remark <=", value, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkLike(String value) {
            addCriterion("off_remark like", value, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkNotLike(String value) {
            addCriterion("off_remark not like", value, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkIn(List<String> values) {
            addCriterion("off_remark in", values, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkNotIn(List<String> values) {
            addCriterion("off_remark not in", values, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkBetween(String value1, String value2) {
            addCriterion("off_remark between", value1, value2, "offRemark");
            return (Criteria) this;
        }

        public Criteria andOffRemarkNotBetween(String value1, String value2) {
            addCriterion("off_remark not between", value1, value2, "offRemark");
            return (Criteria) this;
        }

        public Criteria andCreatedIdIsNull() {
            addCriterion("created_id is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIdIsNotNull() {
            addCriterion("created_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedIdEqualTo(Integer value) {
            addCriterion("created_id =", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdNotEqualTo(Integer value) {
            addCriterion("created_id <>", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdGreaterThan(Integer value) {
            addCriterion("created_id >", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("created_id >=", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdLessThan(Integer value) {
            addCriterion("created_id <", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdLessThanOrEqualTo(Integer value) {
            addCriterion("created_id <=", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdIn(List<Integer> values) {
            addCriterion("created_id in", values, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdNotIn(List<Integer> values) {
            addCriterion("created_id not in", values, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdBetween(Integer value1, Integer value2) {
            addCriterion("created_id between", value1, value2, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdNotBetween(Integer value1, Integer value2) {
            addCriterion("created_id not between", value1, value2, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Long value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Long value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Long value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Long value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Long value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Long> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Long> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Long value1, Long value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Long value1, Long value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdIsNull() {
            addCriterion("updated_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdIsNotNull() {
            addCriterion("updated_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdEqualTo(Integer value) {
            addCriterion("updated_id =", value, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdNotEqualTo(Integer value) {
            addCriterion("updated_id <>", value, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdGreaterThan(Integer value) {
            addCriterion("updated_id >", value, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("updated_id >=", value, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdLessThan(Integer value) {
            addCriterion("updated_id <", value, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdLessThanOrEqualTo(Integer value) {
            addCriterion("updated_id <=", value, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdIn(List<Integer> values) {
            addCriterion("updated_id in", values, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdNotIn(List<Integer> values) {
            addCriterion("updated_id not in", values, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdBetween(Integer value1, Integer value2) {
            addCriterion("updated_id between", value1, value2, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedIdNotBetween(Integer value1, Integer value2) {
            addCriterion("updated_id not between", value1, value2, "updatedId");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Long value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Long value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Long value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Long value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Long value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Long> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Long> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Long value1, Long value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Long value1, Long value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
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