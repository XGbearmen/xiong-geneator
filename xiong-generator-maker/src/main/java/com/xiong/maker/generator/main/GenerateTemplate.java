package com.xiong.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.xiong.maker.generator.JarGenerator;
import com.xiong.maker.generator.ScriptGenerator;
import com.xiong.maker.generator.file.DynamicFileGenerator;
import com.xiong.maker.meta.Meta;
import com.xiong.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public abstract class GenerateTemplate {
    public void doGenerate() throws IOException, InterruptedException, TemplateException {
        Meta meta = MetaManager.getMetaObject();

        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
       doGenerator(meta,outputPath);
    }

    public void doGenerator(Meta meta,String outputPath) throws TemplateException, IOException, InterruptedException {
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }
        String sourceCopyDestPath = copySource(meta, outputPath);

        generateCode(meta, outputPath);

        String jarPath = bulidJar(outputPath, meta);

        String shellOutputFilePath = bulidScript(outputPath, jarPath);

        bulidDist(outputPath, jarPath, shellOutputFilePath, sourceCopyDestPath);
    }
    /**
     * 生成精简版程序
     * @param outputPath
     * @param sourceCopyDestPath
     * @param jarPath
     * @param shellOutputFilePath
     */
    protected String bulidDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopyDestPath) {
        //生成精简版程序（产物包）
        String disOutPutPath= outputPath +"-dist";
        String targetAbsolutePath=disOutPutPath+File.separator+"target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath= outputPath +File.separator+ jarPath;
        //拷贝jar包
        FileUtil.copy(jarAbsolutePath,targetAbsolutePath,true);
        //拷贝脚本文件
        FileUtil.copy(shellOutputFilePath,disOutPutPath,true);
        FileUtil.copy(shellOutputFilePath +".bat",disOutPutPath,true);
        //拷贝源模板文件
        FileUtil.copy(sourceCopyDestPath,disOutPutPath,true);
        return disOutPutPath;
    }

    /**
     * 封装脚本
     *
     * @param outputPath
     * @param jarPath
     * @return
     * @throws IOException
     */
    protected String bulidScript(String outputPath, String jarPath) throws IOException {
        // 封装脚本
        String shellOutputFilePath = outputPath + File.separator + "generator";
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }

    /**
     * 构建 jar 包
     * @param outputPath
     * @return 返回 jar 包的相对路径
     * @throws IOException
     * @throws InterruptedException
     */
    protected String bulidJar(String outputPath, Meta meta) throws IOException, InterruptedException {
        // 构建 jar 包
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        return jarPath;
    }

    /**
     * 代码生成
     * @param meta
     * @param outputPath
     * @throws IOException
     * @throws TemplateException
     */
    protected void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
        // 读取 resources 目录
        String inputResourcePath = "";

        // Java 包基础路径
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // model.DataModel.java.ftl
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.GenerateCommand.java.ftl
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.JsonGenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/JsonGenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/JsonGenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // generator.DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // generator.MainGenerator.java.ftl
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // generator.StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

//        // README.md
//        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
//        outputFilePath = outputPath + File.separator + "README.md";
//        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);
    }

    /**
     * 复制原始文件
     *
     * @param meta
     * @param outputPath
     * @return
     */
    protected String copySource(Meta meta, String outputPath) {
        //从原始模板文件路径复制到生成的代码包中
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputPath +File.separator+".source";
        FileUtil.copy(sourceRootPath,sourceCopyDestPath,false);
        return sourceCopyDestPath;
    }

    /**
     * 制作压缩包
     * @param outputPath
     * @return
     */
    protected String buildZip(String outputPath){
        String zipPath=outputPath+".zip";
        ZipUtil.zip(outputPath,zipPath);
        return zipPath;
    }
}
