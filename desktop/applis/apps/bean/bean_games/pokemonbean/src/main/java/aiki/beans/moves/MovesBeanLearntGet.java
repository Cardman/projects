package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.WithFilterBean;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
public class MovesBeanLearntGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanLgNames.wrapStd(( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).getLearnt());
    }
}
