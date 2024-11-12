package aiki.util;

import aiki.game.fight.MoveTeamPosition;
import code.util.*;

public abstract class MoveTeamPositions<T> extends AbsBasicMap<MoveTeamPosition,T> {
    protected MoveTeamPositions(){
    }
    protected MoveTeamPositions(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected boolean matchKeys(MoveTeamPosition _one, MoveTeamPosition _two) {
        return _one.eq(_two);
    }
}
