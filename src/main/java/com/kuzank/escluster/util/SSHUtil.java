package com.kuzank.escluster.util;

import com.jcraft.jsch.*;
import com.kuzank.escluster.mapper.entity.LinuxConnEntity;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author kuzan
 * @since 2018/01/28
 */
public class SSHUtil {

    public static Session getSession(LinuxConnEntity linuxConnectBean) {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(linuxConnectBean.getUsername(), linuxConnectBean.getHost(), linuxConnectBean.getPort());
            session.setPassword(linuxConnectBean.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(30000);   // making a connection with timeout.

        } catch (JSchException e) {
            e.printStackTrace();
        }
        return session;
    }

    public static ChannelSftp getChannelSftp(Session session) {
        Channel channel = null;
        try {
            channel = session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建立SFTP通道的连接

        } catch (JSchException e) {
            e.printStackTrace();
        }
        return (ChannelSftp) channel;
    }

    public static String executeCommand(Session session, String command) {
        Channel channel = null;
        InputStream in;
        byte[] tmp = new byte[0];
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            in = channel.getInputStream();
            channel.connect();

            tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            channel.disconnect();
        }
        return new String(tmp);
    }

    public static void sendDir(Session session, String localDir, String remoteDor) {
        ChannelSftp channelSftp = getChannelSftp(session);
        try {
            channelSftp.put(localDir, remoteDor, ChannelSftp.OVERWRITE);
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            channelSftp.quit();
            channelSftp.disconnect();
        }
    }

}


