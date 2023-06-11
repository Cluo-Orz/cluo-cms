package org.cluo.framework.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author canfuu.cts
 * @class CluoManagementApplication
 * @date 2023/6/1 23:54
 */
@SpringBootApplication(scanBasePackages = "org.cluo.framework.management")
public class CluoManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CluoManagementApplication.class, args);
    }
}
