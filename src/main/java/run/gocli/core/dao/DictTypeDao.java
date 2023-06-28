package run.gocli.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import run.gocli.core.entity.DictType;

@Mapper
public interface DictTypeDao extends BaseMapper<DictType> {
}
