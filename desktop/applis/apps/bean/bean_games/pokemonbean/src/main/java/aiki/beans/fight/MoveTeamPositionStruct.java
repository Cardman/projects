package aiki.beans.fight;

import aiki.game.fight.MoveTeamPosition;
import code.bean.nat.NaNuSt;

public final class MoveTeamPositionStruct extends NaNuSt {
    private final MoveTeamPosition inst;
    public MoveTeamPositionStruct(MoveTeamPosition _instance) {
        inst=(_instance);
    }
    public MoveTeamPosition getMoveTeamPosition() {
        return inst;
    }
}
