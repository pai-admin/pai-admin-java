package run.gocli.core.server;

import run.gocli.admin.req.AddDeptReq;
import run.gocli.admin.req.DeptReq;
import run.gocli.admin.vo.DeptListVo;

import java.util.List;

public interface IDeptServer {
    List<DeptListVo> getDeptList(DeptReq request);
    Boolean delDept(String ids);
    Boolean editDept(AddDeptReq request);
    Boolean addDept(AddDeptReq request);
}
