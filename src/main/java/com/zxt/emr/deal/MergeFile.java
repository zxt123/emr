package com.zxt.emr.deal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

public class MergeFile {
    public static final int BUFSIZE = 1024 * 8;
    public static void mergeFiles(String outFile, String[] files) {
        FileChannel outChannel = null;
        out.println("Merge " + Arrays.toString(files) + " into " + outFile);
        try {
            outChannel = new FileOutputStream(outFile).getChannel();
            for(String f : files){
                Charset charset=Charset.forName("utf-8");
                CharsetDecoder chdecoder=charset.newDecoder();
                CharsetEncoder chencoder=charset.newEncoder();
                FileChannel fc = new FileInputStream(f).getChannel();
                ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
                CharBuffer charBuffer=chdecoder.decode(bb);
                ByteBuffer nbuBuffer=chencoder.encode(charBuffer);
                while(fc.read(nbuBuffer) != -1){

                    bb.flip();
                    nbuBuffer.flip();
                    outChannel.write(nbuBuffer);
                    bb.clear();
                    nbuBuffer.clear();
                }
                fc.close();
            }
            out.println("Merged!! ");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {if (outChannel != null) {outChannel.close();}} catch (IOException ignore) {}
        }
    }
    public static void main(String[] args){
//        String[] files={"D:\\文件\\毕设\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\02-病史特点-1-200\\病史特点-1.txt","D:\\文件\\毕设\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\02-病史特点-1-200\\病史特点-2.txt"};
//        String target="C:\\Users\\zxt\\Desktop\\02-病史特点-1target.txt";
//        MergeFile.mergeFiles(target,files);
        List<String> lists = new LinkedList<>();
        for (int num =42;num<52;num++){
            if (num == 45){
                continue;
            }
            String target =  "C:\\Users\\zxt\\Desktop\\DataLabelMyself\\label\\label-"+num+".txt";
            lists.add(target);
        }
//        for (int num =31;num<42;num++){
//            String target =  "C:\\Users\\zxt\\Desktop\\DataLabelMyself\\label\\label-"+num+".txt";
//            lists.add(target);
//        }
        int size = lists.size();
        String[] files = (String[]) lists.toArray(new String[size]);
        String target="C:\\Users\\zxt\\Desktop\\test2019.txt";
        MergeFile.mergeFiles(target,files);

    }
}
