package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.VarargError;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class IdFctOperation extends LeafOperation {

    private String className;
    private int offset;

    private ClassMethodId method;

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
        String extr_ = className.substring(className.indexOf('(')+1, className.lastIndexOf(')'));
        StringList args_ = Templates.getAllSepCommaTypes(extr_);
        String firstFull_ = args_.first();
        int off_ = StringList.getFirstPrintableCharIndex(firstFull_);
        String fromType_ = ContextEl.removeDottedSpaces(firstFull_);
        String cl_ = _conf.resolveAccessibleIdType(off_+className.indexOf('(')+1,fromType_);
        if (cl_.isEmpty()) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
        String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
        String keyWordStaticCall_ = _conf.getKeyWords().getKeyWordStaticCall();
        MethodAccessId idUpdate_ = new MethodAccessId(1);
        idUpdate_.setupInfos(1,args_,keyWordStatic_,keyWordStaticCall_);
        MethodAccessKind static_;
        int i_ = idUpdate_.getIndex();
        static_ = idUpdate_.getKind();
        MethodId argsRes_ = resolveArguments(i_, _conf, cl_, EMPTY_STRING, static_, args_);
        if (m_ == null ||!m_.isCallMethodCtor()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (!isFirstChild()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (argsRes_ == null) {
            return;
        }
        method = new ClassMethodId(cl_, argsRes_);
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
                    VarargError varg_ = new VarargError();
                    varg_.setFileName(_conf.getCurrentFileName());
                    varg_.setIndexFile(_conf.getCurrentLocationIndex());
                    varg_.setMethodName(VAR_ARG);
                    _conf.getClasses().addError(varg_);
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

    public ClassMethodId getMethod() {
        return method;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
