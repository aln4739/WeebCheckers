package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.app.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests {@link PostSubmitTurnRoute}
 * @author Anh Nguyen
 */
@Tag("UI-tier")
@Testable
public class TestPostSubmitTurnRoute {
    private PostSubmitTurnRoute CuT;

    private Game game;
    private HashMap<String, Game> gameMap;
    private Gson gson;

    private Request request;
    private Response response;
    private Session session;

    private Player p1;
    private Player p2;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);

        p1 = new Player("player1");
        p2 = new Player("player2");
        game = new Game(p1, p2);
        gameMap = new HashMap<>();
        gameMap.put(String.valueOf(game.getID()), game);

        gson = new Gson();

        // create a unique CuT for each test
        CuT = new PostSubmitTurnRoute(gameMap, gson);
    }

    /**
     * Tests {@link PostSubmitTurnRoute#handle(Request, Response)}
     */
    @Test
    public void testHandle() {
        when(request.queryParams(GetGameRoute.GAME_ID_PARAM)).thenReturn(String.valueOf(game.getID()));
        // fake move
        when(request.queryParams("actionData")).thenReturn
                ("{\"start\":{\"row\":5,\"cell\":2},\"end\":{\"row\":4,\"cell\":3}}");

        Object actual = CuT.handle(request, response);

        // fixed expected
        Object expected = gson.toJson(Message.error("Possible jump move detected. You must play all jump moves."));
        assertEquals(expected, actual);

        // to switch turns back to player1 (redPlayer)
        CuT.handle(request, response);

        PostValidateMoveRoute movesMaker = new PostValidateMoveRoute(gameMap, gson);
        movesMaker.handle(request, response);

        actual = CuT.handle(request, response);
        expected = gson.toJson(Message.info("Turn submitted."));

        assertEquals(expected, actual);
    }
}
