package musialkov.digitalgamesstore.repository;

import musialkov.digitalgamesstore.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ShopUser, Long> {

    Optional<ShopUser> findByEmail(String email);
}
