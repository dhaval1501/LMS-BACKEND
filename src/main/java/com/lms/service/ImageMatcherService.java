package com.lms.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public interface ImageMatcherService {
    public byte[] filterImages(MultipartFile referenceImage) throws IOException;
}
