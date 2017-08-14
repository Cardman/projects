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
//        Classes classes_ = _cont.getClasses();
//        classes_.preInitializeStaticFields(_class, false);
//        ClassBlock class_ = classes_.getClassBody(_class);
//        Block firstChild_ = class_.getFirstChild();
//        if (firstChild_ == null) {
//            return;
//        }
//        PageEl page_ = new PageEl();
//        Argument argGl_ = new Argument();
//        argGl_.setArgClassName(_class);
//        page_.setInitializingClass(true);
//        page_.setGlobalArgument(argGl_);
////        page_.setHtml(classes_.getClassContent(_class));
//        ReadWrite rw_ = new ReadWrite();
//        rw_.setBlock(firstChild_);
//        page_.setReadWrite(rw_);
//        page_.setBlockRoot(class_);
        _cont.getClasses().initialize(_class);
        _cont.addPage(createInstancingClass(_class, _cont));
        loopCallings(_cont);
    }
    public static Argument instanceArgument(String _class, Argument _global, FctConstraints _id, CustList<Argument> _args, ContextEl _cont) {
//        Classes classes_ = _cont.getClasses();
//        ClassBlock class_ = classes_.getClassBody(_class);
        CallConstructor call_ = new CallConstructor();
        call_.setArgument(_global);
        call_.setId(_id);
        call_.setInstancingStep(InstancingStep.NEWING);
        PageEl page_ = createInstancing(_class, call_, _args, _cont);
//        ConstructorBlock method_ = classes_.getConstructorBody(_class, _method);
//        Argument argGl_ = new Argument();
//        argGl_.setStruct(new Struct(new Object(),_class));
//        argGl_.setArgClassName(_class);
//        page_.setGlobalArgument(argGl_);
////        page_.setHtml(classes_.getClassContent(_class));
//        ReadWrite rw_ = new ReadWrite();
//        if (method_ != null) {
//            StringList params_ = method_.getParametersNames();
//            StringList types_ = method_.getParametersTypes();
//            int len_ = params_.size();
//            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//                String p_ = params_.get(i);
//                String c_ = types_.get(i);
//                LocalVariable lv_ = new LocalVariable();
////                lv_.setElement(_args.get(i).getObject());
//                lv_.setStruct(_args.get(i).getStruct());
//                lv_.setClassName(c_);
//                page_.getParameters().put(p_, lv_);
//            }
//            Block firstChild_ = method_.getFirstChild();
//            rw_.setBlock(firstChild_);
//        }
////        int sizeBk_ = _cont.getImporting().size();
//        page_.setReadWrite(rw_);
//        page_.getCallingConstr().setInstancingStep(InstancingStep.NEWING);
//        page_.getCallingConstr().setUsedConstructor(method_);
//        page_.setBlockRoot(class_);
        _cont.addPage(page_);
        loopCallings(_cont);
//        return page_.getCurrentEl().getRoot().getArgument();
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
//        Classes classes_ = _cont.getClasses();
//        CustList<PageEl> ips_ = _cont.getImporting();
//        int sizeBk_ = ips_.size() - 1;
        int sizeBk_ = _cont.nbPages() - 1;
//        Block method_ = _root;
//        PageEl ip_ = ips_.last();
//        _cont.setCallingXml(true);
        while (true) {
            try {
                if (_cont.getLastPage().getReadWrite() == null) {
//                    ips_.removeLast();
                    //%%%%%%
                    PageEl p_ = _cont.getLastPage();
                    //%%%%%%
                    _cont.removeLastPage();
//                    ips_.removeLast();
//                    if (ips_.size() == _sizeBk) {
//                        break;
//                    }
//                    if (ips_.size() == sizeBk_) {
//                        break;
//                    }
                    if (_cont.nbPages() == sizeBk_) {
                        break;
                    }
                    //%%%%%%
                    Argument a_ = p_.getReturnedArgument();
                    if (a_ != null) {
                        PageEl l_ = _cont.getLastPage();
                        if (!l_.getCurrentEls().isEmpty()) {
                            l_.getCurrentEls().last().setArgument(a_, _cont);
                        }
                    }
                    //%%%%%%
//                    ip_ = ips_.last();
                    continue;
                }
//                PageEl ret_ = processTags(_cont, sizeBk_);
                processTags(_cont);
//                if (ret_ != null) {
//                    ip_ = ret_;
//                    continue;
//                }
            } catch (CustomFoundConstructorException _0) {
                addPage(_cont, createInstancing(_0, _cont));
            } catch (NotInitializedClassException _0){
//                String cl_ = _0.getClassName();
//                classes_.preInitializeStaticFields(cl_, false);
//                PageEl pageLoc_ = new PageEl();
//                pageLoc_.setInitializingClass(true);
////                pageLoc_.setHtml(classes_.getClassContent(cl_));
//                Argument argGlLoc_ = new Argument();
//                argGlLoc_.setArgClassName(cl_);
//                pageLoc_.setGlobalArgument(argGlLoc_);
//                ClassBlock class_ = classes_.getClassBody(cl_);
//                ReadWrite rwLoc_ = new ReadWrite();
//                rwLoc_.setBlock(class_.getFirstChild());
//                pageLoc_.setReadWrite(rwLoc_);
//                pageLoc_.setBlockRoot(class_);
//                _cont.addPage(pageLoc_);
                addPage(_cont, createInstancingClass(_0, _cont));
            } catch (CustomFoundMethodException _0){
//                String cl_ = _0.getClassName();
//                PageEl pageLoc_ = new PageEl();
////                pageLoc_.setHtml(classes_.getClassContent(cl_));
//                Argument argGlLoc_ = new Argument();
//                argGlLoc_.setArgClassName(cl_);
//                pageLoc_.setGlobalArgument(argGlLoc_);
//                MethodId id_ = _0.getId();
//                MethodBlock methodLoc_ = classes_.getMethodBody(cl_, id_);
//                StringList paramsLoc_ = methodLoc_.getParametersNames();
//                StringList typesLoc_ = methodLoc_.getParametersTypes();
//                CustList<Argument> args_ = _0.getArguments();
//                int lenLoc_ = paramsLoc_.size();
//                for (int i = CustList.FIRST_INDEX; i < lenLoc_; i++) {
//                    String p_ = paramsLoc_.get(i);
//                    String c_ = typesLoc_.get(i);
//                    LocalVariable lv_ = new LocalVariable();
//                    lv_.setStruct(args_.get(i).getStruct());
//                    lv_.setClassName(c_);
//                    pageLoc_.getParameters().put(p_, lv_);
//                }
//                ReadWrite rwLoc_ = new ReadWrite();
//                rwLoc_.setBlock(methodLoc_.getFirstChild());
//                pageLoc_.setReadWrite(rwLoc_);
//                pageLoc_.setBlockRoot(methodLoc_);
//                _cont.addPage(pageLoc_);
                addPage(_cont, createCallingMethod(_0, _cont));
//                ip_ = pageLoc_;
//                method_ = methodLoc_;
//            } catch (IndirectException _0){
//                //indirect
//                Throwable t_ = throwException(_cont, _0);
//                if (t_ == null) {
//                    continue;
//                }
////                FoundCatch catchElt_ = getCatchElement(_cont, _0, false);
////                if (catchElt_.processCatchingBlock()) {
//////                    ip_ = createException(catchElt_, _cont, _0);
////                    createException(catchElt_, _cont, _0);
////                    continue;
////                }
//                throw _0;
            } catch (RuntimeException _0){
                Throwable t_ = throwException(_cont, _0);
                if (t_ == null) {
                    continue;
                }
//                FoundCatch catchElt_ = getCatchElement(_cont, _0, true);
//                if (catchElt_.processCatchingBlock()) {
////                    ip_ = createException(catchElt_, _cont, _0);
//                    createException(catchElt_, _cont, _0);
//                    continue;
//                }
                throw _0;
            } catch (Error _0){
                Throwable t_ = throwException(_cont, _0);
                if (t_ == null) {
                    continue;
                }
//                FoundCatch catchElt_ = getCatchElement(_cont, _0, true);
//                if (catchElt_.processCatchingBlock()) {
////                    ip_ = createException(catchElt_, _cont, _0);
//                    createException(catchElt_, _cont, _0);
//                    continue;
//                }
                throw _0;
            }
        }
//        _cont.setCallingXml(false);
    }

    private static PageEl createInstancingClass(NotInitializedClassException _e, ContextEl _cont) {
        return createInstancingClass(_e.getClassName(), _cont);
    }
    private static PageEl createInstancingClass(String _class, ContextEl _cont) {
        Classes classes_ = _cont.getClasses();
        classes_.preInitializeStaticFields(_class);
        RootedBlock class_ = classes_.getClassBody(_class);
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
//        pageLoc_.setHtml(classes_.getClassContent(cl_));
//        Argument argGlLoc_ = new Argument();
//        argGlLoc_.setArgClassName(cl_);
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
        RootedBlock class_ = classes_.getClassBody(_class);
        ConstructorBlock method_ = classes_.getConstructorBody(_class, id_);
        Argument argGl_ = new Argument();
        if (in_ == InstancingStep.NEWING) {
            ClassMetaInfo clMeta_ = classes_.getClassMetaInfo(_class);
            StringList allClasses_ = new StringList(_class);
            if (clMeta_.getCategory() == ClassCategory.CLASS) {
                StringList superClasses_ = clMeta_.getSuperClasses(_cont);
                allClasses_.addAllElts(superClasses_);
            }
            ObjectMap<ClassField,Struct> fields_;
            fields_ = new ObjectMap<ClassField,Struct>();
            for (String c: allClasses_) {
                ClassMetaInfo clMetaLoc_ = classes_.getClassMetaInfo(c);
                if (clMetaLoc_ == null) {
                    continue;
                }
                clMeta_ = clMetaLoc_;
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
//            page_.getCallingConstr().getCalledConstructors().add(_class);
        } else {
            PageEl last_ = _cont.getLastPage();
            page_.setEnumName(last_.getEnumName());
//            page_.getCallingConstr().getCalledConstructors().addAllElts(_call.getCalledConstructors());
            argGl_.setStruct(global_.getStruct());
//            System.out.println(page_.getCallingConstr().getCalledConstructors());
        }
        page_.setReadUrl(_class);
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
//        page_.setHtml(classes_.getClassContent(_class));
        ReadWrite rw_ = new ReadWrite();
        if (method_ != null) {
            StringList params_ = method_.getParametersNames();
            StringList types_ = method_.getParametersTypes();
            int len_ = params_.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                String p_ = params_.get(i);
                String c_ = types_.get(i);
                LocalVariable lv_ = new LocalVariable();
//                lv_.setElement(_args.get(i).getObject());
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
//        int sizeBk_ = _cont.getImporting().size();
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
//        FoundCatch f_ = new FoundCatch();
//        CustList<PageEl> ips_ = _conf.getImporting();
//        int indexIp_ = ips_.size() - 1;
//        int indexIp_ = _conf.nbPages() - 1;
        CatchEval catchElt_ = null;
//        boolean entered_ = false;
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
//        boolean exit_ = false;
        while (!_conf.isEmptyPages()) {
            PageEl bkIp_ = _conf.getLastPage();
//            bkIp_.setReturning(null);
//            CustList<BlockStack> l_ = bkIp_.getBlockStacks();
//            int indexTry_ = l_.size() - 1;
            while (!bkIp_.noBlock()) {
                RemovableVars bl_ = bkIp_.getLastStack();
                if (!(bl_ instanceof TryBlockStack)) {
                    ((RemovableVars)bl_).removeVarAndLoop(bkIp_);
//                    if (bl_ instanceof LoopBlockStack) {
//                        LoopBlockStack loopStack_ = (LoopBlockStack) bl_;
//                        BracedBlock forNode_ = loopStack_.getBlock();
////                        removeLocalVars(bkIp_, forNode_);
//                        forNode_.removeLocalVars(bkIp_);
////                        removeVarAndLoop(_conf, forNode_, bkIp_.getVars());
//                        forNode_.removeVarAndLoop(bkIp_);
//                    } else {
//                        if (bl_ instanceof IfBlockStack) {
//                            IfBlockStack t_ = (IfBlockStack) bl_;
//                            BracedBlock cur_ = t_.getCurentVisitedBlock();
////                            removeLocalVars(bkIp_, cur_);
//                            cur_.removeLocalVars(bkIp_);
//                        }
//                        if (bl_ instanceof SwitchBlockStack) {
//                            SwitchBlockStack t_ = (SwitchBlockStack) bl_;
//                            BracedBlock cur_ = t_.getCurentVisitedBlock();
////                            removeLocalVars(bkIp_, cur_);
//                            cur_.removeLocalVars(bkIp_);
//                        }
//                        bkIp_.removeLastBlock();
//                    }
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
//                            f_.setProcessingFinallyBlock(try_.getCatchBlocks().last());
//                            f_.setImportingPage(indexIp_);
//                            if (entered_) {
//                                f_.setTryBlock(indexTry_ - 1);
//                            } else {
//                                f_.setTryBlock(indexTry_);
//                            }
//                            bkIp_.setReturning(null);
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
//                    Class<?> cl_ = ConstClasses.classForNameNotInit(name_);
                    if (!indirect_) {
                        if (PrimitiveTypeUtil.isAssignableFrom(name_, _t.getClass().getName(), _conf.getClasses())) {
                            catchElt_ = ca_;
//                            f_.setCause(false);
//                            f_.setExceptionClass(cl_);
//                            f_.setImportingPage(indexIp_);
//                            f_.setTryBlock(indexTry_);
//                            f_.setCatchBlock(i_);
//                            f_.setCatchBl(ca_);
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    } else {
                        if (PrimitiveTypeUtil.isAssignableFrom(name_, custCause_.getClassName(), _conf.getClasses())) {
                            catchElt_ = ca_;
//                            f_.setCause(true);
//                            f_.setExceptionClass(cl_);
//                            f_.setImportingPage(indexIp_);
//                            f_.setTryBlock(indexTry_);
//                            f_.setCatchBlock(i_);
//                            f_.setCatchBl(ca_);
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    }
                    i_++;
                }
                if (catchElt_ != null) {
                    CatchEval catchElement_ = catchElt_;
//                    bkIp_.setEvaluatingKeepLoop(false);
                    try_.setThrownException(null);
//                    bkIp_.setReturning(null);
                    bkIp_.getCurrentEls().clear();
                    if (catchElement_.getFirstChild() != null) {
                        String var_ = catchElement_.getVariableName();
                        LocalVariable lv_ = new LocalVariable();
                        Throwable t_ = _t;
//                        if (_t instanceof IndirectException) {
//                            custCause_ = ((IndirectException)_t).getCustCause();
//                        } else {
//                            if (_t.getCause() == null) {
//                                custCause_ = new Struct();
//                            } else {
//                                custCause_ = new Struct(_t.getCause());
//                            }
//                        }
//                        if (f_.isCause())
                        if (indirect_) {
//                            t_ = t_.getCause();
                            lv_.setStruct(custCause_);
                        } else {
                            lv_.setStruct(new Struct(t_));
                        }
//                        lv_.setElement(t_);
                        lv_.setClassName(catchElement_.getClassName());
                        bkIp_.getCatchVars().put(var_, lv_);
                        bkIp_.getReadWrite().setBlock(catchElement_.getFirstChild());
                        return null;
                    }
                    bkIp_.getReadWrite().setBlock(catchElement_);
                    return null;
                }
                if (addFinallyClause_) {
//                    f_.setProcessingFinallyBlock(try_.getCatchBlocks().last());
//                    f_.setImportingPage(indexIp_);
//                    if (entered_) {
//                        f_.setTryBlock(indexTry_ - 1);
//                    } else {
//                        f_.setTryBlock(indexTry_);
//                    }
                    try_.setThrownException(_t);
//                    bkIp_.setReturning(null);
                    bkIp_.getCurrentEls().clear();
                    bkIp_.getReadWrite().setBlock(try_.getCatchBlocks().last());
                    return null;
                }
                bkIp_.removeLastBlock();
            }
//            if (catchElt_ != null) {
//                break;
//            }
            _conf.removeLastPage();
//            if (exit_) {
//                break;
//            }
//            entered_ = true;
//            indexIp_--;
        }
        return _t;
//        CatchEval catchElement_ = _catchElt.getCatchBl();
//        int lastIp_ = _catchElt.getImportingPage();
//        int lastTry_ = _catchElt.getTryBlock();
////        CustList<PageEl> ips_ = _conf.getImporting();
////        while (lastIp_ + 1 < ips_.size())
//        while (lastIp_ + 1 < _conf.nbPages()) {
////            ips_.removeLast();
//            _conf.removeLastPage();
////            ips_.remove(lastIp_ + 1);
//        }
//        PageEl ip_ = _conf.getLastPage();
//        ip_.setReturning(false);
//        Block finally_ = _catchElt.getProcessingFinallyBlock();
//        CustList<BlockStack> l_ = ip_.getBlockStacks();
//        while (lastTry_ + 1 < l_.size()) {
//            BlockStack bl_ = l_.last();
//            if (bl_ instanceof LoopBlockStack) {
//                LoopBlockStack loopStack_ = (LoopBlockStack) bl_;
//                Block forNode_ = loopStack_.getBlock();
//                removeLocalVars(ip_, forNode_);
//                removeVarAndLoop(_conf, forNode_, ip_.getVars());
//            } else {
//                if (bl_ instanceof TryBlockStack) {
//                    TryBlockStack t_ = (TryBlockStack) bl_;
//                    if (t_.getVisitedCatch() >= CustList.FIRST_INDEX) {
//                        Block catchBlock_ = t_.getCurrentCatchBlock();
//                        removeLocalVars(ip_, catchBlock_);
//                        if (catchBlock_ instanceof CatchEval) {
//                            String var_ = ((CatchEval)catchBlock_).getVariableName();
//                            ip_.getCatchVars().removeKey(var_);
//                        }
//                    } else {
//                        removeLocalVars(ip_, t_.getBlock());
//                    }
//                    if (finally_ == t_.getCatchBlocks().last()) {
//                        ip_.setThrownException(_t);
//                        ip_.getCurrentEls().clear();
//                        ip_.getReadWrite().setBlock(finally_);
//                        return ip_;
//                    }
//                }
//                if (bl_ instanceof IfBlockStack) {
//                    IfBlockStack t_ = (IfBlockStack) bl_;
//                    Block cur_ = t_.getCurentVisitedBlock();
//                    removeLocalVars(ip_, cur_);
//                }
//                if (bl_ instanceof SwitchBlockStack) {
//                    SwitchBlockStack t_ = (SwitchBlockStack) bl_;
//                    Block cur_ = t_.getCurentVisitedBlock();
//                    removeLocalVars(ip_, cur_);
//                }
//                l_.removeLast();
//            }
//        }
//        ip_.setEvaluatingKeepLoop(false);
//        ip_.setThrownException(null);
//        ip_.getCurrentEls().clear();
//        if (catchElement_.getFirstChild() != null) {
//            String var_ = catchElement_.getVariableName();
//            LocalVariable lv_ = new LocalVariable();
//            Throwable t_ = _t;
//            Struct custCause_;
//            if (_t instanceof IndirectException) {
//                custCause_ = ((IndirectException)_t).getCustCause();
//            } else {
//                if (_t.getCause() == null) {
//                    custCause_ = new Struct();
//                } else {
//                    custCause_ = new Struct(_t.getCause());
//                }
//            }
//            if (_catchElt.isCause()) {
////                t_ = t_.getCause();
//                lv_.setStruct(custCause_);
//            } else {
//                lv_.setStruct(new Struct(t_));
//            }
////            lv_.setElement(t_);
//            lv_.setClassName(catchElement_.getClassName());
//            ip_.getCatchVars().put(var_, lv_);
//            ip_.getReadWrite().setBlock(catchElement_.getFirstChild());
//            return ip_;
//        }
//        ip_.getReadWrite().setBlock(catchElement_);
//        return ip_;
    }
//    private static PageEl createException(FoundCatch _catchElt, ContextEl _conf, Throwable _t) {
//        CatchEval catchElement_ = _catchElt.getCatchBl();
//        int lastIp_ = _catchElt.getImportingPage();
//        int lastTry_ = _catchElt.getTryBlock();
////        CustList<PageEl> ips_ = _conf.getImporting();
////        while (lastIp_ + 1 < ips_.size())
//        while (lastIp_ + 1 < _conf.nbPages()) {
////            ips_.removeLast();
//            _conf.removeLastPage();
////            ips_.remove(lastIp_ + 1);
//        }
//        PageEl ip_ = _conf.getLastPage();
//        ip_.setReturning(false);
//        Block finally_ = _catchElt.getProcessingFinallyBlock();
//        CustList<BlockStack> l_ = ip_.getBlockStacks();
//        while (lastTry_ + 1 < l_.size()) {
//            BlockStack bl_ = l_.last();
//            if (bl_ instanceof LoopBlockStack) {
//                LoopBlockStack loopStack_ = (LoopBlockStack) bl_;
//                Block forNode_ = loopStack_.getBlock();
//                removeLocalVars(ip_, forNode_);
//                removeVarAndLoop(_conf, forNode_, ip_.getVars());
//            } else {
//                if (bl_ instanceof TryBlockStack) {
//                    TryBlockStack t_ = (TryBlockStack) bl_;
//                    if (t_.getVisitedCatch() >= CustList.FIRST_INDEX) {
//                        Block catchBlock_ = t_.getCurrentCatchBlock();
//                        removeLocalVars(ip_, catchBlock_);
//                        if (catchBlock_ instanceof CatchEval) {
//                            String var_ = ((CatchEval)catchBlock_).getVariableName();
//                            ip_.getCatchVars().removeKey(var_);
//                        }
//                    } else {
//                        removeLocalVars(ip_, t_.getBlock());
//                    }
//                    if (finally_ == t_.getCatchBlocks().last()) {
//                        ip_.setThrownException(_t);
//                        ip_.getCurrentEls().clear();
//                        ip_.getReadWrite().setBlock(finally_);
//                        return ip_;
//                    }
//                }
//                if (bl_ instanceof IfBlockStack) {
//                    IfBlockStack t_ = (IfBlockStack) bl_;
//                    Block cur_ = t_.getCurentVisitedBlock();
//                    removeLocalVars(ip_, cur_);
//                }
//                if (bl_ instanceof SwitchBlockStack) {
//                    SwitchBlockStack t_ = (SwitchBlockStack) bl_;
//                    Block cur_ = t_.getCurentVisitedBlock();
//                    removeLocalVars(ip_, cur_);
//                }
//                l_.removeLast();
//            }
//        }
//        ip_.setEvaluatingKeepLoop(false);
//        ip_.setThrownException(null);
//        ip_.getCurrentEls().clear();
//        if (catchElement_.getFirstChild() != null) {
//            String var_ = catchElement_.getVariableName();
//            LocalVariable lv_ = new LocalVariable();
//            Throwable t_ = _t;
//            Struct custCause_;
//            if (_t instanceof IndirectException) {
//                custCause_ = ((IndirectException)_t).getCustCause();
//            } else {
//                if (_t.getCause() == null) {
//                    custCause_ = new Struct();
//                } else {
//                    custCause_ = new Struct(_t.getCause());
//                }
//            }
//            if (_catchElt.isCause()) {
////                t_ = t_.getCause();
//                lv_.setStruct(custCause_);
//            } else {
//                lv_.setStruct(new Struct(t_));
//            }
////            lv_.setElement(t_);
//            lv_.setClassName(catchElement_.getClassName());
//            ip_.getCatchVars().put(var_, lv_);
//            ip_.getReadWrite().setBlock(catchElement_.getFirstChild());
//            return ip_;
//        }
//        ip_.getReadWrite().setBlock(catchElement_);
//        return ip_;
//    }
//
//    private static FoundCatch getCatchElement(ContextEl _conf, Throwable _t, boolean _direct) {
//        FoundCatch f_ = new FoundCatch();
////        CustList<PageEl> ips_ = _conf.getImporting();
////        int indexIp_ = ips_.size() - 1;
//        int indexIp_ = _conf.nbPages() - 1;
//        Block catchElt_ = null;
//        boolean entered_ = false;
//        Struct custCause_;
//        if (_t instanceof IndirectException) {
//            custCause_ = ((IndirectException)_t).getCustCause();
//        } else {
//            if (_t.getCause() == null) {
//                custCause_ = new Struct();
//            } else {
//                custCause_ = new Struct(_t.getCause());
//            }
//        }
//        while (indexIp_ >= CustList.FIRST_INDEX) {
////            PageEl bkIp_ = ips_.get(indexIp_);
//            PageEl bkIp_ = _conf.getPage(indexIp_);
//            CustList<BlockStack> l_ = bkIp_.getBlockStacks();
//            int indexTry_ = l_.size() - 1;
//            while (indexTry_ >= CustList.FIRST_INDEX) {
//                BlockStack bl_ = l_.get(indexTry_);
//                if (!(bl_ instanceof TryBlockStack)) {
//                    indexTry_--;
//                    continue;
//                }
//                TryBlockStack try_ = (TryBlockStack)bl_;
//                boolean addFinallyClause_ = true;
//                if (!(try_.getCatchBlocks().last() instanceof FinallyEval)) {
//                    addFinallyClause_ = false;
//                }
//                if (try_.getVisitedCatch() >= CustList.FIRST_INDEX) {
//                    indexTry_--;
//                    if (!(try_.getCurrentCatchBlock() instanceof FinallyEval)) {
//                        if (addFinallyClause_) {
//                            f_.setProcessingFinallyBlock(try_.getCatchBlocks().last());
//                            f_.setImportingPage(indexIp_);
//                            if (entered_) {
//                                f_.setTryBlock(indexTry_ - 1);
//                            } else {
//                                f_.setTryBlock(indexTry_);
//                            }
//                            return f_;
//                        }
//                    }
//                    continue;
//                }
//                //process try block
//                int i_ = 0;
//                for (Block e: try_.getCatchBlocks()) {
//                    if (e instanceof FinallyEval) {
//                        break;
//                    }
//                    CatchEval ca_ = (CatchEval) e;
//                    String name_ = ca_.getClassName();
////                    Class<?> cl_ = ConstClasses.classForNameNotInit(name_);
//                    if (_direct) {
//                        if (PrimitiveTypeUtil.isAssignableFrom(name_, _t.getClass().getName(), _conf.getClasses())) {
//                            catchElt_ = e;
//                            f_.setCause(false);
////                            f_.setExceptionClass(cl_);
//                            f_.setImportingPage(indexIp_);
//                            f_.setTryBlock(indexTry_);
//                            f_.setCatchBlock(i_);
//                            f_.setCatchBl(ca_);
//                            try_.setVisitedCatch(i_);
//                            break;
//                        }
//                    } else {
//                        if (PrimitiveTypeUtil.isAssignableFrom(name_, custCause_.getClassName(), _conf.getClasses())) {
//                            catchElt_ = e;
//                            f_.setCause(true);
////                            f_.setExceptionClass(cl_);
//                            f_.setImportingPage(indexIp_);
//                            f_.setTryBlock(indexTry_);
//                            f_.setCatchBlock(i_);
//                            f_.setCatchBl(ca_);
//                            try_.setVisitedCatch(i_);
//                            break;
//                        }
//                    }
//                    i_++;
//                }
//                if (catchElt_ != null) {
//                    break;
//                }
//                indexTry_--;
//                if (addFinallyClause_) {
//                    f_.setProcessingFinallyBlock(try_.getCatchBlocks().last());
//                    f_.setImportingPage(indexIp_);
//                    if (entered_) {
//                        f_.setTryBlock(indexTry_ - 1);
//                    } else {
//                        f_.setTryBlock(indexTry_);
//                    }
//                    return f_;
//                }
//            }
//            if (catchElt_ != null) {
//                break;
//            }
//            entered_ = true;
//            indexIp_--;
//        }
//        return f_;
//    }
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
//            String curClass_ = ip_.getGlobalArgument().getArgClassName();
            String curClass_ = ip_.getGlobalClass();
            ClassMetaInfo meta_ = _conf.getClasses().getClassMetaInfo(curClass_);
            String superClass_ = meta_.getSuperClass();
            ClassMetaInfo s_ = _conf.getClasses().getClassMetaInfo(superClass_);
            if (s_ != null && !_conf.getClasses().isInitialized(superClass_)) {
                _conf.getClasses().initialize(superClass_);
                throw new NotInitializedClassException(superClass_);
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
//            Argument global_ = ip_.getGlobalArgument();
//            String curClass_ = global_.getArgClassName();
            String curClass_ = ip_.getGlobalClass();
            ClassMetaInfo meta_ = _conf.getClasses().getClassMetaInfo(curClass_);
            String superClass_ = meta_.getSuperClass();
            if (!calledImpl_ && !StringList.quickEq(superClass_, Object.class.getName()) && !StringList.quickEq(superClass_, Enum.class.getName())) {
                ip_.getCallingConstr().setCalledImplicitConstructor(true);
                FctConstraints super_ = new FctConstraints(superClass_, new EqList<StringList>());
                StringList called_ = ip_.getCallingConstr().getCalledConstructors();
                called_.add(superClass_);
                Argument global_ = ip_.getGlobalArgument();
                throw new CustomFoundConstructorException(superClass_, called_, super_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER_IMPLICIT);
            }
            if (!caller_.isFirstField()) {
                RootedBlock class_ = _conf.getClasses().getClassBody(curClass_);
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
        if (en_ instanceof ConstructorBlock) {
            en_.processBlock(_conf);
            return;
        }
        if (en_ instanceof MethodBlock) {
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
        if (root_ instanceof RootedBlock) {
            if (ip_.isInstancing()) {
                ip_.exitFromConstructor();
                return;
            }
//            String curClass_ = ip_.getGlobalArgument().getArgClassName();
            String curClass_ = ip_.getGlobalClass();
            _conf.getClasses().successInitClass(curClass_);
            ip_.setNullReadWrite();
            return;
        }
        Argument void_ = Argument.createVoid();
        _conf.getLastPage().setReturnedArgument(void_);
        //%%%%%
        ip_.setNullReadWrite();
    }
//    private static void processAfterBlock(ContextEl _conf, int _bkSize, PageEl _ip) {
//        ReadWrite rw_ = _ip.getReadWrite();
//        Block en_ = rw_.getBlock();
//        if (en_.getFirstChild() != null) {
//            en_ = en_.getFirstChild();
//            rw_.setBlock(en_);
//        } else {
//            processBlock(_conf, _bkSize, _ip);
//        }
//    }
//    private static void processBlock(ContextEl _conf, int _bkSize, PageEl _ip) {
//        ReadWrite rw_ = _ip.getReadWrite();
//        Block en_ = rw_.getBlock();
//        ParentStackBlock parElt_ = getParentOfLastNode(_conf, en_);
//        //, false
//        if (parElt_ == null) {
////            Argument void_ = new Argument();
////            CustList<PageEl> list_ = _conf.getImporting();
////            PageEl befLast_ = list_.get(list_.size() - 2);
////            if (list_.size() - 1 > _bkSize) {
////                befLast_ = list_.get(list_.size() - 2);
//////                befLast_.getCurrentEls().last().getCurrentOper().setArgument(void_, _conf);
////                befLast_.getCurrentEls().last().setArgument(void_, _conf);
////            } else {
////                befLast_ = list_.get(list_.size() - 1);
////                befLast_.setReturnedArgument(void_);
////            }
//            PageEl ip_ = _conf.getLastPage();
//            Block root_ = ip_.getBlockRoot();
//            if (root_ instanceof RootedBlock) {
//                _ip.setNullReadWrite();
//                return;
//            }
//            Argument void_ = new Argument();
////            CustList<PageEl> list_ = _conf.getImporting();
////            PageEl befLast_;
//            //%%%%%
////            if (_conf.nbPages() - 1 > _bkSize) {
//////                befLast_ = list_.get(list_.size() - 2);
//////                befLast_.getCurrentEls().last().getCurrentOper().setArgument(void_, _conf);
////                _conf.getBeforeLastPage().getCurrentEls().last().setArgument(void_, _conf);
////            } else {
////                _conf.getLastPage().setReturnedArgument(void_);
////            }
//            _conf.getLastPage().setReturnedArgument(void_);
//            //%%%%%
//            _ip.setNullReadWrite();
//            return;
//        }
//        Block par_ = parElt_.getElement();
//        if (par_ == null) {
//            Block na_ = getNextNodeWrite(_conf, en_);
//            //, false
//            rw_.setBlock(na_);
//            return;
//        }
//        removeLocalVars(_ip, par_);
//        if (isCatchNode(par_)) {
////            TryBlockStack tryStack_ = (TryBlockStack) _ip.getBlockStacks().last();
//            TryBlockStack tryStack_ = (TryBlockStack) _ip.getLastStack();
//            CatchEval catch_ = (CatchEval) tryStack_.getCurrentCatchBlock();
//            String var_ = catch_.getVariableName();
//            Map<String,LocalVariable> vars_ = _ip.getCatchVars();
//            vars_.removeKey(var_);
//            //exit catch block
//            rw_.setBlock(catch_);
//            return;
//        }
//        if (isFinallyNode(par_)) {
//            interruptAfterFinally(_ip);
//            if (_ip.isReturning()) {
////                _ip.getBlockStacks().removeLast();
//                _ip.removeLastBlock();
//                removeBlockFinally(_conf, _ip);
//                FunctionBlock f_ = en_.getFunction();
//                if (f_ instanceof StaticBlock) {
//                    if (_ip.getReadWrite() == null) {
//                        Block bn_ = ((StaticBlock)f_).getNextSibling();
//                        if (bn_ != null) {
//                            _ip.setReturning(false);
//                            rw_.setBlock(bn_);
//                            _ip.setReadWrite(rw_);
//                        }
//                    }
//                }
//                return;
//            }
////            TryBlockStack tryStack_ = (TryBlockStack) _ip.getBlockStacks().last();
//            TryBlockStack tryStack_ = (TryBlockStack) _ip.getLastStack();
//            FinallyEval catch_ = (FinallyEval) tryStack_.getCurrentCatchBlock();
//            tryStack_.setVisitedFinally(true);
//            rw_.setBlock(catch_);
//            return;
//        }
//        if (isLoopNode(par_)) {
//            processLastElementLoop(_conf, _ip);
//        } else {
//            if (isTryNode(par_)) {
//                rw_.setBlock(par_.getNextSibling());
//                return;
//            }
//            if (isInstructionNode(par_)) {
////                IfBlockStack if_ = (IfBlockStack) _ip.getBlockStacks().last();
//                IfBlockStack if_ = (IfBlockStack) _ip.getLastStack();
//                if (if_.lastVisitedBlock() == par_) {
//                    rw_.setBlock(par_);
//                } else {
//                    if_.setVisitedBlock(if_.getVisitedBlock()+1);
//                    rw_.setBlock(par_.getNextSibling());
//                }
//                return;
//            }
//            if (isSwitchNode(par_)) {
////                SwitchBlockStack if_ = (SwitchBlockStack) _ip.getBlockStacks().last();
//                SwitchBlockStack if_ = (SwitchBlockStack) _ip.getLastStack();
//                if (if_.lastVisitedBlock() == par_) {
//                    if_.setFinished(true);
//                    rw_.setBlock(if_.getBlock());
//                } else {
//                    if_.setVisitedBlock(if_.getVisitedBlock()+1);
//                    rw_.setBlock(par_.getNextSibling());
//                }
//                return;
//            }
//        }
//    }
//    static void removeLocalVars(PageEl _ip, Block _block) {
//        for (Block s: TreeRetrieving.<Block>getDirectChildren(_block)) {
//            if (s instanceof InitVariable) {
//                String var_ = ((InitVariable)s).getVariableName();
//                _ip.getLocalVars().removeKey(var_);
//            }
//        }
//    }
//    static void interruptAfterFinally(PageEl _ip) {
//        Throwable t_ = _ip.getThrownException();
//        if (t_ != null) {
////            _ip.getBlockStacks().removeLast();
//            _ip.removeLastBlock();
//            _ip.setThrownException(null);
//            if (t_ instanceof RuntimeException) {
//                throw (RuntimeException)t_;
//            }
//            throw (Error)t_;
//        }
//    }
//    static void removeBlockFinally(ContextEl _conf,
//            PageEl _ip) {
//        ReadWrite rw_ = _ip.getReadWrite();
//        PageEl ip_ = _ip;
////        CustList<BlockStack> l_ = ip_.getBlockStacks();
////        int i_ = l_.size() - 1;
////        while (i_ >= CustList.FIRST_INDEX)
////        while (!l_.isEmpty())
//        while (!ip_.noBlock()) {
////            BlockStack bl_ = l_.last();
//            BlockStack bl_ = ip_.getLastStack();
//            if (bl_ instanceof TryBlockStack) {
//                TryBlockStack t_ = (TryBlockStack) bl_;
//                boolean isFinallyBlock_ = false;
//                if (t_.getVisitedCatch() >= CustList.FIRST_INDEX) {
//                    BracedBlock catchBlock_ = t_.getCurrentCatchBlock();
//                    if (catchBlock_ instanceof CatchEval) {
//                        String var_ = ((CatchEval)catchBlock_).getVariableName();
//                        ip_.getCatchVars().removeKey(var_);
//                    } else {
//                        isFinallyBlock_ = true;
//                    }
////                    removeLocalVars(ip_, catchBlock_);
//                    catchBlock_.removeLocalVars(ip_);
//                } else {
////                    removeLocalVars(ip_, t_.getBlock());
//                    t_.getBlock().removeLocalVars(ip_);
//                }
////                Block cur_ = t_.getBlock();
////                if (t_.getVisitedCatch() >= CustList.FIRST_INDEX) {
////                    cur_ = t_.getCurrentCatchBlock();
////                }
//                if (isFinallyBlock_) {
////                    l_.removeLast();
//                    ip_.removeLastBlock();
//                    continue;
//                }
//                if (t_.getLastCatchBlock() instanceof FinallyEval) {
//                    //                    if (t_.getLastCatchNode().hasChildNodes()) {
//                    //                        rw_.setRead(t_.getLastCatchNode());
//                    //                        rw_.setWrite(t_.getWriteNode());
//                    //                        return ip_;
//                    //                    }
//                    ip_.getCurrentEls().clear();
//                    rw_.setBlock(t_.getLastCatchBlock());
//                    return;
//                }
//                //                else {
//                //                    if (t_.getVisitedCatch() >= CustList.FIRST_INDEX) {
//                //                        Element catchBlock_ = t_.getCurrentCatchNode();
//                //                        String var_ = catchBlock_.getAttribute(ATTRIBUTE_VAR);
//                //                        ip_.getCatchVars().removeKey(var_);
//                //                    }
//                //                }
////                l_.removeLast();
//                ip_.removeLastBlock();
//            } else if (bl_ instanceof LoopBlockStack){
//                LoopBlockStack loopStack_ = (LoopBlockStack) bl_;
//                BracedBlock forNode_ = loopStack_.getBlock();
////                removeLocalVars(ip_, forNode_);
//                forNode_.removeLocalVars(ip_);
////                removeVarAndLoop(_conf, forNode_, ip_.getVars());
//                forNode_.removeVarAndLoop(_conf, ip_.getVars());
//            } else {
//                if (bl_ instanceof IfBlockStack) {
//                    IfBlockStack t_ = (IfBlockStack) bl_;
//                    BracedBlock cur_ = t_.getCurentVisitedBlock();
////                    removeLocalVars(ip_, cur_);
//                    cur_.removeLocalVars(ip_);
//                }
//                if (bl_ instanceof SwitchBlockStack) {
//                    SwitchBlockStack t_ = (SwitchBlockStack) bl_;
//                    BracedBlock cur_ = t_.getCurentVisitedBlock();
////                    removeLocalVars(ip_, cur_);
//                    cur_.removeLocalVars(ip_);
//                }
////                l_.removeLast();
//                ip_.removeLastBlock();
//            }
////            i_--;
//        }
//        ip_.setNullReadWrite();
//    }
//    static void processLastElementLoop(ContextEl _conf, PageEl _ip) {
//        ReadWrite rw_ = _ip.getReadWrite();
//        Map<String,LoopVariable> vars_ = _ip.getVars();
//
////        CustList<BlockStack> stacks_ = _ip.getBlockStacks();
////        LoopBlockStack l_ = (LoopBlockStack) stacks_.last();
//        LoopBlockStack l_ = (LoopBlockStack) _ip.getLastStack();
//        _ip.setEvaluatingKeepLoop(true);
//        Block forLoopLoc_ = l_.getBlock();
//        rw_.setBlock(forLoopLoc_);
//        if (keepLoop(_conf, _ip)) {
//            _ip.setEvaluatingKeepLoop(false);
//            incrementLoop(_conf, l_, vars_);
////            rw_.setBlock(forLoopLoc_);
//            return;
//        }
//        _ip.setEvaluatingKeepLoop(false);
////        Block forLoopLoc_ = l_.getBlock();
////        rw_.setBlock(forLoopLoc_);
//        l_.setFinished(true);
//    }
//    private static void incrementLoop(ContextEl _conf, LoopBlockStack _l, Map<String,LoopVariable> _vars) {
//        Block forLoopLoc_ = _l.getBlock();
//        if (!(forLoopLoc_ instanceof ForLoop)) {
//            return;
//        }
//        _l.setIndex(_l.getIndex() + 1);
//        if (forLoopLoc_ instanceof ForEachLoop) {
////            _conf.getLastPage().setProcessingNode(forLoopLoc_.getAssociateElement());
//            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR);
////            _conf.getLastPage().setLookForAttrValue(false);
//            _conf.getLastPage().setOffset(0);
//            String var_ = ((ForLoop) forLoopLoc_).getVariableName();
//            LoopVariable lv_ = _vars.getVal(var_);
//            Iterator<?> iterator_ = _l.getIterator();
//            if (iterator_ != null) {
//                lv_.setElement(next(_conf, iterator_));
//            } else {
//                lv_.setElement(Array.get(lv_.getArray(), (int) _l.getIndex()));
//            }
//            lv_.setIndex(lv_.getIndex() + 1);
//        } else {
////            _conf.getLastPage().setProcessingNode(forLoopLoc_.getAssociateElement());
//            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR);
////            _conf.getLastPage().setLookForAttrValue(false);
//            _conf.getLastPage().setOffset(0);
//            String var_ = ((ForLoop) forLoopLoc_).getVariableName();
//            LoopVariable lv_ = _vars.getVal(var_);
//            Number element_ = (Number) lv_.getElement();
//            lv_.setElement(element_.longValue()+lv_.getStep());
//            lv_.setIndex(lv_.getIndex() + 1);
//        }
//    }

    //, CustList<LoopBlockStack> _stacks
//    private static void removeVarAndLoop(ContextEl _conf, Block _forLoop, Map<String,LoopVariable> _vars) {
//        PageEl ip_ = _conf.getLastPage();
////        ip_.getBlockStacks().removeLast();
//        ip_.removeLastBlock();
//        if (!(_forLoop instanceof ForLoop)) {
//            return;
//        }
//        String var_ = ((ForLoop)_forLoop).getVariableName();
//        _vars.removeKey(var_);
//    }
    /*private static void processWhile(ContextEl _conf,
            PageEl _ip) {
        ReadWrite rw_ = _ip.getReadWrite();
        //        CustList<LoopBlockStack> stacks_ = _ip.getStacks();
        Condition currentWhileNode_ = (Condition) rw_.getBlock();
        boolean res_ = evaluateCondition(currentWhileNode_, _conf);
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(currentWhileNode_);
        l_.setFinished(!res_);
//        _ip.getBlockStacks().add(l_);
        _ip.addBlock(l_);
    }

    private static void processDoWhile(ContextEl _conf,
            PageEl _ip) {
        ReadWrite rw_ = _ip.getReadWrite();
        //        CustList<LoopBlockStack> stacks_ = _ip.getStacks();
        Block currentWhileNode_ = rw_.getBlock();
        //        Node next_ = currentWhileNode_.getNextSibling();
        //        while (true) {
        //            if (next_ instanceof Text) {
        //                next_ = next_.getNextSibling();
        //                continue;
        //            }
        //            if (next_ instanceof Comment) {
        //                next_ = next_.getNextSibling();
        //                continue;
        //            }
        //            break;
        //        }
        //        boolean res_ = ExtractCondition.evaluateCondition((Element) next_, _conf, _ip);
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(currentWhileNode_);
        //        l_.setFinished(!res_);
        //        if (!res_) {
        //            return false;
        //        }
        //        stacks_.add(l_);
//        _ip.getBlockStacks().add(l_);
        _ip.addBlock(l_);
        //        return true;
    }

    private static void processLoop(ContextEl _conf,
            PageEl _ip) {
        ReadWrite rw_ = _ip.getReadWrite();
        ForLoop currentForNode_ = (ForLoop) rw_.getBlock();
        //        Map<String,LocalVariable> varsLoc_ = _ip.getLocalVars();
        Map<String,LoopVariable> varsLoop_ = _ip.getVars();
        Object iterable_ = null;
        String var_ = currentForNode_.getVariableName();
        //        String base_;
        long nbMaxIterations_ = 0;
        boolean iterationNb_ = false;
        long stepValue_ = 0;
        long fromValue_ = 0;
        Object realFromValue_ = 0;
        if (currentForNode_ instanceof ForEachLoop) {
            ForEachLoop f_ = (ForEachLoop) currentForNode_;
            _ip.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
//            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            ExpressionLanguage el_;
            if (!_ip.getCurrentEls().isEmpty()) {
                el_ = _ip.getCurrentEls().last();
            } else {
                el_ = f_.getEl();
                _ip.setCurrentEls(new CustList<ExpressionLanguage>(el_));
            }
            Object it_ = el_.calculateMember(_conf).getObject();
            _ip.getCurrentEls().clear();
            if (it_ == null) {
                //                _conf.getImporting().last().addToOffset(listAttr_.length()+1);
                throw new NullObjectException(_conf.joinPages());
            }
            //            String listAttr_ = currentForNode_.getAttribute(ATTRIBUTE_LIST);
            //            listAttr_ = formatNumVariables(listAttr_, _conf, _ip.getLocalVars(), varsLoop_, _files);
            //            base_ = listAttr_;
            //            Object it_ = ExtractObject.improvedExtractObject(_conf, listAttr_);
            //            Object it_ = ElUtil.processEl(listAttr_, 0, _conf.toContextEl()).getObject();
            //            if (it_ == null) {
            //                _conf.getImporting().last().addToOffset(listAttr_.length()+1);
            //                throw new NullObjectException(_conf.getImporting().join(RETURN_LINE));
            //            }
            iterable_ = it_;
            //            if (it_.getClass().isArray()) {
            //                iterable_ = it_;
            //            } else {
            //                iterable_ = ExtractObject.castIterable(_conf, listAttr_.length(), it_);
            //            }
        } else {
            ForIterativeLoop f_ = (ForIterativeLoop) currentForNode_;
            iterationNb_ = true;
            boolean eq_ = f_.isEq();
            _ip.setProcessingAttribute(ATTRIBUTE_INIT);
//            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            ExpressionLanguage from_;
            // = f_.getInitEl(_conf);
            if (!_ip.getCurrentEls().isEmpty()) {
                from_ = _ip.getCurrentEls().first();
            } else {
                from_ = f_.getInitEl();
                _ip.setCurrentEls(new CustList<ExpressionLanguage>(from_));
            }
            Argument argFrom_ = from_.calculateMember(_conf);
//            if (!PrimitiveTypeUtil.isIntegerType(argFrom_.getArgClass()) || argFrom_.getObject() == null) {
//                throw new DynamicCastClassException(argFrom_.getArgClass()+RETURN_LINE+_conf.getImporting().join(RETURN_LINE));
//            }
            if (argFrom_.getObject() == null) {
                throw new DynamicCastClassException(argFrom_.getArgClassName()+RETURN_LINE+_conf.joinPages());
            }
            _ip.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
//            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            ExpressionLanguage to_;
            // = f_.getExpressionEl(_conf);
            if (_ip.getCurrentEls().size() > CustList.ONE_ELEMENT) {
                to_ = _ip.getCurrentEls().get(CustList.SECOND_INDEX);
            } else {
                to_ = f_.getExpressionEl();
                _ip.getCurrentEls().add(to_);
            }
            Argument argTo_ = to_.calculateMember(_conf);
//            if (!PrimitiveTypeUtil.isIntegerType(argTo_.getArgClass()) || argTo_.getObject() == null) {
//                throw new DynamicCastClassException(argTo_.getArgClass()+RETURN_LINE+_conf.getImporting().join(RETURN_LINE));
//            }
            if (argTo_.getObject() == null) {
                throw new DynamicCastClassException(argTo_.getArgClassName()+RETURN_LINE+_conf.joinPages());
            }
            _ip.setProcessingAttribute(ATTRIBUTE_STEP);
//            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            ExpressionLanguage step_;
            // = f_.getStepEl(_conf);
//            ExpressionLanguage step_;
            // = f_.getExpressionEl(_conf);
            if (_ip.getCurrentEls().size() > 2) {
                step_ = _ip.getCurrentEls().last();
            } else {
                step_ = f_.getStepEl();
                _ip.getCurrentEls().add(step_);
            }
            Argument argStep_ = step_.calculateMember(_conf);
//            if (!PrimitiveTypeUtil.isIntegerType(argStep_.getArgClass()) || argStep_.getObject() == null) {
//                throw new DynamicCastClassException(argStep_.getArgClass()+RETURN_LINE+_conf.getImporting().join(RETURN_LINE));
//            }
            if (argStep_.getObject() == null) {
                throw new DynamicCastClassException(argStep_.getArgClassName()+RETURN_LINE+_conf.joinPages());
            }
            realFromValue_ = argFrom_.getObject();
            _ip.setCurrentBlock(null);
            _ip.getCurrentEls().clear();
            fromValue_ = (Long)PrimitiveTypeUtil.convert(long.class, realFromValue_);
            long toValue_ = (Long)PrimitiveTypeUtil.convert(long.class, argTo_.getObject());
            stepValue_ = (Long)PrimitiveTypeUtil.convert(long.class, argStep_.getObject());
            if (stepValue_ > 0) {
                if (fromValue_ > toValue_) {
                    stepValue_ = -stepValue_;
                }
            } else if (stepValue_ < 0) {
                if (fromValue_ < toValue_) {
                    stepValue_ = -stepValue_;
                }
            }
            if (stepValue_ > 0) {
                long copyFrom_ = fromValue_;
                while (true) {
                    if (copyFrom_ >= toValue_ && !eq_) {
                        break;
                    }
                    if (copyFrom_ > toValue_ && eq_) {
                        break;
                    }
                    nbMaxIterations_++;
                    copyFrom_ += stepValue_;
                }
            } else if (stepValue_ < 0) {
                long copyFrom_ = fromValue_;
                while (true) {
                    if (copyFrom_ <= toValue_ && !eq_) {
                        break;
                    }
                    if (copyFrom_ < toValue_ && eq_) {
                        break;
                    }
                    nbMaxIterations_++;
                    copyFrom_ += stepValue_;
                }
            }
        }
        Iterator<?> it_ = null;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        if (iterationNb_) {
            length_ = nbMaxIterations_;
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
                //                res_.setFinished(true);
                //                res_.setNextNode(getNextNodeWrite(_currentForNode, false));
                //                return res_;
            }
        } else if (iterable_.getClass().isArray()) {
            length_ = Array.getLength(iterable_);
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
                //                res_.setNextNode(getNextNodeWrite(_currentForNode, false));
                //                return res_;
            }
        } else {
            it_ = iterator(_conf, (Iterable<?>) iterable_);
            //            it_ = ((Iterable<?>) iterable_).iterator();
            if (!hasNext(_conf, it_)) {
                finished_ = true;
                //                res_.setNextNode(getNextNodeWrite(_currentForNode, false));
                //                return res_;
            }
        }
        if (((Block) currentForNode_).getFirstChild() == null) {
            finished_ = true;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setFinished(finished_);
        l_.setBlock((Block) currentForNode_);
        l_.setIterator(it_, length_);
        //        stacks_.add(l_);
//        _ip.getBlockStacks().add(l_);
        _ip.addBlock(l_);
        if (finished_) {
            return;
        }
        Object int_;
        if (iterationNb_) {
            int_ = realFromValue_;
        } else if (iterable_.getClass().isArray()) {
            int_ = Array.get(iterable_, CustList.FIRST_INDEX);
        } else {
            int_ = next(_conf, it_);
        }
        String indexClassName_;
        indexClassName_ = currentForNode_.getClassIndexName();
        //        ExtractObject.checkClassNotEmptyName(_conf, 0, indexClassName_);
        //        try {
        //            if (!indexClassName_.isEmpty()) {
        //                ConstClasses.classForName(indexClassName_);
        //            }
        //        } catch (RuntimeClassNotFoundException _0) {
        //            throw new RuntimeClassNotFoundException(indexClassName_+RETURN_LINE+_conf.getImporting().join(RETURN_LINE));
        //        }
        String className_;
        if (iterationNb_) {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getClassName();
            if (className_.isEmpty()) {
                className_ = long.class.getName();
            }
            Class<?> cl_ = classForName(_conf, 0, className_);
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
            lv_.setElement(PrimitiveTypeUtil.convert(cl_, int_));
            lv_.setStep(stepValue_);
            lv_.setExtendedExpression(EMPTY_STRING);
            varsLoop_.put(var_, lv_);
        } else {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getClassName();
            //            ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
            //            try {
                //                if (!className_.isEmpty()) {
            //                    ConstClasses.classForName(className_);
            //                }
            //            } catch (RuntimeClassNotFoundException _0) {
            //                throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.getImporting().join(RETURN_LINE));
            //            }
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
            lv_.setElement(int_);
            //lv_.setBaseExpression(calcultateBase(base_, varsLoc_, varsLoop_));
            //            lv_.setList(iterable_);
            if (iterable_.getClass().isArray()) {
                lv_.setArray(iterable_);
            } else {
                lv_.setList((Iterable<?>)iterable_);
            }
            lv_.setExtendedExpression(EMPTY_STRING);
            varsLoop_.put(var_, lv_);
        }
        //        res_.setNextNode(getNextNode(_currentForNode, true));
        //        return res_;
    }*/
//    private static boolean keepLoop(ContextEl _conf, PageEl _ip) {
////        CustList<BlockStack> stacks_ = _ip.getBlockStacks();
//        //        ReadWrite ret_;
////        LoopBlockStack l_ = (LoopBlockStack) stacks_.last();
//        LoopBlockStack l_ = (LoopBlockStack) _ip.getLastStack();
//        //        int index_ = stacks_.size() - 1;
//        //        while (index_ >= CustList.FIRST_INDEX) {
//        //            if (stacks_.get(index_) instanceof LoopBlockStack) {
//        //                l_ = (LoopBlockStack) stacks_.get(index_);
//        //                break;
//        //            }
//        //            index_--;
//        //        }
////        _conf.getLastPage().setProcessingNode(l_.getAssociatedElement());
//        _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
//        _conf.getLastPage().setOffset(0);
////        _conf.getLastPage().setLookForAttrValue(false);
//        Block bl_ = l_.getBlock();
//        if (bl_ instanceof DoBlock) {
//            Condition c_ = ((DoBlock)bl_).getNext();
////            Block n_ = l_.getBlock().getNextSibling();
////            return evaluateCondition((Condition)n_, _conf);
//            return c_.evaluateCondition(_conf);
//        }
//        if (bl_ instanceof  ForLoop) {
//            //            return hasNext(_conf, l_.getIterator());
//            return l_.hasNext(_conf);
//        }
//        return ((Condition)bl_).evaluateCondition(_conf);
////        return evaluateCondition((Condition)bl_, _conf);
//    }
//    static boolean evaluateCondition(Condition _condition, ContextEl _context) {
//        ExpressionLanguage exp_;
//        PageEl last_ = _context.getLastPage();
//        if (!last_.getCurrentEls().isEmpty()) {
//            exp_ = last_.getCurrentEls().last();
//        } else {
//            exp_ = _condition.getElCondition();
//            last_.setCurrentBlock(_condition);
//            last_.setCurrentEls(new CustList<ExpressionLanguage>(exp_));
//        }
//        last_.setOffset(0);
////        last_.setLookForAttrValue(true);
//        last_.setProcessingAttribute(ATTRIBUTE_CONDITION);
//        Argument arg_ = exp_.calculateMember(_context);
//        exp_.setCurrentOper(null);
//        last_.getCurrentEls().clear();
//        return (Boolean) arg_.getObject();
//    }
    static Class<?> classForName(ContextEl _conf, int _offest, String _className) {
        try {
            if (_className.startsWith(PrimitiveTypeUtil.PRIM)) {
                return ConstClasses.getPrimitiveClass(_className.substring(1));
            }
            return ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(_className));
        } catch (RuntimeException _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new RuntimeClassNotFoundException(_className+RETURN_LINE+_conf.joinPages());
        } catch (VirtualMachineError _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new ErrorCausingException(_className+RETURN_LINE+_conf.joinPages(), new Struct(_0));
        } catch (ExceptionInInitializerError _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new ErrorCausingException(_className+RETURN_LINE+_conf.joinPages(), new Struct(_0));
        }
    }
    static Iterator<?> iterator(ContextEl _conf, Iterable<?> _it) {
        try {
            return _it.iterator();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static boolean hasNext(ContextEl _conf, Iterator<?> _it) {
        try {
            return _it.hasNext();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static Object next(ContextEl _conf, Iterator<?> _it) {
        try {
            return _it.next();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
}
