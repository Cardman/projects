package code.formathtml.sample;

import code.bean.*;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.NatStringTreeMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SampleBeanStruct extends BeanStruct {

    public static final String NULLABLE_INT = "nullableInt";
    public static final String TYPED_STRING = "typedString";
    public static final String CHECKED = "checked";
    public static final String TYPED_SHORT = "typedShort";

    public SampleBeanStruct(Bean _bean) {
        super(_bean);
        setTypedShort((short) 0);
        getMap().put("ONE",1);
        getMap().put("TWO",2);
    }
    public void beforeDisplaying() {
        if (getBean().getBaseForms().getValStr(TYPED_STRING) == null) {
            getBean().getBaseForms().put(TYPED_STRING,"TYPED_STRING");
        } else {
            getBean().getBaseForms().put(TYPED_STRING, StringUtil.concatNbs(getBean().getBaseForms().getValStr(TYPED_STRING),2));
        }
    }

    public String go() {
        getForms().put(CHECKED, getBean().getBaseForms().getValBool(CHECKED));
        getForms().put(TYPED_STRING, getBean().getBaseForms().getValStr(TYPED_STRING));
        getForms().put(NULLABLE_INT, getBean().getBaseForms().getValRate(NULLABLE_INT));
        return "change";
    }
    public Bean getComposite() {
        return getOthers().lastValue();
    }

    public StringMap<Bean> getOthers() {
        return getBean().getBaseForms().getBeansOthers();
    }

    public NatStringTreeMap<Integer> getTree() {
        return getForms().getTree();
    }

    public StringMap<Integer> getMap() {
        return getForms().getMap();
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

    public StringMapObjectBase getForms() {
        return getBean().getBaseForms();
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
}
