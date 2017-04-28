package cn.andyleeblog.customutils;

import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * FtpUtil
 * 上传文件到ftp服务器
 * Created by andy on 17-4-27.
 */

public class FtpUtil {
    /**
     * 向FTP服务器上传文件
     *
     * @param url      FTP服务器hostname
     * @param port     端口默认80
     * @param username 用户名
     * @param password 密码
     * @param path     FTP服务器保存目录，是linux下的目录形式,如/photo/
     * @param filename 文件名称 上传到FTP服务器上的文件名,是自己定义的名字
     * @param input    输入流
     * @return success or failed
     */
    public static boolean upload(String url, int port, String username,
                                 String password, String path, String filename, InputStream input) {
        boolean success = false;
        FTPClient ftpClient = new FTPClient();
        try {
            int reply;
            ftpClient.connect(url, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.login(username, password);// 登录
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }
            boolean isExist = ftpClient.changeWorkingDirectory(path);
            if (!isExist) {
                ftpClient.makeDirectory(path);
                ftpClient.changeWorkingDirectory(path);
            }

            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("utf-8");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //处理中文名称的文件名，如果不加这一句的话，中文命名的文件是不能上传的
            filename = new String(filename.getBytes("GBK"), "iso-8859-1");
            ftpClient.storeFile(filename, input);


            input.close();
            ftpClient.logout();
            success = true;
            Log.e("ftp upload is :", "success");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    Log.e("ftp连接关闭失败", ioe.toString());
                }
            }
        }
        return success;
    }
}
