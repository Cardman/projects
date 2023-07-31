package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.AnaInheritedType;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.util.*;
import code.util.core.StringUtil;

public abstract class StandardType implements GeneType,AnaGeneType,AnaInheritedType {

    private final String name;
    private final String packageName;

    private final CustList<StandardConstructor> constructors;
    private final CustList<StandardMethod> methods;
    private final DfInstancer instancer;

    private final StringList directInterfaces = new StringList();

    private final StringList allSuperTypes = new StringList();
    private final CustList<StandardType> allSuperStdTypes = new CustList<StandardType>();

    protected StandardType(String _name,
                           CustList<StandardConstructor> _constructors,
                           CustList<StandardMethod> _methods, DfInstancer _caller) {
        int indexDot_ = _name.lastIndexOf('.');
        name = _name.substring(indexDot_ + 1);
        packageName = _name.substring(0, indexDot_);
        constructors = _constructors;
        methods = _methods;
        instancer = _caller;
    }

    public static StdCaller caller(StandardConstructor _constructor, StdCaller _stdCaller) {
        StdCaller stdCaller_;
        if (_constructor != null) {
            stdCaller_ = _constructor.getCaller();
        } else {
            stdCaller_ = _stdCaller;
        }
        return stdCaller_;
    }

    public DfInstancer getInstancer() {
        return instancer;
    }

    public StringList getDirectInterfaces() {
        return directInterfaces;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    public void addSuperStdTypes(StandardType _std) {
        allSuperTypes.add(_std.getFullName());
        getAllSuperStdTypes().add(_std);
    }
    public CustList<StandardType> getAllSuperStdTypes() {
        return allSuperStdTypes;
    }

    @Override
    public boolean withoutInstance() {
        return true;
    }

    @Override
    public Ints getTypeVarCounts() {
        return Ints.newList(0);
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
        return pkg_+"."+name_;
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
