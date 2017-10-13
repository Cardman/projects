package code.expressionlanguage.types;

import java.lang.reflect.Type;

class NativeVariable extends LeafType {

    NativeVariable(Type _type, ParentType _parent, int _index) {
        super(_type, _parent, _index);
    }

}
