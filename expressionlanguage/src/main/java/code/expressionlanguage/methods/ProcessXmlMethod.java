package code.expressionlanguage.methods;
import java.util.Iterator;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustBase;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.exceptions.BadIndexException;
import code.expressionlanguage.exceptions.CustomFoundConstructorException;
import code.expressionlanguage.exceptions.CustomFoundMethodException;
import code.expressionlanguage.exceptions.DivideZeroException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.IndirectException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.exceptions.NegativeSizeException;
import code.expressionlanguage.exceptions.NotInitializedClassException;
import code.expressionlanguage.exceptions.StackOverFlow;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.methods.util.CallConstructor;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.FctConstraints;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ProcessXmlMethod {
    protected static final String ATTRIBUTE_PACKAGE = "package";
    protected static final String ATTRIBUTE_NAME = "name";
    protected static final String ATTRIBUTE_LEFT = "left";
    protected static final String ATTRIBUTE_OPER = "oper";
    protected static final String ATTRIBUTE_RIGHT = "right";
    protected static final String ATTRIBUTE_VAR = "var";
    protected static final String ATTRIBUTE_CLASS = "class";
    protected static final String ATTRIBUTE_CLASS_INDEX = "classindex";
    protected static final String ATTRIBUTE_CONDITION = "condition";
    protected static final String ATTRIBUTE_VALUE = "value";
    protected static final String ATTRIBUTE_EXPRESSION = "expression";
    protected static final String ATTRIBUTE_EQ = "eq";
    protected static final String ATTRIBUTE_INIT = "init";
    protected static final String ATTRIBUTE_STEP = "step";
    private static final String EMPTY_STRING = "";
    private static final String RETURN_LINE = "\n";

    private ProcessXmlMethod() {
    }

    public static void initializeClass(String _class, ContextEl _cont) {
        _cont.getClasses().initialize(_class);
        _cont.addPage(createInstancingClass(_class, _cont));
        loopCallings(_cont);
    }
    public static Argument instanceArgument(String _class, Argument _global, FctConstraints _id, CustList<Argument> _args, ContextEl _cont) {
        CallConstructor call_ = new CallConstructor();
        call_.setArgument(_global);
        call_.setId(_id);
        call_.setInstancingStep(InstancingStep.NEWING);
        PageEl page_ = createInstancing(_class, call_, _args, _cont);
        _cont.addPage(page_);
        loopCallings(_cont);
        return page_.getReturnedArgument();
    }

    public static Argument calculateArgument(Argument _global, String _class, FctConstraints _method, CustList<Argument> _args, ContextEl _cont) {
        Classes classes_ = _cont.getClasses();
        MethodBlock method_ = classes_.getMethodBody(_class, _method);
        Block firstChild_ = method_.getFirstChild();
        if (firstChild_ == null) {
            Argument a_ = new Argument();
            return a_;
        }
        PageEl page_ = createCallingMethod(_global, _class, _method, _args, _cont);
        _cont.addPage(page_);
        loopCallings(_cont);
        return page_.getReturnedArgument();
    }
    private static void loopCallings(ContextEl _cont) {
        int sizeBk_ = _cont.nbPages() - 1;
        while (true) {
            try {
                if (_cont.getLastPage().getReadWrite() == null) {
                    PageEl p_ = _cont.getLastPage();
                    _cont.removeLastPage();
                    if (_cont.nbPages() == sizeBk_) {
                        break;
                    }
                    Argument a_ = p_.getReturnedArgument();
                    PageEl l_ = _cont.getLastPage();
                    if (a_ != null) {
                        l_.getCurrentEls().last().setArgument(a_, _cont);
                    }
                    if (p_.getCallingConstr().getInstancingStep() == InstancingStep.USING_THIS) {
                        l_.getCallingConstr().setInitializedFields(true);
                    }
                    continue;
                }
                processTags(_cont);
            } catch (CustomFoundConstructorException _0) {
                addPage(_cont, createInstancing(_0, _cont));
            } catch (NotInitializedClassException _0){
                addPage(_cont, createInstancingClass(_0, _cont));
            } catch (CustomFoundMethodException _0){
                addPage(_cont, createCallingMethod(_0, _cont));
            } catch (Throwable _0){
                Throwable t_ = throwException(_cont, _0);
                if (t_ == null) {
                    continue;
                }
                if (_0 instanceof IndirectException) {
                    throw (IndirectException) _0;
                }
                throw new InvokeRedinedMethException(new Struct(_0));
            }
        }
    }

    private static PageEl createInstancingClass(NotInitializedClassException _e, ContextEl _cont) {
        return createInstancingClass(_e.getClassName(), _cont);
    }
    private static PageEl createInstancingClass(String _class, ContextEl _cont) {
        Classes classes_ = _cont.getClasses();
        classes_.preInitializeStaticFields(_class);
        RootBlock class_ = classes_.getClassBody(_class);
        Block firstChild_ = class_.getFirstChild();
        PageEl page_ = new PageEl();
        Argument argGl_ = new Argument();
        page_.setInitializingClass(true);
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        page_.setReadUrl(_class);
        ReadWrite rw_ = new ReadWrite();
        rw_.setBlock(firstChild_);
        page_.setReadWrite(rw_);
        page_.setBlockRoot((Block) class_);
        return page_;
    }

    private static PageEl createCallingMethod(CustomFoundMethodException _e, ContextEl _conf) {
        String cl_ = _e.getClassName();
        FctConstraints id_ = _e.getId();
        CustList<Argument> args_ = _e.getArguments();
        Argument gl_ = _e.getGl();
        return createCallingMethod(gl_, cl_, id_, args_, _conf);
    }
    private static PageEl createCallingMethod(Argument _gl, String _class, FctConstraints _method, CustList<Argument> _args, ContextEl _conf) {
        Classes classes_ = _conf.getClasses();
        String cl_ = _class;
        PageEl pageLoc_ = new PageEl();
        pageLoc_.setGlobalArgument(_gl);
        pageLoc_.setGlobalClass(_class);
        pageLoc_.setReadUrl(_class);
        FctConstraints id_ = _method;
        MethodBlock methodLoc_ = classes_.getMethodBody(cl_, id_);
        StringList paramsLoc_ = methodLoc_.getParametersNames();
        StringList typesLoc_ = methodLoc_.getParametersTypes();
        CustList<Argument> args_ = _args;
        int lenLoc_ = paramsLoc_.size();
        for (int i = CustList.FIRST_INDEX; i < lenLoc_; i++) {
            String p_ = paramsLoc_.get(i);
            String c_ = typesLoc_.get(i);
            LocalVariable lv_ = new LocalVariable();
            lv_.setStruct(args_.get(i).getStruct());
            lv_.setClassName(c_);
            pageLoc_.getParameters().put(p_, lv_);
        }
        ReadWrite rwLoc_ = new ReadWrite();
        rwLoc_.setBlock(methodLoc_.getFirstChild());
        pageLoc_.setReadWrite(rwLoc_);
        pageLoc_.setBlockRoot(methodLoc_);
        return pageLoc_;
    }
    private static PageEl createInstancing(CustomFoundConstructorException _e, ContextEl _conf) {
        String cl_ = _e.getClassName();
        CustList<Argument> args_ = _e.getArguments();
        return createInstancing(cl_, _e.getCall(), args_, _conf);
    }
    private static PageEl createInstancing(String _class, CallConstructor _call, CustList<Argument> _args, ContextEl _cont) {
        PageEl page_ = new PageEl();
        Argument global_ = _call.getArgument();
        FctConstraints id_ = _call.getId();
        InstancingStep in_ = _call.getInstancingStep();
        Classes classes_ = _cont.getClasses();
        RootBlock class_ = classes_.getClassBody(_class);
        ConstructorBlock method_ = classes_.getConstructorBody(_class, id_);
        Argument argGl_ = new Argument();
        if (in_ == InstancingStep.NEWING) {
            StringList allClasses_ = new StringList(_class);
            allClasses_.addAllElts(class_.getAllSuperTypes());
            ObjectMap<ClassField,Struct> fields_;
            fields_ = new ObjectMap<ClassField,Struct>();
            for (String c: allClasses_) {
                ClassMetaInfo clMetaLoc_ = classes_.getClassMetaInfo(c);
                if (clMetaLoc_ == null) {
                    continue;
                }
                for (EntryCust<String, FieldMetaInfo> e: clMetaLoc_.getFields().entryList()) {
                    FieldMetaInfo fieldMeta_ = e.getValue();
                    if (fieldMeta_.isStaticField()) {
                        continue;
                    }
                    String fieldDeclClass_ = fieldMeta_.getType().getName();
                    Object o_ = PrimitiveTypeUtil.defaultValue(fieldDeclClass_);
                    if (o_ == null) {
                        fields_.put(new ClassField(c, e.getKey()), new Struct());
                    } else {
                        fields_.put(new ClassField(c, e.getKey()), new Struct(o_));
                    }
                }
            }
            Struct str_ = null;
            if (global_ != null) {
                str_ = global_.getStruct();
            }
            String fieldName_ = EMPTY_STRING;
            if (!_cont.isEmptyPages()) {
                PageEl last_ = _cont.getLastPage();
                fieldName_ = last_.getEnumName();
            }
            if (fieldName_.isEmpty()) {
                argGl_.setStruct(new Struct(new CustBase(),_class, fields_, str_));
            } else {
                Struct enum_ = classes_.getStaticField(new ClassField(_class, fieldName_));
                if (!enum_.isNull()) {
                    argGl_.setStruct(enum_);
                } else {
                    argGl_.setStruct(new Struct(new CustBase(),_class, fields_, str_));
                }
            }
        } else {
            PageEl last_ = _cont.getLastPage();
            page_.setEnumName(last_.getEnumName());
            argGl_.setStruct(global_.getStruct());
        }
        page_.setReadUrl(_class);
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        if (method_ != null) {
            StringList params_ = method_.getParametersNames();
            StringList types_ = method_.getParametersTypes();
            int len_ = params_.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                String p_ = params_.get(i);
                String c_ = types_.get(i);
                LocalVariable lv_ = new LocalVariable();
                lv_.setStruct(_args.get(i).getStruct());
                lv_.setClassName(c_);
                page_.getParameters().put(p_, lv_);
            }
            Block firstChild_ = method_.getFirstChild();
            rw_.setBlock(firstChild_);
        } else {
            Block firstChild_ = class_.getFirstChild();
            rw_.setBlock(firstChild_);
        }
        page_.setReadWrite(rw_);
        page_.getCallingConstr().setInstancingStep(in_);
        page_.getCallingConstr().setUsedConstructor(method_);
        page_.setBlockRoot((Block) class_);
        return page_;
    }
    private static void addPage(ContextEl _conf, PageEl _page) {
        try {
            _conf.addPage(_page);
        } catch (StackOverFlow _0) {
            Throwable t_ = throwException(_conf, _0);
            if (t_ == null) {
                return;
            }
            throw _0;
        }
    }
    private static Throwable throwException(ContextEl _conf, Throwable _t) {
        CatchEval catchElt_ = null;
        boolean indirect_ = _t instanceof IndirectException;
        Struct custCause_;
        if (indirect_) {
            custCause_ = ((IndirectException)_t).getCustCause();
        } else {
            Throwable cause_ = _t.getCause();
            if (cause_ == null) {
                custCause_ = new Struct();
            } else {
                custCause_ = new Struct(cause_);
            }
        }
        while (!_conf.isEmptyPages()) {
            PageEl bkIp_ = _conf.getLastPage();
            while (!bkIp_.noBlock()) {
                RemovableVars bl_ = bkIp_.getLastStack();
                if (!(bl_ instanceof TryBlockStack)) {
                    ((RemovableVars)bl_).removeVarAndLoop(bkIp_);
                    continue;
                }
                TryBlockStack try_ = (TryBlockStack)bl_;
                boolean addFinallyClause_ = true;
                if (!(try_.getCatchBlocks().last() instanceof FinallyEval)) {
                    addFinallyClause_ = false;
                }
                if (try_.getVisitedCatch() >= CustList.FIRST_INDEX) {
                    if (!(try_.getCurrentCatchBlock() instanceof FinallyEval)) {
                        if (addFinallyClause_) {
                            try_.setThrownException(_t);
                            bkIp_.getCurrentEls().clear();
                            bkIp_.getReadWrite().setBlock(try_.getCatchBlocks().last());
                            return null;
                        }
                    }
                    bkIp_.removeLastBlock();
                    continue;
                }
                //process try block
                int i_ = 0;
                for (Block e: try_.getCatchBlocks()) {
                    if (e instanceof FinallyEval) {
                        break;
                    }
                    CatchEval ca_ = (CatchEval) e;
                    String name_ = ca_.getClassName();
                    if (!indirect_) {
                        if (PrimitiveTypeUtil.canBeUseAsArgument(name_, _t.getClass().getName(), _conf.getClasses())) {
                            catchElt_ = ca_;
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    } else {
                        if (PrimitiveTypeUtil.canBeUseAsArgument(name_, custCause_.getClassName(), _conf.getClasses())) {
                            catchElt_ = ca_;
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    }
                    i_++;
                }
                if (catchElt_ != null) {
                    CatchEval catchElement_ = catchElt_;
                    try_.setThrownException(null);
                    bkIp_.getCurrentEls().clear();
                    if (catchElement_.getFirstChild() != null) {
                        String var_ = catchElement_.getVariableName();
                        LocalVariable lv_ = new LocalVariable();
                        Throwable t_ = _t;
                        if (indirect_) {
                            lv_.setStruct(custCause_);
                        } else {
                            lv_.setStruct(new Struct(t_));
                        }
                        lv_.setClassName(catchElement_.getClassName());
                        bkIp_.getCatchVars().put(var_, lv_);
                        bkIp_.getReadWrite().setBlock(catchElement_.getFirstChild());
                        return null;
                    }
                    bkIp_.getReadWrite().setBlock(catchElement_);
                    return null;
                }
                if (addFinallyClause_) {
                    try_.setThrownException(_t);
                    bkIp_.getCurrentEls().clear();
                    bkIp_.getReadWrite().setBlock(try_.getCatchBlocks().last());
                    return null;
                }
                bkIp_.removeLastBlock();
            }
            _conf.removeLastPage();
        }
        return _t;
    }
    /**@throws InvokeRedinedMethException
    @throws DivideZeroException
    @throws BadIndexException
    @throws NegativeSizeException
    @throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException
    @throws BadEnumeratingException
    @throws BadFilePropertiesException
    @throws BadReferenceEqualsException
    @throws CharacterFormatException
    @throws GettingKeysException
    @throws InexistingTranslatorException
    @throws MessageKeyNotFoundException
    @throws NoSuchResourceException
    @throws NotCastableException
    @throws NotPrimitivableException
    @throws SettingArrayException*/
    private static void processTags(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        Block en_ = rw_.getBlock();
        if (ip_.isInitializingClass()) {
            String curClass_ = ip_.getGlobalClass();
            ClassMetaInfo meta_ = _conf.getClasses().getClassMetaInfo(curClass_);
            String superClass_ = meta_.getSuperClass();
            ClassMetaInfo s_ = _conf.getClasses().getClassMetaInfo(superClass_);
            if (s_ != null && !_conf.getClasses().isInitialized(superClass_)) {
                _conf.getClasses().initialize(superClass_);
                throw new NotInitializedClassException(superClass_);
            }
            if (meta_.getCategory() != ClassCategory.INTERFACE) {
                UniqueRootedBlock root_ = (UniqueRootedBlock) _conf.getClasses().getClassBody(curClass_);
                for (String i: root_.getAllNeededSortedInterfaces()) {
                    if (!_conf.getClasses().isInitialized(i)) {
                        _conf.getClasses().initialize(i);
                        throw new NotInitializedClassException(i);
                    }
                }
            }
        }
        boolean implicitConstr_ = false;
        if (ip_.isInstancing()) {
            CallConstructor caller_ = ip_.getCallingConstr();
            ConstructorBlock const_ = caller_.getUsedConstructor();
            if (const_ == null) {
                implicitConstr_ = true;
            } else if (const_.implicitConstr()) {
                implicitConstr_ = true;
            }
        }
        if (implicitConstr_) {
            CallConstructor caller_ = ip_.getCallingConstr();
            boolean calledImpl_ = caller_.isCalledImplicitConstructor();
            String curClass_ = ip_.getGlobalClass();
            ClassMetaInfo meta_ = _conf.getClasses().getClassMetaInfo(curClass_);
            String superClass_ = meta_.getSuperClass();
            if (!calledImpl_ && !StringList.quickEq(superClass_, Object.class.getName()) && meta_.getCategory() != ClassCategory.INTERFACE) {
                ip_.getCallingConstr().setCalledImplicitConstructor(true);
                FctConstraints super_ = new FctConstraints(superClass_, new EqList<StringList>());
                StringList called_ = ip_.getCallingConstr().getCalledConstructors();
                called_.add(superClass_);
                Argument global_ = ip_.getGlobalArgument();
                throw new CustomFoundConstructorException(superClass_, called_, super_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER);
            }
            if (meta_.getCategory() != ClassCategory.INTERFACE) {
                UniqueRootedBlock root_ = (UniqueRootedBlock) _conf.getClasses().getClassBody(curClass_);
                for (String i: root_.getAllNeededSortedInterfaces()) {
                    if (!ip_.getIntializedInterfaces().containsStr(i)) {
                        ip_.getIntializedInterfaces().add(i);
                        FctConstraints super_ = new FctConstraints(superClass_, new EqList<StringList>());
                        StringList called_ = ip_.getCallingConstr().getCalledConstructors();
                        Argument global_ = ip_.getGlobalArgument();
                        throw new CustomFoundConstructorException(i, called_, super_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER);
                    }
                }
            }
            if (!caller_.isFirstField()) {
                RootBlock class_ = _conf.getClasses().getClassBody(curClass_);
                Block first_ = class_.getFirstChild();
                if (first_ == null) {
                    ip_.exitFromConstructor();
                    return;
                }
                caller_.setFirstField(true);
                rw_.setBlock(first_);
                return;
            }
        }
        if (en_ != null) {
            ip_.setProcessingAttribute(EMPTY_STRING);
            ip_.setOffset(0);
        }
        if (en_ instanceof WithEl) {
            ip_.setCurrentBlock(en_);
            ((WithEl)en_).processEl(_conf);
            return;
        }
        if (en_ instanceof Returnable) {
            en_.processBlock(_conf);
            return;
        }
        if (en_ instanceof StaticBlock) {
            if (ip_.isInstancing()) {
                en_.processBlock(_conf);
                return;
            }
            rw_.setBlock(en_.getFirstChild());
            return;
        }
        if (en_ instanceof InstanceBlock) {
            if (!ip_.isInstancing()) {
                en_.processBlock(_conf);
                return;
            }
            rw_.setBlock(en_.getFirstChild());
            return;
        }
        Block root_ = ip_.getBlockRoot();
        if (root_ instanceof RootBlock) {
            if (ip_.isInstancing()) {
                ip_.exitFromConstructor();
                return;
            }
            String curClass_ = ip_.getGlobalClass();
            _conf.getClasses().successInitClass(curClass_);
            ip_.setNullReadWrite();
            return;
        }
        _conf.getLastPage().setReturnedArgument(PrimitiveTypeUtil.defaultValue(root_, _conf.getLastPage().getGlobalArgument()));
        ip_.setNullReadWrite();
    }
    static Class<?> classForName(ContextEl _conf, int _offest, String _className) {
        try {
            if (_className.startsWith(PrimitiveTypeUtil.PRIM)) {
                return ConstClasses.getPrimitiveClass(_className.substring(1));
            }
            return ConstClasses.classForNameNotInit(PrimitiveTypeUtil.getArrayClass(_className));
        } catch (Throwable _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new RuntimeClassNotFoundException(_className+RETURN_LINE+_conf.joinPages());
        }
    }
    static Iterator<?> iterator(ContextEl _conf, Iterable<?> _it) {
        try {
            return _it.iterator();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static boolean hasNext(ContextEl _conf, Iterator<?> _it) {
        try {
            return _it.hasNext();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static Object next(ContextEl _conf, Iterator<?> _it) {
        try {
            return _it.next();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
}
