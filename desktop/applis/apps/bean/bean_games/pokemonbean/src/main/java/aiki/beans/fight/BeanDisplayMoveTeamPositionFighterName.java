package aiki.beans.fight;

import aiki.beans.*;
import aiki.game.fight.*;

public final class BeanDisplayMoveTeamPositionFighterName implements BeanDisplay<MoveTeamPositionFighterName> {
    private final String foe;
    private final String player;

    public BeanDisplayMoveTeamPositionFighterName(String _f, String _p) {
        this.foe = _f;
        this.player = _p;
    }

    @Override
    public int display(CommonBean _rend, MoveTeamPositionFighterName _info, int _index) {
        _rend.formatMessageDirCts(_info.getMoveTeamPosition().getMove());
        team(_rend, _info.getMoveTeamPosition().getTeamPosition().getTeam(),foe,player);
        _rend.formatMessageDirCts(_info.getName());
        return 3;
    }
    private void team(CommonBean _rend, int _value, String _foe, String _player) {
        if (_value == Fight.CST_FOE) {
            _rend.formatMessageDirCts(_foe);
        } else {
            _rend.formatMessageDirCts(_player);
        }
    }
}
