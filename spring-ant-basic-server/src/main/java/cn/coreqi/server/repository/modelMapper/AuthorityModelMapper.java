package cn.coreqi.server.repository.modelMapper;

import cn.coreqi.server.model.PmAuthorityModel;

import java.util.List;

public interface AuthorityModelMapper {
    List<PmAuthorityModel> selectAll();
}
