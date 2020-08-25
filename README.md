# MAC OS
```
-- 
javac WriteFile.java
export CLASSPATH=/Users/liyubo/gitpath/work/hdfs-sdk/src/main/java
-- 
javah -jni com.jd.write.WriteFile

-- 
gcc -dynamiclib -I /Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/include/darwin -I /Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/include write.c -o libwrite.jnilib


-- 将client 的动态库编译进去
gcc -dynamiclib -I /Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/include/darwin -I /Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/include write.c -o libwrite.jnilib -L. -lclient


java com.jd.write.WriteFile



-- 根据 go 源码生成 C 的动态库
go build -buildmode=c-shared -o libclient.so ./client.go

cp /Users/liyubo/gopath/src/github.com/chubaofs/chubaofs/testClient/libclient.so .

scp -P 80 libclient.so /home/liyubo/hdfs-sdk/com/jd/write
```
# Linux
```
1、先编写java文件

2、编译java文件至class文件，并上传至服务器
javac WriteFile.java
scp -P 80 WriteFile.class /home/liyubo/hdfs-sdk/com/jd/write

3、根据class文件生成C的头文件
javah -jni com.jd.write.WriteFile

4、编写C源文件，并将C源文件上传至服务器
scp -P 80 write.c /home/liyubo/hdfs-sdk/com/jd/write

5、将c源文件编译成动态库文件，必须加-fPIC
gcc -shared -fPIC -I /export/servers/jdk1.8.0_181/include/linux -I /export/servers/jdk1.8.0_181/include write.c -o libwrite.so -L. -lclient
cp libwrite.so /export/servers/jdk1.8.0_181/lib/amd64/jli

6、将Golang的client源码编译成动态库
-- 编译
go build -buildmode=c-shared -o libclient.so ./client.go
scp libclient.so liyubo@172.28.77.55:/home/liyubo/hdfs-sdk/com/jd/write

7、执行java程序，带上参数Djava.library.path，指定要加载的动态库
export CLASSPATH=/home/liyubo/hdfs-sdk
export LD_LIBRARY_PATH=/export/servers/jdk1.8.0_181/lib/amd64/jli
java com.jd.write.WriteFile

java -Djava.library.path=. com.jd.write.WriteFile
```
