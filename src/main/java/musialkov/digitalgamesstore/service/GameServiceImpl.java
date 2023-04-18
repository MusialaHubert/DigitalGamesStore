package musialkov.digitalgamesstore.service;

import musialkov.digitalgamesstore.entity.Category;
import musialkov.digitalgamesstore.entity.Game;
import musialkov.digitalgamesstore.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public List<Game> getGamesByCategory(String category) {
        return gameRepository.findGamesByCategory(getCategory(category));
    }

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if(optionalGame.isPresent()) {
            Game existingGame = optionalGame.get();
            existingGame.setTitle(game.getTitle());
            existingGame.setDeveloper(game.getDeveloper());
            existingGame.setPublisher(game.getPublisher());
            existingGame.setDescription(game.getDescription());
            existingGame.setCategory(game.getCategory());
            existingGame.setPrice(game.getPrice());
            return gameRepository.save(existingGame);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean deleteGame(Long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            gameRepository.delete(optionalGame.get());
            return true;
        } else {
            return false;
        }
    }

    private Category getCategory(String parameterCategory) {
        for (Category gameCategory : Category.values()) {
            if (gameCategory.name().equalsIgnoreCase(parameterCategory)) {
                return gameCategory;
            }
        }
        throw new IllegalArgumentException("Invalid category: " + parameterCategory);
    }
}
