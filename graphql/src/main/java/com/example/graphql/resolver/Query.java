package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.common.bean.quartz.IcpCode;
import com.example.graphql.service.IcpCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**************************************************************
 ***       S  T  A  G  E    多模块依赖项目                    ***
 **************************************************************
 *                                                            *
 *         Project Name : base             *
 *                                                            *
 *         File Name : Query.java                           *
 *                                                            *
 *         Programmer : Mr.zhang                              *
 *                                                            *
 *         Start Date : 2020/8/6 15:59                       *
 *                                                            *
 *         Last Update : 2020/8/6 15:59                      *
 *                                                            *
 *------------------------------------------------------------*
 * Functions:                                                 *
 *   Get_Build_Frame_Count -- Fetches the number of frames in *
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */
@Component
public class Query  implements GraphQLQueryResolver {

    @Autowired
    IcpCodeService icpCodeService;

    public List<IcpCode> icps() {
        return icpCodeService.selectAll();
    }

    public IcpCode icp(Integer id) {
        return icpCodeService.selectAll().stream().filter(x -> x.getId().equals(id)).findFirst().get();
    }

}
