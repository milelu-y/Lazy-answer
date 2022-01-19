package com.lazy.vm.service.upload;

import com.lazy.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LazyDI
 * @date 2021/1/13 14:19
 */

public interface UploadService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    AjaxResult uploadFile(MultipartFile file);

    AjaxResult deleteFile(String id);
}
