package aiki.beans;

import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectEndRound;
import code.util.NatStringTreeMap;
import code.util.StringList;

public final class EndRoundCommon {
    private boolean endRound;
    private long endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;
    public void endRondElts(DataBase _data,EffectEndRound _eff, String _lg) {
        if (_eff != null) {
            endRound = true;
            endRoundRank = _eff.getEndRoundRank();
            reasonsEndRound =  CommonBean.getFormattedReasons(_data, CommonBean.getReasons(_eff.getFailEndRound()), _lg);
            mapVarsFailEndRound =  CommonBean.getMapVarsFail(_data, _eff.getFailEndRound(), _lg);
        } else {
            endRound = false;
            endRoundRank = 0;
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatStringTreeMap<String>();
        }
    }

    public boolean getEndRound() {
        return endRound;
    }

    public long getEndRoundRank() {
        return endRoundRank;
    }

    public StringList getReasonsEndRound() {
        return reasonsEndRound;
    }

    public NatStringTreeMap<String> getMapVarsFailEndRound() {
        return mapVarsFailEndRound;
    }

}
