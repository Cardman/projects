package code.expressionlanguage;


import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public interface Analyzable {

    ContextEl getContextEl();

    LgNames getStandards();
    String getGlobalClass();
    String getLookLocalClass();
    void setLookLocalClass(String _lookLocalClass);
    void setGlobalClass(String _globalClass);
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


    StringMap<StringList> getCurrentConstraints();

    void appendParts(int _begin, int _end, String _in, CustList<PartOffset> _parts);
    void appendTitleParts(int _begin, int _end, String _in, CustList<PartOffset> _parts);

    boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq);



    boolean isOkNumOp();

    void setOkNumOp(boolean _okNumOp);
    KeyWords getKeyWords();
    void addWarning(FoundWarningInterpret _warning);
    void addError(FoundErrorInterpret _error);

    boolean isValidSingleToken(String _id);
    boolean isValidToken(String _id);

    String getIndexClassName();

    AccessingImportingBlock getCurrentGlobalBlock();
    AccessingImportingBlock getCurrentGlobalBlock(AccessingImportingBlock _bl);
    CustList<PartOffset> getCurrentParts();

    void buildCurrentConstraintsFull();
}
