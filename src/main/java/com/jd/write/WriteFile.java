package com.jd.write;

import java.util.Arrays;

/**
 * @author liyubo
 * @create 2020-08-07 9:31 上午
 **/
public class WriteFile {

    private static final long PARENT_ID = 1;
    private static final int STATUS_SUCCESS = 0;

    private static final String LOCAL_PATH = "/Users/liyubo/gitpath/work/hdfs-sdk/src/main/java/com/jd/write";
    private static final String ONLINE_PATH = "/home/liyubo/hdfs-sdk/com/jd/write";

    public native int NewClient();

    public native long create(long pid, int index);

    public native int open(long iid);

    public native int write(long iid, long offset, String data);

    public native int close(long iid);

    static {
        // load c dynamic library
//        System.setProperty("java.library.path", ONLINE_PATH);
        System.loadLibrary("write");
    }

    public int createFile(int fileIndex, long fileSize, long blockSize) {
        int status;
        long inodeId;
        long originFileSize = fileSize;

        long start = System.currentTimeMillis();

        System.out.println("[java->main] start to write file at time:" + start + ", fileSize: " + fileSize + ", blockSize: " + blockSize);
        // init
        WriteFile writeFile = new WriteFile();
        status = writeFile.NewClient();
        if (status != STATUS_SUCCESS) {
            System.out.println("[java->createFile] Init cfs client failed.");
            return status;
        }
        System.out.println("[java->createFile] Init cfs client success.");

        // create a file, and get a inode
        inodeId = writeFile.create(PARENT_ID, fileIndex);
        if (inodeId < 0) {
            System.out.println("[java->createFile] Create file failed, inode id : " + inodeId);
            return Long.valueOf(inodeId).intValue();
        }
        System.out.println("[java->createFile] Create file success, inode id : " + inodeId);

        // open stream via a inode
        status = writeFile.open(inodeId);
        if (status != STATUS_SUCCESS) {
            System.out.println("[java->createFile] open file stream failed.");
            return status;
        }
        System.out.println("[java->createFile] Open file stream success.");

        // write data to stream
        long offset = 0;
        int writeLength;
        String data = createData(blockSize);

        while (fileSize > 0) {
//            System.out.println("[java->createFile] offset : " + offset + ", fileSize : " + fileSize + ", data length : " + data.getBytes().length);
            writeLength = writeFile.write(inodeId, offset, data);
//            System.out.println("[java->createFile] Write length : " + writeLength);
            offset += writeLength;
            fileSize -= writeLength;
        }
        System.out.println("[java->createFile] Write file finished. cost: "+(System.currentTimeMillis() - start) + ", fileSize: " + originFileSize + ", blockSize: " + blockSize);

        // close
        status = writeFile.close(inodeId);
        if (status != STATUS_SUCCESS) {
            System.out.println("[java->createFile] close file failed");
            return status;
        }
        return 0;
    }

    private static String createData(long dataSize) {
        byte[] bytes = new byte[Long.valueOf(dataSize).intValue()];
        Arrays.fill(bytes, (byte) 97);
        return new String(bytes);
    }

    public static void main(String[] args) {
        WriteFile writeFile = new WriteFile();

        int fileIndex;
        long fileSize;
        long blockSize;

        try {
            // write 1GB file per 1KB
            fileSize = 1024 * 1024 * 1024L;
            blockSize = 1024;
            fileIndex = 101;
            writeFile.createFile(fileIndex, fileSize, blockSize);

            // write 1GB file per 128KB
            fileSize = 1024 * 1024 * 1024L;
            blockSize = 128 * 1024;
            fileIndex = 102;
            writeFile.createFile(fileIndex, fileSize, blockSize);

            // write 1GB file per 1KB
            fileSize = 1024 * 1024 * 1024L;
            blockSize = 1024 * 1024;
            fileIndex = 103;
            writeFile.createFile(fileIndex, fileSize, blockSize);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }
}
