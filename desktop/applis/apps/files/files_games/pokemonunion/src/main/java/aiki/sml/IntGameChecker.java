package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.SexListInt;
import aiki.game.Game;

public interface IntGameChecker {
    Game checkGame(DataBase _data, SexListInt _sexList, Game _game);
}
