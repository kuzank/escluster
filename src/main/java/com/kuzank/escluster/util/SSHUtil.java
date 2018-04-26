package com.kuzank.escluster.util;

import com.jcraft.jsch.*;
import com.kuzank.escluster.common.bean.OperateStatus;
import com.kuzank.escluster.entity.LinuxConnEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Description: </p>
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

    public static boolean hasDir(Session session, String dirName) {
        ChannelSftp channelSftp = SSHUtil.getChannelSftp(session);
        try {
            channelSftp.cd(dirName);
            return true;
        } catch (SftpException sException) {
            return false;
        }
    }

    /**
     * @return 通过 shell 的 which 来判断远程主机是否有命令 command
     */
    public static boolean hasCommand(Session session, String command) {
        //要验证的字符串
        String result = SSHUtil.executeCommand(session, "which " + command);
        // 正则表达式规则
        String regEx = ".*" + command + ".*";
        // 编译正则表达式 ,忽略大小写的写法
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(result);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        boolean b = matcher.find();
        return b;
    }

    /**
     * @return 判断远程主机是否安装 lsof 命令，再根据 lsof 命令判断端口是否被占用
     */
    public static boolean isPortInUse(Session session, String port) {

        if (!hasCommand(session, "lsof")) {
            if (hasCommand(session, "yum")) {
                SSHUtil.executeCommand(session, "yum -y install lsof > /dev/null 2>&1");
            } else
                SSHUtil.executeCommand(session, "apt-get -y install lsof > /dev/null 2>&1");
        }

        String result = SSHUtil.executeCommand(session, "lsof -i:" + port);

        for (byte b : result.getBytes())
            if (b != 0)
                return true;
        return false;
    }

    /**
     * 判断远程主机是否可以连接
     */
    public static OperateStatus isLinuxConnected(LinuxConnEntity connEntity) {

        // 验证参数信息
        if (CheckUtil.NotEmpty(connEntity.getHost(), connEntity.getUsername(), connEntity.getPassword()) != OperateStatus.SUCCESS) {
            return OperateStatus.PARAM_EMPTY;
        }

        Session session = SSHUtil.getSession(connEntity);

        if (session.isConnected()) {
            return OperateStatus.SUCCESS;
        } else {
            session.disconnect();
            return OperateStatus.LINUX_CANT_CONNECT;
        }
    }

}


