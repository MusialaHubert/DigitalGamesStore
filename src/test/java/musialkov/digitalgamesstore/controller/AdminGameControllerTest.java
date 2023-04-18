package musialkov.digitalgamesstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import musialkov.digitalgamesstore.entity.Category;
import musialkov.digitalgamesstore.entity.Game;
import musialkov.digitalgamesstore.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.sql.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AdminGameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private AdminGameController adminGameController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminGameController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getGameById() throws Exception {
        Game game = new Game();
        game.setId(1L);

        when(gameService.getGameById(1L)).thenReturn(game);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/games/1"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/games/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createGame() throws Exception {
        Game testGame = new Game(1L, "Witcher2", "CDP", "CDP", "Nice", Category.RPG, new BigDecimal(20), new Date(System.currentTimeMillis()));
        when(gameService.createGame(testGame)).thenReturn(testGame);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testGame)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.title", equalTo("Witcher2")))
                .andExpect(jsonPath("$.description", equalTo("Nice")));
    }

    @Test
    void updateGame() throws Exception {
        Game testGame = new Game(1L, "Witcher2", "CDP", "CDP", "Nice", Category.RPG, new BigDecimal(20), new Date(System.currentTimeMillis()));
        Game testGame2 = new Game(1L, "GTA", "CDP", "CDP", "GOOD", Category.RPG, new BigDecimal(20), new Date(System.currentTimeMillis()));
        when(gameService.updateGame(1L, testGame)).thenReturn(testGame2);


        mockMvc.perform(MockMvcRequestBuilders.put("/admin/games/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testGame)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.title", equalTo("GTA")))
                .andExpect(jsonPath("$.description", equalTo("GOOD")));
    }

    @Test
    void deleteGame() throws Exception {
        when(gameService.deleteGame(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/games/1"))
                .andExpect(status().isNoContent());
    }
}