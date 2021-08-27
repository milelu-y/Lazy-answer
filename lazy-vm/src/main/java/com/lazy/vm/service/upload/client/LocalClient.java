package com.lazy.vm.service.upload.client;

import com.lazy.common.annotation.DefaultUploadClient;
import com.lazy.common.config.LazyConfig;
import com.lazy.common.core.domain.AjaxResult;
import com.lazy.common.utils.CommonUtils;
import com.lazy.common.utils.file.FileUploadUtils;
import com.lazy.vm.service.upload.impl.UploadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lazy
 * @date 2021/8/3 15:00
 */
@Component
@DefaultUploadClient
public class LocalClient extends UploadClient {

    @Autowired
    LazyConfig lazyConfig;

    @Override
    public AjaxResult readyUpload(MultipartFile file) {
        if (CommonUtils.BeNotNull(file)) {
            return AjaxResult.success();
        }
        return AjaxResult.error("文件上传失败");
    }

    @Override
    public AjaxResult uploadFile(MultipartFile file) {
        try {
            Map<String, Object> uploadRes = new HashMap<>(16);
            String upload = FileUploadUtils.upload(file);
            uploadRes.put("fileName", file.getOriginalFilename());
            uploadRes.put("filePath",upload);
            uploadRes.put("fileFullPath",LazyConfig.getUploadPath()+ File.separator+upload.replace("/profile/",""));
            uploadRes.put("url", getUrl() + upload);
            uploadRes.put("group", file.getOriginalFilename().split("\\.")[1]);
            uploadRes.put("path", upload);
            return AjaxResult.success(uploadRes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void uploadError(AjaxResult result) {

    }

    @Override
    public AjaxResult uploadSuccess(AjaxResult result, MultipartFile file) {
        return saveFileToDb(result,file);
    }


}

