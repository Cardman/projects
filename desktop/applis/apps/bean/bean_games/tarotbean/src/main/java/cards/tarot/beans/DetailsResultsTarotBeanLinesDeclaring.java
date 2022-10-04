package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public final class DetailsResultsTarotBeanLinesDeclaring implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return TarotStandards.getSumDeclaringPlayerArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getLinesDeclaring());
    }
}
