package com.ecommerce.openapi.admin.register.domain.service.impl;


import com.ecommerce.openapi.admin.register.domain.bo.ApiInfoBo;
import com.ecommerce.openapi.admin.register.domain.bo.AppInfoBo;
import com.ecommerce.openapi.admin.register.domain.bo.ArgumentBo;
import com.ecommerce.openapi.admin.register.domain.bo.MethodBo;
import com.ecommerce.openapi.admin.register.domain.service.ApiInfoService;
import com.ecommerce.openapi.admin.register.domain.service.AppInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Service
@Slf4j
public class ApiInfoServiceImpl implements ApiInfoService {
    @Autowired
    private AppInfoService appInfoService;

    /**
     * todo 改成配置
     */
    @Value("jar.path")
    private String jarPath;
//    private final String JAR_PATH = "/data/api-jars/";
//    private final String JAR_PATH = "C:/Users/krystal/Desktop/jar/";

    /**
     * Spring自带的参数提取工具类
     */
    private static final DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();

    /**
     * @param appName
     * @return
     */
    public List<ApiInfoBo> getApiInfo(String appName) {
        List<ApiInfoBo> apiInfoBoList = new ArrayList<>();
        // 根据appName查询jar信息
        AppInfoBo appInfo = appInfoService.getAppInfoByName(appName);
        if (null != appInfo) {
            String jarPath = appInfo.getJarName();
            parse(jarPath, apiInfoBoList);
        }
        return apiInfoBoList;
    }

    /**
     * @param jarPath
     * @param apiInfoBoList
     */
    public void parse(String jarPath, List<ApiInfoBo> apiInfoBoList) {
        try {
            JarFile jarFile = new JarFile(jarPath + jarPath);
            ClassLoader myClassLoader = Thread.currentThread().getContextClassLoader();
            Enumeration enumFiles = jarFile.entries();
            JarEntry entry;
            ApiInfoBo apiInfoBo;
            while (enumFiles.hasMoreElements()) {
                entry = (JarEntry) enumFiles.nextElement();
                if (entry.getName().indexOf("META-INF") < 0) {
                    String classFullName = entry.getName();
                    if (classFullName.endsWith(".class")) {
                        String className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                        Class myClass = myClassLoader.loadClass(className);
                        if (null != myClass && myClass.isInterface()) {
                            apiInfoBo = new ApiInfoBo();
                            apiInfoBo.setAClass(myClass);
                            apiInfoBo.setServiceName(myClass.getName());
                            apiInfoBo.setMethods(parseMethod(myClass));
                            apiInfoBoList.add(apiInfoBo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private List<MethodBo> parseMethod(Class c) {
        List<MethodBo> methodBos = new ArrayList<>();
        Method[] methods = c.getDeclaredMethods();
        if (null != methods) {
            MethodBo methodBo;
            for (Method m : methods) {
                String[] params = discoverer.getParameterNames(m);
                methodBo = new MethodBo();
                methodBo.setMethodName(m.getName());
                methodBo.setInputArguments(parseArgument(m.getParameterTypes(), params));
                methodBo.setOutputArgument(parseArgument(m.getReturnType()));
                methodBos.add(methodBo);
            }
        }
        return methodBos;
    }

    private List<ArgumentBo> parseArgument(Class[] argClasses, String[] argNames) {
        List<ArgumentBo> argumentInfos = new ArrayList<>();
        ArgumentBo argumentInfo;
        if (null != argClasses) {
            for (int i = 0; i < argClasses.length; i++) {
                argumentInfo = parseArgument(argClasses[i]);
                argumentInfo.setIndex(i);
                argumentInfo.setFieldName(argNames[i]);
                argumentInfos.add(argumentInfo);
            }
        }
        return argumentInfos;
    }

    private ArgumentBo parseArgument(Class argClasses) {
        ArgumentBo argumentInfo = new ArgumentBo();
        argumentInfo.setFieldType(argClasses.getName());
        Field[] fields = argClasses.getDeclaredFields();
        if (null != fields) {
            List<ArgumentBo> attrs = new ArrayList<>();
            for (Field field : fields) {
                ArgumentBo attr = new ArgumentBo();
                attr.setFieldType(field.getType().getTypeName());
                attr.setFieldName(field.getName());
                attrs.add(attr);
            }
            argumentInfo.setAttrs(attrs);
        }
        return argumentInfo;
    }
}
