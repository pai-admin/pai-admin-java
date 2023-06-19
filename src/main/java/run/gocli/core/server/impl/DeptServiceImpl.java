package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.gocli.core.dao.DeptDao;
import run.gocli.core.entity.Dept;
import run.gocli.core.server.IDeptServer;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements IDeptServer {
}
