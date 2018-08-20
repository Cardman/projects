package code.expressionlanguage.methods.util;

import code.expressionlanguage.Mapping;
import code.util.EntryCust;
import code.util.StringList;

public final class BadImplicitCast extends FoundErrorInterpret {

    private Mapping mapping;

    @Override
    public String display() {
        StringList tabs_ = new StringList();
        for (EntryCust<String, StringList> e: mapping.getMapping().entryList()) {
            tabs_.add(e.getKey());
            tabs_.add("{");
            tabs_.add(e.getValue().join(";"));
            tabs_.add("}");
        }
        return StringList.concat(super.display(),SEP_INFO,mapping.getArg().getNames().join(""),SEP_INFO,mapping.getParam().getNames().join(""),SEP_INFO,tabs_.join(","),SEP_INFO);
    }

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping _mapping) {
        mapping = _mapping;
    }
}
