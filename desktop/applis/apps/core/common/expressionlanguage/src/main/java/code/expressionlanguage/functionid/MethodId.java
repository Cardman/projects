package code.expressionlanguage.functionid;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class MethodId implements Identifiable {

    private static final String EMPTY = "";
    private static final String VARARG_DOTS = "...";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    private final MethodAccessKind kind;

    private final String name;

    private final StringList classNames;

    private final boolean vararg;

    public MethodId(MethodAccessKind _staticMethod, String _name, StringList _classNames) {
        this(_staticMethod, _name, _classNames, false);
    }

    public MethodId(MethodAccessKind _staticMethod, String _name, StringList _classNames, boolean _vararg) {
        kind = _staticMethod;
        vararg = _vararg;
        name = StringUtil.nullToEmpty(_name);
        classNames = new StringList();
        feedParamTypes(_classNames);
    }

    private void feedParamTypes(StringList _classNames) {
        for (String s: _classNames) {
            classNames.add(StringUtil.nullToEmpty(s));
        }
    }

    public static FormattedMethodId to(MethodId _id) {
        return new FormattedMethodId(_id.name, _id.classNames, _id.vararg);
    }

    public static MethodAccessKind getKind(MethodAccessKind _context, MethodAccessKind _mod) {
        if (_context == MethodAccessKind.STATIC) {
            return MethodAccessKind.STATIC;
        }
        if (_mod == MethodAccessKind.STATIC) {
            return MethodAccessKind.STATIC;
        }
        if (_context == MethodAccessKind.STATIC_CALL) {
            return MethodAccessKind.STATIC_CALL;
        }
        if (_mod == MethodAccessKind.STATIC_CALL) {
            return MethodAccessKind.STATIC_CALL;
        }
        return MethodAccessKind.INSTANCE;
    }
    public static MethodAccessKind getKind(MethodModifier _mod) {
        if (_mod == MethodModifier.STATIC) {
            return MethodAccessKind.STATIC;
        }
        if (_mod == MethodModifier.STATIC_CALL) {
            return MethodAccessKind.STATIC_CALL;
        }
        return MethodAccessKind.INSTANCE;
    }
    public static MethodAccessKind getKind(boolean _static) {
        if (_static) {
            return MethodAccessKind.STATIC;
        }
        return MethodAccessKind.INSTANCE;
    }
    @Override
    public String getSignature(ContextEl _ana) {
        return getSignature(_ana.getStandards().getDisplayedStrings());
    }
    public String getSignature(AnalyzedPageEl _ana) {
        return getSignature(_ana.getDisplayedStrings());
    }
    @Override
    public String getSignature(DisplayedStrings _ana) {
        String pref_ = EMPTY;
        if (kind == MethodAccessKind.STATIC) {
            pref_ = StringUtil.concat(_ana.getStaticString()," ");
        } else {
            if (kind == MethodAccessKind.STATIC_CALL) {
                pref_ = StringUtil.concat(_ana.getStaticCallString()," ");
            }
        }
        String suf_ = EMPTY;
        if (vararg) {
            suf_ = VARARG_DOTS;
        }
        return StringUtil.concat(pref_,name,LEFT, StringUtil.join(classNames, SEP_TYPE),suf_,RIGHT);
    }

    public boolean eq(MethodId _obj) {
        if (!StringUtil.quickEq(_obj.name, name)) {
            return false;
        }
        return eqPartial(_obj);
    }

    public boolean eqPartial(MethodId _other) {
        if (kind != _other.kind) {
            return false;
        }
        return IdentifiableUtil.eqPartial(this,_other);
    }

    public MethodId reflectFormat(String _genericClass, ContextEl _context) {
        String name_ = getName();
        int len_ = classNames.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = classNames.get(i);
            String formatted_ = ExecTemplates.reflectFormat(_genericClass, n_, _context);
            pTypes_.add(formatted_);
        }
        return new MethodId(kind, name_, pTypes_, isVararg());
    }
    
    public MethodId quickFormat(ExecRootBlock _root, String _genericClass) {
        StringList pTypes_ = getFormattedTypes(_root,_genericClass);
        return new MethodId(kind, name, pTypes_, isVararg());
    }

    public MethodId quickFormat(AnaGeneType _root, String _genericClass) {
        StringList pTypes_ = getFormattedTypes(_root,_genericClass);
        return new MethodId(kind, name, pTypes_, isVararg());
    }

    public FormattedMethodId quickOverrideFormat(AnaGeneType _root, String _genericClass) {
        StringList pTypes_ = getFormattedTypes(_root,_genericClass);
        return new FormattedMethodId(name, pTypes_, isVararg());
    }

    private StringList getFormattedTypes(ExecRootBlock _root, String _genericClass) {
        int len_ = classNames.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = classNames.get(i);
            String formatted_ = ExecTemplates.quickFormat(_root,_genericClass, n_);
            pTypes_.add(formatted_);
        }
        return pTypes_;
    }

    private StringList getFormattedTypes(AnaGeneType _root, String _genericClass) {
        int len_ = classNames.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = classNames.get(i);
            String formatted_ = AnaTemplates.quickFormat(_root,_genericClass, n_);
            pTypes_.add(formatted_);
        }
        return pTypes_;
    }


    @Override
    public String getName() {
        return name;
    }

    public boolean canAccessParamTypes() {
        return kind != MethodAccessKind.STATIC;
    }

    @Override
    public boolean isStaticMethod() {
        return kind != MethodAccessKind.INSTANCE;
    }

    public MethodAccessKind getKind() {
        return kind;
    }

    @Override
    public StringList getParametersTypes() {
        return new StringList(classNames);
    }
    public StringList shiftFirst() {
        return new StringList(classNames.mid(1));
    }

    @Override
    public int getParametersTypesLength() {
        return classNames.size();
    }

    @Override
    public String getParametersType(int _index) {
        return classNames.get(_index);
    }

    @Override
    public boolean isVararg() {
        return vararg;
    }

}
