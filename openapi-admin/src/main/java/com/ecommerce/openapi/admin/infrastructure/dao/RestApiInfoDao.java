package com.ecommerce.openapi.admin.infrastructure.dao;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.openapi.admin.register.domain.repository.RestApiInfoRepository;
import com.ecommerce.openapi.admin.infrastructure.mapper.RestApiInfoMapper;
import com.ecommerce.openapi.admin.infrastructure.po.RestApiInfo;
import org.springframework.stereotype.Repository;

@Repository
public class RestApiInfoDao extends ServiceImpl<RestApiInfoMapper, RestApiInfo> implements RestApiInfoRepository {
}
