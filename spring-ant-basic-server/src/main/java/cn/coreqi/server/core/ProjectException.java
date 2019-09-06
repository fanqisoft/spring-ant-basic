package cn.coreqi.server.core;

import java.util.ArrayList;
import java.util.List;

public class ProjectException extends Exception {

    private List<String> messageList = new ArrayList<String>();

    public List<String> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }


    public ProjectException() {
        super();
    }

    public ProjectException(String message) {
        super(message);
        messageList.add(message);
    }

    public ProjectException(List<String> msgList) {
        super("Mutlti Error");
        messageList = msgList;
    }

    public ProjectException(String message, Throwable cause) {
        super(message, cause);
        messageList.add(message);
    }

    public ProjectException(Throwable cause) {
        super(cause);
    }

    protected ProjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
