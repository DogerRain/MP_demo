package com.meizu;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.utils.ZkUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 先用原始的客户端,以后升级为结合配置中心的客户端会非常简便.
 *
 * spring中使用可以把发送者和消费者统一放在一个bean里初始化好,然后外部调用这个bean的方法进行消息发送
 * </pre>
 *
 * @author <a href=mailto://wq163@163.com>jenwang</a>
 * @since 14-6-10 上午11:39
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        //1. 设置必要的配置信息并New session factory,强烈建议使用单例
        //一个工程里只需一个sessionFactory就够了,建议生产者和消费者共用一个sessionFactory
        final MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(initMetaConfig());


        //2. create producer,强烈建议使用单例
        final MessageProducer producer = sessionFactory.createProducer();
        // publish topic,需要申请过才能用
        final String topic = "meta-test";

        //3.publish topic, topic需要申请过才能用
        producer.publish(topic);

        //===========通常情况下以上代码在工程中只需初始化一次.==========//


        //4.发送消息
        //以下为发送消息示意代码 (别照抄哦)
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = readLine(reader)) != null) {
            // send message
            final SendResult sendResult = producer.sendMessage(new Message(topic, line.getBytes()));
            // check result
            if (!sendResult.isSuccess()) {
                System.err.println("Send message failed,error message:" + sendResult.getErrorMessage());
            }
            else {
                System.out.println("Send message successfully,sent to " + sendResult.getPartition());
            }
        }
    }


    private static String readLine(final BufferedReader reader) throws IOException {
        System.out.println("Type a message to send:");
        return reader.readLine();
    }

    private static MetaClientConfig initMetaConfig() {
        final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZkUtils.ZKConfig zkConfig = new ZkUtils.ZKConfig();
        //测试环境： 172.16.200.239:2181,172.16.200.233:2181,172.16.200.234:2181
        //生产环境： 192.168.16.131:2181,192.168.16.132:2181,192.168.16.133:2181
        //不同的环境连接不同的集群,请自行想办法修改
        zkConfig.zkConnect = "127.0.0.1:2181";
        metaClientConfig.setZkConfig(zkConfig);
        return metaClientConfig;
    }
}
