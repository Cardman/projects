package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.common.StringExpUtil;
import code.util.*;
import code.util.core.StringUtil;

public abstract class AbsBk {
    public static final String OR_EQ = "|=";
    public static final String AND_EQ = "&=";
    public static final String OR_LOG_EQ = "||=";
    public static final String OR_LOG_EQ_SHORT = "|||=";
    public static final String AND_LOG_EQ = "&&=";
    public static final String AND_LOG_EQ_SHORT = "&&&=";
    public static final String NULL_EQ = "??=";
    public static final String NULL_EQ_SHORT = "???=";
    public static final String XOR_EQ = "^=";
    public static final String PLUS_EQ = "+=";

    protected static final String VARARG = "...";

    protected static final String DOT = ".";

    protected static final String PAR_LEFT = "(";
    protected static final String PAR_RIGHT = ")";
    protected static final String EMPTY_STRING = "";

    private BracedBlock parent;
    private FileBlock file;
    private MemberCallingsBlock outerFct;

    private AbsBk nextSibling;

    private AbsBk previousSibling;

    private final int offset;

    private int begin;

    private int lengthHeader;

    private final Ints badIndexes = new Ints();
    private final Ints badIndexesGlobal = new Ints();
    private final StringList errorsBlock = new StringList();
    private final StringList errorsLabels = new StringList();
    private int blockNb;

    AbsBk(int _offset) {
        offset = _offset;
    }
    protected final void setParent(BracedBlock _b) {
        parent = _b;
    }
    public final int getOffset() {
        return offset;
    }

    public static boolean isAnnotBlock(AbsBk _bl) {
        if (_bl instanceof NamedCalledFunctionBlock) {
            return ((NamedCalledFunctionBlock)_bl).getTypeCall() == NameCalledEnum.ANNOTATION;
        }
        return false;
    }
    public static boolean isAnonBlock(AbsBk _bl) {
        if (_bl instanceof NamedCalledFunctionBlock) {
            return ((NamedCalledFunctionBlock)_bl).getTypeCall() == NameCalledEnum.ANONYMOUS;
        }
        return false;
    }
    public static boolean isOverBlock(AbsBk _bl) {
        if (_bl instanceof NamedCalledFunctionBlock) {
            return ((NamedCalledFunctionBlock)_bl).getTypeCall() == NameCalledEnum.OVERRIDABLE;
        }
        return false;
    }
    public void checkLabelReference(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        if (this instanceof BreakableBlock) {
            String label_ = ((BreakableBlock)this).getRealLabelInfo().getInfo();
            boolean wc_ = true;
            for (char c: label_.toCharArray()) {
                if (!StringExpUtil.isDollarWordChar(c)) {
                    wc_ = false;
                    break;
                }
            }
            if (!wc_) {
                FoundErrorInterpret bad_ = new FoundErrorInterpret();
                bad_.setFile(getFile());
                bad_.setIndexFile(((BreakableBlock)this).getRealLabelInfo().getOffset());
                //label_ len
                bad_.buildError(_page.getAnalysisMessages().getBadLabel());
                _page.addLocError(bad_);
                errorsLabels.add(bad_.getBuiltError());
            } else if (!label_.isEmpty()){
                if (StringUtil.contains(_anEl.getLabels(), label_)) {
                    FoundErrorInterpret dup_ = new FoundErrorInterpret();
                    dup_.setFile(getFile());
                    dup_.setIndexFile(((BreakableBlock)this).getRealLabelInfo().getOffset());
                    //label_ len
                    dup_.buildError(_page.getAnalysisMessages().getDuplicatedLabel());
                    _page.addLocError(dup_);
                    errorsLabels.add(dup_.getBuiltError());
                } else {
                    _anEl.getLabels().add(label_);
                }
            }
        }
    }

    public abstract void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page);

    protected static boolean tryBuildExpressionLanguageReadOnly(AbsBk _block, AnalyzedPageEl _page) {
        if (isVisitableBlock(_block)) {
            if (_block instanceof BuildableElMethod) {
                ((BuildableElMethod)_block).buildExpressionLanguageReadOnly(_page);
            }
            return true;
        }
        return processOther(_block, _page);
    }

    private static boolean processOther(AbsBk _block, AnalyzedPageEl _page) {
        if (!(_block instanceof RootBlock)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_block.getFile());
            un_.setIndexFile(_block.getOffset());
            //defined len first key words
            un_.buildError(_page.getAnalysisMessages().getUnexpectedBlockExp());
            _page.addLocError(un_);
            _block.addErrorBlock(un_.getBuiltError());
        }
        return false;
    }
    public static boolean isVisitableBlock(AbsBk _block) {
        return _block instanceof DoBlock||_block instanceof TryEval||_block instanceof FinallyEval||_block instanceof NullCatchEval||_block instanceof EmptyInstruction||_block instanceof ElseCondition||_block instanceof BuildableElMethod || _block instanceof UnclassedBracedBlock;
    }
    public final RootBlock retrieveParentType() {
        AbsBk p_ = this;
        while (!(p_ instanceof RootBlock)) {
            if (p_ == null) {
                return null;
            }
            p_ = p_.getParent();
        }
        return (RootBlock) p_;
    }
    public final FileBlock getFile() {
        return file;
    }

    public void setFile(FileBlock _file) {
        file = _file;
    }

    public MemberCallingsBlock getOuterFuntionInType() {
        AbsBk p_ = getParent();
        return getOuterFuntionInType(p_);
    }
    public MemberCallingsBlock getStrictOuterFuntion() {
        AbsBk p_ = getParent();
        return getOuterFuntion(p_);
    }

    public static MemberCallingsBlock getOuterFuntionInType(AbsBk _p) {
        AbsBk p_ = _p;
        while (p_ != null) {
            if (p_ instanceof RootBlock) {
                return null;
            }
            if (p_ instanceof MemberCallingsBlock) {
                return (MemberCallingsBlock)p_;
            }
            p_ = p_.getParent();
        }
        return null;
    }
    public static MemberCallingsBlock getOuterFuntion(AbsBk _p) {
        AbsBk p_ = _p;
        while (p_ != null) {
            if (p_ instanceof MemberCallingsBlock) {
                return (MemberCallingsBlock)p_;
            }
            p_ = p_.getParent();
        }
        return null;
    }
    public final AbsBk getPreviousSibling() {
        return previousSibling;
    }
    public abstract AbsBk getFirstChild();

    public final AbsBk getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(AbsBk _nextSibling) {
        nextSibling = _nextSibling;
    }
    final void setPreviousSibling(AbsBk _previousSibling) {
        previousSibling = _previousSibling;
    }

    public final BracedBlock getParent() {
        return parent;
    }

    public Ints getBadIndexes() {
        return badIndexes;
    }

    public Ints getBadIndexesGlobal() {
        return badIndexesGlobal;
    }

    public void addErrorBlock(String _err) {
        errorsBlock.add(_err);
    }

    public StringList getErrorsBlock() {
        return errorsBlock;
    }

    public StringList getErrorsLabels() {
        return errorsLabels;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int _begin) {
        this.begin = _begin;
    }

    public int getLengthHeader() {
        return lengthHeader;
    }

    public void setLengthHeader(int _lengthHeader) {
        this.lengthHeader = _lengthHeader;
    }

    public MemberCallingsBlock getOuterFct() {
        return outerFct;
    }

    public void setOuterFct(MemberCallingsBlock _outerFct) {
        outerFct = _outerFct;
    }

    public int getBlockNb() {
        return blockNb;
    }

    public void setBlockNb(int _blockNb) {
        this.blockNb = _blockNb;
    }
}
