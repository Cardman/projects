package code.expressionlanguage.common;

import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class FeedMappingTypePair {
    private FeedMappingTypePair() {
    }

    public static MappingPairs getMappingFctPairs(String _arg, String _param, String _fct, String _obj) {
        StringList typesArg_ = StringExpUtil.getAllTypes(_arg);
        StringList typesParam_ = StringExpUtil.getAllTypes(_param);
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayParam_ = dParam_.getComponent();
        String baseArrayArg_ = dArg_.getComponent();
        String idBaseArrayArg_ = StringExpUtil.getIdFromAllTypes(baseArrayArg_);
        String idBaseArrayParam_ = StringExpUtil.getIdFromAllTypes(baseArrayParam_);
        if (StringUtil.quickEq(idBaseArrayArg_, _fct)) {
            if (StringUtil.quickEq(idBaseArrayParam_, _fct)) {
                int dim_ = dArg_.getDim();
                if (dim_ != dParam_.getDim()) {
                    return null;
                }
                if (StringUtil.quickEq(baseArrayParam_, _fct)) {
                    return new MappingPairs();
                }
                return newMappingPairsFct(typesArg_, typesParam_, _obj);
            }
            return getMappingFctPairs(dArg_, dParam_, baseArrayParam_, _obj);
        }
        return null;
    }
    private static MappingPairs getMappingFctPairs(DimComp _dArg, DimComp _dParam, String _baseArrayParam, String _aliasObject) {
        if (StringUtil.quickEq(_baseArrayParam, _aliasObject)) {
            int dim_ = _dArg.getDim();
            if (dim_ >= _dParam.getDim()) {
                return new MappingPairs();
            }
        }
        return null;
    }
    /**arg - param*/
    private static MappingPairs newMappingPairsFct(StringList _args, StringList _params, String _objectType) {
        int len_ = _params.size();
        if (_args.size() != len_) {
            return null;
        }
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
        int argCall_ = len_ - 1;
        for (int i = IndexConstants.SECOND_INDEX; i < len_; i++) {
            String arg_ = _args.get(i);
            String param_ = _params.get(i);
            if (stopFct(arg_,param_,pairsArgParam_,i,argCall_,_objectType)) {
                return null;
            }
        }
        MappingPairs m_ = new MappingPairs();
        m_.setPairsArgParam(pairsArgParam_);
        return m_;
    }

    /**arg - param*/
    public static MappingPairs newMappingPairs(String _generic, String _param) {
        StringList foundSuperClass_ = StringExpUtil.getAllTypes(_generic);
        StringList typesParam_ = StringExpUtil.getAllTypes(_param);
        int len_ = foundSuperClass_.size();
        if (typesParam_.size() != len_) {
            return null;
        }
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
        for (int i = IndexConstants.SECOND_INDEX; i < len_; i++) {
            String arg_ = foundSuperClass_.get(i);
            String param_ = typesParam_.get(i);
            if (stopFct(arg_,param_,pairsArgParam_)) {
                return null;
            }
        }
        MappingPairs m_ = new MappingPairs();
        m_.setPairsArgParam(pairsArgParam_);
        return m_;
    }

    private static boolean stopFct(String _arg, String _param, CustList<Matching> _pairs, int _i, int _argCall, String _objectType) {
        if (_param.startsWith("~")) {
            if (_arg.startsWith("~")) {
                Matching match_ = new Matching();
                match_.setArg(_arg.substring(1));
                match_.setParam(_param.substring(1));
                match_.setMatchEq(MatchingEnum.EQ);
                _pairs.add(match_);
                return false;
            }
            return true;
        }
        if (StringUtil.quickEq(_param, StringExpUtil.SUB_TYPE)) {
            return false;
        }
        if (_arg.startsWith("~")) {
            return true;
        }
        if (StringUtil.quickEq(_arg, StringExpUtil.SUB_TYPE)) {
            return true;
        }
        if (_i < _argCall) {
            Matching match_ = new Matching();
            match_.setArg(_arg);
            match_.setParam(_param);
            match_.setMatchEq(MatchingEnum.SUP);
            _pairs.add(match_);
            return false;
        }
        if (!StringUtil.quickEq(_param, _objectType)) {
            Matching match_ = new Matching();
            match_.setArg(_arg);
            match_.setParam(_param);
            match_.setMatchEq(MatchingEnum.SUB);
            _pairs.add(match_);
        }
        return false;
    }
    private static boolean stopFct(String _arg, String _param, CustList<Matching> _pairs) {
        Matching match_ = new Matching();
        if (StringUtil.quickEq(_param, StringExpUtil.SUB_TYPE)) {
            return false;
        }
        if (StringUtil.quickEq(_arg, StringExpUtil.SUB_TYPE)) {
            return true;
        }
        if (!isBound(_param)) {
            if (isBound(_arg)) {
                return true;
            }
            match_.setArg(_arg);
            match_.setParam(_param);
            _pairs.add(match_);
            return false;
        }
        if (!isBound(_arg)) {
            if (_param.startsWith(StringExpUtil.SUP_TYPE)) {
                match_.setArg(_arg);
                match_.setParam(_param.substring(StringExpUtil.SUP_TYPE.length()));
                match_.setMatchEq(MatchingEnum.SUP);
                _pairs.add(match_);
                return false;
            }
            match_.setArg(_arg);
            match_.setParam(_param.substring(StringExpUtil.SUB_TYPE.length()));
            match_.setMatchEq(MatchingEnum.SUB);
            _pairs.add(match_);
            return false;
        }
        if (_arg.startsWith(StringExpUtil.SUP_TYPE)) {
            if (_param.startsWith(StringExpUtil.SUB_TYPE)) {
                return true;
            }
            match_.setArg(_arg.substring(StringExpUtil.SUP_TYPE.length()));
            match_.setParam(_param.substring(StringExpUtil.SUP_TYPE.length()));
            match_.setMatchEq(MatchingEnum.SUP);
            _pairs.add(match_);
            return false;
        }
        if (_param.startsWith(StringExpUtil.SUP_TYPE)) {
            return true;
        }
        match_.setArg(_arg.substring(StringExpUtil.SUB_TYPE.length()));
        match_.setParam(_param.substring(StringExpUtil.SUB_TYPE.length()));
        match_.setMatchEq(MatchingEnum.SUB);
        _pairs.add(match_);
        return false;
    }
    private static boolean isBound(String _type) {
        return _type.startsWith(StringExpUtil.SUP_TYPE) || _type.startsWith(StringExpUtil.SUB_TYPE);
    }

}
