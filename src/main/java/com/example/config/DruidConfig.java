package com.example.config;




import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DruidConfig {
    @Bean
    //绑定数据源配置
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     * 配置Druid监控
     * 1. 配置⼀个管理后台的Servlet
     * 2. 配置⼀个监控的filter
     */
    @Bean // 1. 配置⼀个管理后台的Servlet
    public ServletRegistrationBean statViewServlet() {
        //StatViewServlet是 配置管理后台的servlet
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),
                "/druid/*");
        //配置初始化参数
        Map<String, String> initParam = new HashMap<>();
        //访问的⽤户名密码
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME, "root");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD, "123");
        //允许访问的ip，默认所有ip访问
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW, "");
        //禁⽌访问的ip
        initParam.put(StatViewServlet.PARAM_NAME_DENY, "192.168.10.1");
        bean.setInitParameters(initParam);
        return bean;
    }
    //2. 配置⼀个监控的filter
    @Bean
    public FilterRegistrationBean filter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //配置初始化参数
        Map<String, String> initParam = new HashMap<>();
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,
                "*.js,*.css,/druid/*");
        //拦截所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

    @Bean
    // MyBatis注解版-配置类替换配置⽂件
    public ConfigurationCustomizer configurationCustomizer(){
        ConfigurationCustomizer configurationCustomizer = new
                ConfigurationCustomizer() {
                    @Override
                    public void customize(org.apache.ibatis.session.Configuration
                                                  configuration) {
                        //开启驼峰命名⽅式
                        configuration.setMapUnderscoreToCamelCase(true);
                    }
                };
        return configurationCustomizer;
    }
}
