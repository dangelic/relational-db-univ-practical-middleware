package uni.dbprak21.shopmiddleware.system;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.InitFinish;
import uni.dbprak21.shopmiddleware.ShopMiddleware;


@Component
public class Finisher implements InitFinish {

    // Diese Logik wird beim Beenden der Applikation automatisch getriggert.
    @PreDestroy
    public void finish() {
        System.out.println();
        System.out.println("**********************************************************************");
        System.out.println("* >>> Shop Middleware Application will be terminated...              *");
        System.out.println("* >>> Pre-Destroy function triggered: finish() is called...          *");
        System.out.println("* >>> finish: Database resources are released.                       *");

        // Hier können weitere Aktionen durchgeführt werden, die vor dem Beenden der Middleware ausgeführt werden...
        // Diverse Persistenz-Aktionen werden via default von Spring Boot JPA / Hibernate ausgeführt.
    }
}