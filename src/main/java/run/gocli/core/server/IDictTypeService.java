package run.gocli.core.server;

import run.gocli.admin.req.AddDictTypeReq;
import run.gocli.admin.vo.DictTypeListVo;

import java.util.List;

public interface IDictTypeService {
    List<DictTypeListVo> getDictType();
    Boolean addDictType(AddDictTypeReq request);
    Boolean editDictType(AddDictTypeReq request);
    Boolean delDictType(String ids);
}
