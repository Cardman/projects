package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class GeneralHelpBeanPokemonDefaultEggGroupGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getPokemonDefaultEggGroup());
    }
}
