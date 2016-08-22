package com.shark.common.utils;

import javax.transaction.SystemException;

import com.shark.pcf.type.SubImagUrlType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2015-06-30.
 */
public class PdfTool {

    /**
     * Html文件转出PDF的方法.
     * @param htmlName
     * @param pdfName
     * @return
     */
    public static File getFile(String htmlName , String pdfName){

        //获取html转PDF工具安装目录
        String exportPdfUrl = Config.getConfigProperty(SubImagUrlType.exportPdfSoftwareUrl, "");

        /** html内容转PDF命令 */
        List<String> commandList = new ArrayList<String>();
        commandList.add(exportPdfUrl);
        commandList.add(htmlName);
        commandList.add("--custom-header");
        commandList.add("CONTENT-TYPE");
        commandList.add("application/x-www-form-urlencoded");
        commandList.add("--disable-external-links");
        commandList.add(pdfName);

        ProcessBuilder pb = new ProcessBuilder(commandList);

        pb.redirectErrorStream(true);
        BufferedReader inStreamReader = null;
        Process process = null;
        try {
            process = pb.start();
            inStreamReader = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            //此处用于判断,文件是否写完,不能删除.
            String line = null;
            while ((line = inStreamReader.readLine()) != null) {
                if (line != null) {
                    //执行...
                }
            }
            try {
                process.waitFor();
                if (process.exitValue() != 0) {
                    process.destroy();
                    throw new SystemException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (SystemException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        process.destroy();
        return new File(pdfName);
    }
}
