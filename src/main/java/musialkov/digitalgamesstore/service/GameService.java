package musialkov.digitalgamesstore.service;

import musialkov.digitalgamesstore.entity.Game;

import java.util.List;

public interface GameService {
    Game getGameById(Long id);

    List<Game> getAllGames();

    List<Game> getGamesByCategory(String category);

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    boolean deleteGame(Long id);
}
