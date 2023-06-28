package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import run.gocli.admin.req.AddDictDataReq;
import run.gocli.admin.req.DictDataReq;
import run.gocli.core.dao.DictDataDao;
import run.gocli.core.entity.DictData;
import run.gocli.core.server.IDictDataService;
import run.gocli.utils.DateUtil;

import java.util.Arrays;

@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataDao, DictData> implements IDictDataService {
    @Override
    public IPage<DictData> getDictType(DictDataReq request) {
        IPage<DictData> iPage = new Page<>(request.getPage(), request.getLimit());
        QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", request.getTypeId());
        queryWrapper.orderByDesc("rank");
        return page(iPage, queryWrapper);
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
