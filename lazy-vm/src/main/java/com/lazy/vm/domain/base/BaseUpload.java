package com.lazy.vm.domain.base;

import com.lazy.common.annotation.DefaultUploadClient;
import com.lazy.vm.service.upload.impl.UploadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author Lazy
 * @date 2021/8/3 14:58
 */
public abstract class BaseUpload {
    public UploadClient uploadClient;

    @Autowired
    public void initClient(List<UploadClient> uploadClients) {
        if (!CollectionUtils.isEmpty(uploadClients)) {
            uploadClients.forEach(uploadClient -> {
                DefaultUploadClient annotation = uploadClient.getClass().getAnnotation(DefaultUploadClient.class);
                if (annotation != null) {
                    this.uploadClient = uploadClient;
                }
            });
        }
    }
}
