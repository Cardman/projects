package code.bean.help.analyze;

import code.util.StringList;
import code.util.core.IndexConstants;

public final class HelpResultText {

    private static final char QUOTE = 39;

    private final StringList texts = new StringList();

    public void buildAna(String _expression) {
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (cur_ == QUOTE) {
                str_.append(QUOTE);
                i_++;
                i_++;
            } else {
                str_.append(cur_);
                i_++;
            }
        }
        texts.add(str_.toString());
    }

    public void buildIdAna(String _expression) {
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }

    public StringList getTexts() {
        return texts;
    }

}
