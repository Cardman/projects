package code.sml.indexes;

import code.sml.DocumentAttribute;
import code.sml.Element;
import code.sml.RowCol;
import code.util.Ints;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class CalculNextIndexes {
    private static final String EMPTY_STRING = "";
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private static final char LT = 60;
    private static final char GT = 62;

    private static final char EQUALS = '=';
    private static final char SLASH = '/';

    private static final char TAB = '\t';

    private static final char LINE_RETURN = '\n';

    private int next = IndexConstants.INDEX_NOT_FOUND_ELT;
    private final StringMap<RowCol> map = new StringMap<RowCol>();
    private final StringMap<Ints> offsetsMap = new StringMap<Ints>();
    private Ints offsets = new Ints();
    private final StringMap<Ints> tabsMap = new StringMap<Ints>();
    private Ints tabs = new Ints();
    private int index;
    private final int nodeLen;
    private int secIndex;
    private boolean addAttribute;
    private int delimiter;
    private StringBuilder str = new StringBuilder();
    private int thirdIndex;
    private int offset;
    private final RowCol endHeader = new RowCol();
    private final RowCol nextCol = new RowCol();
    private RowCol rc = new RowCol();
    private int nbLineReturns;
    private final int minLine;
    private final int found;
    CalculNextIndexes(
            String _xml, ElementOffsetsNext _previous,
            Element _node) {
        int index_ = _previous.getNextElt();
        String nodeName_ = _node.getTagName();
        minLine = _previous.getNextCol().getRow();
        found = _xml.indexOf(StringUtil.concat(Character.toString(LT),nodeName_), index_);
        int indexLoc_ = _previous.getNextCol().getCol();
        for (int i = index_; i <= found; i++) {
            if (_xml.charAt(i) == LINE_RETURN) {
                nbLineReturns++;
                indexLoc_ = 0;
            } else {
                indexLoc_++;
            }
        }
        rc.setRow(nbLineReturns + minLine +1);
        rc.setCol(indexLoc_+1);
        map.put(EMPTY_STRING, rc);
        index = found +1;
        nodeLen = nodeName_.length();
        secIndex = 0;
        addAttribute = true;
        delimiter = -1;
        thirdIndex = indexLoc_;
        offset = 0;
    }

    private static void addPossibleSpace(Ints _offsets, Ints _tabs, int _offset, char _ch) {
        if (_ch == LINE_RETURN) {
            _offsets.add(_offset);
        } else {
            addPossibleTab(_tabs, _offset, _ch);
        }
    }

    private static void addPossibleTab(Ints _tabs, int _offset, char _ch) {
        if (_ch == TAB) {
            _tabs.add(_offset);
        }
    }

    private static void completeAttr(StringBuilder _str, char _ch) {
        if (!StringUtil.isWhitespace(_ch) && _ch != EQUALS) {
            _str.append(_ch);
        }
    }

    private static void processEndHeader(int _nbLineReturns, int _minLine, boolean _addAttribute, int _j, RowCol _endHeader) {
        if (_addAttribute) {
            _endHeader.setRow(_nbLineReturns + _minLine +1);
            _endHeader.setCol(_j +2);
        }
    }

    boolean keep(String _xml, int _tabWidth) {
        char ch_ = _xml.charAt(index);
        if (ch_ == GT) {
            processEndHeader(nbLineReturns, minLine, addAttribute, thirdIndex, endHeader);
            addAttribute = false;
        }
        if (addAttribute && secIndex > nodeLen) {
            addAttr(ch_);
        } else {
            if (ch_ == LT && _xml.charAt(index + 1) != SLASH) {
                next = index;
                nextCol.setRow(nbLineReturns + minLine);
                nextCol.setCol(thirdIndex);
                return false;
            }
        }
        return nextChar(_tabWidth, ch_);
    }

    private void addAttr(char _ch) {
        if (delimiter == -1) {
            if (DocumentAttribute.isDel(_ch, APOS, QUOT)) {
                delimiter = _ch;
            }
            if (delimiter == -1) {
                completeAttr(str, _ch);
            } else {
                rc = new RowCol();
                rc.setRow(nbLineReturns + minLine +1);
                rc.setCol(thirdIndex +2);
                offset = 0;
            }
        } else {
            if (_ch == delimiter) {
                delimiter = -1;
                map.put(str.toString(), rc);
                offsetsMap.put(str.toString(), offsets);
                offsets = new Ints();
                tabsMap.put(str.toString(), tabs);
                tabs = new Ints();
                str = new StringBuilder();
            } else {
                addPossibleSpace(offsets, tabs, offset, _ch);
            }
            offset++;
        }
    }

    private boolean nextChar(int _tabWidth, char _ch) {
        secIndex++;
        if (_ch == LINE_RETURN) {
            nbLineReturns++;
            thirdIndex = 0;
        } else {
            thirdIndex++;
            if (_ch == TAB) {
                thirdIndex += _tabWidth -1;
            }
        }
        index++;
        return true;
    }

    int getIndex() {
        return index;
    }

    int getNext() {
        return next;
    }

    StringMap<RowCol> getMap() {
        return map;
    }

    StringMap<Ints> getOffsetsMap() {
        return offsetsMap;
    }

    StringMap<Ints> getTabsMap() {
        return tabsMap;
    }

    RowCol getEndHeader() {
        return endHeader;
    }

    RowCol getNextCol() {
        return nextCol;
    }

    int getFound() {
        return found;
    }
}
