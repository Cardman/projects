package code.expressionlanguage.analyze.types;

abstract class AnaLeafPartType extends AnaPartType {
    private String typeName;
    private String previousSeparator;
    AnaLeafPartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType);
        typeName = _type;
        previousSeparator = _previousSeparator;
    }


    final String getTypeName() {
        return typeName;
    }
    @Override
    final AnaPartType getFirstChild() {
        return null;
    }

    public String getPreviousSeparator() {
        return previousSeparator;
    }
}
