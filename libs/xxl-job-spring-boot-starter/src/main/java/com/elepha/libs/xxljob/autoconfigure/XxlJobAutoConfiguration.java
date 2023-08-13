package com.elepha.libs.xxljob.autoconfigure;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(name = "xxl-job.enabled", havingValue = "true")
public class XxlJobAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(XxlJobAutoConfiguration.class);

    @ConfigurationProperties(XxlJobProperties.PREFIX)
    @Bean
    public XxlJobProperties xxlJobProperties() {
        return new XxlJobProperties();
    }

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobProperties props) {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(props.getAdminAddresses());
        xxlJobSpringExecutor.setAppname(props.getAppName());
        xxlJobSpringExecutor.setAccessToken(props.getAccessToken());
        xxlJobSpringExecutor.setLogPath(props.getLogPath());
        return xxlJobSpringExecutor;
    }
}
