package aiki.beans.game;

import aiki.beans.CommonBean;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class DifficultyBeanChange implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (DifficultyBean) ((PokemonBeanStruct)_instance).getInstance()).change();
        return new StringStruct(CommonBean.DEST_WEB_GAME_HTML_DIFFICULTY_HTML);
    }
}
