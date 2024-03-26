package aiki.facade;

import aiki.game.Game;

public interface IntGamePkStream {
    Game load(String _fileName, SexListInt _sexList);
    Game loadThen(String _fileName, SexListInt _sexList);
    void save(String _fileName, Game _data);
}
