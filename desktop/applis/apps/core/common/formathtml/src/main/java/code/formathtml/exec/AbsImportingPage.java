package code.formathtml.exec;

import code.expressionlanguage.structs.Struct;

public abstract class AbsImportingPage {
    private Struct internGlobal;

    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
    }

    public Struct getInternGlobal() {
        return internGlobal;
    }

    public void setInternGlobal(Struct _internGlobal) {
        internGlobal = _internGlobal;
    }

}
