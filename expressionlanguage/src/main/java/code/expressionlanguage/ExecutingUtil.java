package code.expressionlanguage;

import code.expressionlanguage.calls.*;
import code.expressionlanguage.calls.util.*;
import code.expressionlanguage.common.FunctionIdUtil;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.util.*;

public final class ExecutingUtil {
    private ExecutingUtil() {
    }

    static void processException(ContextEl _context) {
        if (!_context.getInitializingTypeInfos().isFailInit() && _context.getCallingState() instanceof Struct) {
            _context.getThrowing().removeBlockFinally(_context);
        }
    }

    static void processTagsBase(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        if (!ip_.checkCondition(_context)) {
            return;
        }
        ReadWrite rw_ = ip_.getReadWrite();
        Block en_ = rw_.getBlock();
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
            }
            if (_context.callsOrException()) {
                return EndCallValue.NEXT;
            }
            return EndCallValue.FORWARD;
        }
        return EndCallValue.NEXT;
    }

    private static AbstractPageEl createInstancingClass(ContextEl _context,NotInitializedClass _e) {
        return createInstancingClass(_context,_e.getClassName());
    }
    public static AbstractPageEl createInstancingClass(ContextEl _context,String _class) {
        _context.setCallingState(null);
        String baseClass_ = Templates.getIdFromAllTypes(_class);
        RootBlock class_ = _context.getClasses().getClassBody(baseClass_);
        Block firstChild_ = class_.getFirstChild();
        StaticInitPageEl page_ = new StaticInitPageEl();
        Argument argGl_ = new Argument();
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        rw_.setBlock(firstChild_);
        page_.setReadWrite(rw_);
        page_.setBlockRoot(class_);
        while (firstChild_ != null) {
            if (firstChild_ instanceof StaticBlock) {
                page_.getProcessedBlocks().put((InitBlock) firstChild_, false);
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
        NamedFunctionBlock methodLoc_;
        if (FunctionIdUtil.isOperatorName(_method)) {
            methodLoc_ = Classes.getOperatorsBodiesById(_context, _method).first();
            _context.getCoverage().passCalls(_context,"",methodLoc_);
        } else {
            methodLoc_ = Classes.getMethodBodiesById(_context, _class, _method).first();
            String idCl_ = Templates.getIdFromAllTypes(_class);
            _context.getCoverage().passCalls(_context,idCl_,methodLoc_);
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
        NamedFunctionBlock methodLoc_;
        methodLoc_ = Classes.getMethodBodiesById(_context, _class, _method).first();
        String idCl_ = Templates.getIdFromAllTypes(_class);
        _context.getCoverage().passCalls(_context,idCl_,methodLoc_);
        String ret_ = methodLoc_.getImportedReturnType();
        CastPageEl pageLoc_ = new CastPageEl(_context,ret_,Argument.createVoid(),_class);
        setMethodInfos(_context,pageLoc_,methodLoc_, _args);
        return pageLoc_;
    }
    private static void setMethodInfos(ContextEl _context,AbstractMethodPageEl _page, NamedFunctionBlock _block, CustList<Argument> _args) {
        StringList paramsLoc_ = _block.getParametersNames();
        int lenLoc_ = paramsLoc_.size();
        for (int i = CustList.FIRST_INDEX; i < lenLoc_; i++) {
            String p_ = paramsLoc_.get(i);
            LocalVariable lv_ = LocalVariable.newLocalVariable(_args.get(i).getStruct(),_context);
            _page.getParameters().put(p_, lv_);
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
    public static CallConstructorPageEl createInstancing(ContextEl _context,String _class, RootBlock _type,CallConstructor _call, CustList<Argument> _args) {
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
    private static NewAnnotationPageEl createAnnotation(ContextEl _context,String _class,RootBlock _type,
                                                        StringMap<AnnotationTypeInfo> _id,
                                                        CustList<Argument> _args) {
        _context.setCallingState(null);
        NewAnnotationPageEl page_;
        FileBlock file_ = _type.getFile();
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
    private static AbstractPageEl createForwardingInstancing(ContextEl _context,String _class, RootBlock _type,CallConstructor _call, CustList<Argument> _args) {
        _context.setCallingState(null);
        AbstractPageEl page_ = new CallConstructorPageEl();
        Argument global_ = _call.getArgument();
        Argument argGl_ = new Argument();
        argGl_.setStruct(global_.getStruct());
        page_.setGlobalArgument(argGl_);
        setInstanciationInfos(_context,page_,_class,_type,_call,_args);
        return page_;
    }
    private static void setInstanciationInfos(ContextEl _context,AbstractPageEl _page,String _class, RootBlock _type,CallConstructor _call, CustList<Argument> _args) {
        ConstructorId id_ = _call.getId();
        FileBlock file_ = _type.getFile();
        CustList<ConstructorBlock> methods_ = Classes.getConstructorBodiesById(_context, _class, id_);
        ConstructorBlock method_ = null;
        _page.setGlobalClass(_class);
        ReadWrite rw_ = new ReadWrite();
        if (!methods_.isEmpty()) {
            String idCl_ = Templates.getIdFromAllTypes(_class);
            method_ = methods_.first();
            _context.getCoverage().passCalls(_context,idCl_,method_);
            StringList params_ = method_.getParametersNames();
            int len_ = params_.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                String p_ = params_.get(i);
                LocalVariable lv_ = LocalVariable.newLocalVariable(_args.get(i).getStruct(),_context);
                _page.getParameters().put(p_, lv_);
            }
            Block firstChild_ = method_.getFirstChild();
            rw_.setBlock(firstChild_);
        }
        _page.setReadWrite(rw_);
        _page.setBlockRoot(method_);
        _page.setFile(file_);
    }
    private static FieldInitPageEl createInitFields(ContextEl _context,String _class, Argument _current) {
        _context.setCallingState(null);
        String baseClass_ = Templates.getIdFromAllTypes(_class);
        RootBlock class_ = _context.getClasses().getClassBody(baseClass_);
        FieldInitPageEl page_ = new FieldInitPageEl();
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(_current);
        ReadWrite rw_ = new ReadWrite();
        Block firstChild_ = class_.getFirstChild();
        rw_.setBlock(firstChild_);
        while (firstChild_ != null) {
            if (firstChild_ instanceof InstanceBlock) {
                page_.getProcessedBlocks().put((InitBlock) firstChild_, false);
            }
            firstChild_ = firstChild_.getNextSibling();
        }
        page_.setReadWrite(rw_);
        page_.setBlockRoot(class_);
        page_.setFile(class_.getFile());
        return page_;
    }
    private static BlockPageEl createBlockPageEl(ContextEl _context,String _class, Argument _current, InitBlock _block) {
        _context.setCallingState(null);
        FileBlock file_ = _block.getFile();
        BlockPageEl page_ = new BlockPageEl();
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(_current);
        ReadWrite rw_ = new ReadWrite();
        Block firstChild_ = _block.getFirstChild();
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
        if (_name.startsWith(Templates.ARR_BEG_STRING)) {
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
        String base_ = Templates.getIdFromAllTypes(_name);
        LgNames stds_ = _context.getStandards();
        for (EntryCust<String, StandardType> c: stds_.getStandards().entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            StandardType clblock_ = c.getValue();
            return getClassMetaInfo(_context,clblock_, _name);
        }
        return _context.getClasses().getClassMetaInfo(_name, _context);
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
            return new ClassMetaInfo(_name, ((StandardInterface)_type).getDirectInterfaces(), "",inners_,infosFields_,infos_, infosConst_, ClassCategory.INTERFACE,st_,AccessEnum.PUBLIC);
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        boolean abs_ = ((StandardClass) _type).isAbstractStdType();
        boolean final_ = ((StandardClass) _type).isFinalStdType();
        String superClass_ = ((StandardClass) _type).getSuperClass();
        StringList superInterfaces_ = _type.getDirectInterfaces();
        return new ClassMetaInfo(_name, superClass_, superInterfaces_, "",inners_,infosFields_,infos_, infosConst_, cat_, abs_, st_, final_,AccessEnum.PUBLIC);
    }

}
