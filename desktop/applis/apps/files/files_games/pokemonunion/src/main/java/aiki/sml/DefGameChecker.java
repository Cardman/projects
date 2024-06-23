package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.SexListInt;
import aiki.game.Game;

public final class DefGameChecker implements IntGameChecker {
    @Override
    public Game checkGame(DataBase _data, SexListInt _sexList, Game _game) {
        return DefGamePkStream.checkGame(_data, _sexList, _game);
    }
}
