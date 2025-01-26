package code.gui.document;

import aiki.beans.fight.*;
import aiki.game.fight.Fight;
import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayMoveTeamPositionFighterName implements BeanDisplay<MoveTeamPositionFighterName> {
    private final String foe;
    private final String player;

    public BeanDisplayMoveTeamPositionFighterName(String _f, String _p) {
        this.foe = _f;
        this.player = _p;
    }

    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, MoveTeamPositionFighterName _info, int _index, int _count) {
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),_info.getMoveTeamPosition().getMove());
        team(_rend,_api,_form,AbsBeanRender.remainder(_api, _index+1, _count),_info.getMoveTeamPosition().getTeamPosition().getTeam(),foe,player);
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index+2,_count),_info.getName());
        return 3;
    }
    private void team(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _line, AbsGridConstraints _cts, int _value, String _foe, String _player) {
        if (_value == Fight.CST_FOE) {
            _rend.formatMessage(_api, _line,_cts,MessagesFightFighter.FIGHTER, _foe);
        } else {
            _rend.formatMessage(_api, _line,_cts,MessagesFightFighter.FIGHTER, _player);
        }
    }
}
