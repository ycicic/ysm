package cn.ycicic.ysm.exception;

/**
 * @author ycc
 */
public class YsmException extends RuntimeException{
    private static final long serialVersionUID = 7302913316367283587L;

    public YsmException() {
        super();
    }

    public YsmException(String message) {
        super(message);
    }

    public YsmException(String message, Throwable cause) {
        super(message, cause);
    }

    public YsmException(Throwable cause) {
        super(cause);
    }

    protected YsmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
