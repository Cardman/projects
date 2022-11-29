package aiki.beans;

import code.bean.Bean;
import code.bean.nat.BeanStruct;
import code.bean.nat.StringMapObjectBase;

public final class PokemonBeanStruct extends BeanStruct {

    public PokemonBeanStruct(CommonBean _bean) {
        super(_bean);
    }

    public Bean getInstance() {
        return getBean();
    }

    public StringMapObjectBase getForms() {
        return ((CommonBean)getBean()).getBaseForms();
    }


}
