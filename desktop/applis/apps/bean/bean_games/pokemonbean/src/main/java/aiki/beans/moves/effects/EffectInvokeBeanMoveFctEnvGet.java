package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectInvokeBeanMoveFctEnvGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStrKey(( (EffectInvokeBean) ((PokemonBeanStruct)_instance).getInstance()).getMoveFctEnv());
    }
}
