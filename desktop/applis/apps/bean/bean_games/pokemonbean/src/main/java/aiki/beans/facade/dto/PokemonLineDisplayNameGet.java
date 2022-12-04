package aiki.beans.facade.dto;

import aiki.beans.PkLineStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonLineDisplayNameGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((PkLineStruct) _instance).getWildPk()).getDisplayName());
    }
}
