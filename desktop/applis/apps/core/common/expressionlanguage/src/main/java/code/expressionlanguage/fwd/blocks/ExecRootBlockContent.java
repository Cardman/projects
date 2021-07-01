package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.util.*;

public final class ExecRootBlockContent {
    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();
    private StringList paramTypes;
    private StringList paramTypesValues = new StringList();
    private CustList<ExecTypeVar> paramTypesMapValues = new CustList<ExecTypeVar>();
    private CustList<StringList> boundsAll = new CustList<StringList>();
    private Ints typeVarCounts = new Ints();
    private String wildCardString = "";
    private String genericString = "";
    private String fullName = "";
    private CustList<ExecRootBlock> selfAndParentTypes = new CustList<ExecRootBlock>();
    private final int idRowCol;
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

    public void setBoundsAll(CustList<StringList> _boundsAll) {
        this.boundsAll = _boundsAll;
    }

    public StringList getParamTypesValues() {
        return paramTypesValues;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String _fullName) {
        this.fullName = _fullName;
    }

    public String getGenericString() {
        return genericString;
    }

    public void setGenericString(String _genericString) {
        this.genericString = _genericString;
    }

    public String getWildCardString() {
        return wildCardString;
    }

    public void setWildCardString(String _wildCardString) {
        this.wildCardString = _wildCardString;
    }

    public CustList<ExecRootBlock> getSelfAndParentTypes() {
        return selfAndParentTypes;
    }

    public void setSelfAndParentTypes(CustList<ExecRootBlock> _selfAndParentTypes) {
        this.selfAndParentTypes = _selfAndParentTypes;
    }

    public Ints getTypeVarCounts() {
        return typeVarCounts;
    }

    public void setTypeVarCounts(Ints _typeVarCounts) {
        this.typeVarCounts = _typeVarCounts;
    }

    public StringList getImportedDirectSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }

    public void setImportedDirectSuperInterfaces(StringList _importedDirectSuperInterfaces) {
        this.importedDirectSuperInterfaces = _importedDirectSuperInterfaces;
    }

    public String getImportedDirectSuperClass() {
        return importedDirectSuperClass;
    }

    public void setImportedDirectSuperClass(String _importedDirectSuperClass) {
        this.importedDirectSuperClass = _importedDirectSuperClass;
    }
}
