package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class GameProgressionBeanHeroImageOppositeSexGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getHeroImageOppositeSex());
    }
}
