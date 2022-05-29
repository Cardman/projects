package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.util.StringList;

public abstract class AbstractInferringEmptyType extends AbstractInferringType{
    private String typeInfer = "";

    public void infer(AnalyzedPageEl _page, ParentInferring _par) {
        KeyWords keyWords_ = _page.getKeyWords();
        OperationNode m_ = _par.getOperation();
        int nbParentsInfer_ = _par.getNbParentsInfer();
        String typeAff_ = typeAff(_page, _par);
        String keyWordVar_ = keyWords_.getKeyWordVar();
        boolean list_ = false;
        if (m_ instanceof ArgumentListInstancing){
            list_ = true;
            m_ = m_.getParent().getParent();
        }
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String inferRecord_ = n_.infer();
            if (!inferRecord_.isEmpty()) {
                String format_ = tryFormatEmpInstRec(inferRecord_, nbParentsInfer_, _page);
                if (format_ != null) {
                    typeInfer = format_;
                }
                return;
            }
            String name_ = n_.getName();
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                StringList candidates_ = namedMethod(_page,_par,call_,name_);
                tryRetrieve(candidates_);
            }
            if (call_ instanceof RetrieveConstructor) {
                StringList candidates_ = namedCtor(_page,_par,call_,name_);
                tryRetrieve(candidates_);
            }
            return;
        }
        if (m_ instanceof RetrieveMethod){
            RetrieveMethod f_ = (RetrieveMethod) m_;
            StringList candidates_ = unNamedMethod(_page,_par,f_,list_);
            tryRetrieve(candidates_);
            return;
        }
        if (m_ instanceof RetrieveConstructor){
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            StringList candidates_ = unNamedCtor(_page,_par,f_,list_);
            tryRetrieve(candidates_);
            return;
        }
        if (InvokingOperation.isUndefined(typeAff_, keyWordVar_)) {
            return;
        }
        String cp_ = getQuickComponentType(typeAff_, nbParentsInfer_);
        if (InvokingOperation.isNotCorrectDim(cp_)) {
            return;
        }
        typeInfer = cp_;
    }

    private void tryRetrieve(StringList _candidates) {
        if (_candidates.onlyOneElt()) {
            typeInfer = _candidates.first();
        }
    }

    @Override
    protected String tryParamFormat(MethodInfo _methodInfo, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page) {
        return tryFormatEmpInst(_methodInfo,_indexChild,_nbParentsInfer,_page);
    }

    @Override
    protected String tryParamFormat(ConstructorInfo _methodInfo, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page) {
        return tryFormatEmpInst(_methodInfo,_indexChild,_nbParentsInfer,_page);
    }

    @Override
    protected String tryParamNamedFormat(NameParametersFilter _filter, MethodInfo _paramet, String _name, int _nbParentsInfer, AnalyzedPageEl _page) {
        return tryParamFormatEmpInst(_filter,_paramet,_name,_nbParentsInfer,_page);
    }

    @Override
    protected String tryParamNamedFormat(NameParametersFilter _filter, ConstructorInfo _paramet, String _name, int _nbParentsInfer, AnalyzedPageEl _page) {
        return tryParamFormatEmpInst(_filter,_paramet,_name,_nbParentsInfer,_page);
    }

    protected abstract String tryFormatEmpInst(Parametrable _methodInfo, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page);

    protected abstract String tryParamFormatEmpInst(NameParametersFilter _filter, Parametrable _methodInfo, String _name, int _nbParentsInfer, AnalyzedPageEl _page);

    protected abstract String tryFormatEmpInstRec(String _inferRecord, int _nbParentsInfer, AnalyzedPageEl _page);

    protected abstract String getQuickComponentType(String _typeAff, int _nbParentsInfer);

    protected abstract String typeAff(AnalyzedPageEl _page, ParentInferring _par);

    public String getTypeInfer() {
        return typeInfer;
    }
}
