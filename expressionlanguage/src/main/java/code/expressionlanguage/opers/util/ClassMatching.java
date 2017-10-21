package code.expressionlanguage.opers.util;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Classes;
import code.util.StringList;
import code.util.StringMap;

public final class ClassMatching {

    private String className;

    public ClassMatching(String _className) {
        className = _className;
    }

    @Override
    public String toString() {
        return className.toString();
    }
    public boolean matchClass(String _className) {
        return StringList.quickEq(className, _className);
    }
    public boolean matchClass(Class<?> _class) {
        return StringList.quickEq(className, _class.getName());
    }

    public boolean matchClass(ClassMatching _className) {
        return StringList.quickEq(className, _className.className);
    }

    public boolean isAssignableFrom(ClassMatching _c, StringMap<StringList> _map, Classes _classes) {
        Mapping map_ = new Mapping();
        map_.setMapping(_map);
        map_.setArg(_c.getClassName());
        map_.setParam(className);
        return Templates.isCorrect(map_, _classes);
    }

    public boolean isPrimitive() {
        return PrimitiveTypeUtil.isPrimitive(className);
    }

    public String getClassName() {
        return className;
    }
}
