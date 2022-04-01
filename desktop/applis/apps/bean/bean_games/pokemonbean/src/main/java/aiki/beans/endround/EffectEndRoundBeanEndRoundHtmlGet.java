package aiki.beans.endround;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundBeanEndRoundHtmlGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(EffectEndRoundBean.END_ROUND_HTML);
    }
}
