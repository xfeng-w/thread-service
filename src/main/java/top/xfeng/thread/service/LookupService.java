package top.xfeng.thread.service;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author xuefeng.wang
 * @date 2020-07-02
 */
@Service
public class LookupService {
    private static final Logger logger = LoggerFactory.getLogger(LookupService.class);

//    @Async("threadPoolExecutor")
    @SneakyThrows
    public void thread(String name)  {
        Thread.sleep(3000L);
        logger.info(name);
    }
}
