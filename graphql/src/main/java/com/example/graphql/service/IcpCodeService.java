package com.example.graphql.service;

import com.example.common.bean.quartz.IcpCode;

import java.util.List;

/**
 * ClassName: IcpCodeService
 * Description:
 * date: 2020/7/21 16:21
 *
 * @author zyl
 */
public interface IcpCodeService {
    List<IcpCode> selectAll();

}
