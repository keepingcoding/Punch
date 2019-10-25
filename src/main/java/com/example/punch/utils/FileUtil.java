package com.example.punch.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzs
 * @date 2019/10/25 16:58
 */
public class FileUtil {

    public static void main(String[] args) {
        File file = new File("D:\\temp\\Ttt.java");
        List<String> strings = readLastNLine(file, 2);
        System.err.println(strings);
    }

    public static void readLastLine(File file) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long len = raf.length();
            long pos = len - 1;
            raf.seek(pos);
            while (pos > 0) {
                if (raf.readByte() == '\n') {
                    String row = new String(raf.readLine().getBytes(), "UTF-8");
                    System.out.println(row);
                    break;
                }
                pos--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<String> readLastNLine(File file, long numRead) {
        List<String> result = new ArrayList<>();
        //定义行数
        long count = 0;
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }
        RandomAccessFile fileRead = null;
        try {
            fileRead = new RandomAccessFile(file, "r");
            long length = fileRead.length();
            if (length == 0L) {
                return result;
            } else {
                long pos = length - 1;
                while (pos > 0) {
                    pos--;
                    fileRead.seek(pos);
                    if (fileRead.readByte() == '\n') {
                        String line = new String(fileRead.readLine().getBytes(), "UTF-8");
                        result.add(line);
                        count++;
                        if (count == numRead) {//满足指定行数 退出。
                            break;
                        }
                    }
                }

                if (pos == 0) {
                    fileRead.seek(0);
                    result.add(new String(fileRead.readLine().getBytes("ISO-8859-1"), "UTF-8"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileRead != null) {
                try {
                    // 关闭资源
                    fileRead.close();
                } catch (Exception e) {
                }
            }
        }

        return result;
    }

}
