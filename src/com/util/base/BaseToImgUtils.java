package com.util.base;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: D丶Cheng
 * Date: 2017/6/18
 * Time: 20:27
 * Description:
 * 从Base64提取图片信息的工具类，有data:image/XXX;base64（只表示这是图片，并且XXX表示图片拓展名，
 * base64转回图片必须去除）,前缀的要先调用formatBase64()方法去前缀，不然无法提取出图片
 */
public class BaseToImgUtils {

    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理 <br>
     * 编码后的base64字符串不带data:image/XXX;base64,前缀<br>
     *
     * @param imgFilePath 图片路径
     * @return
     */
    public static String GetImageStr(String imgFilePath) {
        byte[] data = null;

        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * 获取图片文件的拓展名
     *
     * @param baseStr
     * @return
     */
    public static String getSuffix(String baseStr) {
        int start = baseStr.indexOf("data:image/");
        start += "data:image/".length();
        int end = baseStr.indexOf(";base64,");
        return baseStr.substring(start, end);
    }

    /**
     * 格式化base64字符串
     * 一般拿到的base64都是以data:image/XXX;base64,开头的
     * (不去除data:image/XXX;base64,无法还原图片)
     *
     * @param baseStr
     * @return
     */
    public static String formatBase64(String baseStr) {
        int end = baseStr.indexOf(";base64,");
        return baseStr.substring(end + ";base64,".length());
    }

    /**
     * 将base64字符保存文本文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static Boolean base64ToFile(String base64Code, String targetPath) {
        //对字节数组字符串进行Base64解码并生成图片
        if (base64Code == null) { //图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64Code);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成新图片，并写入磁盘
            OutputStream out = new FileOutputStream(targetPath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对字节数组字符串进行Base64解码并转化为输入流
     *
     * @param baseStr
     * @return
     * @throws IOException
     */
    public static InputStream BaseToInputStream(String baseStr) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = new byte[0];
        try {
            bytes = decoder.decodeBuffer(baseStr.replaceAll("\r\n", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Base64解码
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        return new ByteArrayInputStream(bytes);
    }

    public static void main(String[] args) {
        /**** 测试两个方法 ****/

        //图片文件转base64
        String baseStr = GetImageStr("E:\\图片\\1.jpg");
        System.out.println(baseStr);

        //base64转图片文件，并写入D盘，有data:image/XXX;base,
        // 前缀的要先调用formatBase64()方法去前缀，不然无法提取出图片
        Boolean flag = base64ToFile(baseStr, "G:\\new.jpg");
        if (flag == true){
            System.out.println("写入成功...");
        }else {
            System.out.println("写入失败...");
        }
    }
}