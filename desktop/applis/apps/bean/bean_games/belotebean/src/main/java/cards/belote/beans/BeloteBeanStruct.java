package cards.belote.beans;

import code.bean.nat.BeanStruct;

public final class BeloteBeanStruct extends BeanStruct {

    public BeloteBeanStruct(BeloteBean _bean) {
        super(_bean);
    }

    public BeloteBean getInstance() {
        return (BeloteBean)getBean();
    }


}
