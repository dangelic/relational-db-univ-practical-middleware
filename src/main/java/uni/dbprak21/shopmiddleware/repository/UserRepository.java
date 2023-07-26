package uni.dbprak21.shopmiddleware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.dbprak21.shopmiddleware.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> { };