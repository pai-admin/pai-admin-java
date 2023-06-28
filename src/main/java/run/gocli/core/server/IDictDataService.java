package run.gocli.core.server;

import run.gocli.admin.req.AddDictDataReq;
import run.gocli.admin.req.DictDataReq;
import run.gocli.admin.vo.DictDataListVo;

import java.util.List;

public interface IDictDataService {
    List<DictDataListVo> getDictType(DictDataReq request);
    Boolean addDictType(AddDictDataReq request);
    Boolean editDictType(AddDictDataReq request);
    Boolean delDictType(String ids);
}
