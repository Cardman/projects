package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.stds.LgNames;
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

    public boolean matchClass(ClassMatching _className) {
        return StringList.quickEq(className, _className.className);
    }

    public boolean isAssignableFrom(ClassMatching _c, StringMap<StringList> _map, ContextEl _context) {
        Mapping map_ = new Mapping();
        map_.setMapping(_map);
        map_.setArg(_c.getClassName());
        map_.setParam(className);
        return Templates.isCorrect(map_, _context);
    }

    public boolean isAssignableFrom(ClassMatching _c, ContextEl _context) {
        return LgNames.canBeUseAsArgument(className, _c.getClassName(), _context.getStandards());
    }

    public boolean isPrimitive(LgNames _context) {
        return PrimitiveTypeUtil.isPrimitive(className, _context);
    }

    public boolean isPrimitive(ContextEl _context) {
        return PrimitiveTypeUtil.isPrimitive(className, _context);
    }

    public String getClassName() {
        return className;
    }
}
