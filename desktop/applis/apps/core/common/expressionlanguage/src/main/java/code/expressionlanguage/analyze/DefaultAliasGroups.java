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

    public StringMap<String> allPrimitives() {
        return getContent().allPrimitives();
    }

    public StringMap<String> allRefTypes() {
        return getContent().allRefTypes();
    }
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        return getContent().allMergeTableTypeMethodNames();
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        return getContent().allTableTypeMethodParamNames();
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        return getContent().allTableTypeMethodNames();
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        return getContent().allTableTypeFieldNames();
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        return getContent().allTableTypeVarTypes();
    }

}
