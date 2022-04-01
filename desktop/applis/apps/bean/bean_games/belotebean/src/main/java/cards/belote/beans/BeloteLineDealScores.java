package cards.belote.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class BeloteLineDealScores implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getLongArray((((BeloteLineDealStruct)_instance).getLineDeal()).getScores());
    }
}
