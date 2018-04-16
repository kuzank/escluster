package com.kuzank.escluster.controller;

import com.kuzank.escluster.mapper.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
@Controller
public class UserController {

    @RequestMapping(value = {"/login"})
    public String index(HttpServletRequest request, @RequestParam("user") UserEntity userEntity) {



        return "/index";
    }

}
