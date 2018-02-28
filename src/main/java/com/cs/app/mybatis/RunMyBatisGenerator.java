package com.cs.app.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RunMyBatisGenerator {
	
	public static void main(String[] args) {
			 RunMyBatisGenerator runMyBatisGenerator = new RunMyBatisGenerator();
			 runMyBatisGenerator.generator();
	}

	public void generator()  {
		try {
            System.out.println("start generator ...");
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            String url = RunMyBatisGenerator.class.getResource("/generatorConfig.xml").getFile();
            url = java.net.URLDecoder.decode(url,"utf-8");   
            File configFile = new File(url);
           // File configFile = new File(RunMyBatisGenerator.class.getResource("/generatorConfig.xml").getFile());
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            System.out.println("end generator!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
