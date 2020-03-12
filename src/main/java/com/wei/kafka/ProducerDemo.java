package com.wei.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

    public class ProducerDemo {
        public static void main(String[] args) {

            Properties properties = new Properties();
            properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hdp-1:9092");
            properties.put("ack", true);
            //重试次数
            properties.put("retries", 3);

            // 批次大小：16k
            properties.put("batch.size", 16384);
            //等待时间1ms
            properties.put("linger.ms", 1);
            //RecordAccumulator 缓冲区大小
            properties.put("buffer.memory", 33554432);
            properties.put("key.serializer",
                    "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer",
                    "org.apache.kafka.common.serialization.StringSerializer");


            KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < 10; i++) {
                producer.send(new ProducerRecord<String, String>("test_api",  i+"hahaha"));
            }
            producer.close();
        //我第一次修改！！！！！！！！！！！！！！21：05

	//我的第二次修改！！！！！！！！！！！！！！！21:12


            //第三次修改
            add(1, 1);

            System.out.println("hot fix");


        }

        public static void add(int i,int j){
            int result = i + j;

            System.out.println(result);
        }
    }
