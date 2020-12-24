package supoman.basedStatic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import supoman.dao.UserDao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bz
 * @date 2020/9/27
 *
 * 定时任务支持的 cron 表达式：以5或者6个空格隔开，共有 6 或者 7 个域
 *
 *  秒 分 时 日 月 周 年 （年可以省）
 *
 *
 * 周末-周六
 * SUN MON TUE WED THU FRI SAT
 * 0    1   2   3    4  5   6
 * https://www.cnblogs.com/xiang--liu/p/11378860.html
 * 此处定时的写死的
 */
@Slf4j
@Configuration
@EnableScheduling
@ConditionalOnProperty(value = "task.enable.static",havingValue = "true")
public class StaticScheduleTask {

    @Autowired
    private UserDao userDao1;

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 从项目启动，每隔5秒执行一次
     */
    //@Scheduled(cron = "0/5 * * * * *")
    private void task1() {
        log.info("[task1]: " + format.format(new Date()));
    }

    /**
     * 从项目启动的第10秒开始，每2秒执行一次
     */
    //@Scheduled(cron = "10/2 * * * * *")
    private void task2(){
        log.info("[task2]:执行"+ format.format(new Date()));
    }

    /**
     * 每隔3秒执行
     *
     * 上一次执行时间点，多久后再执行
     */
    //@Scheduled(fixedRate = 3000)
    private void task3(){
        log.info("[fixedRate]:执行"+ format.format(new Date()));
    }

    /**
     * 上一次执行完毕后多久再执行
     */
    @Scheduled(fixedDelay = 3000)
    private void task4(){
        log.info("[fixedDelay]:执行"+ format.format(new Date()));
    }

    /**
     * 测试更新数据库,成功
     */
    //@Scheduled(cron = "0 5 11 * * *")
    private void task5(){
        userDao1.updateUserAge(1L,20);
        log.info("更新成功");
    }

}
