//package com.hl;
//
//import com.google.gson.Gson;
//import com.qiniu.common.QiniuException;
//import com.qiniu.http.Response;
//import com.qiniu.storage.Configuration;
//import com.qiniu.storage.Region;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.storage.model.DefaultPutRet;
//import com.qiniu.util.Auth;
//import lombok.Setter;
//import org.junit.Test;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.*;
//
///**
// * @Author : hupo, 创建于:2023/3/18
// */
//@SpringBootTest
//@Setter
//@ConfigurationProperties(prefix = "oss")
//public class OssTest {
//    String accessKey;
//    String secretKey;
//    String bucket;
//
//    @Test
//    public void test(){
//        //构造一个带指定 Region 对象的配置类
//        Configuration cfg = new Configuration(Region.huanan());
//        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//        //...其他参数参考类注释
//
//        UploadManager uploadManager = new UploadManager(cfg);
//        //...生成上传凭证，然后准备上传
////        String accessKey = "";
////        String secretKey = "";
////        String bucket = "";
//
//        //默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = "2022/hl.png";
//
//        try {
////            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
////            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
//
//            InputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Pictures\\Camera Roll\\1113555B2-21.png");
//
//            Auth auth = Auth.create(accessKey, secretKey);
//            String upToken = auth.uploadToken(bucket);
//
//            try {
//                Response response = uploadManager.put(inputStream,key,upToken,null, null);
//                //解析上传成功的结果
//                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
//            } catch (QiniuException ex) {
//                Response r = ex.response;
//                System.err.println(r.toString());
//                try {
//                    System.err.println(r.bodyString());
//                } catch (QiniuException ex2) {
//                    //ignore
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
