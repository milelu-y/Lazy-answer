package com.lazy.web.controller.file;

import com.lazy.common.core.domain.AjaxResult;
import com.lazy.vm.service.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadFileController {

    @Autowired
    UploadService uploadService;

    @PostMapping("/uploadFile")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file){
        return uploadService.uploadFile(file);
    }

}
