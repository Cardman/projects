package code.expressionlanguage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import code.util.NatTreeMap;
import code.util.StringMap;
import code.xml.AttributePart;


public final class MethodUtil {
//    static final String EMPTY_STRING = "";
//    static final String RETURN_LINE = "\n";
//    static final String RETURN_TAB = "\n\t";

//    static final String CLASS_NAME_ATTRIBUTE = ATTRIBUTE_CLASS_NAME;
    //    static final String NULL_METHOD = String.valueOf((Object)null);
//    static final String NULL_METHOD = "goto";
//    static final String NULL_METHOD = "";
//    static final String SPACE = " ";
//    static final String VAR_METHOD = "varMethod";
//    static final String BEAN_ATTRIBUTE = "bean";
//    static final String ATTRIBUTE_VALUE_CHANGE_EVENT = "valueChangeEvent";
//    static final String COMMA = ",";
//    static final String DOT = ".";
//    static final String GET_KEY = "!key";

    //    private static final String ESCAPED_RIGHT_EL = "\\}";
    //    private static final String ESCAPED_LEFT_EL = "\\{";
    //    private static final String NEXT_FIELDS = "([^,\\}]+(,\\w+){0,2})";
    //    private static final String FORMAT_VAR = "$1";
    //    private static final String NEXT_ARG = "([^,]+),,";
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
//    static StringList checkForLoop(Configuration _conf, Element _node, String _html) {
//        StringList vars_ = new StringList();
//        _conf.getImporting().last().setProcessingAttribute(EMPTY_STRING);
//        _conf.getImporting().last().setOffset(0);
//        _conf.getImporting().last().setLookForAttrValue(false);
//        if (_node.hasAttribute(ATTRIBUTE_LIST)) {
//            String varName_ = _node.getAttribute(ATTRIBUTE_VAR);
//            if (!StringList.isWord(varName_)) {
//                throw new BadVariableNameException(varName_, _conf.getImporting().join(RETURN_LINE), ATTRIBUTE_VAR);
//            }
//            vars_.add(varName_);
//            return vars_;
//        }
////        if (_node.hasAttribute(ATTRIBUTE_MAP)) {
////            String key_ = _node.getAttribute(ATTRIBUTE_KEY);
////            if (!StringList.isWord(key_)) {
////                throw new BadVariableNameException(key_, _conf.getImporting().join(RETURN_LINE), ATTRIBUTE_KEY);
////            }
////            String value_ = _node.getAttribute(ATTRIBUTE_VALUE);
////            if (!StringList.isWord(value_)) {
////                throw new BadVariableNameException(value_, _conf.getImporting().join(RETURN_LINE), ATTRIBUTE_VALUE);
////            }
////            if (StringList.eq(key_,value_)) {
////                throw new KeyValueException(key_, _conf.getImporting().join(RETURN_LINE));
////            }
////            vars_.add(key_);
////            vars_.add(value_);
////            return vars_;
////        }
//        if (_node.hasAttribute(ATTRIBUTE_FROM)) {
//            if (_node.hasAttribute(ATTRIBUTE_TO)) {
//                if (_node.hasAttribute(ATTRIBUTE_STEP)) {
//                    String varName_ = _node.getAttribute(ATTRIBUTE_VAR);
//                    if (!StringList.isWord(varName_)) {
//                        throw new BadVariableNameException(varName_, _conf.getImporting().join(RETURN_LINE), ATTRIBUTE_VAR);
//                    }
//                    vars_.add(varName_);
//                    return vars_;
//                }
//            }
//        }
//        throw new BadLoopException(_conf.getImporting().join(RETURN_LINE));
//    }
}
