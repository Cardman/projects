package code.expressionlanguage.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

final class NativeTemplate extends ParentType {

    NativeTemplate(Type _type, ParentType _parent, int _index) {
        super(_type, _parent, _index);
    }

    @Override
    String getBegin() {
        Class<?> cl_ = (Class<?>) ((ParameterizedType)getType()).getRawType();
        return cl_.getName()+TEMPLATE_BEGIN;
    }
    @Override
    String getEnd() {
        return TEMPLATE_END;
    }
}
