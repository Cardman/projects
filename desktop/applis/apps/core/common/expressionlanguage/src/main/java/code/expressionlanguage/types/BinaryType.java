package code.expressionlanguage.types;

abstract class BinaryType extends ParentPartType {
    BinaryType(ParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
    }

    abstract String getSeparator(int _index);
    abstract String getSingleSeparator(int _index);
}
