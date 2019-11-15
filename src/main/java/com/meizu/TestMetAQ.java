package com.meizu;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.utils.ZkUtils;

public class TestMetAQ {
    public static void main(String[] args) {
        //1. 设置必要的配置信息
        final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZkUtils.ZKConfig zkConfig = new ZkUtils.ZKConfig();
        //测试环境：zk-test-master1.meizu.mz:2181,zk-test-master2.meizu.mz:2181,zk-test-master3.meizu.mz:2181
        //生产环境： 192.168.16.131:2181,192.168.16.132:2181,192.168.16.133:2181
        //不同的环境连接不同的集群,请自行想办法修改，建议使用配置中心
        zkConfig.zkConnect = "127.0.0.1:2181";
        metaClientConfig.setZkConfig(zkConfig);

        //2. New session factory,强烈建议使用单例,
        //一个工程里只需一个sessionFactory就够了,建议生产者和消费者共用一个sessionFactory
        try {
            final MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
            //3. create producer,强烈建议使用单例
            final MessageProducer producer = sessionFactory.createProducer();
            //4. publish topic,需要申请过才能用
            final String topic = "meta-test";
            String line = "";
            producer.publish(topic);

            //===========通常情况下以上代码在工程中只需初始化一次.==========//
            //5. 调用发送消息API
            // send message
            final SendResult sendResult = producer.sendMessage(new Message(topic, line.getBytes()));
            // check result
            if (!sendResult.isSuccess()) {
                System.err.println("Send message failed,error message:" + sendResult.getErrorMessage());
            } else {
                System.out.println("Send message successfully,sent to " + sendResult.getPartition());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
