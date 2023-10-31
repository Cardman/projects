package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliasGroups;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public class GuiAliasGroups extends CustAliasGroups {
    private final GuiAliases guiAliases;
    public GuiAliasGroups(GuiAliases _guiAliases,CustAliases _custAliases, LgNamesContent _content) {
        super(_custAliases, _content);
        guiAliases = _guiAliases;
    }

    @Override
    public CustList<KeyValueMemberName> allMergeTableTypeMethodNames(StringMap<String> _mapping) {
        CustList<KeyValueMemberName> val_ = super.allMergeTableTypeMethodNames(_mapping);
        val_.addAllElts(guiAliases.allMergeTableTypeMethodNames(_mapping));
        return val_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames(_mapping);
        for (EntryCust<String,CustList<KeyValueMemberName>> o: guiAliases.allTableTypeFieldNames(_mapping).entryList()) {
            f_.addEntry(o.getKey(),o.getValue());
        }
        return f_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames(_mapping);
        for (EntryCust<String,CustList<KeyValueMemberName>> o: guiAliases.allTableTypeMethodNames(_mapping).entryList()) {
            m_.addEntry(o.getKey(),o.getValue());
        }
        return m_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodParamNames(_mapping);
        m_.addAllElts(guiAliases.allTableTypeMethodParamNames(_mapping));
        return m_;
    }
    @Override
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> ref_ =  super.allRefTypes(_mapping);
        for (EntryCust<String, String> o: guiAliases.allRefTypes(_mapping).entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
    }

}
