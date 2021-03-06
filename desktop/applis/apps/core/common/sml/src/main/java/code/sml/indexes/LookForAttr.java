package code.sml.indexes;

import code.sml.DocumentAttribute;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class LookForAttr {
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private static final char EQUALS = '=';
    private int beginToken;
    private StringBuilder str = new StringBuilder();
    private int delimiter = -1;
    private int foundAttr = IndexConstants.INDEX_NOT_FOUND_ELT;
    LookForAttr(int _firstIndex) {
        beginToken = _firstIndex;
    }
    boolean keep(int _i, String _xml, String _attribute) {
        char ch_ = _xml.charAt(_i);
        if (delimiter == -1) {
            if (DocumentAttribute.isDel(ch_, APOS, QUOT)) {
                delimiter = ch_;
            }
        } else {
            if (ch_ == delimiter) {
                delimiter = -1;
                beginToken = _i + 1;
                beginToken = DocumentIndexer.skipSpace(_xml, beginToken);
                return true;
            }
        }
        if (delimiter == -1) {
            if (DocumentAttribute.spaceOrEq(ch_)) {
                if (StringUtil.quickEq(str.toString(), _attribute)) {
                    foundAttr = beginToken;
                    return false;
                }
                str = renew(ch_,str);
                return true;
            }
            str.append(ch_);
        }
        return true;
    }

    int getFoundAttr() {
        return foundAttr;
    }

    private static StringBuilder renew(char _cur, StringBuilder _str) {
        if (_cur == EQUALS) {
            return new StringBuilder();
        }
        return _str;
    }
}
