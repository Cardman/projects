package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.Parametrable;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.CustList;
import code.util.core.StringUtil;

public final class VarargOperation extends LeafOperation {

    private String className;

    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public VarargOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        MethodOperation m_ = getParent();
        if (isNotChildOfCallDyn(m_)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            varg_.setIndexFile(i_);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordVararg());
            _page.getLocalizer().addError(varg_);
            partOffsets.add(new PartOffset("<a title=\""+LinkageUtil.transform(varg_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsets.add(new PartOffset("</a>",i_+ _page.getKeyWords().getKeyWordVararg().length()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        if (!isFirstChildInParent()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            varg_.setIndexFile(i_);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordVararg());
            _page.getLocalizer().addError(varg_);
            partOffsets.add(new PartOffset("<a title=\""+LinkageUtil.transform(varg_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsets.add(new PartOffset("</a>",i_+ _page.getKeyWords().getKeyWordVararg().length()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        int afterLeftPar_ = className.indexOf(PAR_LEFT) + 1;
        String str_ = className.substring(afterLeftPar_, className.lastIndexOf(PAR_RIGHT));
        int off_ = StringUtil.getFirstPrintableCharIndex(str_);
        str_ = ResolvingTypes.resolveCorrectTypeAccessible(afterLeftPar_+off_,str_, _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        setResultClass(new AnaClassArgumentMatching(str_));
        className = str_;
        if (m_ instanceof RetrieveMethod) {
            RetrieveMethod f_ = (RetrieveMethod) m_;
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            for (int i = 0; i < len_; i++) {
                int gr_ = methodInfos_.get(i).size();
                CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                for (int j = 0; j < gr_; j++) {
                    MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                    if (exclude(methodInfo_)) {
                        continue;
                    }
                    newList_.add(methodInfo_);
                }
                methodInfos_.set(i,newList_);
            }
        }
        if (m_ instanceof RetrieveConstructor) {
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
            int len_ = methodInfos_.size();
            CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
            for (int i = 0; i < len_; i++) {
                ConstructorInfo methodInfo_ = methodInfos_.get(i);
                if (exclude(methodInfo_)) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            methodInfos_.clear();
            methodInfos_.addAllElts(newList_);
        }
        setSimpleArgument(new Argument());
    }

    private boolean exclude(Parametrable _methodInfo) {
        Identifiable geneFormatted_ = _methodInfo.getGeneFormatted();
        if (!_methodInfo.isVararg()) {
            return true;
        }
        String wc_ = geneFormatted_.getParametersType(geneFormatted_.getParametersTypesLength() - 1);
        return !StringUtil.quickEq(wc_, className);
    }

    public String getClassName() {
        return className;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
