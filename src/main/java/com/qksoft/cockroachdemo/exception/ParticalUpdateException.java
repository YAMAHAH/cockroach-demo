package com.qksoft.cockroachdemo.exception;

public class ParticalUpdateException extends Exception {
    public ParticalUpdateException(String messages)  {
        super(messages);
    }
    public ParticalUpdateException()  {
        super();
    }
}
