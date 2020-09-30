package code.renders.utilcompo;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.formathtml.util.BeanAliasGroups;
import code.formathtml.util.DefaultBeanAliases;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public class CustBeanAliasGroups extends BeanAliasGroups {
    private CustRenderAliases custRenderAliases;
    public CustBeanAliasGroups(CustRenderAliases _custRenderAliases,DefaultBeanAliases _beanAliases, LgNamesContent content) {
        super(_beanAliases, content);
        custRenderAliases = _custRenderAliases;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> t_ = super.allTableTypeVarTypes();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custRenderAliases.allTableTypeVarTypes().entryList()) {
            t_.addEntry(o.getKey(),o.getValue());
        }
        return t_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = super.allMergeTableTypeMethodNames();
        list_.addAllElts(custRenderAliases.allMergeTableTypeMethodNames(getContent()));
        return list_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custRenderAliases.allTableTypeFieldNames().entryList()) {
            f_.addEntry(o.getKey(),o.getValue());
        }
        return f_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> e: custRenderAliases.allTableTypeMethodNames(getContent()).entryList()) {
            m_.addEntry(e.getKey(),e.getValue());
        }
        return m_;
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ =  super.allRefTypes();
        for (EntryCust<String,String> o: custRenderAliases.allRefTypes().entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
    }


}
