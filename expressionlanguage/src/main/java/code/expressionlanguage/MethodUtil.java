package code.expressionlanguage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import code.util.NatTreeMap;
import code.util.StringMap;
import code.xml.AttributePart;


public final class MethodUtil {

    private static final String COMMENT = "!";
    private static final char GT_TAG = '>';
    private static final char LT_BEGIN_TAG = '<';

    private static final char END_ESCAPED = ';';

    private static final char ENCODED = '&';
    private static final char EQUALS = '=';
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private MethodUtil() {
    }

    public static NatTreeMap<Integer, Integer> getIndexesSpecChars(
            String _html, boolean _realAttr, AttributePart _att, int _beginNode) {
        int begin_ = _att.getBegin();
        int end_ = _att.getEnd();
        int i_ = begin_;
        int delta_ = 0;
        if (_realAttr) {
            delta_ = begin_ - _beginNode;
        }
        int beginEscaped_ = i_;
        NatTreeMap<Integer, Integer> indexes_;
        indexes_ = new NatTreeMap<Integer, Integer>();
        while (i_ < end_) {
            if (_html.charAt(i_) == ENCODED) {
                beginEscaped_ = i_;
                i_++;
                while (_html.charAt(i_) != END_ESCAPED) {
                    i_++;
                }
                indexes_.put(beginEscaped_ - _beginNode - delta_, i_ - beginEscaped_);
            }
            i_++;
        }
        return indexes_;
    }
    public static StringMap<AttributePart> getAttributes(String _html, int _from, int _to) {
        StringMap<AttributePart> attributes_;
        attributes_ = new StringMap<AttributePart>();
        StringBuilder str_ = new StringBuilder();
        int beginToken_ = _from;
        Character delimiter_ = null;
        for (int i = _from; i < _to; i++) {
            char ch_ = _html.charAt(i);
            if (delimiter_ == null) {
                if (ch_ == APOS) {
                    delimiter_ = ch_;
                    beginToken_ = i + 1;
                } else if (ch_ == QUOT) {
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
                    delimiter_ = null;
                    continue;
                }
            }
            if (delimiter_ == null) {
                if (Character.isWhitespace(ch_) || ch_ == EQUALS) {
                    continue;
                }
                str_.append(ch_);
            }
        }
        return attributes_;
    }
    public static int indexOfBeginNode(Node _node, String _html, int _from) {
        if (_node instanceof Element) {
            return _html.indexOf(LT_BEGIN_TAG+_node.getNodeName(), _from) + 1;
        }
        if (_node instanceof Text) {
            int indexText_ = _html.indexOf(GT_TAG, _from);
            while (true) {
                if (_html.charAt(indexText_ + 1) != LT_BEGIN_TAG) {
                    break;
                }
                indexText_ = _html.indexOf(GT_TAG, indexText_ + 1);
            }
            return indexText_ + 1;
        }
        return _html.indexOf(LT_BEGIN_TAG+COMMENT, _from);
    }
}
