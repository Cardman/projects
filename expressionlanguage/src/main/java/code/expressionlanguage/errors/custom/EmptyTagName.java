package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;


public final class EmptyTagName extends FoundErrorInterpret {

    private static final String TAG = "empty tag";

    private String foundTag = "";

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),TAG,SEP_KEY_VAL,foundTag,SEP_INFO);
    }

}
