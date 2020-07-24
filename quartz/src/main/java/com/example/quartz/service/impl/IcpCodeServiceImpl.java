package com.example.quartz.service.impl;

import com.example.common.bean.quartz.CoustomerZt;
import com.example.common.bean.quartz.IcpCode;
import com.example.common.dynamicds.DS;
import com.example.common.util.redis.CacheRedis;
import com.example.quartz.mapper.IcpCodeMapper;
import com.example.quartz.service.IcpCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * ClassName: IcpCodeServiceImpl
 * Description:
 * date: 2020/7/21 16:22
 *
 * @author zyl
 */
@Service("icpCodeService")
@DS("slave_1")
public class IcpCodeServiceImpl implements IcpCodeService {

    @Autowired
    IcpCodeMapper icpCodeMapper;

    @Autowired
    ICoustomerServiceImpl coustomerService;

    @Override
    @CacheRedis(value = "icp_code",type = "String")
    public List<IcpCode> selectAll() {
        return icpCodeMapper.selectList(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public long insert(IcpCode icpCode,CoustomerZt coustomerZt) {
        int insert = icpCodeMapper.insert(icpCode);
        long insert1 = coustomerService.insert(coustomerZt);
//        int i = 1 / 0;
        return insert+insert1;
    }
}
