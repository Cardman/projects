package aiki.beans;

import aiki.map.pokemon.PkTrainer;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class PkTrainerGetMoves implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (PkTrainer) ((PkStruct)_instance).getWildPk()).getMoves());
    }
}
