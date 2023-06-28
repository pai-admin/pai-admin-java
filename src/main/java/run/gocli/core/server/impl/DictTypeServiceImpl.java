package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import run.gocli.admin.req.AddDictTypeReq;
import run.gocli.admin.vo.AddDictTypeVo;
import run.gocli.admin.vo.AddMenuVo;
import run.gocli.admin.vo.DictTypeListVo;
import run.gocli.core.dao.DictTypeDao;
import run.gocli.core.entity.Dept;
import run.gocli.core.entity.DictData;
import run.gocli.core.entity.DictType;
import run.gocli.core.server.IDictTypeService;
import run.gocli.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeDao, DictType> implements IDictTypeService {
    @Override
    public List<DictTypeListVo> getDictType() {
        QueryWrapper<DictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("rank");
        List<DictType> dictTypes = list(queryWrapper);
        List<DictTypeListVo> dictTypeListVos = new ArrayList<>();
        dictTypes.forEach(dictType -> {
            DictTypeListVo deptListVo = new DictTypeListVo();
            BeanUtils.copyProperties(dictType , deptListVo);
            dictTypeListVos.add(deptListVo);
        });
        return dictTypeListVos;
    }

    @Override
    public AddDictTypeVo addDictType(AddDictTypeReq request) {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(request , dictType);
        dictType.setCreateTime(DateUtil.getCurrentDateTime());
        boolean res = save(dictType);
        AddDictTypeVo addDictTypeVo = new AddDictTypeVo();
        addDictTypeVo.setTypeId(dictType.getTypeId());
        return res ? addDictTypeVo : null;
    }

    @Override
    public Boolean editDictType(AddDictTypeReq request) {
        System.out.println(request);
        DictType dictType = new DictType();
        BeanUtils.copyProperties(request , dictType);
        dictType.setUpdateTime(DateUtil.getCurrentDateTime());
        if (dictType.getParentId() == null) {
            dictType.setParentId(0);
        }
        return updateById(dictType);
    }

    @Override
    public Boolean delDictType(String ids) {
        return removeBatchByIds(Arrays.asList(ids.split(",")));
    }
}
