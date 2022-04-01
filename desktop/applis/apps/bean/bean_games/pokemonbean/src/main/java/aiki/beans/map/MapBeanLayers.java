package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
public class MapBeanLayers implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getLayers(( (MapBean) ((PokemonBeanStruct)_instance).getInstance()).layers(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
