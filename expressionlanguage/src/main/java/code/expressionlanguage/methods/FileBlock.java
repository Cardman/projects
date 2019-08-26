package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.util.*;

public final class FileBlock extends BracedBlock implements ImportingBlock {

    private Ints lineReturns = new Ints();
    private Ints leftSpaces = new Ints();
    private Ints tabulations = new Ints();
    private Ints beginStrings = new Ints();
    private Ints beginTexts = new Ints();
    private Ints beginChars = new Ints();
    private Ints endStrings = new Ints();
    private Ints endTexts = new Ints();
    private Ints endChars = new Ints();

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

    public Ints getBeginChars() {
        return beginChars;
    }

    public Ints getEndChars() {
        return endChars;
    }

    public Ints getBeginTexts() {
        return beginTexts;
    }

    public Ints getEndTexts() {
        return endTexts;
    }

    public Ints getBeginStrings() {
        return beginStrings;
    }

    public Ints getEndStrings() {
        return endStrings;
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
            String fileExp_ = f.getKey() + ".html";
            _cont.getCoverage().setCurrentFileName(fileExp_);
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
                    fileBlock_.insertTr(xml_, ch_,i_);
                    i_--;
                }
                xml_.insert(0,t.getPart());
            }
            while (i_ >= 0) {
                char ch_ = value_.charAt(i_);
                fileBlock_.insertTr(xml_, ch_,i_);
                i_--;
            }
            String rel_ = ElUtil.relativize(fileExp_,"css/style.css");
            String cssPart_ = "<head>" +
                    "<link href=\""+rel_+"\" rel=\"stylesheet\" type=\"text/css\"/>" +
                    "</head>";
            files_.addEntry(fileExp_,"<html>"+cssPart_+"<body><pre>"+xml_+"</pre></body></html>");
        }
        String cssContent_ = ".f{background-color:green;}\n";
        cssContent_ += ".p{background-color:yellow;}\n";
        cssContent_ += ".n{background-color:red;}\n";
        cssContent_ += ".s{color:blue;}\n";
        files_.addEntry("css/style.css",cssContent_);
        return files_;
    }

    private void insertTr(StringBuilder _xml, char _ch, int _index) {
        if (_ch == '<') {
            _xml.insert(0,"&lt;");
        } else if (_ch == '>') {
            _xml.insert(0,"&gt;");
        } else if (_ch == '&') {
            _xml.insert(0,"&amp;");
        } else {
            if (beginChars.containsObj(_index)) {
                _xml.insert(0, _ch);
                _xml.insert(0, "<span class=\"s\">");
                return;
            }
            if (endChars.containsObj(_index)) {
                _xml.insert(0, "</span>");
                _xml.insert(0, _ch);
                return;
            }
            if (beginStrings.containsObj(_index)) {
                _xml.insert(0, _ch);
                _xml.insert(0, "<span class=\"s\">");
                return;
            }
            if (endStrings.containsObj(_index)) {
                _xml.insert(0, "</span>");
                _xml.insert(0, _ch);
                return;
            }
            if (beginTexts.containsObj(_index)) {
                _xml.insert(0, _ch);
                _xml.insert(0, "<span class=\"s\">");
                return;
            }
            if (endTexts.containsObj(_index)) {
                _xml.insert(0, "</span>");
                _xml.insert(0, _ch);
                return;
            }
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
