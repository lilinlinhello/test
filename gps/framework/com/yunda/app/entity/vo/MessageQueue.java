package com.yunda.app.entity.vo;


public class MessageQueue extends PageQueryParams{
    //队列名称
	private String name;
    //已准备好消息数
	private int messagesReady;
    //未应答消息数
	private int messagesUnacknowledged;
    //队列中消息总数
	private int messages;
    //消费者
	private int consumers;
    //队列状态
	private String state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMessagesReady() {
		return messagesReady;
	}
	public void setMessagesReady(int messagesReady) {
		this.messagesReady = messagesReady;
	}
	public int getMessagesUnacknowledged() {
		return messagesUnacknowledged;
	}
	public void setMessagesUnacknowledged(int messagesUnacknowledged) {
		this.messagesUnacknowledged = messagesUnacknowledged;
	}
	public int getMessages() {
		return messages;
	}
	public void setMessages(int messages) {
		this.messages = messages;
	}
	public int getConsumers() {
		return consumers;
	}
	public void setConsumers(int consumers) {
		this.consumers = consumers;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}