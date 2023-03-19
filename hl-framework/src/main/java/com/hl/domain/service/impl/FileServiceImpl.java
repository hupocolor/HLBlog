package com.hl.domain.service.impl;

import com.google.gson.Gson;
import com.hl.domain.ResponseResult;
import com.hl.domain.service.FileService;
import com.hl.enums.AppHttpCodeEnum;
import com.hl.exception.SysException;
import com.hl.utils.PathUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author : hupo, 创建于:2023/3/18
 */
@Service
@Setter
@ConfigurationProperties(prefix = "oss")
public class FileServiceImpl implements FileService {
    @Override
    public ResponseResult upload(MultipartFile img) {
        //判断文件类型
        //获取原始文件名
        //对文件名进行判断
        String originalFilename = img.getOriginalFilename();
        if (!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")){
            throw new SysException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        String path = PathUtils.getFileOssPath(originalFilename);
        //如果通过上传文件到oss
        String url = uploadOss(img,path);
        return ResponseResult.okResult(url);
    }
    private String accessKey;
    private String secretKey;
    private String bucket;

    private String uploadOss(MultipartFile imgFile, String path){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        //默认不指定key的情况下，以文件内容的hash值作为文件名

        String key = path;

        try {
            InputStream inputStream = imgFile.getInputStream();

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "http://rrpq1gqi6.hn-bkt.clouddn.com/"+key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "ERROR";
    }
}
