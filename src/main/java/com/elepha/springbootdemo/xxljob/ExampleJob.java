package com.elepha.springbootdemo.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class ExampleJob {

    @XxlJob("hello")
    public ReturnT<String> beanMethodJobHandler() throws Exception {
        XxlJobHelper.log("hello {}", XxlJobHelper.getJobParam());
        return ReturnT.SUCCESS;
    }

}
