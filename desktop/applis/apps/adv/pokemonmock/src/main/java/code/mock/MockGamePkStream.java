package code.mock;

import aiki.facade.IntGamePkStream;
import aiki.facade.SexListInt;
import aiki.game.Game;

public final class MockGamePkStream implements IntGamePkStream {
    private Game game;

    @Override
    public Game load(String _fileName, SexListInt _sexList) {
        return game;
    }

    @Override
    public Game loadThen(String _fileName, SexListInt _sexList) {
        return game;
    }

    @Override
    public void save(String _fileName, Game _data) {
        game = _data;
    }
}
