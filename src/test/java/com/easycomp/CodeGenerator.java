package com.easycomp;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 * 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 * @author rensq
 * @create 2019-07-07
 */
public class CodeGenerator {

    //TODO:多个表逗号分隔
    static String tableName = "t_dic";

    //逻辑删除字段名, 假如表没有逻辑删除字段，请忽视
    //static String logicDeleteFieldName = "isdelete";

    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //TODO:生成文件的输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        //TODO:也可以输出到物理路径
        //gc.setOutputDir("C://Users/Administrator/Desktop/备份/ssm/src/main/java");
        //TODO:作者
        gc.setAuthor("rensq");
        gc.setFileOverride(false);				//是否覆蓋已有文件 默认值：false
        gc.setOpen(false);						//是否打开输出目录 默认值:true
        gc.setSwagger2(true);					//开启 swagger2 模式 默认false
        gc.setBaseColumnList(true);				//开启 baseColumnList 默认false
        gc.setBaseResultMap(true);				//开启 BaseResultMap 默认false
        gc.setDateType(DateType.ONLY_DATE);
        //c.setEntityName("%sEntity");			//实体命名方式  默认值：null 例如：%sEntity 生成 UserEntity
        gc.setMapperName("%sMapper");			//mapper 命名方式 默认值：null 例如：%sDao 生成 UserDao
        gc.setXmlName("%sMapper");				//Mapper xml 命名方式   默认值：null 例如：%sDao 生成 UserDao.xml
        gc.setServiceName("%sService");			//service 命名方式   默认值：null 例如：%sBusiness 生成 UserBusiness
        gc.setServiceImplName("%sServiceImpl");	//service impl 命名方式  默认值：null 例如：%sBusinessImpl 生成 UserBusinessImpl
        gc.setControllerName("%sController");	//controller 命名方式    默认值：null 例如：%sAction 生成 UserAction
        mpg.setGlobalConfig(gc);

        //TODO:数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://100.100.200.71:3306/ehall?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        //dsc.setSchemaName("public");
        dsc.setUsername("root");
        dsc.setPassword("aaaaaa");
        mpg.setDataSource(dsc);

        //TODO:包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.easycomp.ehall");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        //TODO:如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //TODO: 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity.java");
        // templateConfig.setService();
        templateConfig.setController("templates/controller.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEnableSqlFilter(false);//需要关闭sql过滤
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableName.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setLogicDeleteFieldName(logicDeleteFieldName); // 逻辑删除字段名称
        //TODO:去掉表中前缀
        strategy.setTablePrefix("t_");
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}