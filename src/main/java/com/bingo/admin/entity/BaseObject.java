package com.bingo.admin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * 统一定义entity基类.
 *
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. 子类可重载getId()函数重定义id的列名映射和生成策略. 子类需要实现
 * toString(), equals() and hashCode().
 *
 * @author bingo
 */
@MappedSuperclass
public abstract class BaseObject implements Serializable {


        protected Long id;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonProperty
        // @GeneratedValue(strategy = GenerationType.IDENTITY)
        // @GeneratedValue(strategy = GenerationType.SEQUENCE)
        // @GeneratedValue(generator = "system-uuid")
        // @GenericGenerator(name = "system-uuid", strategy = "uuid")
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        /**
         * Returns a multi-line String with key=value pairs.
         *
         * @return a String representation of this class.
         */
        @Override
        public abstract String toString();

        /**
         * Compares object equality. When using Hibernate, the primary key should
         * not be a part of this comparison.
         *
         * @param o
         *            object to compare to
         * @return true/false based on equality tests
         */
        @Override
        public abstract boolean equals(Object o);

        /**
         * When you override equals, you should override hashCode. See "Why are
         * equals() and hashCode() importation" for more information:
         * http://www.hibernate.org/109.html
         * http://www.91linux.com/html/article/program/java/20080530/11875.html
         *
         * @return hashCode
         */
        @Override
        public abstract int hashCode();

        /**
         * 数据采集过程标记业务+orm唯一约束，非持久化
         */
        protected Integer sessCode;

        @Transient
        public Integer getSessCode() {
            return sessCode;
        }

        public void setSessCode(Integer sessCode) {
            this.sessCode = sessCode;
        }
}
