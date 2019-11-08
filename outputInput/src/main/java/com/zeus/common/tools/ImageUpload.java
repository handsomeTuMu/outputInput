package com.zeus.common.tools;

import com.zeus.common.Response;
import com.zeus.common.utils.HttpServletUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author:tumu
 * @date:2019/7/5
 * @ver:1.0
 **/
@Component
public class ImageUpload {
    @Value("${server.port}")
    private static String port;
//    @Value("${linux.local}")
    private static String local="/code/freedomBoat/img/";
//    @Value("${linux.online}")
    private static String online="http://39.100.107.142:8080/img/";

    public static Response uploadImg(MultipartFile[] file, String userId) {
        String LocalCommonImgUrl=local;
        String OnlineCommonImgUrl= online;
        List<String> list=new ArrayList<>();
        if(file.length==0){
            return new Response(0, "请选择图片");}
        if(file.length>9){
            return new Response(0, "一次上传最多9张图片！");}
        for (int i = 0; i < file.length; i++) {
            MultipartFile files = file[i];
            String[] name=files.getOriginalFilename().split("\\.");
            long num = Math.abs(new Random().nextLong());
            long num1=Math.abs(new Random().nextLong());
            String imageUrl;
            String imageUrl1;
        if(userId==null) {
           imageUrl = LocalCommonImgUrl + "common/" + num +num1+ "."+name[1];
           imageUrl1 = OnlineCommonImgUrl + "common/" + num +num1+ "."+name[1];
        }else{
            imageUrl = LocalCommonImgUrl + userId + "/" + num + num1+ "."+name[1];
            imageUrl1 = OnlineCommonImgUrl + userId + "/" + num + num1+ "."+name[1];
        }
            File file1 = new File(imageUrl);
            if (!files.isEmpty()) {
                try {
                    File fileParent = file1.getParentFile();
                    if (!fileParent.exists()) {
                        fileParent.mkdirs();
                    }
                    files.transferTo(file1);
                    BufferedImage bufferedImage = ImageIO.read(file1);
                    // 通过临时文件获取图片流
                    if (bufferedImage != null) {
                        // 证明上传的文件不是图片，获取图片流失败，不进行下面的操作
                        Integer width = bufferedImage.getWidth();
                        // 通过图片流获取图片宽度
                        Integer height = bufferedImage.getHeight();
                        // 通过图片流获取图片高度
                    if(width>2484||height>2484){
                        changeSize(2484,2484,LocalCommonImgUrl);
                        bufferedImage = ImageIO.read(file1);
                        width = bufferedImage.getWidth();
                        height = bufferedImage.getHeight();
                    }
                        list.add(imageUrl1 + "?width=" + width + "&height=" + height);
                    }
                    else {
                    return new Response<String>(0, "图片上传成功,但长宽未获取", imageUrl1);}
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Response(0, "图片上传失败");
                }
            } else {
                return new Response(0, "图片未接收到，请检查是否上传空文件");
            }
        }
        return new Response<List>(200, "图片上传成功", list);
    }

    /**
     * 改变图片的尺寸
     *
     * @param newWidth, newHeight, path
     * @return boolean
     */
    public static boolean  changeSize(int newWidth, int newHeight, String path) {
        BufferedInputStream in = null;
     try { in = new BufferedInputStream(new FileInputStream(path));
         //字节流转图片对象
         Image bi = ImageIO.read(in);
         // 构建图片流
         BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
         //绘制改变尺寸后的图
         tag.getGraphics().drawImage(bi, 0, 0, newWidth, newHeight, null);
         //输出流
         BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));
         //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
         // encoder.encode(tag);
         ImageIO.write(tag, "PNG", out);
         in.close();
         out.close();
         return true; }
         catch (IOException e) {
         return false;
     }


    }

    public static void main(String[] args) {
        System.out.println(local);
        System.out.println(online);
//        changeSize(200,200,"F:\\a.jpg");
    }




}
