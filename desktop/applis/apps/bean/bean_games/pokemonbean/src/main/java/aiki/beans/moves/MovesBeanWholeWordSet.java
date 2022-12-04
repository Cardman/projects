package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MovesBeanWholeWordSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (MovesBean) ((PokemonBeanStruct)_instance).getInstance()).setWholeWord(NaBoSt.isTrue(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
