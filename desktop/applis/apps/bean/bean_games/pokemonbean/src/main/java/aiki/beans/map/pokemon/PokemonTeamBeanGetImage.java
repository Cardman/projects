package aiki.beans.map.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
public class PokemonTeamBeanGetImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getImage(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
