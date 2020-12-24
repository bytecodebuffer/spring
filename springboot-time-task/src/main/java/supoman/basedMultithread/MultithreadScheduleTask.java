package supoman.basedMultithread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author bz
 * @date 2020/9/27
 *
 *  EnableAsync 开启多线程
 *
 */
@Slf4j
@Component
@EnableScheduling
@EnableAsync
@ConditionalOnProperty(value = "task.enable.multi",havingValue = "true")
public class MultithreadScheduleTask {


    @Async
    @Scheduled(cron = "0/3 * * * * *")  //间隔1秒
    public void first() throws InterruptedException {
        log.info("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
    }

    @Async
    @Scheduled(cron = "18/1 * * * * *")
    public void second() {
        log.info("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
    }
}
