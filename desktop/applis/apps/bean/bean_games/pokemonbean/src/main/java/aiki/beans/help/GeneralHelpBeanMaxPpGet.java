package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
public class GeneralHelpBeanMaxPpGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new LongStruct(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getMaxPp());
    }
}
