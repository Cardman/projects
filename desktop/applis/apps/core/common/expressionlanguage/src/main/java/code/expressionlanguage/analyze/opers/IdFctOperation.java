package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ResolvedId;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.MethodAccessId;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class IdFctOperation extends LeafOperation implements FunctFilterOperation {

    private final String className;

    private ClassMethodIdAncestor method;

    private InfoErrorDto partOffsetsErr;
    private CustList<AnaResultPartType> partOffsets;

    public IdFctOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        partOffsetsErr = new InfoErrorDto("");
        partOffsets = new CustList<AnaResultPartType>();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        MethodOperation m_ = getParent();
        if (isNotChildOfCall(m_)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordId());
            _page.getLocalizer().addError(varg_);
            partOffsetsErr = new InfoErrorDto(varg_,_page,_page.getKeyWords().getKeyWordId().length());
//            partOffsets.add(new PartOffset(ExportCst.anchorErr(varg_.getBuiltError()),i_));
//            partOffsets.add(new PartOffset(ExportCst.END_ANCHOR,i_+ _page.getKeyWords().getKeyWordId().length()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (!isFirstChildInParent()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordId());
            _page.getLocalizer().addError(varg_);
            partOffsetsErr = new InfoErrorDto(varg_,_page,_page.getKeyWords().getKeyWordId().length());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        String extr_ = className.substring(className.indexOf('(')+1, className.lastIndexOf(')'));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(extr_);
        MethodAccessKind static_;
        boolean retRef_;
        int i_ = 0;
        String cl_ = "";
        int anc_ = 0;
        if (!(m_ instanceof ExplicitOperatorOperation)) {
            String firstFull_ = args_.first();
            int off_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
            String fromType_ = firstFull_.trim();
            ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(off_ + className.indexOf('(') + 1, fromType_, _page);
            cl_ = resolvedIdType_.getFullName();
            partOffsets.add(resolvedIdType_.getDels());
            if (cl_.isEmpty()) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _page.getKeyWords().getKeyWordStaticCall();
            MethodAccessId idUpdate_ = new MethodAccessId(1);
            idUpdate_.setupInfosId(1,args_,keyWordStatic_,keyWordStaticCall_);
            static_ = idUpdate_.getKind();
            retRef_ = idUpdate_.isRetRef();
            i_ = idUpdate_.getIndex();
            anc_ = idUpdate_.getAncestor();
        } else {
            cl_ = ((ExplicitOperatorOperation)m_).getFrom();
            if (!cl_.isEmpty()) {
                String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
                String keyWordStaticCall_ = _page.getKeyWords().getKeyWordStaticCall();
                MethodAccessId idUpdate_ = new MethodAccessId(0);
                idUpdate_.setupInfosId(0,args_,keyWordStatic_,keyWordStaticCall_);
                static_ = idUpdate_.getKind();
                retRef_ = idUpdate_.isRetRef();
                i_ = idUpdate_.getIndex();
                anc_ = idUpdate_.getAncestor();
            } else {
                MethodAccessId idUpdate_ = new MethodAccessId(0);
                idUpdate_.setupAncestorId(args_,0);
                static_ = MethodAccessKind.STATIC;
                retRef_ = idUpdate_.isRetRef();
                i_ = idUpdate_.getIndex();
                anc_ = idUpdate_.getAncestor();
            }
        }
        ResolvedId resolved_ = resolveArguments(i_, retRef_, cl_, EMPTY_STRING, static_, args_, className, _page);
        partOffsets.addAllElts(resolved_.getResults());
        partOffsetsErr = resolved_.getInfo();
        MethodId argsRes_ = resolved_.getId();
        if (argsRes_ == null) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        method = new ClassMethodIdAncestor(new ClassMethodId(cl_, argsRes_),anc_);
        if (m_ instanceof RetrieveMethod) {
            RetrieveMethod f_ = (RetrieveMethod) m_;
            ClassMethodId id_ = new ClassMethodId(cl_, argsRes_);
            String idClass_ = id_.getClassName();
            MethodId mid_ = id_.getConstraints();
            MethodAccessKind staticKind_ = MethodId.getKind(f_.isStaticAccess(), mid_.getKind());
            ClassMethodId classMethodId_ = new ClassMethodId(idClass_, MethodId.to(staticKind_, f_.getMethodFound(), mid_));
            ClassMethodIdAncestor feed_ = new ClassMethodIdAncestor(classMethodId_,anc_);
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            for (int i = 0; i < len_; i++) {
                int gr_ = methodInfos_.get(i).size();
                CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                for (int j = 0; j < gr_; j++) {
                    MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                    String className_ = methodInfo_.getClassName();
                    className_ = StringExpUtil.getIdFromAllTypes(className_);
                    if (isCandidateMethod(feed_,methodInfo_.getAncestor(), className_,methodInfo_.getConstraints())){
                        continue;
                    }
                    newList_.add(methodInfo_);
                }
                methodInfos_.set(i,newList_);
            }
        }
        if (m_ instanceof RetrieveConstructor) {
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            ClassMethodId id_ = new ClassMethodId(cl_, argsRes_);
            String idClass_ = id_.getClassName();
            ConstructorId feed_ = MethodId.to(idClass_, id_.getConstraints());
            CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
            int len_ = methodInfos_.size();
            CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
            for (int i = 0; i < len_; i++) {
                ConstructorInfo methodInfo_ = methodInfos_.get(i);
                if (!methodInfo_.getConstraints().eq(feed_)) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            methodInfos_.clear();
            methodInfos_.addAllElts(newList_);
        }
        setSimpleArgument(new Argument());
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }
    public static ResolvedId resolveArguments(int _from, boolean _retRef, String _fromType, String _name, MethodAccessKind _static, StringList _params, String _className, AnalyzedPageEl _page){
        StringList out_ = new StringList();
        CustList<Boolean> ref_ = new CustList<Boolean>();
        int len_ = _params.size();
        int vararg_ = -1;
        int off_ = _className.indexOf('(')+1;
        for (int i = 0; i < _from; i++) {
            off_ += _params.get(i).length() + 1;
        }
        CustList<AnaResultPartType> types_ = new CustList<AnaResultPartType>();
        for (int i = _from; i < len_; i++) {
            String full_ = _params.get(i);
            int loc_ = StringUtil.getFirstPrintableCharIndex(full_);
            String arg_ = full_.trim();
            boolean refParam_ = false;
            if (arg_.startsWith("~")) {
                arg_ = arg_.substring(1);
                loc_ += StringUtil.getFirstPrintableCharIndex(arg_)+1;
                arg_ = arg_.trim();
                refParam_ = true;
            }
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    FoundErrorInterpret varg_ = new FoundErrorInterpret();
                    varg_.setFile(_page.getCurrentFile());
                    int offSum_ = off_ + full_.lastIndexOf("...");
                    varg_.setIndexFile(_page, offSum_);
                    //three dots
                    varg_.buildError(_page.getAnalysisMessages().getUnexpectedVararg());
                    _page.getLocalizer().addError(varg_);
//                    _partOffsets.add(new PartOffset(ExportCst.anchorErr(varg_.getBuiltError()),i_));
//                    _partOffsets.add(new PartOffset(ExportCst.END_ANCHOR,i_+3));
                    return new ResolvedId(null,new InfoErrorDto(varg_.getBuiltError(),_page,offSum_,3),types_);
                }
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length()).trim();
            } else {
                type_ = arg_;
            }
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectAccessibleType(off_ + loc_, type_, _fromType, _page);
            types_.add(result_);
            arg_ = result_.getResult(_page);
            off_ += _params.get(i).length() + 1;
            out_.add(arg_);
            ref_.add(refParam_);
        }
        return new ResolvedId(new MethodId(_retRef, _static, _name, out_,ref_, vararg_ != -1),new InfoErrorDto(""),types_);
    }

    public ClassMethodIdAncestor getMethod() {
        return method;
    }

    @Override
    public CustList<AnaResultPartType> getPartOffsets() {
        return partOffsets;
    }

    @Override
    public InfoErrorDto getPartOffsetsErr() {
        return partOffsetsErr;
    }
}
