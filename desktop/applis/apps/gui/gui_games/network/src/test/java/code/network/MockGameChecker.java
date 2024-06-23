package code.network;

import aiki.db.*;
import aiki.facade.*;
import aiki.game.*;
import aiki.sml.*;

public final class MockGameChecker implements IntGameChecker {
    @Override
    public Game checkGame(DataBase _data, SexListInt _sexList, Game _game) {
        return _game;
    }
}
