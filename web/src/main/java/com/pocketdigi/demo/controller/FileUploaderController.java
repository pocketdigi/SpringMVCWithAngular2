package com.pocketdigi.demo.controller;

import com.pocketdigi.demo.common.util.FileUtil;
import com.pocketdigi.demo.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by fhp on 2017/2/17.
 */
@Controller
@Slf4j
public class FileUploaderController {
    @Value("${upload.path}")
    private String uploadPath;
    @RequestMapping(value = "/upload",method =  RequestMethod.POST)
    @ResponseBody
    public ResponseEntity index(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();
        String ext = FileUtil.contentType2Ext(contentType);
        if(ext==null) {
            return new ResponseEntity<ResultDTO>(ResultDTO.wrapError(-1,"不允许上传的文件类型"+contentType), HttpStatus.FORBIDDEN);
        }
        String fileName = FileUtil.generateFileName(ext);
        String filePath=uploadPath+fileName;
        File newFile=new File(filePath);
        try {
            file.transferTo(newFile);
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/upload/"+fileName;
            return new ResponseEntity<ResultDTO>(ResultDTO.wrapSuccess(url), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();

            return new ResponseEntity<ResultDTO>(ResultDTO.wrapError(-1,"上传失败"), HttpStatus.FORBIDDEN);
        }

    }

}
