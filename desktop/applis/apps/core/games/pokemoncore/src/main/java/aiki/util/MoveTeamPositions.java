package aiki.util;

import aiki.game.fight.MoveTeamPosition;
import code.util.*;

public class MoveTeamPositions<T> extends AbsBasicMap<MoveTeamPosition,T> {
    public MoveTeamPositions(){
    }
    protected MoveTeamPositions(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected boolean matchKeys(MoveTeamPosition _one, MoveTeamPosition _two) {
        return _one.eq(_two);
    }
}
