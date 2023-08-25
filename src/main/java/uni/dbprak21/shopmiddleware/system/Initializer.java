package uni.dbprak21.shopmiddleware.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddleware;

import java.util.Arrays;

@Component
public class Initializer implements ApplicationRunner, ShopMiddleware {

    @Autowired
    private Environment environment;

    // Diese Logik wird beim Starten der Applikation automatisch getriggert.

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }

    public void init() {
        // Zur Demonstration werden hier aus Environment ein paar Werte extrahiert, die aus application.properties stammen
        // Hier könnten noch weitere INIT-Funktionalitäten hinzugefügt werden
        String profile = Arrays.toString(environment.getDefaultProfiles());
        String jdbcDriver = environment.getProperty("spring.datasource.url");
        String hibernateDialect = environment.getProperty("spring.jpa.properties.hibernate.dialect");

        System.out.println();
        System.out.println("**********************************************************************************************************");
        System.out.println("* >>> Initializer is executed on application startup.  ");
        System.out.println("* >>> application.properties is loaded as config file. ");
        System.out.println("* ===> Reading environment defined by config:          ");
        System.out.println("*          Profile: " + profile);
        System.out.println("*          JDBC Driver: " + jdbcDriver);
        System.out.println("*          Hibernate Dialect: " + hibernateDialect);
        System.out.println("**********************************************************************************************************");
        System.out.println();
    }
}