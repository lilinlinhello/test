package com.yunda.app.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.app.entity.vo.MessageQueue;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.StaticVar;

@Service(value = "messageQueueService")
@Transactional
public class MessageQueueServiceImpl implements MessageQueueService {
	private Scanner scanner;

	@Override
	public Page pageQuery(MessageQueue messageQueue) {
		Page page = new Page();
		try {
			StringBuilder ipText = new StringBuilder();
			String url = StaticVar.RABBITMQ_URL;
			// 用户名密码部分
			String username = StaticVar.RABBITMQ_USERNAME;
			String password = StaticVar.RABBITMQ_PASSWORD;
			String input = username + ":" + password;
			String encoding = new String((new sun.misc.BASE64Encoder()).encode(input.getBytes()));
			URL myURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
			conn.setConnectTimeout(6 * 1000);
			conn.setReadTimeout(6 * 1000);
			conn.setRequestProperty("Authorization", "Basic " + encoding);
			//如果连接成功，获得返回字符串
			if (conn.getResponseCode() == 200) {
				InputStream inputStream = conn.getInputStream();
				scanner = new Scanner(inputStream, "utf-8");
				while (scanner.hasNext()) {
					ipText.append(scanner.next());
				}
				if (inputStream != null) {
		            inputStream.close();
		        }
				conn.disconnect();
				JSONArray jsonArray = JSONArray.fromObject(ipText.toString());
				List<MessageQueue> mqList = new ArrayList<MessageQueue>();
				int pageNo = messageQueue.getPageNo();
				int pageSize = messageQueue.getPageSize();
				//如果json有子项，说明有消息队列
				if (jsonArray != null && jsonArray.size() > 0) {
					String messageQueueName = messageQueue.getName();
					//不是搜索某个消息队列
					if (messageQueueName == null) {
						//分页选取消息队列
						for (int i = (pageNo - 1) * pageSize; i < pageNo
								* pageSize; i++) {
							if (i == jsonArray.size()) {
								break;
							}
							MessageQueue mq = new MessageQueue();
							JSONObject jsonobject = jsonArray.getJSONObject(i);
							//获取消息队列name等字段
							mq.setName(jsonobject.getString("name"));
							mq.setMessagesReady(jsonobject
									.getInt("messages_ready"));
							mq.setMessagesUnacknowledged(jsonobject
									.getInt("messages_unacknowledged"));
							mq.setMessages(jsonobject.getInt("messages"));
							mq.setConsumers(jsonobject.getInt("consumers"));
							mq.setState(jsonobject.getString("state"));
							mqList.add(mq);
						}
						if (mqList.size() > 0) {
							page.setTotal(jsonArray.size());
							page.setRows(mqList);
						}
					} else {//是搜索某个消息队列
						MessageQueue mq = new MessageQueue();
						for (int i = 0; i < jsonArray.size(); i++) {
							JSONObject jsonobject = jsonArray.getJSONObject(i);
							//获取消息队列name字段,判断是否和搜索队列名称相同
							String jsonobjectName=jsonobject.getString("name");
							if (jsonobjectName.equals(messageQueueName)) {
								mq.setName(messageQueueName);
								mq.setMessagesReady(jsonobject
										.getInt("messages_ready"));
								mq.setMessagesUnacknowledged(jsonobject
										.getInt("messages_unacknowledged"));
								mq.setMessages(jsonobject.getInt("messages"));
								mq.setConsumers(jsonobject.getInt("consumers"));
								mq.setState(jsonobject.getString("state"));
								mqList.add(mq);
								break;
							} 
						}
						if (mqList.size() > 0) {
							page.setTotal(1);
							page.setRows(mqList);
						}
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
}
