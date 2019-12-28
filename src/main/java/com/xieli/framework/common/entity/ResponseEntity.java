package com.xieli.framework.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.xieli.framework.common.constant.StatusCode;
import com.xieli.framework.common.util.FwMessageUtils;
import com.xieli.framework.common.util.StringUtils;

import lombok.Getter;
import lombok.Setter;


/**
 * 返回数据模型
 * @author 李文龙
 *
 */

@Getter
@Setter
public class ResponseEntity<T> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2305573436444343979L;

	/**
	 * 状态，
	 */
	private int status;

	/**
	 * 信息
	 */
	private List<Message> message;

	/**
	 * 数据
	 */
	private T data;

	private long date;

	public ResponseEntity(){
		this.date = new Date().getTime();
	}


	/**
	 * 构建ResponseResult
	 *
	 * @param status
	 *            响应状态吗
	 * @param message
	 *            响应消息
	 * @return ResponseResult
	 */
	public static <T> ResponseEntity build(int status, Message message) {
		ResponseEntity<T> result = new ResponseEntity<T>();
		result.setStatus(status);
		result.setMessage(Arrays.asList(message));
		return result;
	}

	public static <T> ResponseEntity build(int status, Message message,T t) {
		ResponseEntity result = new ResponseEntity();
		result.setStatus(status);
		result.setData(t);
		result.setMessage(Arrays.asList(message));
		return result;
	}

	/**
	 * 构建ResponseResult
	 *
	 * @param status
	 *            响应状态吗
	 * @param msg
	 *            响应消息
	 * @param t
	 *            响应数据
	 * @return ResponseResult
	 */
	public static <T> ResponseEntity build(int status, String msg, T t) {
		ResponseEntity<T> result = new ResponseEntity<T>();
		result.setStatus(status);
		result.setData(t);
		if(StringUtils.isNotEmpty(msg)){
			Message mess = new Message();
			mess.setCode(String.valueOf(status));
			mess.setMessage(msg);
			result.setMessage(Arrays.asList(mess));
		}
		return result;
	}

	/**
	 * 构建ResponseResult
	 *
	 * @param status
	 *            响应状态吗
	 * @param messages
	 *            响应消息
	 * @return ResponseResult
	 */
	public static <T> ResponseEntity build(int status, List<Message> messages) {
		ResponseEntity<T> result = new ResponseEntity<T>();
		result.setStatus(status);
		result.setMessage(messages);
		return result;
	}

	/**
	 * 构建ResponseResult
	 *
	 * @param status
	 *            响应状态吗
	 * @param codes
	 *            响应错误代码
	 * @return ResponseResult
	 */
	public static <T> ResponseEntity buildCodes(int status, String ... codes) {
		ResponseEntity<T> result = new ResponseEntity<T>();
		result.setStatus(status);
		if (codes != null) {
			List<Message> messages = new ArrayList<>();
			for (String code : codes) {
				messages.add(Message.message(code, FwMessageUtils.getMessageString(code)));
			}
			result.setMessage(messages);
		}

		return result;
	}

	/**
	 * 构建ResponseResult
	 *
	 * @param data
	 *            响应状态吗
	 * @return ResponseResult
	 */
	public static <T> ResponseEntity buildSuccess(T data) {
		ResponseEntity<T> result = new ResponseEntity<T>();
		result.setStatus(StatusCode.OK);
		result.setData(data);
		return result;
	}

}
