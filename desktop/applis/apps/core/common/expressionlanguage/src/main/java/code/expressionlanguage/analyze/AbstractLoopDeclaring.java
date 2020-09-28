package code.expressionlanguage.analyze;

public interface AbstractLoopDeclaring {
    boolean hasLoopDeclarator();
    void setupLoopDeclaratorClass(String _className);
    String getIndexClassName();
}
