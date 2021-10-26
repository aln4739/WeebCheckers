package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.app.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.HashMap;
import java.util.logging.Logger;

public class PostBackupMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private final HashMap<String, Game> gameMap;
    private final Gson gson;

    public PostBackupMoveRoute(HashMap<String, Game> gameMap, Gson gson) {
        this.gameMap = gameMap;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostBackupMoveRoute has been invoked.");

        final Session httpSession = request.session();
        Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        String gameID = request.queryParams(GetGameRoute.GAME_ID_PARAM);
        Game game = gameMap.get(gameID);

        Message message;
        if ((game.isRedPlayer(player) && game.isRedPlayerTurn()) ||
            (!game.isRedPlayer(player) && !game.isRedPlayerTurn())) {
            if (game.backupMove()) {
                message = Message.info("Move backed up.");
            } else {
                message = Message.error("No move to back up.");
            }
        } else {
            message = Message.error("Not your turn!");
        }

        return gson.toJson(message);
    }
}
