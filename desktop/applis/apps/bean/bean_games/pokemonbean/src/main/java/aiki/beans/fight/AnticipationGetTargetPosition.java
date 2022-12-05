package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
public class AnticipationGetTargetPosition implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new TargetCoordsStruct(( ((AnticipationStruct) _instance).getAnticipation()).getTargetPosition());
    }
}
