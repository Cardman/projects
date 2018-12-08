package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;
import code.util.StringList;

public class ErrorStruct implements DisplayableStruct {

    private final ArrayStruct instance;

    private final String className;

    private final String message;

    public ErrorStruct(ExecutableCode _context, String _className) {
        this(_context, "", _className);
    }

    public ErrorStruct(ExecutableCode _context, String _message, String _className) {
        ContextEl cont_ = _context.getContextEl();
        instance = StackTraceElementStruct.newStackTraceElementArray(cont_);
        className = _className;
        message = _message;
    }
    @Override
    public boolean isNull() {
        return false;
    }

    public ArrayStruct getStack() {
        return instance;
    }
    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public Object getInstance() {
        return this;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }
    public String getClassName() {
        return className;
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        StringList str_ = new StringList();
        for (Struct s: instance.getInstance()) {
            str_.add(((StackTraceElementStruct)s).getStringRep());
        }
        return new StringStruct(str_.join("\n"));
    }

    public Struct getMessage() {
        return new StringStruct(message);
    }
}
