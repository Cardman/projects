package code.expressionlanguage;

import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeOwnersDepends;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.AnalyzingEl;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.RowCol;
import code.util.CustList;
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
    RowCol getCurrentLocation();

    String getCurrentVarSetting();
    void setCurrentVarSetting(String _currentVarSetting);
    LoopVariable getVar(String _key);

    LoopVariable getMutableLoopVar(String _key, int _index);
    boolean containsMutableLoopVar(String _string);
    LoopVariable getMutableLoopVar(String _key);
    void putMutableLoopVar(String _string, LoopVariable _loc);

    CustList<StringMap<LocalVariable>> getLocalVariables();
    LocalVariable getLocalVar(String _key, int _index);
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
    int getGlobalOffset();

    void setAnalyzedOffset(int _offset);

    void setOffset(int _offset);

    boolean isStaticContext();

    void setStaticContext(boolean _staticContext);
    GeneType getClassBody(String _type);
    CustList<GeneType> getClassBodies();
    boolean isAmbigous();
    void setAmbigous(boolean _ambigous);
    ClassMetaInfo getExtendedClassMetaInfo(String _name);
    ClassMetaInfo getClassMetaInfo(String _name);
    FieldInfo getFieldInfo(ClassField _classField);
    CustList<GeneMethod> getMethodBodiesById(String _genericClassName, MethodId _id);
    int getCurrentChildTypeIndex();
    void setCurrentChildTypeIndex(int _index);
    boolean isMerged();
    Struct getInternGlobal();
    String getInternGlobalClass();
    boolean isInternGlobal();

    void setMerged(boolean _merged);

    boolean isFinalVariable();
    void setFinalVariable(boolean _finalVariable);

    Options getOptions();
    AssignedVariablesBlock getAssignedVariables();
    AnalyzedPageEl getAnalyzing();
    Block getCurrentBlock();
    CustList<OperationNode> getTextualSortedOperations();
    boolean isGearConst();
    StringList getNeedInterfaces();

    String resolveCorrectType(String _in);
    String resolveIdType(String _in);
    String resolveAccessibleIdType(String _in);
    String resolveCorrectAccessibleType(String _in, String _fromType);
    String resolveCorrectType(String _in, boolean _exact);
    StringMap<StringList> getCurrentConstraints();
    String resolveCorrectTypeWithoutErrors(String _in, boolean _exact);

    ObjectMap<ClassMethodId,Integer> lookupImportStaticMethods(String _glClass,String _method, Block _rooted);
    ObjectMap<ClassField,Integer> lookupImportStaticFields(String _glClass,String _field, Block _rooted);

    String lookupImportMemberType(String _type, AccessingImportingBlock _rooted, boolean _inherits);
    TypeOwnersDepends lookupImportMemberTypeDeps(String _type, AccessingImportingBlock _rooted);
    String lookupImportType(String _type, AccessingImportingBlock _rooted);
    String lookupSingleImportType(String _type, AccessingImportingBlock _rooted);

    boolean isDirectImport();

    void setDirectImport(boolean _directImport);
    StringList getAvailableVariables();
    StringList getVariablesNames();
    SortedClassField getCurrentInitizedField();
    void setCurrentInitizedField(SortedClassField _field);

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
}
