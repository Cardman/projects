package aiki.db;

import aiki.map.pokemon.PkTrainer;
import code.util.CustList;

public final class ChangeStringFieldLevelMoves extends ChangeStringFieldLevel{
    @Override
    protected void changeValue(CustList<PkTrainer> _team, String _oldName, String _newName) {
        DataBase.changeMoves(_oldName,_newName,_team);
    }

    @Override
    protected CustList<ChangeStringFieldMatch> changeValue(CustList<PkTrainer> _team) {
        return DataBase.changeMoves(_team);
    }
}
