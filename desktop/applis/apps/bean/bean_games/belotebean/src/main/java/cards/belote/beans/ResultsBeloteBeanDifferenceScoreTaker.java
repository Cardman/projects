package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsBeloteBeanDifferenceScoreTaker implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(((ResultsBeloteBean) ((BeloteBeanStruct) _instance).getInstance()).getTakerResult().getDifferenceScoreTaker());
    }
}
