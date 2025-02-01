package code.gui.document;

import aiki.beans.fight.*;
import aiki.game.fight.Fight;

public final class BeanDisplayMoveTeamPositionFighterName implements BeanDisplay<MoveTeamPositionFighterName> {
    private final String foe;
    private final String player;

    public BeanDisplayMoveTeamPositionFighterName(String _f, String _p) {
        this.foe = _f;
        this.player = _p;
    }

    @Override
    public int display(AbsBeanRender _rend, MoveTeamPositionFighterName _info, int _index) {
        _rend.formatMessageDirCts(_info.getMoveTeamPosition().getMove());
        team(_rend, _info.getMoveTeamPosition().getTeamPosition().getTeam(),foe,player);
        _rend.formatMessageDirCts(_info.getName());
        return 3;
    }
    private void team(AbsBeanRender _rend, int _value, String _foe, String _player) {
        if (_value == Fight.CST_FOE) {
            _rend.formatMessageCts(MessagesPkBean.FIGHTER, _foe);
        } else {
            _rend.formatMessageCts(MessagesPkBean.FIGHTER, _player);
        }
    }
}
