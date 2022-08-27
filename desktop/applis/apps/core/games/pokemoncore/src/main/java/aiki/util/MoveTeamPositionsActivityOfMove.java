package aiki.util;

import aiki.game.fight.ActivityOfMove;
import code.util.CollCapacity;

public final class MoveTeamPositionsActivityOfMove extends MoveTeamPositions<ActivityOfMove> {
    public MoveTeamPositionsActivityOfMove(){
    }
    public MoveTeamPositionsActivityOfMove(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected ActivityOfMove def() {
        return new ActivityOfMove("");
    }
}
