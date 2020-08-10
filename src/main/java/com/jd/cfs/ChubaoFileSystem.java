package com.jd.cfs;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.util.Progressable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

/**
 * @author liyubo
 * @create 2020-08-05 3:24 下午
 **/
public class ChubaoFileSystem extends FileSystem {
    public URI getUri() {
        return null;
    }

    public FSDataInputStream open(Path f, int bufferSize) throws IOException {
        return null;
    }

    public FSDataOutputStream create(Path f, FsPermission permission, boolean overwrite, int bufferSize, short replication, long blockSize, Progressable progress) throws IOException {
        return null;
    }

    public FSDataOutputStream append(Path f, int bufferSize, Progressable progress) throws IOException {
        return null;
    }

    public boolean rename(Path src, Path dst) throws IOException {
        return false;
    }

    public boolean delete(Path f, boolean recursive) throws IOException {
        return false;
    }

    public FileStatus[] listStatus(Path f) throws FileNotFoundException, IOException {
        return new FileStatus[0];
    }

    public void setWorkingDirectory(Path new_dir) {

    }

    public Path getWorkingDirectory() {
        return null;
    }

    public boolean mkdirs(Path f, FsPermission permission) throws IOException {
        return false;
    }

    public FileStatus getFileStatus(Path f) throws IOException {
        return null;
    }
}
