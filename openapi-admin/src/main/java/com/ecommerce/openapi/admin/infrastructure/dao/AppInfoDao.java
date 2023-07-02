package com.ecommerce.openapi.admin.infrastructure.dao;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.openapi.admin.register.domain.repository.AppInfoRepository;
import com.ecommerce.openapi.admin.infrastructure.mapper.AppInfoMapper;
import com.ecommerce.openapi.admin.infrastructure.po.AppInfo;
import org.springframework.stereotype.Repository;

@Repository
public class AppInfoDao extends ServiceImpl<AppInfoMapper, AppInfo> implements AppInfoRepository {
}
