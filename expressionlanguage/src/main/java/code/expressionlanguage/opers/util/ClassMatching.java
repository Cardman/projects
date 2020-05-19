package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.util.StringList;
import code.util.StringMap;

public final class ClassMatching {

    private String className;

    public ClassMatching(String _className) {
        className = _className;
    }

    public boolean matchClass(ClassMatching _className) {
        return StringList.quickEq(className, _className.className);
    }

    public boolean isAssignableFrom(ClassMatching _c, StringMap<StringList> _map, ContextEl _context) {
        Mapping map_ = new Mapping();
        map_.setMapping(_map);
        map_.setArg(_c.getClassName());
        map_.setParam(className);
        return Templates.isCorrectOrNumbers(map_, _context);
    }

    public String getClassName() {
        return className;
    }
}
