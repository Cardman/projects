package cards.belote.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class DetailsResultsBeloteBeanDeclaring implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeloteStandards.getSumDeclaringPlayerArray(((DetailsResultsBeloteBean)((BeloteBeanStruct)_instance).getInstance()).getDeclaring());
    }
}
