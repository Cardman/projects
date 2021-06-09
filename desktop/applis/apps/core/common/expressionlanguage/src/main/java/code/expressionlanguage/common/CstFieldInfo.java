package code.expressionlanguage.common;

public final class CstFieldInfo {

    private final String name;

    private final String importedClassName;

    public CstFieldInfo(String _fieldName, String _className) {
        name = _fieldName;
        importedClassName = _className;
    }
    public String getFieldName() {
        return name;
    }

    public String getImportedClassName() {
        return importedClassName;
    }
}
