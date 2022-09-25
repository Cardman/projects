package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.Parametrable;
import code.expressionlanguage.analyze.opers.util.VarsComparer;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;

import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class AnaTemplates {

    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";

    public static final char LT = '<';

    public static final char GT = '>';

    private AnaTemplates() {
    }

    public static CustList<ConstrustorIdVarArg> reduceCtors(CustList<ConstrustorIdVarArg> _ls) {
        CustList<ConstrustorIdVarArg> ls_ = new CustList<ConstrustorIdVarArg>();
        for (ConstrustorIdVarArg c: _ls) {
            if (!containsResCtor(ls_,c)) {
                ls_.add(c);
            }
        }
        return ls_;
    }
    public static boolean containsResCtor(CustList<ConstrustorIdVarArg> _ls, ConstrustorIdVarArg _res) {
        for (ConstrustorIdVarArg c: _ls) {
            if (!StringUtil.quickEq(c.getConstId().getName(), _res.getConstId().getName()) || !c.getRealId().eq(_res.getRealId())) {
                continue;
            }
            return true;
        }
        return false;
    }

    public static CustList<ClassMethodIdReturn> reduceMethods(CustList<ClassMethodIdReturn> _ls) {
        CustList<ClassMethodIdReturn> ls_ = new CustList<ClassMethodIdReturn>();
        for (ClassMethodIdReturn c: _ls) {
            if (!containsResMethod(ls_,c)) {
                ls_.add(c);
            }
        }
        return ls_;
    }
    public static boolean containsResMethod(CustList<ClassMethodIdReturn> _ls, ClassMethodIdReturn _res) {
        ClassMethodId input_ = new ClassMethodId(_res.getRealClass(),_res.getRealId());
        for (ClassMethodIdReturn c: _ls) {
            if (c.getAncestor() != _res.getAncestor() || !new ClassMethodId(c.getRealClass(), c.getRealId()).eq(input_)) {
                continue;
            }
            return true;
        }
        return false;
    }

    /** Return if possible the inferred form<br/>
     Sample 1: "int" => null<br/>
     Sample 2: "Pair&gt;" => null<br/>
     Sample 3: "Pair&lt;int&gt;" => null<br/>
     Sample 4: "Pa,ir&lt;&gt;" => null<br/>
     Sample 5: "Pair&lt;&gt;" => Pair<br/>
     Sample 6: "Pa..ir&lt;&gt;" => Pa..ir<br/>
     */
    public static String getInferForm(String _type) {
        String tr_ = _type.trim();
        if (!tr_.endsWith(TEMPLATE_END)) {
            return null;
        }
        tr_ = tr_.substring(0, tr_.length() - 1).trim();
        if (!tr_.endsWith(TEMPLATE_BEGIN)) {
            return null;
        }
        tr_ = tr_.substring(0, tr_.length() - 1).trim();
        for (String p: StringUtil.splitChars(tr_,'.')) {
            if (p.isEmpty() || StringExpUtil.isDollarWord(p.trim())) {
                continue;
            }
            return null;
        }
        return tr_;
    }

    public static CustList<Matching> tryInferMethodByOneArgList(MethodInfo _methodInfo,
                                                                int _index, String _staticCall,
                                                                AnaClassArgumentMatching _arg,
                                                                String _returnType,
                                                                AnalyzedPageEl _page) {
        AnaGeneType sub_ = _page.getAnaGeneType(_staticCall);
        if (sub_ == null) {
            return new CustList<Matching>();
        }
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(sub_);
        int sizeVar_ = varTypes_.size();
        StringMap<StringList> inh_ = inhNb(sub_);
        String genericString_ = getGeneNb(sub_);
        String idOwner_ = StringExpUtil.getIdFromAllTypes(_methodInfo.getClassName());
        AnaGeneType cType_ = _page.getAnaGeneType(idOwner_);
        if (cType_ == null) {
            return new CustList<Matching>();
        }
        String candidate_ = AnaInherits.getFullTypeByBases(sub_, genericString_, idOwner_, _page);
        MethodId candidateId_ = _methodInfo.getConstraints();
        CustList<Matching> all_ = retValue(_methodInfo,_returnType,_page,inh_,cType_,candidate_,candidateId_);
        int len_ = candidateId_.getParametersTypesLength();
        if (!candidateId_.isVararg() || _index <= -1) {
            if (_index > -1 && _index < len_) {
                String wc_ = formatArg(_index, cType_, candidate_, candidateId_);
                return getMatchingsArg(_index, _arg, _page, inh_, candidateId_, all_, wc_);
            }
            return all_;
        }
        if (_index < len_ - 1) {
            String wc_ = formatArg(_index, cType_, candidate_, candidateId_);
            return getMatchingsArg(_index, _arg, _page, inh_, candidateId_, all_, wc_);
        }
        if (_index < len_) {
            int last_ = len_ - 1;
            Mapping map_ = new Mapping();
            String wc_ = formatArg(last_, cType_, candidate_, candidateId_);
            if (candidateId_.getParametersRef(last_) == BoolVal.TRUE) {
                map_.setArg(_arg);
                map_.getMapping().putAllMap(inh_);
                map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
                map_.setParam(StringExpUtil.getPrettyArrayType(wc_));
                CustList<Matching> cts_ = inferOrImplicit(_arg, StringExpUtil.getPrettyArrayType(wc_), MatchingEnum.EQ, map_.getMapping(), _page);
                all_.addAllElts(cts_);
                return all_;
            }
            map_.setArg(_arg);
            map_.getMapping().putAllMap(inh_);
            map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
            String arr_ = StringExpUtil.getPrettyArrayType(wc_);
            map_.setParam(arr_);
            CustList<Matching> cts_ = inferOrImplicit(_arg, arr_, MatchingEnum.SUB, map_.getMapping(), _page);
            //compare base of arg - param by sub and return eq constraints
            CustList<Matching> attempt_ = new CustList<Matching>(all_);
            attempt_.addAllElts(cts_);
            //try infer here
            String tried_ = processConstraints(genericString_, attempt_, sizeVar_, _page.getCurrentConstraints().getCurrentConstraints(), _page);
            if (!tried_.isEmpty()) {
                return attempt_;
            }
            map_.setParam(wc_);
            CustList<Matching> cts2_ = inferOrImplicit(_arg, wc_, MatchingEnum.SUB, map_.getMapping(), _page);
            //else compare base of arg - param by sub and return eq constraints and roll back previous
            all_.addAllElts(cts2_);
            return all_;
        }
        int last_ = len_ - 1;
        Mapping map_ = new Mapping();
        map_.getMapping().putAllMap(inh_);
        map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
        String wc_ = formatArg(last_, cType_, candidate_, candidateId_);
        map_.setParam(wc_);
        map_.setArg(_arg);
        CustList<Matching> cts_ = inferOrImplicit(_arg, wc_, MatchingEnum.SUB, map_.getMapping(), _page);
        //compare base of arg - param by sub and return eq constraints
        all_.addAllElts(cts_);
        return all_;
    }

    private static CustList<Matching> getMatchingsArg(int _index, AnaClassArgumentMatching _arg, AnalyzedPageEl _page, StringMap<StringList> _inh, MethodId _candidateId, CustList<Matching> _all, String _wc) {
        if (_candidateId.getParametersRef(_index) == BoolVal.TRUE) {
            Mapping map_ = new Mapping();
            map_.setArg(_arg);
            map_.getMapping().putAllMap(_inh);
            map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
            map_.setParam(_wc);
            CustList<Matching> cts_ = inferOrImplicit(_arg, _wc,MatchingEnum.EQ,map_.getMapping(), _page);
            //compare base of arg - param by eq and return eq constraints
            _all.addAllElts(cts_);
        } else {
            if (!_arg.isVariable()) {
                Mapping map_ = new Mapping();
                map_.setArg(_arg);
                map_.getMapping().putAllMap(_inh);
                map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
                map_.setParam(_wc);
                CustList<Matching> cts_ = inferOrImplicit(_arg, _wc,MatchingEnum.SUB,map_.getMapping(), _page);
                //compare base of arg - param by sub and return eq constraints
                _all.addAllElts(cts_);
            }
        }
        return _all;
    }

    public static String tryInferMethodByOneArg(CustList<Matching> _all,
                                                String _staticCall, StringMap<StringList> _vars,
                                                AnalyzedPageEl _page) {
        AnaGeneType sub_ = _page.getAnaGeneType(_staticCall);
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(sub_);
        int sizeVar_ = varTypes_.size();
        String genericString_ = getGeneNb(sub_);
        return processConstraints(genericString_,_all, sizeVar_,_vars,_page);
    }
    public static String tryInferMethod(int _varargOnly, Parametrable _parame,
                                        String _staticCall,
                                        CustList<AnaClassArgumentMatching> _args,
                                        String _returnType,
                                        AnalyzedPageEl _page) {
        AnaGeneType sub_ = _page.getAnaGeneType(_staticCall);
        if (sub_ == null) {
            return "";
        }
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(sub_);
        int sizeVar_ = varTypes_.size();
        StringMap<StringList> inh_ = inhNb(sub_);
        String genericString_ = getGeneNb(sub_);
        String idOwner_ = StringExpUtil.getIdFromAllTypes(_parame.getClassName());
        AnaGeneType cType_ = _page.getAnaGeneType(idOwner_);
        if (cType_ == null) {
            return "";
        }
        String candidate_ = AnaInherits.getFullTypeByBases(sub_, genericString_, idOwner_, _page);
        int allArgsLen_ = _args.size();
        int startOpt_ = allArgsLen_;
        boolean checkOnlyDem_ = true;
        MethodId candidateId_ = _parame.toId();
        int paramLen_ = candidateId_.getParametersTypesLength();
        boolean vararg_ = candidateId_.isVararg();
        if (koInferNbArgs(_varargOnly, _parame, _args)) {
            return "";
        }
        if (vararg_ && _varargOnly != 0) {
            checkOnlyDem_ = false;
            startOpt_ = paramLen_ - 1;
        }
        CustList<Matching> all_ = retValue(_parame, _returnType, _page, inh_, cType_, candidate_, candidateId_);
        for (int i = IndexConstants.FIRST_INDEX; i < startOpt_; i++) {
            String wc_ = formatArg(i, cType_, candidate_, candidateId_);
            addConstraintsArg(_args, _page, inh_, candidateId_, all_, i, wc_);
        }
        if (checkOnlyDem_) {
            return processConstraints(genericString_,all_, sizeVar_,_page.getCurrentConstraints().getCurrentConstraints(),_page);
        }
        int last_ = paramLen_ - 1;
        if (paramLen_ == allArgsLen_) {
            String wc_ = formatArg(last_, cType_, candidate_, candidateId_);
            AnaClassArgumentMatching resArg_ = _args.last();
            Mapping map_ = new Mapping();
            if (candidateId_.getParametersRef(last_) == BoolVal.TRUE) {
                map_.setArg(resArg_);
                map_.getMapping().putAllMap(inh_);
                map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
                map_.setParam(StringExpUtil.getPrettyArrayType(wc_));
                CustList<Matching> cts_ = inferOrImplicit(resArg_,StringExpUtil.getPrettyArrayType(wc_),MatchingEnum.EQ,map_.getMapping(), _page);
                all_.addAllElts(cts_);
                return processConstraints(genericString_,all_, sizeVar_,_page.getCurrentConstraints().getCurrentConstraints(),_page);
            }
            map_.setArg(resArg_);
            map_.getMapping().putAllMap(inh_);
            map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
            String arr_ = StringExpUtil.getPrettyArrayType(wc_);
            map_.setParam(arr_);
            CustList<Matching> cts_ = inferOrImplicit(resArg_,arr_,MatchingEnum.SUB,map_.getMapping(), _page);
            //compare base of arg - param by sub and return eq constraints
            CustList<Matching> attempt_ = new CustList<Matching>(all_);
            attempt_.addAllElts(cts_);
            //try infer here
            String tried_ = processConstraints(genericString_, attempt_, sizeVar_,_page.getCurrentConstraints().getCurrentConstraints(), _page);
            if (!tried_.isEmpty()) {
                return tried_;
            }
            map_.setParam(wc_);
            CustList<Matching> cts2_ = inferOrImplicit(resArg_,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
            //else compare base of arg - param by sub and return eq constraints and roll back previous
            all_.addAllElts(cts2_);
            return processConstraints(genericString_, all_, sizeVar_,_page.getCurrentConstraints().getCurrentConstraints(), _page);
        }
        Mapping map_ = new Mapping();
        map_.getMapping().putAllMap(inh_);
        map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
        String wc_ = formatArg(last_, cType_, candidate_, candidateId_);
        strictArrVararg(_args, _page, startOpt_, all_, map_, wc_);
        //try infer here
        return processConstraints(genericString_, all_, sizeVar_,_page.getCurrentConstraints().getCurrentConstraints(), _page);
    }

    private static String formatArg(int _index, AnaGeneType _cType, String _candidate, MethodId _candidateId) {
        String wc_ = _candidateId.getParametersType(_index);
        return AnaInherits.quickFormat(_cType, _candidate, wc_);
    }

    private static boolean koInferNbArgs(int _varargOnly, Parametrable _parame,
                                         CustList<AnaClassArgumentMatching> _args) {
        int startOpt_ = _args.size();
        MethodId candidateId_ = _parame.toId();
        int paramLen_ = candidateId_.getParametersTypesLength();
        boolean vararg_ = candidateId_.isVararg();
        if (!vararg_) {
            return paramLen_ != startOpt_;
        }
        if (paramLen_ > startOpt_ + 1) {
            return true;
        }
        if (_varargOnly != 0) {
            startOpt_ = paramLen_ - 1;
        }
        if (_varargOnly > 0) {
            return startOpt_ != _varargOnly - 1;
        }
        if (_varargOnly == 0) {
            return paramLen_ - 1 != startOpt_;
        }
        return false;
    }

    private static void strictArrVararg(CustList<AnaClassArgumentMatching> _args, AnalyzedPageEl _page, int _startOpt, CustList<Matching> _all, Mapping _map, String _wc) {
        int allArgsLen_ = _args.size();
        _map.setParam(_wc);
        for (int i = _startOpt; i < allArgsLen_; i++) {
            AnaClassArgumentMatching a_ = _args.get(i);
            _map.setArg(a_);
            CustList<Matching> cts_ = inferOrImplicit(a_, _wc,MatchingEnum.SUB, _map.getMapping(), _page);
            //compare base of arg - param by sub and return eq constraints
            _all.addAllElts(cts_);
        }
    }

    private static void addConstraintsArg(CustList<AnaClassArgumentMatching> _args, AnalyzedPageEl _page, StringMap<StringList> _inh, MethodId _candidateId, CustList<Matching> _all, int _i, String _wc) {
        AnaClassArgumentMatching resArg_ = _args.get(_i);
        if (_candidateId.getParametersRef(_i) == BoolVal.TRUE) {
            Mapping map_ = new Mapping();
            map_.setArg(resArg_);
            map_.getMapping().putAllMap(_inh);
            map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
            map_.setParam(_wc);
            CustList<Matching> cts_ = inferOrImplicit(resArg_, _wc,MatchingEnum.EQ,map_.getMapping(), _page);
            //compare base of arg - param by eq and return eq constraints
            _all.addAllElts(cts_);
            return;
        }
        if (resArg_.isVariable()) {
            return;
        }
        Mapping map_ = new Mapping();
        map_.setArg(resArg_);
        map_.getMapping().putAllMap(_inh);
        map_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
        map_.setParam(_wc);
        CustList<Matching> cts_ = inferOrImplicit(resArg_, _wc,MatchingEnum.SUB,map_.getMapping(), _page);
        //compare base of arg - param by sub and return eq constraints
        _all.addAllElts(cts_);
    }

    private static CustList<Matching> retValue(Parametrable _parame, String _returnType, AnalyzedPageEl _page, StringMap<StringList> _inh, AnaGeneType _cType, String _candidate, MethodId _candidateId) {
        CustList<Matching> all_ = new CustList<Matching>();
        String returnTypeCandidate_ = _parame.getOriginalReturnType();
        String resRet_ = returnTypeCandidate_;
        if (returnTypeCandidate_.isEmpty()) {
            resRet_ = _page.getAliasVoid();
        }
        if (!StringUtil.quickEq(_page.getAliasVoid(),resRet_)&&!_returnType.isEmpty()) {
            Mapping mapRet_ = new Mapping();
            mapRet_.getMapping().putAllMap(_inh);
            mapRet_.getMapping().putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
            String arg_ = AnaInherits.quickFormat(_cType, _candidate, returnTypeCandidate_);
            mapRet_.setArg(arg_);
            mapRet_.setParam(_returnType);
            if (_candidateId.isRetRef()) {
                all_.addAllElts(inferOrImplicit(new AnaClassArgumentMatching(arg_), _returnType,MatchingEnum.EQ,mapRet_.getMapping(), _page));
            } else {
                all_.addAllElts(inferOrImplicit(new AnaClassArgumentMatching(arg_), _returnType,MatchingEnum.SUB,mapRet_.getMapping(), _page));
            }
        }
        return all_;
    }

    public static StringList tryGetDeclaredImplicitCastFct(String _classes, StringMap<String> _varsOwner, String _arg, AnalyzedPageEl _page, StringMap<StringList> _vars) {
        String arg_ = StringExpUtil.getIdFromAllTypes(_arg);
        StringList ls_ = new StringList();
        for (MethodInfo m: tryGetImplSgn(_classes,_arg,_page,_vars)) {
            String formPar_ = m.getFormatted().shiftFirst().first();
            String candidate_ = tryInfer(arg_, _varsOwner, tryInferParam(_classes, _page, _vars, _arg, formPar_, m.getReturnType()), _page);
            tryAdd(ls_,candidate_);
        }
        return ls_;
    }
    public static void tryAdd(StringList _ls, String _candidate) {
        if (_candidate != null) {
            _ls.add(_candidate);
        }
    }
    public static String tryGetDeclaredImplicitCast(String _classes, StringMap<String> _varsOwner, String _arg, AnalyzedPageEl _page, StringMap<StringList> _vars) {
        AnaGeneType anaGeneType_ = _page.getAnaGeneType(_arg);
        StringMap<StringList> map_ = inhNb(anaGeneType_);
        map_.addAllEntries(_vars);
        String geneNb_ = getGeneNb(anaGeneType_);
        ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_classes, new AnaClassArgumentMatching(geneNb_), _page, map_);
        if (res_ == null) {
            return null;
        }
        String formPar_ = res_.getId().getConstraints().shiftFirst().first();
        return tryInfer(_arg, _varsOwner,tryInferParam(_classes, _page, _vars, geneNb_, formPar_, res_.getReturnType()), _page);
    }

    private static String tryInferParam(String _classes, AnalyzedPageEl _page, StringMap<StringList> _vars, String _gene, String _formPar, String _retType) {
        String form_;
        if (isVar(_formPar)) {
            AnaGeneType typePar_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(_formPar));
            StringMap<StringList> mapTwo_ = inhNb(typePar_);
            mapTwo_.addAllEntries(_vars);
            String geneNbTwo_ = getGeneNb(typePar_);
            CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(typePar_);
            int sizeVar_ = varTypes_.size();
            CustList<Matching> cts_ = inf(_retType, _classes,mapTwo_, _page);
            cts_.addAllElts(inf(_gene, _formPar,mapTwo_, _page));
            form_ = processConstraints(geneNbTwo_, cts_, sizeVar_, mapTwo_, _page);
        } else {
            form_ = _formPar;
        }
        return form_;
    }

    private static CustList<Matching> inf(String _arg, String _par,StringMap<StringList> _inh, AnalyzedPageEl _page) {
        Mapping mapping_ = new Mapping();
        mapping_.setArg(_arg);
        mapping_.setParam(_par);
        mapping_.setMapping(_inh);
        return infer(mapping_,MatchingEnum.SUB,_page);
    }
    public static ClassMethodIdReturn tryGetDeclaredImplicitCast(String _classes, AnaClassArgumentMatching _arg, AnalyzedPageEl _page, StringMap<StringList> _vars) {
        return OperationNode.tryGetDeclaredImplicitCast(_classes, _arg, _page, _vars, new VarsComparer());
    }

    public static CustList<MethodInfo> tryGetImplSgn(String _classes, String _arg, AnalyzedPageEl _page, StringMap<StringList> _vars) {
        return OperationNode.tryGetImplSgn(_classes, _arg, _page, _vars, new VarsComparer());
    }


    public static StringMap<StringList> inhNb(AnaGeneType _sub) {
        if (_sub == null) {
            return new StringMap<StringList>();
        }
        CustList<StringList> bounds_ = ContextUtil.getBoundAllAll(_sub);
        int sizeVar_ = bounds_.size();
        String gene_ = getGeneNb(_sub);
        StringMap<StringList> inh_ = new StringMap<StringList>();
        for (int i = 0; i < sizeVar_; i++) {
            String key_ = Integer.toString(i);
            StringList vs_ = new StringList();
            for (String v: bounds_.get(i)) {
                vs_.add(AnaInherits.quickFormat(_sub,gene_,v));
            }
            inh_.addEntry(key_,vs_);
        }
        return inh_;
    }
    public static String getGeneNb(AnaGeneType _sub) {
        if (_sub == null) {
            return "";
        }
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(_sub);
        int sizeVar_ = varTypes_.size();
        StringMap<String> args_ = new StringMap<String>();
        for (int i = 0; i < sizeVar_; i++) {
            args_.addEntry(varTypes_.get(i).getName(),"#"+ i);
        }
        return StringExpUtil.getQuickFormattedType(_sub.getGenericString(),args_);
    }

    public static String tryInfer(String _erased, StringMap<String> _vars, String _declaring, AnalyzedPageEl _page) {
        AnaGeneType g_ = _page.getAnaGeneType(_erased);
        if (g_ == null) {
            return null;
        }
        String idParam_ = StringExpUtil.getIdFromAllTypes(_declaring);
        String gene_ = g_.getGenericString();
        String type_;
        if (!StringUtil.quickEq(idParam_,_erased)) {
            type_ = AnaInherits.generic(g_,idParam_);
        } else {
            if (StringUtil.quickEq(_erased, _page.getAliasFct())) {
                return _declaring;
            }
            type_ = gene_;
        }
        if (type_.isEmpty()) {
            return null;
        }
        StringList argTypes_ = new StringList();
        for (String p: StringExpUtil.getAllTypes(type_).mid(1)) {
            argTypes_.add(p);
        }
        StringList paramTypes_ = new StringList();
        for (String p: StringExpUtil.getAllTypes(_declaring).mid(1)) {
            paramTypes_.add(p);
        }
        if (argTypes_.size() != paramTypes_.size()) {
            return null;
        }
        CustList<InferenceConstraints> found_ = foundCts(argTypes_, paramTypes_);
        StringMap<StringList> multi_ = new StringMap<StringList>();
        for (String p: StringExpUtil.getAllTypes(gene_).mid(1)) {
            multi_.put(p, new StringList());
        }
        StringMap<String> vars_ = filterVars(_vars, g_);
        for (EntryCust<String,String> e: vars_.entryList()) {
            multi_.put(StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,e.getKey()), new StringList());
        }
        for (EntryCust<String,String> e: vars_.entryList()) {
            feed(multi_,StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,e.getKey()),e.getValue());
        }
        for (InferenceConstraints i: found_) {
            String argLoc_ = i.getArg();
            String paramLoc_ = i.getParam();
            feed(multi_,argLoc_,paramLoc_);
        }
        return tryBuild(_page, gene_, multi_, _page.getCurrentConstraints().getCurrentConstraints());
    }

    private static CustList<InferenceConstraints> foundCts(StringList _argTypes, StringList _paramTypes) {
        CustList<InferenceConstraints> found_ = new CustList<InferenceConstraints>();
        CustList<InferenceConstraints> ics_ = new CustList<InferenceConstraints>();
        int len_ = _argTypes.size();
        for (int i = 0; i < len_; i++) {
            InferenceConstraints i_ = new InferenceConstraints();
            i_.setArg(_argTypes.get(i));
            i_.setParam(_paramTypes.get(i));
            ics_.add(i_);
        }
        while (true) {
            CustList<InferenceConstraints> next_ = new CustList<InferenceConstraints>();
            for (InferenceConstraints i: ics_) {
                tryInferPair(found_, next_, i);
            }
            if (next_.isEmpty()) {
                break;
            }
            ics_ = next_;
        }
        return found_;
    }

    private static void tryInferPair(CustList<InferenceConstraints> _found, CustList<InferenceConstraints> _next, InferenceConstraints _i) {
        String argLoc_ = _i.getArg();
        String paramLoc_ = _i.getParam();
        if (argLoc_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            _found.add(_i);
            return;
        }
        if (argLoc_.startsWith(ARR_BEG_STRING)) {
            if (paramLoc_.startsWith(ARR_BEG_STRING)) {
                InferenceConstraints n_ = new InferenceConstraints();
                n_.setArg(argLoc_.substring(ARR_BEG_STRING.length()));
                n_.setParam(paramLoc_.substring(ARR_BEG_STRING.length()));
                _next.add(n_);
            }
            return;
        }
        if (StringUtil.quickEq(argLoc_, AnaInherits.SUB_TYPE)) {
            return;
        }
        boolean seen_ = false;
        for (String s: new StringList(AnaInherits.SUB_TYPE, AnaInherits.SUP_TYPE,"~")) {
            if (argLoc_.startsWith(s)) {
                if (paramLoc_.startsWith(s)) {
                    InferenceConstraints n_ = new InferenceConstraints();
                    n_.setArg(argLoc_.substring(s.length()));
                    n_.setParam(paramLoc_.substring(s.length()));
                    _next.add(n_);
                }
                seen_ = true;
            }
        }
        if (seen_) {
            return;
        }
        StringList nArgTypes_ = StringExpUtil.getAllTypes(argLoc_);
        StringList nParamTypes_ = StringExpUtil.getAllTypes(paramLoc_);
        if (nArgTypes_.size() != nParamTypes_.size()) {
            return;
        }
        if (!StringUtil.quickEq(nArgTypes_.first(), nParamTypes_.first())) {
            return;
        }
        int lenLoc_ = nArgTypes_.size();
        for (int j = 1; j < lenLoc_; j++) {
            InferenceConstraints i_ = new InferenceConstraints();
            i_.setArg(nArgTypes_.get(j));
            i_.setParam(nParamTypes_.get(j));
            _next.add(i_);
        }
    }

    private static StringMap<String> filterVars(StringMap<String> _vars, AnaGeneType _g) {
        CustList<TypeVar> list_ = ContextUtil.getParamTypesMapValues(_g);
        StringMap<String> vars_ = new StringMap<String>();
        for (EntryCust<String,String> e: _vars.entryList()) {
            boolean foundType_ = false;
            for (TypeVar v: list_) {
                String name_ = v.getName();
                if (StringUtil.quickEq(name_, e.getKey())) {
                    foundType_ = true;
                    break;
                }
            }
            if (foundType_) {
                vars_.addEntry(e.getKey(),e.getValue());
            }
        }
        return vars_;
    }

    private static String tryBuild(AnalyzedPageEl _page, String _gene, StringMap<StringList> _multi, StringMap<StringList> _cts) {
        StringMap<String> vars_ = new StringMap<String>();
        StringList parts_ = new StringList();
        for (EntryCust<String,StringList> e: _multi.entryList()) {
            if (!e.getValue().onlyOneElt()) {
                return null;
            }
            String value_ = e.getValue().first();
            parts_.add(value_);
            vars_.put(e.getKey().substring(1), value_);
        }
        String formattedType_ = StringExpUtil.getQuickFormattedType(_gene, vars_);
        String correct_ = AnaInherits.getCorrectTemplateAllAll(formattedType_, parts_, _cts, _page);
        if (correct_.isEmpty()) {
            return null;
        }
        return formattedType_;
    }

    private static void feed(StringMap<StringList> _multi, String _key, String _value) {
        for (EntryCust<String,StringList> e: _multi.entryList()) {
            if (StringUtil.quickEq(e.getKey(),_key)) {
                e.getValue().add(_value);
            }
        }
    }

    public static boolean isCorrectOrNumbersVars(Mapping _m, AnalyzedPageEl _page) {
        AnaClassArgumentMatching p_ = _m.getParam();
        if (_m.getParam().isVariable()) {
            return false;
        }
        if (_m.getArg().isVariable()) {
            return !AnaTypeUtil.isPrimitive(p_, _page);
        }
        return isCorrectVars(AnaInherits.transform(_m,_page), _page);
    }

    public static boolean isCorrectVars(Mapping _m, AnalyzedPageEl _page) {
        AnaClassArgumentMatching arg_ = _m.getArg();
        AnaClassArgumentMatching param_ = _m.getParam();
        StringMap<StringList> generalMapping_ = _m.getMapping();
        Mapping map_ = new Mapping();
        map_.setParam(param_);
        map_.setArg(arg_);
        map_.setMapping(generalMapping_);
        for (String p: param_.getNames()) {
            boolean ok_ = okParam(_page, arg_, generalMapping_, p);
            if (!ok_) {
                return false;
            }
        }
        return true;
    }

    private static boolean okParam(AnalyzedPageEl _page, AnaClassArgumentMatching _arg, StringMap<StringList> _generalMapping, String _p) {
        boolean ok_ = false;
        StringList names_ = _arg.getNames();
        for (String a: names_) {
            boolean okTree_ = okArgParam(_page, _generalMapping, _p, a);
            if (okTree_) {
                ok_ = true;
                break;
            }
        }
        return ok_;
    }

    private static boolean okArgParam(AnalyzedPageEl _page, StringMap<StringList> _generalMapping, String _p, String _a) {
        boolean okTree_ = true;
        CustList<Matching> all_ = new CustList<Matching>();
        CustList<Matching> matchs_ = new CustList<Matching>();
        Matching match_ = new Matching();
        match_.setArg(_a);
        match_.setParam(_p);
        matchs_.add(match_);
        all_.add(match_);
        while (true) {
            CustList<Matching> new_ = new CustList<Matching>();
            for (Matching m: matchs_) {
                String a_ = m.getArg();
                String p_ = m.getParam();
                MappingPairs m_ = getSimpleMappingVars(a_,p_, _generalMapping, _page);
                if (koTreeSec(_page,m_,all_,new_)) {
                    okTree_ = false;
                    break;
                }
            }
            if (new_.isEmpty() || !okTree_) {
                break;
            }
            matchs_ = new_;
        }
        return okTree_;
    }

    private static boolean koTreeSec(AnalyzedPageEl _page, MappingPairs _m, CustList<Matching> _all, CustList<Matching> _nw) {
        if (_m == null) {
            return true;
        }
        for (Matching n: _m.getPairsArgParam()) {
            if (koTree(_page,n,_all,_nw)) {
                return true;
            }
        }
        return false;
    }
    private static boolean koTree(AnalyzedPageEl _page,Matching _n,CustList<Matching> _all,CustList<Matching> _nw) {
        String baseArrayParamNext_ = StringExpUtil.getQuickComponentBase(_n.getParam());
        String baseArrayArgNext_ = StringExpUtil.getQuickComponentBase(_n.getArg());
        if (StringUtil.quickEq(_n.getParam(), _n.getArg()) || tryGetUnknownVar(baseArrayParamNext_) >= 0 || tryGetUnknownVar(baseArrayArgNext_) >= 0) {
            return false;
        }
        if (_n.getMatchEq() == MatchingEnum.EQ) {
            StringList foundArgTypes_ = StringExpUtil.getAllTypes(_n.getArg());
            StringList foundParamTypes_ = StringExpUtil.getAllTypes(_n.getParam());
            int len_ = foundArgTypes_.size();
            if (foundParamTypes_.size() != len_ || len_ > 1 && !StringUtil.quickEq(foundArgTypes_.first(), foundParamTypes_.first())) {
                return true;
            }
            if (isVar(_n.getParam()) || isVar(_n.getArg())) {
                _nw.add(_n);
                return false;
            }
            return true;
        }
        AbstractInheritProcess.tryAddNext(_all,_nw,_n, _page.getChecker(),MatchingEnum.EQ);
        return false;
    }

    public static boolean isVar(String _var) {
        int len_ = _var.length();
        int i_ = 0;
        while (i_ < len_) {
            char ch_ = _var.charAt(i_);
            if (ch_ == '#' && i_ + 1 < len_ && StringExpUtil.isDigit(_var.charAt(i_ + 1))) {
                return true;
            }
            i_++;
        }
        return false;
    }
    public static String processConstraints(String _gene,CustList<Matching> _infer, int _max, StringMap<StringList> _vars,AnalyzedPageEl _page) {
        StringMap<StringList> multi_ = processConstraints(_infer, _max);
        if (multi_ == null) {
            return "";
        }
        reduceConstraints(multi_,_vars,_page);
        return StringUtil.nullToEmpty(tryBuild(_page,_gene,multi_, _vars));
    }
    private static void reduceConstraints(StringMap<StringList> _cts, StringMap<StringList> _vars,AnalyzedPageEl _page) {
        for (EntryCust<String,StringList> e: _cts.entryList()) {
            e.setValue(AnaTypeUtil.getSubTypes(e.getValue(),_vars,_page));
        }
    }
    public static StringMap<StringList> processConstraints(CustList<Matching> _infer, int _max) {
        CustList<Matching> list_ = resolveEq(mergeEq(resolveEq(removeDup(_infer), _max)), _max);
        reverseEq(list_);
        return checkIfPresComplete(removeDup(list_),_max);
    }
    private static StringMap<StringList> checkIfPresComplete(CustList<Matching> _infer, int _max) {
        if (_infer == null) {
            return null;
        }
        StringMap<StringList> multi_ = new StringMap<StringList>();
        StringMap<StringList> addon_ = new StringMap<StringList>();
        for (int i = 0; i < _max; i++) {
            multi_.addEntry("#"+i,new StringList());
            addon_.addEntry("#"+i,new StringList());
        }
        CustList<Matching> eqs_ = new CustList<Matching>();
        for (Matching m: _infer) {
            if (m.getMatchEq() != MatchingEnum.EQ) {
                continue;
            }
            eqs_.add(m);
        }
        for (int i = 0; i < _max; i++) {
            StringList cts_ = multi_.getValue(i);
            feedCts(eqs_, i, cts_);
        }
        for (int i = 0; i < _max; i++) {
            if (!multi_.getValue(i).isEmpty()) {
                continue;
            }
            StringList ls_ = addon_.getValue(i);
            feedCts(_infer, i, ls_);
        }
        for (int i = 0; i < _max; i++) {
            StringList cts_ = multi_.getValue(i);
            cts_.addAllElts(addon_.getValue(i));
        }
        return multi_;
    }

    private static void feedCts(CustList<Matching> _list, int _i, StringList _dest) {
        for (Matching m : _list) {
            String resVar_ = m.getParam();
            String value_ = m.getArg();
            String solvedValue_ = "";
            int m_ = -1;
            int nbResVar_ = tryGetUnknownVar(resVar_);
            int nbValue_ = tryGetUnknownVar(value_);
            if (nbResVar_ >= 0) {
                solvedValue_ = value_;
                m_ =nbResVar_;
            } else if (nbValue_ >= 0) {
                solvedValue_ = resVar_;
                m_ = nbValue_;
            }
            if (m_ == _i) {
                _dest.add(solvedValue_);
                break;
            }
        }
    }

    public static CustList<Matching> mergeEq(CustList<Matching> _infer) {
        if (_infer == null) {
            return null;
        }
        int len_ = _infer.size();
        CustList<Matching> l_ = new CustList<Matching>();
        for (int i = 0; i < len_; i++) {
            loopMergeEq(_infer, l_, i);
        }
        return l_;
    }

    private static void loopMergeEq(CustList<Matching> _infer, CustList<Matching> _l, int _i) {
        int len_ = _infer.size();
        Matching matching_ = _infer.get(_i);
        if (matching_.getMatchEq() == MatchingEnum.EQ) {
            _l.add(matching_);
            return;
        }
        if (matching_.getMatchEq() == null) {
            return;
        }
        for (int j = 0; j < len_; j++) {
            if (_i != j && _infer.get(j).getMatchEq() != MatchingEnum.EQ) {
                Matching other_ = _infer.get(j);
                if (areCompl(matching_, other_) && AbstractInheritProcess.areTypePairs(matching_, other_) || matching_.getMatchEq() == other_.getMatchEq() && areTypeCross(matching_, other_)) {
                    matching_.setMatchEq(MatchingEnum.EQ);
                    other_.setMatchEq(null);
                    break;
                }
            }
        }
        _l.add(matching_);
    }

    public static CustList<Matching> resolveEq(CustList<Matching> _infer, int _max) {
        if (_infer == null) {
            return null;
        }
        int len_ = _infer.size();
        for (int k = 0; k < _max; k++) {
            for (int i = 0; i < len_; i++) {
                loopEq(_infer, k, i);
            }
        }
        return _infer;
    }

    private static void loopEq(CustList<Matching> _infer, int _k, int _i) {
        int len_ = _infer.size();
        Matching matching_ = _infer.get(_i);
        if (matching_.getMatchEq() != MatchingEnum.EQ) {
            return;
        }
        String resVar_ = matching_.getParam();
        String value_ = matching_.getArg();
        int nbResVar_ = tryGetUnknownVar(resVar_);
        int nbResValue_ = tryGetUnknownVar(value_);
        if (nbResVar_ == _k && nbResValue_ <0) {
            for (int j = 0; j < len_; j++) {
                if (_i == j) {
                    continue;
                }
                Matching other_ = _infer.get(j);
                StringMap<String> varTypes_ = new StringMap<String>();
                varTypes_.addEntry(Integer.toString(_k),value_);
                other_.setParam(StringExpUtil.getQuickFormattedType(other_.getParam(), varTypes_));
                other_.setArg(StringExpUtil.getQuickFormattedType(other_.getArg(), varTypes_));
            }
            return;
        }
        if (nbResValue_ == _k && nbResVar_ <0) {
            for (int j = 0; j < len_; j++) {
                if (_i == j) {
                    continue;
                }
                Matching other_ = _infer.get(j);
                StringMap<String> varTypes_ = new StringMap<String>();
                varTypes_.addEntry(Integer.toString(_k),resVar_);
                other_.setParam(StringExpUtil.getQuickFormattedType(other_.getParam(), varTypes_));
                other_.setArg(StringExpUtil.getQuickFormattedType(other_.getArg(), varTypes_));
            }
        }
    }

    public static CustList<Matching> removeDup(CustList<Matching> _infer) {
        if (_infer == null) {
            return null;
        }
        CustList<Matching> l_ = new CustList<Matching>();
        for (Matching m: _infer) {
            boolean add_ = true;
            for (Matching n: l_) {
                if (exitRemDup(m,n)) {
                    add_ = false;
                    break;
                }
            }
            if (add_) {
                l_.add(m);
            }
        }
        return l_;
    }
    private static boolean exitRemDup(Matching _m,Matching _n) {
        if (_m.getMatchEq() == MatchingEnum.EQ
                &&_n.getMatchEq() == MatchingEnum.EQ) {
            return AbstractInheritProcess.areTypePairs(_m, _n)
                    || areTypeCross(_m, _n);
        }
        if (areCompl(_m, _n)) {
            return areTypeCross(_m, _n);
        }
        return _m.getMatchEq() == _n.getMatchEq() && AbstractInheritProcess.areTypePairs(_m, _n);
    }

    private static boolean areCompl(Matching _m, Matching _n) {
        return _m.getMatchEq() == MatchingEnum.SUB
                && _n.getMatchEq() == MatchingEnum.SUP
                || _n.getMatchEq() == MatchingEnum.SUB
                && _m.getMatchEq() == MatchingEnum.SUP;
    }

    public static void reverseEq(CustList<Matching> _infer) {
        if (_infer == null) {
            return;
        }
        for (Matching m: _infer) {
            if (m.getMatchEq() == MatchingEnum.EQ) {
                String arg_ = m.getArg();
                String baseArrayArgNext_ = StringExpUtil.getQuickComponentBase(arg_);
                if (tryGetUnknownVar(baseArrayArgNext_) >= 0) {
                    m.setArg(m.getParam());
                    m.setParam(arg_);
                }
                continue;
            }
            if (m.getMatchEq() == MatchingEnum.SUP) {
                String arg_ = m.getArg();
                m.setArg(m.getParam());
                m.setParam(arg_);
                m.setMatchEq(MatchingEnum.SUB);
            }
        }
    }
    private static boolean areTypeCross(Matching _m, Matching _n) {
        return StringUtil.quickEq(_m.getArg(), _n.getParam())
                &&StringUtil.quickEq(_m.getParam(), _n.getArg());
    }

    public static CustList<Matching> inferOrImplicit(AnaClassArgumentMatching _arg,String _param, MatchingEnum _base, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        String refTypeArgId_ = StringExpUtil.getIdFromAllTypes(_arg.getSingleNameOrEmpty());
        String refTypeParamId_ = StringExpUtil.getIdFromAllTypes(_param);
        AnaGeneType argType_ = _page.getAnaGeneType(refTypeArgId_);
        Mapping map_ = new Mapping();
        map_.setArg(_arg);
        map_.getMapping().putAllMap(_vars);
        map_.setParam(_param);
        if (argType_ != null && !argType_.isSubTypeOf(refTypeParamId_,_page)) {
            ClassMethodIdReturn result_ = tryGetDeclaredImplicitCast(_param, _arg, _page, _vars);
            if (result_ != null) {
                map_.setArg(result_.getReturnType());
            }
        }
        return infer(map_,_base,_page);
    }
    public static CustList<Matching> infer(Mapping _m, MatchingEnum _base, AnalyzedPageEl _page) {
        CustList<Matching> constraints_ = new CustList<Matching>();
        AnaClassArgumentMatching arg_ = _m.getArg();
        AnaClassArgumentMatching param_ = _m.getParam();
        StringMap<StringList> generalMapping_ = _m.getMapping();
        Mapping map_ = new Mapping();
        //param is for example Tmp<#0>; Tmp<#0,#1> ...
        map_.setParam(param_);
        map_.setArg(arg_);
        map_.setMapping(generalMapping_);
        for (String p: param_.getNames()) {
            StringList names_ = arg_.getNames();
            for (String a: names_) {
                CustList<Matching> all_ = new CustList<Matching>();
                CustList<Matching> matchs_ = new CustList<Matching>();
                Matching match_ = new Matching();
                match_.setArg(a);
                match_.setParam(p);
                match_.setMatchEq(_base);
                matchs_.add(match_);
                all_.add(match_);
                while (true) {
                    CustList<Matching> new_ = nextInfers(_base, _page, constraints_, generalMapping_, all_, matchs_);
                    if (new_.isEmpty()) {
                        break;
                    }
                    matchs_ = new_;
                }
            }
        }
        //replace other variable by solved one
        //try replace numbered variables in param type, if incompat => false
        return constraints_;
    }

    private static CustList<Matching> nextInfers(MatchingEnum _base, AnalyzedPageEl _page, CustList<Matching> _cts, StringMap<StringList> _geneMapping, CustList<Matching> _all, CustList<Matching> _matches) {
        CustList<Matching> new_ = new CustList<Matching>();
        for (Matching m: _matches) {
            String a_ = m.getArg();
            String p_ = m.getParam();
            MappingPairs m_ = getSimpleInferredMapping(a_,p_, _geneMapping, _page, m.getMatchEq());
            if (m_ == null) {
                continue;
            }
            for (Matching n: m_.getPairsArgParam()) {
                inferNextPair(_base, _page, _cts, _all, new_, n);
            }
        }
        return new_;
    }

    private static void inferNextPair(MatchingEnum _base, AnalyzedPageEl _page, CustList<Matching> _cts, CustList<Matching> _all, CustList<Matching> _nw, Matching _n) {
        if (StringUtil.quickEq(_n.getParam(), _n.getArg())) {
            return;
        }
        String baseArrayParamNext_ = StringExpUtil.getQuickComponentBase(_n.getParam());
        String baseArrayArgNext_ = StringExpUtil.getQuickComponentBase(_n.getArg());
        if (tryGetUnknownVar(baseArrayParamNext_) >= 0 || tryGetUnknownVar(baseArrayArgNext_) >= 0) {
            _cts.add(_n);
            return;
        }
        if (_n.getMatchEq() == MatchingEnum.EQ) {
            StringList foundArgTypes_ = StringExpUtil.getAllTypes(_n.getArg());
            StringList foundParamTypes_ = StringExpUtil.getAllTypes(_n.getParam());
            int len_ = foundArgTypes_.size();
            if (foundParamTypes_.size() != len_) {
                return;
            }
            if (!StringUtil.quickEq(foundArgTypes_.first(),foundParamTypes_.first())) {
                return;
            }
            _nw.add(_n);
            return;
        }
        AbstractInheritProcess.tryAddNext(_all, _nw, _n, _page.getChecker(), _base);
    }

    private static int tryGetUnknownVar(String _baseArrayArg) {
        int var_ = -1;
        String af_ = "";
        if (_baseArrayArg.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            af_ = _baseArrayArg.substring(1);
        }
        if (!af_.isEmpty() && StringExpUtil.isDigit(af_.charAt(0))) {
            var_ = NumberUtil.parseInt(af_);
        }
        return var_;
    }

    private static MappingPairs getSimpleMappingVars(String _arg, String _param, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayParam_ = dParam_.getComponent();
        Mapping map_ = new Mapping();
        map_.setMapping(_inherit);
        if (baseArrayParam_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            if (_arg.isEmpty()) {
                return new MappingPairs();
            }
            int min_ = NumberUtil.min(dArg_.getDim(), dParam_.getDim());
            if (tryGetUnknownVar(baseArrayParam_)>=0) {
                MappingPairs pairs_ = new MappingPairs();
                CustList<Matching> pairsArgParam_ = new CustList<Matching>();
                Matching match_ = new Matching();
                match_.setMatchEq(MatchingEnum.SUB);
                match_.setArg(StringExpUtil.getQuickComponentType(_arg,min_));
                match_.setParam(StringExpUtil.getQuickComponentType(_param,min_));
                pairsArgParam_.add(match_);
                pairs_.setPairsArgParam(pairsArgParam_);
                return pairs_;
            }
            return AnaInherits.commonTypeVar(dArg_, dParam_, map_);
        }
        return commonTypePair(_arg, _param, _inherit, _page, MatchingEnum.SUB);
    }

    private static MappingPairs commonTypePair(String _arg, String _param, StringMap<StringList> _inherit, AnalyzedPageEl _page, MatchingEnum _sub) {
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayArg_ = dArg_.getComponent();
        if (tryGetUnknownVar(baseArrayArg_) >= 0) {
            int min_ = NumberUtil.min(dArg_.getDim(), dParam_.getDim());
            MappingPairs pairs_ = new MappingPairs();
            CustList<Matching> pairsArgParam_ = new CustList<Matching>();
            Matching match_ = new Matching();
            match_.setMatchEq(_sub);
            match_.setArg(StringExpUtil.getQuickComponentType(_arg, min_));
            match_.setParam(StringExpUtil.getQuickComponentType(_param, min_));
            pairsArgParam_.add(match_);
            pairs_.setPairsArgParam(pairsArgParam_);
            return pairs_;
        }
        return AnaInherits.getCommentMappingPairs(_arg, _param, _inherit, _page);
    }

    private static MappingPairs getSimpleInferredMapping(String _arg, String _param, StringMap<StringList> _inherit, AnalyzedPageEl _page, MatchingEnum _ct) {
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayParam_ = dParam_.getComponent();
        String baseArrayArg_ = dArg_.getComponent();
        Mapping map_ = new Mapping();
        map_.setMapping(_inherit);
        if (_ct != MatchingEnum.EQ) {
            if (baseArrayParam_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                return getSimpleInferredMappingVar(_arg, _param, _ct, dArg_, dParam_);
            }
            return commonTypePair(_arg, _param, _inherit, _page, _ct);
        }
        if (baseArrayParam_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            return getSimpleInferredMappingVar(_arg, _param, _ct, dArg_, dParam_);
        }
        int min_ = NumberUtil.min(dArg_.getDim(), dParam_.getDim());
        if (tryGetUnknownVar(baseArrayArg_) >= 0) {
            MappingPairs pairs_ = new MappingPairs();
            CustList<Matching> pairsArgParam_ = new CustList<Matching>();
            Matching match_ = new Matching();
            match_.setMatchEq(_ct);
            match_.setArg(StringExpUtil.getQuickComponentType(_arg, min_));
            match_.setParam(StringExpUtil.getQuickComponentType(_param, min_));
            pairsArgParam_.add(match_);
            pairs_.setPairsArgParam(pairsArgParam_);
            return pairs_;
        }
        if (dArg_.getDim() != dParam_.getDim()) {
            return null;
        }
        StringList foundArgTypes_ = StringExpUtil.getAllTypes(baseArrayArg_);
        StringList foundParamTypes_ = StringExpUtil.getAllTypes(baseArrayParam_);
        int len_ = foundArgTypes_.size();
        if (foundParamTypes_.size() != len_ || !StringUtil.quickEq(foundArgTypes_.first(), foundParamTypes_.first())) {
            return null;
        }
        MappingPairs pairs_ = new MappingPairs();
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
        for (int i = 1; i < len_; i++) {
            String cArg_ = foundArgTypes_.get(i);
            String cPar_ = foundParamTypes_.get(i);
            String prArg_ = getPrefix(cArg_);
            String prPar_ = getPrefix(cPar_);
            if (!StringUtil.quickEq(
                    prArg_,
                    prPar_)) {
                return null;
            }
            Matching match_ = new Matching();
            match_.setMatchEq(_ct);
            match_.setArg(cArg_.substring(prArg_.length()));
            match_.setParam(cPar_.substring(prPar_.length()));
            pairsArgParam_.add(match_);
        }
        pairs_.setPairsArgParam(pairsArgParam_);
        return pairs_;
    }

    private static MappingPairs getSimpleInferredMappingVar(String _arg, String _param, MatchingEnum _ct, DimComp _dArg, DimComp _dParam) {
        String baseArrayParam_ = _dParam.getComponent();
        if (_arg.isEmpty()) {
            return new MappingPairs();
        }
        int min_ = NumberUtil.min(_dArg.getDim(), _dParam.getDim());
        if (tryGetUnknownVar(baseArrayParam_)>=0) {
            MappingPairs pairs_ = new MappingPairs();
            CustList<Matching> pairsArgParam_ = new CustList<Matching>();
            Matching match_ = new Matching();
            match_.setMatchEq(_ct);
            match_.setArg(StringExpUtil.getQuickComponentType(_arg,min_));
            match_.setParam(StringExpUtil.getQuickComponentType(_param,min_));
            pairsArgParam_.add(match_);
            pairs_.setPairsArgParam(pairsArgParam_);
            return pairs_;
        }
        return new MappingPairs();
    }

    private static String getPrefix(String _str) {
        for (String s: new StringList(AnaInherits.SUB_TYPE, AnaInherits.SUP_TYPE,"~")) {
            if (_str.startsWith(s)) {
                return s;
            }
        }
        return "";
    }

}
