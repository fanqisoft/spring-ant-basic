package cn.coreqi.server.service;

import cn.coreqi.server.core.MainUtil;
import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.entity.PmDepartment;
import cn.coreqi.server.entity.PmDepartmentExample;
import cn.coreqi.server.repository.entityMapper.PmDepartmentMapper;
import cn.coreqi.server.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PmDepartmentMapper departmentMapper;


    @Override
    public PageInfo<PmDepartment> getList(String name, String description, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize(), MainUtil.GetPageHelperOrdersStr(pageable.getSort()));
        PmDepartmentExample example = new PmDepartmentExample();
        PmDepartmentExample.Criteria criteria = example.createCriteria();
        if (!Strings.isNullOrEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!Strings.isNullOrEmpty(description)) {
            criteria.andDescriptionLike("%" + description + "%");
        }
        return new PageInfo<>(departmentMapper.selectByExample(example));
    }

    @Override
    public List<PmDepartment> getAll() {
        return departmentMapper.selectByExample(null);
    }

    @Override
    public List<PmDepartment> getAll(String departmentId) {
        var example = new PmDepartmentExample();
        example.createCriteria().andIdEqualTo(departmentId);
        return departmentMapper.selectByExample(example);
    }

    @Override
    public PmDepartment getById(String id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PmDepartment create(PmDepartment entity) throws ProjectException {
        entity.setId(UUID.randomUUID().toString());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());

        int affectRow = departmentMapper.insert(entity);
        if (affectRow == 0) {
            throw new ProjectException("创建失败");
        }

        return departmentMapper.selectByPrimaryKey(entity.getId());
    }

    @Override
    public PmDepartment update(PmDepartment entity) throws ProjectException {
        PmDepartment updateEntity = departmentMapper.selectByPrimaryKey(entity.getId());

        updateEntity.setName(entity.getName());
        updateEntity.setFullPath(entity.getFullPath());
        updateEntity.setParentId(entity.getParentId());
        updateEntity.setDescription(entity.getDescription());
        updateEntity.setUpdateTime(LocalDateTime.now());

        int affectRow = departmentMapper.updateByPrimaryKey(updateEntity);
        if (affectRow == 0) {
            throw new ProjectException("更新失败");
        }
        return departmentMapper.selectByPrimaryKey(updateEntity.getId());
    }

    @Override
    public PmDepartment delete(String id) throws ProjectException {
        PmDepartment deleteData = getById(id);
        if (deleteData == null) {
            throw new ProjectException("数据不存在,请刷新重试");
        }

        int affectRow = departmentMapper.deleteByPrimaryKey(deleteData.getId());
        if (affectRow == 0) {
            throw new ProjectException("删除失败");
        }
        return deleteData;
    }
}


