package cn.coreqi.server.repository.entityMapper;

import cn.coreqi.server.entity.ParamAuthorityType;
import cn.coreqi.server.entity.ParamAuthorityTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParamAuthorityTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    long countByExample(ParamAuthorityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    int deleteByExample(ParamAuthorityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    int insert(ParamAuthorityType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    int insertSelective(ParamAuthorityType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    List<ParamAuthorityType> selectByExample(ParamAuthorityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    ParamAuthorityType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ParamAuthorityType record, @Param("example") ParamAuthorityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ParamAuthorityType record, @Param("example") ParamAuthorityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ParamAuthorityType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_authority_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ParamAuthorityType record);
}