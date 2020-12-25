package top.util;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Random;

/**
 * @author bz
 * @date 2020/9/23
 */
public class TencentUploadUtil {


    private static String secretId = "AKID82oMK41heLl34TRT3PUIXieJhpSojgDd";

    private static String secretKey = "KYhbIjIfuWqNwIMIzSeaFE6zmIZCailb";

    private static String bucket = "image-1303052158";

    private static String myRegion = "ap-nanjing";

    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    private static final String URL ="https://image-1303052158.cos.ap-nanjing.myqcloud.com/";

    /**
     * 获取客户端
     * @return
     */
    public static COSClient getClient(){
        TencentUploadUtil property = new TencentUploadUtil();
        COSCredentials cred = new BasicCOSCredentials(secretId,secretKey);
        Region region = new Region(myRegion);
        ClientConfig clientConfig = new ClientConfig(region);
        return new COSClient(cred,clientConfig);
    }


    /**
     * 实现文件上传
     * @param uploadFile
     */
    public static String upload(MultipartFile uploadFile) throws Exception{
        boolean isLegal = false;
        for(String type : IMAGE_TYPE){
            if(StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),type)){
                isLegal = true;
                break;
            }
        }
        if(!isLegal){
            return "格式不支持！";
        }
        String fileName = uploadFile.getOriginalFilename();
        String filePath = getFilePath(fileName);
       // PutObjectRequest putObjectRequest = new PutObjectRequest(bucket,filePath,file);
        PutObjectResult result = getClient().putObject(bucket,filePath,new ByteArrayInputStream(uploadFile.getBytes()),null);
       return URL+filePath;
    }

    /**
     * 生成文件路径
     * @param sourceFileName
     * @return
     */
    private static String getFilePath(String sourceFileName) {
        DateTime dateTime = new DateTime();
        Random random = new Random();
        return dateTime.getMillis()+random.nextInt(9999)+sourceFileName;
    }
}






























