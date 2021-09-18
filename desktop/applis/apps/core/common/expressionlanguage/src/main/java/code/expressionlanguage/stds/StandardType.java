package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.AnaInheritedType;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.util.*;
import code.util.core.StringUtil;

public abstract class StandardType implements GeneType,AnaGeneType,AnaInheritedType {

    private static final String PACKAGE_NAME = "";

    private final String name;
    private final String packageName;

    private final CustList<StandardConstructor> constructors;
    private final CustList<StandardMethod> methods;
    private final StdCaller caller;

    protected StandardType(String _name,
                           CustList<StandardConstructor> _constructors,
                           CustList<StandardMethod> _methods, StdCaller _caller) {
        name = getNamePart(_name);
        packageName = getPackagePart(_name);
        constructors = _constructors;
        methods = _methods;
        caller = _caller;
    }

    public static StdCaller caller(StandardType _standardType, StandardConstructor _constructor, StdCaller _stdCaller) {
        StdCaller stdCaller_;
        if (_constructor != null) {
            stdCaller_ = _constructor.getCaller();
        } else if (_standardType != null) {
            stdCaller_ = _standardType.getCaller();
        } else {
            stdCaller_ = _stdCaller;
        }
        return stdCaller_;
    }

    public StdCaller getCaller() {
        return caller;
    }

    public static String getNamePart(String _fullName) {
        int indexDot_ = _fullName.lastIndexOf('.');
        return _fullName.substring(indexDot_+1);
    }
    public static String getPackagePart(String _fullName) {
        int indexDot_ = _fullName.lastIndexOf('.');
        if (indexDot_ < 0) {
            return PACKAGE_NAME;
        }
        return _fullName.substring(0, indexDot_);
    }

    public abstract StringList getDirectSuperTypes();

    public abstract StringList getDirectInterfaces();
    @Override
    public boolean withoutInstance() {
        return true;
    }

    @Override
    public Ints getTypeVarCounts() {
        return new Ints(0);
    }

    @Override
    public CustList<StringList> getBoundAll() {
        return new CustList<StringList>();
    }

    @Override
    public CustList<ExecTypeVar> getParamTypesMapValues() {
        return new CustList<ExecTypeVar>();
    }

    public StringList getParamTypesValues() {
        return new StringList();
    }

    public final String getName() {
        return name;
    }

    @Override
    public final String getPackageName() {
        return packageName;
    }

    @Override
    public final String getFullName() {
        String pkg_ = getPackageName();
        String name_ = getName();
        return StringUtil.spliceIfFirst('.', StringUtil.concat(pkg_,".",name_));
    }

    @Override
    public final String getGenericString() {
        return getFullName();
    }

    public CustList<StandardConstructor> getConstructors() {
        return constructors;
    }

    public CustList<StandardMethod> getMethods() {
        return methods;
    }

    public boolean isSubTypeOf(String _fullName, AnalyzedPageEl _an) {
        return isSubTypeOf(_fullName);
    }

    private boolean isSubTypeOf(String _fullName) {
        if (StringUtil.quickEq(getFullName(),_fullName)) {
            return true;
        }
        return StringUtil.contains(getAllSuperTypes(),_fullName);
    }
    public StringList getAllGenericSuperTypes() {
        return getAllSuperTypes();
    }
}
