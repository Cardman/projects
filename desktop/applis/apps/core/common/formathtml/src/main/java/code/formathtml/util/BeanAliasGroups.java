package code.formathtml.util;

import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public class BeanAliasGroups extends DefaultAliasGroups {
    private DefaultBeanAliases beanAliases;
    public BeanAliasGroups(DefaultBeanAliases _beanAliases,LgNamesContent content) {
        super(content);
        beanAliases = _beanAliases;
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> types_ = super.allRefTypes();
        for (EntryCust<String,String> e: beanAliases.allRefTypes().entryList()) {
            types_.addEntry(e.getKey(),e.getValue());
        }
        return types_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> methods_ = super.allTableTypeMethodNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> e: beanAliases.allTableTypeMethodNames().entryList()) {
            methods_.addEntry(e.getKey(),e.getValue());
        }
        return methods_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodParamNames();
        m_.addAllElts(beanAliases.allTableTypeMethodParamNames());
        return m_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> fields_ = super.allTableTypeFieldNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> e: beanAliases.allTableTypeFieldNames().entryList()) {
            fields_.addEntry(e.getKey(),e.getValue());
        }
        return fields_;
    }


}
