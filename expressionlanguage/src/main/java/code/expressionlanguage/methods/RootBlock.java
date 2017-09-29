package code.expressionlanguage.methods;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.BadFieldName;
import code.expressionlanguage.methods.util.BadMethodName;
import code.expressionlanguage.methods.util.BadNumberArgMethod;
import code.expressionlanguage.methods.util.BadParamName;
import code.expressionlanguage.methods.util.BadReturnTypeInherit;
import code.expressionlanguage.methods.util.DuplicateField;
import code.expressionlanguage.methods.util.DuplicateMethod;
import code.expressionlanguage.methods.util.DuplicateParamName;
import code.expressionlanguage.methods.util.FinalMethod;
import code.expressionlanguage.methods.util.IncompatibilityReturnType;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.FctConstraints;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.xml.RowCol;

public abstract class RootBlock extends BracedBlock implements AccessibleBlock {

    private final String name;

    private final String packageName;

    private final AccessEnum access;

    private final String templateDef;

    private ObjectMap<ClassMethodId, StringList> allOverridingMethods;

    private CustList<TypeVar> paramTypes = new CustList<TypeVar>();

    private StringMap<TypeVar> paramTypesMap = new StringMap<TypeVar>();

    RootBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        allOverridingMethods = new ObjectMap<ClassMethodId, StringList>();
        name = _el.getAttribute(ATTRIBUTE_NAME);
        packageName = _el.getAttribute(ATTRIBUTE_PACKAGE);
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
        templateDef = _el.getAttribute(ATTRIBUTE_TEMPLATE_DEF);
    }

    public abstract StringList getAllSuperClasses();

    public abstract StringList getAllSuperTypes();

    public abstract StringList getDirectGenericSuperClasses();

    public abstract StringList getDirectSuperClasses();

    public abstract boolean isFinalType();
    public abstract boolean isAbstractType();

    public abstract StringList getAllInterfaces();

    public abstract ObjectMap<FctConstraints, String> getDefaultMethods();

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

    public ObjectMap<ClassMethodId, StringList> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    public final String getFullName() {
        return getPackageName()+DOT+getName();
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
        EqList<FctConstraints> ids_ = new EqList<FctConstraints>();
        StringList idsField_ = new StringList();
        String className_ = getFullName();
        CustList<Block> bl_ = Classes.getDirectChildren(this);
        for (Block b: bl_) {
            for (EntryCust<String, String> n: b.getClassNames().entryList()) {
                String classNameLoc_ = n.getValue();
                try {
                    String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(classNameLoc_).getComponent();
                    if (_context.getClasses().getClassBody(base_) != null) {
                        continue;
                    }
                    if (!StringList.quickEq(classNameLoc_, OperationNode.VOID_RETURN)) {
                        if (classNameLoc_.startsWith(PrimitiveTypeUtil.PRIM)) {
                            Class<?> cl_ = ConstClasses.getPrimitiveClass(classNameLoc_.substring(1));
                            if (cl_ == null) {
                                throw new RuntimeClassNotFoundException(classNameLoc_);
                            }
                        } else {
                            classNameLoc_ = PrimitiveTypeUtil.getArrayClass(classNameLoc_);
                            ConstClasses.classForNameNotInit(classNameLoc_);
                        }
                    } else {
                        if ((b instanceof MethodBlock)) {
                            if (!StringList.quickEq(n.getKey(), ATTRIBUTE_CLASS)) {
                                UnknownClassName un_ = new UnknownClassName();
                                un_.setClassName(classNameLoc_);
                                un_.setFileName(className_);
                                un_.setRc(b.getRowCol(0, _context.getTabWidth(), n.getKey()));
                                _context.getClasses().getErrorsDet().add(un_);
                            }
                        } else {
                            UnknownClassName un_ = new UnknownClassName();
                            un_.setClassName(classNameLoc_);
                            un_.setFileName(className_);
                            un_.setRc(b.getRowCol(0, _context.getTabWidth(), n.getKey()));
                            _context.getClasses().getErrorsDet().add(un_);
                        }
                    }
                } catch (RuntimeClassNotFoundException _0) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(classNameLoc_);
                    un_.setFileName(className_);
                    un_.setRc(b.getRowCol(0, _context.getTabWidth(), n.getKey()));
                    _context.getClasses().getErrorsDet().add(un_);
                }
            }
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
                FctConstraints fct_ = method_.getConstraints(_context.getClasses());
                MethodId id_ = new MethodId(name_, pTypes_);
                for (FctConstraints m: ids_) {
                    if (m.eq(fct_)) {
                        RowCol r_ = method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                        DuplicateMethod duplicate_;
                        duplicate_ = new DuplicateMethod();
                        duplicate_.setRc(r_);
                        duplicate_.setFileName(className_);
                        duplicate_.setId(id_);
                        _context.getClasses().getErrorsDet().add(duplicate_);
                    }
                }
                StringList l_ = method_.getParametersNames();
                if (l_.size() != len_) {
                    BadNumberArgMethod b_;
                    b_ = new BadNumberArgMethod();
                    b_.setFileName(className_);
                    b_.setRc(method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                    b_.setNbTypes(len_);
                    b_.setNbVars(l_.size());
                    b_.setId(id_);
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
                ids_.add(fct_);
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

    public abstract StringList getDirectGenericSuperTypes();

    public final StringList getAllGenericSuperTypes(ContextEl _context) {
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
                if (StringList.quickEq(baseType_, Object.class.getName())) {
                    continue;
                }
                StringList superTypes_ = _context.getClasses().getClassBody(baseType_).getDirectGenericSuperTypes();
                for (String t: superTypes_) {
                    String format_ = Templates.format(c, t, _context.getClasses());
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
    public final void checkCompatibility(ContextEl _context) {
        ObjectMap<FctConstraints, StringList> signatures_ = getAllInstanceSignatures(_context.getClasses());
        ObjectMap<FctConstraints, String> localSignatures_ = getLocalSignatures(_context.getClasses());
        ObjectMap<FctConstraints, StringList> ov_;
        ov_ = RootBlock.getAllOverridingMethods(signatures_, _context.getClasses());
        ObjectMap<FctConstraints, StringList> er_;
        er_ = RootBlock.areCompatible(localSignatures_, ov_, _context.getClasses());
        for (EntryCust<FctConstraints, StringList> e: er_.entryList()) {
            for (String s: e.getValue()) {
                MethodBlock mDer_ = _context.getClasses().getMethodBody(s, e.getKey());
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFullName());
                err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                err_.setReturnType(mDer_.getReturnType());
                err_.setMethod(mDer_.getId());
                err_.setParentClass(s);
                _context.getClasses().getErrorsDet().add(err_);
            }
        }
        er_ = RootBlock.areModifierCompatible(ov_, _context.getClasses());
        for (EntryCust<FctConstraints, StringList> e: er_.entryList()) {
            for (String s: e.getValue()) {
                MethodBlock mDer_ = _context.getClasses().getMethodBody(s, e.getKey());
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
    public final void checkImplements(ContextEl _context) {
        EqList<ClassMethodId> abstractMethods_ = new EqList<ClassMethodId>();
        boolean concreteClass_ = false;
        if (mustImplement()) {
            concreteClass_ = true;
        }
        StringList allSuperClass_ = getAllSuperClasses();
        for (String s: allSuperClass_) {
            Block superBl_ = _context.getClasses().getClassBody(s);
            for (Block b: Classes.getDirectChildren(superBl_)) {
                if (b instanceof MethodBlock) {
                    MethodBlock mDer_ = (MethodBlock) b;
                    if (mDer_.isAbstractMethod()) {
                        abstractMethods_.add(new ClassMethodId(s, mDer_.getConstraints(_context.getClasses())));
                    }
                }
            }
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock mDer_ = (MethodBlock) b;
                FctConstraints id_ = mDer_.getConstraints(_context.getClasses());
                if (mDer_.isAbstractMethod()) {
                    if (concreteClass_) {
                        AbstractMethod err_;
                        err_ = new AbstractMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                        err_.setSgn(id_.getSignature());
                        err_.setClassName(getFullName());
                        _context.getClasses().getErrorsDet().add(err_);
                    }
                    if (mDer_.getFirstChild() != null) {
                        AbstractMethod err_;
                        err_ = new AbstractMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                        err_.setSgn(id_.getSignature());
                        err_.setClassName(getFullName());
                        _context.getClasses().getErrorsDet().add(err_);
                    }
                }
                String retDerive_ = mDer_.getReturnType();
                for (String o: mDer_.getOverridenClasses()) {
                    MethodBlock mBase_ = _context.getClasses().getMethodBody(o, id_);
                    String retBase_ = mBase_.getReturnType();
                    if (mBase_.isFinalMethod()) {
                        FinalMethod err_;
                        err_ = new FinalMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                        err_.setClassName(o);
                        err_.setId(mDer_.getId());
                        _context.getClasses().getErrorsDet().add(err_);
                    } else if (mDer_.getAccess().ordinal() > mBase_.getAccess().ordinal()) {
                        BadAccessMethod err_;
                        err_ = new BadAccessMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_ACCESS));
                        err_.setId(mDer_.getId());
                        _context.getClasses().getErrorsDet().add(err_);
                    } else if (StringList.quickEq(retBase_, OperationNode.VOID_RETURN)) {
                        if (!StringList.quickEq(retDerive_, OperationNode.VOID_RETURN)) {
                            BadReturnTypeInherit err_;
                            err_ = new BadReturnTypeInherit();
                            err_.setFileName(getFullName());
                            err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                            err_.setReturnType(retDerive_);
                            err_.setMethod(mDer_.getId());
                            err_.setParentClass(o);
                            _context.getClasses().getErrorsDet().add(err_);
                            //throw ex
                        }
                    } else if (!PrimitiveTypeUtil.canBeUseAsArgument(retBase_, retDerive_, _context.getClasses())) {
                        //throw ex
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(getFullName());
                        err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                        err_.setReturnType(retDerive_);
                        err_.setMethod(mDer_.getId());
                        err_.setParentClass(o);
                        _context.getClasses().getErrorsDet().add(err_);
                    }
                }
            }
        }
        if (concreteClass_) {
            for (ClassMethodId m: abstractMethods_) {
                StringList allAssignable_ = new StringList(allSuperClass_);
                allAssignable_.add(getFullName());
                boolean ok_ = false;
                for (String s: allAssignable_) {
                    MethodBlock method_ = _context.getClasses().getMethodBody(s, m.getConstraints());
                    if (method_ == null) {
                        continue;
                    }
                    if (!method_.getAllOverridenClasses().containsStr(m.getClassName())) {
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
                    _context.getClasses().getErrorsDet().add(err_);
                }
            }
        }
        ObjectMap<FctConstraints, StringList> signatures_;
        signatures_ = new ObjectMap<FctConstraints, StringList>();
        StringList allInterfaces_ = getAllInterfaces();
        for (String s: allInterfaces_) {
            if (StringList.quickEq(s, Object.class.getName())) {
                continue;
            }
            InterfaceBlock superBl_ = (InterfaceBlock) _context.getClasses().getClassBody(s);
            ObjectMap<FctConstraints, StringList> signaturesInt_;
            signaturesInt_ = superBl_.getAllInstanceSignatures(_context.getClasses());
            for (EntryCust<FctConstraints, StringList> m: signaturesInt_.entryList()) {
                if (!signatures_.contains(m.getKey())) {
                    signatures_.put(m.getKey(), m.getValue());
                } else {
                    signatures_.getVal(m.getKey()).addAllElts(m.getValue());
                    signatures_.getVal(m.getKey()).removeDuplicates();
                }
            }
        }
        ObjectMap<FctConstraints, StringList> ov_;
        ov_ = RootBlock.getAllOverridingMethods(signatures_, _context.getClasses());
        allSuperClass_ = getAllSuperClasses();
        StringList allAssSuperClass_ = new StringList(allSuperClass_);
        allAssSuperClass_.add(getFullName());
        for (EntryCust<FctConstraints, StringList> e: ov_.entryList()) {
            for (String s: allAssSuperClass_) {
                MethodBlock mDer_ = _context.getClasses().getMethodBody(s, e.getKey());
                if (mDer_ == null) {
                    continue;
                }
                String retDerive_ = mDer_.getReturnType();
                if (mDer_.getAccess() != AccessEnum.PUBLIC) {
                    BadAccessMethod err_;
                    err_ = new BadAccessMethod();
                    err_.setFileName(getFullName());
                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_ACCESS));
                    err_.setId(mDer_.getId());
                    _context.getClasses().getErrorsDet().add(err_);
                } else if(mDer_.isStaticMethod()) {
                    continue;
                } else {
                    for (String i: e.getValue()) {
                        MethodBlock mBase_ = _context.getClasses().getMethodBody(i, e.getKey());
                        String retBase_ = mBase_.getReturnType();
                        if (mBase_.isFinalMethod()) {
                            FinalMethod err_;
                            err_ = new FinalMethod();
                            err_.setFileName(getFullName());
                            err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                            err_.setClassName(getFullName());
                            err_.setId(mDer_.getId());
                            _context.getClasses().getErrorsDet().add(err_);
                        } else if (StringList.quickEq(retBase_, OperationNode.VOID_RETURN)) {
                            if (!StringList.quickEq(retDerive_, OperationNode.VOID_RETURN)) {
                                BadReturnTypeInherit err_;
                                err_ = new BadReturnTypeInherit();
                                err_.setFileName(getFullName());
                                err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                                err_.setReturnType(retDerive_);
                                err_.setMethod(mDer_.getId());
                                err_.setParentClass(i);
                                _context.getClasses().getErrorsDet().add(err_);
                                //throw ex
                            }
                        } else if (!PrimitiveTypeUtil.canBeUseAsArgument(retBase_, retDerive_, _context.getClasses())) {
                            //throw ex
                            BadReturnTypeInherit err_;
                            err_ = new BadReturnTypeInherit();
                            err_.setFileName(getFullName());
                            err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                            err_.setReturnType(retDerive_);
                            err_.setMethod(mDer_.getId());
                            err_.setParentClass(i);
                            _context.getClasses().getErrorsDet().add(err_);
                        }
                    }
                }
            }
        }
        abstractMethods_ = RootBlock.remainingMethodsToImplement(ov_, _context.getClasses());
        if (concreteClass_) {
            for (ClassMethodId m: abstractMethods_) {
                boolean ok_ = false;
                for (String s: allAssSuperClass_) {
                    MethodBlock method_ = _context.getClasses().getMethodBody(s, m.getConstraints());
                    if (method_ == null) {
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
                    _context.getClasses().getErrorsDet().add(err_);
                }
            }
        }
        ObjectMap<FctConstraints, String> def_;
        def_ = RootBlock.defaultMethods(signatures_, _context.getClasses());
        for (EntryCust<FctConstraints, String> e: def_.entryList()) {
            boolean addDefault_ = true;
            for (String s: allAssSuperClass_) {
                MethodBlock m_ = _context.getClasses().getMethodBody(s, e.getKey());
                if (m_ != null) {
                    addDefault_ = false;
                    break;
                }
            }
            if (!addDefault_) {
                continue;
            }
            getDefaultMethods().put(e.getKey(), e.getValue());
        }
    }
    public final ObjectMap<FctConstraints, StringList> getAllInstanceSignatures(Classes _classes) {
        ObjectMap<FctConstraints, StringList> map_;
        map_ = new ObjectMap<FctConstraints, StringList>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                if (method_.isStaticMethod()) {
                    continue;
                }
                map_.put(method_.getConstraints(_classes), new StringList(getFullName()));
            }
        }
        for (String s: getAllSuperTypes()) {
            RootBlock b_ = (RootBlock) _classes.getClassBody(s);
            for (Block b: Classes.getDirectChildren(b_)) {
                if (b instanceof MethodBlock) {
                    MethodBlock method_ = (MethodBlock) b;
                    if (method_.isStaticMethod()) {
                        continue;
                    }
                    addClass(map_, method_.getConstraints(_classes), s);
                }
            }
        }
        return map_;
    }

    public final ObjectMap<FctConstraints, String> getLocalSignatures(Classes _classes) {
        ObjectMap<FctConstraints, String> map_;
        map_ = new ObjectMap<FctConstraints, String>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                map_.put(method_.getConstraints(_classes), getFullName());
            }
        }
        return map_;
    }

    public static ObjectMap<FctConstraints, StringList> getAllOverridingMethods(
            ObjectMap<FctConstraints, StringList> _methodIds,
            Classes _classes) {
        ObjectMap<FctConstraints, StringList> map_;
        map_ = new ObjectMap<FctConstraints, StringList>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            map_.put(e.getKey(), PrimitiveTypeUtil.getSubclasses(e.getValue(), _classes));
        }
        return map_;
    }
    public static ObjectMap<FctConstraints, StringList> areCompatible(
            ObjectMap<FctConstraints, String> _localMethodIds,
            ObjectMap<FctConstraints, StringList> _methodIds, Classes _classes) {
        ObjectMap<FctConstraints, StringList> output_;
        output_ = new ObjectMap<FctConstraints, StringList>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            FctConstraints cst_ = e.getKey();
            StringList classes_ = e.getValue();
            if (_localMethodIds.contains(e.getKey())) {
                //overridden by this interface
                String subInt_ = _localMethodIds.getVal(e.getKey());
                MethodBlock sub_ = _classes.getMethodBody(subInt_, cst_);
                if (sub_.isStaticMethod()) {
                    StringList retClasses_ = new StringList();
                    for (String s: e.getValue()) {
                        MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                        if (sup_.isStaticMethod()) {
                            continue;
                        }
                        retClasses_.add(sup_.getReturnType());
                    }
                    if (!retClasses_.isEmpty() && PrimitiveTypeUtil.getSubslass(retClasses_, _classes).isEmpty()) {
                        for (String c: classes_) {
                            addClass(output_, e.getKey(), c);
                        }
                    }
                    continue;
                }
                String subType_ = sub_.getReturnType();
                for (String s: e.getValue()) {
                    MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                    if (sup_.isStaticMethod()) {
                        continue;
                    }
                    String supType_ = sup_.getReturnType();
                    if (StringList.quickEq(supType_, subType_)) {
                        continue;
                    }
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(supType_, subType_, _classes)) {
                        addClass(output_, e.getKey(), subInt_);
                        addClass(output_, e.getKey(), s);
                    }
                }
                continue;
            }
            StringList retClasses_ = new StringList();
            for (String s: e.getValue()) {
                MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                if (sup_.isStaticMethod()) {
                    continue;
                }
                retClasses_.add(sup_.getReturnType());
            }
            if (!retClasses_.isEmpty() && PrimitiveTypeUtil.getSubslass(retClasses_, _classes).isEmpty()) {
                for (String c: classes_) {
                    addClass(output_, e.getKey(), c);
                }
            }
        }
        return output_;
    }
    public static ObjectMap<FctConstraints, StringList> areModifierCompatible(
            ObjectMap<FctConstraints, StringList> _methodIds, Classes _classes) {
        ObjectMap<FctConstraints, StringList> output_;
        output_ = new ObjectMap<FctConstraints, StringList>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            FctConstraints cst_ = e.getKey();
            StringList retClasses_ = new StringList();
            StringList fClasses_ = new StringList();
            StringList aClasses_ = new StringList();
            for (String s: e.getValue()) {
                MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                if (sup_.isStaticMethod()) {
                    continue;
                }
                if (sup_.isAbstractMethod()) {
                    aClasses_.add(s);
                }
                if (sup_.isFinalMethod()) {
                    fClasses_.add(s);
                }
                retClasses_.add(sup_.getReturnType());
            }
            if (fClasses_.size() > 1) {
                for (String c: fClasses_) {
                    addClass(output_, e.getKey(), c);
                }
                continue;
            }
            if (fClasses_.size() > 0 && aClasses_.size() > 0) {
                if (fClasses_.size() == 1 && _classes.getClassBody(fClasses_.first()) instanceof ClassBlock) {
                    continue;
                }
                for (String c: fClasses_) {
                    addClass(output_, e.getKey(), c);
                }
                for (String c: aClasses_) {
                    addClass(output_, e.getKey(), c);
                }
                continue;
            }
            if (fClasses_.size() == 1) {
                String subInt_ = fClasses_.first();
                MethodBlock sub_ = _classes.getMethodBody(subInt_, cst_);
                String subType_ = sub_.getReturnType();
                for (String s: e.getValue()) {
                    MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                    if (sup_.isStaticMethod()) {
                        continue;
                    }
                    String supType_ = sup_.getReturnType();
                    if (StringList.quickEq(supType_, subType_)) {
                        continue;
                    }
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(supType_, subType_, _classes)) {
                        addClass(output_, e.getKey(), subInt_);
                        addClass(output_, e.getKey(), s);
                    }
                }
            }
        }
        return output_;
    }
    public static EqList<ClassMethodId> remainingMethodsToImplement(
            ObjectMap<FctConstraints, StringList> _methodIds,
            Classes _classes) {
        EqList<ClassMethodId> rem_ = new EqList<ClassMethodId>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            int nbConcrete_ = 0;
            int nbFinal_ = 0;
            int nbAbs_ = 0;
            for (String f: e.getValue()) {
                MethodBlock method_ = _classes.getMethodBody(f, e.getKey());
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
                for (String f: e.getValue()) {
                    ClassMethodId id_ = new ClassMethodId(f, e.getKey());
                    rem_.add(id_);
                }
            }
        }
        return rem_;
    }
    public static ObjectMap<FctConstraints, String> defaultMethods(
            ObjectMap<FctConstraints, StringList> _methodIds,
            Classes _classes) {
        ObjectMap<FctConstraints, String> map_;
        map_ = new ObjectMap<FctConstraints, String>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            boolean found_ = false;
            for (String f: e.getValue()) {
                MethodBlock method_ = _classes.getMethodBody(f, e.getKey());
                if (method_.isFinalMethod()) {
                    map_.put(e.getKey(), f);
                    found_ = true;
                    break;
                }
            }
            if (found_) {
                continue;
            }
            for (String f: e.getValue()) {
                MethodBlock method_ = _classes.getMethodBody(f, e.getKey());
                if (method_.isNormalMethod()) {
                    map_.put(e.getKey(), f);
                    break;
                }
            }
        }
        return map_;
    }

    protected static void addClass(ObjectMap<FctConstraints, StringList> _map, FctConstraints _key, String _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
            _map.getVal(_key).removeDuplicates();
        } else {
            _map.put(_key, new StringList(_class));
        }
    }
}
