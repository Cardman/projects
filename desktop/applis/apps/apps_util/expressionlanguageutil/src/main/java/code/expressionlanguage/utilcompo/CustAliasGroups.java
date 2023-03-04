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
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> t_ = super.allTableTypeVarTypes(_mapping);
        t_.addAllEntries(custAlias.allTableTypeVarTypes(_mapping));
        return t_;
    }

    @Override
    public CustList<KeyValueMemberName> allMergeTableTypeMethodNames(StringMap<String> _mapping) {
        return custAlias.allMergeTableTypeMethodNames(_mapping,getContent());
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames(_mapping);
        f_.addAllEntries(custAlias.allTableTypeFieldNames(_mapping));
        return f_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodParamNames(_mapping);
        m_.addAllElts(custAlias.allTableTypeMethodParamNames(_mapping));
        return m_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames(_mapping);
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custAlias.allTableTypeMethodNames(_mapping,m_,getContent()).entryList()) {
            m_.addEntry(o.getKey(),o.getValue());
        }
        return m_;
    }

    @Override
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> ref_ =  super.allRefTypes(_mapping);
        for (EntryCust<String,String> o: custAlias.allRefTypes(_mapping).entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
    }

}
