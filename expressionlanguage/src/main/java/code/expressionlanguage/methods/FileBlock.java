package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.util.*;

public final class FileBlock extends BracedBlock implements ImportingBlock {

    private Ints binChars = new Ints();
    private Ints lineReturns = new Ints();
    private Ints tabulations = new Ints();

    private Ints beginComments = new Ints();
    private Ints endComments = new Ints();

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
        int s_ = Math.max(0,_sum);
        while (i_ < len_) {
            if (s_ <= lineReturns.get(i_)) {
                break;
            }
            i_++;
        }
        return i_;
    }
    public int getColFile(int _sum, int _row) {
        int j_ = 0;
        int s_ = Math.max(0,_sum);
        int begin_ = lineReturns.get(_row - 1)+1;
        for (int j = begin_; j < s_; j++) {
            if (binChars.containsObj(j)) {
                continue;
            }
            if (tabulations.containsObj(j)) {
                j_ += tabWidth;
                j_ -= j_ % tabWidth;
            } else {
                j_++;
            }
        }
        return j_+1;
    }

    public boolean isPredefined() {
        return predefined;
    }

    public Ints getBeginComments() {
        return beginComments;
    }

    public Ints getEndComments() {
        return endComments;
    }

    public Ints getTabulations() {
        return tabulations;
    }

    public Ints getBinChars() {
        return binChars;
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

    public String getRenderFileName() {
        return fileName+".html";
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
        cssContent_ += ".g{background-color:lightgreen;}\n";
        cssContent_ += ".p{background-color:yellow;}\n";
        cssContent_ += ".q{background-color:lightyellow;}\n";
        cssContent_ += ".n{background-color:red;}\n";
        cssContent_ += ".s{color:blue;}\n";
        cssContent_ += ".c{color:grey;}\n";
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
            if (beginComments.containsObj(_index)) {
                _xml.insert(0, _ch);
                _xml.insert(0, "<span class=\"c\">");
                return;
            }
            if (endComments.containsObj(_index)) {
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
