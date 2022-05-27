package code.expressionlanguage.exec.types;

abstract class ExecLeafPartType extends ExecPartType {

    private final String typeName;
    private String importedTypeName = EMPTY_STRING;
    private final String previousSeparator;
    ExecLeafPartType(ExecParentPartType _parent, int _index, String _type, String _previousSeparator, String _previousOperator) {
        super(_parent, _index, _previousOperator);
        typeName = _type;
        previousSeparator = _previousSeparator;
    }
    final String exportHeader() {
        return importedTypeName;
    }
    void setImportedTypeName(String _importedTypeName) {
        importedTypeName = _importedTypeName;
    }

    final String getTypeName() {
        return typeName;
    }
    @Override
    final ExecPartType getFirstChild() {
        return null;
    }

    String getPreviousSeparator() {
        return previousSeparator;
    }

}
