package com.bingo.admin.commons.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FilterBuilder<T> {

    /**
     * 条件列表
     */
    private List<SpecificationOperator> opers;

    /**
     * 构造函数，初始化的条件是and
     */
    public FilterBuilder(String key,String oper,Object value) {
        SpecificationOperator so = new SpecificationOperator();
        so.setJoin("and");
        so.setKey("1");
        so.setOper("=");
        so.setValue("1");
        opers = new ArrayList<SpecificationOperator>();
        opers.add(so);
    }

    public FilterBuilder() {
        opers = new ArrayList<SpecificationOperator>();
    }

    /**
     * 完成条件的添加
     * @return
     */
    public FilterBuilder add(String key,String oper,Object value,String join) {
        SpecificationOperator so = new SpecificationOperator();
        if (StringUtils.isNotEmpty(key)){
            so.setKey(key);
        }else{
            return this;
        }
        if (value!=null&&!"".equals(value)){
            so.setValue(value);
        }else {
            return this;
        }
        if (StringUtils.isNotEmpty(oper)){
            so.setOper(oper);
        }else{
            so.setOper("=");
        }
        so.setJoin(join);
        opers.add(so);
        return this;
    }

    /**
     * 添加or条件的重载
     * @return this，方便后续的链式调用
     */
    public FilterBuilder addOr(String key,String oper,Object value) {
        return this.add(key,oper,value,"or");
    }

    /**
     * 添加and的条件
     * @return
     */
    public FilterBuilder add(String key,String oper,Object value) {
        return this.add(key,oper,value,"and");
    }

    public Specification buildFilter() {
        Specification<T> specification = new SimpleSpecification<T>(opers);
        return specification;
    }
}