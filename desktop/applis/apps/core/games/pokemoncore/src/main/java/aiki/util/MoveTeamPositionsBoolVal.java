package aiki.util;

import code.util.CollCapacity;
import code.util.core.BoolVal;

public final class MoveTeamPositionsBoolVal extends MoveTeamPositions<BoolVal> {
    public MoveTeamPositionsBoolVal(){
    }
    public MoveTeamPositionsBoolVal(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected BoolVal def() {
        return BoolVal.FALSE;
    }
}
