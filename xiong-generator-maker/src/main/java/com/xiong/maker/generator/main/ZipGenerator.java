package com.xiong.maker.generator.main;

public class ZipGenerator extends GenerateTemplate{
    @Override
    protected String bulidDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopyDestPath) {
        String distPath = super.bulidDist(outputPath, jarPath, shellOutputFilePath, sourceCopyDestPath);
        return super.buildZip(distPath);
    }
}
