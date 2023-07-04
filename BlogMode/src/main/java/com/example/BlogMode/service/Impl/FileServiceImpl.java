package com.example.BlogMode.service.Impl;

import com.example.BlogMode.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //file name
        String name= file.getOriginalFilename();   // abc.png

        // random name generate file
        String randomId = UUID.randomUUID().toString();
        String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

        // full path
        String filePath = path + File.separator + fileName1;

        // create folder if not created
        File f=new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }

        // file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String s = path + File.separator + fileName;
        FileInputStream fileInputStream = new FileInputStream(s);
        //db logic to return Input Stream

        return fileInputStream;
    }
}
