package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsBeloteBeanBidString implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(((ResultsBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getBidString());
    }
}
