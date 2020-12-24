package supoman.basedQuartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author bz
 * @date 2020/11/25
 */
@Slf4j
public class ClarkJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("闹钟响了");
    }
}
