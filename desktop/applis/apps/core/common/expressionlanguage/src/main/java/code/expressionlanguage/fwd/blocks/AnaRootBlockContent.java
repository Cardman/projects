package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.TypeVar;
import code.util.*;

public final class AnaRootBlockContent {

    private String packageName ="";

    private CustList<TypeVar> paramTypes = new CustList<TypeVar>();

    private StringMap<TypeVar> paramTypesMap = new StringMap<TypeVar>();

    private int idRowCol;
    private String name = "";

    private String suffix="";
    private RootBlock parentType;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String _packageName) {
        this.packageName = _packageName;
    }

    public CustList<TypeVar> getParamTypes() {
        return paramTypes;
    }

    public StringMap<TypeVar> getParamTypesMap() {
        return paramTypesMap;
    }

    public void setParamTypesMap(StringMap<TypeVar> _paramTypesMap) {
        this.paramTypesMap = _paramTypesMap;
    }

    public int getIdRowCol() {
        return idRowCol;
    }

    public void setIdRowCol(int _idRowCol) {
        this.idRowCol = _idRowCol;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String _suffix) {
        this.suffix = _suffix;
    }

    public RootBlock getParentType() {
        return parentType;
    }

    public void setParentType(RootBlock _parentType) {
        this.parentType = _parentType;
    }
}
