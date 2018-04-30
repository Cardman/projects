package code.expressionlanguage;

import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.RowCol;
import code.util.CustList;
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

    StringMap<LocalVariable> getLocalVars();

    StringMap<LocalVariable> getCatchVars();

    StringMap<LocalVariable> getParameters();

    int getOffset();

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
    boolean isEnabledDotted();
    void setEnabledDotted(boolean _enabled);
    boolean isMerged();

    void setMerged(boolean _merged);
    boolean isAnalyzingRoot();
    boolean isRootAffect();
    void setAnalyzingRoot(boolean _b);
    void setRootAffect(boolean _b);
}
