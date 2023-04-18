package musialkov.digitalgamesstore.controller;

import lombok.RequiredArgsConstructor;
import musialkov.digitalgamesstore.entity.Game;
import musialkov.digitalgamesstore.service.GameService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{category}")
    public List<Game> getGamesByCategory(@PathVariable String category) {
        return gameService.getGamesByCategory(category);
    }
}
