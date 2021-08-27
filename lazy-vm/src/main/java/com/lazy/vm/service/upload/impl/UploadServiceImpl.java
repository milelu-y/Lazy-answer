package com.lazy.vm.service.upload.impl;

import com.lazy.common.core.domain.AjaxResult;
import com.lazy.vm.domain.base.BaseUpload;
import com.lazy.vm.service.upload.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Lazy
 * @date 2021/8/3 14:50
 */
@Service
public class UploadServiceImpl extends BaseUpload implements UploadService {
    @Override
    public AjaxResult uploadFile(MultipartFile file) {
        AjaxResult result = uploadClient.readyUpload(file);
        Integer code = (Integer) result.get("code");
        if (code == 200) {
            result = uploadClient.uploadFile(file);
            code = (Integer) result.get("code");
            if (200 == code) {
                result = uploadClient.uploadSuccess(result, file);
            }
        } else {
            uploadClient.uploadError(result);
        }
        return result;
    }
}
