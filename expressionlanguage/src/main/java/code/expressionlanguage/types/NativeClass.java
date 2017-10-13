package code.expressionlanguage.types;

import java.lang.reflect.Type;

class NativeClass extends LeafType {

    NativeClass(Type _type, ParentType _parent, int _index) {
        super(_type, _parent, _index);
    }

}
