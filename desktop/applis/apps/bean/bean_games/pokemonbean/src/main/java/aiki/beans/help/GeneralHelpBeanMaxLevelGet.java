package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class GeneralHelpBeanMaxLevelGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getMaxLevel());
    }
}
