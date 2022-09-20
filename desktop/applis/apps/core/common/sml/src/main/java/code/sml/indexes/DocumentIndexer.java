package code.sml.indexes;

import code.sml.*;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DocumentIndexer {

    private static final String EMPTY_STRING = "";

    private static final char ENCODED = '&';

    private static final char NUMBERED_CHAR = '#';

    private static final char SLASH = '/';

    private static final char TAB = '\t';

    private static final char LINE_RETURN = '\n';

    private static final char END_ESCAPED = ';';

    private static final char QUOT = 34;
    private static final char APOS = 39;
    private static final char LT = 60;
    private static final char GT = 62;
    private DocumentIndexer() {
    }

    public static RowCol getRowColOfNodeOrAttribute(String _xml, Node _node, int _offest,
                                                    String _attribute, int _tabWidth, boolean _attrValue) {
        if (_node == null) {
            return new RowCol();
        }
        int index_ = getIndexOfNodeOrAttribute(_xml, _node, _attribute, _attrValue);
        return getRowColOfString(_xml, index_ + _offest, _tabWidth);
    }

    public static RowCol getRowColOfString(String _string, int _index, int _tabWidth) {
        int lastIndex_ = _string.lastIndexOf(LINE_RETURN, _index);
        int nbChars_ = 0;
        int nbLine_ = IndexConstants.ONE_ELEMENT;
        for (int i = lastIndex_; i >= IndexConstants.FIRST_INDEX; i--) {
            if (_string.charAt(i) == LINE_RETURN) {
                nbLine_++;
            }
        }
        for (int i = _index; i > lastIndex_; i--) {
            if (_string.charAt(i) == TAB) {
                nbChars_ += _tabWidth;
            } else {
                nbChars_++;
            }
        }
        RowCol rc_ = new RowCol();
        rc_.setRow(nbLine_);
        rc_.setCol(nbChars_);
        return rc_;
    }

    public static int getIndexOfNodeOrAttribute(String _xml, Node _node, String _attribute, boolean _attrValue) {
        Document doc_ = _node.getOwnerDocument();
        Element root_ = doc_.getDocumentElement();
        CustList<Node> nodesBefore_ = getDeepChildNodesDocOrder(root_, _node);
        int nbSameNamedNodes_ = IndexConstants.SIZE_EMPTY;
        if (_node instanceof Element) {
            return processElt(_xml, (Element) _node, _attribute, _attrValue, nodesBefore_, nbSameNamedNodes_);
        }
        String searchedText_ = _node.getTextContent();
        for (Node n: nodesBefore_) {
            if (!(n instanceof Text)) {
                continue;
            }
            if (StringUtil.quickEq(n.getTextContent(), searchedText_)) {
                nbSameNamedNodes_++;
            }
        }
        StringBuilder arg_ = new StringBuilder();
        int i_ = _xml.indexOf(LT) + 1;
        int found_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        int count_ = 0;
        int nb_ = nbSameNamedNodes_ + 1;
        boolean inside_ = false;
        while (count_ < nb_) {
            char cur_ = _xml.charAt(i_);
            if (cur_ == GT) {
                inside_ = true;
                i_++;
                found_ = i_;
            } else if (cur_ == LT) {
                inside_ = false;
                StringBuilder formatted_ = processLt(arg_);
                if (StringUtil.quickEq(formatted_.toString(), searchedText_)) {
                    count_++;
                }
                arg_ = new StringBuilder();
                i_++;
            } else if (inside_) {
                arg_.append(cur_);
                i_++;
            } else {
                i_++;
            }
        }
        return found_;
    }

    private static StringBuilder processLt(StringBuilder _arg) {
        StringBuilder formatted_ = new StringBuilder();
        int j_ = 0;
        int lenArg_ = _arg.length();
        while (j_ < lenArg_) {
            char curCharArg_ = _arg.charAt(j_);
            if (curCharArg_ != ENCODED) {
                formatted_.append(curCharArg_);
            } else {
                if (_arg.charAt(j_ + 1) == NUMBERED_CHAR) {
                    j_++;
                    j_++;
                    StringBuilder nbArg_ = new StringBuilder();
                    j_ = goToEndEsc(_arg, j_, nbArg_);
                    int intArg_ = NumberUtil.parseInt(nbArg_.toString());
                    formatted_.append((char) intArg_);
                    j_++;
                    continue;
                }
                StringBuilder strArg_ = new StringBuilder();
                j_ = goToEndEsc(_arg, j_, strArg_);
                String convered_ = DocumentBuilder.encodeHtml(strArg_.append(END_ESCAPED).toString());
                convered_ = convered_.substring(IndexConstants.SECOND_INDEX + 1, convered_.length() - 1);
                int intArg_ = NumberUtil.parseInt(convered_);
                formatted_.append((char) intArg_);
            }
            j_++;
        }
        return formatted_;
    }

    private static int goToEndEsc(StringBuilder _arg, int _j, StringBuilder _nbArg) {
        int j_ = _j;
        char charArg_ = _arg.charAt(j_);
        while (charArg_ != END_ESCAPED) {
            j_++;
            _nbArg.append(charArg_);
            charArg_ = _arg.charAt(j_);
        }
        return j_;
    }

    private static int processElt(String _xml, Element _node, String _attribute, boolean _attrValue, CustList<Node> _nodesBefore, int _nbSameNamedNodes) {
        int nbSameNamedNodes_ = _nbSameNamedNodes;
        String nodeName_ = _node.getTagName();
        for (Node n: _nodesBefore) {
            if (!(n instanceof Element)) {
                continue;
            }
            if (StringUtil.quickEq(((Element) n).getTagName(), nodeName_)) {
                nbSameNamedNodes_++;
            }
        }
        int index_ = 0;
        int count_ = 0;
        int nb_ = nbSameNamedNodes_ + 1;
        int found_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        while (count_ < nb_) {
            found_ = _xml.indexOf(StringUtil.concat(Character.toString(LT),nodeName_), index_) + 1;
            boolean isTag_ = true;
            int j_ = found_ + nodeName_.length();
            if (!StringUtil.isWhitespace(_xml.charAt(j_)) && _xml.charAt(j_) != GT && _xml.charAt(j_) != SLASH) {
                isTag_ = false;
            }
            if (isTag_) {
                count_++;
            }
            index_ = _xml.indexOf(LT, found_+ nodeName_.length());
        }
        if (_attribute.isEmpty()) {
            return found_;
        }
        return loopAttr2(_xml, _attribute, _attrValue, nodeName_, found_);
    }

    private static int loopAttr2(String _xml, String _attribute, boolean _attrValue, String _nodeName, int _found) {
        int firstIndex_ = _found + _nodeName.length();
        firstIndex_ = skipSpace(_xml, firstIndex_);
        return loopAttr(_xml, _attribute, _attrValue, firstIndex_);
    }

    private static int loopAttr(String _xml, String _attribute, boolean _attrValue, int _firstIndex) {
        int lastIndex_ = _xml.indexOf(GT, _firstIndex);
        LookForAttr lk_ = new LookForAttr(_firstIndex);
        for (int i = _firstIndex; i < lastIndex_; i++) {
            if (!lk_.keep(i,_xml,_attribute)) {
                break;
            }
        }
        return tryFindAttr(_xml, _attribute, _attrValue, lastIndex_, lk_.getFoundAttr());
    }

    static int skipSpace(String _xml, int _beginToken) {
        int beginToken_ = _beginToken;
        while (StringUtil.isWhitespace(_xml.charAt(beginToken_))) {
            beginToken_++;
        }
        return beginToken_;
    }

    private static int tryFindAttr(String _xml, String _attribute, boolean _attrValue, int _lastIndex, int _foundAttr) {
        int foundAttr_ = _foundAttr;
        if (foundAttr_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return _lastIndex;
        }
        if (_attrValue) {
            foundAttr_ += _attribute.length();
            while (!DocumentAttribute.isDel(_xml.charAt(foundAttr_), QUOT, APOS)) {
                foundAttr_++;
            }
            foundAttr_++;
        }
        return foundAttr_;
    }

    public static CustList<Node> getDeepChildNodesDocOrder(Node _root, Node _until) {
        CustList<Node> nodes_ = new CustList<Node>();
        if (_root == null) {
            return nodes_;
        }
        return getDeepChildNodesDocOrder(_root, _until, nodes_);
    }

    private static CustList<Node> getDeepChildNodesDocOrder(Node _root, Node _until, CustList<Node> _nodes) {
        Node en_ = _root;
        while (en_ != null) {
            if (en_ != _until) {
                _nodes.add(en_);
            }
            Node n_ = en_.getFirstChild();
            if (en_ == _until) {
                n_ = _root;
            }
            if (n_ != null||en_ == _root) {
                en_ = FullNode.nextSib(_root, n_);
                continue;
            }
            en_ = next(en_,_root);
        }
        return _nodes;
    }

    private static Node next(Node _en, Node _root) {
        Node en_ = _en;
        while (en_ != null) {
            Node n_ = en_.getNextSibling();
            if (n_ != null) {
                en_ = n_;
                break;
            }
            n_ = en_.getParentNode();
            en_ = FullNode.nextSib(_root, n_);
        }
        return en_;
    }

    public static String indent(String _xml) {
        int index_ = IndexConstants.FIRST_INDEX;
        int indentation_ = IndexConstants.SIZE_EMPTY;
        StringBuilder indented_ = new StringBuilder();
        while (index_ < _xml.length()) {
            int i_ = index_;
            boolean change_ = true;
            if (_xml.charAt(i_) != LT) {
                change_ = false;
                i_ = goTo(_xml, i_, LT);
                i_--;
            } else {
                i_ = goTo(_xml, i_, GT);
            }
            IndentType ind_ = IndentType.MIDDLE;
            if (change_) {
                ind_ = getIndentType(_xml, index_, i_);
            }
            indentation_ = incrIndent(_xml, index_, indentation_, indented_, i_, ind_);
            index_ = i_ + 1;
        }
        indented_.deleteCharAt(indented_.length() - 1);
        return indented_.toString();
    }

    public static String indentWithoutTextNode(String _xml) {
        int index_ = IndexConstants.FIRST_INDEX;
        int indentation_ = IndexConstants.SIZE_EMPTY;
        StringBuilder indented_ = new StringBuilder();
        while (index_ < _xml.length()) {
            int i_ = index_;
            i_ = goTo(_xml, i_, GT);
            IndentType ind_ = getIndentType(_xml, index_, i_);
            indentation_ = incrIndent(_xml, index_, indentation_, indented_, i_, ind_);
            index_ = i_ + 1;
        }
        indented_.deleteCharAt(indented_.length() - 1);
        return indented_.toString();
    }

    private static IndentType getIndentType(String _xml, int _index, int _i) {
        IndentType ind_ = IndentType.MIDDLE;
        if (_xml.charAt(_index + 1) == SLASH) {
            ind_ = IndentType.END;
        } else {
            if (_xml.charAt(_i - 1) != SLASH) {
                ind_ = IndentType.BEGIN;
            }
        }
        return ind_;
    }

    private static int incrIndent(String _xml, int _index, int _indentation, StringBuilder _indented, int _i, IndentType _ind) {
        int indentation_ = _indentation;
        if (_ind == IndentType.END) {
            indentation_--;
        }
        addTab(indentation_, _indented);
        _indented.append(_xml, _index, _i + 1);
        _indented.append(LINE_RETURN);
        if (_ind == IndentType.BEGIN) {
            indentation_++;
        }
        return indentation_;
    }

    private static void addTab(int _indentation, StringBuilder _indented) {
        for (int i = IndexConstants.FIRST_INDEX; i < _indentation; i++) {
            _indented.append(TAB);
        }
    }

    private static int goTo(String _xml, int _i, char _gt) {
        int i_ = _i;
        while (_xml.charAt(i_) != _gt) {
            i_++;
        }
        return i_;
    }

    public static int getIndexOfNodeOrAttribute(String _xml, Node _node, String _attribute) {
        return getIndexOfNodeOrAttribute(_xml, _node, _attribute, false);
    }

    public static StringMap<IntTreeMap<Integer>> getSpecialChars(String _html, Element _element) {
        StringMap<IntTreeMap<Integer>> encoded_;
        encoded_ = new StringMap<IntTreeMap<Integer>>();
        int index_ = getIndexOfNodeOrAttribute(_html, _element, EMPTY_STRING);
        int endHeader_ = _html.indexOf(GT, index_);
        int beginHeader_ = index_ + _element.getTagName().length();
        StringMap<AttributePart> attr_;
        attr_ = DocumentAttribute.getAttributes(_html, beginHeader_, endHeader_);
        for (EntryCust<String, AttributePart> e: attr_.entryList()) {
            encoded_.put(e.getKey(), getIndexesSpecChars(_html, e.getValue(), index_));
        }
        return encoded_;
    }

    private static IntTreeMap< Integer> getIndexesSpecChars(
            String _html, AttributePart _att, int _beginNode) {
        int begin_ = _att.getBegin();
        int end_ = _att.getEnd();
        int i_ = begin_;
        int delta_ = begin_ - _beginNode;
        IntTreeMap< Integer> indexes_;
        indexes_ = new IntTreeMap< Integer>();
        while (i_ < end_) {
            if (_html.charAt(i_) == ENCODED) {
                int beginEscaped_ = i_;
                i_++;
                i_ = goTo(_html, i_, END_ESCAPED);
                indexes_.put(beginEscaped_ - _beginNode - delta_, i_ - beginEscaped_);
            }
            i_++;
        }
        return indexes_;
    }

    public static
            ElementOffsetsNext getIndexesOfElementOrAttribute(
                    String _xml, ElementOffsetsNext _previous,
                    Element _node, int _tabWidth) {
        CalculNextIndexes calc_ = new CalculNextIndexes(_xml,_previous,_node);
        int len_ = _xml.length();
        while (calc_.getIndex() < len_) {
            if (!calc_.keep(_xml,_tabWidth)){
                break;
            }
        }
        return new ElementOffsetsNext(calc_.getTabsMap(), calc_.getOffsetsMap(), calc_.getMap(), calc_.getNextCol(), calc_.getEndHeader(), calc_.getNext(), calc_.getFound() + 1);
    }

    public static RowCol getOffset(String _attribute, StringMap<RowCol> _attributes, StringMap<IntTreeMap<Integer>> _specialChars, int _offset, StringMap<Ints> _offsets, StringMap<Ints> _tabs, int _tabWidth) {
        RowCol offset_ = _attributes.getVal(_attribute);
        if (_attribute.isEmpty()) {
            return offset_;
        }
        int delta_ = 0;
        IntTreeMap< Integer> esc_ = _specialChars.getVal(_attribute);
        int nbIndexes_ = getIndexesCount(_offset, esc_);
        for (int i = 0; i < nbIndexes_; i++) {
            delta_ += esc_.getValue(i);
        }
        delta_ += _offset;
        Ints offsets_ = _offsets.getVal(_attribute);
        Ints tabs_ = _tabs.getVal(_attribute);
        RowCol ret_ = new RowCol();
        boolean exist_ = false;
        int index_ = IndexConstants.FIRST_INDEX;
        if (!offsets_.isEmpty()) {
            int i_ = IndexConstants.FIRST_INDEX;
            while (i_ < offsets_.size()) {
                if (offsets_.get(i_) > delta_) {
                    break;
                }
                i_++;
            }
            if (i_ > IndexConstants.FIRST_INDEX) {
                exist_ = true;
                index_ = offsets_.get(i_ - 1);
            }
            ret_.setRow(offset_.getRow()+i_);
        } else {
            ret_.setRow(offset_.getRow());
        }
        return buildRowCol(_tabWidth, offset_, delta_, tabs_, ret_, exist_, index_);
    }

    private static RowCol buildRowCol(int _tabWidth, RowCol _offset, int _delta, Ints _tabs, RowCol _ret, boolean _exist, int _index) {
        int nb_ = 0;
        for (int i = _index; i < _delta; i++) {
            if (_tabs.containsObj(i)) {
                nb_ += _tabWidth;
            } else {
                nb_++;
            }
        }
        if (_exist) {
            _ret.setCol(nb_);
        } else {
            _ret.setCol(nb_+_offset.getCol());
        }
        return _ret;
    }

    private static int getIndexesCount(int _offset, IntTreeMap< Integer> _t) {
        int delta_ = 0;
        int count_ = 0;
        for (EntryCust<Integer, Integer> e: _t.entryList()) {
            if (e.getKey() - delta_ >= _offset) {
                return count_;
            }
            delta_ += e.getValue();
            count_++;
        }
        return count_;
    }

    public static Ints getIndexes(Node _node) {
        Node par_ = _node.getParentNode();
        Ints indexes_ = new Ints();
        if (par_ == null) {
            return indexes_;
        }
        Document doc_ = _node.getOwnerDocument();
        Element root_ = doc_.getDocumentElement();
        Node currentParent_ = par_;
        Node current_ = _node;
        while (current_ != root_) {
            int index_ = IndexConstants.FIRST_INDEX;
            for (Node c : currentParent_.getChildNodes()) {
                if (c == current_) {
                    indexes_.add(IndexConstants.FIRST_INDEX, index_);
                }
                index_++;
            }
            current_ = currentParent_;
            currentParent_ = currentParent_.getParentNode();
        }
        return indexes_;
    }

    public static RowCol getOffset(String _attr, ElementOffsetsNext _e, StringMap<IntTreeMap<Integer>> _s, int _offset, int _tabWidth) {
        StringMap<RowCol> attributes_ = _e.getAttributes();
        if (!attributes_.contains(_attr)) {
            return _e.getEndHeader();
        }
        return getOffset(_attr, attributes_, _s, _offset, _e.getOffsets(), _e.getTabs(), _tabWidth);
    }
}
