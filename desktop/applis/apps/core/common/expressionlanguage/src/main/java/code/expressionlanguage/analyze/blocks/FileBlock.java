package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorList;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.common.FileMetricsCore;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class FileBlock extends BracedBlock implements ImportingBlock {
    private static final char LINE_RETURN = '\n';
    private static final char CARR_RETURN = '\r';
    private static final char TAB = '\t';
    private Ints binChars = new Ints();
    private final FileMetricsCore metricsCore;

    private Ints beginComments = new Ints();
    private Ints endComments = new Ints();

    private StringList imports = new StringList();

    private Ints importsOffset = new Ints();

    private final String fileName;

    private boolean predefined;

    private GraphicErrorList errorsFiles = new GraphicErrorList();

    private String content;
    private StringList basePackages = new StringList();
    private StringList packages = new StringList();
    private int length;
    private int numberFile;

    public FileBlock(OffsetsBlock _offset, boolean _predefined, String _fileName) {
        super(_offset);
        metricsCore = new FileMetricsCore(new Ints(),new Ints());
        predefined = _predefined;
        fileName = _fileName;
    }

    public static StringMap<String> errors(AnalyzedPageEl _analyzing) {
        return LinkageUtil.errors(_analyzing);
    }

    public void processLinesTabsWithError(String _file, AnalyzedPageEl _page) {
        content = _file;
        int i_ = IndexConstants.FIRST_INDEX;
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
                badCharsStr_.add(Long.toString(i));
            }
            //first bad character
            d_.buildError(_page.getAnalysisMessages().getIllegalCharacter(),
                    StringUtil.join(badCharsStr_,","));
            _page.addLocError(d_);
            for (int i: getBinChars()) {
                d_.setIndexFile(i);
                GraphicErrorInterpret g_ = new GraphicErrorInterpret(d_);
                g_.setLength(1);
                errorsFiles.getLi().add(g_);
            }
        }
    }

    public FileMetrics getMetrics(int _tabWidth) {
        return new FileMetrics(metricsCore,_tabWidth);
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
        return metricsCore.getTabulations();
    }

    public Ints getBinChars() {
        return binChars;
    }

    public Ints getLineReturns() {
        return metricsCore.getLineReturns();
    }

    public FileMetricsCore getMetricsCore() {
        return metricsCore;
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

    public String getRenderFileName() {
        return fileName+".html";
    }

    public StringList getAllPackages() {
        StringList pkgs_ = new StringList();
        for (String r: packages) {
            StringBuilder id_ = new StringBuilder();
            for (String p: StringUtil.splitChars(r, '.')) {
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

    public void setNumberFile(int _numberFile) {
        this.numberFile = _numberFile;
    }
}
