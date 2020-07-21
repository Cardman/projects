package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.util.MethodAccessId;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;

public final class IdFctOperation extends LeafOperation {

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
    public void analyze(ContextEl _conf) {
        partOffsets = new CustList<PartOffset>();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl() + offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (m_ == null ||!m_.isCallMethodCtor()) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            varg_.setIndexFile(i_);
            //key word len
            varg_.buildError(_conf.getAnalysisMessages().getUnexpectedLeaf(),
                    _conf.getKeyWords().getKeyWordId());
            _conf.getAnalyzing().getLocalizer().addError(varg_);
            partOffsets.add(new PartOffset("<a title=\""+LinkageUtil.transform(varg_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsets.add(new PartOffset("</a>",i_+_conf.getKeyWords().getKeyWordId().length()));
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (!isFirstChildInParent()) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            varg_.setIndexFile(i_);
            //key word len
            varg_.buildError(_conf.getAnalysisMessages().getUnexpectedLeaf(),
                    _conf.getKeyWords().getKeyWordId());
            _conf.getAnalyzing().getLocalizer().addError(varg_);
            partOffsets.add(new PartOffset("<a title=\""+LinkageUtil.transform(varg_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsets.add(new PartOffset("</a>",i_+_conf.getKeyWords().getKeyWordId().length()));
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String extr_ = className.substring(className.indexOf('(')+1, className.lastIndexOf(')'));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(extr_);
        MethodAccessKind static_ = MethodAccessKind.STATIC;
        int i_ = 0;
        String cl_ = "";
        int anc_ = 0;
        if (!(m_ instanceof ExplicitOperatorOperation)) {
            String firstFull_ = args_.first();
            int off_ = StringList.getFirstPrintableCharIndex(firstFull_);
            String fromType_ = StringExpUtil.removeDottedSpaces(firstFull_);
            cl_ = ResolvingImportTypes.resolveAccessibleIdType(_conf,off_+className.indexOf('(')+1,fromType_);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            if (cl_.isEmpty()) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _conf.getKeyWords().getKeyWordStaticCall();
            MethodAccessId idUpdate_ = new MethodAccessId(1);
            idUpdate_.setupInfos(1,args_,keyWordStatic_,keyWordStaticCall_);
            static_ = idUpdate_.getKind();
            i_ = idUpdate_.getIndex();
            anc_ = idUpdate_.getAncestor();
        } else {
            cl_ = ((ExplicitOperatorOperation)m_).getFrom();
            if (!cl_.isEmpty()) {
                String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
                String keyWordStaticCall_ = _conf.getKeyWords().getKeyWordStaticCall();
                MethodAccessId idUpdate_ = new MethodAccessId(0);
                idUpdate_.setupInfos(0,args_,keyWordStatic_,keyWordStaticCall_);
                static_ = idUpdate_.getKind();
                i_ = idUpdate_.getIndex();
                anc_ = idUpdate_.getAncestor();
            }
        }
        MethodId argsRes_ = resolveArguments(i_, _conf, cl_, EMPTY_STRING, static_, args_, className, partOffsets);
        if (argsRes_ == null) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        method = new ClassMethodIdAncestor(new ClassMethodId(cl_, argsRes_),anc_);
        setSimpleArgument(new Argument());
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }
    public static MethodId resolveArguments(int _from, ContextEl _conf, String _fromType, String _name, MethodAccessKind _static, StringList _params, String _className, CustList<PartOffset> _partOffsets){
        StringList out_ = new StringList();
        int len_ = _params.size();
        int vararg_ = -1;
        int off_ = _className.indexOf('(')+1;
        for (int i = 0; i < _from; i++) {
            off_ += _params.get(i).length() + 1;
        }
        for (int i = _from; i < len_; i++) {
            String full_ = _params.get(i);
            int loc_ = StringList.getFirstPrintableCharIndex(full_);
            String arg_ = StringExpUtil.removeDottedSpaces(full_);
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    FoundErrorInterpret varg_ = new FoundErrorInterpret();
                    varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    int i_ = off_ + _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex() + full_.lastIndexOf("...");
                    varg_.setIndexFile(i_);
                    //three dots
                    varg_.buildError(_conf.getAnalysisMessages().getUnexpectedVararg());
                    _conf.getAnalyzing().getLocalizer().addError(varg_);
                    _partOffsets.add(new PartOffset("<a title=\""+LinkageUtil.transform(varg_.getBuiltError()) +"\" class=\"e\">",i_));
                    _partOffsets.add(new PartOffset("</a>",i_+3));
                    return null;
                }
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            arg_ = ResolvingImportTypes.resolveCorrectAccessibleType(_conf,off_ + loc_,type_, _fromType);
            _partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
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
