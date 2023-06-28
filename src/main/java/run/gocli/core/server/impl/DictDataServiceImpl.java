package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import run.gocli.admin.req.AddDictDataReq;
import run.gocli.admin.req.DictDataReq;
import run.gocli.admin.vo.DictDataListVo;
import run.gocli.admin.vo.DictTypeListVo;
import run.gocli.core.dao.DictDataDao;
import run.gocli.core.entity.Dept;
import run.gocli.core.entity.DictData;
import run.gocli.core.entity.DictType;
import run.gocli.core.server.IDictDataService;
import run.gocli.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataDao, DictData> implements IDictDataService {
    @Override
    public List<DictDataListVo> getDictType(DictDataReq request) {
        QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", request.getTypeId());
        queryWrapper.orderByDesc("rank");
        List<DictData> dictDataList = list(queryWrapper);
        List<DictDataListVo> dictTypeListVos = new ArrayList<>();
        dictDataList.forEach(dictData -> {
            DictDataListVo deptListVo = new DictDataListVo();
            BeanUtils.copyProperties(dictData , deptListVo);
            dictTypeListVos.add(deptListVo);
        });
        return dictTypeListVos;
    }

    @Override
    public Boolean addDictType(AddDictDataReq request) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(request , dictData);
        dictData.setCreateTime(DateUtil.getCurrentDateTime());
        return save(dictData);
    }

    @Override
    public Boolean editDictType(AddDictDataReq request) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(request , dictData);
        dictData.setUpdateTime(DateUtil.getCurrentDateTime());
        return updateById(dictData);
    }

    @Override
    public Boolean delDictType(String ids) {
        return removeBatchByIds(Arrays.asList(ids.split(",")));
    }
}
