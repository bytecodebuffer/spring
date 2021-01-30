package map.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import map.utils.GouldUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author bz
 * @date 2021/1/30
 */
@RestController
@Api(value = "/api/point",tags = "app-地图")
@RequestMapping("/api/point")
public class ViewController {

    @Resource
    private GouldUtil gouldUtil;

    public static final Logger log = LoggerFactory.getLogger(ViewController.class);


    @ApiOperation(value = "根据经纬度获取地址")
    @PostMapping("/getAddress")
    public String getAddress(String longitude,String lat) {
        try {
            System.out.println(longitude);
            System.out.println(lat);
            String address = gouldUtil.getAMapByLngAndLat(longitude, lat);
            return  address;
        } catch (Exception e) {
            e.printStackTrace();
            return  "";
        }
    }

    @ApiOperation(value = "根据地址获取经纬度")
    @GetMapping("/getLonLat/{address}")
    public String getLonLat(@PathVariable("address") String address) {
        try {
            String result = gouldUtil.getLonLat(address);
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return  "";
        }
    }


}
