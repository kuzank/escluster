package com.kuzank.escluster.common.helper;

import com.kuzank.escluster.common.bean.OperateStatus;
import com.kuzank.escluster.common.exception.OperateException;
import org.springframework.util.ObjectUtils;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
public class Preconditions {
    public static void checkArgument(boolean expression, OperateStatus status, String message) throws OperateException {
        if (!expression) {
            throw new OperateException(status, message);
        }
    }

    public static void checkArgument(boolean expression, OperateStatus status) throws OperateException {
        if (!expression) {
            throw new OperateException(status);
        }
    }

    public static void checkNotNull(Object object, OperateStatus status) throws OperateException {
        if (object == null) {
            throw new OperateException(status);
        }
    }

    public static void checkNotEmpty(Object object, OperateStatus status) throws OperateException {
        if (ObjectUtils.isEmpty(object)) {
            throw new OperateException(status);
        }
    }
}
