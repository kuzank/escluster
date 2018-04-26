package com.kuzank.escluster.controller.user;

import com.kuzank.escluster.common.bean.Constants;
import com.kuzank.escluster.common.bean.OperateStatus;
import com.kuzank.escluster.common.helper.JsonResponse;
import com.kuzank.escluster.mapper.entity.UserEntity;
import com.kuzank.escluster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: </p>
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public JsonResponse login(@RequestParam String account, @RequestParam String password,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        boolean result = userService.validateUserByAccount(account, password);
        if (result) {
            UserEntity userEntity = userService.findUserByAccount(account);
            request.getSession().setAttribute(Constants.USER_SESSION_KEY, userEntity);
            return JsonResponse.SUCCESS;
        }
        return new JsonResponse(OperateStatus.LOGIN_FAIL);
    }
}
