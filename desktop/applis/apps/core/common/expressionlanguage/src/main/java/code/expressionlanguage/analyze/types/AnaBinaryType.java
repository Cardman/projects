package code.expressionlanguage.analyze.types;

import code.maths.litteral.StrTypes;

abstract class AnaBinaryType extends AnaParentPartType {
    AnaBinaryType(AnaParentPartType _parent, int _index, int _indexInType, StrTypes _operators) {
        super(_parent, _index, _indexInType,_operators);
    }
}
