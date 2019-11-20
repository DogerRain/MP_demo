package com.meizu.schedule;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.ArrayList;
import java.util.List;

/**
 * DataFlowJob 数据流作业
 */
public class MyElasticDataflowJob implements DataflowJob<String> {
    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        int item = shardingContext.getShardingItem();
        List<String> list = new ArrayList<String>();
        System.out.printf(MyElasticDataflowJob.class.getName());
        switch (item) {
            case 0:
                System.out.printf("数据流作业执行ing，item为：" + item);
                list.add("get data from database by sharding item 0");
                return list;
            case 1:
                System.out.printf("数据流作业执行ing，item为：" + item);
                list.add("get data from database by sharding item 1");
                return list;
            case 2:
                System.out.printf("数据流作业执行ing，item为：" + item);
                list.add("get data from database by sharding item 2");
                return list;
        }
        return null;
    }


    @Override
    public void processData(ShardingContext shardingContext, List<String> list) {
        int count = 0;
        // process data
        // ...
        for (String string : list) {
            count++;
            System.out.println(string);
            if (count > 10) {
                return;
            }
        }

    }
}
