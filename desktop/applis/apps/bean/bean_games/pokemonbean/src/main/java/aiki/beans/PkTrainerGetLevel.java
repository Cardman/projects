package aiki.beans;

import aiki.map.pokemon.PkTrainer;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class PkTrainerGetLevel implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (PkTrainer) ((PkStruct)_instance).getWildPk()).getLevel());
    }
}
