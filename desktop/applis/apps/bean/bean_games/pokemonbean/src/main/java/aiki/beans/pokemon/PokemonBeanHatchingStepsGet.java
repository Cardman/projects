package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.LgIntStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class PokemonBeanHatchingStepsGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new LgIntStruct(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHatchingSteps(),BeanNatCommonLgNames.TYPE_LG_INT);
    }
}
