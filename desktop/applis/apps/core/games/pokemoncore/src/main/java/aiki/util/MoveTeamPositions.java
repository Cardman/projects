package aiki.util;

import aiki.game.fight.MoveTeamPosition;
import code.util.CollCapacity;

public abstract class MoveTeamPositions<T> extends CommonMap<MoveTeamPosition,T> {
    protected MoveTeamPositions(){
    }
    protected MoveTeamPositions(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected boolean eq(MoveTeamPosition _one, MoveTeamPosition _two) {
        return _one.eq(_two);
    }
}
