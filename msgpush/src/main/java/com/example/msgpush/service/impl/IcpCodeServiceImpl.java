package com.example.msgpush.service.impl;

import com.example.common.bean.quartz.IcpCode;
import com.example.common.aop.annotation.DS;
import com.example.msgpush.mapper.IcpCodeMapper;
import com.example.msgpush.service.IcpCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public List<IcpCode> selectAll() {
        return icpCodeMapper.selectList(null);
    }

}
