package code.expressionlanguage.types;

import java.lang.reflect.Type;

import code.expressionlanguage.PrimitiveTypeUtil;

final class NativeClass extends LeafType {

    NativeClass(Type _type, ParentType _parent, int _index) {
        super(_type, _parent, _index);
    }

    @Override
    String getBegin() {
        Class<?> cl_ = (Class<?>)getType();
        String name_ = cl_.getName();
        if (cl_.isPrimitive()) {
            name_ = PrimitiveTypeUtil.PRIM + name_;
        }
        return name_;
    }
}
