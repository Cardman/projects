package code.expressionlanguage.types;

abstract class LeafPartType extends PartType {

    private String typeName;
    private String previousSeparator;
    LeafPartType(ParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType);
        typeName = _type;
        previousSeparator = _previousSeparator;

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
