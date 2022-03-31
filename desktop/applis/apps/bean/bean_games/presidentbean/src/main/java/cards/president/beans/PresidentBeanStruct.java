package cards.president.beans;

import code.bean.Bean;
import code.bean.nat.BeanStruct;

public final class PresidentBeanStruct extends BeanStruct {

    public PresidentBeanStruct(Bean _bean) {
        super(_bean);
    }

    public Bean getInstance() {
        return getBean();
    }


}
