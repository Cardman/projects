package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.util.CustList;
import code.util.*;

abstract class LeafPartType extends PartType {

    private String typeName;
    private String importedTypeName = EMPTY_STRING;
    private String previousSeparator = "..";
    LeafPartType(ParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType);
        typeName = _type;
        previousSeparator = _previousSeparator;

    }
    abstract void checkDynExistence(Analyzable _an, CustList<IntTreeMap< String>>_dels);
    final String exportHeader() {
        return importedTypeName;
    }
    void setImportedTypeName(String _importedTypeName) {
        importedTypeName = _importedTypeName;
    }

    final String getTypeName() {
        return typeName;
    }
    @Override
    final PartType getFirstChild() {
        return null;
    }

    public String getPreviousSeparator() {
        return previousSeparator;
    }
}
