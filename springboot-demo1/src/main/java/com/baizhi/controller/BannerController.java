package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.dto.BannerDTO;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
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

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @RequestMapping("/queryAllByPage")
    public BannerDTO queryAllByPage(Integer page,Integer rows){
        BannerDTO bannerDTO = bannerService.queryByPage(page, rows);
        return bannerDTO;

    }
    @RequestMapping("addBanner")
    public void addBanner(Banner banner, MultipartFile file, HttpSession session) throws IOException {
        /*ServletContext sc = session.getServletContext();
        String realPath = sc.getRealPath("/upload");
        String n = file.getOriginalFilename();
        banner.setImgPath("/"+n);
        banner.setStatus(0);
        System.out.println(realPath+"---==="+n);
        File f = new File(realPath+"/"+n);
        file.transferTo(f);*/
        //fastdfs文件上传
        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();
        banner.setStatus(0);
        String substring = filename.substring(filename.lastIndexOf(".") + 1);
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.getSize(), substring, null);
        banner.setImgPath(storePath.getGroup()+"/"+storePath.getPath());
        System.out.println(storePath);
        //写入数据库
        bannerService.insert(banner);
    }

    @RequestMapping("delBanner")
    public void delBanner(Integer id){
        bannerService.delete(id);
    }

    @RequestMapping("/updateBanner")
    public void updateBanner(Banner banner){
        bannerService.update(banner);
    }

    @RequestMapping("export")
    public void export(HttpSession session){
        //String realPath = session.getServletContext().getRealPath("/upload");
        List<Banner> banners = bannerService.selectAll();
        for (Banner banner : banners) {
            String imgPath1 = banner.getImgPath();
            banner.setImgPath("http://192.168.85.136/"+imgPath1);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图信息","轮播图"),Banner.class,banners);
        try {
            workbook.write(new FileOutputStream(new File("D:/Frame/banner.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("importance")
    public void importance(){
        ImportParams params = new ImportParams();
        //params.setNeedSave(true);
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<Banner> list = ExcelImportUtil.importExcel(
                new File("D:\\Frame\\bannerImport.xls"),
                Banner.class, params);
        for (Banner banner : list) {
            banner.setId(null);
            String[] split = banner.getImgPath().split("/upload");
            String str = split[1];
            banner.setImgPath(str);
            //添加方法
            bannerService.insert(banner);
        }
    }

    @RequestMapping("query")
    public List<Banner> query(String keyWords){
        List<Banner> list = bannerService.queryByDesc(keyWords);
        return list;
    }

}
