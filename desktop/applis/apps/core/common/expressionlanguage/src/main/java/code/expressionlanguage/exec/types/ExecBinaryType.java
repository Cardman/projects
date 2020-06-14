package code.expressionlanguage.exec.types;

abstract class ExecBinaryType extends ExecParentPartType {
    ExecBinaryType(ExecParentPartType _parent, int _index) {
        super(_parent, _index);
    }

    abstract String getSeparator(int _index);
    abstract String getSingleSeparator(int _index);
}
