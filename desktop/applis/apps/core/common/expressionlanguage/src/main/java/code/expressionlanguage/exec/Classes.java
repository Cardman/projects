package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.AbstractTypePairHash;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.ExecOperatorBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.dbg.DebugMapping;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.AbsContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public final class Classes {

    private final ClassesCommon common;
    private final StringMap<ExecRootBlock> classesBodies;

    private String iteratorVarCust;
    private String hasNextVarCust;
    private String nextVarCust;
    private String iteratorTableVarCust;
    private String hasNextPairVarCust;
    private String nextPairVarCust;
    private String firstVarCust;
    private String secondVarCust;
    private CustList<ExecOperationNode> expsIteratorCust;
    private CustList<ExecOperationNode> expsHasNextCust;
    private CustList<ExecOperationNode> expsNextCust;
    private CustList<ExecOperationNode> expsIteratorTableCust;
    private CustList<ExecOperationNode> expsHasNextPairCust;
    private CustList<ExecOperationNode> expsNextPairCust;
    private CustList<ExecOperationNode> expsFirstCust;
    private CustList<ExecOperationNode> expsSecondCust;
    private final CustList<ExecOperatorBlock> sortedOperators = new CustList<ExecOperatorBlock>();
    private ExecTypeFunction seedDoubleGeneratorPair;
    private ExecTypeFunction seedGeneratorPair;
    private final CustList<ClassMetaInfo> classMetaInfos = new CustList<ClassMetaInfo>();
    private String keyWordValue = "";
    private final AbstractTypePairHash checker;
    private final DebugMapping debugMapping;
    public Classes(AbstractTypePairHash _checker){
        this(_checker,new DebugMapping(false));
    }
    public Classes(AbstractTypePairHash _checker, DebugMapping _d){
        common = new ClassesCommon();
        classesBodies = new StringMap<ExecRootBlock>();
        checker = _checker;
        debugMapping = _d;
    }

    public DebugMapping getDebugMapping() {
        return debugMapping;
    }

    public AbstractTypePairHash getChecker() {
        return checker;
    }

    public StringMap<String> getResources() {
		return getCommon().getResources();
	}

    public static void tryInit(ResultContext _res) {
        ContextEl ctx_ = _res.getContext();
        if (ctx_ != null) {
            ExecClassesUtil.tryInitStaticlyTypes(ctx_, _res.getForwards().getOptions());
        }
    }
    public static void fwdGenerate(ResultContext _res, AbsContextGenerator _gene) {
        AnalyzedPageEl fwd_ = _res.getPageEl();
        if (fwd_.notAllEmptyErrors()) {
            return;
        }
        Forwards f_ = _res.getForwards();
        ForwardInfos.generalForward(fwd_,f_);
        ContextEl ctx_ = _gene.gene(f_);
        forwardAndClear(ctx_);
        _res.setContext(ctx_);
    }
    public static void forwardAndClear(ContextEl _context) {
        _context.forwardAndClear();
    }

    public static AnalyzedPageEl validateWithoutInit(StringMap<String> _files, AnalyzedPageEl _page) {
        if (_page.notAllEmptyErrors()) {
            //all standards errors are logged here
            return _page;
        }
        AnalyzedPageEl copy_ = ClassesUtil.buildAllBracesBodies(_files, _page);
        postValidate(copy_);
        return copy_;
    }

    public static void postValidate(AnalyzedPageEl _page) {
        _page.setCustomAna(true);
        ClassesUtil.checkImpls(_page);
        if (_page.isGettingErrors()) {
            _page.getToStringOwners().add(_page.getAliasObject());
            _page.getRandCodeOwners().add(_page.getAliasObject());
            for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getToStr().entryList()) {
                String fullName_ = e.getKey().getFullName();
                _page.getToStringOwners().add(fullName_);
            }
            for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getRandCodes().entryList()) {
                String fullName_ = e.getKey().getFullName();
                _page.getRandCodeOwners().add(fullName_);
            }
            for (EntryCust<String, FileBlock> f: _page.getPreviousFilesBodies().entryList()) {
                FileBlock content_ = f.getValue();
                _page.getErrors().putFile(content_);
            }
            ReportedMessages messages_ = _page.getMessages();
            messages_.setErrors(FileBlock.errors(_page));
        }
    }


    public CustList<ExecOperatorBlock> getSortedOperators() {
        return sortedOperators;
    }

    public CustList<ExecRootBlock> getClassBodies() {
        return classesBodies.values();
    }

    public ExecRootBlock getClassBody(String _className) {
        return classesBodies.getVal(_className);
    }


    public Struct getStaticField(ClassField _clField, String _returnType, ContextEl _context) {
        StringMap<StringMap<Struct>> staticFields_ = getStaticFields();
        Struct strInit_ = NullStruct.def(NumParsers.getStaticField(_clField, staticFields_));
        if (strInit_ != NullStruct.DEF_VALUE) {
            return strInit_;
        }
        return ExecClassArgumentMatching.defaultValue(_returnType, _context);
    }

    public StringMap<StringMap<Struct>> getStaticFields() {
        return getCommon().getStaticFields();
    }

    public ClassesCommon getCommon() {
        return common;
    }

    public String getIteratorVarCust() {
        return iteratorVarCust;
    }

    public void setIteratorVarCust(String _iteratorVarCust) {
        iteratorVarCust = _iteratorVarCust;
    }

    public String getHasNextVarCust() {
        return hasNextVarCust;
    }

    public void setHasNextVarCust(String _hasNextVarCust) {
        hasNextVarCust = _hasNextVarCust;
    }

    public String getNextVarCust() {
        return nextVarCust;
    }

    public void setNextVarCust(String _nextVarCust) {
        nextVarCust = _nextVarCust;
    }

    public String getIteratorTableVarCust() {
        return iteratorTableVarCust;
    }
    public void setIteratorTableVarCust(String _iteratorTableVarCust) {
        iteratorTableVarCust = _iteratorTableVarCust;
    }
    public String getHasNextPairVarCust() {
        return hasNextPairVarCust;
    }
    public void setHasNextPairVarCust(String _hasNextPairVarCust) {
        hasNextPairVarCust = _hasNextPairVarCust;
    }
    public String getNextPairVarCust() {
        return nextPairVarCust;
    }
    public void setNextPairVarCust(String _nextPairVarCust) {
        nextPairVarCust = _nextPairVarCust;
    }
    public String getFirstVarCust() {
        return firstVarCust;
    }
    public void setFirstVarCust(String _firstVarCust) {
        firstVarCust = _firstVarCust;
    }
    public String getSecondVarCust() {
        return secondVarCust;
    }
    public void setSecondVarCust(String _secondVarCust) {
        secondVarCust = _secondVarCust;
    }
    public CustList<ExecOperationNode> getExpsIteratorCust() {
        return expsIteratorCust;
    }

    public void setExpsIteratorCust(CustList<ExecOperationNode> _expsIteratorCust) {
        expsIteratorCust = _expsIteratorCust;
    }

    public CustList<ExecOperationNode> getExpsHasNextCust() {
        return expsHasNextCust;
    }

    public void setExpsHasNextCust(CustList<ExecOperationNode> _expsHasNextCust) {
        expsHasNextCust = _expsHasNextCust;
    }

    public CustList<ExecOperationNode> getExpsNextCust() {
        return expsNextCust;
    }

    public void setExpsNextCust(CustList<ExecOperationNode> _expsNextCust) {
        expsNextCust = _expsNextCust;
    }
    public CustList<ExecOperationNode> getExpsIteratorTableCust() {
        return expsIteratorTableCust;
    }
    public void setExpsIteratorTableCust(
            CustList<ExecOperationNode> _expsIteratorTableCust) {
        expsIteratorTableCust = _expsIteratorTableCust;
    }
    public CustList<ExecOperationNode> getExpsHasNextPairCust() {
        return expsHasNextPairCust;
    }
    public void setExpsHasNextPairCust(CustList<ExecOperationNode> _expsHasNextPairCust) {
        expsHasNextPairCust = _expsHasNextPairCust;
    }
    public CustList<ExecOperationNode> getExpsNextPairCust() {
        return expsNextPairCust;
    }
    public void setExpsNextPairCust(CustList<ExecOperationNode> _expsNextPairCust) {
        expsNextPairCust = _expsNextPairCust;
    }
    public CustList<ExecOperationNode> getExpsFirstCust() {
        return expsFirstCust;
    }
    public void setExpsFirstCust(CustList<ExecOperationNode> _expsFirstCust) {
        expsFirstCust = _expsFirstCust;
    }
    public CustList<ExecOperationNode> getExpsSecondCust() {
        return expsSecondCust;
    }
    public void setExpsSecondCust(CustList<ExecOperationNode> _expsSecondCust) {
        expsSecondCust = _expsSecondCust;
    }

    public StringMap<ExecTypeFunction> getToStringMethodsToCallBodies() {
        return getCommon().getToStringMethodsToCallBodies();
    }

    public StringMap<ExecTypeFunction> getRandCodeMethodsToCallBodies() {
        return getCommon().getRandCodeMethodsToCallBodies();
    }
    public StringMap<ExecRootBlock> getClassesBodies() {
        return classesBodies;
    }

    public CustList<ClassMetaInfo> getClassMetaInfos() {
        return classMetaInfos;
    }

    public ExecTypeFunction getSeedGeneratorPair() {
        return seedGeneratorPair;
    }

    public void setSeedGeneratorPair(ExecTypeFunction _seedGeneratorPair) {
        this.seedGeneratorPair = _seedGeneratorPair;
    }


    public ExecTypeFunction getSeedDoubleGeneratorPair() {
        return seedDoubleGeneratorPair;
    }

    public void setSeedDoubleGeneratorPair(ExecTypeFunction _seedDoubleGeneratorPair) {
        this.seedDoubleGeneratorPair = _seedDoubleGeneratorPair;
    }

    public String getKeyWordValue() {
        return keyWordValue;
    }

    public void setKeyWordValue(String _keyWordValue) {
        this.keyWordValue = _keyWordValue;
    }
}
