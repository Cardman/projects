package code.expressionlanguage.opers.util;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;
import code.util.StringMap;

public final class ClassMatching {

    private String className;

    public ClassMatching(String _className) {
        className = _className;
    }

    public boolean matchClass(String _className) {
        return StringList.quickEq(className, _className);
    }

    public boolean matchClass(ClassMatching _className) {
        return StringList.quickEq(className, _className.className);
    }

    public boolean isAssignableFrom(ClassMatching _c, StringMap<StringList> _map, Analyzable _context) {
        Mapping map_ = new Mapping();
        map_.setMapping(_map);
        map_.setArg(_c.getClassName());
        map_.setParam(className);
        return Templates.isCorrectOrNumbers(map_, _context);
    }

    public boolean isPrimitive(LgNames _context) {
        return PrimitiveTypeUtil.isPrimitive(className, _context);
    }

    public boolean isPrimitive(Analyzable _context) {
        return PrimitiveTypeUtil.isPrimitive(className, _context);
    }

    public String getClassName() {
        return className;
    }
}
