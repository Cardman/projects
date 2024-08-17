package code.formathtml.util;

import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public class BeanAliasGroups extends DefaultAliasGroups {
    private DefaultBeanAliases beanAliases;
    public BeanAliasGroups(DefaultBeanAliases _beanAliases,LgNamesContent _content) {
        super(_content);
        beanAliases = _beanAliases;
    }

    @Override
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> types_ = super.allRefTypes(_mapping);
        for (EntryCust<String,String> e: beanAliases.allRefTypes(_mapping).entryList()) {
            types_.addEntry(e.getKey(),e.getValue());
        }
        return types_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> methods_ = super.allTableTypeMethodNames(_mapping);
        for (EntryCust<String,CustList<KeyValueMemberName>> e: beanAliases.allTableTypeMethodNames(getContent().getReflect(),_mapping).entryList()) {
            methods_.addEntry(e.getKey(),e.getValue());
        }
        return methods_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodParamNames(_mapping);
        m_.addAllElts(beanAliases.allTableTypeMethodParamNames(_mapping));
        return m_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> fields_ = super.allTableTypeFieldNames(_mapping);
        for (EntryCust<String,CustList<KeyValueMemberName>> e: beanAliases.allTableTypeFieldNames(_mapping).entryList()) {
            fields_.addEntry(e.getKey(),e.getValue());
        }
        return fields_;
    }


}
