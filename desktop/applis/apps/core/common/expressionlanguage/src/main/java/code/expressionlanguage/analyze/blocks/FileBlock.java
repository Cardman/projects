package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.expressionlanguage.analyze.files.StringComment;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.opers.SwitchOperation;
import code.expressionlanguage.common.AbstractFileEscapedCalc;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorList;
import code.expressionlanguage.common.FileMetricsCore;
import code.expressionlanguage.common.MessagesCdmBase;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class FileBlock extends BracedBlock implements ImportingBlock {
    private static final char LINE_RETURN = '\n';
    private static final char CARR_RETURN = '\r';
    private static final char TAB = '\t';
    private final Ints binChars = new Ints();
    private final FileMetricsCore metricsCore;

    private final Ints beginComments = new Ints();
    private final Ints endComments = new Ints();
    private final CustList<SegmentStringPart> stringParts = new CustList<SegmentStringPart>();
    private final StringList imports = new StringList();

    private final Ints importsOffset = new Ints();

    private final String fileName;

    private final boolean predefined;

    private final GraphicErrorList errorsFiles = new GraphicErrorList();

    private String content;
    private final StringList basePackages = new StringList();
    private final StringList packages = new StringList();
    private int length;
    private int numberFile;
    private final AbstractFileEscapedCalc fileEscapedCalc;
    private final CustList<RootBlock> allFoundTypes = new CustList<RootBlock>();
    private final CustList<OperatorBlock> allOperators = new CustList<OperatorBlock>();
    private final CustList<AnonymousLambdaOperation> allAnonymousLambda = new CustList<AnonymousLambdaOperation>();
    private final CustList<SwitchOperation> allSwitchMethods = new CustList<SwitchOperation>();

    public FileBlock(int _offset, boolean _predefined, String _fileName, AbstractFileEscapedCalc _fileEscapedCalc) {
        super(_offset);
        metricsCore = new FileMetricsCore(new Ints(),new Ints());
        predefined = _predefined;
        fileName = _fileName;
        fileEscapedCalc = _fileEscapedCalc;
    }

    public static String name(FileBlock _f) {
        if (_f == null) {
            return "";
        }
        return _f.getFileName();
    }

    public static int number(FileBlock _f) {
        if (_f == null) {
            return -1;
        }
        return _f.getNumberFile();
    }
    public AbstractFileEscapedCalc getFileEscapedCalc() {
        return fileEscapedCalc;
    }

    public static StringMap<String> errors(AnalyzedPageEl _analyzing) {
        return LinkageUtil.errors(_analyzing);
    }

    public void processLinesTabsWithError(String _file, AnalyzedPageEl _page) {
        metrics(_file);
        checkErrors(_page);
    }

    public void checkErrors(AnalyzedPageEl _page) {
        if (!getBinChars().isEmpty()) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setIndexFile(getBinChars().first());
            d_.setFile(this);
            StringList badCharsStr_ = new StringList();
            for (int i: getBinChars()) {
                badCharsStr_.add(Long.toString(content.charAt(i)));
            }
            badCharsStr_.sort();
            badCharsStr_.removeDuplicates();
            //first bad character
            d_.buildError(_page.getAnalysisMessages().getIllegalCharacter(),
                    StringUtil.join(badCharsStr_,ExportCst.SEP_CHAR));
            _page.addLocError(d_);
            for (int i: getBinChars()) {
                d_.setIndexFile(i);
                GraphicErrorInterpret g_ = new GraphicErrorInterpret(d_,i);
                g_.setLength(1);
                errorsFiles.getLi().add(g_);
            }
        }
    }

    public void metrics(String _file) {
        content = _file;
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _file.length();
        length = len_;
        getLineReturns().add(-1);
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
                    getBinChars().add(i_);
                }
            }
            i_++;
        }
    }

    public StringComment stringComment(CustList<CommentDelimiters> _comments) {
        return new StringComment(content, _comments);
    }
    public void metrics(StringComment _stringComment) {
        getBeginComments().addAllElts(_stringComment.getBeginComments());
        getEndComments().addAllElts(_stringComment.getEndComments());
        getStringParts().addAllElts(_stringComment.getStringParts());
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

    public CustList<SegmentStringPart> getStringParts() {
        return stringParts;
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
        return fileName+ MessagesCdmBase.EXT;
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

    public CustList<RootBlock> getAllFoundTypes() {
        return allFoundTypes;
    }

    public CustList<OperatorBlock> getAllOperators() {
        return allOperators;
    }

    public CustList<AnonymousLambdaOperation> getAllAnonymousLambda() {
        return allAnonymousLambda;
    }

    public CustList<SwitchOperation> getAllSwitchMethods() {
        return allSwitchMethods;
    }
}
