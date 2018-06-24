package code.expressionlanguage;

import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.RowCol;
import code.util.CustList;
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

    String resolveDynamicType(String _in, RootBlock _file);

    String resolveTypeMapping(String _in, Block _currentBlock,RowCol _location);
    String resolveCorrectType(String _in);
    String resolveCorrectType(String _in, boolean _exact);

    String resolveType(String _in, Block _currentBlock,RowCol _location);
    String resolveBaseType(String _in, Block _currentBlock,RowCol _location);
    String resolveBaseTypeBuildInherits(String _in, Block _currentBlock);
}
