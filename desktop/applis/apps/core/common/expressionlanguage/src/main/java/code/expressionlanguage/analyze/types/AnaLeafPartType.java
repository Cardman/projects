package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.errors.AnalysisMessages;

abstract class AnaLeafPartType extends AnaPartType {
    private final String typeName;
    private final String previousSeparator;
    AnaLeafPartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator, AnalysisMessages _messages) {
        super(_parent, _index, _indexInType, _messages);
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
