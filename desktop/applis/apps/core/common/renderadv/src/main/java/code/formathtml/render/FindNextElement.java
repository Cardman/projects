package code.formathtml.render;

import code.util.*;

public final class FindNextElement {

    private final StringBuilder line = new StringBuilder();
    private final CustList<MetaSearchableContent> labels = new CustList<MetaSearchableContent>();
    private final IdList<MetaSearchableContent> allTxtParts;
    private int index;
    private MetaSearchableContent label;
    private boolean setup;
    private int row;
    private int group;
    private int form;
    private MetaSearchableContent cur;
    private final IdMap<MetaSearchableContent, CustList<SegmentPart>> segments = new IdMap<MetaSearchableContent, CustList<SegmentPart>>();

    public FindNextElement(IdList<MetaSearchableContent> _parts) {
        allTxtParts = _parts;
    }
    /**
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
            cur_ = next_;
        }
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
                label = null;
                reset();
                return;
            }
            if (!fetchedGroupRow(next_)) {
                cur_ = next_;
            }
        }
    }*/
    public void next(String _text) {
        cur = first();
        if (label != null) {
            setResults(label, _text);
            if (setup) {
                return;
            }
            MetaSearchableContent next_ = next(label);
            if (exit(next_)) {
                return;
            }
        }
        while (true) {
            if (cur.getText() != null) {
                labels.add(cur);
                line.append(cur.getText());
                setResults(cur, _text);
                if (setup) {
                    return;
                }
            }
            MetaSearchableContent next_ = next(cur);
            if (exit(next_)) {
                return;
            }
        }
    }
    private boolean exit(MetaSearchableContent _next) {
        if (_next == null) {
            label = null;
            reset();
            return true;
        }
        if (!fetchedGroupRow(_next)) {
            cur = _next;
        }
        return false;
    }
    private boolean fetchedGroupRow(MetaSearchableContent _meta) {
        if (_meta.getText() == null) {
            return false;
        }
        if (_meta.getFormGroup() != form) {
            form = _meta.getFormGroup();
            group = _meta.getPartGroup();
            row = _meta.getRowGroup();
            reset();
            return true;
        }
        if (_meta.getPartGroup() != group) {
            group = _meta.getPartGroup();
            row = _meta.getRowGroup();
            reset();
            return true;
        }
        if (_meta.getRowGroup() != row) {
            row = _meta.getRowGroup();
            reset();
            return true;
        }
        return false;
    }
    private void setResults(MetaSearchableContent _label, String _text) {
        int index_ = line.indexOf(_text, index);
        setup = false;
        if (index_ > -1) {
            setup = true;
            int relIndex_ = 0;
            int relIndexEnd_ = 0;
            int len_ = labels.size();
            int lastIndex_ = 0;
            for (MetaSearchableContent l: labels) {
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
                MetaSearchableContent l_ = labels.get(beginLabel_);
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
    private void addSegment(MetaSearchableContent _label, SegmentPart _seg) {
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

    private MetaSearchableContent first() {
        return allTxtParts.first();
    }

    private MetaSearchableContent next(MetaSearchableContent _c) {
        int ind_ = allTxtParts.indexOfObj(_c)+1;
        if (!allTxtParts.isValidIndex(ind_)) {
            return null;
        }
        return allTxtParts.get(ind_);
    }
    public IdMap<MetaSearchableContent, CustList<SegmentPart>> getSegments() {
        return segments;
    }

    public MetaSearchableContent getLabel() {
        return label;
    }

}
