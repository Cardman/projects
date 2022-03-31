package code.bean.nat;

import code.bean.Bean;
import code.maths.Rate;
import code.util.NatStringTreeMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class BeanStruct extends CommNatStruct {

    public static final String NULLABLE_INT = "nullableInt";
    public static final String TYPED_STRING = "typedString";
    public static final String CHECKED = "checked";
    public static final String TYPED_SHORT = "typedShort";
    private final Bean bean;

    public BeanStruct(Bean _bean) {
        super(_bean.getClassName());
        bean = _bean;
        setTypedShort((short) 0);
        getMap().put("ONE",1);
        getMap().put("TWO",2);
    }
    public void beforeDisplaying() {
        if (bean.getBaseForms().getValStr(TYPED_STRING) == null) {
            bean.getBaseForms().put(TYPED_STRING,"TYPED_STRING");
        } else {
            bean.getBaseForms().put(TYPED_STRING,StringUtil.concatNbs(bean.getBaseForms().getValStr(TYPED_STRING),2));
        }
    }
    public Bean getBean() {
        return bean;
    }

    public String go() {
        getForms().put(CHECKED, bean.getBaseForms().getValBool(CHECKED));
        getForms().put(TYPED_STRING, bean.getBaseForms().getValStr(TYPED_STRING));
        getForms().put(NULLABLE_INT, bean.getBaseForms().getValRate(NULLABLE_INT));
        return "change";
    }
    public Bean getComposite() {
        return getOthers().lastValue();
    }

    public StringMap<Bean> getOthers() {
        return bean.getBaseForms().getBeansOthers();
    }

    public NatStringTreeMap<Integer> getTree() {
        return getForms().getTree();
    }

    public StringMap<Integer> getMap() {
        return getForms().getMap();
    }

    public Rate getNullableInt() {
        return bean.getBaseForms().getValRate(NULLABLE_INT);
    }

    public void setNullableInt(Rate _v) {
        bean.getBaseForms().put(NULLABLE_INT,_v);
    }

    public String getTypedString() {
        return bean.getBaseForms().getValStr(TYPED_STRING);
    }

    public boolean isChecked() {
        return bean.getBaseForms().getValBool(CHECKED);
    }

    public void setChecked(boolean _v) {
        bean.getBaseForms().put(CHECKED,_v);
    }

    public void setTypedString(String _v) {
        bean.getBaseForms().put(TYPED_STRING,_v);
    }

    public StringMapObjectBase getForms() {
        return bean.getBaseForms();
    }

    public void setForms(StringMapObjectBase _v) {
        this.bean.setBaseForms(_v);
    }

    public int getTypedShort() {
        return bean.getBaseForms().getValInt(TYPED_SHORT);
    }

    public void setTypedShort(short _v) {
        bean.getBaseForms().put(TYPED_SHORT,_v);
    }
}
