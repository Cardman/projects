package cards.president.beans;

import cards.consts.beans.LineDealStruct;
import code.bean.nat.*;
import code.bean.nat.*;

public class PresidentBeanLinesDeal implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return LineDealStruct.getLineDealArray(((PresidentBean)((PresidentBeanStruct)_instance).getInstance()).getLinesDeal());
    }
}
