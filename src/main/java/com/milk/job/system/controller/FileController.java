package com.milk.job.system.controller;

import com.google.gson.Gson;
import com.milk.job.common.R;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.common.utils.FileUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-12 10:10
 */

@Slf4j
@RestController
@Api(value = "文件上传",tags = "fileUpload")
@RequestMapping("/file")
public class FileController {

    @Value("${qiniu.accessKey}")
    private String accessKey ;

    @Value("${qiniu.secretKey}")
    private String secretKey ;

    @Value("${qiniu.bucket}")
    private String bucket ;

    @Value("${qiniu.baseUrl}")
    private  String url ;


    @PostMapping("/upload")
    @ApiOperation(value = "上传文件到七牛云")
    public R upload(MultipartFile file){

        log.info("文件名：{}",file.getOriginalFilename());

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String suffix = file.getOriginalFilename().split("\\.")[1];
        if(!FileUtils.isFileAllowed(suffix)){
            return R.fail("不支持的文件类型！");
        }

        String mulu = "jobimg/";
        if (suffix.equals("pdf")){
            mulu = "jobresume/";
        }
        String key = mulu+UUID.randomUUID().toString().replace("-","")+"."+suffix;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(file.getBytes(), key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            return R.fail(r.error);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomerException("网络波动！请重新上传！",500);
        }
        return R.success(url+key);
    }
}
