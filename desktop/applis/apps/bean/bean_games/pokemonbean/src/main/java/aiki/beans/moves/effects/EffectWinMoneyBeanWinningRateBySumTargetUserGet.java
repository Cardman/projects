package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class EffectWinMoneyBeanWinningRateBySumTargetUserGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (EffectWinMoneyBean) ((PokemonBeanStruct)_instance).getInstance()).getWinningRateBySumTargetUser());
    }
}
