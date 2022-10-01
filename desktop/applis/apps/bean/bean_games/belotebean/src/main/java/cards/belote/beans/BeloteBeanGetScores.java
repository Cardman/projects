package cards.belote.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class BeloteBeanGetScores implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getLongsArray(((BeloteBeanStruct)_instance).getInstance().scores());
    }
}
