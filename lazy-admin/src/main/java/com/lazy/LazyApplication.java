package com.lazy;

import com.lazy.framework.interceptor.CustomInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 启动程序
 *
 * @author lazy
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class LazyApplication
{
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(LazyApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Lazy启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
