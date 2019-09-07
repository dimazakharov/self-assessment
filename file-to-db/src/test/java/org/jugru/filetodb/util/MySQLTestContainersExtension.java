package org.jugru.filetodb.util;

import org.hibernate.dialect.MySQL5Dialect;
import org.junit.jupiter.api.extension.Extension;
import org.testcontainers.containers.MySQLContainer;

public class MySQLTestContainersExtension implements Extension {

    static {

        MySQLContainer mySQLContainer = new MySQLContainer();
        mySQLContainer.start();


        System.setProperty("spring.datasource.driver-class-name", mySQLContainer.getDriverClassName());
        System.setProperty("spring.datasource.url", mySQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", mySQLContainer.getUsername());
        System.setProperty("spring.datasource.password", mySQLContainer.getPassword());
        System.setProperty("spring.jpa.properties.hibernate.dialect", MySQL5Dialect.class.getCanonicalName());
    }
}
