package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dto.AlbumDTO;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;

    @RequestMapping("/queryAllByPage")
    public AlbumDTO queryAllByPage(Integer page,Integer rows){
        AlbumDTO albumDTO = albumService.queryByPage(page, rows);
        return albumDTO;
    }
    @RequestMapping("/queryOne")
    public Album queryOne(Integer id){
        Album album = albumService.queryOne(id);
        return album;
    }
    @RequestMapping("/addAlbum")
    public void addAlbum(Album album, MultipartFile file, HttpSession session) throws IOException {
        String s = UUID.randomUUID().toString().replace("-", "");
        String newImgname = s+file.getOriginalFilename();

        /*ServletContext sc = session.getServletContext();
        String realPath = sc.getRealPath("/upload");
        File f = new File(realPath+"/"+newImgname);
        file.transferTo(f);*/
        //fastdfs文件上传
        InputStream inputStream = file.getInputStream();
        String substring = newImgname.substring(newImgname.lastIndexOf(".") + 1);
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.getSize(),substring, null);
        album.setCoverImg(storePath.getGroup()+"/"+storePath.getPath());
        System.out.println(storePath+"--album--");
        //调用添加方法
        albumService.insert(album);
    }

    @RequestMapping("/export")
    public void export(HttpSession session){
        String realPath = session.getServletContext().getRealPath("/upload");
        List<Album> albums = albumService.queryAll();
        for (Album album : albums) {
            String coverImg = album.getCoverImg();
            album.setCoverImg(realPath+"\\"+coverImg);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑信息","专辑"),Album.class,albums);
        try {
            workbook.write(new FileOutputStream(new File("D:/Frame/album.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
