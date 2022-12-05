package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AnticipationGetNbRounds implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((AnticipationStruct) _instance).getAnticipation()).getNbRounds());
    }
}
