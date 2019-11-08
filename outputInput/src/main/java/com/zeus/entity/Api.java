package com.zeus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tumu
 * @since 2019-11-08
 */
@TableName("tbl_api")
public class Api extends Model<Api> {

    private static final long serialVersionUID = 1L;

        /**
     * 主键，userId
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 手机号
     */
         private String phone;

        /**
     * 手机号
     */
         private String password;

    /**
     * 验证码
     */
         private String token;

        /**
     * 创建时间
     */
         private Timestamp createTime;

    private Timestamp updateTime;


    public Integer getId() {
        return id;
    }

    public Api setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Api setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Api setPassword(String password) {
        this.password = password;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Api setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public Api setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Api{" +
        "id=" + id +
        ", phone=" + phone +
        ", password=" + password +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

    public Api() {
    }

    public Api(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
