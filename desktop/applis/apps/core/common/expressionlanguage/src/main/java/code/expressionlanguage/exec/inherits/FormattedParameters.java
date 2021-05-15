package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public final class FormattedParameters {
    private ExecFormattedRootBlock formattedClass;
    private Parameters parameters = new Parameters();

    public ExecFormattedRootBlock getFormattedClass() {
        return formattedClass;
    }

    public void setFormattedClass(ExecFormattedRootBlock _formattedClass) {
        formattedClass = _formattedClass;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters _parameters) {
        this.parameters = _parameters;
    }
}
