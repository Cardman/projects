package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.sml.Element;
import code.sml.RowCol;
import code.util.CustList;
import code.util.IdMap;
import code.util.Numbers;
import code.util.StringList;

public abstract class NamedFunctionBlock extends MemberCallingsBlock implements Returnable {

    private final String name;

    private int nameOffset;

    private final StringList parametersTypes;

    private Numbers<Integer> parametersTypesOffset;

    private final String returnType;

    private int returnTypeOffset;

    private final StringList parametersNames;

    private Numbers<Integer> parametersNamesOffset;

    private final AccessEnum access;

    private int accessOffset;

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
            if (!_el.hasAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,i_+1))) {
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
            OffsetAccessInfo _access,
            OffsetStringInfo _retType, OffsetStringInfo _fctName,
            StringList _paramTypes, Numbers<Integer> _paramTypesOffset,
            StringList _paramNames, Numbers<Integer> _paramNamesOffset,
            OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        name = _fctName.getInfo();
        nameOffset = _fctName.getOffset();
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
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        returnType = _retType.getInfo();
        returnTypeOffset = _retType.getOffset();
        parametersNames = new StringList();
        i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            parametersNames.add(_paramNames.get(i_));
            i_++;
        }
        parametersTypesOffset = _paramTypesOffset;
        parametersNamesOffset = _paramNamesOffset;
    }

    public Numbers<Integer> getParametersTypesOffset() {
        return parametersTypesOffset;
    }

    public Numbers<Integer> getParametersNamesOffset() {
        return parametersNamesOffset;
    }

    public int getNameOffset() {
        return nameOffset;
    }

    public int getAccessOffset() {
        return accessOffset;
    }

    public int getReturnTypeOffset() {
        return returnTypeOffset;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public final StringList getParametersTypes(Analyzable _an) {
        StringList params_ = new StringList();
        int off_ = getOffset().getOffsetTrim();
        int i_ = 0;
        for (String p: parametersTypes) {
            RowCol rc_ = getRowCol(off_, parametersTypesOffset.get(i_));
            params_.add(_an.resolveType(p, this, rc_, true, true, false));
            i_++;
        }
        return params_;
    }
    public final StringList getParametersTypes() {
        return new StringList(parametersTypes);
    }

    @Override
    public String getReturnType(Analyzable _stds) {
        String void_ = _stds.getStandards().getAliasVoid();
        if (StringList.quickEq(returnType, void_)) {
            return returnType;
        }
        RowCol rc_ = getRowCol(returnTypeOffset, getOffset().getOffsetTrim());
        return _stds.resolveType(returnType, this, rc_, true, true, false);
    }
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
    @Override
    public void setAssignmentBefore(Analyzable _an, AnalyzingEl _anEl) {
        AssignedVariables ass_;
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        ass_ = _an.getAssignedVariables().getFinalVariablesGlobal();
        id_.put(this, ass_);
    }
}
