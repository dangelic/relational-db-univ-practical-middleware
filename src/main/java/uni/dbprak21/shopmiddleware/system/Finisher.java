package uni.dbprak21.shopmiddleware.system;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddleware;

@Component
public class Finisher implements ShopMiddleware {
    @PreDestroy
    public void finish() {
        System.out.println("Release Database Resources here...");
    }
}
