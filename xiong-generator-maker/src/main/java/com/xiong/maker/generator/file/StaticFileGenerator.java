package com.xiong.maker.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * 静态文件生成器
 */
public class StaticFileGenerator {

    /**
     * 拷贝文件(hutool)
     * @param inputPath 输入路径
     * @param outPath 输出路径
     */
    public static void copyFileByHutool(String inputPath,String outPath){
        FileUtil.copy(inputPath,outPath,false);
    }


}
