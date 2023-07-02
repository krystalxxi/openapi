package com.ecommerce.openapi.admin.register.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ecommerce.openapi.admin.register.domain.bo.AppInfoBo;
import com.ecommerce.openapi.admin.register.domain.repository.AppInfoRepository;
import com.ecommerce.openapi.admin.register.domain.service.AppInfoService;
import com.ecommerce.openapi.admin.infrastructure.po.AppInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * app应用管理
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
    AppInfoRepository appInfoRepository;

    @Override
    public AppInfoBo getAppInfoByName(String appName) {
        QueryWrapper<AppInfo> queryWrapper = new QueryWrapper<AppInfo>();
        queryWrapper.eq("app_name", appName);
        AppInfo appInfo = appInfoRepository.getOne(queryWrapper);
        AppInfoBo appInfoBo = null;
        if (null != appInfo) {
            appInfoBo = new AppInfoBo();
            BeanUtils.copyProperties(appInfo, appInfoBo);
        }
        return appInfoBo;
    }

}
