package com.inet.code.utlis;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * FileUtils
 *
 * @author HCY
 * @since 2020/11/21 下午 06:42
 */
public class FileUtils {
    /**
    * 加密/解密文件的密钥
    * @author HCY
    * @since 2020/11/21 下午 06:53
    */
    public static final int CRYPTO_SECRET_KEY = 0x99;

    public static int FILE_DATA = 0;

    /**
    * 加密/解密 文件
    * @author HCY
    * @since 2020/11/21 下午 06:53
    * @param srcFile: 原文件
    * @param encFile: 加密/解密后的文件
    */
    public static void cryptoFile(File srcFile, File encFile) throws Exception {

        InputStream inputStream = new FileInputStream(srcFile);
        OutputStream outputStream = new FileOutputStream(encFile);
        while ((FILE_DATA = inputStream.read()) > -1) {
            outputStream.write(FILE_DATA ^ CRYPTO_SECRET_KEY);
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    /**
    * MultipartFile 生成临时文件
    * @author HCY
    * @since 2020/11/21 下午 06:53
    * @param multipartFile: 文件
     * @param tempFilePath: 临时文件路径
    * @return java.io.File
    */
    public static File multipartFileToFile(MultipartFile multipartFile, String tempFilePath) {

        File file = new File(tempFilePath);
        // 获取文件原名
        String originalFilename = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (!file.exists()) {
            file.mkdirs();
        }
        // 创建临时文件
        File tempFile = new File(tempFilePath + "\\" + UUID.randomUUID().toString().replaceAll("-", "") + suffix);
        try {
            if (!tempFile.exists()) {
                // 写入临时文件
                multipartFile.transferTo(tempFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    /**
    * 上传文件
    * @author HCY
    * @since 2020/11/21 下午 06:54
    * @param fileServerPath: 文件服务器地址
    * @param folderPath: 存放的文件夹路径（比如存放在文件服务器的 upload 文件夹下，即 ”/upload“）
    * @param uploadFile: 需要上传的文件
    * @param isCrypto: 是否加密
    * @return java.lang.String
    */
    public static String uploadByJersey(String fileServerPath, String folderPath, File uploadFile, boolean isCrypto) {

        String suffix = uploadFile.getName().substring(uploadFile.getName().lastIndexOf("."));
        String randomFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        String fullPath =  folderPath + "\\" + randomFileName;
        try {
            if (isCrypto) {
                // 创建加密文件
                File cryptoFile = new File(
                        uploadFile
                                .getPath()
                                .substring(0, uploadFile.getPath().lastIndexOf("."))
                                + "crypto"
                                + uploadFile.getPath()
                                .substring(uploadFile.getPath().lastIndexOf(".")));
                // 执行加密
                cryptoFile(uploadFile, cryptoFile);
                // 保存加密后的文件
                uploadFile = cryptoFile;
            }
            // 创建 Jersey 服务器
            Client client = Client.create();
            WebResource wr = client.resource(fullPath);
            // 上传文件
            wr.put(String.class, fileToByte(uploadFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullPath;
    }

    /**
    * 下载文件
    * @author HCY
    * @since 2020/11/21 下午 06:54
    * @param url: 文件路径
     * @param filePath: 文件保存路径
     * @param fileName: 文件名称（包含文件后缀）
     * @param isCrypto: 是否解密
    * @return java.io.File
    */
    public static File downloadByURL(String url, String filePath, String fileName, boolean isCrypto) {

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOut;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        try {
            URL httpUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            if (!filePath.endsWith("\\")) {
                filePath += "\\";
            }
            file = new File(filePath + fileName);
            fileOut = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOut);
            byte[] bytes = new byte[4096];
            int length = bufferedInputStream.read(bytes);
            //保存文件
            while (length != -1) {
                bufferedOutputStream.write(bytes, 0, length);
                length = bufferedInputStream.read(bytes);
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isCrypto) {
            try {
                // 创建解密文件
                File cryptoFile = new File(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext().getRealPath("/") +  "\\temp\\" + UUID.randomUUID().toString().replaceAll("-", "") + file.getName().substring(file.getName().lastIndexOf(".")));
                // 执行解密
                cryptoFile(file, cryptoFile);
                // 删除下载的原文件
                file.delete();
                // 保存解密后的文件
                file = cryptoFile;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
    * 删除文件服务器上的文件
    * @author HCY
    * @since 2020/11/21 下午 06:55
    * @param url: 文件路径
    * @return boolean
    */
    public static boolean deleteByJersey(String url) {
        try {
            Client client = new Client();
            WebResource webResource = client.resource(url);
            webResource.delete();
            return true;
        } catch (UniformInterfaceException e) {
            e.printStackTrace();
        } catch (ClientHandlerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * File转Bytes
    * @author HCY
    * @since 2020/11/21 下午 06:55
    * @param file: 文件
    * @return byte[]
    */
    public static byte[] fileToByte(File file) {

        byte[] buffer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int n;
            while ((n = fileInputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, n);
            }
            fileInputStream.close();
            byteArrayOutputStream.close();
            buffer = byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
