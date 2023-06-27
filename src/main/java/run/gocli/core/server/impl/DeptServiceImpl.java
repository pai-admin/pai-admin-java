package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import run.gocli.admin.req.AddDeptReq;
import run.gocli.admin.req.DeptReq;
import run.gocli.admin.vo.DeptListVo;
import run.gocli.core.dao.DeptDao;
import run.gocli.core.entity.Dept;
import run.gocli.core.server.IDeptServer;
import run.gocli.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements IDeptServer {
    @Override
    public List<DeptListVo> getDeptList(DeptReq request) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        if (request.getDeptName() != null && !request.getDeptName().equals("")) {
            queryWrapper.like("dept_name", request.getDeptName());
        }
        List<Dept> depts = list(queryWrapper);
        List<DeptListVo> deptListVos = new ArrayList<>();
        depts.forEach(dept -> {
            DeptListVo deptListVo = new DeptListVo();
            BeanUtils.copyProperties(dept , deptListVo);
            deptListVos.add(deptListVo);
        });
        return deptListVos;
    }

    @Override
    public Boolean delDept(String ids) {
        return removeBatchByIds(Arrays.asList(ids.split(",")));
    }

    @Override
    public Boolean editDept(AddDeptReq request) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(request , dept);
        dept.setUpdateTime(DateUtil.getCurrentDateTime());
        return updateById(dept);
    }

    @Override
    public Boolean addDept(AddDeptReq request) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(request , dept);
        dept.setCreateTime(DateUtil.getCurrentDateTime());
        return save(dept);
    }
}
