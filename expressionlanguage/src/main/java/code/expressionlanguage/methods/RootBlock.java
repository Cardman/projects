package code.expressionlanguage.methods;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.BadFieldName;
import code.expressionlanguage.methods.util.BadMethodName;
import code.expressionlanguage.methods.util.BadNumberArgMethod;
import code.expressionlanguage.methods.util.BadParamName;
import code.expressionlanguage.methods.util.BadReturnTypeInherit;
import code.expressionlanguage.methods.util.DuplicateConstructor;
import code.expressionlanguage.methods.util.DuplicateField;
import code.expressionlanguage.methods.util.DuplicateMethod;
import code.expressionlanguage.methods.util.DuplicateParamMethod;
import code.expressionlanguage.methods.util.DuplicateParamName;
import code.expressionlanguage.methods.util.FinalMethod;
import code.expressionlanguage.methods.util.IncompatibilityReturnType;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassFormattedMethodId;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.types.NativeTypeUtil;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.xml.RowCol;

public abstract class RootBlock extends BracedBlock implements AccessibleBlock {

    private final String name;

    private final String packageName;

    private final AccessEnum access;

    private final String templateDef;

    private ObjectMap<MethodId, EqList<ClassMethodId>> allOverridingMethods;

    private CustList<TypeVar> paramTypes = new CustList<TypeVar>();

    private StringMap<TypeVar> paramTypesMap = new StringMap<TypeVar>();

    private final ObjectMap<MethodId, ClassMethodId> defaultMethodids = new ObjectMap<MethodId, ClassMethodId>();

    RootBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        allOverridingMethods = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        name = _el.getAttribute(ATTRIBUTE_NAME);
        packageName = _el.getAttribute(ATTRIBUTE_PACKAGE);
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
        templateDef = _el.getAttribute(ATTRIBUTE_TEMPLATE_DEF);
    }

    public abstract StringList getAllSuperClasses();

    public abstract StringList getAllGenericSuperClasses(Classes _classes);

    public abstract StringList getAllSuperTypes();

    public abstract StringList getDirectGenericSuperClasses();

    public final StringList getCustomDirectSuperClasses() {
        StringList direct_ = getDirectSuperClasses();
        direct_.removeAllObj(Object.class.getName());
        return direct_;
    }

    /** Copy the list*/
    public abstract StringList getDirectSuperClasses();

    public abstract boolean isFinalType();
    public abstract boolean isAbstractType();

    public abstract StringList getAllGenericInterfaces(Classes _classes);

    public abstract StringList getAllInterfaces();

    public final ObjectMap<MethodId, ClassMethodId> getDefaultMethodIds() {
        return defaultMethodids;
    }

    public abstract boolean mustImplement();

    public void buildMapParamType() {
        paramTypesMap = new StringMap<TypeVar>();
        for (TypeVar t: paramTypes) {
            paramTypesMap.put(t.getName(), t);
        }
    }

    public int getIndex(String _varType) {
        int len_ = paramTypes.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (StringList.quickEq(paramTypes.get(i).getName(), _varType)) {
                return i;
            }
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    public StringMap<TypeVar> getParamTypesMap() {
        return paramTypesMap;
    }

    public CustList<TypeVar> getParamTypes() {
        return paramTypes;
    }

    public String getGenericString() {
        String base_ = getFullName();
        if (paramTypes.isEmpty()) {
            return base_;
        }
        StringBuilder generic_ = new StringBuilder(base_);
        StringList vars_ = new StringList();
        for (TypeVar t:paramTypes) {
            vars_.add(Templates.PREFIX_VAR_TYPE+t.getName());
        }
        generic_.append(Templates.TEMPLATE_BEGIN);
        generic_.append(vars_.join(Templates.TEMPLATE_SEP));
        generic_.append(Templates.TEMPLATE_END);
        return generic_.toString();
    }

    public String getFullDefinition() {
        return getFullName()+getTemplateDef();
    }

    public String getTemplateDef() {
        return templateDef;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    public ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    public final String getFullName() {
        String packageName_ = getPackageName();
        if (packageName_.isEmpty()) {
            return getName();
        }
        return packageName_+DOT+getName();
    }

    protected void validateClassNames(ContextEl _context) {
        CustList<Block> bl_ = Classes.getDirectChildren(this);
        for (Block b: bl_) {
            if (b instanceof Returnable) {
                continue;
            }
            if (b instanceof InfoBlock) {
                continue;
            }
            if (b instanceof AloneBlock) {
                continue;
            }
            //TODO intern classes
            RowCol where_ = ((Block)b).getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
            String tagName_ = ((Block)b).getTagName();
            UnexpectedTagName unexp_ = new UnexpectedTagName();
            unexp_.setFileName(getFullName());
            unexp_.setFoundTag(tagName_);
            unexp_.setRc(where_);
            _context.getClasses().getErrorsDet().add(unexp_);
            //string (class name) - row col - tag name
        }
    }

    public final void validateIds(ContextEl _context) {
        validateClassNames(_context);
        EqList<MethodId> idMethods_ = new EqList<MethodId>();
        EqList<ConstructorId> idConstructors_ = new EqList<ConstructorId>();
        StringList idsField_ = new StringList();
        String className_ = getFullName();
        CustList<Block> bl_;
        bl_ = Classes.getDirectChildren(this);
        for (Block b: bl_) {
            boolean staticContext_;
            if (b instanceof MethodBlock) {
                staticContext_ = ((MethodBlock)b).isStaticMethod();
            } else if (b instanceof InfoBlock) {
                staticContext_ = ((InfoBlock)b).isStaticField();
            } else if (b instanceof ConstructorBlock) {
                staticContext_ = false;
            } else {
                staticContext_ = ((AloneBlock)b).isStaticContext();
            }
            CustList<Block> blLoc_ = Classes.getSortedDescNodes(b);
            StringMap<StringList> vars_ = new StringMap<StringList>();
            if (!staticContext_) {
                for (TypeVar t: getParamTypes()) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            for (Block a: blLoc_) {
                for (EntryCust<String, String> n: a.getClassNames().entryList()) {
                    String classNameLoc_ = n.getValue();
                    if (StringList.quickEq(classNameLoc_, OperationNode.VOID_RETURN)) {
                        if (a == b) {
                            if (b instanceof MethodBlock) {
                                if (!StringList.quickEq(n.getKey(), ATTRIBUTE_CLASS)) {
                                    UnknownClassName un_ = new UnknownClassName();
                                    un_.setClassName(classNameLoc_);
                                    un_.setFileName(className_);
                                    un_.setRc(b.getRowCol(0, _context.getTabWidth(), n.getKey()));
                                    _context.getClasses().getErrorsDet().add(un_);
                                }
                                continue;
                            }
                        }
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(classNameLoc_);
                        un_.setFileName(className_);
                        un_.setRc(b.getRowCol(0, _context.getTabWidth(), n.getKey()));
                        _context.getClasses().getErrorsDet().add(un_);
                        continue;
                    }
                    if (!Templates.correctClassParts(classNameLoc_, vars_, _context.getClasses())) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(classNameLoc_);
                        un_.setFileName(className_);
                        un_.setRc(b.getRowCol(0, _context.getTabWidth(), n.getKey()));
                        _context.getClasses().getErrorsDet().add(un_);
                    }
                }
            }
        }
        for (Block b: bl_) {
            if (b instanceof Returnable) {
                Returnable method_ = (Returnable) b;
                String name_ = method_.getName();
                if (method_ instanceof MethodBlock) {
                    MethodBlock m_ = (MethodBlock) method_;
                    if (!StringList.isWord(name_) || m_.isConcreteInstanceDerivableMethod() != m_.isNormalMethod()) {
                        RowCol r_ = m_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME);
                        BadMethodName badMeth_ = new BadMethodName();
                        badMeth_.setFileName(className_);
                        badMeth_.setRc(r_);
                        badMeth_.setName(name_);
                        _context.getClasses().getErrorsDet().add(badMeth_);
                    }
                }
                StringList types_ = method_.getParametersTypes();
                int len_ = types_.size();
                EqList<ClassName> pTypes_ = new EqList<ClassName>();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    String n_ = types_.get(i);
                    pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                }
                if (name_.isEmpty()) {
                    name_ = className_;
                }
                String sgn_ = method_.getSignature();
                if (method_ instanceof MethodBlock) {
                    boolean st_ = ((MethodBlock)method_).isStaticMethod();
                    MethodId id_ = new MethodId(st_, name_, pTypes_);
                    for (MethodId m: idMethods_) {
                        if (m.eq(id_)) {
                            RowCol r_ = method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                            DuplicateMethod duplicate_;
                            duplicate_ = new DuplicateMethod();
                            duplicate_.setRc(r_);
                            duplicate_.setFileName(className_);
                            duplicate_.setId(id_);
                            _context.getClasses().getErrorsDet().add(duplicate_);
                        }
                    }
                    idMethods_.add(id_);
                }
                if (method_ instanceof ConstructorBlock) {
                    ConstructorId idCt_ = new ConstructorId(name_, pTypes_);
                    for (ConstructorId m: idConstructors_) {
                        if (m.eq(idCt_)) {
                            RowCol r_ = method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                            DuplicateConstructor duplicate_;
                            duplicate_ = new DuplicateConstructor();
                            duplicate_.setRc(r_);
                            duplicate_.setFileName(className_);
                            ConstructorId id_ = new ConstructorId(name_, pTypes_);
                            duplicate_.setId(id_);
                            _context.getClasses().getErrorsDet().add(duplicate_);
                        }
                    }
                    idConstructors_.add(idCt_);
                }
                StringList l_ = method_.getParametersNames();
                if (l_.size() != len_) {
                    BadNumberArgMethod b_;
                    b_ = new BadNumberArgMethod();
                    b_.setFileName(className_);
                    b_.setRc(method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                    b_.setNbTypes(len_);
                    b_.setNbVars(l_.size());
                    b_.setId(sgn_);
                    _context.getClasses().getErrorsDet().add(b_);
                }
                StringList seen_ = new StringList();
                int i_ = CustList.FIRST_INDEX;
                for (String v: l_) {
                    String attr_ = ATTRIBUTE_VAR+i_;
                    if (!StringList.isWord(v)) {
                        BadParamName b_;
                        b_ = new BadParamName();
                        b_.setFileName(className_);
                        b_.setRc(method_.getRowCol(0, _context.getTabWidth(), attr_));
                        b_.setParamName(v);
                        _context.getClasses().getErrorsDet().add(b_);
                    } else if (seen_.containsStr(v)){
                        DuplicateParamName b_;
                        b_ = new DuplicateParamName();
                        b_.setFileName(className_);
                        b_.setRc(method_.getRowCol(0, _context.getTabWidth(), attr_));
                        b_.setParamName(v);
                        _context.getClasses().getErrorsDet().add(b_);
                    } else {
                        seen_.add(v);
                    }
                    i_++;
                }
            } else if (b instanceof InfoBlock) {
                InfoBlock method_ = (InfoBlock) b;
                String name_ = method_.getFieldName();
                InfoBlock m_ = (InfoBlock) b;
                if (!StringList.isWord(name_)) {
                    RowCol r_ = m_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME);
                    BadFieldName badMeth_ = new BadFieldName();
                    badMeth_.setFileName(className_);
                    badMeth_.setRc(r_);
                    badMeth_.setName(name_);
                    _context.getClasses().getErrorsDet().add(badMeth_);
                }
                for (String m: idsField_) {
                    if (StringList.quickEq(m, name_)) {
                        RowCol r_ = method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                        DuplicateField duplicate_;
                        duplicate_ = new DuplicateField();
                        duplicate_.setRc(r_);
                        duplicate_.setFileName(className_);
                        duplicate_.setId(name_);
                        _context.getClasses().getErrorsDet().add(duplicate_);
                    }
                }
                idsField_.add(name_);
            }
        }
    }
    public abstract void setupBasicOverrides(ContextEl _context);

    final void useSuperTypesOverrides(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        StringList all_ = getAllGenericSuperTypes(classesRef_);
        String genericString_ = getGenericString();
        for (MethodBlock m: Classes.getMethodBlocks(this)) {
            if (m.isStaticMethod()) {
                continue;
            }
            MethodId id_ = m.getId();
            addClass(getAllOverridingMethods(), id_, new ClassMethodId(genericString_, id_));
        }
        for (String s: all_) {
            String base_ = StringList.getAllTypes(s).first();
            RootBlock r_ = classesRef_.getClassBody(base_);
            for (MethodBlock m: Classes.getMethodBlocks(r_)) {
                if (m.isStaticMethod()) {
                    continue;
                }
                MethodId id_ = m.getFormattedId(s, classesRef_);
                CustList<MethodBlock> mBases_ = classesRef_.getMethodBodiesByFormattedId(getGenericString(), id_);
                if (!mBases_.isEmpty()) {
                    MethodBlock mBas_ = mBases_.first();
                    MethodId mId_ = mBas_.getId();
                    for (String d: getDirectGenericSuperTypes()) {
                        CustList<MethodBlock> mBasesSuper_ = classesRef_.getMethodBodiesByFormattedId(d, mId_);
                        if (mBasesSuper_.isEmpty()) {
                            continue;
                        }
                        MethodBlock found_ = null;
                        int nbInstances_ = 0;
                        for (MethodBlock g: mBasesSuper_) {
                            found_ = g;
                            nbInstances_++;
                        }
                        if (nbInstances_ > 1) {
                            DuplicateParamMethod duplicate_ = new DuplicateParamMethod();
                            duplicate_.setFileName(getFullName());
                            duplicate_.setRc(new RowCol());
                            duplicate_.setCommonSignature(mId_.getSignature());
                            duplicate_.setOtherType(d);
                            classesRef_.getErrorsDet().add(duplicate_);
                        }
                        if (found_ == null) {
                            continue;
                        }
                        MethodBlock mBase_ = found_;
                        if (mBase_.isFinalMethod()) {
                            FinalMethod err_;
                            err_ = new FinalMethod();
                            err_.setFileName(getFullName());
                            err_.setRc(mBas_.getAttributes().getVal(ATTRIBUTE_NAME));
                            err_.setClassName(d);
                            err_.setId(mId_);
                            classesRef_.getErrorsDet().add(err_);
                        }
                        addClass(getAllOverridingMethods(), mId_, new ClassMethodId(d, mBase_.getId()));
                    }
                    for (String d: getAllGenericSuperTypes(classesRef_)) {
                        RootBlock rGene_ = classesRef_.getClassBody(StringList.getAllTypes(d).first());
                        for (EntryCust<MethodId, EqList<ClassMethodId>> e: rGene_.getAllOverridingMethods().entryList()) {
                            if (e.getKey().format(d, classesRef_).eq(mId_)) {
                                CustList<MethodBlock> methods_ = classesRef_.getMethodBodiesByFormattedId(d, mId_);
                                if (methods_.isEmpty()) {
                                    continue;
                                }
                                MethodBlock m_ = methods_.first();
                                if (m_.isFinalMethod()) {
                                    FinalMethod err_;
                                    err_ = new FinalMethod();
                                    err_.setFileName(getFullName());
                                    err_.setRc(mBas_.getAttributes().getVal(ATTRIBUTE_NAME));
                                    err_.setClassName(d);
                                    err_.setId(mId_);
                                    classesRef_.getErrorsDet().add(err_);
                                }
                                for (ClassMethodId t: e.getValue()) {
                                    String formatted_ = Templates.format(d, t.getClassName(), classesRef_);
                                    addClass(getAllOverridingMethods(), mId_, new ClassMethodId(formatted_, t.getConstraints()));
                                }
                            }
                        }
                    }
                    continue;
                }
                MethodId idReal_ = m.getFormattedId(s, classesRef_);
                addClass(getAllOverridingMethods(), idReal_, new ClassMethodId(s, m.getId()));
            }
        }
    }
    public abstract StringList getDirectGenericSuperTypes();

    public final StringList getAllGenericSuperTypes(Classes _classes) {
        StringList list_ = new StringList();
        StringList vars_ = new StringList();
        for (TypeVar t: getParamTypes()) {
            vars_.add(Templates.PREFIX_VAR_TYPE+t.getName());
        }
        StringList current_;
        if (vars_.isEmpty()) {
            current_ = new StringList(getFullName());
        } else {
            current_ = new StringList(getFullName()+LT+vars_.join(SEP_TMP)+GT);
        }
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = StringList.getAllTypes(c).first();
                StringList superTypes_ = _classes.getClassBody(baseType_).getDirectGenericSuperTypes();
                for (String t: superTypes_) {
                    String format_ = Templates.format(c, t, _classes);
                    list_.add(format_);
                    next_.add(format_);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        return list_;
    }
    public final void checkCompatibilityBounds(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        ObjectMap<MethodId, ClassMethodId> localSignatures_;
        localSignatures_ = new ObjectMap<MethodId, ClassMethodId>();
        ObjectMap<MethodId, EqList<ClassMethodId>> signatures_;
        signatures_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        Mapping mapping_ = new Mapping();
        for (TypeVar t: getParamTypes()) {
            vars_.put(t.getName(), t.getConstraints());
            mapping_.getMapping().put(t.getName(), t.getConstraints());
        }
        for (TypeVar t: getParamTypes()) {
            StringList upper_ = mapping_.getAllUpperBounds(t.getName());
            for (String c: upper_) {
                String base_ = StringList.getAllTypes(c).first();
                if (classesRef_.isCustomType(base_)) {
                    RootBlock r_ = classesRef_.getClassBody(base_);
                    for (EntryCust<MethodId, EqList<ClassMethodId>> e: r_.getAllOverridingMethods().entryList()) {
                        for (ClassMethodId s: e.getValue()) {
                            String c_ = s.getClassName();
                            MethodBlock m_ = classesRef_.getMethodBodiesById(c_, s.getConstraints()).first();
                            if (!classesRef_.canAccess(getFullName(), m_)) {
                                continue;
                            }
                            if (m_.isStaticMethod()) {
                                continue;
                            }
                            MethodId id_ = m_.getFormattedId(c, classesRef_);
                            String formattedType_ = Templates.format(c, c_, classesRef_);
                            addClass(signatures_, id_, new ClassMethodId(formattedType_, m_.getId()));
                        }
                    }
                } else {
                    Class<?> clBound_ = PrimitiveTypeUtil.getSingleNativeClass(base_);
                    for (Method m: clBound_.getMethods()) {
                        if (Modifier.isStatic(m.getModifiers())) {
                            continue;
                        }
                        EqList<ClassName> types_ = new EqList<ClassName>();
                        EqList<ClassName> realTypes_ = new EqList<ClassName>();
                        int len_ = m.getParameterTypes().length;
                        int nbParams_ = m.getTypeParameters().length;
                        for (int i = 0; i < len_; i++) {
                            Class<?> cl_ = m.getParameterTypes()[i];
                            String defaultName_ = cl_.getName();
                            Type p_ = m.getGenericParameterTypes()[i];
                            String alias_ = NativeTypeUtil.getFormattedType(defaultName_, p_.toString(), nbParams_, p_);
                            String formatted_ = Templates.format(c, alias_, classesRef_);
                            types_.add(new ClassName(formatted_, i + 1 == len_ && m.isVarArgs()));
                            realTypes_.add(new ClassName(alias_, i + 1 == len_ && m.isVarArgs()));
                        }
                        MethodId id_ = new MethodId(false, m.getName(), types_);
                        MethodId realId_ = new MethodId(false, m.getName(), realTypes_);
                        addClass(signatures_, id_, new ClassMethodId(c, realId_));
                    }
                }
            }
        }
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: signatures_.entryList()) {
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: e.getValue()) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            StringMap<StringList> map_ = Classes.getBaseParams(list_);
            for (EntryCust<String,StringList> m:map_.entryList()) {
                if (m.getValue().size() > 1) {
                    for (String s: m.getValue()) {
                        MethodId id_ = defs_.getVal(s);
                        MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s, id_).first();
                        IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                        err_.setFileName(getFullName());
                        err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                        err_.setReturnType(mDer_.getReturnType());
                        err_.setMethod(mDer_.getId());
                        err_.setParentClass(s);
                        _context.getClasses().getErrorsDet().add(err_);
                    }
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> ov_;
        ov_ = RootBlock.getAllOverridingMethods(signatures_, _context.getClasses());
        ObjectMap<MethodId, EqList<ClassMethodId>> er_;
        er_ = RootBlock.areCompatible(localSignatures_, vars_, ov_, _context.getClasses());
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
            for (ClassMethodId s: e.getValue()) {
                String s_ = s.getClassName();
                MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFullName());
                err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                err_.setReturnType(mDer_.getReturnType());
                err_.setMethod(mDer_.getId());
                err_.setParentClass(s_);
                _context.getClasses().getErrorsDet().add(err_);
            }
        }
        er_ = RootBlock.areModifierCompatible(ov_, _context.getClasses());
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
            for (ClassMethodId s: e.getValue()) {
                String s_ = s.getClassName();
                MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFullName());
                err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                err_.setReturnType(mDer_.getReturnType());
                err_.setMethod(mDer_.getId());
                err_.setParentClass(s_);
                _context.getClasses().getErrorsDet().add(err_);
            }
        }
    }

    public final void checkCompatibility(ContextEl _context) {
        ObjectMap<MethodId, EqList<ClassMethodId>> signatures_ = getAllInstanceSignatures(_context.getClasses());
        ObjectMap<MethodId, ClassMethodId> localSignatures_ = getLocalSignatures(_context.getClasses());
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypes()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: signatures_.entryList()) {
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: e.getValue()) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            StringMap<StringList> map_ = Classes.getBaseParams(list_);
            for (EntryCust<String,StringList> m:map_.entryList()) {
                if (m.getValue().size() > 1) {
                    for (String s: m.getValue()) {
                        MethodId id_ = defs_.getVal(s);
                        MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s, id_).first();
                        IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                        err_.setFileName(getFullName());
                        err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                        err_.setReturnType(mDer_.getReturnType());
                        err_.setMethod(mDer_.getId());
                        err_.setParentClass(s);
                        _context.getClasses().getErrorsDet().add(err_);
                    }
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> ov_;
        ov_ = RootBlock.getAllOverridingMethods(signatures_, _context.getClasses());
        ObjectMap<MethodId, EqList<ClassMethodId>> er_;
        er_ = RootBlock.areCompatible(localSignatures_, vars_, ov_, _context.getClasses());
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
            for (ClassMethodId s: e.getValue()) {
                String s_ = s.getClassName();
                MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFullName());
                err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                err_.setReturnType(mDer_.getReturnType());
                err_.setMethod(mDer_.getId());
                err_.setParentClass(s_);
                _context.getClasses().getErrorsDet().add(err_);
            }
        }
        er_ = RootBlock.areModifierCompatible(ov_, _context.getClasses());
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
            for (ClassMethodId s: e.getValue()) {
                String s_ = s.getClassName();
                MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFullName());
                err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                err_.setReturnType(mDer_.getReturnType());
                err_.setMethod(mDer_.getId());
                err_.setParentClass(s_);
                _context.getClasses().getErrorsDet().add(err_);
            }
        }
    }
    public final void checkImplements(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        EqList<ClassFormattedMethodId> abstractMethods_ = new EqList<ClassFormattedMethodId>();
        boolean concreteClass_ = false;
        if (mustImplement()) {
            concreteClass_ = true;
        }
        StringList allSuperClass_ = getAllGenericSuperClasses(classesRef_);
        for (String s: allSuperClass_) {
            String base_ = StringList.getAllTypes(s).first();
            RootBlock superBl_ = classesRef_.getClassBody(base_);
            for (MethodBlock m: Classes.getMethodBlocks(superBl_)) {
                if (m.isAbstractMethod()) {
                    abstractMethods_.add(new ClassFormattedMethodId(s, m.getFormattedId(s, classesRef_)));
                }
            }
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypes()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (!(b instanceof MethodBlock)) {
                continue;
            }
            MethodBlock mDer_ = (MethodBlock) b;
            MethodId idFor_ = mDer_.getId();
            if (mDer_.isAbstractMethod()) {
                if (concreteClass_) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFullName());
                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                    err_.setSgn(idFor_.getSignature());
                    err_.setClassName(getFullName());
                    classesRef_.getErrorsDet().add(err_);
                }
                if (mDer_.getFirstChild() != null) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFullName());
                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                    err_.setSgn(idFor_.getSignature());
                    err_.setClassName(getFullName());
                    classesRef_.getErrorsDet().add(err_);
                }
            }
            if (mDer_.isStaticMethod()) {
                continue;
            }
            String retDerive_ = mDer_.getReturnType();
            for (ClassMethodId o: getAllOverridingMethods().getVal(idFor_)) {
                String o_ = o.getClassName();
                if (StringList.quickEq(o_, getGenericString())) {
                    continue;
                }
                MethodBlock mBase_ = classesRef_.getMethodBodiesById(o_, o.getConstraints()).first();
                String retBase_ = mBase_.getReturnType();
                String formattedRetBase_ = Templates.format(o_, retBase_, classesRef_);
                Mapping mapping_ = new Mapping();
                mapping_.getMapping().putAllMap(vars_);
                mapping_.setArg(retDerive_);
                mapping_.setParam(formattedRetBase_);
                if (mBase_.isFinalMethod()) {
                    FinalMethod err_;
                    err_ = new FinalMethod();
                    err_.setFileName(getFullName());
                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                    err_.setClassName(o_);
                    err_.setId(mDer_.getId());
                    classesRef_.getErrorsDet().add(err_);
                } else if (mDer_.getAccess().ordinal() > mBase_.getAccess().ordinal()) {
                    BadAccessMethod err_;
                    err_ = new BadAccessMethod();
                    err_.setFileName(getFullName());
                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_ACCESS));
                    err_.setId(mDer_.getId());
                    classesRef_.getErrorsDet().add(err_);
                } else if (StringList.quickEq(retBase_, OperationNode.VOID_RETURN)) {
                    if (!StringList.quickEq(retDerive_, OperationNode.VOID_RETURN)) {
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(getFullName());
                        err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                        err_.setReturnType(retDerive_);
                        err_.setMethod(mDer_.getId());
                        err_.setParentClass(o_);
                        classesRef_.getErrorsDet().add(err_);
                        //throw ex
                    }
                } else if (!Templates.isCorrect(mapping_, classesRef_)) {
                    //throw ex
                    BadReturnTypeInherit err_;
                    err_ = new BadReturnTypeInherit();
                    err_.setFileName(getFullName());
                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                    err_.setReturnType(retDerive_);
                    err_.setMethod(mDer_.getId());
                    err_.setParentClass(o_);
                    classesRef_.getErrorsDet().add(err_);
                }
            }
        }
        if (concreteClass_) {
            boolean isEnum_ = this instanceof EnumBlock;
            for (ClassFormattedMethodId m: abstractMethods_) {
                if (isEnum_ && m.getConstraints().eq(new MethodId(false, OperationNode.METH_NAME, new EqList<ClassName>()))) {
                    continue;
                }
                if (isEnum_ && m.getConstraints().eq(new MethodId(false, OperationNode.METH_ORDINAL, new EqList<ClassName>()))) {
                    continue;
                }
                StringList allAssignable_ = new StringList();
                allAssignable_.add(getGenericString());
                allAssignable_.addAllElts(allSuperClass_);
                boolean ok_ = false;
                for (String s: allAssignable_) {
                    CustList<MethodBlock> method_ = classesRef_.getMethodBodiesByFormattedId(s, m.getConstraints());
                    if (method_.isEmpty()) {
                        continue;
                    }
                    boolean override_ = false;
                    for (ClassMethodId o: getAllOverridingMethods().getVal(m.getConstraints())) {
                        if (StringList.quickEq(o.getClassName(), m.getClassName())) {
                            override_ = true;
                            break;
                        }
                    }
                    if (!override_) {
                        continue;
                    }
                    if (!method_.first().isConcreteMethod()) {
                        continue;
                    }
                    ok_ = true;
                    break;
                }
                if (!ok_) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFullName());
                    err_.setClassName(m.getClassName());
                    err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                    err_.setSgn(m.getConstraints().getSignature());
                    classesRef_.getErrorsDet().add(err_);
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> signatures_;
        signatures_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        StringList allInterfaces_ = getAllGenericInterfaces(classesRef_);
        for (String s: allInterfaces_) {
            String base_ = StringList.getAllTypes(s).first();
            InterfaceBlock superBl_ = (InterfaceBlock) classesRef_.getClassBody(base_);
            ObjectMap<MethodId, EqList<ClassMethodId>> signaturesInt_;
            signaturesInt_ = superBl_.getAllInstanceSignatures(classesRef_);
            for (EntryCust<MethodId, EqList<ClassMethodId>> m: signaturesInt_.entryList()) {
                MethodId key_ = m.getKey().format(s, classesRef_);
                for (ClassMethodId c: m.getValue()) {
                    String c_ = c.getClassName();
                    String formatCl_ = Templates.format(s, c_, classesRef_);
                    addClass(signatures_, key_, new ClassMethodId(formatCl_, c.getConstraints()));
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> ov_;
        ov_ = RootBlock.getAllOverridingMethods(signatures_, classesRef_);
        allSuperClass_ = getAllGenericSuperClasses(classesRef_);
        StringList allAssSuperClass_ = new StringList();
        allAssSuperClass_.add(getGenericString());
        allAssSuperClass_.addAllElts(allSuperClass_);
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: ov_.entryList()) {
            for (String s: allAssSuperClass_) {
                CustList<MethodBlock> mDers_ = classesRef_.getMethodBodiesByFormattedId(s, e.getKey());
                if (mDers_.isEmpty()) {
                    continue;
                }
                MethodBlock mDer_ = mDers_.first();
                String retDerive_ = mDer_.getReturnType();
                if (mDer_.isStaticMethod()) {
                    continue;
                }
                for (ClassMethodId i: e.getValue()) {
                    String i_ = i.getClassName();
                    MethodBlock mBase_ = classesRef_.getMethodBodiesById(i_, i.getConstraints()).first();
                    String retBase_ = mBase_.getReturnType();
                    String formattedRetBase_ = Templates.format(i_, retBase_, classesRef_);
                    Mapping mapping_ = new Mapping();
                    mapping_.getMapping().putAllMap(vars_);
                    mapping_.setArg(retDerive_);
                    mapping_.setParam(formattedRetBase_);
                    if (mBase_.isFinalMethod()) {
                        FinalMethod err_;
                        err_ = new FinalMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                        err_.setClassName(getFullName());
                        err_.setId(mDer_.getId());
                        classesRef_.getErrorsDet().add(err_);
                    } else if (StringList.quickEq(retBase_, OperationNode.VOID_RETURN)) {
                        if (!StringList.quickEq(retDerive_, OperationNode.VOID_RETURN)) {
                            BadReturnTypeInherit err_;
                            err_ = new BadReturnTypeInherit();
                            err_.setFileName(getFullName());
                            err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                            err_.setReturnType(retDerive_);
                            err_.setMethod(mDer_.getId());
                            err_.setParentClass(i_);
                            classesRef_.getErrorsDet().add(err_);
                            //throw ex
                        }
                    } else if (!Templates.isCorrect(mapping_, classesRef_)) {
                        //throw ex
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(getFullName());
                        err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                        err_.setReturnType(retDerive_);
                        err_.setMethod(mDer_.getId());
                        err_.setParentClass(i_);
                        classesRef_.getErrorsDet().add(err_);
                    }
                }
            }
        }
        abstractMethods_ = RootBlock.remainingMethodsToImplement(ov_, classesRef_);
        if (concreteClass_) {
            boolean isEnum_ = this instanceof EnumBlock;
            for (ClassFormattedMethodId m: abstractMethods_) {
                if (isEnum_ && m.getConstraints().eq(new MethodId(false, OperationNode.METH_NAME, new EqList<ClassName>()))) {
                    continue;
                }
                if (isEnum_ && m.getConstraints().eq(new MethodId(false, OperationNode.METH_ORDINAL, new EqList<ClassName>()))) {
                    continue;
                }
                boolean ok_ = false;
                ok_ = false;
                for (String s: allAssSuperClass_) {
                    CustList<MethodBlock> method_ = classesRef_.getMethodBodiesByFormattedId(s, m.getConstraints());
                    if (method_.isEmpty()) {
                        continue;
                    }
                    if (!method_.first().isConcreteMethod()) {
                        continue;
                    }
                    ok_ = true;
                    break;
                }
                if (!ok_) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFullName());
                    err_.setClassName(m.getClassName());
                    err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                    err_.setSgn(m.getConstraints().getSignature());
                    classesRef_.getErrorsDet().add(err_);
                }
            }
        }
        ObjectMap<MethodId, ClassMethodId> def_;
        def_ = RootBlock.defaultMethods(signatures_, classesRef_);
        for (EntryCust<MethodId, ClassMethodId> e: def_.entryList()) {
            ClassMethodId v_ = e.getValue();
            String e_ = v_.getClassName();
            CustList<MethodBlock> current_ = classesRef_.getMethodBodiesById(e_, v_.getConstraints());
            boolean addDefault_ = true;
            for (String s: allAssSuperClass_) {
                CustList<MethodBlock> m_ = classesRef_.getMethodBodiesByFormattedId(s, e.getKey());
                if (!m_.isEmpty()) {
                    addDefault_ = false;
                    break;
                }
            }
            if (!addDefault_) {
                continue;
            }
            MethodBlock method_ = current_.first();
            ClassMethodId clId_ = e.getValue();
            String cl_ = clId_.getClassName();
            MethodId id_ = method_.getFormattedId(cl_, classesRef_);
            getDefaultMethodIds().put(id_, clId_);
        }
    }
    public final ObjectMap<MethodId, EqList<ClassMethodId>> getAllInstanceSignatures(Classes _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                if (method_.isStaticMethod()) {
                    continue;
                }
                map_.put(method_.getId(), new EqList<ClassMethodId>(new ClassMethodId(getGenericString(), method_.getId())));
            }
        }
        for (String s: getAllGenericSuperTypes(_classes)) {
            String base_ = StringList.getAllTypes(s).first();
            RootBlock b_ = (RootBlock) _classes.getClassBody(base_);
            for (Block b: Classes.getDirectChildren(b_)) {
                if (b instanceof MethodBlock) {
                    MethodBlock method_ = (MethodBlock) b;
                    if (method_.isStaticMethod()) {
                        continue;
                    }
                    addClass(map_, method_.getFormattedId(s, _classes), new ClassMethodId(s, method_.getId()));
                }
            }
        }
        return map_;
    }
    public final ObjectMap<MethodId, String> getLocalInstanceSignatures(Classes _classes) {
        ObjectMap<MethodId, String> map_;
        map_ = new ObjectMap<MethodId, String>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                if (method_.isStaticMethod()) {
                    continue;
                }
                map_.put(method_.getId(), getGenericString());
            }
        }
        return map_;
    }
    public final ObjectMap<MethodId, ClassMethodId> getLocalSignatures(Classes _classes) {
        ObjectMap<MethodId, ClassMethodId> map_;
        map_ = new ObjectMap<MethodId, ClassMethodId>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                map_.put(method_.getId(), new ClassMethodId(getGenericString(), method_.getId()));
            }
        }
        return map_;
    }

    public static ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds,
            Classes _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: e.getValue()) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            list_ = PrimitiveTypeUtil.getSubclasses(list_, _classes);
            EqList<ClassMethodId> out_ = new EqList<ClassMethodId>();
            for (String v: list_) {
                out_.add(new ClassMethodId(v, defs_.getVal(v)));
            }
            map_.put(e.getKey(), out_);
        }
        return map_;
    }

    public static ObjectMap<MethodId, EqList<ClassMethodId>> areCompatible(
            ObjectMap<MethodId, ClassMethodId> _localMethodIds,
            StringMap<StringList> _vars,
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds, Classes _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> output_;
        output_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            MethodId cst_ = e.getKey();
            EqList<ClassMethodId> classes_ = e.getValue();
            Mapping mapping_ = new Mapping();
            mapping_.getMapping().putAllMap(_vars);
            if (_localMethodIds.contains(e.getKey())) {
                //overridden by this interface
                ClassMethodId subInt_ = _localMethodIds.getVal(e.getKey());
                String name_ = subInt_.getClassName();
                MethodBlock sub_ = _classes.getMethodBodiesById(name_, subInt_.getConstraints()).first();
                if (sub_.isStaticMethod()) {
                    StringMap<StringList> vars_ = new StringMap<StringList>();
                    StringList retClasses_ = new StringList();
                    for (ClassMethodId s: e.getValue()) {
                        String supName_ = s.getClassName();
                        MethodBlock sup_ = _classes.getMethodBodiesById(supName_, s.getConstraints()).first();
                        if (sup_.isStaticMethod()) {
                            continue;
                        }
                        retClasses_.add(sup_.getReturnType());
                    }
                    if (!retClasses_.isEmpty() && PrimitiveTypeUtil.getSubslass(retClasses_, vars_, _classes).isEmpty()) {
                        for (ClassMethodId c: classes_) {
                            addClass(output_, e.getKey(), c);
                        }
                    }
                    continue;
                }
                String subType_ = sub_.getReturnType();
                mapping_.setArg(subType_);
                for (ClassMethodId s: e.getValue()) {
                    String supName_ = s.getClassName();
                    MethodBlock sup_ = _classes.getMethodBodiesById(supName_, s.getConstraints()).first();
                    if (sup_.isStaticMethod()) {
                        continue;
                    }
                    String formattedSup_ = Templates.format(supName_, sup_.getReturnType(), _classes);
                    mapping_.setParam(formattedSup_);
                    if (StringList.quickEq(formattedSup_, subType_)) {
                        continue;
                    }
                    if (!Templates.isCorrect(mapping_, _classes)) {
                        addClass(output_, e.getKey(), subInt_);
                        addClass(output_, e.getKey(), s);
                    }
                }
                continue;
            }
            StringList retClasses_ = new StringList();
            for (ClassMethodId s: e.getValue()) {
                String name_ = s.getClassName();
                String base_ = StringList.getAllTypes(name_).first();
                if (_classes.getClassBody(base_) == null) {
                    Class<?> clBound_ = PrimitiveTypeUtil.getSingleNativeClass(base_);
                    for (Method m: clBound_.getMethods()) {
                        if (Modifier.isStatic(m.getModifiers())) {
                            continue;
                        }
                        EqList<ClassName> types_ = new EqList<ClassName>();
                        int len_ = m.getParameterTypes().length;
                        int nbParams_ = m.getTypeParameters().length;
                        for (int i = 0; i < len_; i++) {
                            Class<?> cl_ = m.getParameterTypes()[i];
                            String defaultName_ = cl_.getName();
                            Type p_ = m.getGenericParameterTypes()[i];
                            String alias_ = NativeTypeUtil.getFormattedType(defaultName_, p_.toString(), nbParams_, p_);
                            String formatted_ = Templates.format(name_, alias_, _classes);
                            types_.add(new ClassName(formatted_, i + 1 == len_ && m.isVarArgs()));
                        }
                        MethodId id_ = new MethodId(false, m.getName(), types_);
                        if (!id_.eq(cst_)) {
                            continue;
                        }
                        Class<?> cl_ = m.getReturnType();
                        String defaultName_ = cl_.getName();
                        Type returnType_ = m.getGenericReturnType();
                        String alias_ = NativeTypeUtil.getFormattedType(defaultName_, returnType_.toString(), nbParams_, returnType_);
                        String formatted_ = Templates.format(name_, alias_, _classes);
                        retClasses_.add(formatted_);
                    }
                    continue;
                }
                MethodBlock sup_ = _classes.getMethodBodiesById(name_, s.getConstraints()).first();
                if (sup_.isStaticMethod()) {
                    continue;
                }
                String ret_ = sup_.getReturnType();
                retClasses_.add(Templates.format(name_, ret_, _classes));
            }
            if (!retClasses_.isEmpty() && PrimitiveTypeUtil.getSubslass(retClasses_, _vars, _classes).isEmpty()) {
                for (ClassMethodId c: classes_) {
                    addClass(output_, e.getKey(), c);
                }
            }
        }
        return output_;
    }

    public static ObjectMap<MethodId, EqList<ClassMethodId>> areModifierCompatible(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds, Classes _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> output_;
        output_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            MethodId cst_ = e.getKey();
            EqList<ClassMethodId> fClasses_ = new EqList<ClassMethodId>();
            EqList<ClassMethodId> aClasses_ = new EqList<ClassMethodId>();
            for (ClassMethodId s: e.getValue()) {
                String name_ = s.getClassName();
                String base_ = StringList.getAllTypes(name_).first();
                if (_classes.getClassBody(base_) == null) {
                    continue;
                }
                MethodBlock sup_ = _classes.getMethodBodiesById(name_, s.getConstraints()).first();
                if (sup_.isStaticMethod()) {
                    continue;
                }
                if (sup_.isAbstractMethod()) {
                    aClasses_.add(s);
                }
                if (sup_.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
            if (fClasses_.size() > 1) {
                for (ClassMethodId c: fClasses_) {
                    addClass(output_, cst_, c);
                }
                continue;
            }
            if (fClasses_.size() > 0 && aClasses_.size() > 0) {
                if (fClasses_.size() == 1) {
                    String name_ = fClasses_.first().getClassName();
                    String base_ = StringList.getAllTypes(name_).first();
                    if (_classes.getClassBody(base_) instanceof ClassBlock) {
                        continue;
                    }
                }
                for (ClassMethodId c: fClasses_) {
                    addClass(output_, cst_, c);
                }
                for (ClassMethodId c: aClasses_) {
                    addClass(output_, cst_, c);
                }
                continue;
            }
            if (fClasses_.size() == 1) {
                Mapping map_ = new Mapping();
                ClassMethodId subInt_ = fClasses_.first();
                String subIntName_ = subInt_.getClassName();
                String subIntBase_ = StringList.getAllTypes(subIntName_).first();
                RootBlock r_ = _classes.getClassBody(subIntBase_);
                StringMap<StringList> vars_ = new StringMap<StringList>();
                for (TypeVar t: r_.getParamTypes()) {
                    vars_.put(t.getName(), t.getConstraints());
                }
                map_.getMapping().putAllMap(vars_);
                MethodBlock sub_ = _classes.getMethodBodiesById(subIntName_, subInt_.getConstraints()).first();
                String subType_ = sub_.getReturnType();
                map_.setParam(subType_);
                for (ClassMethodId s: e.getValue()) {
                    String s_ = s.getClassName();
                    MethodBlock sup_ = _classes.getMethodBodiesById(s_, s.getConstraints()).first();
                    if (sup_.isStaticMethod()) {
                        continue;
                    }
                    String supType_ = sup_.getReturnType();
                    String formattedSupType_ = Templates.format(s_, supType_, _classes);
                    map_.setArg(formattedSupType_);
                    if (StringList.quickEq(formattedSupType_, subType_)) {
                        continue;
                    }
                    if (!Templates.isCorrect(map_, _classes)) {
                        addClass(output_, cst_, subInt_);
                        addClass(output_, cst_, s);
                    }
                }
            }
        }
        return output_;
    }
    public static EqList<ClassFormattedMethodId> remainingMethodsToImplement(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds,
            Classes _classes) {
        EqList<ClassFormattedMethodId> rem_ = new EqList<ClassFormattedMethodId>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            int nbConcrete_ = 0;
            int nbFinal_ = 0;
            int nbAbs_ = 0;
            for (ClassMethodId f: e.getValue()) {
                String f_ = f.getClassName();
                MethodBlock method_ = _classes.getMethodBodiesById(f_, f.getConstraints()).first();
                if (method_.isStaticMethod()) {
                    continue;
                }
                if (method_.isFinalMethod()) {
                    nbFinal_++;
                }
                if (method_.isConcreteMethod()) {
                    nbConcrete_++;
                } else {
                    nbAbs_++;
                }
            }
            if (nbConcrete_ != 1 && nbFinal_ == 0|| nbAbs_ != 0) {
                for (ClassMethodId f: e.getValue()) {
                    String f_ = f.getClassName();
                    ClassFormattedMethodId id_ = new ClassFormattedMethodId(f_, e.getKey());
                    rem_.add(id_);
                }
            }
        }
        return rem_;
    }
    public static ObjectMap<MethodId, ClassMethodId> defaultMethods(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds,
            Classes _classes) {
        ObjectMap<MethodId, ClassMethodId> map_;
        map_ = new ObjectMap<MethodId, ClassMethodId>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            boolean found_ = false;
            for (ClassMethodId f: e.getValue()) {
                String f_ = f.getClassName();
                MethodBlock method_ = _classes.getMethodBodiesById(f_, f.getConstraints()).first();
                if (method_.isFinalMethod()) {
                    map_.put(e.getKey(), f);
                    found_ = true;
                    break;
                }
            }
            if (found_) {
                continue;
            }
            for (ClassMethodId f: e.getValue()) {
                String f_ = f.getClassName();
                MethodBlock method_ = _classes.getMethodBodiesById(f_, f.getConstraints()).first();
                if (method_.isNormalMethod()) {
                    map_.put(e.getKey(), f);
                    break;
                }
            }
        }
        return map_;
    }
    
    protected static void addClass(ObjectMap<MethodId, EqList<ClassMethodId>> _map, MethodId _key, ClassMethodId _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
            _map.getVal(_key).removeDuplicates();
        } else {
            _map.put(_key, new EqList<ClassMethodId>(_class));
        }
    }

    public abstract StringList getAllSortedInterfaces();

    public abstract StringList getAllNeededSortedInterfaces();

    public abstract StringList getDirectInterfaces();
}
