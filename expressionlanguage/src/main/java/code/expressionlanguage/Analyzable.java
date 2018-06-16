package code.expressionlanguage;

import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
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

    LocalVariable getLocalVar(String _key);

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
    Block getCurrentBlock();
    CustList<OperationNode> getTextualSortedOperations();
    boolean isGearConst();
    StringList getNeedInterfaces();
    String resolveType(String _in, boolean _correct);
    MethodId getId(GeneMethod _m);
    ConstructorId getId(GeneConstructor _m);
    String resolveType(String _in, Block _currentBlock,RowCol _location, boolean _correct,
            boolean _checkSimpleCorrect, boolean _checkOnlyExistence);
    String resolveBaseType(String _in, Block _currentBlock,RowCol _location);
}
