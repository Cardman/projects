package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class IdFctOperation extends LeafOperation implements IdFctOperable {

    private String className;
    private int offset;

    private ClassMethodIdAncestor method;

    private CustList<PartOffset> partOffsets;

    public IdFctOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        offset = _op.getValues().firstKey();
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(Analyzable _conf) {
        partOffsets = new CustList<PartOffset>();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl() + offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (m_ == null ||!m_.isCallMethodCtor()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            //key word len
            varg_.buildError(_conf.getContextEl().getAnalysisMessages().getUnexpectedLeaf(),
                    _conf.getContextEl().getKeyWords().getKeyWordId());
            _conf.addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (!isFirstChildInParent()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            //key word len
            varg_.buildError(_conf.getContextEl().getAnalysisMessages().getUnexpectedLeaf(),
                    _conf.getContextEl().getKeyWords().getKeyWordId());
            _conf.addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String extr_ = className.substring(className.indexOf('(')+1, className.lastIndexOf(')'));
        StringList args_ = Templates.getAllSepCommaTypes(extr_);
        MethodAccessKind static_ = MethodAccessKind.STATIC;
        int i_ = 0;
        String cl_ = "";
        int anc_ = 0;
        if (!(m_ instanceof ExplicitOperatorOperation)) {
            String firstFull_ = args_.first();
            int off_ = StringList.getFirstPrintableCharIndex(firstFull_);
            String fromType_ = ContextEl.removeDottedSpaces(firstFull_);
            cl_ = _conf.resolveAccessibleIdType(off_+className.indexOf('(')+1,fromType_);
            if (cl_.isEmpty()) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
            String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _conf.getKeyWords().getKeyWordStaticCall();
            MethodAccessId idUpdate_ = new MethodAccessId(1);
            idUpdate_.setupInfos(1,args_,keyWordStatic_,keyWordStaticCall_);
            static_ = idUpdate_.getKind();
            i_ = idUpdate_.getIndex();
            anc_ = idUpdate_.getAncestor();
        }
        MethodId argsRes_ = resolveArguments(i_, _conf, cl_, EMPTY_STRING, static_, args_);
        if (argsRes_ == null) {
            return;
        }
        method = new ClassMethodIdAncestor(new ClassMethodId(cl_, argsRes_),anc_);
        setSimpleArgument(new Argument());
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }
    private MethodId resolveArguments(int _from, Analyzable _conf, String _fromType, String _name, MethodAccessKind _static, StringList _params){
        StringList out_ = new StringList();
        LgNames stds_ = _conf.getStandards();
        int len_ = _params.size();
        int vararg_ = -1;
        int off_ = className.indexOf('(')+1;
        for (int i = 0; i < _from; i++) {
            off_ += _params.get(i).length() + 1;
        }
        for (int i = _from; i < len_; i++) {
            String full_ = _params.get(i);
            int loc_ = StringList.getFirstPrintableCharIndex(full_);
            String arg_ = ContextEl.removeDottedSpaces(full_);
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    FoundErrorInterpret varg_ = new FoundErrorInterpret();
                    varg_.setFileName(_conf.getCurrentFileName());
                    varg_.setIndexFile(_conf.getCurrentLocationIndex());
                    //three dots
                    varg_.buildError(_conf.getContextEl().getAnalysisMessages().getUnexpectedVararg());
                    _conf.addError(varg_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return null;
                }
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            arg_ = _conf.resolveCorrectAccessibleType(off_ + loc_,type_, _fromType);
            partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
            off_ += _params.get(i).length() + 1;
            out_.add(arg_);
        }
        return new MethodId(_static, _name, out_, vararg_ != -1);
    }

    public ClassMethodIdAncestor getMethod() {
        return method;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
