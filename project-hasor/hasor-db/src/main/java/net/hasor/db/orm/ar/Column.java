/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.db.orm.ar;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Types;
import org.more.builder.ReflectionToStringBuilder;
import org.more.builder.ToStringStyle;
/**
 * 
 * @version : 2014年10月27日
 * @author 赵永春(zyc@hasor.net)
 */
public final class Column implements Serializable {
    private static final long serialVersionUID = 7779635033083814425L;
    private String            name             = null;                //字段名
    private String            title            = null;                //字段title
    private int               sqlType          = Types.NULL;          //SQL类型
    private Field             beanField        = null;                //字段类型
    //
    private boolean           primaryKey       = false;               //主键约束
    private Integer           maxSize          = null;                //字段值大小限制
    private boolean           identify         = false;               //具有自增标识属性的列。
    private boolean           empty            = true;                //非空约束
    private Object            defaultValue     = null;                //默认约束
    private boolean           insert           = true;                //是否允许用于数据新增
    private boolean           update           = true;                //是否允许用于数据更新
    private boolean           deleteWhere      = true;                //是否允许作为删除条件
    private boolean           updateWhere      = true;                //是否允许作为更新条件
    private boolean           ignoreUnset      = true;                //没有给它设置任何值的情况下忽略它作为查询条件
    //
    public Column(String colName, int colSQLType) {
        this.name = colName;
        this.sqlType = colSQLType;
    }
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    //
    //
    /**获取列名*/
    public String getName() {
        return this.name;
    }
    /**获取列标题*/
    public String getTitle() {
        return this.title;
    }
    /**获取列表示的 SQL 类型。
     * @see java.sql.Types*/
    public int getSqlType() {
        return this.sqlType;
    }
    /**字段类型*/
    public Field getBeanField() {
        return this.beanField;
    }
    /**获取列表示的Java类型。*/
    public Class<?> getJavaType() {
        return InnerArUtils.sqlTypeToJavaType(getSqlType());
    }
    //
    /**表示列是否为主键列。*/
    public boolean isPrimaryKey() {
        return this.primaryKey;
    }
    /**字段值内容大小限制（0表示不详）*/
    public int getMaxSize() {
        return this.maxSize == null ? 0 : this.maxSize.intValue();
    }
    /**表示是否具有自增标识属性的列。*/
    public boolean isIdentify() {
        return this.identify;
    }
    /**表示列是否在insert时将实际数据用于新增(true)，如果不是新增将采用默认值约束(false)。*/
    public boolean allowInsert() {
        return this.insert;
    }
    /**表示列是否在update时将实际数据用于更新(true)，如果不是则忽略对该列的更新(false)。*/
    public boolean allowUpdate() {
        return this.update;
    }
    /**表示列是否在delete时将实际数据用于条件查询(true)，如果不是则忽略对该列的条件判断(false)。*/
    public boolean allowDeleteWhere() {
        return this.deleteWhere;
    }
    /**表示列是否在update时将实际数据用于条件查询(true)，如果不是则忽略对该列的条件判断(false)。*/
    public boolean allowUpdateWhere() {
        return this.updateWhere;
    }
    /**表示列是否允许空值出现。*/
    public boolean allowEmpty() {
        return this.empty;
    }
    /**表示默认值约束的值。*/
    public Object getDefaultValue() {
        return this.defaultValue;
    }
    /**没有给它设置任何值的情况下忽略它作为查询条件*/
    public boolean isIgnoreUnset() {
        return ignoreUnset;
    }
    /**设置列标题*/
    protected void setTitle(String title) {
        this.title = title;
    }
    /**设置列名*/
    protected void setName(String name) {
        this.name = name;
    }
    /**字段类型*/
    protected void setBeanField(Field beanField) {
        this.beanField = beanField;
    }
    /**设置修改默认值约束.*/
    protected void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
    /**设置允许用于数据新增*/
    protected void setAllowInsert(boolean insert) {
        this.insert = insert;
    }
    /**设置允许用于数据更新*/
    protected void setAllowUpdate(boolean update) {
        this.update = update;
    }
    /**设置是否允许作为删除条件*/
    protected void setAllowDeleteWhere(boolean deleteWhere) {
        this.deleteWhere = deleteWhere;
    }
    /**设置是否允许作为更新条件*/
    protected void setAllowUpdateWhere(boolean updateWhere) {
        this.updateWhere = updateWhere;
    }
    //
    /**设置是否为主键列*/
    protected void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
    /**设置是否支持自动增长。*/
    protected void setIdentify(boolean identify) {
        this.identify = identify;
    }
    /**设置是否允许为空*/
    protected void setEmpty(boolean empty) {
        this.empty = empty;
    }
    /**设置字段的大小限制*/
    protected void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    /**没有给它设置任何值的情况下忽略它作为查询条件*/
    protected void setIgnoreUnset(boolean ignoreUnset) {
        this.ignoreUnset = ignoreUnset;
    }
}