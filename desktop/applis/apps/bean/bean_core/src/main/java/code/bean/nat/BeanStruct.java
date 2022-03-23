package code.bean.nat;

import code.bean.Bean;
import code.maths.Rate;
import code.util.NatStringTreeMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class BeanStruct extends CommNatStruct {

    private final Bean bean;

    private boolean checked;

    private final CompositeStruct composite = new CompositeStruct("");

    private final NatStringTreeMap< Integer> tree = new NatStringTreeMap< Integer>();

    private final StringMap<Integer> map = new StringMap<Integer>();

    private Rate nullableInt;

    private String typedString;

    private short typedShort;
    private StringMapObjectSample forms = new StringMapObjectSample();
    public BeanStruct(Bean _bean) {
        super(_bean.getClassName());
        getMap().put("ONE",1);
        getMap().put("TWO",2);
        bean = _bean;
    }
    public void beforeDisplaying() {
        if (typedString == null) {
            typedString = "TYPED_STRING";
        } else {
            typedString = StringUtil.concatNbs(typedString,2);
        }
    }

    public Bean getBean() {
        return bean;
    }

    public String go() {
        getForms().put("checked", checked);
        getForms().put("typedString", typedString);
        getForms().put("nullableInt", nullableInt);
        return "change";
    }
    public CompositeStruct getComposite() {
        return composite;
    }

    public NatStringTreeMap<Integer> getTree() {
        return tree;
    }

    public StringMap<Integer> getMap() {
        return map;
    }

    public Rate getNullableInt() {
        return nullableInt;
    }

    public void setNullableInt(Rate _v) {
        this.nullableInt = _v;
    }

    public String getTypedString() {
        return typedString;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean _v) {
        this.checked = _v;
    }

    public void setTypedString(String _v) {
        this.typedString = _v;
    }

    public StringMapObjectSample getForms() {
        return forms;
    }

    public void setForms(StringMapObjectSample _v) {
        this.forms = _v;
    }

    public short getTypedShort() {
        return typedShort;
    }

    public void setTypedShort(short _v) {
        typedShort = _v;
    }
}
