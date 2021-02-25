package code.sml;

import code.util.StringMap;
import code.util.core.StringUtil;

public final class DocumentAttribute {
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private static final char EQUALS = '=';

    private DocumentAttribute() {
    }

    public static StringMap<AttributePart> getAttributes(String _html, int _from, int _to) {
        StringMap<AttributePart> attributes_;
        attributes_ = new StringMap<AttributePart>();
        StringBuilder str_ = new StringBuilder();
        int beginToken_ = _from;
        int delimiter_ = -1;
        for (int i = _from; i < _to; i++) {
            char ch_ = _html.charAt(i);
            if (delimiter_ == -1) {
                if (isDelAttr(ch_)) {
                    delimiter_ = ch_;
                    beginToken_ = i + 1;
                }
            } else {
                if (ch_ == delimiter_) {
                    AttributePart attrPart_ = new AttributePart();
                    attrPart_.setBegin(beginToken_);
                    attrPart_.setEnd(i);
                    attributes_.put(str_.toString(), attrPart_);
                    str_ = new StringBuilder();
                    delimiter_ = -1;
                    continue;
                }
            }
            tryAddChar(str_, delimiter_, ch_);
        }
        return attributes_;
    }

    private static void tryAddChar(StringBuilder _str, int _delimiter, char _ch) {
        if (_delimiter == -1) {
            if (spaceOrEq(_ch)) {
                return;
            }
            _str.append(_ch);
        }
    }

    static boolean spaceOrEq(char _ch) {
        return StringUtil.isWhitespace(_ch) || _ch == EQUALS;
    }

    static boolean isDelAttr(char _ch) {
        return DocumentIndexer.isDel(_ch, APOS, QUOT);
    }
}
