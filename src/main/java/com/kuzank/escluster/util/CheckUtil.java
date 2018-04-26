package com.kuzank.escluster.util;

import com.kuzank.escluster.common.bean.OperateStatus;
import org.springframework.util.ObjectUtils;

public class CheckUtil {

    public static OperateStatus NotEmpty(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            return OperateStatus.PARAM_EMPTY;
        }
        return OperateStatus.SUCCESS;
    }

    public static OperateStatus NotEmpty(Object... objects) {

        if (ObjectUtils.isEmpty(objects))
            return OperateStatus.PARAM_EMPTY;

        for (Object object : objects) {
            if (ObjectUtils.isEmpty(object)) {
                return OperateStatus.PARAM_EMPTY;
            }
        }
        return OperateStatus.SUCCESS;
    }

}
