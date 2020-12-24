package supoman.basedQuartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author bz
 * @date 2020/9/27
 */
@Slf4j
public class HelloWorldJob implements Job {

    String name;
    int age;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Hello,World!");
        log.info(name);
        log.info(age+"");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
