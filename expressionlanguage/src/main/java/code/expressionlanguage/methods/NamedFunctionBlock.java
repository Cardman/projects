package code.expressionlanguage.methods;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.CustList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.StringList;

public abstract class NamedFunctionBlock extends MemberCallingsBlock implements Returnable {

    private final String name;

    private final StringList parametersTypes;

    private final String returnType;

    private final StringList parametersNames;

    private final AccessEnum access;

    private final boolean varargs;

    public NamedFunctionBlock(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        name = _el.getAttribute(ATTRIBUTE_NAME);
        parametersTypes = new StringList();
        int i_ = CustList.FIRST_INDEX;
        boolean varargs_ = false;
        while (_el.hasAttribute(ATTRIBUTE_CLASS+i_)) {
            String className_ = _el.getAttribute(ATTRIBUTE_CLASS+i_);
            if (!_el.hasAttribute(ATTRIBUTE_CLASS+(i_+1))) {
                varargs_ = className_.endsWith(VARARG);
                if (varargs_) {
                    parametersTypes.add(className_.substring(CustList.FIRST_INDEX, className_.length()-VARARG.length()));
                } else {
                    parametersTypes.add(className_);
                }
            } else {
                parametersTypes.add(className_);
            }
            i_++;
        }
        varargs = varargs_;
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
        returnType = _el.getAttribute(ATTRIBUTE_CLASS);
        parametersNames = new StringList();
        i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(ATTRIBUTE_VAR+i_)) {
            parametersNames.add(_el.getAttribute(ATTRIBUTE_VAR+i_));
            i_++;
        }
    }


    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        StringList l_ = getParametersTypes();
        int i_ = 0;
        for (String t: l_) {
            tr_.put(ATTRIBUTE_CLASS+i_, t);
            i_++;
        }
        return tr_;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public final StringList getParametersTypes() {
        return new StringList(parametersTypes);
    }

    @Override
    public String getReturnType() {
        return returnType;
    }

    @Override
    public final StringList getParametersNames() {
        return new StringList(parametersNames);
    }

    @Override
    public final boolean isVarargs() {
        return varargs;
    }

    @Override
    public final AccessEnum getAccess() {
        return access;
    }

    public FctConstraints getBaseConstraints(String _type,Classes _classes) {
        EqList<StringList> ctrs_ = new EqList<StringList>();
        StringList l_ = getParametersTypes();
        StringList bounds_ = Templates.getClassLeftMostBounds(_type, _classes);
        RootBlock r_ = _classes.getClassBody(_type);
        int nbArgs_ = l_.size();
        for (int i = CustList.FIRST_INDEX; i < nbArgs_; i++) {
            String paramType_ = l_.get(i);
            DimComp dimComp_ = PrimitiveTypeUtil.getQuickComponentBaseType(paramType_);
            int dim_ = dimComp_.getDim();
            String paramTypeArr_ = dimComp_.getComponent();
            boolean addPrefix_ = false;
            if (varargs && i + 1 == nbArgs_) {
                addPrefix_ = true;
            }
            String res_;
            if (paramTypeArr_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                String t_ = paramTypeArr_.substring(Templates.PREFIX_VAR_TYPE.length());
                res_ = PrimitiveTypeUtil.getPrettyArrayType(bounds_.get(r_.getIndex(t_)), dim_);
            } else if (paramType_.contains(Templates.TEMPLATE_BEGIN)){
                res_ = paramType_.substring(CustList.FIRST_INDEX, paramType_.indexOf(Templates.TEMPLATE_BEGIN));
            } else {
                res_ = l_.get(i);
            }
            if (addPrefix_) {
                res_ = PrimitiveTypeUtil.getPrettyArrayType(res_);
            }
            ctrs_.add(new StringList(res_));
        }
        String name_ = getName();
        if (name_.isEmpty()) {
            return new FctConstraints(_type, ctrs_);
        }
        return new FctConstraints(name_, ctrs_);
    }
}
