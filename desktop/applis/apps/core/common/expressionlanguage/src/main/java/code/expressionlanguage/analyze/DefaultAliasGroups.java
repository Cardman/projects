package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.CustList;
import code.util.StringMap;

public class DefaultAliasGroups {

    private final LgNamesContent content;

    public DefaultAliasGroups(LgNamesContent _content) {
        this.content = _content;
    }

    public LgNamesContent getContent() {
        return content;
    }

    public StringMap<String> allPrimitives(StringMap<String> _mapping) {
        return getContent().allPrimitives(_mapping);
    }

    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        return getContent().allRefTypes(_mapping);
    }
    public CustList<KeyValueMemberName> allMergeTableTypeMethodNames(StringMap<String> _mapping) {
        return getContent().allMergeTableTypeMethodNames(_mapping);
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        return getContent().allTableTypeMethodParamNames(_mapping);
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        return getContent().allTableTypeMethodNames(_mapping);
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        return getContent().allTableTypeFieldNames(_mapping);
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes(StringMap<String> _mapping) {
        return getContent().allTableTypeVarTypes(_mapping);
    }

}
