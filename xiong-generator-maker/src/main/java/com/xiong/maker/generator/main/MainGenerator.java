package com.xiong.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.xiong.maker.generator.JarGenerator;
import com.xiong.maker.generator.ScriptGenerator;
import com.xiong.maker.generator.file.DynamicFileGenerator;
import com.xiong.maker.meta.Meta;
import com.xiong.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator extends GenerateTemplate{
    @Override
    protected String bulidDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopyDestPath) {
        return "";
    }
}
