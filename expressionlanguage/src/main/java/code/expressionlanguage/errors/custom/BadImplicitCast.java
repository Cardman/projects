package code.expressionlanguage.errors.custom;

import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.methods.Classes;
import code.util.EntryCust;
import code.util.StringList;

public final class BadImplicitCast extends FoundErrorInterpret {

    private Mapping mapping;

    @Override
    public String display(Classes _classes) {
        StringList tabs_ = new StringList();
        for (EntryCust<String, StringList> e: getMapping().getMapping().entryList()) {
            tabs_.add(e.getKey());
            tabs_.add("{");
            tabs_.add(StringList.join(e.getValue(), ";"));
            tabs_.add("}");
        }
        return StringList.concat(super.display(_classes),SEP_INFO, StringList.join(getMapping().getArg().getNames(), ""),SEP_INFO, StringList.join(mapping.getParam().getNames(), ""),SEP_INFO, StringList.join(tabs_, ","),SEP_INFO);
    }

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping _mapping) {
        mapping = _mapping;
    }
}
