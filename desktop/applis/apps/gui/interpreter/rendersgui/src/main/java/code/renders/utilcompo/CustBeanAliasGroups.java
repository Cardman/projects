package code.renders.utilcompo;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.formathtml.util.BeanAliasGroups;
import code.formathtml.util.DefaultBeanAliases;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public class CustBeanAliasGroups extends BeanAliasGroups {
    private CustAliases custAliases;
    public CustBeanAliasGroups(DefaultBeanAliases _beanAliases, CustAliases _custAliases, LgNamesContent _content) {
        super(_beanAliases, _content);
        custAliases = _custAliases;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> t_ = super.allTableTypeVarTypes(_mapping);
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custAliases.allTableTypeVarTypes(_mapping).entryList()) {
            t_.addEntry(o.getKey(),o.getValue());
        }
        return t_;
    }

    @Override
    public CustList<KeyValueMemberName> allMergeTableTypeMethodNames(StringMap<String> _mapping) {
        return custAliases.allMergeTableTypeMethodNames(_mapping,getContent());
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames(_mapping);
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custAliases.allTableTypeFieldNames(_mapping).entryList()) {
            f_.addEntry(o.getKey(),o.getValue());
        }
        return f_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames(_mapping);
        for (EntryCust<String,CustList<KeyValueMemberName>> e: custAliases.allTableTypeMethodNames(_mapping, m_,getContent()).entryList()) {
            m_.addEntry(e.getKey(),e.getValue());
        }
        return m_;
    }

    @Override
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> ref_ =  super.allRefTypes(_mapping);
        for (EntryCust<String,String> o: custAliases.allRefTypes(_mapping).entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
    }


}
