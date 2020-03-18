package com.qf.message.service.impl;


import com.qf.message.model.Message;
import com.qf.message.model.MessageStatus;
import com.qf.message.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 独立消息服务，目前只实现一个大框架，主要目的是给大家理清分布式事务的控制思路
 */
@Service(value = "messageServiceImpl")
public class MessageServiceImpl implements MessageService {

	@Override
	@Transactional
	public void receiveMsg(Message message) {
		if (message == null) {
			throw new RuntimeException("消息为空");
		}

		switch (message.getStatus()) {
		case MessageStatus.INIT:
			// TODO save msg
			System.out.println("保存初始消息");
			break;
		case MessageStatus.SENT:
			// TODO update msg status 为sent
			// TODO 发送消息到订单减库存队列，这块待同学们自己实现
			System.out.println("更新消息状态为sent");
			break;
		case MessageStatus.END:
			// TODO update msg status 为end
			break;

		default:
			throw new RuntimeException("消息状态有误");
		}
	}

}
