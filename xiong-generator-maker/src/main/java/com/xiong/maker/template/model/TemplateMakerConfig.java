package com.xiong.maker.template.model;

import com.xiong.maker.meta.Meta;
import lombok.Data;

/**
 * 模板制作配置
 */
@Data
public class TemplateMakerConfig {
    private Long id;

    private Meta meta;

    private String originProjectPath;

    private TemplateMakerFileConfig fileConfig = new TemplateMakerFileConfig();

    private TemplateMakerModelConfig modelConfig=new TemplateMakerModelConfig();

    private TemplateMakerOutputConfig outputConfig=new TemplateMakerOutputConfig();
}
