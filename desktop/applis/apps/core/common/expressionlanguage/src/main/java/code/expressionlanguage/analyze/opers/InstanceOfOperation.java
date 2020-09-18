package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.*;

public final class InstanceOfOperation extends AbstractUnaryOperation {

    private String className;
    private int offset;
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public InstanceOfOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        KeyWords keyWords_ = _conf.getAnalyzing().getKeyWords();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        int begin_ = keyWordInstanceof_.length() + className.indexOf(keyWordInstanceof_);
        String sub_ = className.substring(begin_);
        int off_ = StringList.getFirstPrintableCharIndex(sub_);
        String compo_ = StringExpUtil.getQuickComponentBaseType(sub_).getComponent();
        boolean exact_ = compo_.contains(Templates.TEMPLATE_BEGIN);
        if (sub_.isEmpty()) {
            int rc_ = page_.getLocalizer().getCurrentLocationIndex() + begin_ + off_;
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getEmptyType());
            page_.getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            className = page_.getStandards().getAliasObject();
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            return;
        }
        sub_ = ResolvingImportTypes.resolveCorrectType(_conf, begin_ + off_, sub_, exact_);
        partOffsets.addAllElts(page_.getCurrentParts());
        if (!exact_) {
            RootBlock r_ = page_.getAnaClassBody(StringExpUtil.getIdFromAllTypes(sub_));
            if (r_ != null) {
                sub_ = r_.getWildCardString();
            }
        }
        className = sub_;
        setResultClass(new AnaClassArgumentMatching(stds_.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
    }

    public String getClassName() {
        return className;
    }

    public int getOffset() {
        return offset;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
