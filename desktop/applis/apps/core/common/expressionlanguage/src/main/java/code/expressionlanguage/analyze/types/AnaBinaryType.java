package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.maths.litteralcom.StrTypes;

abstract class AnaBinaryType extends AnaParentPartType {
    AnaBinaryType(AnaParentPartType _parent, int _index, int _indexInType, StrTypes _operators, AnalysisMessages _messages) {
        super(_parent, _index, _indexInType,_operators, _messages);
    }
}
