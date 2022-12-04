package aiki.beans;

import aiki.map.pokemon.PkTrainer;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class PkTrainerGetMoves implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (PkTrainer) ((PkStruct)_instance).getWildPk()).getMoves());
    }
}
