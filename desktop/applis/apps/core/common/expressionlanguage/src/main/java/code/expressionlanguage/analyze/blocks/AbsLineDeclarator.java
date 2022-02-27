package code.expressionlanguage.analyze.blocks;

public interface AbsLineDeclarator {
    void setImportedClassName(String _importedClassName);
    boolean isRefVariable();
    boolean isFinalVariable();
}
