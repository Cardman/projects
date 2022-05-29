package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbstractInferringDiamondType extends AbstractInferringType{
    private final String inferForm;
    private ResolvedInstance resolvedInstance = new ResolvedInstance();
    private String typeInfer = "";
    private final int local;
    private final String className;
    private StringMap<String> vars = new StringMap<String>();
    private String type = "";


    protected AbstractInferringDiamondType(String _inf, int _loc, String _cl) {
        this.inferForm = _inf;
        this.local = _loc;
        this.className = _cl;
    }

    public void infer(AnalyzedPageEl _page, ParentInferring _par, StringMap<String> _vars) {
        vars = _vars;
        String typeAff_ = typeAff(_page, _par);
        KeyWords keyWords_ = _page.getKeyWords();
        String type_ = solve(inferForm, _page);
        type = type_;
        if (type_.isEmpty()) {
            return;
        }
        OperationNode m_ = _par.getOperation();
        int nbParentsInfer_ = _par.getNbParentsInfer();
        boolean list_ = false;
        if (m_ instanceof ArgumentListInstancing){
            list_ = true;
            m_ = m_.getParent().getParent();
        }
        int lt_ = keyWords_.getKeyWordNew().length() + local + className.indexOf('<');
        int gt_ = keyWords_.getKeyWordNew().length() + local + className.indexOf('>') + 1;
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String inferRecord_ = n_.infer();
            if (!inferRecord_.isEmpty()) {
                String format_ = tryFormatArrRec(inferRecord_, nbParentsInfer_, type_, _vars, _page);
                tryRetrieve(_page, lt_, gt_, format_);
                return;
            }
            String name_ = n_.getName();
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                StringList candidates_ = namedMethod(_page,_par,call_,name_);
                tryRetrieve(_page, lt_, gt_, candidates_);
            }
            if (call_ instanceof RetrieveConstructor) {
                StringList candidates_ = namedCtor(_page,_par,call_,name_);
                tryRetrieve(_page, lt_, gt_, candidates_);
            }
            return;
        }
        if (m_ instanceof RetrieveMethod){
            RetrieveMethod f_ = (RetrieveMethod) m_;
            StringList candidates_ = unNamedMethod(_page,_par,f_,list_);
            tryRetrieve(_page, lt_, gt_, candidates_);
            return;
        }
        if (m_ instanceof RetrieveConstructor){
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            StringList candidates_ = unNamedCtor(_page,_par,f_,list_);
            tryRetrieve(_page, lt_, gt_, candidates_);
            return;
        }
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (InvokingOperation.isUndefined(typeAff_, keyWordVar_)) {
            return;
        }
        String cp_ = getQuickComponentType(typeAff_, nbParentsInfer_);
        if (InvokingOperation.isNotCorrectDim(cp_)) {
            return;
        }
        String infer_ = tryInferOrImplicitArr(type_,_vars, _page, cp_);
        tryRetrieve(_page, lt_, gt_, infer_);
    }

    private void tryRetrieve(AnalyzedPageEl _page, int _lt, int _gt, String _inf) {
        if (_inf != null) {
            String arr_ = getPrettyArrayType(_inf);
            resolvedInstance = new ResolvedInstance(resolvedInstance, _page, _lt, _gt, arr_);
            typeInfer = arr_;
        }
    }

    private void tryRetrieve(AnalyzedPageEl _page, int _lt, int _gt, StringList _candidates) {
        if (_candidates.onlyOneElt()) {
            String infer_ = getPrettyArrayType(_candidates.first());
            resolvedInstance = new ResolvedInstance(resolvedInstance, _page, _lt, _gt,infer_);
            typeInfer = infer_;
        }
    }

    @Override
    protected String tryParamFormat(MethodInfo _methodInfo, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page) {
        return tryFormatArr(_methodInfo,_indexChild,_nbParentsInfer,type,vars,_page);
    }

    @Override
    protected String tryParamFormat(ConstructorInfo _methodInfo, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page) {
        return tryFormatArr(_methodInfo,_indexChild,_nbParentsInfer,type,vars,_page);
    }
    @Override
    protected String tryParamNamedFormat(NameParametersFilter _filter, MethodInfo _paramet, String _name, int _nbParentsInfer, AnalyzedPageEl _page) {
        return tryParamFormatArr(_filter,_paramet,_name,_nbParentsInfer,type,vars,_page);
    }

    @Override
    protected String tryParamNamedFormat(NameParametersFilter _filter, ConstructorInfo _paramet, String _name, int _nbParentsInfer, AnalyzedPageEl _page) {
        return tryParamFormatArr(_filter,_paramet,_name,_nbParentsInfer,type,vars,_page);
    }

    protected abstract String getQuickComponentType(String _typeAff, int _nbParentsInfer);

    protected abstract String tryInferOrImplicitArr(String _type, StringMap<String> _vars, AnalyzedPageEl _page, String _cp);

    protected abstract String tryFormatArr(Parametrable _methodInfo, int _indexChild, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page);

    protected abstract String tryParamFormatArr(NameParametersFilter _filter, Parametrable _methodInfo, String _name, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page);

    protected abstract String tryFormatArrRec(String _inferRecord, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page);

    protected abstract String getPrettyArrayType(String _format);

    protected abstract String typeAff(AnalyzedPageEl _page, ParentInferring _par);

    protected abstract String solve(String _inferForm, AnalyzedPageEl _page);

    public String getInferForm() {
        return inferForm;
    }

    public ResolvedInstance getResolvedInstance() {
        return resolvedInstance;
    }

    public void setResolvedInstance(ResolvedInstance _res) {
        this.resolvedInstance = _res;
    }

    public String getTypeInfer() {
        return typeInfer;
    }

    public int getLocal() {
        return local;
    }

    public String getClassName() {
        return className;
    }
}
