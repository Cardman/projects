package cards.belote.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public final class RulesBeloteBeanRepartition implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(((RulesBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getRepartition());
    }
}
