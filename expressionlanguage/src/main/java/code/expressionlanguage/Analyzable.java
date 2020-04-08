package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.instr.ResultAfterInstKeyWord;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.Ints;
import code.util.ObjectMap;
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

    String getCurrentVarSetting();
    void setCurrentVarSetting(String _currentVarSetting);
    LoopVariable getVar(String _key);

    boolean containsMutableLoopVar(String _string);
    LoopVariable getMutableLoopVar(String _key);
    void putMutableLoopVar(String _string, LoopVariable _loc);

    CustList<StringMap<LocalVariable>> getLocalVariables();

    boolean containsLocalVar(String _string);
    LocalVariable getLocalVar(String _key);
    void putLocalVar(String _string, LocalVariable _loc);
    StringList getInfersLocalVars();
    StringList getInfersMutableLocalVars();
    void putLocalVar(String _string);
    void putMutableLoopVar(String _string);

    LocalVariable getCatchVar(String _key);

    StringMap<LocalVariable> getParameters();
    boolean isEnabledInternVars();
    StringMap<LocalVariable> getInternVars();



    void setAnalyzedOffset(int _offset);

    void setOffset(int _offset);

    MethodAccessKind getStaticContext();
    boolean isStaticContext();

    void setAccessStaticContext(MethodAccessKind _staticContext);

    GeneType getClassBody(String _type);


    ClassMetaInfo getExtendedClassMetaInfo(String _name);

    FieldInfo getFieldInfo(ClassField _classField);

    int getCurrentChildTypeIndex(OperationNode _op, GeneType _type, String _fieldName, String _className);

    boolean isMerged();


    void setMerged(boolean _merged);
    boolean isAcceptCommaInstr();
    void setAcceptCommaInstr(boolean _acceptCommaInstr);

    boolean isFinalVariable();
    void setFinalVariable(boolean _finalVariable);

    Options getOptions();

    AnalyzedPageEl getAnalyzing();

    boolean isStaticAccess();
    Ints getCurrentBadIndexes();
    Block getCurrentBlock();
    AnalyzedBlock getCurrentAnaBlock();
    boolean hasDeclarator();
    void setupDeclaratorClass(String _className);
    boolean hasLoopDeclarator();
    void setupLoopDeclaratorClass(String _className);
    boolean isGearConst();


    String resolveCorrectType(String _in);
    String resolveCorrectType(int _loc,String _in);
    String resolveAccessibleIdType(int _loc,String _in);
    String resolveAccessibleIdTypeWithoutError(int _loc,String _in);
    String resolveCorrectAccessibleType(int _loc,String _in, String _fromType);
    String resolveCorrectType(int _loc,String _in, boolean _exact);
    StringMap<StringList> getCurrentConstraints();

    void appendMultiParts(int _begin, String _full, String _in, CustList<PartOffset> _parts);
    void appendParts(int _begin, int _end, String _in, CustList<PartOffset> _parts);
    void appendTitleParts(int _begin, int _end, String _in, CustList<PartOffset> _parts);
    String checkExactType(int _loc,String _in, String _orig);
    String resolveCorrectTypeWithoutErrors(int _loc,String _in, boolean _exact);

    ObjectMap<ClassMethodId,ImportedMethod> lookupImportStaticMethods(String _glClass,String _method, Block _rooted);
    ObjectMap<ClassField,ImportedField> lookupImportStaticFields(String _glClass,String _field, Block _rooted);

    String lookupImportMemberType(String _type, AccessingImportingBlock _rooted, boolean _inherits, StringList _readTypes);

    String lookupImportType(String _type, AccessingImportingBlock _rooted,boolean _line);

    StringMap<Integer> getAvailableVariables();
    StringList getVariablesNamesLoopToInfer();
    StringList getVariablesNamesToInfer();
    StringList getVariablesNames();

    ForLoopPart getForLoopPartState();
    void setForLoopPartState(ForLoopPart _state);

    boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq);



    boolean isOkNumOp();

    void setOkNumOp(boolean _okNumOp);
    KeyWords getKeyWords();
    void addWarning(FoundWarningInterpret _warning);
    void addError(FoundErrorInterpret _error);

    boolean isValidSingleToken(String _id);
    boolean isValidToken(String _id);
    void processInternKeyWord(String _exp, int _fr, ResultAfterInstKeyWord _out);
    boolean isHidden(AccessingImportingBlock _global, RootBlock _type);

    String getIndexClassName();
}
