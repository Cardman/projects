package code.formathtml.render;

import code.util.CustList;

public final class FindNextElement {

    private StringBuilder line = new StringBuilder();
    private CustList<MetaSearchableLabel> labels = new CustList<MetaSearchableLabel>();
    private int index;
    private MetaSearchableLabel label;
    private int beginLabel;
    private int offset;
    private int end;
    private MetaDocument document;
    private boolean setup;

    public FindNextElement(MetaDocument _document) {
        document = _document;
    }
    public void next(String _text) {
        int row_ = 0;
        int group_ = 0;
        MetaComponent cur_ = document.getRoot();
        if (label != null) {
            row_ = label.getRowGroup();
            group_ = label.getPartGroup();
            setResults(label, _text);
            if (setup) {
                return;
            }
            MetaComponent next_ = getNextElement(label);
            if (next_ == null) {
                label = null;
                reset();
                return;
            }
            if (next_ instanceof MetaSearchableLabel) {
                MetaSearchableLabel l_ = (MetaSearchableLabel) next_;
                if (l_.getPartGroup() != group_) {
                    group_ = l_.getPartGroup();
                    row_ = l_.getRowGroup();
                    reset();
                    cur_ = l_;
                } else if (l_.getRowGroup() != row_) {
                    row_ = l_.getRowGroup();
                    reset();
                    cur_ = l_;
                }
            }
        }
        while (true) {
            boolean keep_ = true;
            while (true) {
                if (cur_ instanceof MetaSearchableLabel) {
                    MetaSearchableLabel l_ = (MetaSearchableLabel) cur_;
                    labels.add(l_);
                    line.append(l_.getText());
                    setResults(l_, _text);
                    if (setup) {
                        return;
                    }
                }
                MetaComponent next_ = getNextElement(cur_);
                if (next_ == null) {
                    keep_ = false;
                    break;
                }
                if (next_ instanceof MetaSearchableLabel) {
                    MetaSearchableLabel l_ = (MetaSearchableLabel) next_;
                    if (l_.getPartGroup() != group_) {
                        group_ = l_.getPartGroup();
                        row_ = l_.getRowGroup();
                        reset();
                        break;
                    } else if (l_.getRowGroup() != row_) {
                        row_ = l_.getRowGroup();
                        reset();
                        break;
                    }
                }
                cur_ = next_;
            }
            if (!keep_) {
                break;
            }
        }
    }
    private void setResults(MetaSearchableLabel _label, String _text) {
        int index_ = line.indexOf(_text, index);
        setup = false;
        if (index_ > -1) {
            setup = true;
            int relIndex_ = 0;
            int relIndexEnd_ = 0;
            int len_ = labels.size();
            int lastIndex_ = 0;
            for (MetaSearchableLabel l: labels) {
                relIndexEnd_+= l.getText().length();
            }
            for (int i = 0; i < len_; i++) {
                lastIndex_ = i;
                int lenLoc_ = labels.get(i).getText().length();
                relIndex_ += lenLoc_;
                if (index_ < relIndex_) {
                    lastIndex_--;
                    relIndex_ -= lenLoc_;
                    break;
                }
            }
            lastIndex_++;
            beginLabel = lastIndex_;
            offset = index_ - relIndex_;
            end = _label.getText().length() - (relIndexEnd_ - index_ - _text.length());
            index = index_ + _text.length();
            label = _label;
        }
    }
    private void reset() {
        line.delete(0, line.length());
        labels.clear();
        index = 0;
    }
    private MetaComponent getNextElement(MetaComponent _current) {
        if (_current instanceof MetaContainer) {
            MetaContainer cont_ = (MetaContainer) _current;
            MetaComponent ch_ = cont_.getFirstChild();
            if (ch_ != null) {
                return ch_;
            }
        }
        MetaComponent next_ = getNextSibling(_current);
        if (next_ != null) {
            return next_;
        }
        MetaContainer par_ = _current.getParent();
        if (par_ == document.getRoot()) {
            return null;
        }
        next_ = getNextSibling(par_);
        while (next_ == null) {
            MetaContainer grandPar_ = par_.getParent();
            if (grandPar_ == document.getRoot()) {
                return null;
            }
            next_ = getNextSibling(grandPar_);
            par_ = grandPar_;
        }
        return next_;
    }
    private static MetaComponent getNextSibling(MetaComponent _current) {
        MetaContainer cont_ = _current.getParent();
        CustList<MetaComponent> ch_ = cont_.getChildren();
        int len_ = ch_.size();
        int index_ = -1;
        for (int i = 0; i < len_; i++) {
            MetaComponent c_ = ch_.get(i);
            if (c_ == _current) {
                index_ = i + 1;
                break;
            }
        }
        if (index_ >= len_) {
            return null;
        }
        return ch_.get(index_);
    }
    public StringBuilder getLine() {
        return line;
    }
    public void setLine(StringBuilder _line) {
        line = _line;
    }
    public CustList<MetaSearchableLabel> getLabels() {
        return labels;
    }
    public void setLabels(CustList<MetaSearchableLabel> _labels) {
        labels = _labels;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int _index) {
        index = _index;
    }
    public MetaSearchableLabel getLabel() {
        return label;
    }
    public void setLabel(MetaSearchableLabel _label) {
        label = _label;
    }
    public int getBeginLabel() {
        return beginLabel;
    }
    public void setBeginLabel(int _beginLabel) {
        beginLabel = _beginLabel;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int _offset) {
        offset = _offset;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int _end) {
        end = _end;
    }
    public MetaDocument getDocument() {
        return document;
    }
    public void setDocument(MetaDocument _document) {
        document = _document;
    }
}
