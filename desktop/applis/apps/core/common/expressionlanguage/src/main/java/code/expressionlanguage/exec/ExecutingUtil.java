package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.FunctionBlock;
import code.expressionlanguage.analyze.blocks.MethodKind;
import code.expressionlanguage.analyze.blocks.ReturnableWithSignature;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;

import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.util.*;

public final class ExecutingUtil {
    private ExecutingUtil() {
    }

    static void processException(ContextEl _context) {
        if (!_context.getInitializingTypeInfos().isFailInit()) {
            processGeneException(_context);
        }
    }

    public static void processGeneException(ContextEl _context) {
        CallingState callingState_ = _context.getCallingState();
        if (callingState_ instanceof Struct) {
            _context.getThrowing().removeBlockFinally(_context, (Struct) callingState_);
        }
    }

    static void processTagsBase(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        if (!ip_.checkCondition(_context)) {
            return;
        }
        ReadWrite rw_ = ip_.getReadWrite();
        ExecBlock en_ = rw_.getBlock();
        if (en_ != null) {
            ip_.setGlobalOffset(en_.getOffset().getOffsetTrim());
            ip_.setOffset(0);
        }
        ip_.tryProcessEl(_context);
    }

    static AbstractPageEl processAfterOperation(ContextEl _context) {
        if (_context.getCallingState() instanceof CustomFoundConstructor) {
            return createInstancing(_context,(CustomFoundConstructor)_context.getCallingState());
        }
        if (_context.getCallingState() instanceof CustomFoundAnnotation) {
            CustomFoundAnnotation c_ = (CustomFoundAnnotation) _context.getCallingState();
            return createAnnotation(_context,c_.getClassName(),c_.getType(), c_.getId(), c_.getArguments());
        }
        if (_context.getCallingState() instanceof CustomFoundMethod) {
            return createCallingMethod(_context,(CustomFoundMethod)_context.getCallingState());
        }
        if (_context.getCallingState() instanceof CustomFoundCast) {
            return createCallingCast(_context,(CustomFoundCast)_context.getCallingState());
        }
        if (_context.getCallingState() instanceof CustomReflectMethod) {
            return createReflectMethod(_context,(CustomReflectMethod)_context.getCallingState());
        }
        if (_context.getCallingState() instanceof NotInitializedClass) {
            return createInstancingClass(_context,(NotInitializedClass)_context.getCallingState());
        }
        if (_context.getCallingState() instanceof NotInitializedFields) {
            NotInitializedFields i_ = (NotInitializedFields) _context.getCallingState();
            return createInitFields(_context,i_.getClassName(), i_.getCurrentObject());
        }
        if (_context.getCallingState() instanceof CustomFoundBlock) {
            CustomFoundBlock b_ = (CustomFoundBlock) _context.getCallingState();
            return createBlockPageEl(_context,b_.getClassName(), b_.getCurrentObject(), b_.getBlock());
        }
        return null;
    }


    static EndCallValue removeCallBase(ContextEl _context) {
        AbstractPageEl p_ = _context.getLastPage();
        if (p_.getReadWrite() == null) {
            if (p_ instanceof StaticInitPageEl) {
                ((StaticInitPageEl)p_).sucessClass(_context);
            }
            _context.removeLastPage();
            if (_context.nbPages() == 0) {
                return EndCallValue.EXIT;
            }
            if (p_ instanceof ForwardPageEl) {
                ((ForwardPageEl)p_).forwardTo(_context.getLastPage(), _context);
            } else if (p_ instanceof StaticInitPageEl) {
                StaticInitPageEl s_ = (StaticInitPageEl) p_;
                Argument fwd_ = s_.getFwd();
                if (fwd_ != null) {
                    _context.getLastPage().receive(fwd_, _context);
                }
            }
            if (_context.callsOrException()) {
                return EndCallValue.NEXT;
            }
            return EndCallValue.FORWARD;
        }
        return EndCallValue.NEXT;
    }

    private static AbstractPageEl createInstancingClass(ContextEl _context,NotInitializedClass _e) {
        return createInstancingClass(_context,_e.getClassName(),_e.getArgument());
    }
    public static AbstractPageEl createInstancingClass(ContextEl _context,String _class,Argument _fwd) {
        _context.setCallingState(null);
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_class);
        ExecRootBlock class_ = _context.getClasses().getClassBody(baseClass_);
        ExecBlock firstChild_ = class_.getFirstChild();
        StaticInitPageEl page_ = new StaticInitPageEl();
        Argument argGl_ = new Argument();
        page_.setGlobalClass(_class);
        page_.setFwd(_fwd);
        page_.setGlobalArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        rw_.setBlock(firstChild_);
        page_.setReadWrite(rw_);
        page_.setBlockRoot(class_);
        while (firstChild_ != null) {
            if (firstChild_ instanceof ExecStaticBlock) {
                page_.getProcessedBlocks().put((ExecInitBlock) firstChild_, false);
            }
            firstChild_ = firstChild_.getNextSibling();
        }
        page_.setFile(class_.getFile());
        return page_;
    }

    private static AbstractPageEl createCallingMethod(ContextEl _context,CustomFoundMethod _e) {
        String cl_ = _e.getClassName();
        MethodId id_ = _e.getId();
        CustList<Argument> args_ = _e.getArguments();
        Argument gl_ = _e.getGl();
        Argument right_ = _e.getRight();
        return createCallingMethod(_context,gl_, cl_, id_, args_, right_);
    }
    public static MethodPageEl createCallingMethod(ContextEl _context, Argument _gl, String _class, MethodId _method, CustList<Argument> _args, Argument _right) {
        _context.setCallingState(null);
        ExecNamedFunctionBlock methodLoc_;
        CustList<ExecOverridableBlock> methods_ = ExecBlock.getDeepMethodBodiesById(_context, _class, _method);
        if (!methods_.isEmpty()) {
            methodLoc_ = methods_.first();
            String idCl_ = StringExpUtil.getIdFromAllTypes(_class);
            _context.getCoverage().passCalls(_context,idCl_,methodLoc_);
        } else {
            CustList<ExecNamedFunctionBlock> opers_ = ExecBlock.getOperatorsBodiesById(_context, _method);
            methodLoc_ = opers_.first();
            _context.getCoverage().passCalls(_context,"",methodLoc_);
        }
        String ret_ = methodLoc_.getImportedReturnType();
        MethodPageEl pageLoc_ = new MethodPageEl(_context,ret_,_gl,_class,_right);
        setMethodInfos(_context,pageLoc_,methodLoc_, _args);
        return pageLoc_;
    }

    private static AbstractPageEl createCallingCast(ContextEl _context,CustomFoundCast _e) {
        String cl_ = _e.getClassName();
        MethodId id_ = _e.getId();
        CustList<Argument> args_ = _e.getArguments();
        return createCallingCast(_context,cl_, id_, args_);
    }
    public static CastPageEl createCallingCast(ContextEl _context, String _class, MethodId _method, CustList<Argument> _args) {
        _context.setCallingState(null);
        ExecNamedFunctionBlock methodLoc_;
        methodLoc_ = ExecBlock.getDeepMethodBodiesById(_context, _class, _method).first();
        String idCl_ = StringExpUtil.getIdFromAllTypes(_class);
        _context.getCoverage().passCalls(_context,idCl_,methodLoc_);
        String ret_ = methodLoc_.getImportedReturnType();
        CastPageEl pageLoc_ = new CastPageEl(_context,ret_,Argument.createVoid(),_class);
        setMethodInfos(_context,pageLoc_,methodLoc_, _args);
        return pageLoc_;
    }
    private static void setMethodInfos(ContextEl _context,AbstractMethodPageEl _page, ExecNamedFunctionBlock _block, CustList<Argument> _args) {
        StringList paramsLoc_ = _block.getParametersNames();
        int lenLoc_ = paramsLoc_.size();
        for (int i = CustList.FIRST_INDEX; i < lenLoc_; i++) {
            String p_ = paramsLoc_.get(i);
            LocalVariable lv_ = LocalVariable.newLocalVariable(_args.get(i).getStruct(),_context);
            _page.getValueVars().put(p_, lv_);
        }
        ReadWrite rwLoc_ = new ReadWrite();
        rwLoc_.setBlock(_block.getFirstChild());
        _page.setReadWrite(rwLoc_);
        _page.setBlockRoot(_block);
        _page.setFile(_block.getFile());
    }
    private static AbstractPageEl createInstancing(ContextEl _context,CustomFoundConstructor _e) {
        String cl_ = _e.getClassName();
        CustList<Argument> args_ = _e.getArguments();
        InstancingStep in_ = _e.getInstanceStep();
        if (in_ == InstancingStep.NEWING) {
            return createInstancing(_context,cl_, _e.getType(),_e.getCall(), args_);
        }
        return createForwardingInstancing(_context,cl_, _e.getType(),_e.getCall(), args_);
    }
    public static CallConstructorPageEl createInstancing(ContextEl _context,String _class, ExecRootBlock _type,CallConstructor _call, CustList<Argument> _args) {
        _context.setCallingState(null);
        CallConstructorPageEl page_;
        Argument global_ = _call.getArgument();
        Argument argGl_ = new Argument();
        page_ = new CallConstructorPageEl();
        Struct str_ = NullStruct.NULL_VALUE;
        if (global_ != null) {
            str_ = global_.getStruct();
        }
        String fieldName_ = _call.getFieldName();
        int ordinal_ = _call.getChildIndex();
        argGl_.setStruct(_context.getInit().processInit(_context, str_, _class, fieldName_, ordinal_));
        page_.setGlobalArgument(argGl_);
        setInstanciationInfos(_context,page_,_class,_type,_call,_args);
        return page_;
    }
    private static NewAnnotationPageEl createAnnotation(ContextEl _context,String _class,ExecRootBlock _type,
                                                        StringMap<AnnotationTypeInfo> _id,
                                                        CustList<Argument> _args) {
        _context.setCallingState(null);
        NewAnnotationPageEl page_;
        ExecFileBlock file_ = _type.getFile();
        Argument argGl_ = new Argument();
        page_ = new NewAnnotationPageEl();
        page_.setArgs(_args);
        page_.setNames(_id);
        argGl_.setStruct(_context.getInit().processInitAnnot(_context, _class));
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        page_.setReadWrite(rw_);
        page_.setFile(file_);
        return page_;
    }
    private static AbstractPageEl createForwardingInstancing(ContextEl _context,String _class, ExecRootBlock _type,CallConstructor _call, CustList<Argument> _args) {
        _context.setCallingState(null);
        AbstractPageEl page_ = new CallConstructorPageEl();
        Argument global_ = _call.getArgument();
        Argument argGl_ = new Argument();
        argGl_.setStruct(global_.getStruct());
        page_.setGlobalArgument(argGl_);
        setInstanciationInfos(_context,page_,_class,_type,_call,_args);
        return page_;
    }
    private static void setInstanciationInfos(ContextEl _context,AbstractPageEl _page,String _class, ExecRootBlock _type,CallConstructor _call, CustList<Argument> _args) {
        ConstructorId id_ = _call.getId();
        ExecFileBlock file_ = _type.getFile();
        CustList<ExecConstructorBlock> methods_ = ExecBlock.getConstructorBodiesById(_context, _class, id_);
        ExecConstructorBlock method_ = null;
        _page.setGlobalClass(_class);
        ReadWrite rw_ = new ReadWrite();
        if (!methods_.isEmpty()) {
            String idCl_ = StringExpUtil.getIdFromAllTypes(_class);
            method_ = methods_.first();
            _context.getCoverage().passCalls(_context,idCl_,method_);
            StringList params_ = method_.getParametersNames();
            int len_ = params_.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                String p_ = params_.get(i);
                LocalVariable lv_ = LocalVariable.newLocalVariable(_args.get(i).getStruct(),_context);
                _page.getValueVars().put(p_, lv_);
            }
            ExecBlock firstChild_ = method_.getFirstChild();
            rw_.setBlock(firstChild_);
        }
        _page.setReadWrite(rw_);
        _page.setBlockRoot(method_);
        _page.setFile(file_);
    }
    private static FieldInitPageEl createInitFields(ContextEl _context,String _class, Argument _current) {
        _context.setCallingState(null);
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_class);
        ExecRootBlock class_ = _context.getClasses().getClassBody(baseClass_);
        FieldInitPageEl page_ = new FieldInitPageEl();
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(_current);
        ReadWrite rw_ = new ReadWrite();
        ExecBlock firstChild_ = class_.getFirstChild();
        rw_.setBlock(firstChild_);
        while (firstChild_ != null) {
            if (firstChild_ instanceof ExecInstanceBlock) {
                page_.getProcessedBlocks().put((ExecInitBlock) firstChild_, false);
            }
            firstChild_ = firstChild_.getNextSibling();
        }
        page_.setReadWrite(rw_);
        page_.setBlockRoot(class_);
        page_.setFile(class_.getFile());
        return page_;
    }
    private static BlockPageEl createBlockPageEl(ContextEl _context,String _class, Argument _current, ExecInitBlock _block) {
        _context.setCallingState(null);
        ExecFileBlock file_ = _block.getFile();
        BlockPageEl page_ = new BlockPageEl();
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(_current);
        ReadWrite rw_ = new ReadWrite();
        ExecBlock firstChild_ = _block.getFirstChild();
        rw_.setBlock(firstChild_);
        page_.setReadWrite(rw_);
        page_.setBlockRoot(_block);
        page_.setFile(file_);
        return page_;
    }
    private static AbstractReflectPageEl createReflectMethod(ContextEl _context,CustomReflectMethod _e) {
        ReflectingType r_ = _e.getReflect();
        CustList<Argument> args_ = _e.getArguments();
        Argument gl_ = _e.getGl();
        boolean l_ = _e.isLambda();
        return createReflectMethod(_context,gl_, args_, r_, l_);
    }
    public static AbstractReflectPageEl createReflectMethod(ContextEl _context,Argument _gl, CustList<Argument> _args, ReflectingType _reflect, boolean _lambda) {
        _context.setCallingState(null);
        AbstractReflectPageEl pageLoc_;
        if (_reflect == ReflectingType.METHOD) {
            pageLoc_ = new PolymorphRefectMethodPageEl();
        } else if (_reflect == ReflectingType.DIRECT) {
            pageLoc_ = new DirectRefectMethodPageEl();
        } else if (_reflect == ReflectingType.STATIC_CALL) {
            pageLoc_ = new StaticCallMethodPageEl();
        } else if (_reflect == ReflectingType.CAST) {
            pageLoc_ = new CastRefectMethodPageEl(false);
        } else if (_reflect == ReflectingType.CAST_DIRECT) {
            pageLoc_ = new CastRefectMethodPageEl(true);
        } else if (_reflect == ReflectingType.CONSTRUCTOR) {
            pageLoc_ = new ReflectConstructorPageEl();
        } else if (_reflect == ReflectingType.GET_FIELD) {
            pageLoc_ = new ReflectGetFieldPageEl();
        } else if (_reflect == ReflectingType.SET_FIELD) {
            pageLoc_ = new ReflectSetFieldPageEl();
        } else if (_reflect == ReflectingType.DEFAULT_VALUE) {
            pageLoc_ = new ReflectGetDefaultValuePageEl();
        } else {
            pageLoc_ = new ReflectAnnotationPageEl();
            ((ReflectAnnotationPageEl)pageLoc_).setOnParameters(_reflect == ReflectingType.ANNOTATION_PARAM);
        }
        pageLoc_.setLambda(_lambda);
        pageLoc_.setGlobalArgument(_gl);
        pageLoc_.setArguments(_args);
        ReadWrite rwLoc_ = new ReadWrite();
        pageLoc_.setReadWrite(rwLoc_);
        return pageLoc_;
    }

    private static ClassMetaInfo getCustomClassMetaInfo(String _name, ContextEl _context) {
        String base_ = StringExpUtil.getIdFromAllTypes(_name);
        for (ExecRootBlock c: _context.getClasses().getClassBodies()) {
            String k_ = c.getFullName();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            return getCustomClassMetaInfo(c, _name, _context);
        }
        return new ClassMetaInfo(_context.getStandards().getAliasVoid(),_context, ClassCategory.VOID,"");
    }
    public static ClassMetaInfo getCustomClassMetaInfo(ExecRootBlock _type,String _name, ContextEl _context) {
        ObjectMap<MethodId, MethodMetaInfo> infos_;
        infos_ = new ObjectMap<MethodId, MethodMetaInfo>();
        ObjectMap<MethodId, MethodMetaInfo> infosExplicits_;
        ObjectMap<MethodId, MethodMetaInfo> infosImplicits_;
        ObjectMap<MethodId, MethodMetaInfo> infosTrues_;
        ObjectMap<MethodId, MethodMetaInfo> infosFalses_;
        infosExplicits_ = new ObjectMap<MethodId, MethodMetaInfo>();
        infosImplicits_ = new ObjectMap<MethodId, MethodMetaInfo>();
        infosTrues_ = new ObjectMap<MethodId, MethodMetaInfo>();
        infosFalses_ = new ObjectMap<MethodId, MethodMetaInfo>();
        StringMap<FieldMetaInfo> infosFields_;
        infosFields_ = new StringMap<FieldMetaInfo>();
        ObjectMap<ConstructorId, ConstructorMetaInfo> infosConst_;
        infosConst_ = new ObjectMap<ConstructorId, ConstructorMetaInfo>();
        CustList<ExecBlock> bl_ = ExecBlock.getDirectChildren(_type);
        String fileName_ = _type.getFile().getFileName();
        StringList inners_ = new StringList();
        boolean existCtor_ = false;
        for (ExecRootBlock b: _type.getChildrenTypes()) {
            inners_.add(b.getFullName());
        }
        for (ExecBlock b: bl_) {
            if (b instanceof ExecInfoBlock) {
                ExecInfoBlock method_ = (ExecInfoBlock) b;
                String ret_ = method_.getImportedClassName();
                boolean staticElement_ = method_.isStaticField();
                boolean finalElement_ = method_.isFinalField();

                for (String f: method_.getFieldName()) {
                    FieldMetaInfo met_ = new FieldMetaInfo(_name, f, ret_, staticElement_, finalElement_, method_.getAccess());
                    met_.setFileName(fileName_);
                    met_.setAnnotableBlock(method_);
                    infosFields_.put(f, met_);
                }
            }
            if (b instanceof ExecOverridableBlock) {
                ExecOverridableBlock method_ = (ExecOverridableBlock) b;
                MethodId id_ = method_.getId();
                String ret_ = method_.getImportedReturnType();
                boolean param_ = id_.getKind() == MethodAccessKind.STATIC_CALL || method_.getKind() == MethodKind.EXPLICIT_CAST || method_.getKind() == MethodKind.IMPLICIT_CAST
                        || method_.getKind() == MethodKind.TRUE_OPERATOR  || method_.getKind() == MethodKind.FALSE_OPERATOR;
                MethodId fid_ = tryFormatId(_name, _context, id_);
                String idType_ = _type.getFullName();
                String formCl_ = tryFormatType(idType_, _name, _context);
                String idCl_ = _type.getFullName();
                if (param_) {
                    idCl_ = _name;
                }
                MethodMetaInfo met_ = new MethodMetaInfo(method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                met_.setAnnotableBlock(method_);
                met_.setFileName(fileName_);
                met_.setExpCast(method_.getKind() == MethodKind.EXPLICIT_CAST || method_.getKind() == MethodKind.IMPLICIT_CAST
                        || method_.getKind() == MethodKind.TRUE_OPERATOR  || method_.getKind() == MethodKind.FALSE_OPERATOR);
                infos_.put(id_, met_);
                if (method_.getKind() == MethodKind.EXPLICIT_CAST) {
                    met_ = new MethodMetaInfo(method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                    met_.setAnnotableBlock(method_);
                    met_.setFileName(fileName_);
                    met_.setExpCast(true);
                    infosExplicits_.put(id_, met_);
                }
                if (method_.getKind() == MethodKind.IMPLICIT_CAST) {
                    met_ = new MethodMetaInfo(method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                    met_.setAnnotableBlock(method_);
                    met_.setFileName(fileName_);
                    met_.setExpCast(true);
                    infosImplicits_.put(id_, met_);
                }
                if (method_.getKind() == MethodKind.TRUE_OPERATOR) {
                    met_ = new MethodMetaInfo(method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                    met_.setAnnotableBlock(method_);
                    met_.setFileName(fileName_);
                    met_.setExpCast(true);
                    infosTrues_.put(id_, met_);
                }
                if (method_.getKind() == MethodKind.FALSE_OPERATOR) {
                    met_ = new MethodMetaInfo(method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                    met_.setAnnotableBlock(method_);
                    met_.setFileName(fileName_);
                    met_.setExpCast(true);
                    infosFalses_.put(id_, met_);
                }
            }
            if (b instanceof ExecAnnotationMethodBlock) {
                ExecAnnotationMethodBlock method_ = (ExecAnnotationMethodBlock) b;
                MethodId id_ = method_.getId();
                String ret_ = method_.getImportedReturnType();
                MethodId fid_;
                String formCl_ = _type.getFullName();
                fid_ = id_;
                MethodMetaInfo met_ = new MethodMetaInfo(AccessEnum.PUBLIC,_type.getFullName(), id_, method_.getModifier(), ret_, fid_, formCl_);
                met_.setAnnotableBlock(method_);
                met_.setFileName(fileName_);
                infos_.put(id_, met_);
            }
            if (b instanceof ExecConstructorBlock) {
                existCtor_ = true;
                ExecConstructorBlock method_ = (ExecConstructorBlock) b;
                ConstructorId id_ = method_.getGenericId();
                String ret_ = method_.getImportedReturnType();
                String formCl_ = method_.getDeclaringType();
                ConstructorId fid_ = tryFormatId(_name, _context, id_);
                ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, method_.getAccess(), id_, ret_, fid_, formCl_);
                met_.setAnnotableBlock(method_);
                met_.setFileName(fileName_);
                infosConst_.put(id_, met_);
            }
        }
        if (!existCtor_) {
            ConstructorId id_ = new ConstructorId(_name, new StringList(), false);
            AccessEnum acc_ = _type.getAccess();
            ConstructorId fid_;
            String ret_ = _context.getStandards().getAliasVoid();
            fid_ = id_;
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, acc_, id_, ret_, fid_, _name);
            met_.setFileName(fileName_);
            infosConst_.put(id_, met_);
        }
        if (_type instanceof ExecEnumBlock) {
            String valueOf_ = _context.getStandards().getAliasEnumPredValueOf();
            String values_ = _context.getStandards().getAliasEnumValues();
            String string_ = _context.getStandards().getAliasString();
            MethodId id_ = new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_));
            String ret_ = _type.getWildCardString();
            MethodId fid_;
            fid_ = id_;
            String decl_ = _type.getFullName();
            MethodMetaInfo met_ = new MethodMetaInfo(AccessEnum.PUBLIC,decl_, id_, MethodModifier.STATIC, ret_, fid_, decl_);
            met_.setFileName(fileName_);
            infos_.put(id_, met_);
            id_ = new MethodId(MethodAccessKind.STATIC, values_, new StringList());
            ret_ = StringExpUtil.getPrettyArrayType(ret_);
            fid_ = id_;
            met_ = new MethodMetaInfo(AccessEnum.PUBLIC,decl_, id_, MethodModifier.STATIC, ret_, fid_, decl_);
            met_.setFileName(fileName_);
            infos_.put(id_, met_);
        }
        ExecRootBlock par_ = _type.getParentType();
        String format_;
        if (par_ != null) {
            String gene_ = par_.getGenericString();
            if (Templates.correctNbParameters(_name, _context)) {
                format_ = Templates.quickFormat(_name, gene_, _context);
            } else {
                format_ = par_.getFullName();
            }
        } else {
            format_ = "";
        }
        AccessEnum acc_ = _type.getAccess();
        boolean st_ = _type.isStaticType();
        if (_type instanceof ExecInterfaceBlock) {
            ClassMetaInfo cl_ = new ClassMetaInfo(_name, _type.getImportedDirectGenericSuperInterfaces(), format_, inners_,
                    infosFields_, infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, ClassCategory.INTERFACE, st_, acc_);
            cl_.setFileName(fileName_);
            cl_.setAnnotableBlock(_type);
            return cl_;
        }
        if (_type instanceof ExecAnnotationBlock) {
            ClassMetaInfo cl_ = new ClassMetaInfo(_name, new StringList(), format_, inners_,
                    infosFields_, infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, ClassCategory.ANNOTATION, st_, acc_);
            cl_.setFileName(fileName_);
            cl_.setAnnotableBlock(_type);
            return cl_;
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        if (_type instanceof ExecEnumBlock) {
            cat_ = ClassCategory.ENUM;
        }
        boolean abs_ = true;
        boolean final_ = true;
        if (_type instanceof ExecClassBlock) {
            abs_ = ((ExecClassBlock)_type).isAbstractType();
            final_ = ((ExecClassBlock)_type).isFinalType();
        }
        String superClass_ = _type.getImportedDirectGenericSuperClass();
        StringList superInterfaces_ = _type.getImportedDirectGenericSuperInterfaces();
        ClassMetaInfo cl_ = new ClassMetaInfo(_name, superClass_, superInterfaces_, format_, inners_,
                infosFields_, infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, cat_, abs_, st_, final_, acc_);
        cl_.setFileName(fileName_);
        cl_.setAnnotableBlock(_type);
        return cl_;
    }

    public static ConstructorId tryFormatId(String _name, ContextEl _context, ConstructorId _id) {
        ConstructorId fid_;
        if (Templates.correctNbParameters(_name, _context)) {
            fid_ = _id.reflectFormat(_name, _context);
        } else {
            fid_ = _id;
        }
        return fid_;
    }

    public static String tryFormatType(String _idType, String _name, ContextEl _context) {
        String formCl_ = _idType;
        if (Templates.correctNbParameters(_name, _context)) {
            formCl_ = _name;
        }
        return formCl_;
    }

    public static MethodId tryFormatId(String _name, ContextEl _context, MethodId _id) {
        MethodId fid_;
        if (Templates.correctNbParameters(_name, _context)) {
            fid_ = _id.reflectFormat(_name, _context);
        } else {
            fid_ = _id;
        }
        return fid_;
    }

    public static boolean isAbstractType(GeneType _type) {
        if (_type instanceof StandardClass) {
            return ((StandardClass)_type).isAbstractStdType();
        }
        if (_type instanceof ExecClassBlock) {
            return ((ExecClassBlock)_type).isAbstractType();
        }
        return true;
    }
    public static ClassMetaInfo getExtendedClassMetaInfo(ContextEl _context,String _name, String _variableOwner) {
        if (StringList.quickEq(_name, Templates.SUB_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.SUB_TYPE)) {
            StringList upperBounds_ = new StringList(_name.substring(Templates.SUB_TYPE.length()));
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.SUP_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList(_name.substring(Templates.SUB_TYPE.length()));
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.PREFIX_VAR_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.VARIABLE,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.ARR_BEG_STRING)&&_name.contains(Templates.PREFIX_VAR_TYPE)) {
            return new ClassMetaInfo(_name, _context, ClassCategory.ARRAY, _variableOwner);
        }
        return getClassMetaInfo(_context,_name);
    }
    public static ClassMetaInfo getClassMetaInfo(ContextEl _context,String _name) {
        if (PrimitiveTypeUtil.isPrimitive(_name, _context)) {
            return new ClassMetaInfo(_name, _context, ClassCategory.PRIMITIVE,"");
        }
        if (new ClassArgumentMatching(_name).isArray()) {
            return new ClassMetaInfo(_name, _context, ClassCategory.ARRAY, "");
        }
        String base_ = StringExpUtil.getIdFromAllTypes(_name);
        LgNames stds_ = _context.getStandards();
        for (EntryCust<String, StandardType> c: stds_.getStandards().entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            StandardType clblock_ = c.getValue();
            return getClassMetaInfo(_context,clblock_, _name);
        }
        return getCustomClassMetaInfo(_name, _context);
    }
    public static ClassMetaInfo getClassMetaInfo(ContextEl _context,StandardType _type,String _name) {
        String k_ = _type.getFullName();
        ObjectMap<MethodId, MethodMetaInfo> infos_;
        infos_ = new ObjectMap<MethodId, MethodMetaInfo>();
        StringMap<FieldMetaInfo> infosFields_;
        infosFields_ = new StringMap<FieldMetaInfo>();
        ObjectMap<ConstructorId, ConstructorMetaInfo> infosConst_;
        infosConst_ = new ObjectMap<ConstructorId, ConstructorMetaInfo>();
        StringList inners_ = new StringList();
        boolean existCtor_ = false;
        ObjectMap<MethodId, MethodMetaInfo> infosExplicits_;
        infosExplicits_ = new ObjectMap<MethodId, MethodMetaInfo>();
        ObjectMap<MethodId, MethodMetaInfo> infosImplicits_;
        infosImplicits_ = new ObjectMap<MethodId, MethodMetaInfo>();
        ObjectMap<MethodId, MethodMetaInfo> infosTrues_;
        infosTrues_ = new ObjectMap<MethodId, MethodMetaInfo>();
        ObjectMap<MethodId, MethodMetaInfo> infosFalses_;
        infosFalses_ = new ObjectMap<MethodId, MethodMetaInfo>();
        for (StandardField f: _type.getFields().values()) {
            String ret_ = f.getImportedClassName();
            boolean staticElement_ = f.isStaticField();
            boolean finalElement_ = f.isFinalField();
            for (String g: f.getFieldName()) {
                FieldMetaInfo met_ = new FieldMetaInfo(k_, g, ret_, staticElement_, finalElement_, AccessEnum.PUBLIC);
                infosFields_.put(g, met_);
            }
        }
        for (StandardMethod m: _type.getMethods().values()) {
            MethodId id_ = m.getId();
            String ret_ = m.getImportedReturnType();
            String decl_ = m.getDeclaringType();
            MethodMetaInfo met_ = new MethodMetaInfo(AccessEnum.PUBLIC,decl_, id_, m.getModifier(), ret_, id_, decl_);
            infos_.put(id_, met_);
        }
        for (StandardConstructor d: _type.getConstructors()) {
            existCtor_ = true;
            ConstructorId id_ = d.getGenericId();
            String decl_ = d.getDeclaringType();
            String ret_ = d.getImportedReturnType();
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, AccessEnum.PUBLIC, id_, ret_, id_, decl_);
            infosConst_.put(id_, met_);
        }
        if (!existCtor_) {
            ConstructorId id_ = new ConstructorId(_name, new StringList(), false);
            ConstructorId fid_;
            String ret_ = _context.getStandards().getAliasVoid();
            fid_ = id_;
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, AccessEnum.PUBLIC, id_, ret_, fid_, _name);
            infosConst_.put(id_, met_);
        }
        boolean st_ = _type.isStaticType();
        if (_type instanceof StandardInterface) {
            return new ClassMetaInfo(_name, ((StandardInterface)_type).getDirectInterfaces(), "",inners_,infosFields_,infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, ClassCategory.INTERFACE,st_,AccessEnum.PUBLIC);
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        boolean abs_ = ((StandardClass) _type).isAbstractStdType();
        boolean final_ = ((StandardClass) _type).isFinalStdType();
        String superClass_ = ((StandardClass) _type).getSuperClass();
        StringList superInterfaces_ = _type.getDirectInterfaces();
        return new ClassMetaInfo(_name, superClass_, superInterfaces_, "",inners_,infosFields_,infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, cat_, abs_, st_, final_,AccessEnum.PUBLIC);
    }

    public static ArrayStruct newStackTraceElementArrayFull(ContextEl _cont) {
        return _cont.getFullStack().newStackTraceElementArray();
    }

    public static ArrayStruct newStackTraceElementArray(ContextEl _cont) {
        int count_ = _cont.nbPages();
        Struct[] arr_ = new Struct[count_];
        for (int i = 0; i < count_; i++) {
            arr_[i] = newStackTraceElement(_cont,i);
        }
        String cl_ = _cont.getStandards().getAliasStackTraceElement();
        cl_ = StringExpUtil.getPrettyArrayType(cl_);
        return new ArrayStruct(arr_, cl_);
    }

    public static StackTraceElementStruct newStackTraceElement(ContextEl _cont, int _index) {
        AbstractPageEl call_ = _cont.getCall(_index);
        int indexFileType = call_.getTraceIndex();
        ExecFileBlock f_ = call_.getFile();
        String fileName;
        int row;
        int col;
        if (f_ != null) {
            fileName = f_.getFileName();
            FileMetrics metrics_ = f_.getMetrics();
            row = metrics_.getRowFile(indexFileType);
            col = metrics_.getColFile(indexFileType,row);
        } else {
            fileName = "";
            row = 0;
            col = 0;
        }
        String currentClassName = call_.getGlobalClass();
        ExecBlock bl_ = call_.getBlockRoot();
        if (bl_ != null) {
            FunctionBlock fct_ = bl_.getFunction();
            if (fct_ instanceof ReturnableWithSignature) {
                String signature =((ReturnableWithSignature)fct_).getSignature(_cont);
                return new StackTraceElementStruct(fileName,row,col,indexFileType,currentClassName,signature);
            }
        }
        String signature = "";
        return new StackTraceElementStruct(fileName,row,col,indexFileType,currentClassName,signature);
    }

    public static void addPage(ContextEl _cont,AbstractPageEl _page) {
        LgNames stds_ = _cont.getStandards();
        String sof_ = stds_.getAliasSof();
        if (_cont.getStackOverFlow() >= CustList.FIRST_INDEX && _cont.getStackOverFlow() <= _cont.nbPages()) {
            _cont.setCallingState( new ErrorStruct(_cont,sof_));
        } else {
            _cont.addInternPage(_page);
        }
    }

    public static boolean hasToExit(ContextEl _cont,String _className) {
        return hasToExit(_cont,_className,null);
    }
    public static boolean hasToExit(ContextEl _cont,String _className,Argument _arg) {
        Classes classes_ = _cont.getClasses();
        String idClass_ = StringExpUtil.getIdFromAllTypes(_className);
        String curClass_ = _cont.getLastPage().getGlobalClass();
        curClass_ = StringExpUtil.getIdFromAllTypes(curClass_);
        if (StringList.quickEq(curClass_, idClass_)) {
            return false;
        }
        ExecRootBlock c_ = classes_.getClassBody(idClass_);
        if (c_ != null) {
            DefaultLockingClass locks_ = classes_.getLocks();
            if (_cont.getInitializingTypeInfos().isInitEnums()) {
                InitClassState res_ = locks_.getState(idClass_);
                if (res_ != InitClassState.SUCCESS) {
                    _cont.getInitializingTypeInfos().failInitEnums();
                    return true;
                }
                return false;
            }
            InitClassState res_ = locks_.getState(_cont, idClass_);
            if (res_ == InitClassState.NOT_YET) {
                _cont.setCallingState(new NotInitializedClass(idClass_, _arg));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(idClass_, _cont);
                _cont.setException(causing_);
                return true;
            }
        }
        return false;
    }
}
