package aiki.util;

import aiki.game.fight.util.AffectedMove;
import code.util.CollCapacity;

public final class MoveTeamPositionsAffectedMove extends MoveTeamPositions<AffectedMove> {
    public MoveTeamPositionsAffectedMove(){
    }
    public MoveTeamPositionsAffectedMove(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected AffectedMove def() {
        return new AffectedMove("");
    }
}
