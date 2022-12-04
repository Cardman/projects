package aiki.beans;

import aiki.map.pokemon.PkTrainer;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PkTrainerGetItem implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PkTrainer) ((PkStruct)_instance).getWildPk()).getItem());
    }
}
