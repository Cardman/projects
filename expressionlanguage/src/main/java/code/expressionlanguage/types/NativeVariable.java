package code.expressionlanguage.types;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

class NativeVariable extends LeafType {

    NativeVariable(Type _type, ParentType _parent, int _index) {
        super(_type, _parent, _index);
    }

    @Override
    String getBegin() {
        TypeVariable<?> cl_ = (TypeVariable<?>)getType();
        return PREFIX+cl_.getName();
    }
}
