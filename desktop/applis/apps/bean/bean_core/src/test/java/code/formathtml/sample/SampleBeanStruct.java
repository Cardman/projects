package code.formathtml.sample;

import code.bean.Bean;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.BeanStruct;
import code.bean.nat.StringMapObjectBase;
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;
import code.util.StringMap;

public final class SampleBeanStruct extends BeanStruct {

    public static final String NULLABLE_INT = "nullableInt";
    public static final String TYPED_STRING = "typedString";
    public static final String CHECKED = "checked";
    public static final String TYPED_SHORT = "typedShort";
    public static final String TYPED_INT2 = "typedInt2";

    public SampleBeanStruct(Bean _bean) {
        super(_bean);
        getMap().put("ONE",1);
        getMap().put("TWO",2);
        getOthers().addEntry("",new BasicBeanStruct(new BeanThree()));
    }

    static void spec(Bean _bean) {
        String retr_ = _bean.getBaseForms().getValStr(TYPED_STRING);
        String res_ = BeanNatCommonLgNames.safe(retr_, "TYPED_STRING", 2);
        _bean.getBaseForms().put(TYPED_STRING,res_);
    }

    public String go() {
        getForms().put(CHECKED, getBean().getBaseForms().getValBool(CHECKED));
        getForms().put(TYPED_STRING, getBean().getBaseForms().getValStr(TYPED_STRING));
        getForms().put(NULLABLE_INT, getBean().getBaseForms().getValRate(NULLABLE_INT));
        return "change";
    }
    public Bean getComposite() {
        return ((BeanStruct)getOthers().lastValue()).getBean();
    }

    public StringMap<Struct> getOthers() {
        return getBean().getBaseForms().getBeansOthers();
    }

    public StringMap<Integer> getMap() {
        return getForms().getMapInt();
    }

    public Rate getNullableInt() {
        return getBean().getBaseForms().getValRate(NULLABLE_INT);
    }

    public void setNullableInt(Rate _v) {
        getBean().getBaseForms().put(NULLABLE_INT,_v);
    }

    public String getTypedString() {
        return getBean().getBaseForms().getValStr(TYPED_STRING);
    }

    public boolean isChecked() {
        return getBean().getBaseForms().getValBool(CHECKED);
    }

    public void setChecked(boolean _v) {
        getBean().getBaseForms().put(CHECKED,_v);
    }

    public void setTypedString(String _v) {
        getBean().getBaseForms().put(TYPED_STRING,_v);
    }


    public void setForms(StringMapObjectBase _v) {
        this.getBean().setBaseForms(_v);
    }

    public int getTypedShort() {
        return getBean().getBaseForms().getValInt(TYPED_SHORT);
    }

    public void setTypedShort(short _v) {
        getBean().getBaseForms().put(TYPED_SHORT,_v);
    }

    public int getTypedInt() {
        return getBean().getBaseForms().getValInt(TYPED_INT2);
    }

    public void setTypedInt(short _v) {
        getBean().getBaseForms().put(TYPED_INT2,_v);
    }
}
