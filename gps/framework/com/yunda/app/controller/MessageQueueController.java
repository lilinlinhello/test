package com.yunda.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.MessageQueue;
import com.yunda.app.service.MessageQueueService;

/**
 * 消息队列控制器
 * 
 * 
 */
@Controller
@RequestMapping(value = "/auth/mq.do")
public class MessageQueueController extends BaseController {

	@Resource(name = "messageQueueService")
	private MessageQueueService messageQueueService;

	/**
	 * 转发到消息队列管理页面
	 * 
	 * @param response
	 * 
	 * @return 消息队列管理jsp页面视图
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String forwardMq_list(HttpServletResponse response,
			HttpServletRequest request) {
		return "auth/mq_list";
	}

	@RequestMapping(params = "method=pageQuery")
	public void pageQuery(HttpServletResponse response,HttpServletRequest request,MessageQueue messageQueue) {
		sendJsonDataToClient(messageQueueService.pageQuery(messageQueue), response);
	}

}
