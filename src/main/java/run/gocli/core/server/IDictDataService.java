package run.gocli.core.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import run.gocli.admin.req.AddDictDataReq;
import run.gocli.admin.req.DictDataReq;
import run.gocli.core.entity.DictData;


public interface IDictDataService {
    IPage<DictData> getDictType(DictDataReq request);
    Boolean addDictType(AddDictDataReq request);
    Boolean editDictType(AddDictDataReq request);
    Boolean delDictType(String ids);
}
