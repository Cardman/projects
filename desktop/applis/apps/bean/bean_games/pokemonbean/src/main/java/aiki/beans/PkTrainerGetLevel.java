package aiki.beans;

import aiki.map.pokemon.PkTrainer;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PkTrainerGetLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (PkTrainer) ((PkStruct)_instance).getWildPk()).getLevel());
    }
}
