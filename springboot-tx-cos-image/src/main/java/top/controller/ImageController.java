package top.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.util.TencentUploadUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bz
 * @date 2020/9/23
 */
@RestController
@RequestMapping("/")
public class ImageController {

    /**
     * 测试客户端
     * @return
     */
    @GetMapping("/getClient")
    public String getClient(){
        return TencentUploadUtil.getClient().toString();
    }

    /**
     * 上传单个照片
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile) throws Exception{
        return TencentUploadUtil.upload(multipartFile);
    }

    /**
     * 批量上传图片
     * @param multipartFiles
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadBatch")
    public List<String> uploadBatch(@RequestParam("file")MultipartFile[] multipartFiles) throws Exception{
        List<String> urls = new ArrayList<>();
        for(MultipartFile multipartFile:multipartFiles){
            String url = TencentUploadUtil.upload(multipartFile);
            urls.add(url);
        }
        return urls;
    }

}


























