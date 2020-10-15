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
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> t_ = super.allTableTypeVarTypes();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custAliases.allTableTypeVarTypes().entryList()) {
            t_.addEntry(o.getKey(),o.getValue());
        }
        return t_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = super.allMergeTableTypeMethodNames();
        list_.addAllElts(custAliases.allMergeTableTypeMethodNames(getContent()));
        return list_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custAliases.allTableTypeFieldNames().entryList()) {
            f_.addEntry(o.getKey(),o.getValue());
        }
        return f_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> e: custAliases.allTableTypeMethodNames(getContent()).entryList()) {
            m_.addEntry(e.getKey(),e.getValue());
        }
        return m_;
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ =  super.allRefTypes();
        for (EntryCust<String,String> o: custAliases.allRefTypes().entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
    }


}
