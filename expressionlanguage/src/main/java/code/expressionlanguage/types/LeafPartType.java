package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.util.CustList;
import code.util.NatTreeMap;

abstract class LeafPartType extends PartType {

    private String typeName;
    private String importedTypeName = EMPTY_STRING;
    public LeafPartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType);
        typeName = _type;
    }
    public abstract void checkDynExistence(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels);
    public final String exportHeader() {
        return importedTypeName;
    }
    protected void setImportedTypeName(String _importedTypeName) {
        importedTypeName = _importedTypeName;
    }

    public String getTypeName() {
        return typeName;
    }
    @Override
    public final PartType getFirstChild() {
        return null;
    }
}
