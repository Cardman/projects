package cards.president.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;

public class PresidentBeanNicknames implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return BeanNatCommonLgNames.getStringArray(((PresidentBean)((PresidentBeanStruct)_instance).getInstance()).getNicknames());
    }
}
