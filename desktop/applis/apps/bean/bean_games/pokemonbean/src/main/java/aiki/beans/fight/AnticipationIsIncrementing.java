package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AnticipationIsIncrementing implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( ((AnticipationStruct) _instance).getAnticipation()).isIncrementing());
    }
}
