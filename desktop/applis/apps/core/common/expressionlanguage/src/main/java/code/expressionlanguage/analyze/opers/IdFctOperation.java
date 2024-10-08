package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ResolvedId;
import code.expressionlanguage.analyze.opers.util.ResolvedIdBuilder;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.MethodAccessId;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class IdFctOperation extends LeafOperation implements FunctFilterOperation {

    private final String className;

    private ClassMethodIdAncestor method;
    private ClassMethodIdAncestor methodSet;

    private InfoErrorDto partOffsetsErr;
    private InfoErrorDto partOffsetsErrSet;
    private CustList<AnaResultPartTypeDtoInt> partOffsets;
    private CustList<AnaResultPartTypeDtoInt> partOffsetsSet;

    public IdFctOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        partOffsetsErr = new InfoErrorDto("");
        partOffsetsErrSet = new InfoErrorDto("");
        partOffsets = new CustList<AnaResultPartTypeDtoInt>();
        partOffsetsSet = new CustList<AnaResultPartTypeDtoInt>();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        MethodOperation m_ = getParent();
        if (isNotChildOfCall(m_)||!isFirstChildInParent()) {
            errorUnexpected(_page);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (m_ instanceof ArrOperation) {
            arr(_page);
            return;
        }
        String extr_ = className.substring(className.indexOf('(')+1, className.lastIndexOf(')'));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(extr_);
        MethodAccessKind static_;
        boolean retRef_;
        int i_;
        String cl_;
        int anc_;
        AnaGeneType accType_;
        if (!(m_ instanceof ExplicitOperatorOperation)) {
            String firstFull_ = args_.first();
            int off_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
            String fromType_ = firstFull_.trim();
            ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(off_ + className.indexOf('(') + 1, fromType_, _page);
            cl_ = resolvedIdType_.getFullName();
            accType_ = resolvedIdType_.getGeneType();
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
            accType_ = ((ExplicitOperatorOperation)m_).getAccType();
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
        ResolvedId resolved_;
        if (off(false, i_, args_, className) > -1) {
            resolved_ = errCase(i_, cl_, args_, className, _page);
        } else {
            resolved_ = new ResolvedIdBuilder(i_, cl_, args_, className, _page).build(i_, retRef_, EMPTY_STRING, static_, args_, _page);
        }
        partOffsets.addAllElts(resolved_.getResults());
        partOffsetsErr = resolved_.getInfo();
        MethodId argsRes_ = resolved_.getId();
        if (argsRes_ == null) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        method = new ClassMethodIdAncestor(accType_,new ClassMethodId(cl_, argsRes_),anc_);
        filterMethod();
        filterCtor();
        setSimpleArgument(NullStruct.NULL_VALUE);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    private void arr(AnalyzedPageEl _page) {
        String extr_ = className.substring(className.indexOf('(')+1, className.lastIndexOf(')'));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(extr_);
        String firstFull_ = args_.first();
        int off_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
        String fromType_ = firstFull_.trim();
        if (StringUtil.quickEq(fromType_,"[]=")) {
            MethodAccessId idUpdate_ = new MethodAccessId(1);
            idUpdate_.setupAncestorId(args_,1);
            boolean retRef_ = idUpdate_.isRetRef();
            int i_ = idUpdate_.getIndex();
            if (i_ >= args_.size()) {
                errorUnexpected(_page);
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            String firstFullSet_ = args_.get(i_);
            int offSet_ = StringUtil.getFirstPrintableCharIndex(firstFullSet_) + sum(i_,args_);
            String fromTypeSet_ = firstFullSet_.trim();
            ResolvedIdType resolvedIdTypeSet_ = ResolvingTypes.resolveAccessibleIdTypeBlock(offSet_ + className.indexOf('(') + 1, fromTypeSet_, _page);
            String clSet_ = resolvedIdTypeSet_.getFullName();
            partOffsetsSet.add(resolvedIdTypeSet_.getDels());
            if (clSet_.isEmpty()) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            int iSet_ = i_ + 1;
            arrDef(_page, args_, retRef_, resolvedIdTypeSet_, iSet_);
            return;
        }
        ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(off_ + className.indexOf('(') + 1, fromType_, _page);
        String cl_ = resolvedIdType_.getFullName();
        AnaGeneType accType_ = resolvedIdType_.getGeneType();
        partOffsets.add(resolvedIdType_.getDels());
        if (cl_.isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
        String keyWordStaticCall_ = _page.getKeyWords().getKeyWordStaticCall();
        MethodAccessId idUpdate_ = new MethodAccessId(1);
        idUpdate_.setupInfosId(1,args_,keyWordStatic_,keyWordStaticCall_);
        boolean retRef_ = idUpdate_.isRetRef();
        int i_ = idUpdate_.getIndex();
        int anc_ = idUpdate_.getAncestor();
        int fu_ = off(true,i_, args_, className);
        if (fu_ > -1) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page, fu_);
            //three dots
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedVararg());
            _page.getLocalizer().addError(varg_);
            CustList<AnaResultPartTypeDtoInt> typesAna_ = types(i_, args_, cl_, className, _page);
            InfoErrorDto info_ = new InfoErrorDto(varg_, _page, fu_, 3);
            partOffsets.addAllElts(typesAna_);
            partOffsetsErr = info_;
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        CustList<AnaResultPartTypeDtoInt> typesAna_ = types(i_, args_, cl_, className, _page);
        StringList classNames_ = typesStr(typesAna_, _page);
        MethodId idCt_ = new MethodId(retRef_, MethodAccessKind.INSTANCE, EMPTY_STRING, classNames_, refs(i_, i_+classNames_.size(), args_), vararg(i_, args_) != -1);
        method = new ClassMethodIdAncestor(accType_,new ClassMethodId(cl_, idCt_),anc_);
        partOffsets.addAllElts(typesAna_);
        partOffsetsErr = new InfoErrorDto("");
        if (i_ + typesAna_.size() + 1 >= args_.size()) {
            filterMethod();
            filterCtor();
            setSimpleArgument(NullStruct.NULL_VALUE);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        String firstFullSet_ = args_.get(i_ + typesAna_.size()+1);
        int offSet_ = StringUtil.getFirstPrintableCharIndex(firstFullSet_) + sum(i_ + typesAna_.size()+1,args_);
        String fromTypeSet_ = firstFullSet_.trim();
        ResolvedIdType resolvedIdTypeSet_ = ResolvingTypes.resolveAccessibleIdTypeBlock(offSet_ + className.indexOf('(') + 1, fromTypeSet_, _page);
        String clSet_ = resolvedIdTypeSet_.getFullName();
        partOffsetsSet.add(resolvedIdTypeSet_.getDels());
        if (clSet_.isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }

        int iSet_ = i_ + typesAna_.size() + 2;
        arrDef(_page, args_, retRef_,resolvedIdTypeSet_, iSet_);
    }

    private void errorUnexpected(AnalyzedPageEl _page) {
        FoundErrorInterpret varg_ = new FoundErrorInterpret();
        varg_.setFile(_page.getCurrentFile());
        varg_.setIndexFile(_page);
        //key word len
        varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                _page.getKeyWords().getKeyWordId());
        _page.getLocalizer().addError(varg_);
        partOffsetsErr = new InfoErrorDto(varg_, _page, _page.getKeyWords().getKeyWordId().length());
    }

    private void arrDef(AnalyzedPageEl _page, StringList _args, boolean _retRef, ResolvedIdType _resolvedIdTypeSet, int _iSet) {
        String clSet_ = _resolvedIdTypeSet.getFullName();
        AnaGeneType accTypeSet_ = _resolvedIdTypeSet.getGeneType();
        CustList<AnaResultPartTypeDtoInt> typesAnaSet_ = types(_iSet, _args, clSet_, className, _page);
        int fu2_ = off(false, _iSet, _args, className);
        if (fu2_ > -1) {
            varargErr(_page, typesAnaSet_, fu2_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        StringList classNamesSet_ = typesStr(typesAnaSet_, _page);
        MethodId idCtSet_ = new MethodId(_retRef, MethodAccessKind.INSTANCE, EMPTY_STRING, classNamesSet_, refs(_iSet, _args.size(), _args), vararg(_iSet, _args) != -1);
        methodSet = new ClassMethodIdAncestor(accTypeSet_,new ClassMethodId(clSet_, idCtSet_),0);
        partOffsetsSet.addAllElts(typesAnaSet_);
        partOffsetsErrSet = new InfoErrorDto("");
        String firstFull_ = _args.first();
        String fromType_ = firstFull_.trim();
        if (!StringUtil.quickEq(fromType_,"[]=")) {
            filterMethod();
            filterCtor();
        }
        setSimpleArgument(NullStruct.NULL_VALUE);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    private void varargErr(AnalyzedPageEl _page, CustList<AnaResultPartTypeDtoInt> _typesAnaSet, int _fu2) {
        FoundErrorInterpret varg_ = new FoundErrorInterpret();
        varg_.setFile(_page.getCurrentFile());
        varg_.setIndexFile(_page, _fu2);
        //three dots
        varg_.buildError(_page.getAnalysisMessages().getUnexpectedVararg());
        _page.getLocalizer().addError(varg_);
        InfoErrorDto info_ = new InfoErrorDto(varg_, _page, _fu2, 3);
        partOffsetsSet.addAllElts(_typesAnaSet);
        partOffsetsErrSet = info_;
    }

    private void filterMethod() {
        MethodOperation m_ = getParent();
        if (m_ instanceof RetrieveMethod) {
            RetrieveMethod f_ = (RetrieveMethod) m_;
            ClassMethodId id_ = new ClassMethodId(method.getClassMethodId().getClassName(), method.getClassMethodId().getConstraints());
            String idClass_ = id_.getClassName();
            MethodId mid_ = id_.getConstraints();
            MethodAccessKind staticKind_ = MethodId.getKind(f_.isStaticAccess(), mid_.getKind());
            ClassMethodId classMethodId_ = new ClassMethodId(idClass_, MethodId.to(staticKind_, f_.getMethodFound(), mid_));
            ClassMethodIdAncestor feed_ = new ClassMethodIdAncestor(method.getGt(),classMethodId_, method.getAncestor());
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            for (int i = 0; i < len_; i++) {
                int gr_ = methodInfos_.get(i).size();
                CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                for (int j = 0; j < gr_; j++) {
                    MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                    if (isCandidateMethod(feed_,methodInfo_.getAncestor(), methodInfo_.getOwner(),methodInfo_.getConstraints())){
                        continue;
                    }
                    newList_.add(methodInfo_);
                }
                methodInfos_.set(i,newList_);
            }
        }
    }

    private void filterCtor() {
        MethodOperation m_ = getParent();
        if (m_ instanceof RetrieveConstructor) {
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            ClassMethodId id_ = new ClassMethodId(method.getClassMethodId().getClassName(), method.getClassMethodId().getConstraints());
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
    }

    public static ResolvedId errCase(int _from, String _fromType, StringList _params, String _className, AnalyzedPageEl _page) {
        FoundErrorInterpret varg_ = err(_from, _params, _className, _page);
        return unsolvedId(_from, _fromType, _params, _className, _page, varg_);
    }

    public static FoundErrorInterpret err(int _from, StringList _params, String _className, AnalyzedPageEl _page) {
        int fu2_ = off(false, _from, _params, _className);
        FoundErrorInterpret varg_ = new FoundErrorInterpret();
        varg_.setFile(_page.getCurrentFile());
        varg_.setIndexFile(_page, fu2_);
        //three dots
        varg_.buildError(_page.getAnalysisMessages().getUnexpectedVararg());
        _page.getLocalizer().addError(varg_);
        return varg_;
    }

    public static ResolvedId unsolvedId(int _from, String _fromType, StringList _params, String _className, AnalyzedPageEl _page, FoundErrorInterpret _varg) {
        int fu_ = off(false,_from, _params, _className);
        CustList<AnaResultPartTypeDtoInt> typesAna_ = types(_from, _params, _fromType, _className, _page);
        return new ResolvedId(null, new InfoErrorDto(_varg, _page, fu_, 3), typesAna_);
    }


    public static int off(boolean _next,int _from, StringList _params, String _className){
        int len_ = _params.size();
        int off_ = _className.indexOf('(')+1;
        for (int i = 0; i < _from; i++) {
            off_ += _params.get(i).length() + 1;
        }
        for (int i = _from; i < len_; i++) {
            String full_ = _params.get(i);
            String arg_ = full_.trim();
            if (_next && StringUtil.quickEq(arg_, "[]=")) {
                return -1;
            }
            if (arg_.startsWith("~")) {
                arg_ = arg_.substring(1);
                arg_ = arg_.trim();
            }
            if (arg_.endsWith(VARARG_SUFFIX) && i + 1 < len_ && !StringUtil.quickEq(_params.get(i+1), "[]=")) {
                //last type => error
                return off_ + full_.lastIndexOf(VARARG_SUFFIX);
            }
            off_ += _params.get(i).length() + 1;
        }
        return -1;
    }
    public static int vararg(int _from, StringList _params){
        int len_ = _params.size();
        int vararg_ = -1;
        for (int i = _from; i < len_; i++) {
            String full_ = _params.get(i);
            String arg_ = full_.trim();
            if (StringUtil.quickEq(arg_,"[]=")) {
                break;
            }
            if (arg_.startsWith("~")) {
                arg_ = arg_.substring(1);
                arg_ = arg_.trim();
            }
            if (arg_.endsWith(VARARG_SUFFIX)) {
                vararg_ = len_ - _from;
            }
        }
        return vararg_;
    }
    public static StringList typesStr(CustList<AnaResultPartTypeDtoInt> _params, AnalyzedPageEl _page){
        StringList out_ = new StringList();
        for (AnaResultPartTypeDtoInt a: _params) {
            String arg_ = a.build().getResult(_page);
            out_.add(arg_);
        }
        return out_;
    }
    public static CustList<AnaResultPartTypeDtoInt> types(int _from, StringList _params, String _fromType, String _className, AnalyzedPageEl _page){
        int len_ = _params.size();
        int off_ = _className.indexOf('(')+1;
        for (int i = 0; i < _from; i++) {
            off_ += _params.get(i).length() + 1;
        }
        CustList<AnaResultPartTypeDtoInt> types_ = new CustList<AnaResultPartTypeDtoInt>();
        for (int i = _from; i < len_; i++) {
            String full_ = _params.get(i);
            int loc_ = StringUtil.getFirstPrintableCharIndex(full_);
            String arg_ = full_.trim();
            if (StringUtil.quickEq(arg_,"[]=")) {
                break;
            }
            if (arg_.startsWith("~")) {
                arg_ = arg_.substring(1);
                loc_ += StringUtil.getFirstPrintableCharIndex(arg_)+1;
                arg_ = arg_.trim();
            }
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length()).trim();
            } else {
                type_ = arg_;
            }
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectAccessibleType(off_ + loc_, type_, _fromType, _page);
            types_.add(new AnaResultPartTypeDirectDto(result_));
            off_ += _params.get(i).length() + 1;
        }
        return types_;
    }
    private static int sum(int _to, StringList _params) {
        int off_ = 0;
        for (int i = 0; i < _to; i++) {
            off_ += _params.get(i).length() + 1;
        }
        return off_;
    }
    public static CustList<BoolVal> refs(int _from, int _to, StringList _params){
        CustList<BoolVal> ref_ = new CustList<BoolVal>();
        for (int i = _from; i < _to; i++) {
            String full_ = _params.get(i);
            String arg_ = full_.trim();
            BoolVal refParam_ = BoolVal.FALSE;
            if (arg_.startsWith("~")) {
                refParam_ = BoolVal.TRUE;
            }
            ref_.add(refParam_);
        }
        return ref_;
    }

    public ClassMethodIdAncestor getMethod() {
        return method;
    }

    @Override
    public CustList<AnaResultPartTypeDtoInt> getPartOffsets() {
        return partOffsets;
    }

    @Override
    public InfoErrorDto getPartOffsetsErr() {
        return partOffsetsErr;
    }

    public ClassMethodIdAncestor getMethodSet() {
        return methodSet;
    }

    public CustList<AnaResultPartTypeDtoInt> getPartOffsetsSet() {
        return partOffsetsSet;
    }

    public InfoErrorDto getPartOffsetsErrSet() {
        return partOffsetsErrSet;
    }
}
