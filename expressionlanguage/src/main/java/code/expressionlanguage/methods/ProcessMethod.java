package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.CallConstructor;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.StringList;

public final class ProcessMethod {
    private static final String EMPTY_STRING = "";

    private ProcessMethod() {
    }

    public static void initializeClass(String _class, ContextEl _cont) {
        _cont.getClasses().getLocks().initClass(_class);
        _cont.addPage(createInstancingClass(_class, _cont));
        loopCallings(_cont);
    }
    public static Argument instanceArgument(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        CallConstructor call_ = new CallConstructor();
        call_.setArgument(_global);
        call_.setId(_id);
        call_.setInstancingStep(InstancingStep.NEWING);
        call_.setFieldName(EMPTY_STRING);
        PageEl page_ = createInstancing(_class, call_, _args, _cont);
        _cont.addPage(page_);
        loopCallings(_cont);
        return page_.getReturnedArgument();
    }

    public static Argument calculateArgument(Argument _global, String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        Classes classes_ = _cont.getClasses();
        MethodBlock method_ = classes_.getMethodBodiesById(_class, _method).first();
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
                        l_.getLastEl().setArgument(a_, _cont);
                        if (_cont.getException() != null) {
                            _cont.getThrowing().removeBlockFinally(_cont);
                            if (_cont.getException() != null) {
                                return;
                            }
                            continue;
                        }
                    }
                    if (p_.getCallingConstr().getInstancingStep() == InstancingStep.USING_THIS) {
                        l_.getCallingConstr().setInitializedFields(true);
                    }
                    continue;
                }
                processTags(_cont);
                if (_cont.getCallCtor() != null) {
                    addPage(_cont, createInstancing(_cont.getCallCtor(), _cont));
                } else if (_cont.getCallMethod() != null) {
                    addPage(_cont, createCallingMethod(_cont.getCallMethod(), _cont));
                } else if (_cont.getInitClass() != null) {
                    addPage(_cont, createInstancingClass(_cont.getInitClass(), _cont));
                } else if (_cont.getException() != null) {
                    _cont.getThrowing().removeBlockFinally(_cont);
                }
                if (_cont.getException() != null) {
                    return;
                }
            } catch (OutOfMemoryError _0){
                _cont.setException(_cont.getMemoryError());
                _cont.getThrowing().removeBlockFinally(_cont);
                if (_cont.getException() != null) {
                    return;
                }
            }
        }
    }

    private static PageEl createInstancingClass(NotInitializedClass _e, ContextEl _cont) {
        return createInstancingClass(_e.getClassName(), _cont);
    }
    private static PageEl createInstancingClass(String _class, ContextEl _cont) {
        _cont.setInitClass(null);
        Classes classes_ = _cont.getClasses();
        classes_.preInitializeStaticFields(_class, _cont);
        String baseClass_ = StringList.getAllTypes(_class).first();
        RootBlock class_ = classes_.getClassBody(baseClass_);
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
        page_.setBlockRoot(class_);
        return page_;
    }

    private static PageEl createCallingMethod(CustomFoundMethod _e, ContextEl _conf) {
        String cl_ = _e.getClassName();
        MethodId id_ = _e.getId();
        CustList<Argument> args_ = _e.getArguments();
        Argument gl_ = _e.getGl();
        return createCallingMethod(gl_, cl_, id_, args_, _conf);
    }
    private static PageEl createCallingMethod(Argument _gl, String _class, MethodId _method, CustList<Argument> _args, ContextEl _conf) {
        _conf.setCallMethod(null);
        Classes classes_ = _conf.getClasses();
        PageEl pageLoc_ = new PageEl();
        pageLoc_.setGlobalArgument(_gl);
        pageLoc_.setGlobalClass(_class);
        pageLoc_.setReadUrl(_class);
        MethodId id_ = _method;
        MethodBlock methodLoc_ = classes_.getMethodBodiesById(_class, id_).first();
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
    private static PageEl createInstancing(CustomFoundConstructor _e, ContextEl _conf) {
        String cl_ = _e.getClassName();
        CustList<Argument> args_ = _e.getArguments();
        return createInstancing(cl_, _e.getCall(), args_, _conf);
    }
    private static PageEl createInstancing(String _class, CallConstructor _call, CustList<Argument> _args, ContextEl _cont) {
        _cont.setCallCtor(null);
        PageEl page_ = new PageEl();
        Argument global_ = _call.getArgument();
        ConstructorId id_ = _call.getId();
        InstancingStep in_ = _call.getInstancingStep();
        Classes classes_ = _cont.getClasses();
        String baseClass_ = StringList.getAllTypes(_class).first();
        RootBlock class_ = classes_.getClassBody(baseClass_);
        CustList<ConstructorBlock> methods_ = classes_.getConstructorBodiesById(_class, id_);
        ConstructorBlock method_ = null;
        Argument argGl_ = new Argument();
        if (in_ == InstancingStep.NEWING) {
            Struct str_ = null;
            if (global_ != null) {
                str_ = global_.getStruct();
            }
            String fieldName_ = _call.getFieldName();
            int ordinal_ = _call.getChildIndex();
            argGl_.setStruct(_cont.getInit().processInit(_cont, str_, _class, fieldName_, ordinal_));
        } else {
            argGl_.setStruct(global_.getStruct());
        }
        page_.setReadUrl(_class);
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        if (!methods_.isEmpty()) {
            method_ = methods_.first();
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
        page_.setBlockRoot(class_);
        return page_;
    }
    private static void addPage(ContextEl _conf, PageEl _page) {
        _conf.addPage(_page);
        if (_conf.getException() != null) {
            _conf.getThrowing().removeBlockFinally(_conf);
            if (_conf.getException() != null) {
                return;
            }
        }
    }
    private static void processTags(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        Block en_ = rw_.getBlock();
        if (ip_.isInitializingClass()) {
            String curClass_ = ip_.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(curClass_).first();
            RootBlock root_ =  _conf.getClasses().getClassBody(curClassBase_);
            if (root_ instanceof UniqueRootedBlock) {
                String superClass_ = ((UniqueRootedBlock) root_).getSuperClass(_conf);
                ClassMetaInfo s_ = _conf.getClasses().getClassMetaInfo(superClass_, _conf);
                if (s_ != null) {
                    InitClassState res_ = _conf.getClasses().getLocks().getState(_conf, superClass_);
                    if (res_ == InitClassState.NOT_YET) {
                        _conf.setInitClass(new NotInitializedClass(superClass_));
                        return;
                    }
                    if (res_ == InitClassState.ERROR) {
                        CausingErrorStruct causing_ = new CausingErrorStruct(superClass_);
                        _conf.setException(causing_);
                        return;
                    }
                }
            }
            for (String i: root_.getStaticInitInterfaces()) {
                String t_ = StringList.removeAllSpaces(i);
                InitClassState res_ = _conf.getClasses().getLocks().getState(_conf, t_);
                if (res_ == InitClassState.NOT_YET) {
                    _conf.setInitClass(new NotInitializedClass(t_));
                    return;
                }
                if (res_ == InitClassState.ERROR) {
                    CausingErrorStruct causing_ = new CausingErrorStruct(t_);
                    _conf.setException(causing_);
                    return;
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
            String instClass_ = ip_.getGlobalArgument().getObjectClassName(_conf);
            String curClass_ = ip_.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(curClass_).first();
            String formatted_ = Templates.getFullTypeByBases(instClass_, curClassBase_, _conf);
            RootBlock class_ = _conf.getClasses().getClassBody(curClassBase_);
            if (class_ instanceof UniqueRootedBlock) {
                UniqueRootedBlock root_ = (UniqueRootedBlock) class_;
                String superClassBase_ = root_.getSuperClass(_conf);
                String objectClassName_ = _conf.getStandards().getAliasObject();
                if (!calledImpl_ && !StringList.quickEq(superClassBase_, objectClassName_)) {
                    ip_.getCallingConstr().setCalledImplicitConstructor(true);
                    ConstructorId super_ = new ConstructorId(superClassBase_, new StringList(), false);
                    StringList called_ = ip_.getCallingConstr().getCalledConstructors();
                    called_.add(superClassBase_);
                    Argument global_ = ip_.getGlobalArgument();
                    String generic_ = Templates.getFullTypeByBases(formatted_, superClassBase_, _conf);
                    _conf.setCallCtor(new CustomFoundConstructor(generic_, EMPTY_STRING, -1, called_, super_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER));
                    return;
                }
                ConstructorBlock const_ = caller_.getUsedConstructor();
                StringList ints_;
                if (const_ != null) {
                    ints_ = const_.getInterfaces();
                } else {
                    ints_ = class_.getInstInitInterfaces();
                }
                for (String i: ints_) {
                    String t_ = StringList.removeAllSpaces(i);
                    if (!ip_.getIntializedInterfaces().containsStr(t_)) {
                        ip_.getIntializedInterfaces().add(t_);
                        ConstructorId super_ = new ConstructorId(superClassBase_, new StringList(), false);
                        StringList called_ = ip_.getCallingConstr().getCalledConstructors();
                        Argument global_ = ip_.getGlobalArgument();
                        String generic_ = Templates.getFullTypeByBases(formatted_, t_, _conf);
                        _conf.setCallCtor(new CustomFoundConstructor(generic_, EMPTY_STRING, -1, called_, super_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER));
                        return;
                    }
                }
            }
            if (!caller_.isFirstField()) {
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
            ip_.setGlobalOffset(en_.getOffset().getOffsetTrim());
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
            String curClassBase_ = StringList.getAllTypes(curClass_).first();
            _conf.getClasses().getLocks().successClass(_conf, curClassBase_);
            ip_.setNullReadWrite();
            return;
        }
        _conf.getLastPage().setReturnedArgument(PrimitiveTypeUtil.defaultValue(root_, _conf.getLastPage().getGlobalArgument(), _conf));
        ip_.setNullReadWrite();
    }
}
