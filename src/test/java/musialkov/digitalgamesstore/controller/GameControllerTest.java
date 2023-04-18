package musialkov.digitalgamesstore.controller;

import musialkov.digitalgamesstore.entity.Game;
import musialkov.digitalgamesstore.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {
    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    public void getAllGames() throws Exception {
        List<Game> games = Arrays.asList(new Game(), new Game());
        when(gameService.getAllGames()).thenReturn(games);

        mockMvc.perform(MockMvcRequestBuilders.get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getGamesByCategory() throws Exception {
        String category = "rpg";
        List<Game> games = Arrays.asList(new Game(), new Game());
        when(gameService.getGamesByCategory(category)).thenReturn(games);

        mockMvc.perform(MockMvcRequestBuilders.get("/games/" + category))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}