package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsBeloteBeanFailedBid implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(((ResultsBeloteBean) ((BeloteBeanStruct) _instance).getInstance()).getTakerResult().failedBid());
    }
}
