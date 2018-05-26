package code.expressionlanguage;

import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
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
    StringMap<LoopVariable> getVars();

    CustList<StringMap<LocalVariable>> getLocalVariables();

    LocalVariable getLocalVar(String _key);

    StringMap<LocalVariable> getCatchVars();

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
    boolean isAmbigous();
    void setAmbigous(boolean _ambigous);
    ClassMetaInfo getClassMetaInfo(String _name);
    CustList<GeneMethod> getMethodBodiesById(String _genericClassName, MethodId _id);
    int getCurrentChildTypeIndex();
    void setCurrentChildTypeIndex(int _index);
    boolean isMerged();

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
}
