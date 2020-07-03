package code.expressionlanguage.analyze.types;

import code.util.IntTreeMap;

abstract class AnaBinaryType extends AnaParentPartType {
    AnaBinaryType(AnaParentPartType _parent, int _index, int _indexInType, IntTreeMap<String> _operators) {
        super(_parent, _index, _indexInType,_operators);
    }
}
