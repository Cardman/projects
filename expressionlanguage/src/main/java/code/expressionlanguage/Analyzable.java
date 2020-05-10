package code.expressionlanguage;


import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;

public interface Analyzable {

    ContextEl getContextEl();

    LgNames getStandards();
    String getGlobalClass();
    String getLookLocalClass();
    void setLookLocalClass(String _lookLocalClass);

    Classes getClasses();
    String getCurrentFileName();
    int getCurrentLocationIndex();


    void setAnalyzedOffset(int _offset);

    void setOffset(int _offset);

    boolean isMerged();


    void setMerged(boolean _merged);
    boolean isAcceptCommaInstr();
    void setAcceptCommaInstr(boolean _acceptCommaInstr);

    boolean isFinalVariable();
    void setFinalVariable(boolean _finalVariable);

    AnalyzedPageEl getAnalyzing();

    boolean isStaticAccess();

    boolean hasDeclarator();
    void setupDeclaratorClass(String _className);
    boolean hasLoopDeclarator();
    void setupLoopDeclaratorClass(String _className);


    boolean isOkNumOp();

    void setOkNumOp(boolean _okNumOp);
    KeyWords getKeyWords();
    void addWarning(FoundWarningInterpret _warning);
    void addError(FoundErrorInterpret _error);

    boolean isValidSingleToken(String _id);
    boolean isValidToken(String _id);

    String getIndexClassName();


    void buildCurrentConstraintsFull();
}
