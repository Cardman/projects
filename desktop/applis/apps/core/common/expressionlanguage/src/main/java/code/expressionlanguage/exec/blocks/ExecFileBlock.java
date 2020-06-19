package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.FileBlock;
import code.expressionlanguage.methods.ImportingBlock;
import code.util.*;

public final class ExecFileBlock extends ExecBracedBlock implements ImportingBlock {

    private Ints lineReturns;
    private Ints tabulations;

    private Ints beginComments;
    private Ints endComments;

    private StringList imports;

    private Ints importsOffset;

    private final String fileName;

    private boolean predefined;

    private int tabWidth;
    public ExecFileBlock(FileBlock _file, int _tabWidth) {
        super(_file.getOffset());
        predefined = _file.isPredefined();
        tabWidth = _tabWidth;
        lineReturns=_file.getLineReturns();
        tabulations=_file.getTabulations();
        beginComments = _file.getBeginComments();
        endComments = _file.getEndComments();
        imports = _file.getImports();
        importsOffset = _file.getImportsOffset();
        fileName = _file.getFileName();
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

    @Override
    public StringList getImports() {
        return imports;
    }

    public String getFileName() {
        return fileName;
    }

    public String getRenderFileName() {
        return fileName+".html";
    }

    public static StringMap<String> export(ContextEl _cont) {
        StringMap<String> files_ = new StringMap<String>();
        for (EntryCust<String,ExecFileBlock> f: _cont.getClasses().getFilesBodies().entryList()) {
            ExecFileBlock fileBlock_ = f.getValue();
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
                String part_ = t.getPart();
                if (part_.isEmpty()) {
                    continue;
                }
                int off_ = t.getOffset();
                while (i_ >= off_) {
                    char ch_ = value_.charAt(i_);
                    fileBlock_.insertTr(xml_, ch_,i_);
                    i_--;
                }
                xml_.insert(0, part_);
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
        cssContent_ += ".i{color:red;}\n";
        files_.addEntry("css/style.css",cssContent_);
        return files_;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = imports.size();
        for (int i = 0; i < len_; i++) {
            _parts.add(new PartOffset("<span class=\"i\">",importsOffset.get(i)));
            _parts.add(new PartOffset("</span>",importsOffset.get(i)+imports.get(i).length()));
        }
    }

    private void insertTr(StringBuilder _xml, char _ch, int _index) {
        String tr_ = transformText(_ch);
        if (beginComments.containsObj(_index)) {
            _xml.insert(0, tr_);
            _xml.insert(0, "<span class=\"c\">");
            return;
        }
        if (endComments.containsObj(_index)) {
            _xml.insert(0, "</span>");
            _xml.insert(0, tr_);
            return;
        }
        _xml.insert(0, tr_);
    }
    private static String transformText(char _ch) {
        if (_ch == '<') {
            return("&lt;");
        }
        if (_ch == '>') {
            return("&gt;");
        }
        if (_ch == '&') {
            return("&amp;");
        }
        return Character.toString(_ch);
    }
    public CustList<PartOffset> processReport(ContextEl _cont){
        CustList<PartOffset> list_ = new CustList<PartOffset>();
        ExecBlock child_ = this;
        while (true) {
            child_.processReport(_cont,list_);
            ExecBlock firstChild_ = child_.getFirstChild();
            if (firstChild_ != null) {
                child_ = firstChild_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                ExecBlock nextSibling_ = child_.getNextSibling();
                if (nextSibling_ != null) {
                    child_ = nextSibling_;
                    break;
                }
                ExecBracedBlock parent_ = child_.getParent();
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
}
