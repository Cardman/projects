package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public final class RulesTarotBeanPoigneesAutorisees implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return TarotStandards.getStringIntegerTree(((RulesTarotBean) ((TarotBeanStruct)_instance).getInstance()).getPoigneesAutorisees());
    }
}
