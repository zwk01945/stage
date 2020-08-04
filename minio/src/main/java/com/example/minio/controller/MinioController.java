package com.example.minio.controller;

import com.example.common.bean.ResultObject;
import com.example.common.controller.BaseController;
import com.example.common.util.file.FileUtil;
import com.example.minio.service.ApiUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**************************************************************
 ***       S  T  A  G  E    多模块依赖项目                    ***
 **************************************************************
 *                                                            *
 *         Project Name : base             *
 *                                                            *
 *         File Name : MinioController.java                           *
 *                                                            *
 *         Programmer : Mr.zhang                              *
 *                                                            *
 *         Start Date : 2020/8/3 12:58                       *
 *                                                            *
 *         Last Update : 2020/8/3 12:58                      *
 *                                                            *
 *------------------------------------------------------------*
 * Functions:                                                 *
 *   图片文件服务器控制器                                         *
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */
@RestController
@RequestMapping(value = "/minio")
public class MinioController extends BaseController {

    ApiUpload apiUpload;

    @Autowired
    public void setApiUpload(ApiUpload apiUpload) {
        this.apiUpload = apiUpload;
    }


    @RequestMapping(value = "/upload")
    public ResultObject upload(@RequestParam("path") String filePath,
                               @RequestParam("bucket") String bucket,
                               @RequestParam("obj") String objName,
                               HttpServletRequest request) {
        String contentType = request.getContentType();
        boolean file = StringUtils.isEmpty(filePath);
        boolean bkt = StringUtils.isEmpty(bucket);
        boolean obj = StringUtils.isEmpty(objName);
        boolean cty = StringUtils.isEmpty(contentType);
        if (file || bkt || obj || cty) return new ResultObject(HttpStatus.INTERNAL_SERVER_ERROR.value());
        boolean upload = apiUpload.upload(filePath, bucket, objName,contentType);
        return new ResultObject(HttpStatus.OK.value(),String.valueOf(upload),upload);
    }

    @RequestMapping(value = "/download")
    public void read(@RequestParam("path") String filePath,@RequestParam("bucket") String bucket,
                     @RequestParam("obj") String objName, HttpServletResponse response) {
        apiUpload.download(filePath,bucket, objName);
    }

    @RequestMapping(value = "/url")
    public String url(@RequestParam("bucket") String bucket,
                     @RequestParam("obj") String objName) {
       return apiUpload.getUri(bucket, objName);
    }


    @RequestMapping(value = "/obj")
    public ResultObject uploadByObject(@RequestParam("path") String filePath,
                               @RequestParam("bucket") String bucket,
                               @RequestParam("obj") String objName,
                               HttpServletRequest request) {
        String contentType = request.getContentType();
        boolean file = StringUtils.isEmpty(filePath);
        boolean bkt = StringUtils.isEmpty(bucket);
        boolean obj = StringUtils.isEmpty(objName);
        boolean cty = StringUtils.isEmpty(contentType);
        if (file || bkt || obj || cty) return new ResultObject(HttpStatus.INTERNAL_SERVER_ERROR.value());
        boolean upload = apiUpload.uploadByObject(filePath, bucket, objName,contentType);
        return new ResultObject(HttpStatus.OK.value(),String.valueOf(upload),upload);
    }

    @RequestMapping(value = "/remove")
    public ResultObject removeObject(@RequestParam("bucket") String bucket,
                      @RequestParam("obj") String objName) {
        apiUpload.removeObject(bucket, objName);
        return new ResultObject(HttpStatus.OK.value());
    }

}
