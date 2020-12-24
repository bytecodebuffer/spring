package supoman.basedQuartz;

import org.quartz.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import supoman.basedQuartz.job.ClarkJob;
import supoman.basedQuartz.job.HelloWorldJob;

/**
 * @author bz
 * @date 2020/9/27
 *
 * 参考：https://blog.csdn.net/qq_40369944/article/details/83926214
 */
@Configuration
@ConditionalOnProperty(value = "quartz.enable",havingValue = "true")
public class QuartzConfiguration {

    @Bean
    public JobDetail helloWorldJobDetail(){
        JobDetail jobDetail = JobBuilder.newJob(HelloWorldJob.class)
                .withIdentity("myJob1","myJobGroup1")
                //JobDataMap可以给任务execute传递参数
                .usingJobData("name","张三")
                .usingJobData("age",15)
                .storeDurably()
                .build();
        return jobDetail;
    }
    @Bean
    public Trigger helloWorldTrigger(){
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(helloWorldJobDetail())
                .withIdentity("mytrigger1","myJobGroup1")
                .usingJobData("job_trigger_param","job_trigger_param1")
                .startNow()
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        return trigger;
    }

    @Bean
    public JobDetail clarkJobDetail(){
        JobDetail clarkJobDetail = JobBuilder.newJob(ClarkJob.class)
                .withIdentity("clarkJob","clarkJobGroup")
                .storeDurably()
                .build();
        return clarkJobDetail;
    }

    @Bean
    public Trigger clarkTrigger(){
        Trigger clarkTrigger = TriggerBuilder
                .newTrigger()
                .forJob(clarkJobDetail())
                .withPriority(6)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                .build();
        return clarkTrigger;
    }

}
