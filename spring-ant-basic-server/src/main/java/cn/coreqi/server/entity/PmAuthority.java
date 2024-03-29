package cn.coreqi.server.entity;

import java.time.LocalDateTime;

public class PmAuthority {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.row_version
     *
     * @mbg.generated
     */
    private LocalDateTime rowVersion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.summary
     *
     * @mbg.generated
     */
    private String summary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.order_code
     *
     * @mbg.generated
     */
    private String orderCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.virtual_url
     *
     * @mbg.generated
     */
    private String virtualUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.parent_id
     *
     * @mbg.generated
     */
    private String parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.type
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.is_del
     *
     * @mbg.generated
     */
    private Boolean isDel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_authority.full_path
     *
     * @mbg.generated
     */
    private String fullPath;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.id
     *
     * @return the value of pm_authority.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.id
     *
     * @param id the value for pm_authority.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.create_time
     *
     * @return the value of pm_authority.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.create_time
     *
     * @param createTime the value for pm_authority.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.update_time
     *
     * @return the value of pm_authority.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.update_time
     *
     * @param updateTime the value for pm_authority.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.row_version
     *
     * @return the value of pm_authority.row_version
     *
     * @mbg.generated
     */
    public LocalDateTime getRowVersion() {
        return rowVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.row_version
     *
     * @param rowVersion the value for pm_authority.row_version
     *
     * @mbg.generated
     */
    public void setRowVersion(LocalDateTime rowVersion) {
        this.rowVersion = rowVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.name
     *
     * @return the value of pm_authority.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.name
     *
     * @param name the value for pm_authority.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.description
     *
     * @return the value of pm_authority.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.description
     *
     * @param description the value for pm_authority.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.summary
     *
     * @return the value of pm_authority.summary
     *
     * @mbg.generated
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.summary
     *
     * @param summary the value for pm_authority.summary
     *
     * @mbg.generated
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.order_code
     *
     * @return the value of pm_authority.order_code
     *
     * @mbg.generated
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.order_code
     *
     * @param orderCode the value for pm_authority.order_code
     *
     * @mbg.generated
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.virtual_url
     *
     * @return the value of pm_authority.virtual_url
     *
     * @mbg.generated
     */
    public String getVirtualUrl() {
        return virtualUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.virtual_url
     *
     * @param virtualUrl the value for pm_authority.virtual_url
     *
     * @mbg.generated
     */
    public void setVirtualUrl(String virtualUrl) {
        this.virtualUrl = virtualUrl == null ? null : virtualUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.parent_id
     *
     * @return the value of pm_authority.parent_id
     *
     * @mbg.generated
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.parent_id
     *
     * @param parentId the value for pm_authority.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.type
     *
     * @return the value of pm_authority.type
     *
     * @mbg.generated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.type
     *
     * @param type the value for pm_authority.type
     *
     * @mbg.generated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.is_del
     *
     * @return the value of pm_authority.is_del
     *
     * @mbg.generated
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.is_del
     *
     * @param isDel the value for pm_authority.is_del
     *
     * @mbg.generated
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_authority.full_path
     *
     * @return the value of pm_authority.full_path
     *
     * @mbg.generated
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_authority.full_path
     *
     * @param fullPath the value for pm_authority.full_path
     *
     * @mbg.generated
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath == null ? null : fullPath.trim();
    }
}