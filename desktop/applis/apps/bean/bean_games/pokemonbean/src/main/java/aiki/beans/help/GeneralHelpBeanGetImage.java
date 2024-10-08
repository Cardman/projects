package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class GeneralHelpBeanGetImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getImage());
    }
}
