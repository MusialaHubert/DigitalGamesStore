package musialkov.digitalgamesstore.repository;

import musialkov.digitalgamesstore.entity.Category;
import musialkov.digitalgamesstore.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findGamesByCategory(Category category);
}
