package code.expressionlanguage.exec.types;

abstract class ExecBinaryType extends ExecParentPartType {
    ExecBinaryType(ExecParentPartType _parent, int _index, String _previousOperator) {
        super(_parent, _index, _previousOperator);
    }
}
