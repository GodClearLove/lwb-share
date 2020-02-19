package com.baizhi.controller;

import com.baizhi.conf.Audioutil;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @Autowired
    AlbumService albumService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;

    @RequestMapping("/addChapter")
    public void addChapter(Chapter chapter, MultipartFile file1, HttpSession session) throws IOException {
        //获取uuid设置章节的id
        String uuid = UUID.randomUUID().toString().replace("-","");
        chapter.setId(uuid);
        //文件上传--音频
        String newName = file1.getOriginalFilename();


        /*ServletContext sc = session.getServletContext();
        String realPath = sc.getRealPath("/upload");
        File f = new File(realPath+"/"+newName);
        file1.transferTo(f);*/

        //fastdfs文件上传---
        InputStream inputStream = file1.getInputStream();
        String substring = newName.substring(newName.lastIndexOf(".") + 1);
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file1.getSize(),substring, null);
        System.out.println(storePath);
        chapter.setUrl(storePath.getGroup()+"/"+storePath.getPath());
        session.setAttribute("chapter",storePath);
        //获取大小---
        File f = new File("/"+newName);
        double length = (f.length()/1024+1)/1024.0;
        String size=length+"";
        chapter.setSize(size.substring(0,4)+"MB");
        //获取文件时长---待补充？？？？
        Long duration = Audioutil.getDuration(f);

        String l = duration/60+"min";
        chapter.setDuration(l);
        System.out.println(l+"时长---");
        chapterService.insert(chapter);
        //同时修改专辑的章节数+1
        Integer idd = chapter.getAid();
        Album album = albumService.queryOne(idd);
        Integer count = album.getCount()+1;
        albumService.updateCount(idd,count);
    }
    @RequestMapping("/downLoad")
    public void downLoad(String url,String title, HttpSession session, HttpServletResponse response) {

        //String realPath = session.getServletContext().getRealPath("/upload");
        //String filePath = realPath + "/" + url;

        StorePath storePath =(StorePath) session.getAttribute("chapter");
        byte[] bytes = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());

        String filePath = "/"+url;
        File file = new File(filePath);
        String extension = FilenameUtils.getExtension(url);
        String oldName = title + "." + extension;
        String encode = null;
        try {
            encode = URLEncoder.encode(oldName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        response.setContentType("audio/mpeg");
        try {
            /*ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));*/

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
