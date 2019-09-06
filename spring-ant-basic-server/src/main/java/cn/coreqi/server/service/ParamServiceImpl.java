package cn.coreqi.server.service;

import cn.coreqi.server.entity.ParamAuthorityType;
import cn.coreqi.server.repository.entityMapper.ParamAuthorityTypeMapper;
import cn.coreqi.server.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamServiceImpl implements ParamService {
    @Autowired
    private ParamAuthorityTypeMapper authorityTypeMapper;

    @Override
    public List<ParamAuthorityType> getAuthorityTypeList() {
        return authorityTypeMapper.selectByExample(null);
    }
}
