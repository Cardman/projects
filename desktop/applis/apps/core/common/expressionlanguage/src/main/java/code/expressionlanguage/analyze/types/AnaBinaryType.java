package code.expressionlanguage.analyze.types;

import code.expressionlanguage.common.StrTypes;

abstract class AnaBinaryType extends AnaParentPartType {
    AnaBinaryType(AnaParentPartType _parent, int _index, int _indexInType, StrTypes _operators) {
        super(_parent, _index, _indexInType,_operators);
    }
}
