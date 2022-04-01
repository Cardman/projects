package aiki.beans.endround;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundStatusBeanEndRoundStatusHtmlGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(EffectEndRoundStatusBean.END_ROUND_STATUS_HTML);
    }
}
