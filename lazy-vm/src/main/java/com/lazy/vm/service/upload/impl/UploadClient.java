package com.lazy.vm.service.upload.impl;

import ch.qos.logback.core.util.FileUtil;
import com.lazy.common.core.domain.AjaxResult;
import com.lazy.common.core.domain.entity.SysResource;
import com.lazy.common.utils.SecurityUtils;
import com.lazy.common.utils.ServletUtils;
import com.lazy.common.utils.uuid.SnowflakeIdWorker;
import com.lazy.vm.service.ISysResourceService;
import com.lazy.vm.service.impl.SysResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author Lazy
 * @date 2021/8/3 15:01
 */
public abstract class UploadClient {

    @Autowired
    ISysResourceService resourceService;


    /**
     * 文件上传前触发
     *
     * @param file
     * @return
     */
    public abstract AjaxResult readyUpload(MultipartFile file);


    /**
     * 文件上传中触发
     * @param file
     * @return
     */
    public abstract AjaxResult uploadFile(MultipartFile file);


    /**
     * 文件上传失败触发
     *
     * @param result
     * @return
     */
    public abstract void uploadError(AjaxResult result);


    /**
     * 文件上传成功触发
     *
     * @param file
     * @return
     */
    public abstract AjaxResult uploadSuccess(AjaxResult result, MultipartFile file);



    public AjaxResult saveFileToDb(AjaxResult resMap, MultipartFile file) {
        Map<String,String> params = (Map<String, String>) resMap.get("data");
        String id = SnowflakeIdWorker.getId();
        String uid=UUID.randomUUID().toString();
        SysResource resource = new SysResource();
        String path = params.get("path");
        String group = params.get("group");
        String url = params.get("rul");
        resource.setFileName(file.getOriginalFilename());
        resource.setFilePath(path);
        resource.setFileFullPath(params.get("fileFullPath"));
        resource.setGroupName(group);
        resource.setId(id);
        resource.setFileSize(file.getSize());
        resource.setFileUid(uid);
        resource.setFileType(group);
        resource.setUrl(url);
        resource.setCreateBy(SecurityUtils.getUsername());
        resource.setCreateId(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        resource.setCreateTime(new Date());
        resourceService.insertSysResource(resource);
        params.put("fileUid",uid);
        params.put("id",id);
        return AjaxResult.success(params);
    }

    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     *
     * @return 服务地址
     */
    public String getUrl() {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
