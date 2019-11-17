package com.example.punch.util;

import com.example.punch.contract.Tuple;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author zzs
 * @date 2019/10/25 16:58
 */
public class FileUtils {

    private FileUtils() {
    }

    /***
     * 读取指定文件的最后一行
     *
     * @param file
     * @return
     */
    public static Tuple<String> readLastLine(File file) {
        Tuple<List<String>> tuple = readLastNLine(file, 1L);
        if (tuple == null || tuple.getResult().isEmpty()) {
            return null;
        }
        return new Tuple<>(tuple.getIndex(), tuple.getResult().get(0));
    }

    /**
     * 读取指定文件的最后N行
     *
     * @param file
     * @param numRead
     * @return
     */
    public static Tuple<List<String>> readLastNLine(File file, long numRead) {
        List<String> result = new ArrayList<>();
        long count = 0;
        long lastFrom = 0;
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long length = raf.length();
            if (length == 0L) {
                return new Tuple<>(lastFrom, result);
            } else {
                long pos = length - 1;
                while (pos > 0) {
                    //先减一为了防止最后有一个换行而导致读取到null
                    //如果文件最后有多个空行，这里先不考虑了
                    pos--;
                    raf.seek(pos);
                    if (raf.readByte() == '\n') {
                        result.add(raf.readLine());
                        count++;
                        if (count == numRead) {
                            //记录位置，这时的pos指向'\n'，所以要+1
                            lastFrom = pos + 1;
                            break;
                        }
                    }
                }

                //pos等于0，说明上边的where条件里的读取行数还不满足
                if (pos == 0) {
                    raf.seek(0);
                    result.add(raf.readLine());
                    lastFrom = 0L;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Tuple<>(lastFrom, result);
    }

    /**
     * 从给定的指针位置开始写入数据
     *
     * @param file
     * @param index
     * @param data
     */
    public static void writeFromIndex(File file, long index, String data) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rws")) {
            raf.setLength(index);
            raf.seek(index);
            raf.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
