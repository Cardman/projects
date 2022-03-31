package cards.tarot.beans;

import code.bean.nat.BeanStruct;

public final class TarotBeanStruct extends BeanStruct {

    public TarotBeanStruct(TarotBean _bean) {
        super(_bean);
    }

    public TarotBean getInstance() {
        return (TarotBean)getBean();
    }


}