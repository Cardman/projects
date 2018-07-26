package code.expressionlanguage;

import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AnalyzingEl;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.RowCol;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public interface Analyzable {

    LgNames getStandards();
    String getGlobalClass();
    void setGlobalClass(String _globalClass);
    Classes getClasses();
    String getCurrentFileName();
    RowCol getCurrentLocation();

    String getCurrentVarSetting();
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
    boolean isAnalyzingRoot();
    boolean isRootAffect();
    boolean isFinalVariable();
    void setFinalVariable(boolean _finalVariable);
    void setAnalyzingRoot(boolean _b);
    void setRootAffect(boolean _b);
    Options getOptions();
    AssignedVariablesBlock getAssignedVariables();
    AnalyzedPageEl getAnalyzing();
    Block getCurrentBlock();
    CustList<OperationNode> getTextualSortedOperations();
    boolean isGearConst();
    StringList getNeedInterfaces();

    String resolveTypeMapping(String _in, Block _currentBlock,RowCol _location);
    String resolveCorrectType(String _in);
    String resolveCorrectType(String _in, boolean _exact);

    String resolveBaseType(String _in, Block _currentBlock,RowCol _location);
    String resolveBaseTypeBuildInherits(String _in, Block _currentBlock);
    ObjectMap<ClassMethodId,Integer> lookupImportStaticMethods(String _glClass,String _method, Block _rooted);
    ObjectMap<ClassField,Integer> lookupImportStaticFields(String _glClass,String _field, Block _rooted);
    String lookupImportsDirect(String _type, RootBlock _rooted);
    String lookupImportsIndirect(String _type, RootBlock _rooted);

    boolean isDirectImport();

    void setDirectImport(boolean _directImport);
    StringList getAvailableVariables();
    StringList getVariablesNames();

    boolean isAssignedStaticFields();

    void setAssignedStaticFields(boolean _assignedStaticFields);

    boolean isAssignedFields();

    void setAssignedFields(boolean _assignedFields);
    ForLoopPart getForLoopPartState();
    void setForLoopPartState(ForLoopPart _state);
    AnalyzingEl getAnalysisAss();
}
