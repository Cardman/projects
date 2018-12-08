package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;


public final class UnexpectedTagName extends FoundErrorInterpret {

    private static final String TAG = "unexpected tag";

    private String foundTag = "";

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),TAG,SEP_KEY_VAL,foundTag,SEP_INFO);
    }

    public String getFoundTag() {
        return foundTag;
    }

    public void setFoundTag(String _foundTag) {
        foundTag = _foundTag;
    }

}
