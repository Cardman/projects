package aiki.beans.map.characters;

import aiki.beans.AllyStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class DualFightBeanAllyGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new AllyStruct(( (DualFightBean) ((PokemonBeanStruct)_instance).getInstance()).getAlly());
    }
}
