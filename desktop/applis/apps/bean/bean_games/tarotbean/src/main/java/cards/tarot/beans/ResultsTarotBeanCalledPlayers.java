package cards.tarot.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class ResultsTarotBeanCalledPlayers implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getCalledPlayers());
    }
}
