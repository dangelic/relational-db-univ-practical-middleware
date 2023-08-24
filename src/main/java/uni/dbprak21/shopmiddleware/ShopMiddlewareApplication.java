package uni.dbprak21.shopmiddleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import uni.dbprak21.shopmiddleware.system.Finisher;

@SpringBootApplication
@ComponentScan(basePackages = "uni.dbprak21.shopmiddleware")
public class ShopMiddlewareApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopMiddlewareApplication.class, args);
	}
}