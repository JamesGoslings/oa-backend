package com.myPro.common.utils;

import com.google.common.primitives.Bytes;
import com.myPro.common.result.Result;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    public static String classpath;

    public static String rootPath = "D:\\Program\\JAVA\\Java-projects-me\\oa-files";

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

    /**
     * 将字符串存到指定目录的新文件中
     * @param dataStr 存有数据的字符串
     * @param path  新文件的路径（除去根路径和文件名）
     * @param name  新文件的名称
     * @return 是否成功
     */
    public static boolean setStrToFile(String dataStr, String path, String name){
        // TODO 创建目录（如果不存在）
        Path dirPath = Paths.get(rootPath + "/" + path);
        if(!Files.exists(dirPath)){
            try {
                Files.createDirectories(dirPath);
            }catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }
        // TODO 写入文件
        String filePath = rootPath + "/" + path + "/" + name;
        try {
            Path totalFilePath = Paths.get(filePath);
            Files.writeString(totalFilePath, dataStr, StandardOpenOption.CREATE);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取一个bpmn的xml中的流程id
     * @param xmlData xml的数据字符串
     * @return 该流程id，如果查找失败返回空串
     */
    public static String getBpmnXmlId(String xmlData) {
        String regex = "<bpmn:process[^>]*id=\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(xmlData);

        if (matcher.find()) {
            return matcher.group(1); // 第一个捕获组是id属性的值
        }
        return ""; // 如果没有找到匹配的process元素或id属性，则返回空字符串
    }
    /**
     * 将bpmnXml的字符串存到新文件中
     * @param dataStr 含xml数据的字符串
     * @param path  新文件的路径（除去根路径和文件名）
     * @param suffix 新文件的名称的后缀
     * @return 是否成功
     */
    public static boolean setXmlStrToFile(String dataStr, String path, String suffix){
        String bpmnXmlId = getBpmnXmlId(dataStr);
        if(Objects.equals(bpmnXmlId, "")){
            return false;
        }
        return setStrToFile(dataStr, path, bpmnXmlId + suffix);
    }
}

