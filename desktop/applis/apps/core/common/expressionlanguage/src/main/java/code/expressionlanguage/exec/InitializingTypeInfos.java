package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.*;
import code.util.IdList;
import code.util.core.StringUtil;

public final class InitializingTypeInfos {
    private InitPhase initEnums = InitPhase.READ_ONLY_OTHERS;
    private boolean failInit;
    private final IdList<Struct> sensibleFields = new IdList<Struct>();
    public boolean isSensibleField(ContextEl _context, String _clName) {
        if (!isInitEnums()) {
            return false;
        }
        String curr_ = getCurInitType(_context);
        return !StringUtil.quickEq(curr_, _clName);
    }
    public boolean isContainedSensibleFields(Struct _array) {
        if (!isInitEnums()) {
            return false;
        }
        return getSensibleFields().containsObj(_array);
    }
    public void addSensibleField(ContextEl _context,String _fc, Struct _container) {
        if (!isInitEnums()) {
            return;
        }
        if (!isPossibleSensible(_container)) {
            return;
        }
        String curr_ = getCurInitType(_context);
        if (!StringUtil.quickEq(curr_, _fc)) {
            getSensibleFields().add(_container);
        }
    }
    public void addSensibleField(Struct _container, Struct _owned) {
        if (!isInitEnums()) {
            return;
        }
        if (!isPossibleSensible(_owned)) {
            return;
        }
        if (getSensibleFields().containsObj(_container)) {
            getSensibleFields().add(_owned);
        }
    }
    public void addSensibleElementsFromClonedArray(Struct _array, ArrayStruct _cloned) {
        if (!isInitEnums()) {
            return;
        }
        if (!getSensibleFields().containsObj(_array)) {
            return;
        }
        for (Struct s: _cloned.getInstance()) {
            if (!isPossibleSensible(s)) {
                continue;
            }
            getSensibleFields().add(s);
        }
    }
    private static boolean isPossibleSensible(Struct _s) {
        if (_s == NullStruct.NULL_VALUE) {
            return false;
        }
        if (_s instanceof BooleanStruct) {
            return false;
        }
        if (_s instanceof NumberStruct) {
            return false;
        }
        if (_s instanceof StringStruct) {
            return false;
        }
        if (_s instanceof ReplacementStruct) {
            return false;
        }
        return !(_s instanceof AnnotationStruct);
    }
    public void failInitEnums() {
        if (!isInitEnums()) {
            return;
        }
        setFailInit(true);
    }
    public boolean isInitEnums() {
        return getInitEnums() == InitPhase.READ_ONLY_OTHERS;
    }
    public boolean isWideInitEnums() {
        return getInitEnums() != InitPhase.NOTHING;
    }


    public void resetInitEnums(ContextEl _context) {
        setFailInit(false);
        _context.setCallingState(null);
        getSensibleFields().clear();
        _context.clearPages();
    }
    private String getCurInitType(ContextEl _context) {
        return _context.getCall(0).getGlobalClass();
    }
    public InitPhase getInitEnums() {
        return initEnums;
    }

    public void setInitEnums(InitPhase initEnums) {
        this.initEnums = initEnums;
    }

    public boolean isFailInit() {
        return failInit;
    }

    public void setFailInit(boolean failInit) {
        this.failInit = failInit;
    }

    public IdList<Struct> getSensibleFields() {
        return sensibleFields;
    }
}
