package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class FightHelpBeanGetAnimAbsorb implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getAnimAbsorb());
    }
}
