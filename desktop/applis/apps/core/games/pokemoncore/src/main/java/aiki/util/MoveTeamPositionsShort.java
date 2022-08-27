package aiki.util;

import code.util.CollCapacity;

public final class MoveTeamPositionsShort extends MoveTeamPositions<Short> {
    public MoveTeamPositionsShort(){
    }
    public MoveTeamPositionsShort(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected Short def() {
        return 0;
    }
}
