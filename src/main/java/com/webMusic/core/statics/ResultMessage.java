package com.webMusic.core.statics;

import java.util.HashMap;
import java.util.Map;

/*
 * 定义公共返回实体消息
 */
public class ResultMessage {
    private String code;
    private Object returnObj;
    private Map<String, String> messages = new HashMap<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }

    public void addSuccessMessage(String value) {
        messages.put(Constant.result_success, value);
    }

    public void addFailMessage(String value) {
        messages.put(Constant.result_fail, value);
    }

    public Object getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }
}
