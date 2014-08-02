package project.banmua.com.network.exceptions;

/**
 */
public class ResException extends Throwable{
    private String messageError;
    private int errorCode;

    public ResException(String messageError){
        this.messageError = messageError;
    }

    @Override
    public String getMessage() {
        return messageError;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessageError() {
        return messageError;
    }
}
