package code.formathtml.exec;

import code.expressionlanguage.exec.calls.IntAbstractPageEl;
import code.expressionlanguage.structs.Struct;

public abstract class AbsImportingPage implements IntAbstractPageEl {
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
