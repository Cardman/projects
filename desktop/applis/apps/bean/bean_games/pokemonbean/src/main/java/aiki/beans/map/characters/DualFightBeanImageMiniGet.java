package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class DualFightBeanImageMiniGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (DualFightBean) ((PokemonBeanStruct)_instance).getInstance()).getImageMini());
    }
}
