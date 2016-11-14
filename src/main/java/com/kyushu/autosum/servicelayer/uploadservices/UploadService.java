package com.kyushu.autosum.servicelayer.uploadservices;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by ANDRE on 25/04/16.
 */
public interface UploadService {
    File uploadFile(MultipartFile file) throws Exception;
}
