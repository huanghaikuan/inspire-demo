package com.wgs.codedesign.工厂模式.v2;

import com.wgs.codedesign.工厂模式.common_config.InvalidRuleConfigException;
import com.wgs.codedesign.工厂模式.common_config.RuleConfig;
import com.wgs.codedesign.工厂模式.parser.IRuleConfigParser;
import com.wgs.codedesign.工厂模式.v2.factory.RuleConfigParserFactory;

/**
 * @author: wanggenshen
 * @date: 2020/3/23 21:25.
 * @description: XXX
 */
public class RuleConfigSourceV2_2 {

    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        RuleConfigParserFactory factory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        if (factory == null) {
            throw new InvalidRuleConfigException( "Rule config file format is not supported: " + ruleConfigFilePath);
        }

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        IRuleConfigParser parser = factory.createParser();
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;

    }


    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return filePath.substring(filePath.indexOf(".") + 1);
    }
}
