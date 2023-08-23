package uni.dbprak21.shopmiddleware.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
import uni.dbprak21.shopmiddleware.ShopMiddleware;

@Component
public class Initializer implements ShopMiddleware {

    private final Environment environment;

    @Autowired
    public Initializer(Environment environment) {
        this.environment = environment;
    }

    public void init() {
        // Fetch properties from application.properties
        String dbUrl = environment.getProperty("spring.datasource.url");
        String dbUsername = environment.getProperty("spring.datasource.username");
        String dbPassword = environment.getProperty("spring.datasource.password");

        // Perform initialization tasks
        initializeDatabaseConnection(dbUrl, dbUsername, dbPassword);
        // Other initialization tasks
        // ...
    }

    private void initializeDatabaseConnection(String url, String username, String password) {
        // TODO: Implement database connection logic here!
    }
}