package aiki.beans.facade.fight;

import aiki.beans.fight.KeyHypothesisStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class KeyHypothesisGetPlayerPokemon implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((KeyHypothesisStruct) _instance).getKeyHypothesis()).getPlayerPokemon());
    }
}
