package com.meepwn.ssm.common.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author MeePwn
 */
public class FileUtils {

    public byte[] getContent(String filePath) {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead;
            while (offset < buffer.length && (numRead = fis.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            // 确保所有数据均被读取
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            return buffer;
        } catch (IOException e) {
            LogUtils.e("{}", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    LogUtils.e("{}", e);
                }
            }
        }
        return null;
    }

    /**
     * the traditional io way
     *
     * @param fileName 文件名
     * @return 文件流
     */
    public static byte[] toByteArrayTraditional(String fileName) {
        File f = new File(fileName);
        ByteArrayOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(f));
            bos = new ByteArrayOutputStream((int) f.length());
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len;
            while (-1 != (len = bis.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            LogUtils.e("{}", e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    LogUtils.e("{}", e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LogUtils.e("{}", e);
                }
            }
        }
        return null;
    }

    /**
     * NIO way
     *
     * @param fileName 文件名
     * @return 文件流
     */
    public static byte[] toByteArrayNIO(String fileName) {
        File f = new File(fileName);

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
            }
            return byteBuffer.array();
        } catch (IOException e) {
            LogUtils.e("{}", e);
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    LogUtils.e("{}", e);
                }
            }
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    LogUtils.e("{}", e);
                }
            }
        }
        return null;
    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param file 文件
     * @return 文件流
     */
    public static byte[] toByteArray(File file) {
        return toByteArray(file.getPath());
    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param fileName 文件名
     * @return 文件流
     */
    public static byte[] toByteArray(String fileName) {
        RandomAccessFile raf = null;
        FileChannel fc = null;
        try {
            raf = new RandomAccessFile(fileName, "r");
            fc = raf.getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            LogUtils.e("{}", e);
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    LogUtils.e("{}", e);
                }
            }
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException e) {
                    LogUtils.e("{}", e);
                }
            }
        }
        return null;
    }

    private FileUtils() {
    }

}
