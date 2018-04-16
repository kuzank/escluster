package exception;

/**
 * Author: kuzan
 * Date: 2018-01-14 17:08
 * Desc: ESIllegalArgumentException
 */
public class ESIllegalArgumentException extends Exception {

    public ESIllegalArgumentException() {
        super((String) null);
    }

    public ESIllegalArgumentException(String msg) {
        super(msg);
    }

    public ESIllegalArgumentException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
