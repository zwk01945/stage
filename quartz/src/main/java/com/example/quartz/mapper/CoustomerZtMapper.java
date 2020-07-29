package com.example.quartz.mapper;

import com.example.common.bean.quartz.CoustomerZt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zwk
 * @since 2020-07-20
 */
@Repository
public interface CoustomerZtMapper extends BaseMapper<CoustomerZt> {

    /** 自定义sql 多表连接 */
    List<CoustomerZt> selectJoin();

}
