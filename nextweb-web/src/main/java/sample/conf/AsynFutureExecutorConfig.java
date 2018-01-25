package sample.conf;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

/**
 * 异步线程池处理类,用于mq调用的异步处理，返回completeableFuture
 * @author masen
 *
 */
@Configuration
public class AsynFutureExecutorConfig {

	@Value("${asynFuture.threadool.thread.count:10}")
	int corePoolSize ;
	@Value("${asynFuture.threadool.thread.maxCount:100}")
	int maxPoolSize ;
	@Value("${asynFuture.threadool.thread.queueSize:10}")
	int queueCapacity ;
	@Value("${asynFuture.threadool.thread.keepAliveTime:60000}")
	int keepAliveSeconds ;
	private final static String danyDaoLocations = "sample.dany.dao";
	// 动态DAO
	@Bean(name="asynFutureExecutor")
	public Executor mapperScannerConfigurer() {
		ThreadPoolExecutorFactoryBean factory = new ThreadPoolExecutorFactoryBean();
		System.out.println(corePoolSize+":"+maxPoolSize+":"+queueCapacity+":"+keepAliveSeconds);
		factory.setCorePoolSize(corePoolSize);
		factory.setMaxPoolSize(maxPoolSize);
		factory.setQueueCapacity(queueCapacity);
		factory.setKeepAliveSeconds(keepAliveSeconds);
		
		factory.initialize();
		
		//饱和策略默认
        return factory.getObject();

	}
}
