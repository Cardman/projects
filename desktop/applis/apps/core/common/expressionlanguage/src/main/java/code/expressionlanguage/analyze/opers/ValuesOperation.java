package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvedIdType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaValuesContent;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.core.StringUtil;

public final class ValuesOperation extends LeafOperation {

    private final String className;
    private final AnaValuesContent valuesContent;

    private final CustList<AnaResultPartType> partOffsets = new CustList<AnaResultPartType>();

    public ValuesOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        StrTypes vs_ = _op.getValues();
        className = vs_.firstValue();
        valuesContent = new AnaValuesContent(0);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ valuesContent.getArgOffset(), _page);
        int leftPar_ = className.indexOf('(')+1;
        String sub_ = className.substring(leftPar_,className.lastIndexOf(')'));
        leftPar_ += StringUtil.getFirstPrintableCharIndex(sub_);
        ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(leftPar_, sub_.trim(), _page);
        RootBlock r_ = checkType(_page, this, resolvedIdType_, partOffsets, valuesContent);
        if (r_ == null) {
            return;
        }
        String ret_ = StringExpUtil.getPrettyArrayType(r_.getWildCardElement());
        setResultClass(new AnaClassArgumentMatching(ret_));
    }
    static RootBlock checkType(AnalyzedPageEl _page,OperationNode _current,ResolvedIdType _resolved,CustList<AnaResultPartType> _parts,AnaValuesContent _content){
        String glClass_ = _page.getGlobalClass();
        String clName_ = _resolved.getFullName();
        _parts.add(_resolved.getDels());
        RootBlock r_ = _page.getAnaClassBody(clName_);
        if (!(r_ instanceof EnumBlock)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //className len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    clName_);
            _page.getLocalizer().addError(un_);
            _current.addErr(un_.getBuiltError());
            String argClName_ = _page.getAliasObject();
            _current.setResultClass(new AnaClassArgumentMatching(argClName_));
            return null;
        }
        _content.setNumberEnum(r_.getNumberAll());
        String curClassBase_ = StringExpUtil.getIdFromAllTypes(glClass_);
        Accessed a_ = new Accessed(r_.getAccess(), r_.getPackageName(), r_.getParentType(), r_);
        if (!ContextUtil.canAccessType(curClassBase_, a_, _page)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_page);
            badAccess_.setFile(_page.getCurrentFile());
            //className len
            badAccess_.buildError(_page.getAnalysisMessages().getInaccessibleType(),
                    clName_,
                    curClassBase_);
            _page.getLocalizer().addError(badAccess_);
            _current.addErr(badAccess_.getBuiltError());
        }
        return r_;
    }

    public CustList<AnaResultPartType> getPartOffsets() {
        return partOffsets;
    }

    public AnaValuesContent getValuesContent() {
        return valuesContent;
    }
}
