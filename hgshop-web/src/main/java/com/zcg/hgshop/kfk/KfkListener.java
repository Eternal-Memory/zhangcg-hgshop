package com.zcg.hgshop.kfk;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.controller.ElSearchUtil;
import com.zcg.hgshop.domain.EsSpu;
import com.zcg.hgshop.domain.Spu;

public class KfkListener implements MessageListener<String, String>{

	@Autowired
	RedisTemplate<String, PageInfo<Spu>> redisTemplate;
	@Autowired
	ElSearchUtil<EsSpu> esUtil;
	
	
	public KfkListener() {
		System.out.println(" KfkListener 实例化了。。。。");
	}

	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String key = data.key();
		System.out.println(" KfkListener  收到消息 key " + key + " and  value is " + data.value());
		switch (key) {
		case "addspu":
			//可能首页数据受到影响
			redisTemplate.delete("firstPage");
			redisTemplate.delete("fenlei");
			
			// es 的数据也会受影响
			String spuJsonStr = data.value();
			Spu spu = JSON.parseObject(spuJsonStr, Spu.class);
			System.out.println("==========="+spu);
			EsSpu esSpu = new EsSpu(spu);
			esUtil.saveObject(esSpu.getId()+"", esSpu);
			break;
		case "delspu":
			
			break;

		default:
			break;
		}
	}

}
