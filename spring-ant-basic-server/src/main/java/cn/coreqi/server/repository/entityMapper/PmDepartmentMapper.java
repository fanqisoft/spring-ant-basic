package cn.coreqi.server.repository.entityMapper;

import cn.coreqi.server.entity.PmDepartment;
import cn.coreqi.server.entity.PmDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmDepartmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    long countByExample(PmDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    int deleteByExample(PmDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    int insert(PmDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    int insertSelective(PmDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    List<PmDepartment> selectByExample(PmDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    PmDepartment selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") PmDepartment record, @Param("example") PmDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") PmDepartment record, @Param("example") PmDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PmDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_department
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PmDepartment record);
}