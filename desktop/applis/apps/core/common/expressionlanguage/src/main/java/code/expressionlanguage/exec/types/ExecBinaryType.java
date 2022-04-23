package code.expressionlanguage.exec.types;

import code.maths.litteralcom.StrTypes;

abstract class ExecBinaryType extends ExecParentPartType {
    ExecBinaryType(ExecParentPartType _parent, int _index, String _previousOperator, StrTypes _valuesEx) {
        super(_parent, _index, _previousOperator,_valuesEx);
    }
}
