package project.banmua.com.network.exceptions;

/**
 */
public class ResException extends Throwable{
    private String messageError;
    private int errorCode;

    public ResException(String messageError,int errorCode){
        this.messageError = messageError;
        this.errorCode = errorCode;
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
