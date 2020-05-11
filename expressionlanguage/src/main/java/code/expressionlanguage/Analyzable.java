package code.expressionlanguage;


import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;

public interface Analyzable {

    ContextEl getContextEl();

    LgNames getStandards();

    Classes getClasses();
    String getCurrentFileName();
    int getCurrentLocationIndex();

    void setOffset(int _offset);


    AnalyzedPageEl getAnalyzing();

    boolean isStaticAccess();

    boolean hasDeclarator();
    void setupDeclaratorClass(String _className);
    boolean hasLoopDeclarator();
    void setupLoopDeclaratorClass(String _className);


    KeyWords getKeyWords();
    void addWarning(FoundWarningInterpret _warning);
    void addError(FoundErrorInterpret _error);

    boolean isValidSingleToken(String _id);
    boolean isValidToken(String _id);

    String getIndexClassName();


    void buildCurrentConstraintsFull();
}
