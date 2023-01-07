package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public class CustAliasGroups extends DefaultAliasGroups {
    private final CustAliases custAlias;
    public CustAliasGroups(CustAliases _custAliases,LgNamesContent _content) {
        super(_content);
        custAlias = _custAliases;
    }

    public CustAliases getCustAlias() {
        return custAlias;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> t_ = super.allTableTypeVarTypes();
        t_.addAllEntries(custAlias.allTableTypeVarTypes());
        return t_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = super.allMergeTableTypeMethodNames();
        list_.addAllElts(custAlias.allMergeTableTypeMethodNames(getContent()));
        return list_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames();
        f_.addAllEntries(custAlias.allTableTypeFieldNames());
        return f_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodParamNames();
        m_.addAllElts(custAlias.allTableTypeMethodParamNames());
        return m_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custAlias.allTableTypeMethodNames(getContent()).entryList()) {
            m_.addEntry(o.getKey(),o.getValue());
        }
        return m_;
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ =  super.allRefTypes();
        for (EntryCust<String,String> o: custAlias.allRefTypes().entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
    }

}
