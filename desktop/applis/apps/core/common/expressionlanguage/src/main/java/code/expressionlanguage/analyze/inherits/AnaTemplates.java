package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.VarsComparer;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;

import code.util.*;
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
            if (!StringUtil.quickEq(c.getConstId().getName(),_res.getConstId().getName())) {
                continue;
            }
            if (!c.getRealId().eq(_res.getRealId())) {
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
            if (c.getAncestor() != _res.getAncestor()) {
                continue;
            }
            ClassMethodId cur_ = new ClassMethodId(c.getRealClass(),c.getRealId());
            if (!cur_.eq(input_)) {
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
            if (p.isEmpty()) {
                continue;
            }
            if (StringExpUtil.isDollarWord(p.trim())) {
                continue;
            }
            return null;
        }
        return tr_;
    }

    public static String tryInferMethodByOneArg(String _owner,
                                                int _index, MethodId _candidate, String _staticCall, StringMap<StringList> _vars,
                                                AnaClassArgumentMatching _arg,
                                                String _returnCandidate, String _returnType,
                                                AnalyzedPageEl _page) {
        AnaGeneType sub_ = _page.getAnaGeneType(_staticCall);
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(sub_);
        int sizeVar_ = varTypes_.size();
        String genericString_ = getGeneNb(sub_);
        CustList<Matching> all_ = tryInferMethodByOneArgList(_owner, _index, _candidate, _staticCall, _vars, _arg, _returnCandidate, _returnType, _page);
        return processConstraints(genericString_,all_, sizeVar_,_vars,_page);
    }

    public static CustList<Matching> tryInferMethodByOneArgList(String _owner,
                                                                int _index, MethodId _candidate, String _staticCall, StringMap<StringList> _vars,
                                                                AnaClassArgumentMatching _arg,
                                                                String _returnCandidate, String _returnType,
                                                                AnalyzedPageEl _page) {
        AnaGeneType sub_ = _page.getAnaGeneType(_staticCall);
        if (sub_ == null) {
            return new CustList<Matching>();
        }
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(sub_);
        int sizeVar_ = varTypes_.size();
        StringMap<StringList> inh_ = inhNb(sub_);
        String genericString_ = getGeneNb(sub_);
        String idOwner_ = StringExpUtil.getIdFromAllTypes(_owner);
        AnaGeneType cType_ = _page.getAnaGeneType(idOwner_);
        if (cType_ == null) {
            return new CustList<Matching>();
        }
        String candidate_ = AnaInherits.getFullTypeByBases(sub_, genericString_, idOwner_, _page);
        CustList<Matching> all_ = new CustList<Matching>();
        String resRet_ = _returnCandidate;
        if (_returnCandidate.isEmpty()) {
            resRet_ = _page.getAliasVoid();
        }
        if (!StringUtil.quickEq(_page.getAliasVoid(),resRet_)&&!_returnType.isEmpty()) {
            Mapping mapRet_ = new Mapping();
            mapRet_.getMapping().putAllMap(inh_);
            mapRet_.getMapping().putAllMap(_vars);
            String arg_ = AnaInherits.quickFormat(cType_, candidate_, _returnCandidate);
            mapRet_.setArg(arg_);
            mapRet_.setParam(_returnType);
            if (_candidate.isRetRef()) {
                all_.addAllElts(inferOrImplicit(new AnaClassArgumentMatching(arg_),_returnType,MatchingEnum.EQ,mapRet_.getMapping(), _page));
            } else {
                all_.addAllElts(inferOrImplicit(new AnaClassArgumentMatching(arg_),_returnType,MatchingEnum.SUB,mapRet_.getMapping(), _page));
            }
        }
        int len_ = _candidate.getParametersTypesLength();
        if (_candidate.isVararg()) {
            if (_index > -1) {
                if (_index < len_ -1) {
                    String wc_ = _candidate.getParametersType(_index);
                    wc_ = AnaInherits.quickFormat(cType_,candidate_,wc_);
                    if (_candidate.getParametersRef(_index)) {
                        Mapping map_ = new Mapping();
                        map_.setArg(_arg);
                        map_.getMapping().putAllMap(inh_);
                        map_.getMapping().putAllMap(_vars);
                        map_.setParam(wc_);
                        CustList<Matching> cts_ = inferOrImplicit(_arg,wc_,MatchingEnum.EQ,map_.getMapping(), _page);
                        //compare base of arg - param by eq and return eq constraints
                        all_.addAllElts(cts_);
                    } else {
                        if (!_arg.isVariable()) {
                            Mapping map_ = new Mapping();
                            map_.setArg(_arg);
                            map_.getMapping().putAllMap(inh_);
                            map_.getMapping().putAllMap(_vars);
                            map_.setParam(wc_);
                            CustList<Matching> cts_ = inferOrImplicit(_arg,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
                            //compare base of arg - param by sub and return eq constraints
                            all_.addAllElts(cts_);
                        }
                    }
                    return all_;
                }
                if (_index < len_) {
                    int last_ = len_ - 1;
                    Mapping map_ = new Mapping();
                    String wc_ = _candidate.getParametersType(last_);
                    wc_ = AnaInherits.quickFormat(cType_,candidate_,wc_);
                    if (_candidate.getParametersRef(last_)) {
                        map_.setArg(_arg);
                        map_.getMapping().putAllMap(inh_);
                        map_.getMapping().putAllMap(_vars);
                        map_.setParam(StringExpUtil.getPrettyArrayType(wc_));
                        CustList<Matching> cts_ = inferOrImplicit(_arg,StringExpUtil.getPrettyArrayType(wc_),MatchingEnum.EQ,map_.getMapping(), _page);
                        all_.addAllElts(cts_);
                        return all_;
                    }
                    map_.setArg(_arg);
                    map_.getMapping().putAllMap(inh_);
                    map_.getMapping().putAllMap(_vars);
                    String arr_ = StringExpUtil.getPrettyArrayType(wc_);
                    map_.setParam(arr_);
                    CustList<Matching> cts_ = inferOrImplicit(_arg,arr_,MatchingEnum.SUB,map_.getMapping(), _page);
                    //compare base of arg - param by sub and return eq constraints
                    CustList<Matching> attempt_ = new CustList<Matching>(all_);
                    attempt_.addAllElts(cts_);
                    //try infer here
                    String tried_ = processConstraints(genericString_, attempt_, sizeVar_,_vars, _page);
                    if (!tried_.isEmpty()) {
                        return attempt_;
                    }
                    map_.setParam(wc_);
                    CustList<Matching> cts2_ = inferOrImplicit(_arg,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
                    //else compare base of arg - param by sub and return eq constraints and roll back previous
                    all_.addAllElts(cts2_);
                    return all_;
                }
                int last_ = len_ - 1;
                Mapping map_ = new Mapping();
                map_.getMapping().putAllMap(inh_);
                map_.getMapping().putAllMap(_vars);
                String wc_ = _candidate.getParametersType(last_);
                wc_ = AnaInherits.quickFormat(cType_,candidate_,wc_);
                map_.setParam(wc_);
                map_.setArg(_arg);
                CustList<Matching> cts_ = inferOrImplicit(_arg,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
                //compare base of arg - param by sub and return eq constraints
                all_.addAllElts(cts_);
                return all_;
            }
        }
        if (_index > -1 && _index < len_) {
            String wc_ = _candidate.getParametersType(_index);
            wc_ = AnaInherits.quickFormat(cType_,candidate_,wc_);
            if (_candidate.getParametersRef(_index)) {
                Mapping map_ = new Mapping();
                map_.setArg(_arg);
                map_.getMapping().putAllMap(inh_);
                map_.getMapping().putAllMap(_vars);
                map_.setParam(wc_);
                CustList<Matching> cts_ = inferOrImplicit(_arg,wc_,MatchingEnum.EQ,map_.getMapping(), _page);
                //compare base of arg - param by eq and return eq constraints
                all_.addAllElts(cts_);
            } else {
                if (!_arg.isVariable()) {
                    Mapping map_ = new Mapping();
                    map_.setArg(_arg);
                    map_.getMapping().putAllMap(inh_);
                    map_.getMapping().putAllMap(_vars);
                    map_.setParam(wc_);
                    CustList<Matching> cts_ = inferOrImplicit(_arg,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
                    //compare base of arg - param by sub and return eq constraints
                    all_.addAllElts(cts_);
                }
            }
        }
        return all_;
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
    public static String tryInferMethod(int _varargOnly, String _owner,
                                        MethodId _candidate, String _staticCall, StringMap<StringList> _vars,
                                        CustList<AnaClassArgumentMatching> _args,
                                        String _returnCandidate, String _returnType,
                                        AnalyzedPageEl _page) {
        AnaGeneType sub_ = _page.getAnaGeneType(_staticCall);
        if (sub_ == null) {
            return "";
        }
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(sub_);
        int sizeVar_ = varTypes_.size();
        StringMap<StringList> inh_ = inhNb(sub_);
        String genericString_ = getGeneNb(sub_);
        String idOwner_ = StringExpUtil.getIdFromAllTypes(_owner);
        AnaGeneType cType_ = _page.getAnaGeneType(idOwner_);
        if (cType_ == null) {
            return "";
        }
        String candidate_ = AnaInherits.getFullTypeByBases(sub_, genericString_, idOwner_, _page);
        int allArgsLen_ = _args.size();
        int startOpt_ = allArgsLen_;
        boolean checkOnlyDem_ = true;
        int paramLen_ = _candidate.getParametersTypesLength();
        boolean vararg_ = _candidate.isVararg();
        if (!vararg_) {
            if (paramLen_ != startOpt_) {
                return "";
            }
        } else {
            if (paramLen_ > startOpt_ + 1) {
                return "";
            }
            if (_varargOnly != 0) {
                checkOnlyDem_ = false;
                startOpt_ = paramLen_ - 1;
            }
            if (_varargOnly > 0) {
                if (startOpt_ != _varargOnly - 1) {
                    return "";
                }
            } else if (_varargOnly == 0) {
                if (paramLen_ -1 != startOpt_) {
                    return "";
                }
            }
        }
        CustList<Matching> all_ = new CustList<Matching>();
        String resRet_ = _returnCandidate;
        if (_returnCandidate.isEmpty()) {
            resRet_ = _page.getAliasVoid();
        }
        if (!StringUtil.quickEq(_page.getAliasVoid(),resRet_)&&!_returnType.isEmpty()) {
            Mapping mapRet_ = new Mapping();
            mapRet_.getMapping().putAllMap(inh_);
            mapRet_.getMapping().putAllMap(_vars);
            String arg_ = AnaInherits.quickFormat(cType_, candidate_, _returnCandidate);
            mapRet_.setArg(arg_);
            mapRet_.setParam(_returnType);
            if (_candidate.isRetRef()) {
                all_.addAllElts(inferOrImplicit(new AnaClassArgumentMatching(arg_),_returnType,MatchingEnum.EQ,mapRet_.getMapping(), _page));
            } else {
                all_.addAllElts(inferOrImplicit(new AnaClassArgumentMatching(arg_),_returnType,MatchingEnum.SUB,mapRet_.getMapping(), _page));
            }
        }
        for (int i = IndexConstants.FIRST_INDEX; i < startOpt_; i++) {
            String wc_ = _candidate.getParametersType(i);
            wc_ = AnaInherits.quickFormat(cType_,candidate_,wc_);
            AnaClassArgumentMatching resArg_ = _args.get(i);
            if (_candidate.getParametersRef(i)) {
                Mapping map_ = new Mapping();
                map_.setArg(resArg_);
                map_.getMapping().putAllMap(inh_);
                map_.getMapping().putAllMap(_vars);
                map_.setParam(wc_);
                CustList<Matching> cts_ = inferOrImplicit(resArg_,wc_,MatchingEnum.EQ,map_.getMapping(), _page);
                //compare base of arg - param by eq and return eq constraints
                all_.addAllElts(cts_);
                continue;
            }
            if (resArg_.isVariable()) {
                continue;
            }
            Mapping map_ = new Mapping();
            map_.setArg(resArg_);
            map_.getMapping().putAllMap(inh_);
            map_.getMapping().putAllMap(_vars);
            map_.setParam(wc_);
            CustList<Matching> cts_ = inferOrImplicit(resArg_,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
            //compare base of arg - param by sub and return eq constraints
            all_.addAllElts(cts_);
        }
        if (checkOnlyDem_) {
            return processConstraints(genericString_,all_, sizeVar_,_vars,_page);
        }
        int last_ = paramLen_ - 1;
        if (paramLen_ == allArgsLen_) {
            Mapping map_ = new Mapping();
            String wc_ = _candidate.getParametersType(last_);
            wc_ = AnaInherits.quickFormat(cType_,candidate_,wc_);
            AnaClassArgumentMatching resArg_ = _args.last();
            if (_candidate.getParametersRef(last_)) {
                map_.setArg(resArg_);
                map_.getMapping().putAllMap(inh_);
                map_.getMapping().putAllMap(_vars);
                map_.setParam(StringExpUtil.getPrettyArrayType(wc_));
                CustList<Matching> cts_ = inferOrImplicit(resArg_,StringExpUtil.getPrettyArrayType(wc_),MatchingEnum.EQ,map_.getMapping(), _page);
                all_.addAllElts(cts_);
                return processConstraints(genericString_,all_, sizeVar_,_vars,_page);
            }
            map_.setArg(resArg_);
            map_.getMapping().putAllMap(inh_);
            map_.getMapping().putAllMap(_vars);
            String arr_ = StringExpUtil.getPrettyArrayType(wc_);
            map_.setParam(arr_);
            CustList<Matching> cts_ = inferOrImplicit(resArg_,arr_,MatchingEnum.SUB,map_.getMapping(), _page);
            //compare base of arg - param by sub and return eq constraints
            CustList<Matching> attempt_ = new CustList<Matching>(all_);
            attempt_.addAllElts(cts_);
            //try infer here
            String tried_ = processConstraints(genericString_, attempt_, sizeVar_,_vars, _page);
            if (!tried_.isEmpty()) {
                return tried_;
            }
            map_.setParam(wc_);
            CustList<Matching> cts2_ = inferOrImplicit(resArg_,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
            //else compare base of arg - param by sub and return eq constraints and roll back previous
            all_.addAllElts(cts2_);
            return processConstraints(genericString_, all_, sizeVar_,_vars, _page);
        }
        Mapping map_ = new Mapping();
        map_.getMapping().putAllMap(inh_);
        map_.getMapping().putAllMap(_vars);
        String wc_ = _candidate.getParametersType(last_);
        wc_ = AnaInherits.quickFormat(cType_,candidate_,wc_);
        map_.setParam(wc_);
        for (int i = startOpt_; i < allArgsLen_; i++) {
            AnaClassArgumentMatching a_ = _args.get(i);
            map_.setArg(a_);
            CustList<Matching> cts_ = inferOrImplicit(a_,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
            //compare base of arg - param by sub and return eq constraints
            all_.addAllElts(cts_);
        }
        //try infer here
        return processConstraints(genericString_, all_, sizeVar_,_vars, _page);
    }

    public static StringList tryGetDeclaredImplicitCastFct(String _classes, StringMap<String> _varsOwner, String _arg, AnalyzedPageEl _page, StringMap<StringList> _vars) {
        String arg_ = StringExpUtil.getIdFromAllTypes(_arg);
        StringList ls_ = new StringList();
        for (MethodInfo m: tryGetImplSgn(_classes,_arg,_page,_vars)) {
            String formPar_ = m.getFormatted().shiftFirst().first();
            String candidate_ = tryInferParam(_classes, _varsOwner, _page, _vars, arg_, _arg, formPar_, m.getReturnType());
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
        if (!res_.isFoundMethod()) {
            return null;
        }
        String formPar_ = res_.getId().getConstraints().shiftFirst().first();
        return tryInferParam(_classes, _varsOwner, _page, _vars, _arg, geneNb_, formPar_, res_.getReturnType());
    }

    private static String tryInferParam(String _classes, StringMap<String> _varsOwner, AnalyzedPageEl _page, StringMap<StringList> _vars, String _arg, String _gene, String _formPar, String _retType) {
        if (isVar(_formPar)) {
            AnaGeneType typePar_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(_formPar));
            StringMap<StringList> mapTwo_ = inhNb(typePar_);
            mapTwo_.addAllEntries(_vars);
            String geneNbTwo_ = getGeneNb(typePar_);
            CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(typePar_);
            int sizeVar_ = varTypes_.size();
            CustList<Matching> cts_ = inf(_retType,_classes,mapTwo_, _page);
            cts_.addAllElts(inf(_gene,_formPar,mapTwo_, _page));
            String formParam_ = processConstraints(geneNbTwo_, cts_, sizeVar_, mapTwo_, _page);
            return tryInfer(_arg,_varsOwner,formParam_,_page);
        }
        return tryInfer(_arg,_varsOwner,_formPar,_page);
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
        String type_ = "";
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
        CustList<InferenceConstraints> ics_ = new CustList<InferenceConstraints>();
        CustList<InferenceConstraints> found_ = new CustList<InferenceConstraints>();
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
        int len_ = argTypes_.size();
        for (int i = 0; i < len_; i++) {
            InferenceConstraints i_ = new InferenceConstraints();
            i_.setArg(argTypes_.get(i));
            i_.setParam(paramTypes_.get(i));
            ics_.add(i_);
        }
        while (true) {
            CustList<InferenceConstraints> next_ = new CustList<InferenceConstraints>();
            for (InferenceConstraints i: ics_) {
                String argLoc_ = i.getArg();
                String paramLoc_ = i.getParam();
                if (argLoc_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                    found_.add(i);
                    continue;
                }
                if (argLoc_.startsWith(ARR_BEG_STRING)) {
                    if (paramLoc_.startsWith(ARR_BEG_STRING)) {
                        InferenceConstraints n_ = new InferenceConstraints();
                        n_.setArg(argLoc_.substring(ARR_BEG_STRING.length()));
                        n_.setParam(paramLoc_.substring(ARR_BEG_STRING.length()));
                        next_.add(n_);
                    }
                    continue;
                }
                if (StringUtil.quickEq(argLoc_, AnaInherits.SUB_TYPE)) {
                    continue;
                }
                boolean seen_ = false;
                for (String s: new StringList(AnaInherits.SUB_TYPE, AnaInherits.SUP_TYPE,"~")) {
                    if (argLoc_.startsWith(s)) {
                        if (paramLoc_.startsWith(s)) {
                            InferenceConstraints n_ = new InferenceConstraints();
                            n_.setArg(argLoc_.substring(s.length()));
                            n_.setParam(paramLoc_.substring(s.length()));
                            next_.add(n_);
                        }
                        seen_ = true;
                    }
                }
                if (seen_) {
                    continue;
                }
                StringList nArgTypes_ = StringExpUtil.getAllTypes(argLoc_);
                StringList nParamTypes_ = StringExpUtil.getAllTypes(paramLoc_);
                if (nArgTypes_.size() != nParamTypes_.size()) {
                    continue;
                }
                if (!StringUtil.quickEq(nArgTypes_.first(), nParamTypes_.first())) {
                    continue;
                }
                int lenLoc_ = nArgTypes_.size();
                for (int j = 1; j < lenLoc_; j++) {
                    InferenceConstraints i_ = new InferenceConstraints();
                    i_.setArg(nArgTypes_.get(j));
                    i_.setParam(nParamTypes_.get(j));
                    next_.add(i_);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            ics_ = next_;
        }
        StringMap<StringList> multi_ = new StringMap<StringList>();
        for (String p: StringExpUtil.getAllTypes(gene_).mid(1)) {
            multi_.put(p, new StringList());
        }
        for (EntryCust<String,String> e: _vars.entryList()) {
            multi_.put(StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,e.getKey()), new StringList());
        }
        for (EntryCust<String,String> e: _vars.entryList()) {
            feed(multi_,StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,e.getKey()),e.getValue());
        }
        for (InferenceConstraints i: found_) {
            String argLoc_ = i.getArg();
            String paramLoc_ = i.getParam();
            feed(multi_,argLoc_,paramLoc_);
        }
        return tryBuild(_page, gene_, multi_, _page.getCurrentConstraints().getCurrentConstraints());
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
        AnaClassArgumentMatching a_ = _m.getArg();
        AnaClassArgumentMatching p_ = _m.getParam();
        if (_m.getParam().isVariable()) {
            return false;
        }
        if (_m.getArg().isVariable()) {
            return !AnaTypeUtil.isPrimitive(p_, _page);
        }
        if (AnaTypeUtil.isPrimitive(p_, _page)) {
            Mapping m_ = new Mapping();
            m_.setArg(AnaTypeUtil.toPrimitive(a_, _page.getPrimitiveTypes()));
            m_.setParam(p_);
            m_.setMapping(_m.getMapping());
            return isCorrectVars(m_, _page);
        }
        return isCorrectVars(_m, _page);
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
            boolean ok_ = false;
            StringList names_ = arg_.getNames();
            for (String a: names_) {
                CustList<Matching> matchs_ = new CustList<Matching>();
                Matching match_ = new Matching();
                match_.setArg(a);
                match_.setParam(p);
                matchs_.add(match_);
                boolean okTree_ = true;
                while (true) {
                    CustList<Matching> new_ = new CustList<Matching>();
                    for (Matching m: matchs_) {
                        String a_ = m.getArg();
                        String p_ = m.getParam();
                        MappingPairs m_ = getSimpleMappingVars(a_,p_,generalMapping_, _page);
                        if (m_ == null) {
                            okTree_ = false;
                            break;
                        }
                        for (Matching n: m_.getPairsArgParam()) {
                            String baseArrayParamNext_ = StringExpUtil.getQuickComponentBase(n.getParam());
                            String baseArrayArgNext_ = StringExpUtil.getQuickComponentBase(n.getArg());
                            if (StringUtil.quickEq(n.getParam(), n.getArg())) {
                                continue;
                            }
                            if (tryGetUnknownVar(baseArrayParamNext_) >= 0 || tryGetUnknownVar(baseArrayArgNext_) >= 0) {
                                continue;
                            }
                            if (n.getMatchEq() == MatchingEnum.EQ) {
                                StringList foundArgTypes_ = StringExpUtil.getAllTypes(n.getArg());
                                StringList foundParamTypes_ = StringExpUtil.getAllTypes(n.getParam());
                                int len_ = foundArgTypes_.size();
                                if (foundParamTypes_.size() != len_) {
                                    okTree_ = false;
                                    break;
                                }
                                if (len_ > 1) {
                                    if (!StringUtil.quickEq(foundArgTypes_.first(), foundParamTypes_.first())) {
                                        okTree_ = false;
                                        break;
                                    }
                                }
                                if (isVar(n.getParam()) || isVar(n.getArg())) {
                                    new_.add(n);
                                    continue;
                                }
                                okTree_ = false;
                                break;
                            }
                            Matching n_ = new Matching();
                            if (n.getMatchEq() == MatchingEnum.SUB) {
                                n_.setArg(n.getArg());
                                n_.setParam(n.getParam());
                            } else {
                                n_.setArg(n.getParam());
                                n_.setParam(n.getArg());
                            }
                            new_.add(n_);
                        }
                        if (!okTree_) {
                            break;
                        }
                    }
                    if (new_.isEmpty()) {
                        break;
                    }
                    matchs_ = new_;
                    if (!okTree_) {
                        break;
                    }
                }
                if (!okTree_) {
                    continue;
                }
                ok_ = true;
                break;
            }
            if (!ok_) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVar(String _var) {
        int len_ = _var.length();
        int i_ = 0;
        while (i_ < len_) {
            char ch_ = _var.charAt(i_);
            if (ch_ == '#') {
                if (i_ + 1 < len_ && StringExpUtil.isDigit(_var.charAt(i_+1))) {
                    return true;
                }
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
            Matching matching_ = _infer.get(i);
            if (matching_.getMatchEq() == MatchingEnum.EQ) {
                l_.add(matching_);
                continue;
            }
            if (matching_.getMatchEq() == null) {
                continue;
            }
            for (int j = 0; j < len_; j++) {
                if (i == j) {
                    continue;
                }
                Matching other_ = _infer.get(j);
                if (other_.getMatchEq() == MatchingEnum.EQ) {
                    continue;
                }
                if (areCompl(matching_, other_)) {
                    if (AbstractInheritProcess.areTypePairs(matching_,other_)) {
                        matching_.setMatchEq(MatchingEnum.EQ);
                        other_.setMatchEq(null);
                        break;
                    }
                }
                if (matching_.getMatchEq() == other_.getMatchEq()) {
                    if (areTypeCross(matching_,other_)) {
                        matching_.setMatchEq(MatchingEnum.EQ);
                        other_.setMatchEq(null);
                        break;
                    }
                }
            }
            l_.add(matching_);
        }
        return l_;
    }
    public static CustList<Matching> resolveEq(CustList<Matching> _infer, int _max) {
        if (_infer == null) {
            return null;
        }
        int len_ = _infer.size();
        for (int k = 0; k < _max; k++) {
            for (int i = 0; i < len_; i++) {
                Matching matching_ = _infer.get(i);
                if (matching_.getMatchEq() != MatchingEnum.EQ) {
                    continue;
                }
                String resVar_ = matching_.getParam();
                String value_ = matching_.getArg();
                int nbResVar_ = tryGetUnknownVar(resVar_);
                int nbResValue_ = tryGetUnknownVar(value_);
                if (nbResVar_ == k&& nbResValue_ <0) {
                    for (int j = 0; j < len_; j++) {
                        if (i == j) {
                            continue;
                        }
                        Matching other_ = _infer.get(j);
                        StringMap<String> varTypes_ = new StringMap<String>();
                        varTypes_.addEntry(Integer.toString(k),value_);
                        other_.setParam(StringExpUtil.getQuickFormattedType(other_.getParam(), varTypes_));
                        other_.setArg(StringExpUtil.getQuickFormattedType(other_.getArg(), varTypes_));
                    }
                    continue;
                }
                if (nbResValue_ == k&& nbResVar_ <0) {
                    for (int j = 0; j < len_; j++) {
                        if (i == j) {
                            continue;
                        }
                        Matching other_ = _infer.get(j);
                        StringMap<String> varTypes_ = new StringMap<String>();
                        varTypes_.addEntry(Integer.toString(k),resVar_);
                        other_.setParam(StringExpUtil.getQuickFormattedType(other_.getParam(), varTypes_));
                        other_.setArg(StringExpUtil.getQuickFormattedType(other_.getArg(), varTypes_));
                    }
                }
            }
        }
        return _infer;
    }

    public static CustList<Matching> removeDup(CustList<Matching> _infer) {
        if (_infer == null) {
            return null;
        }
        CustList<Matching> l_ = new CustList<Matching>();
        for (Matching m: _infer) {
            boolean add_ = true;
            for (Matching n: l_) {
                if (m.getMatchEq() == MatchingEnum.EQ
                 &&n.getMatchEq() == MatchingEnum.EQ) {
                    if (AbstractInheritProcess.areTypePairs(m, n)
                    || areTypeCross(m, n)) {
                        add_ = false;
                        break;
                    }
                    continue;
                }
                if (areCompl(m, n)) {
                    if (areTypeCross(m, n)) {
                        add_ = false;
                        break;
                    }
                    continue;
                }
                if (m.getMatchEq() == n.getMatchEq()) {
                    if (AbstractInheritProcess.areTypePairs(m, n)) {
                        add_ = false;
                        break;
                    }
                }
            }
            if (add_) {
                l_.add(m);
            }
        }
        return l_;
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
            if (result_.isFoundMethod()) {
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
                CustList<Matching> matchs_ = new CustList<Matching>();
                Matching match_ = new Matching();
                match_.setArg(a);
                match_.setParam(p);
                match_.setMatchEq(_base);
                matchs_.add(match_);
                while (true) {
                    CustList<Matching> new_ = new CustList<Matching>();
                    for (Matching m: matchs_) {
                        String a_ = m.getArg();
                        String p_ = m.getParam();
                        MappingPairs m_ = getSimpleInferredMapping(a_,p_,generalMapping_, _page, m.getMatchEq());
                        if (m_ == null) {
                            continue;
                        }
                        for (Matching n: m_.getPairsArgParam()) {
                            if (StringUtil.quickEq(n.getParam(), n.getArg())) {
                                continue;
                            }
                            String baseArrayParamNext_ = StringExpUtil.getQuickComponentBase(n.getParam());
                            String baseArrayArgNext_ = StringExpUtil.getQuickComponentBase(n.getArg());
                            if (tryGetUnknownVar(baseArrayParamNext_) >= 0 || tryGetUnknownVar(baseArrayArgNext_) >= 0) {
                                constraints_.add(n);
                                continue;
                            }
                            if (n.getMatchEq() == MatchingEnum.EQ) {
                                StringList foundArgTypes_ = StringExpUtil.getAllTypes(n.getArg());
                                StringList foundParamTypes_ = StringExpUtil.getAllTypes(n.getParam());
                                int len_ = foundArgTypes_.size();
                                if (foundParamTypes_.size() != len_) {
                                    continue;
                                }
                                if (!StringUtil.quickEq(foundArgTypes_.first(),foundParamTypes_.first())) {
                                    continue;
                                }
                                new_.add(n);
                                continue;
                            }
                            Matching n_ = new Matching();
                            if (n.getMatchEq() == MatchingEnum.SUB) {
                                n_.setArg(n.getArg());
                                n_.setParam(n.getParam());
                            } else {
                                n_.setArg(n.getParam());
                                n_.setParam(n.getArg());
                            }
                            n_.setMatchEq(_base);
                            new_.add(n_);
                        }
                    }
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

    private static int tryGetUnknownVar(String _baseArrayArg) {
        int var_ = -1;
        String af_ = "";
        if (_baseArrayArg.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            af_ = _baseArrayArg.substring(1);
        }
        if (!af_.isEmpty()){
            if (StringExpUtil.isDigit(af_.charAt(0))) {
                var_ = NumberUtil.parseInt(af_);
            }
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
            int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
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
            int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
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
        if (_ct == MatchingEnum.EQ) {
            if (baseArrayParam_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                if (_arg.isEmpty()) {
                    return new MappingPairs();
                }
                int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
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
            int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
            if (tryGetUnknownVar(baseArrayArg_)>=0) {
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
            if (dArg_.getDim() != dParam_.getDim()) {
                return null;
            }
            StringList foundArgTypes_ = StringExpUtil.getAllTypes(baseArrayArg_);
            StringList foundParamTypes_ = StringExpUtil.getAllTypes(baseArrayParam_);
            int len_ = foundArgTypes_.size();
            if (foundParamTypes_.size() != len_) {
                return null;
            }
            if (!StringUtil.quickEq(foundArgTypes_.first(),foundParamTypes_.first())) {
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
        if (baseArrayParam_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            if (_arg.isEmpty()) {
                return new MappingPairs();
            }
            int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
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
        return commonTypePair(_arg, _param, _inherit, _page, _ct);
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
