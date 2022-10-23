package code.formathtml.sample;

import code.bean.Bean;
import code.bean.nat.StringMapObjectBase;
import code.util.StringList;


public class BeanThree extends Bean {

    public BeanThree() {
        setBaseForms(new StringMapObjectBase());
        getBaseForms().put("integer",0);
        getBaseForms().put("strings",new StringList());
    }
    @Override
    public void beforeDisplaying() {
        SampleBeanStruct.spec(this);
    }

    public int getInteger() {
        return getBaseForms().getValInt("integer");
    }

    public void setInteger(int _v) {
        getBaseForms().put("integer",_v);
    }

    public StringList getStrings() {
        return getBaseForms().getValList("strings");
    }
}
