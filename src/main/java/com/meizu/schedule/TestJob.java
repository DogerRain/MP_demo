package com.meizu.schedule;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * SimpleJob 简单作业
 */
public class TestJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        //shardingTotalCount 分片
        int shardingTotalCount = shardingContext.getShardingTotalCount();
        System.out.printf("shardingTotalCount: " + shardingTotalCount);

        int item = shardingContext.getShardingItem();
        System.out.printf("item: " + item);
        switch (item) {
            case 0:
                System.out.println("do something by sharding item 0");
                break;
            case 1:
                System.out.println("do something by sharding item 1");
                break;
            case 2:
                System.out.println("do something by sharding item 2");
                break;
        }
    }
}
