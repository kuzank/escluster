package com.kuzank.escluster.controller.app;

import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.AuthEnum;
import com.kuzank.escluster.common.bean.Constants;
import com.kuzank.escluster.common.bean.OperateStatus;
import com.kuzank.escluster.common.helper.JsonResponse;
import com.kuzank.escluster.mapper.entity.AppEntity;
import com.kuzank.escluster.mapper.entity.UserEntity;
import com.kuzank.escluster.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Description: </p>
 */
@Controller
@AppAuth(role = AuthEnum.Observation)
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST})
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json"})
    @ResponseBody
    public JsonResponse info(HttpServletRequest request) throws Exception {

        UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        List<AppEntity> appEntitys = appService.findByUserId(userEntity.getId());
        if (appEntitys != null && appEntitys.size() > 0) {
            AppEntity entity = appEntitys.get(0);
            return new JsonResponse(OperateStatus.SUCCESS, entity.getClusterName());
        }

        return JsonResponse.FALSE;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public JsonResponse insert(@RequestParam String clusterName, @RequestParam String description, HttpServletRequest request) throws Exception {

        if (clusterName == null || clusterName.length() == 0 || !clusterName.contains(" "))
            return new JsonResponse(OperateStatus.PARAM_NO_ALLOW);

        UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constants.USER_SESSION_KEY);

        AppEntity appEntity = new AppEntity();
        appEntity.setClusterName(clusterName);
        appEntity.setDescription(description);
        appEntity.setCreatedBy(userEntity.getId());

        if (appService.insert(appEntity) == 0) {
            return JsonResponse.FALSE;
        }
        return JsonResponse.SUCCESS;
    }


}
