package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.util.*;

public final class FileBlock extends BracedBlock implements ImportingBlock {

    private Ints lineReturns = new Ints();
    private Ints leftSpaces = new Ints();
    private Ints tabulations = new Ints();

    private StringList imports = new StringList();

    private Ints importsOffset = new Ints();

    private String fileName;

    private boolean predefined;

    private int tabWidth;

    public FileBlock(OffsetsBlock _offset, boolean _predefined, int _tabWidth) {
        super(_offset);
        predefined = _predefined;
        tabWidth = _tabWidth;
    }
    public int getRowFile(int _sum) {
        int len_ = lineReturns.size();
        int i_ = 0;
        while (i_ < len_) {
            if (_sum < lineReturns.get(i_)) {
                break;
            }
            i_ += 2;
        }
        return i_/2;
    }
    public int getColFile(int _sum, int _row) {
        int r_ = _row * 2;
        int j_ = 0;
        if (r_ < lineReturns.size()) {
            j_ = leftSpaces.get(_row - 1);
            int begin_ = lineReturns.get(r_ - 1)+1;
            for (int j = begin_; j <= _sum; j++) {
                if (tabulations.containsObj(j)) {
                    j_ += tabWidth;
                    j_ -= j_ % tabWidth;
                } else {
                    j_++;
                }
            }
        }
        return j_;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public boolean isPredefined() {
        return predefined;
    }

    public Ints getLeftSpaces() {
        return leftSpaces;
    }

    public Ints getTabulations() {
        return tabulations;
    }
    public Ints getLineReturns() {
        return lineReturns;
    }

    @Override
    public StringList getImports() {
        return imports;
    }

    public Ints getImportsOffset() {
        return importsOffset;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public static StringMap<String> export(ContextEl _cont) {
        StringMap<String> files_ = new StringMap<String>();
        for (EntryCust<String,FileBlock> f: _cont.getClasses().getFilesBodies().entryList()) {
            FileBlock fileBlock_ = f.getValue();
            if (fileBlock_.isPredefined()) {
                continue;
            }
            String value_ = _cont.getClasses().getResources().getVal(f.getKey());
            CustList<PartOffset> listStr_ = fileBlock_.processReport(_cont);
            StringBuilder xml_ = new StringBuilder(value_.length());
            int i_ = value_.length() - 1;
            for (PartOffset t:listStr_.getReverse()) {
                int off_ = t.getOffset();
                if (t.getPart().isEmpty()) {
                    continue;
                }
                while (i_ >= off_) {
                    char ch_ = value_.charAt(i_);
                    insertTr(xml_, ch_);
                    i_--;
                }
                xml_.insert(0,t.getPart());
            }
            while (i_ >= 0) {
                char ch_ = value_.charAt(i_);
                insertTr(xml_, ch_);
                i_--;
            }
            files_.addEntry(f.getKey()+".html","<html><body><pre>"+xml_+"</pre></body></html>");
        }
        return files_;
    }

    private static void insertTr(StringBuilder _xml, char _ch) {
        if (_ch == '<') {
            _xml.insert(0,"&lt;");
        } else if (_ch == '>') {
            _xml.insert(0,"&gt;");
        } else if (_ch == '&') {
            _xml.insert(0,"&amp;");
        } else {
            _xml.insert(0, _ch);
        }
    }

    public CustList<PartOffset> processReport(ContextEl _cont){
        CustList<PartOffset> list_ = new CustList<PartOffset>();
        Block child_ = this;
        while (true) {
            child_.processReport(_cont,list_);
            Block firstChild_ = child_.getFirstChild();
            if (firstChild_ != null) {
                child_ = firstChild_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                Block nextSibling_ = child_.getNextSibling();
                if (nextSibling_ != null) {
                    child_ = nextSibling_;
                    break;
                }
                BracedBlock parent_ = child_.getParent();
                if (parent_ == this) {
                    stop_ = true;
                    break;
                }
                child_ = parent_;
            }
            if (stop_) {
                break;
            }
        }
        return list_;
    }
    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {

    }
}
