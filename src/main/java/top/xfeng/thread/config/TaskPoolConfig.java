package top.xfeng.thread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuefeng.wang
 * @date 2020-07-01
 */
@Configuration
@EnableAsync
public class TaskPoolConfig {

    @Bean("taskExecutor")
    public Executor export() {
        return getCommonExecutor("taskExecutor-");
    }

    @Bean("threadPoolExecutor")
    public Executor executor() {
        return getThreadPoolExecutor("threadPoolExecutor");
    }

    /**
     * 核心线程数10：线程池创建时初始化的线程数
     * 最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
     * 缓冲队列200：用来缓冲执行任务的队列
     * 允许线程的空闲时间60秒：超过了核心线程数之外的线程，在空闲时间到达之后会被销毁
     * 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
     * 线程池对拒绝任务的处理策略：此处采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在execute方法的调用线程中运行被拒绝的任务；如果执行程序已被关闭，则会丢弃该任务
     * 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
     * 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
     */
    private ThreadPoolTaskExecutor getCommonExecutor(String name) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(21);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix(name);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }

    private ExecutorService getThreadPoolExecutor(String name) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);
        RejectedExecutionHandler discardPolicyHandler = new ThreadPoolExecutor.DiscardPolicy();
        return new ThreadPoolExecutor(5, 10, 11, TimeUnit.SECONDS, workQueue, new MyThreadFactory(name), discardPolicyHandler);
    }

    static class MyThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + name + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }


}
