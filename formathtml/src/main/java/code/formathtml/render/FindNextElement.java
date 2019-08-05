package code.formathtml.render;

import code.util.CustList;
import code.util.IdMap;

public final class FindNextElement {

    private StringBuilder line = new StringBuilder();
    private CustList<MetaSearchableLabel> labels = new CustList<MetaSearchableLabel>();
    private int index;
    private MetaSearchableLabel label;
    private MetaDocument document;
    private boolean setup;
    private int row = 0;
    private int group = 0;
    private IdMap<MetaSearchableLabel, CustList<SegmentPart>> segments = new IdMap<MetaSearchableLabel, CustList<SegmentPart>>();

    public FindNextElement(MetaDocument _document) {
        document = _document;
    }
    public void next(String _text) {
        row = 0;
        group = 0;
        IntComponent cur_ = document.getRoot();
        if (label != null) {
            row = label.getRowGroup();
            group = label.getPartGroup();
            setResults(label, _text);
            if (setup) {
                return;
            }
            IntComponent next_ = getNextElement(label);
            if (next_ == null) {
                label = null;
                reset();
                return;
            }
            fetchedGroupRow(next_);
            cur_ = next_;
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
                IntComponent next_ = getNextElement(cur_);
                if (next_ == null) {
                    keep_ = false;
                    break;
                }
                if (fetchedGroupRow(next_)) {
                    break;
                }
                cur_ = next_;
            }
            if (!keep_) {
                label = null;
                reset();
                break;
            }
        }
    }
    private boolean fetchedGroupRow(IntComponent _meta) {
        if (_meta instanceof MetaSearchableLabel) {
            MetaSearchableLabel l_ = (MetaSearchableLabel) _meta;
            if (l_.getPartGroup() != group) {
                group = l_.getPartGroup();
                row = l_.getRowGroup();
                reset();
                return true;
            } else if (l_.getRowGroup() != row) {
                row = l_.getRowGroup();
                reset();
                return true;
            }
        }
        return false;
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
            int beginLabel_ = lastIndex_;
            int offset_ = index_ - relIndex_;
            int end_ = _label.getText().length() - (relIndexEnd_ - index_ - _text.length());
            index = index_ + _text.length();
            label = _label;
            int lenMinusOne_ = len_ - 1;
            if (beginLabel_ + 1 <= lenMinusOne_) {
                MetaSearchableLabel l_ = labels.get(beginLabel_);
                addSegment(l_, new SegmentPart(offset_, l_.getText().length()));
                for (int i = beginLabel_ + 1; i < lenMinusOne_; i++) {
                    l_ = labels.get(i);
                    addSegment(l_, new SegmentPart(0, l_.getText().length()));
                }
                addSegment(label, new SegmentPart(0, end_));
            } else {
                addSegment(label, new SegmentPart(offset_, end_));
            }
        }
    }
    private void addSegment(MetaSearchableLabel _label, SegmentPart _seg) {
        CustList<SegmentPart> segs_ = segments.getVal(_label);
        if (segs_ == null) {
            segs_ = new CustList<SegmentPart>(_seg);
            segments.put(_label, segs_);
        } else {
            segs_.add(_seg);
        }
    }
    private void reset() {
        line.delete(0, line.length());
        labels.clear();
        segments.clear();
        index = 0;
    }
    private IntComponent getNextElement(IntComponent _current) {
        if (_current instanceof MetaContainer) {
            MetaContainer cont_ = (MetaContainer) _current;
            IntComponent ch_ = cont_.getFirstChildCompo();
            if (ch_ != null) {
                return ch_;
            }
        }
        IntComponent current_ = _current;
        while (true) {
            IntComponent next_ = current_.getNextSibling();
            if (next_ != null) {
                return next_;
            }
            IntComponent par_ = current_.getParentCompo();
            if (par_ == document.getRoot()) {
                return null;
            }
            current_ = par_;
        }
    }

    public IdMap<MetaSearchableLabel, CustList<SegmentPart>> getSegments() {
        return segments;
    }

    public MetaSearchableLabel getLabel() {
        return label;
    }

}
