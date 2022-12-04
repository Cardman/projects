package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanCopiedMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getCpStr(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getCopiedMoves());
    }
}
