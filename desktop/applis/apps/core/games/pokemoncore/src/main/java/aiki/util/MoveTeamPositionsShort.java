package aiki.util;

import code.util.CollCapacity;

public final class MoveTeamPositionsShort extends MoveTeamPositions<Long> {
    public MoveTeamPositionsShort(){
    }
    public MoveTeamPositionsShort(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected Long def() {
        return 0L;
    }
}
