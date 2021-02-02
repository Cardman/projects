package code.formathtml.nat;

import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class BeanStruct extends WithoutParentIdStruct {

    private final Bean bean;

    private StringMapObject forms = new StringMapObject();

    public BeanStruct(Bean _bean) {
        bean = _bean;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return bean.getClassName();
    }

    public StringMapObject getForms() {
        return forms;
    }

    public void setForms(StringMapObject _forms) {
        forms = _forms;
    }

    public Bean getInstance() {
        return getBean();
    }

    public Bean getBean() {
        return bean;
    }
}
