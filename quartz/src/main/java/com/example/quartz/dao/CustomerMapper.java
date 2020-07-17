package com.example.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.bean.CoustomerZt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ClassName: CustomerMapper
 * Description:
 * date: 2020/7/17 16:17
 *
 * @author zyl
 */
@Repository
public interface CustomerMapper extends BaseMapper<CoustomerZt> {
}
