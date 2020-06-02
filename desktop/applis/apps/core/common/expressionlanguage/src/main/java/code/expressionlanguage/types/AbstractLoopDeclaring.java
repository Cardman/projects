package code.expressionlanguage.types;

public interface AbstractLoopDeclaring {
    boolean hasLoopDeclarator();
    void setupLoopDeclaratorClass(String _className);
    String getIndexClassName();
}
