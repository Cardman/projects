package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
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
        LgNames stds_ = _conf.getStandards();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        int begin_ = keyWordInstanceof_.length() + className.indexOf(keyWordInstanceof_);
        String sub_ = className.substring(begin_);
        int off_ = StringList.getFirstPrintableCharIndex(sub_);
        String compo_ = StringExpUtil.getQuickComponentBaseType(sub_).getComponent();
        boolean exact_ = compo_.contains(Templates.TEMPLATE_BEGIN);
        if (sub_.isEmpty()) {
            int rc_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex() + begin_ + off_;
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_conf.getAnalysisMessages().getEmptyType());
            _conf.getAnalyzing().getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            className = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            return;
        }
        sub_ = ResolvingImportTypes.resolveCorrectType(_conf, begin_ + off_, sub_, exact_);
        partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        if (!exact_) {
            ExecRootBlock r_ = _conf.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(sub_));
            if (r_ != null) {
                sub_ = r_.getWildCardString();
            }
        }
        className = sub_;
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
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
