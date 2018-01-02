package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
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
        while (_el.hasAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,i_))) {
            String className_ = _el.getAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,i_));
            if (!_el.hasAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,(i_+1)))) {
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
        access = AccessEnum.getAccessByName(_el.getAttribute(ATTRIBUTE_ACCESS));
        returnType = _el.getAttribute(ATTRIBUTE_CLASS);
        parametersNames = new StringList();
        i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(StringList.concatNbs(ATTRIBUTE_VAR,i_))) {
            parametersNames.add(_el.getAttribute(StringList.concatNbs(ATTRIBUTE_VAR,i_)));
            i_++;
        }
    }

    public NamedFunctionBlock(ContextEl _importingPage,
            int _indexChild, BracedBlock _m,
            AccessEnum _access,
            String _retType, String _fctName,
            StringList _paramTypes,
            StringList _paramNames) {
        super(_importingPage, _indexChild, _m);
        name = _fctName;
        parametersTypes = new StringList();
        int i_ = CustList.FIRST_INDEX;
        int len_ = _paramTypes.size();
        boolean varargs_ = false;
        while (i_ < len_) {
            String className_ = _paramTypes.get(i_);
            if (i_+1 == len_) {
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
        access = _access;
        returnType = _retType;
        parametersNames = new StringList();
        i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            parametersNames.add(_paramNames.get(i_));
            i_++;
        }
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        StringList l_ = getParametersTypes();
        int i_ = 0;
        for (String t: l_) {
            tr_.put(StringList.concatNbs(ATTRIBUTE_CLASS,i_), t);
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
    public String getReturnType(LgNames _stds) {
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
}
