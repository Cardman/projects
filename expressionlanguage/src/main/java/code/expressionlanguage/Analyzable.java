package code.expressionlanguage;

import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.TypeOwnersDepends;
import code.expressionlanguage.instr.ResultAfterInstKeyWord;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.AnalyzingEl;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.Ints;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public interface Analyzable {

    ContextEl getContextEl();
    ExecutableCode getCurrentExec();
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

    boolean isFinalMutableLoopVar(String _key, int _index);
    boolean containsMutableLoopVar(String _string);
    LoopVariable getMutableLoopVar(String _key);
    void putMutableLoopVar(String _string, LoopVariable _loc);

    CustList<StringMap<LocalVariable>> getLocalVariables();

    boolean isFinalLocalVar(String _key, int _index);
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
    int getOffset();


    void setAnalyzedOffset(int _offset);

    void setOffset(int _offset);

    boolean isStaticContext();

    void setStaticContext(boolean _staticContext);
    GeneType getClassBody(String _type);
    CustList<GeneType> getClassBodies();

    ClassMetaInfo getExtendedClassMetaInfo(String _name);
    ClassMetaInfo getClassMetaInfo(String _name);
    FieldInfo getFieldInfo(ClassField _classField);
    CustList<GeneMethod> getMethodBodiesById(String _genericClassName, MethodId _id);
    int getCurrentChildTypeIndex();

    boolean isMerged();


    void setMerged(boolean _merged);

    boolean isFinalVariable();
    void setFinalVariable(boolean _finalVariable);

    Options getOptions();
    AssignedVariablesBlock getAssignedVariables();
    AnalyzedPageEl getAnalyzing();
    Ints getCurrentBadIndexes();
    Block getCurrentBlock();
    boolean hasDeclarator();
    void setupDeclaratorClass(String _className);
    boolean hasLoopDeclarator();
    void setupLoopDeclaratorClass(String _className);
    boolean isGearConst();
    StringList getNeedInterfaces();

    String resolveCorrectType(String _in);
    String resolveAccessibleIdType(String _in);
    String resolveAccessibleIdTypeWithoutError(String _in);
    String resolveCorrectAccessibleType(String _in, String _fromType);
    String resolveCorrectType(String _in, boolean _exact);
    StringMap<StringList> getCurrentConstraints();
    String resolveCorrectTypeWithoutErrors(String _in, boolean _exact);

    ObjectMap<ClassMethodId,Integer> lookupImportStaticMethods(String _glClass,String _method, Block _rooted);
    ObjectMap<ClassField,Integer> lookupImportStaticFields(String _glClass,String _field, Block _rooted);

    String lookupImportMemberType(String _type, AccessingImportingBlock _rooted, boolean _inherits);
    TypeOwnersDepends lookupImportMemberTypeDeps(String _type, RootBlock _rooted);
    String lookupImportType(String _type, AccessingImportingBlock _rooted);
    String lookupSingleImportType(String _type, AccessingImportingBlock _rooted);

    StringList getAvailableVariables();
    StringList getVariablesNames();

    boolean isAssignedStaticFields();

    void setAssignedStaticFields(boolean _assignedStaticFields);

    boolean isAssignedFields();

    void setAssignedFields(boolean _assignedFields);
    ForLoopPart getForLoopPartState();
    void setForLoopPartState(ForLoopPart _state);
    AnalyzingEl getAnalysisAss();
    boolean isAnnotAnalysis();
    void setAnnotAnalysis(boolean _ana);

    boolean isOkNumOp();

    void setOkNumOp(boolean _okNumOp);
    KeyWords getKeyWords();
    void setKeyWords(KeyWords _keyWords);
    boolean isValidSingleToken(String _id);
    boolean isValidToken(String _id);
    void processInternKeyWord(String _exp, int _fr, ResultAfterInstKeyWord _out);
}
