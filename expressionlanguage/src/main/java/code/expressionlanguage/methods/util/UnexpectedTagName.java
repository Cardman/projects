package code.expressionlanguage.methods.util;


public final class UnexpectedTagName extends FoundErrorInterpret {

    private static final String TAG = "unexpected tag";

    private String foundTag;

    @Override
    public String display() {
        return super.display()+TAG+SEP_KEY_VAL+foundTag+SEP_INFO;
    }

    public String getFoundTag() {
        return foundTag;
    }

    public void setFoundTag(String _foundTag) {
        foundTag = _foundTag;
    }

}
