package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class GameProgressionBeanBeatenImportantTrainersGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return AikiBeansGameStd.getTrPlNa(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getBeatenImportantTrainers());
    }
}
