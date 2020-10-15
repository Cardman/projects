package code.expressionlanguage.exec.inherits;

public final class FormattedParameters {
    private String formattedClass = "";
    private Parameters parameters = new Parameters();

    public String getFormattedClass() {
        return formattedClass;
    }

    public void setFormattedClass(String _formattedClass) {
        formattedClass = _formattedClass;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters _parameters) {
        this.parameters = _parameters;
    }
}
