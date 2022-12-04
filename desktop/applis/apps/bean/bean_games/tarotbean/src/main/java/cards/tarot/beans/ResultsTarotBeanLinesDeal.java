package cards.tarot.beans;

import cards.consts.beans.LineDealStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsTarotBeanLinesDeal implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return LineDealStruct.getLineDealArray(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getLinesDeal());
    }
}
