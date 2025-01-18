package aiki.util;

import code.util.CollCapacity;

public final class MoveTeamPositionsShort extends MoveTeamPositions<Integer> {
    public MoveTeamPositionsShort(){
    }
    public MoveTeamPositionsShort(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected Integer def() {
        return 0;
    }
}
