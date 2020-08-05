package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.errors.custom.GraphicErrorList;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.*;

public final class FileBlock extends BracedBlock implements ImportingBlock {
    private static final char LINE_RETURN = '\n';
    private static final char CARR_RETURN = '\r';
    private static final char TAB = '\t';
    private Ints binChars = new Ints();
    private Ints lineReturns = new Ints();
    private Ints tabulations = new Ints();

    private Ints beginComments = new Ints();
    private Ints endComments = new Ints();

    private StringList imports = new StringList();

    private Ints importsOffset = new Ints();

    private String fileName;

    private boolean predefined;

    private GraphicErrorList errorsFiles = new GraphicErrorList();

    private String content;
    private StringList basePackages = new StringList();
    private StringList packages = new StringList();
    private int length;
    private int numberFile;

    public FileBlock(OffsetsBlock _offset, boolean _predefined) {
        super(_offset);
        predefined = _predefined;
    }
    public boolean processLinesTabsWithError(ContextEl _context, String _file) {
        content = _file;
        int i_ = CustList.FIRST_INDEX;
        int len_ = _file.length();
        length = len_;
        getLineReturns().add(-1);
        boolean foundBinChar_ = false;
        Ints badChars_ = new Ints();
        while (i_ < len_) {
            char ch_ = _file.charAt(i_);
            if (ch_ < ' ') {
                if (ch_ == TAB) {
                    getTabulations().add(i_);
                } else if (ch_ == LINE_RETURN) {
                    getLineReturns().add(i_);
                } else if (ch_ == CARR_RETURN){
                    if (i_ + 1 >= len_ || _file.charAt(i_ + 1) != LINE_RETURN) {
                        getLineReturns().add(i_);
                    }
                } else {
                    badChars_.add((int)ch_);
                    getBinChars().add(i_);
                    foundBinChar_ = true;
                }
            }
            i_++;
        }
        if (foundBinChar_) {
            badChars_.sort();
            badChars_.removeDuplicates();
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setIndexFile(badChars_.first());
            d_.setFileName(fileName);
            StringList badCharsStr_ = new StringList();
            for (int i: badChars_) {
                badCharsStr_.add(Integer.toString(i));
            }
            //first bad character
            d_.buildError(_context.getAnalysisMessages().getIllegalCharacter(),
                    StringList.join(badCharsStr_,","));
            _context.addError(d_);
            for (int i: getBinChars()) {
                d_.setIndexFile(i);
                GraphicErrorInterpret g_ = new GraphicErrorInterpret(d_);
                g_.setLength(1);
                errorsFiles.add(g_);
            }
        }
        return foundBinChar_;
    }

    public FileMetrics getMetrics(int _tabWidth) {
        return new FileMetrics(lineReturns,tabulations,_tabWidth);
    }
    public GraphicErrorList getErrorsFiles() {
        return errorsFiles;
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

    public StringList getImports() {
        return imports;
    }

    public Ints getImportsOffset() {
        return importsOffset;
    }

    public String getContent() {
        return content;
    }

    public int getLength() {
        return length;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public String getRenderFileName() {
        return fileName+".html";
    }

    public StringList getAllPackages() {
        StringList pkgs_ = new StringList();
        for (String r: packages) {
            StringBuilder id_ = new StringBuilder();
            for (String p: StringList.splitChars(r, '.')) {
                id_.append(p);
                pkgs_.add(id_.toString());
                id_.append('.');
            }
        }
        pkgs_.removeDuplicates();
        return pkgs_;
    }

    public StringList getAllBasePackages() {
        StringList pkgs_ = new StringList(basePackages);
        pkgs_.removeDuplicates();
        return pkgs_;
    }

    public StringList getBasePackages() {
        return basePackages;
    }

    public StringList getPackages() {
        return packages;
    }

    public int getNumberFile() {
        return numberFile;
    }

    public void setNumberFile(int numberFile) {
        this.numberFile = numberFile;
    }
}
