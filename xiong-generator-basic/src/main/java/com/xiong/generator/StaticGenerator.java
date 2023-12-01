package com.xiong.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * 静态文件生成器
 */
public class StaticGenerator {
    public static void main(String[] args) {
        //获取整个项目根路径
        String propertyPath = System.getProperty("user.dir");
        String inputPath=propertyPath+File.separator+"xiong-generator-demo-projects"+ File.separator +"acm-template";
        String outputPath=propertyPath;
        copyFileByHutool(inputPath,outputPath);
    }

    /**
     * 拷贝文件(hutool)
     * @param inputPath 输入路径
     * @param outPath 输出路径
     */
    public static void copyFileByHutool(String inputPath,String outPath){
        FileUtil.copy(inputPath,outPath,false);
    }


}
