package aiki.util;

import code.util.*;

public final class MoveTeamPositionsStringList extends MoveTeamPositions<CustList<String>> {
    public MoveTeamPositionsStringList(){
    }
    public MoveTeamPositionsStringList(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected CustList<String> def() {
        return new StringList();
    }
}
