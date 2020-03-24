package com.surfilter.boot;

import java.util.Collection;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.surfilter.boot.config.SpringConfigTest;
import com.surfilter.boot.ssm.core.AppContext;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.util.ShardInfo;

@SpringBootApplication
@MapperScan("com.surfilter.boot")
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
		getAppContext();
	}

	public static  void getAppContext(){
		System.out.println(AppContext.getBean("testUser"));
		ShardedJedisPool jedis = (ShardedJedisPool)AppContext.getBean("shardedJedisPool");
		ShardedJedis shard = jedis.getResource();
		Collection<JedisShardInfo> list = shard.getAllShardInfo();
		System.out.println(list.size());
		for(JedisShardInfo info : list){
			System.out.println(info);
		}
		System.out.println(shard);
		//shard.hget(key, field);
		//System.out.println(AppContext.getBean("shardedJedisPool"));
	}
}
