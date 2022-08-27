package aiki.util;

import code.util.CollCapacity;
import code.util.StringList;

public final class MoveTeamPositionsStringList extends MoveTeamPositions<StringList> {
    public MoveTeamPositionsStringList(){
    }
    public MoveTeamPositionsStringList(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected StringList def() {
        return new StringList();
    }
}
