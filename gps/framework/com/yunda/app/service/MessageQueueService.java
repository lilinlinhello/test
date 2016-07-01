package com.yunda.app.service;

import com.yunda.app.entity.vo.MessageQueue;
import com.yunda.app.entity.vo.Page;


public interface MessageQueueService {

	public Page pageQuery (MessageQueue messageQueue);
}