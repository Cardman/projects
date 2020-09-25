package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.util.*;

public final class ExecRootBlockContent {
    private final int idRowCol;
    private StringList paramTypes;
    private StringList paramTypesValues = new StringList();
    private CustList<ExecTypeVar> paramTypesMapValues = new CustList<ExecTypeVar>();
    private CustList<StringList> boundsAll = new CustList<StringList>();
    private Ints typeVarCounts = new Ints();
    private String wildCardString = "";
    private String genericString = "";
    private String fullName = "";
    private CustList<ExecRootBlock> selfAndParentTypes = new CustList<ExecRootBlock>();
    public ExecRootBlockContent(AnaRootBlockContent _cont) {
        idRowCol = _cont.getIdRowCol();
        paramTypes = new StringList();
        for (TypeVar t: _cont.getParamTypes()) {
            paramTypes.add(t.getName());
        }
    }

    public void update(AnaRootBlockContent _cont) {
        paramTypesValues = new StringList();
        for (ExecRootBlock r: getSelfAndParentTypes()) {
            for (String t: r.getRootBlockContent().paramTypes) {
                paramTypesValues.add(t);
            }
        }
        paramTypesMapValues = new CustList<ExecTypeVar>();
        for (EntryCust<String,TypeVar> e: _cont.getParamTypesMap().entryList()) {
            ExecTypeVar t_ = new ExecTypeVar();
            t_.setName(e.getValue().getName());
            t_.setConstraints(e.getValue().getConstraints());
            paramTypesMapValues.add(t_);
        }
    }

    public int getIdRowCol() {
        return idRowCol;
    }

    public CustList<ExecTypeVar> getParamTypesMapValues() {
        return paramTypesMapValues;
    }

    public CustList<StringList> getBoundsAll() {
        return boundsAll;
    }

    public void setBoundsAll(CustList<StringList> boundsAll) {
        this.boundsAll = boundsAll;
    }

    public StringList getParamTypesValues() {
        return paramTypesValues;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGenericString() {
        return genericString;
    }

    public void setGenericString(String genericString) {
        this.genericString = genericString;
    }

    public String getWildCardString() {
        return wildCardString;
    }

    public void setWildCardString(String wildCardString) {
        this.wildCardString = wildCardString;
    }

    public CustList<ExecRootBlock> getSelfAndParentTypes() {
        return selfAndParentTypes;
    }

    public void setSelfAndParentTypes(CustList<ExecRootBlock> selfAndParentTypes) {
        this.selfAndParentTypes = selfAndParentTypes;
    }

    public Ints getTypeVarCounts() {
        return typeVarCounts;
    }

    public void setTypeVarCounts(Ints typeVarCounts) {
        this.typeVarCounts = typeVarCounts;
    }

}
