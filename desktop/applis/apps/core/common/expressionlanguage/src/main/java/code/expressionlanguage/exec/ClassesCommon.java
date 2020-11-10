package code.expressionlanguage.exec;

import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class ClassesCommon {
    private final StringMap<String> resources;
    private StringMap<StringMap<Struct>> staticFields;
    private final StringMap<ExecTypeFunction> toStringMethodsToCallBodies = new StringMap<ExecTypeFunction>();

    public ClassesCommon(){
        resources = new StringMap<String>();
        staticFields = new StringMap<StringMap<Struct>>();
    }

    public StringMap<String> getResources() {
        return resources;
    }

    public StringMap<ExecTypeFunction> getToStringMethodsToCallBodies() {
        return toStringMethodsToCallBodies;
    }

    public StringMap<StringMap<Struct>> getStaticFields() {
        return staticFields;
    }

    public void setStaticFields(StringMap<StringMap<Struct>> _staticFields) {
        this.staticFields = _staticFields;
    }
}
