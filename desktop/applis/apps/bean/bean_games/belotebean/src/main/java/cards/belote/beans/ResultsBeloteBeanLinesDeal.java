package cards.belote.beans;

import cards.consts.beans.LineDealStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsBeloteBeanLinesDeal implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return LineDealStruct.getLineDealArray(((ResultsBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getLinesDeal());
    }
}
