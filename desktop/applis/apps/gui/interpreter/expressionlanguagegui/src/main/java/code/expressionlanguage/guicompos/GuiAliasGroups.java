package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliasGroups;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public class GuiAliasGroups extends CustAliasGroups {
    private GuiAliases guiAliases;
    public GuiAliasGroups(GuiAliases _guiAliases,CustAliases _custAliases, LgNamesContent _content) {
        super(_custAliases, _content);
        guiAliases = _guiAliases;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = super.allMergeTableTypeMethodNames();
        list_.addAllElts(guiAliases.allMergeTableTypeMethodNames(getCustAliases(),getContent()));
        return list_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: guiAliases.allTableTypeFieldNames().entryList()) {
            f_.addEntry(o.getKey(),o.getValue());
        }
        return f_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: guiAliases.allTableTypeMethodNames().entryList()) {
            m_.addEntry(o.getKey(),o.getValue());
        }
        return m_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodParamNames();
        m_.addAllElts(guiAliases.allTableTypeMethodParamNames());
        return m_;
    }
    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ =  super.allRefTypes();
        for (EntryCust<String, String> o: guiAliases.allRefTypes().entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
    }

}
