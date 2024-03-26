package aiki.sml;

import aiki.game.Game;

public final class LoadedGameConf {
    private Game game;
    private LoadingGame loadingGame;

    public Game getGame() {
        return game;
    }

    public void setGame(Game _g) {
        this.game = _g;
    }

    public LoadingGame getLoadingGame() {
        return loadingGame;
    }

    public void setLoadingGame(LoadingGame _l) {
        this.loadingGame = _l;
    }
}
