package com.kuzank.escluster.util;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Description: </p>
 */
public class ShellUtil {
    // 执行Linux shell命令,执行完命令可将shell执行子线程destroy
    public static String execute_shell_destroy(String shell) {
        BufferedReader br = null;
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(shell);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    // 执行Linux shell命令,执行完命令可将shell执行子线程destroy
    public static String execute_shells_destroy(String[] shell) {
        BufferedReader br = null;
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(shell);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    // 执行Linux shell命令,shell执行子线程会一直等待shell命令执行结束
    public static String execute_shell_wait(String shell) {
        BufferedReader br = null;
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(shell);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            process.wait();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    // 执行Linux shell命令,shell执行子线程会一直等待shell命令执行结束
    public static String execute_shell_waitfor(String shell) {
        BufferedReader br = null;
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(shell);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    // 判断环境变量中是否有command的命令，通过shell的which来判断
    public static boolean has_command(String command) {
        // 要验证的字符串
        String result = execute_shell_destroy("which " + command);
        // 正则表达式规则
        String regEx = ".*" + command + ".*";
        // 编译正则表达式 ,忽略大小写的写法
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(result);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        boolean b = matcher.find();
        return b;
    }

    // 通过lsof命令判断port端口是否被占用
    public static boolean is_port_in_use(String port) {
        // 判断lsof命令是否已经安装,若没有则安装
        if (!has_command("lsof")) {
            if (has_command("yum")) {
                execute_shell_waitfor("yum -y install lsof > /dev/null 2>&1");
            } else
                execute_shell_waitfor("apt-get -y install lsof > /dev/null 2>&1");
        }
        String result = execute_shell_destroy("lsof -i:" + port);
        if (result == null || "".equals(result)) {
            return false;
        }
        return true;
    }

    // 读取filename文件并返回字符串
    public static String read(String filename) {
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
