package Util;

public class LimitedBrowserException extends RuntimeException{
    public LimitedBrowserException(){
        super();
    }
    public LimitedBrowserException(String msg){
        super(msg);
    }

}
