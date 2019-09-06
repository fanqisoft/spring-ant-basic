package cn.coreqi.server.repository.entityMapper;

import cn.coreqi.server.entity.PmRole;
import cn.coreqi.server.entity.PmRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    long countByExample(PmRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    int deleteByExample(PmRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    int insert(PmRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    int insertSelective(PmRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    List<PmRole> selectByExample(PmRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    PmRole selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") PmRole record, @Param("example") PmRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") PmRole record, @Param("example") PmRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PmRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PmRole record);
}