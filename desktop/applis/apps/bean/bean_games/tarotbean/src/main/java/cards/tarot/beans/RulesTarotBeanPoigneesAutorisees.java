package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class RulesTarotBeanPoigneesAutorisees implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return TarotStandards.getStringIntegerTree(((RulesTarotBean) ((TarotBeanStruct)_instance).getInstance()).getPoigneesAutorisees());
    }
}
