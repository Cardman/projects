package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.fwd.opers.AnaTypeCheckContent;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.core.StringUtil;

public final class InstanceOfOperation extends AbstractUnaryOperation {

    private AnaTypeCheckContent typeCheckContent;
    private AnaResultPartType partOffsets = new AnaResultPartType();
    public InstanceOfOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        typeCheckContent = new AnaTypeCheckContent( getOperations().getOperators().firstKey());
        typeCheckContent.setClassName(getOperations().getOperators().firstValue());
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ typeCheckContent.getOffset(), _page);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        int begin_ = keyWordInstanceof_.length() + typeCheckContent.getClassName().indexOf(keyWordInstanceof_);
        String sub_ = typeCheckContent.getClassName().substring(begin_);
        int off_ = StringUtil.getFirstPrintableCharIndex(sub_);
        String compo_ = StringExpUtil.getQuickComponentBaseType(sub_).getComponent();
        boolean exact_ = compo_.contains(StringExpUtil.TEMPLATE_BEGIN);
        if (sub_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page, begin_ + off_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            typeCheckContent.setClassName(_page.getAliasObject());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            return;
        }
        partOffsets = ResolvingTypes.resolveCorrectType(begin_ + off_, sub_, exact_, _page);
        sub_ = partOffsets.getResult(_page);
        if (!exact_) {
            RootBlock r_ = _page.getAnaClassBody(StringExpUtil.getIdFromAllTypes(sub_));
            if (r_ != null) {
                sub_ = r_.getWildCardString();
            }
        }
        typeCheckContent.setClassName(sub_);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
    }

    public int getOffset() {
        return typeCheckContent.getOffset();
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    public AnaTypeCheckContent getTypeCheckContent() {
        return typeCheckContent;
    }
}
