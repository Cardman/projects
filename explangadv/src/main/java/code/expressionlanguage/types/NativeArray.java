package code.expressionlanguage.types;

import java.lang.reflect.Type;

final class NativeArray extends ParentType {

    NativeArray(Type _type, ParentType _parent, int _index) {
        super(_type, _parent, _index);
    }

    @Override
    String getBegin() {
        return ARRAY;
    }

    @Override
    String getEnd() {
        return EMPTY_STRING;
    }
}
