package cn.coreqi.server.entity;

import java.time.LocalDateTime;

public class PmDepartment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.row_vesion
     *
     * @mbg.generated
     */
    private LocalDateTime rowVesion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.summary
     *
     * @mbg.generated
     */
    private String summary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.full_path
     *
     * @mbg.generated
     */
    private String fullPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.parent_id
     *
     * @mbg.generated
     */
    private String parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_department.order_code
     *
     * @mbg.generated
     */
    private String orderCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.id
     *
     * @return the value of pm_department.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.id
     *
     * @param id the value for pm_department.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.row_vesion
     *
     * @return the value of pm_department.row_vesion
     *
     * @mbg.generated
     */
    public LocalDateTime getRowVesion() {
        return rowVesion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.row_vesion
     *
     * @param rowVesion the value for pm_department.row_vesion
     *
     * @mbg.generated
     */
    public void setRowVesion(LocalDateTime rowVesion) {
        this.rowVesion = rowVesion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.create_time
     *
     * @return the value of pm_department.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.create_time
     *
     * @param createTime the value for pm_department.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.update_time
     *
     * @return the value of pm_department.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.update_time
     *
     * @param updateTime the value for pm_department.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.name
     *
     * @return the value of pm_department.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.name
     *
     * @param name the value for pm_department.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.description
     *
     * @return the value of pm_department.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.description
     *
     * @param description the value for pm_department.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.summary
     *
     * @return the value of pm_department.summary
     *
     * @mbg.generated
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.summary
     *
     * @param summary the value for pm_department.summary
     *
     * @mbg.generated
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.full_path
     *
     * @return the value of pm_department.full_path
     *
     * @mbg.generated
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.full_path
     *
     * @param fullPath the value for pm_department.full_path
     *
     * @mbg.generated
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath == null ? null : fullPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.parent_id
     *
     * @return the value of pm_department.parent_id
     *
     * @mbg.generated
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.parent_id
     *
     * @param parentId the value for pm_department.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_department.order_code
     *
     * @return the value of pm_department.order_code
     *
     * @mbg.generated
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_department.order_code
     *
     * @param orderCode the value for pm_department.order_code
     *
     * @mbg.generated
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }
}