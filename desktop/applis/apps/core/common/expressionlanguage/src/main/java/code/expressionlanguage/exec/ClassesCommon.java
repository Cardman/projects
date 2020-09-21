package code.expressionlanguage.exec;

import code.expressionlanguage.exec.util.PolymorphMethod;
import code.expressionlanguage.structs.Struct;
import code.util.EntryCust;
import code.util.StringMap;

public final class ClassesCommon {
    private final StringMap<String> resources;
    private StringMap<StringMap<Struct>> staticFields;
    private final StringMap<PolymorphMethod> toStringMethodsToCallBodies = new StringMap<PolymorphMethod>();

    public ClassesCommon(){
        resources = new StringMap<String>();
        staticFields = new StringMap<StringMap<Struct>>();
    }

    public void addResources(StringMap<String> _resources) {
        for (EntryCust<String, String> e: _resources.entryList()) {
            resources.addEntry(e.getKey(), e.getValue());
        }
    }
    public StringMap<String> getResources() {
        return resources;
    }

    public StringMap<PolymorphMethod> getToStringMethodsToCallBodies() {
        return toStringMethodsToCallBodies;
    }

    public StringMap<StringMap<Struct>> getStaticFields() {
        return staticFields;
    }

    public void setStaticFields(StringMap<StringMap<Struct>> staticFields) {
        this.staticFields = staticFields;
    }
}
