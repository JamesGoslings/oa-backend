package com.myPro.common.utils;

import com.myPro.common.result.Result;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class FileUtil {

    public static String classpath;

    static {
        try {
            classpath = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 图片转Base64字符串
     * @param imageFilePath 图片的路径
     * @return Base64字符串
     */
    public static String convertImageToBase64Str(String imageFilePath) {
        ByteArrayOutputStream baos = null;
        try {
            //获取图片类型
            String suffix = imageFilePath.substring(imageFilePath.lastIndexOf(".") + 1);
            //构建文件
            File imageFile = new File(imageFilePath);
            //通过ImageIO把文件读取成BufferedImage对象
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            //构建字节数组输出流
            baos = new ByteArrayOutputStream();
            //写入流
            ImageIO.write(bufferedImage, suffix, baos);
            //通过字节数组流获取字节数组
            byte[] bytes = baos.toByteArray();
            //获取JDK8里的编码器Base64.Encoder转为base64字符
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通过文件对象获取文件后缀(MultipartFile的文件对象)
     * @param file MultipartFile对象
     * */
    public static String getFileSuffix(MultipartFile file){
        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    }

    /**
     * 通过文件名称获取文件后缀
     * @param fileName 文件名称
     * */
    public static String getFileSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }


    /**
     * 单文件上传，传地址、文件名、MultipartFile对象
     * @return 返回是否成功上传
     * */
    public static boolean uploadFile(MultipartFile file,String uploadTotalPath,String fileName){
        //创对应文件夹
        File tempFile = new File(uploadTotalPath);
        if(!tempFile.exists()){
            tempFile.mkdirs();
        }
        File saveFile = new File(uploadTotalPath + fileName);

        try{
            file.transferTo(saveFile);
        }catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * 不传文件名，默认是原始文件名
     * */
    public static boolean uploadFile(MultipartFile file,String uploadTotalPath){
        return uploadFile(file, uploadTotalPath, file.getOriginalFilename());
    }
}
