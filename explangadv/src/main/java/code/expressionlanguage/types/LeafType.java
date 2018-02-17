package code.expressionlanguage.types;

import java.lang.reflect.Type;

abstract class LeafType extends NativeType {

    LeafType(Type _type, ParentType _parent, int _index) {
        super(_type, _parent, _index);
    }

    @Override
    final NativeType getFirstChild() {
        return null;
    }
}
