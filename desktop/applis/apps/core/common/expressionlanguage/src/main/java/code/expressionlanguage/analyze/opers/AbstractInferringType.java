package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.util.CustList;
import code.util.StringList;

public abstract class AbstractInferringType {
    protected StringList namedMethod(AnalyzedPageEl _page, ParentInferring _par, MethodOperation _call, String _name){
        int nbParentsInfer_ = _par.getNbParentsInfer();
        RetrieveMethod f_ = (RetrieveMethod) _call;
        NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page,_call);
        CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
        int len_ = methodInfos_.size();
        StringList candidates_ = new StringList();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos_.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                String format_ = tryParamNamedFormat(filter_,methodInfo_, _name, nbParentsInfer_, _page);
                if (format_ == null) {
                    continue;
                }
                candidates_.add(format_);
                newList_.add(methodInfo_);
            }
            methodInfos_.set(i,newList_);
        }
        return candidates_;
    }
    protected StringList namedCtor(AnalyzedPageEl _page, ParentInferring _par, MethodOperation _call, String _name){
        int nbParentsInfer_ = _par.getNbParentsInfer();
        RetrieveConstructor f_ = (RetrieveConstructor) _call;
        NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page,_call);
        CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
        int len_ = methodInfos_.size();
        StringList candidates_ = new StringList();
        CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
        for (int i = 0; i < len_; i++) {
            ConstructorInfo methodInfo_ = methodInfos_.get(i);
            String format_ = tryParamNamedFormat(filter_,methodInfo_, _name, nbParentsInfer_, _page);
            if (format_ == null) {
                continue;
            }
            candidates_.add(format_);
            newList_.add(methodInfo_);
        }
        methodInfos_.clear();
        methodInfos_.addAllElts(newList_);
        return candidates_;
    }
    protected StringList unNamedMethod(AnalyzedPageEl _page, ParentInferring _par, RetrieveMethod _call, boolean _list){
        int nbParentsInfer_ = _par.getNbParentsInfer();
        int indexChild_ = indexChild(_call.getFirstChild(), _list, _par);
        CustList<CustList<MethodInfo>> methodInfos_ = _call.getMethodInfos();
        int len_ = methodInfos_.size();
        StringList candidates_ = new StringList();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos_.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                String format_ = tryParamFormat(methodInfo_, indexChild_, nbParentsInfer_, _page);
                if (format_ == null) {
                    continue;
                }
                candidates_.add(format_);
                newList_.add(methodInfo_);
            }
            methodInfos_.set(i,newList_);
        }
        return candidates_;
    }
    protected StringList unNamedCtor(AnalyzedPageEl _page, ParentInferring _par, RetrieveConstructor _call, boolean _list){
        int nbParentsInfer_ = _par.getNbParentsInfer();
        int indexChild_ = indexChild(_call.getFirstChild(), _list, _par);
        CustList<ConstructorInfo> methodInfos_ = _call.getCtors();
        int len_ = methodInfos_.size();
        StringList candidates_ = new StringList();
        CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
        for (int i = 0; i < len_; i++) {
            ConstructorInfo methodInfo_ = methodInfos_.get(i);
            String format_ = tryParamFormat(methodInfo_, indexChild_, nbParentsInfer_, _page);
            if (format_ == null) {
                continue;
            }
            candidates_.add(format_);
            newList_.add(methodInfo_);
        }
        methodInfos_.clear();
        methodInfos_.addAllElts(newList_);
        return candidates_;
    }

    private static int indexChild(OperationNode _call, boolean _list, ParentInferring _par) {
        int deltaCount_ = InvokingOperation.getDeltaCount(_list, _call);
        return _par.getOperationChild().getIndexChild()-deltaCount_;
    }

    protected abstract String tryParamFormat(MethodInfo _methodInfo, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page);
    protected abstract String tryParamFormat(ConstructorInfo _methodInfo, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page);

    protected abstract String tryParamNamedFormat(NameParametersFilter _filter, MethodInfo _paramet, String _name, int _nbParentsInfer, AnalyzedPageEl _page);
    protected abstract String tryParamNamedFormat(NameParametersFilter _filter, ConstructorInfo _paramet, String _name, int _nbParentsInfer, AnalyzedPageEl _page);
}
