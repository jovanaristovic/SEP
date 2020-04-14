package com.ftn.backend.controller;

import com.ftn.backend.common.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping(value = "/api/download", produces = MediaType.APPLICATION_JSON_VALUE)
public class DownloadController {


    @Autowired
    private ServletContext servletContext;

    @GetMapping("/file")
    public void downloadFile3(HttpServletResponse resonse,
                              @RequestParam String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File("src/main/pdf/" + fileName);

        resonse.setContentType(mediaType.getType());

        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());

        resonse.setContentLength((int) file.length());

        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }
}