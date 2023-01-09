package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class EffectEndRoundTeamBeanDeleteAllStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (EffectEndRoundTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getDeleteAllStatus());
    }
}
