package com.cs.app.util;

import java.io.Serializable;


/**
 * 消息
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 3703273818413218489L;
	public static final Integer SUCCESS=1;
	public static final Integer FAIL=-1;

	/** 类型 */
	private Integer status;

	/** 内容 */
	private String msg;
	
	/** 返回对象 **/
	private Object obj;

	/**
	 * 初始化一个新创建的 Message 对象，使其表示一个空消息。
	 */
	public Message() {

	}

	/**
	 * 初始化一个新创建的 Message 对象
	 * 
	 * @param status
	 *            状态
	 * @param msg
	 *            内容
	 */
	public Message(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	/**
	 * 初始化一个新创建的 Message 对象
	 *
	 * @param status
	 *            状态
	 * @param msg
	 *            内容
	 */
	public Message(Integer status, String msg, Object obj) {
		this.status = status;
		this.msg = msg;
		this.obj = obj;
	}

	public Message(String msg) {
		this.status =SUCCESS;
		this.msg = msg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}