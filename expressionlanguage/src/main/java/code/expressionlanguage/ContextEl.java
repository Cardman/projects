package code.expressionlanguage;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.AbstractReflectPageEl;
import code.expressionlanguage.calls.BlockPageEl;
import code.expressionlanguage.calls.CurrentInstancingPageEl;
import code.expressionlanguage.calls.FieldInitPageEl;
import code.expressionlanguage.calls.ForwardPageEl;
import code.expressionlanguage.calls.MethodPageEl;
import code.expressionlanguage.calls.NewAnnotationPageEl;
import code.expressionlanguage.calls.NewInstancingPageEl;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.calls.ReflectAnnotationPageEl;
import code.expressionlanguage.calls.ReflectConstructorPageEl;
import code.expressionlanguage.calls.ReflectGetDefaultValuePageEl;
import code.expressionlanguage.calls.ReflectGetFieldPageEl;
import code.expressionlanguage.calls.ReflectMethodPageEl;
import code.expressionlanguage.calls.ReflectSetFieldPageEl;
import code.expressionlanguage.calls.StaticInitPageEl;
import code.expressionlanguage.calls.SuperInstancingImplicitPageEl;
import code.expressionlanguage.calls.SuperInstancingPageEl;
import code.expressionlanguage.calls.util.CallConstructor;
import code.expressionlanguage.calls.util.CustomFoundAnnotation;
import code.expressionlanguage.calls.util.CustomFoundBlock;
import code.expressionlanguage.calls.util.CustomFoundConstructor;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.calls.util.CustomReflectMethod;
import code.expressionlanguage.calls.util.InstancingStep;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.calls.util.NotInitializedFields;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeOwnersDepends;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.errors.custom.BadInheritedClass;
import code.expressionlanguage.errors.custom.IllegalCallCtorByType;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.AnalyzingEl;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.FileBlock;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.methods.InitBlock;
import code.expressionlanguage.methods.InstanceBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.ReflectingType;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.StaticBlock;
import code.expressionlanguage.methods.util.LocalThrowing;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.AnnotationStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.types.PartTypeUtil;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.graphs.SortedGraph;
import code.util.ints.MathFactory;

public final class ContextEl implements FieldableStruct, EnumerableStruct,Runnable,ExecutableCode {

    private static final String EMPTY_TYPE = "";
    private static final String EMPTY_PREFIX = "";
    private static final int DEFAULT_TAB_WIDTH = 4;

    private MathFactory mathFactory;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private String filesConfName;

    private int stackOverFlow;

    private Options options;

    private Struct exception;

    private Struct memoryError;

    private LocalThrowing throwing = new LocalThrowing();

    private CustomFoundAnnotation callAnnot;
    private CustomFoundConstructor callCtor;

    private NotInitializedFields initFields;
    private CustomFoundBlock foundBlock;

    private CustomFoundMethod callMethod;

    private CustomReflectMethod reflectMethod;

    private NotInitializedClass initClass;

    private Struct parent;

    private LgNames standards;

    private AnalyzedPageEl analyzing;

    private Classes classes;

    private CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private String html;

    private ContextEl parentThread;

    private CustList<ContextEl> children = new CustList<ContextEl>();

    private String className = "";
    private ObjectMap<ClassField, Struct> fields = new ObjectMap<ClassField, Struct>();
    private Initializer init;
    private String name;
    private int ordinal;
    private KeyWords keyWords;
    private boolean initEnums;
    private boolean failInit;
    private IdList<Struct> sensibleFields = new IdList<Struct>();

    public ContextEl() {
        this(CustList.INDEX_NOT_FOUND_ELT);
    }

    public ContextEl(int _stackOverFlow) {
        this(_stackOverFlow, new DefaultLockingClass(),new DefaultInitializer(), new Options(), new KeyWords());
    }
    public ContextEl(DefaultLockingClass _lock,Initializer _init, Options _options, KeyWords _keyWords) {
        this(CustList.INDEX_NOT_FOUND_ELT, _lock,_init, _options,_keyWords);
    }

    public ContextEl(int _stackOverFlow, DefaultLockingClass _lock,Initializer _init, Options _options, KeyWords _keyWords) {
        options = _options;
        stackOverFlow = _stackOverFlow;
        init = _init;
        keyWords = _keyWords;
        classes = new Classes();
        classes.setLocks(_lock);
    }
    public ContextEl(ContextEl _context, String _className,
            String _name, int _ordinal,
            ObjectMap<ClassField,Struct> _fields, Struct _parent) {
        parentThread = _context;
        name = _name;
        ordinal = _ordinal;
        classes = _context.classes;
        options = _context.options;
        standards = _context.standards;
        tabWidth = _context.tabWidth;
        stackOverFlow = _context.stackOverFlow;
        filesConfName = _context.filesConfName;
        memoryError = _context.memoryError;
        className = _className;
        fields = _fields;
        parent = _parent;
        init = _context.init;
        keyWords = _context.keyWords;
        sensibleFields = _context.sensibleFields;
        _context.children.add(this);
    }
    public boolean isSensibleField(String _clName) {
        if (!initEnums) {
            return false;
        }
        String curr_ = getCurInitType();
        if (curr_.isEmpty()) {
            return true;
        }
        if (!StringList.quickEq(curr_, _clName)) {
            return true;
        }
        return false;
    }
    public boolean isContainedSensibleFields(Struct _array) {
        if (!initEnums) {
            return false;
        }
        return sensibleFields.containsObj(_array);
    }
    public IdList<Struct> getSensibleFields() {
        return sensibleFields;
    }
    public void addSensibleField(String _fc, Struct _container) {
        if (!initEnums) {
            return;
        }
        if (_container == NullStruct.NULL_VALUE) {
            return;
        }
        if (_container instanceof BooleanStruct) {
            return;
        }
        if (_container instanceof NumberStruct) {
            return;
        }
        if (_container instanceof StringStruct) {
            return;
        }
        if (_container instanceof AnnotationStruct) {
            return;
        }
        String curr_ = getCurInitType();
        if (!StringList.quickEq(curr_, _fc)) {
            sensibleFields.add(_container);
        }
    }
    public void addSensibleField(Struct _container, Struct _owned) {
        if (!initEnums) {
            return;
        }
        if (_owned == NullStruct.NULL_VALUE) {
            return;
        }
        if (_owned instanceof BooleanStruct) {
            return;
        }
        if (_owned instanceof NumberStruct) {
            return;
        }
        if (_owned instanceof StringStruct) {
            return;
        }
        if (_owned instanceof AnnotationStruct) {
            return;
        }
        if (sensibleFields.containsObj(_container)) {
            sensibleFields.add(_owned);
        }
    }
    public void addSensibleElementsFromClonedArray(Struct _array, ArrayStruct _cloned) {
        if (!initEnums) {
            return;
        }
        if (!sensibleFields.containsObj(_array)) {
            return;
        }
        for (Struct s: _cloned.getInstance()) {
            if (s == NullStruct.NULL_VALUE) {
                continue;
            }
            if (s instanceof BooleanStruct) {
                continue;
            }
            if (s instanceof NumberStruct) {
                continue;
            }
            if (s instanceof StringStruct) {
                continue;
            }
            if (s instanceof AnnotationStruct) {
                continue;
            }
            sensibleFields.add(s);
        }
    }
    public void failInitEnums() {
        if (!initEnums) {
            return;
        }
        failInit = true;
    }
    public boolean isInitEnums() {
        return initEnums;
    }
    public void setInitEnums(boolean _initEnums) {
        initEnums = _initEnums;
    }
    public void resetInitEnums() {
        failInit = false;
        exception = null;
        sensibleFields.clear();
        clearPages();
    }
    private String getCurInitType() {
        for (AbstractPageEl a: importing) {
            if (a instanceof StaticInitPageEl) {
                return a.getGlobalClass();
            }
        }
        return "";
    }
    public ContextEl getParentThread() {
        return parentThread;
    }
    @Override
    public Struct getParent() {
        return parent;
    }
    @Override
    public void run() {
        String run_ = init.getRunTask(standards);
        String runnable_ = init.getInterfaceTask(standards);
        MethodId id_ = new MethodId(MethodModifier.ABSTRACT, run_, new StringList(), false);
        GeneType type_ = classes.getClassBody(runnable_);
        String base_ = Templates.getIdFromAllTypes(className);
        ClassMethodId mId_ = TypeUtil.getConcreteMethodsToCall(type_, id_, this).getVal(base_);
        Argument arg_ = new Argument();
        arg_.setStruct(this);
        ProcessMethod.calculateArgument(arg_, mId_.getClassName(), mId_.getConstraints(), new CustList<Argument>(), this);
    }
    public boolean processException() {
        if (exception != null) {
            throwing.removeBlockFinally(this);
            if (exception != null) {
                return false;
            }
        }
        return true;
    }
    public void processTags() {
        AbstractPageEl ip_ = getLastPage();
        if (!ip_.checkCondition(this)) {
            return;
        }
        ReadWrite rw_ = ip_.getReadWrite();
        Block en_ = rw_.getBlock();
        if (en_ != null) {
            ip_.setGlobalOffset(en_.getOffset().getOffsetTrim());
            ip_.setOffset(0);
        }
        ip_.tryProcessEl(this);
    }
    public AbstractPageEl processAfterOperation() {
        if (callCtor != null) {
            return createInstancing(callCtor);
        }
        if (callAnnot != null) {
            return createAnnotation(callAnnot.getClassName(), callAnnot.getId(), callAnnot.getArguments());
        }
        if (callMethod != null) {
            return createCallingMethod(callMethod);
        }
        if (reflectMethod != null) {
            return createReflectMethod(reflectMethod);
        }
        if (initClass != null) {
            return createInstancingClass(initClass);
        }
        if (initFields != null) {
            return createInitFields(initFields.getClassName(), initFields.getCurrentObject());
        }
        if (foundBlock != null) {
            return createBlockPageEl(foundBlock.getClassName(), foundBlock.getCurrentObject(), foundBlock.getBlock());
        }
        if (failInit) {
            return null;
        }
        if (exception != null) {
            throwing.removeBlockFinally(this);
        }
        return null;
    }

    public Boolean removeCall(int _sizeBk) {
        AbstractPageEl p_ = getLastPage();
        if (p_.getReadWrite() == null) {
            removeLastPage();
            if (nbPages() == _sizeBk) {
                return null;
            }
            if (p_ instanceof ForwardPageEl) {
                if(((ForwardPageEl)p_).forwardTo(getLastPage(), this)) {
                    return true;
                }
                return null;
            }
            return true;
        }
        return false;
    }
    private AbstractPageEl createInstancingClass(NotInitializedClass _e) {
        return createInstancingClass(_e.getClassName());
    }
    public AbstractPageEl createInstancingClass(String _class) {
        setInitClass(null);
//        classes.preInitializeStaticFields(_class, this);
        String baseClass_ = Templates.getIdFromAllTypes(_class);
        RootBlock class_ = classes.getClassBody(baseClass_);
        Block firstChild_ = class_.getFirstChild();
        StaticInitPageEl page_ = new StaticInitPageEl();
        page_.setTabWidth(tabWidth);
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

    private AbstractPageEl createCallingMethod(CustomFoundMethod _e) {
        String cl_ = _e.getClassName();
        MethodId id_ = _e.getId();
        CustList<Argument> args_ = _e.getArguments();
        Argument gl_ = _e.getGl();
        return createCallingMethod(gl_, cl_, id_, args_);
    }
    public MethodPageEl createCallingMethod(Argument _gl, String _class, MethodId _method, CustList<Argument> _args) {
        setCallMethod(null);
        MethodPageEl pageLoc_ = new MethodPageEl();
        pageLoc_.setTabWidth(tabWidth);
        pageLoc_.setGlobalArgument(_gl);
        pageLoc_.setGlobalClass(_class);
        MethodId id_ = _method;
        NamedFunctionBlock methodLoc_;
        if (!StringList.isWord(id_.getName())) {
            methodLoc_ = Classes.getOperatorsBodiesById(this, id_).first();
        } else {
            methodLoc_ = Classes.getMethodBodiesById(this, _class, id_).first();
        }
        StringList paramsLoc_ = methodLoc_.getParametersNames();
        StringList typesLoc_ = methodLoc_.getImportedParametersTypes();
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
        pageLoc_.setFile(methodLoc_.getFile());
        return pageLoc_;
    }
    private AbstractPageEl createInstancing(CustomFoundConstructor _e) {
        String cl_ = _e.getClassName();
        CustList<Argument> args_ = _e.getArguments();
        InstancingStep in_ = _e.getInstanceStep();
        if (in_ == InstancingStep.NEWING) {
            return createInstancing(cl_, _e.getCall(), args_);
        }
        return createInstancing(cl_, _e.getCall(), in_, args_);
    }
    public NewInstancingPageEl createInstancing(String _class, CallConstructor _call, CustList<Argument> _args) {
        setCallCtor(null);
        NewInstancingPageEl page_;
        Argument global_ = _call.getArgument();
        ConstructorId id_ = _call.getId();
        FileBlock file_ = getFile(_class);
        CustList<GeneConstructor> methods_ = Classes.getConstructorBodiesById(this, _class, id_);
        ConstructorBlock method_ = null;
        Argument argGl_ = new Argument();
        page_ = new NewInstancingPageEl();
        Struct str_ = NullStruct.NULL_VALUE;
        if (global_ != null) {
            str_ = global_.getStruct();
        }
        String fieldName_ = _call.getFieldName();
        int ordinal_ = _call.getChildIndex();
        argGl_.setStruct(init.processInit(this, str_, _class, fieldName_, ordinal_));
        page_.setTabWidth(tabWidth);
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        if (!methods_.isEmpty()) {
            method_ = (ConstructorBlock) methods_.first();
            StringList params_ = method_.getParametersNames();
            StringList types_ = method_.getImportedParametersTypes();
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
        }
        page_.setReadWrite(rw_);
        page_.setBlockRoot(method_);
        page_.setFile(file_);
        return page_;
    }
    public NewAnnotationPageEl createAnnotation(String _class,
            StringMap<String> _id,
            CustList<Argument> _args) {
        setCallAnnot(null);
        NewAnnotationPageEl page_;
        ConstructorBlock method_ = null;
        FileBlock file_ = getFile(_class);
        Argument argGl_ = new Argument();
        page_ = new NewAnnotationPageEl();
        page_.setArgs(_args);
        page_.setNames(_id);
        argGl_.setStruct(init.processInitAnnot(this, _class));
        page_.setTabWidth(tabWidth);
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        page_.setReadWrite(rw_);
        page_.setBlockRoot(method_);
        page_.setFile(file_);
        return page_;
    }
    public AbstractPageEl createInstancing(String _class, CallConstructor _call, InstancingStep _in,CustList<Argument> _args) {
        setCallCtor(null);
        AbstractPageEl page_;
        FileBlock file_ = getFile(_class);
        Argument global_ = _call.getArgument();
        ConstructorId id_ = _call.getId();
        CustList<GeneConstructor> methods_ = Classes.getConstructorBodiesById(this, _class, id_);
        ConstructorBlock method_ = null;
        Argument argGl_ = new Argument();
        if (_in == InstancingStep.USING_SUPER) {
            page_ = new SuperInstancingPageEl();
        } else if (_in == InstancingStep.USING_SUPER_IMPL) {
            page_ = new SuperInstancingImplicitPageEl();
        } else {
            page_ = new CurrentInstancingPageEl();
        }
        argGl_.setStruct(global_.getStruct());
        page_.setTabWidth(tabWidth);
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        if (!methods_.isEmpty()) {
            method_ = (ConstructorBlock) methods_.first();
            StringList params_ = method_.getParametersNames();
            StringList types_ = method_.getImportedParametersTypes();
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
        }
        page_.setReadWrite(rw_);
        page_.setBlockRoot(method_);
        page_.setFile(file_);
        return page_;
    }
    public FieldInitPageEl createInitFields(String _class, Argument _current) {
        setInitFields(null);
        String baseClass_ = Templates.getIdFromAllTypes(_class);
        RootBlock class_ = classes.getClassBody(baseClass_);
        FieldInitPageEl page_ = new FieldInitPageEl();
        page_.setTabWidth(tabWidth);
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
    public BlockPageEl createBlockPageEl(String _class, Argument _current, InitBlock _block) {
        setFoundBlock(null);
        FileBlock file_ = getFile(_class);
        BlockPageEl page_ = new BlockPageEl();
        page_.setTabWidth(tabWidth);
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
    private AbstractReflectPageEl createReflectMethod(CustomReflectMethod _e) {
        ReflectingType r_ = _e.getReflect();
        CustList<Argument> args_ = _e.getArguments();
        Argument gl_ = _e.getGl();
        return createReflectMethod(gl_, args_, r_);
    }
    public AbstractReflectPageEl createReflectMethod(Argument _gl, CustList<Argument> _args, ReflectingType _reflect) {
        setReflectMethod(null);
        AbstractReflectPageEl pageLoc_;
        if (_reflect == ReflectingType.METHOD) {
            pageLoc_ = new ReflectMethodPageEl();
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
        pageLoc_.setTabWidth(tabWidth);
        pageLoc_.setGlobalArgument(_gl);
        pageLoc_.setArguments(_args);
        ReadWrite rwLoc_ = new ReadWrite();
        pageLoc_.setReadWrite(rwLoc_);
        return pageLoc_;
    }
    private FileBlock getFile(String _class) {
        String idCl_= Templates.getIdFromAllTypes(_class);
        FileBlock file_ = null;
        for (RootBlock c: classes.getClassBodies()) {
            if (StringList.quickEq(c.getFullName(), idCl_)) {
                file_ = c.getFile();
                break;
            }
        }
        return file_;
    }
    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }
    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return fields;
    }
    @Override
    public Object getInstance() {
        return this;
    }
    @Override
    public boolean isArray() {
        return false;
    }
    @Override
    public boolean isNull() {
        return false;
    }
    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public Struct getStruct(ClassField _classField) {
        return fields.getVal(_classField);
    }

    @Override
    public void setStruct(ClassField _classField, Struct _value) {
        for (EntryCust<ClassField, Struct> e: fields.entryList()) {
            if (e.getKey().eq(_classField)) {
                e.setValue(_value);
                return;
            }
        }
    }
    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
    public void initError() {
        memoryError = new ErrorStruct(this, standards.getAliasError());
    }
    @Override
    public ClassMetaInfo getClassMetaInfo(String _name) {
        if (!classes.isCustomType(_name)) {
            String base_ = Templates.getIdFromAllTypes(_name);
            LgNames stds_ = getStandards();
            for (EntryCust<String, StandardType> c: stds_.getStandards().entryList()) {
                String k_ = c.getKey();
                if (!StringList.quickEq(k_, base_)) {
                    continue;
                }
                StandardType clblock_ = c.getValue();
                return getClassMetaInfo(clblock_, _name);
            }
            return null;
        }
        return classes.getClassMetaInfo(_name, this);
    }
    public ClassMetaInfo getClassMetaInfo(StandardType _type,String _name) {
        String k_ = _type.getFullName();
        ObjectNotNullMap<MethodId, MethodMetaInfo> infos_;
        infos_ = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
        StringMap<FieldMetaInfo> infosFields_;
        infosFields_ = new StringMap<FieldMetaInfo>();
        ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> infosConst_;
        infosConst_ = new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>();
        StringList inners_ = new StringList();
        boolean existCtor_ = false;
        for (StandardField f: _type.getFields().values()) {
            String ret_ = f.getClassName();
            boolean staticElement_ = f.isStaticField();
            boolean finalElement_ = f.isFinalField();
            AccessEnum acc_ = f.getAccess();
            for (String g: f.getFieldName()) {
                FieldMetaInfo met_ = new FieldMetaInfo(k_, g, ret_, staticElement_, finalElement_, false, acc_);
                infosFields_.put(g, met_);
            }
        }
        for (StandardMethod m: _type.getMethods().values()) {
            MethodId id_ = m.getId();
            String ret_ = m.getImportedReturnType();
            AccessEnum acc_ = m.getAccess();
            String decl_ = m.getDeclaringType();
            MethodMetaInfo met_ = new MethodMetaInfo(acc_,decl_, id_, m.getModifier(), ret_, id_, ret_, decl_);
            infos_.put(id_, met_);
        }
        for (StandardConstructor d: _type.getConstructors()) {
            existCtor_ = true;
            ConstructorId id_ = d.getGenericId();
            AccessEnum acc_ = d.getAccess();
            String decl_ = d.getDeclaringType();
            String ret_ = d.getImportedReturnType();
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, acc_, id_, ret_, id_, ret_, decl_);
            infosConst_.put(id_, met_);
        }
        if (!existCtor_) {
            ConstructorId id_ = new ConstructorId(_name, new StringList(), false);
            AccessEnum acc_ = _type.getAccess();
            String formatRet_;
            ConstructorId fid_;
            String ret_ = getStandards().getAliasVoid();
            String formCl_ = _name;
            if (Templates.correctNbParameters(_name, this)) {
                formatRet_ = Templates.wildCardFormat(false, _name, ret_, this,true);
                fid_ = id_.reflectFormat(_name, this);
            } else {
                formatRet_ = ret_;
                fid_ = id_;
            }
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, acc_, id_, ret_, fid_, formatRet_,formCl_);
            infosConst_.put(id_, met_);
        }
        AccessEnum acc_ = _type.getAccess();
        boolean st_ = _type.isStaticType();
        if (_type instanceof StandardInterface) {
            return new ClassMetaInfo(_name, ((StandardInterface)_type).getDirectInterfaces(), "",inners_,infosFields_,infos_, infosConst_, ClassCategory.INTERFACE,st_,acc_);
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        if (_type instanceof StandardInterface) {
            cat_ = ClassCategory.INTERFACE;
        }
        boolean abs_ = _type.isAbstractType();
        boolean final_ = _type.isFinalType();
        String superClass_ = ((StandardClass) _type).getSuperClass(this);
        StringList superInterfaces_ = ((StandardClass) _type).getDirectInterfaces();
        return new ClassMetaInfo(_name, superClass_, superInterfaces_, "",inners_,infosFields_,infos_, infosConst_, cat_, abs_, st_, final_,acc_);
    }
    @Override
    public CustList<GeneType> getClassBodies() {
        CustList<GeneType> types_ = new CustList<GeneType>();
        for (StandardType t: standards.getStandards().values()) {
            types_.add(t);
        }
        for (RootBlock t: classes.getClassBodies()) {
            types_.add(t);
        }
        return types_;
    }
    @Override
    public CustList<GeneMethod> getMethodBodiesById(String _genericClassName, MethodId _id) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        GeneType r_ = getClassBody(base_);
        if (!classes.isCustomType(_genericClassName)) {
            for (EntryCust<MethodId, StandardMethod> m: ((StandardType)r_).getMethods().entryList()) {
                if (m.getKey().eq(_id)) {
                    methods_.add(m.getValue());
                    break;
                }
            }
            return methods_;
        }
        for (GeneMethod m: Classes.getMethodBlocks((RootBlock) r_)) {
            if (m.getId().eq(_id)) {
                methods_.add(m);
                break;
            }
        }
        return methods_;
    }
    public static CustList<GeneConstructor> getConstructorBlocks(GeneType _element) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        if (_element instanceof RootBlock) {
            for (Block b: Classes.getDirectChildren((RootBlock)_element)) {
                if (b instanceof ConstructorBlock) {
                    methods_.add((ConstructorBlock) b);
                }
            }
        } else {
            for (StandardConstructor m: ((StandardType)_element).getConstructors()) {
                methods_.add(m);
            }
        }
        return methods_;
    }
    public static CustList<GeneMethod> getMethodBlocks(GeneType _element) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        if (_element instanceof RootBlock) {
            for (Block b: Classes.getDirectChildren((RootBlock)_element)) {
                if (b instanceof MethodBlock) {
                    methods_.add((GeneMethod) b);
                }
                if (b instanceof AnnotationMethodBlock) {
                    methods_.add((GeneMethod) b);
                }
            }
        } else {
            for (StandardMethod m: ((StandardType)_element).getMethods().values()) {
                methods_.add(m);
            }
        }
        return methods_;
    }
    public static CustList<GeneField> getFieldBlocks(GeneType _element) {
        CustList<GeneField> methods_ = new CustList<GeneField>();
        if (_element instanceof RootBlock) {
            for (Block b: Classes.getDirectChildren((RootBlock)_element)) {
                if (b instanceof InfoBlock) {
                    methods_.add((InfoBlock) b);
                }
            }
        } else {
            for (StandardField m: ((StandardType)_element).getFields().values()) {
                methods_.add(m);
            }
        }
        return methods_;
    }
    @Override
    public GeneType getClassBody(String _type) {
        if (classes.isCustomType(_type)) {
            return classes.getClassBody(_type);
        }
        return standards.getStandards().getVal(_type);
    }

    @Override
    public Options getOptions() {
        return options;
    }

    public void setOptions(Options _options) {
        options = _options;
    }

    public void setStackOverFlow(int _stackOverFlow) {
        stackOverFlow = _stackOverFlow;
    }

    public MathFactory getMathFactory() {
        return mathFactory;
    }

    public void setMathFactory(MathFactory _mathFactory) {
        mathFactory = _mathFactory;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public String getFilesConfName() {
        return filesConfName;
    }

    public void setFilesConfName(String _filesConfName) {
        filesConfName = _filesConfName;
    }

    @Override
    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes _classes) {
        classes = _classes;
    }
    public void clearPages() {
        importing.clear();
    }
    public boolean isEmptyPages() {
        return importing.isEmpty();
    }

    public AbstractPageEl getCall(int _index) {
        return importing.get(_index);
    }
    public int nbPages() {
        return importing.size();
    }

    public void removeLastPage() {
        importing.removeLast();
    }

    public void addPage(AbstractPageEl _page) {
        LgNames stds_ = getStandards();
        String sof_ = stds_.getAliasSof();
        if (stackOverFlow >= CustList.FIRST_INDEX && stackOverFlow <= importing.size()) {
            exception = new ErrorStruct(this,sof_);
        } else {
            importing.add(_page);
        }
    }

    @Override
    public String getCurrentFileName() {
        if (analyzing.getCurrentBlock() == null) {
            return null;
        }
        return analyzing.getCurrentBlock().getFile().getFileName();
    }
    @Override
    public Block getCurrentBlock() {
        return analyzing.getCurrentBlock();
    }

    @Override
    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public void setAnalyzing(AnalyzedPageEl _analyzing) {
        analyzing = _analyzing;
    }

    public String getNextTempVar() {
        return analyzing.getNextTempVar(classes);
    }

    public AbstractPageEl getLastPage() {
        return importing.last();
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String _html) {
        html = _html;
    }

    @Override
    public boolean isAmbigous() {
        return analyzing.isAmbigous();
    }

    @Override
    public void setAmbigous(boolean _ambigous) {
        analyzing.setAmbigous(_ambigous);
    }

    @Override
    public LgNames getStandards() {
        return standards;
    }

    public void setStandards(LgNames _standards) {
        standards = _standards;
    }

    @Override
    public String getGlobalClass() {
        return analyzing.getGlobalClass();
    }

    @Override
    public void setGlobalClass(String _globalClass) {
        analyzing.setGlobalClass(_globalClass);
    }

    @Override
    public LoopVariable getVar(String _key) {
        return analyzing.getVar(_key);
    }

    @Override
    public boolean containsLocalVar(String _string) {
        return analyzing.containsLocalVar(_string);
    }

    @Override
    public LocalVariable getLocalVar(String _string) {
        return analyzing.getLocalVar(_string);
    }

    @Override
    public void putLocalVar(String _string, LocalVariable _loc) {
        analyzing.putLocalVar(_string, _loc);
    }

    @Override
    public LocalVariable getCatchVar(String _key) {
        return analyzing.getCatchVar(_key);
    }

    @Override
    public StringMap<LocalVariable> getParameters() {
        return analyzing.getParameters();
    }

    @Override
    public int getOffset() {
        return analyzing.getOffset();
    }

    @Override
    public void setAnalyzedOffset(int _offset) {
        analyzing.setOffset(_offset);
    }

    @Override
    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }

    @Override
    public boolean isStaticContext() {
        return analyzing.isStaticContext();
    }

    @Override
    public void setStaticContext(boolean _staticContext) {
        analyzing.setStaticContext(_staticContext);
    }

    @Override
    public boolean isGearConst() {
        return analyzing.isGearConst();
    }

    public void setGearConst(boolean _gearConst) {
        analyzing.setGearConst(_gearConst);
    }

    public boolean callsOrException() {
        if (calls()) {
            return true;
        }
        if (failInit) {
            return true;
        }
        return exception != null;
    }

    public boolean calls() {
        if (callMethod != null) {
            return true;
        }
        if (reflectMethod != null) {
            return true;
        }
        if (callCtor != null) {
            return true;
        }
        if (callAnnot != null) {
            return true;
        }
        if (initClass != null) {
            return true;
        }
        return false;
    }
    @Override
    public Struct getException() {
        return exception;
    }
    public boolean hasExceptionOrFailInit() {
        if (isFailInit()) {
            return true;
        }
        return hasException();
    }
    public boolean hasException() {
        return exception != null;
    }
    public boolean isFailInit() {
        return failInit;
    }

    @Override
    public void setException(Struct _exception) {
        exception = _exception;
    }
    public LocalThrowing getThrowing() {
        return throwing;
    }
    
    public CustomFoundConstructor getCallCtor() {
        return callCtor;
    }
    
    public void setCallCtor(CustomFoundConstructor _callCtor) {
        callCtor = _callCtor;
    }

    public CustomFoundAnnotation getCallAnnot() {
        return callAnnot;
    }

    public void setCallAnnot(CustomFoundAnnotation _callAnnot) {
        callAnnot = _callAnnot;
    }

    public CustomFoundMethod getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(CustomFoundMethod _callMethod) {
        callMethod = _callMethod;
    }

    public CustomReflectMethod getReflectMethod() {
        return reflectMethod;
    }

    public void setReflectMethod(CustomReflectMethod _reflectMethod) {
        reflectMethod = _reflectMethod;
    }

    public NotInitializedClass getInitClass() {
        return initClass;
    }

    public void setInitClass(NotInitializedClass _initClass) {
        initClass = _initClass;
    }

    public NotInitializedFields getInitFields() {
        return initFields;
    }

    public void setInitFields(NotInitializedFields _initFields) {
        initFields = _initFields;
    }

    public CustomFoundBlock getFoundBlock() {
        return foundBlock;
    }

    public void setFoundBlock(CustomFoundBlock _foundBlock) {
        foundBlock = _foundBlock;
    }

    public Struct getMemoryError() {
        return memoryError;
    }

    public Initializer getInit() {
        return init;
    }

    public void setInit(Initializer _init) {
        init = _init;
    }

    @Override
    public int getCurrentChildTypeIndex() {
        return analyzing.getIndexChildType();
    }

    @Override
    public void setCurrentChildTypeIndex(int _index) {
        analyzing.setIndexChildType(_index);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public boolean isMerged() {
        return analyzing.isMerged();
    }

    @Override
    public void setMerged(boolean _merged) {
        analyzing.setMerged(_merged);
    }

    @Override
    public String getCurrentVarSetting() {
        return analyzing.getCurrentVarSetting();
    }

    @Override
    public void setCurrentVarSetting(String _currentVarSetting) {
        analyzing.setCurrentVarSetting(_currentVarSetting);
    }

    @Override
    public boolean isFinalVariable() {
        return analyzing.isFinalVariable();
    }

    @Override
    public void setFinalVariable(boolean _finalVariable) {
        analyzing.setFinalVariable(_finalVariable);
    }

    @Override
    public AssignedVariablesBlock getAssignedVariables() {
        return analyzing.getAssignedVariables();
    }

    @Override
    public CustList<OperationNode> getTextualSortedOperations() {
        return analyzing.getTextualSortedOperations();
    }

    @Override
    public CustList<StringMap<LocalVariable>> getLocalVariables() {
        return analyzing.getLocalVars();
    }

    @Override
    public LocalVariable getLocalVar(String _key, int _index) {
        return analyzing.getLocalVar(_key, _index);
    }

    @Override
    public PageEl getOperationPageEl() {
        return getLastPage();
    }

    @Override
    public ContextEl getContextEl() {
        return this;
    }

    @Override
    public StringList getNeedInterfaces() {
        return analyzing.getNeedInterfaces();
    }

    @Override
    public StringMap<LocalVariable> getInternVars() {
        return analyzing.getInternVars();
    }


    @Override
    public boolean isEnabledInternVars() {
        return analyzing.isEnabledInternVars();
    }

    @Override
    public boolean isInternGlobal() {
        return false;
    }

    @Override
    public Struct getInternGlobal() {
        return null;
    }

    @Override
    public String getInternGlobalClass() {
        return null;
    }
    @Override
    public String resolveAccessibleIdType(String _in) {
        Block bl_ = getCurrentBlock();
        int rc_ = getCurrentLocationIndex();
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            un_.setType(_in);
            classes.addError(un_);
            return EMPTY_TYPE;
        }
        AccessingImportingBlock r_ = bl_.getImporting();
        StringList inners_;
        if (options.isSingleInnerParts()) {
            inners_ = Templates.getAllInnerTypesSingleDotted(_in, this);
        } else {
            inners_ = Templates.getAllInnerTypes(_in);
        }
        String base_ = inners_.first().trim();
        String res_ = removeDottedSpaces(base_);
        if (standards.getStandards().contains(res_)) {
            return res_;
        }
        RootBlock b_ = classes.getClassBody(res_);
        if (b_ == null) {
            String id_ = lookupImportType(base_, r_);
            if (id_.isEmpty()) {
                UnknownClassName undef_;
                undef_ = new UnknownClassName();
                undef_.setClassName(base_);
                undef_.setFileName(r_.getFile().getFileName());
                undef_.setIndexFile(rc_);
                classes.addError(undef_);
                return EMPTY_TYPE;
            }
            res_ = id_;
            b_ = classes.getClassBody(id_);
        }
        for (String i: inners_.mid(1)) {
            String resId_ = StringList.concat(res_,"..",i.trim());
            RootBlock inner_ = classes.getClassBody(resId_);
            if (inner_ == null) {
                //ERROR
                UnknownClassName undef_;
                undef_ = new UnknownClassName();
                undef_.setClassName(base_);
                undef_.setFileName(r_.getFile().getFileName());
                undef_.setIndexFile(rc_);
                classes.addError(undef_);
                return EMPTY_TYPE;
            }
            res_ = resId_;
        }
        return res_;
    }
    /**Used at analyzing instructions*/
    @Override
    public String resolveCorrectType(String _in) {
        return resolveCorrectType(_in, true);
    }
    @Override
    public String resolveCorrectAccessibleType(String _in, String _fromType) {
        Block bl_ = getCurrentBlock();
        int rc_ = getCurrentLocationIndex();
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            un_.setType(_in);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        AccessingImportingBlock r_ = bl_.getImporting();
        StringList varsList_ = new StringList();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        String idFromType_ = Templates.getIdFromAllTypes(_fromType);
        RootBlock from_ = classes.getClassBody(idFromType_);
        if (from_ == null) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            un_.setType(_in);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        for (TypeVar t: from_.getParamTypesMapValues()) {
            varsList_.add(t.getName());
            vars_.put(t.getName(), t.getConstraints());
        }
        getAvailableVariables().clear();
        getAvailableVariables().addAllElts(varsList_);
        setDirectImport(false);
        String resType_ = PartTypeUtil.processAnalyzeAccessibleId(_in, this, r_);
        if (resType_.trim().isEmpty()) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(_in);
            un_.setFileName(r_.getFile().getFileName());
            un_.setIndexFile(rc_);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        if (!Templates.isCorrectTemplateAll(resType_, vars_, this, true)) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(_in);
            un_.setFileName(r_.getFile().getFileName());
            un_.setIndexFile(rc_);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        return resType_;
    }
    /**Used at analyzing instructions*/
    @Override
    public String resolveCorrectType(String _in, boolean _exact) {
        Block bl_ = getCurrentBlock();
        int rc_ = getCurrentLocationIndex();
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            un_.setType(_in);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        AccessingImportingBlock r_ = bl_.getImporting();
        StringList varsList_ = new StringList();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        
        boolean static_;
        if (bl_ instanceof InfoBlock) {
            static_ = ((InfoBlock)bl_).isStaticField();
        } else {
            FunctionBlock fct_ = bl_.getFunction();
            if (fct_ == null) {
                static_ = true;
            } else {
                static_ = fct_.isStaticContext();
            }
        }
        if (!static_) {
            for (TypeVar t: r_.getParamTypesMapValues()) {
                varsList_.add(t.getName());
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        getAvailableVariables().clear();
        getAvailableVariables().addAllElts(varsList_);
        setDirectImport(false);
        String gl_ = getGlobalClass();
        String resType_ = PartTypeUtil.processAnalyze(_in, gl_, this, r_, _exact);
        if (resType_.trim().isEmpty()) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(_in);
            un_.setFileName(r_.getFile().getFileName());
            un_.setIndexFile(rc_);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        if (!Templates.isCorrectTemplateAll(resType_, vars_, this, _exact)) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(_in);
            un_.setFileName(r_.getFile().getFileName());
            un_.setIndexFile(rc_);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        return resType_;
    }
    @Override
    public StringMap<StringList> getCurrentConstraints() {
        Block bl_ = getCurrentBlock();
        AccessingImportingBlock r_ = bl_.getImporting();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        
        boolean static_;
        if (bl_ instanceof InfoBlock) {
            static_ = ((InfoBlock)bl_).isStaticField();
        } else {
            FunctionBlock fct_ = bl_.getFunction();
            if (fct_ == null) {
                static_ = true;
            } else {
                static_ = fct_.isStaticContext();
            }
        }
        if (!static_) {
            for (TypeVar t: r_.getParamTypesMapValues()) {
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        return vars_;
    }
    /**Used at analyzing instructions*/
    @Override
    public String resolveCorrectTypeWithoutErrors(String _in, boolean _exact) {
        if (isEnabledInternVars()) {
            return EMPTY_TYPE;
        }
        Block bl_ = getCurrentBlock();
        if (bl_ == null) {
            String tr_ = removeDottedSpaces(_in);
            GeneType g_ = getClassBody(tr_);
            if (g_ != null) {
                return tr_;
            }
            return EMPTY_TYPE;
        }
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            return EMPTY_TYPE;
        }
        AccessingImportingBlock r_ = bl_.getImporting();
        StringList varsList_ = new StringList();
        StringMap<StringList> vars_ = new StringMap<StringList>();

        boolean static_;
        if (bl_ instanceof InfoBlock) {
            static_ = ((InfoBlock)bl_).isStaticField();
        } else {
            FunctionBlock fct_ = bl_.getFunction();
            if (fct_ == null) {
                static_ = true;
            } else {
                static_ = fct_.isStaticContext();
            }
        }
        if (!static_) {
            for (TypeVar t: r_.getParamTypesMapValues()) {
                varsList_.add(t.getName());
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        getAvailableVariables().clear();
        getAvailableVariables().addAllElts(varsList_);
        setDirectImport(false);
        String gl_ = getGlobalClass();
        String resType_ = PartTypeUtil.processAnalyze(_in, gl_, this, r_, _exact);
        if (resType_.trim().isEmpty()) {
            return EMPTY_TYPE;
        }
        if (!Templates.isCorrectTemplateAll(resType_, vars_, this, _exact)) {
            return EMPTY_TYPE;
        }
        return resType_;
    }

    public StringList getSortedTypes(boolean _predefined) {
        SortedGraph<ClassInheritsDeps> gr_;
        gr_ = new SortedGraph<ClassInheritsDeps>();
        EqList<ClassInheritsDeps> absDeps_ = new EqList<ClassInheritsDeps>();
        for (RootBlock b: classes.getClassBodies()) {
            if (b.getFile().isPredefined() != _predefined) {
                continue;
            }
            StringList deps_ = b.getDepends(this);
            if (deps_ == null) {
                return null;
            }
            String c_ = b.getFullName();
            if (deps_.isEmpty()) {
                absDeps_.add(new ClassInheritsDeps(c_));
            }
            for (String d: deps_) {
                gr_.addSegment(new ClassInheritsDeps(c_), new ClassInheritsDeps(d));
            }
        }
        EqList<ClassInheritsDeps> cycle_ = gr_.elementsCycle();
        if (!cycle_.isEmpty()) {
            for (ClassInheritsDeps c: cycle_) {
                BadInheritedClass enum_;
                enum_ = new BadInheritedClass();
                enum_.setClassName(c.getClassField());
                enum_.setFileName(c.getClassField());
                classes.addError(enum_);
            }
            return null;
        }
        EqList<ClassInheritsDeps> sort_;
        sort_ = new EqList<ClassInheritsDeps>();
        sort_.addAllElts(absDeps_);
        for (ClassInheritsDeps e: gr_.process()) {
            if (!sort_.containsObj(e)) {
                sort_.add(e);
            }
        }
        StringList sortTypes_ = new StringList();
        for (ClassInheritsDeps e: sort_) {
            sortTypes_.add(e.getClassField());
        }
        return sortTypes_;
    }
    /**Used at building mapping constraints*/
    public String resolveTypeMapping(String _in, RootBlock _currentBlock,
            int _location) {
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            un_.setType(_in);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        StringList variables_ = new StringList();
        for (RootBlock r: _currentBlock.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.add(t.getName());
            }
        }
        //No need to call Templates.isCorrect
        getAvailableVariables().clear();
        getAvailableVariables().addAllElts(variables_);
        setDirectImport(false);
        String gl_ = _currentBlock.getGenericString();
        String resType_ = PartTypeUtil.processAnalyze(_in, gl_, this, _currentBlock, true);
        if (resType_.trim().isEmpty()) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(_in);
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        return resType_;
    }
    /**Used at building mapping constraints*/
    public String resolveTypeInherits(String _in, RootBlock _currentBlock,
            int _location, int _index) {
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            un_.setType(_in);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        StringList variables_ = new StringList();
        for (RootBlock r: _currentBlock.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.add(t.getName());
            }
        }
        //No need to call Templates.isCorrect
        getAvailableVariables().clear();
        getAvailableVariables().addAllElts(variables_);
        setDirectImport(false);
        String gl_ = _currentBlock.getGenericString();
        String resType_ = PartTypeUtil.processAnalyzeInherits(_in, _index, gl_, this, _currentBlock, true, false);
        if (resType_.trim().isEmpty()) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(_in);
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            classes.addError(un_);
            return standards.getAliasObject();
        }
        for (String p:Templates.getAllTypes(resType_).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(resType_);
                call_.setFileName(_currentBlock.getFile().getFileName());
                call_.setIndexFile(_location);
                classes.addError(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(resType_);
                call_.setFileName(_currentBlock.getFile().getFileName());
                call_.setIndexFile(_location);
                classes.addError(call_);
            }
        }
        return resType_;
    }

    @Override
    public String lookupImportMemberType(String _type, AccessingImportingBlock _rooted, boolean _inherits) {
        String prefixedType_;
        if (options.isSingleInnerParts()) {
            prefixedType_ = getSinglePrefixedMemberType(_type, _rooted);
        } else {
            prefixedType_ = getPrefixedMemberType(_type, _rooted);
        }
        if (prefixedType_.isEmpty()) {
            return EMPTY_TYPE;
        }
        String trQual_ = prefixedType_.trim();
        String typeFound_ = trQual_;
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        boolean stQualifier_ = startsWithKeyWord(trQual_, keyWordStatic_);
        if (stQualifier_) {
            typeFound_ = typeFound_.substring(keyWordStatic_.length()).trim();
        }
        StringList inners_;
        inners_ = Templates.getAllInnerTypes(typeFound_);
        String res_ = inners_.first();
        String fullName_;
        if (_rooted instanceof RootBlock) {
            fullName_ = ((RootBlock)_rooted).getFullName();
        } else {
            fullName_ = null;
        }
        int index_ = 1;
        int max_ = inners_.size() - 1;
        boolean incProt_ = !_inherits;
        for (String i: inners_.mid(1)) {
            String i_ = i.trim();
            StringList builtInners_ = TypeUtil.getOwners(_inherits, incProt_ || index_ == inners_.size() - 1, fullName_,res_, i_, stQualifier_ || index_ < max_, this);
            if (builtInners_.size() == 1) {
                res_ = StringList.concat(builtInners_.first(),"..",i_);
                index_++;
                continue;
            }
            return EMPTY_TYPE;
        }
        return res_;
    }
    @Override
    public TypeOwnersDepends lookupImportMemberTypeDeps(String _type,
            AccessingImportingBlock _rooted) {
        String prefixedType_;
        if (options.isSingleInnerParts()) {
            prefixedType_ = getSinglePrefixedMemberType(_type, _rooted);
        } else {
            prefixedType_ = getPrefixedMemberType(_type, _rooted);
        }
        TypeOwnersDepends out_ = new TypeOwnersDepends();
        if (prefixedType_.isEmpty()) {
            return out_;
        }
        String trQual_ = prefixedType_.trim();
        String typeFound_ = trQual_;
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        boolean stQualifier_ = startsWithKeyWord(trQual_, keyWordStatic_);
        if (stQualifier_) {
            typeFound_ = typeFound_.substring(keyWordStatic_.length()).trim();
        }
        StringList inners_ = Templates.getAllInnerTypes(typeFound_);
        String res_ = inners_.first();
        String fullName_;
        if (_rooted instanceof RootBlock) {
            fullName_ = ((RootBlock)_rooted).getFullName();
        } else {
            fullName_ = null;
        }
        int index_ = 1;
        int max_ = inners_.size() - 1;
        for (String i: inners_.mid(1)) {
            String i_ = i.trim();
            TypeOwnersDepends ownersDeps_ = TypeUtil.getOwnersDepends(index_ == max_, fullName_, res_, i_, this);
            out_.getDepends().addAllElts(ownersDeps_.getDepends());
            StringList owners_ = ownersDeps_.getTypeOwners();
            if (owners_.size() == 1) {
                res_ = StringList.concat(owners_.first(),"..",i_);
                index_++;
                continue;
            }
            return out_;
        }
        out_.getTypeOwners().add(res_);
        return out_;
    }
    public String resolveBaseInherits(String _idSup, RootBlock _root, int _index, StringList _readyTypes, boolean _static) {
        String id_ = Templates.getIdFromAllTypes(_idSup);
        boolean single_ = options.isSingleInnerParts();
        StringList inners_;
        if (single_) {
            inners_ = Templates.getAllInnerTypesSingleDotted(id_, this);
        } else {
            inners_ = Templates.getAllInnerTypes(id_);
        }
        String base_ = inners_.first().trim();
        if (base_.isEmpty()) {
            if (inners_.size() == 1) {
                return null;
            }
            return localSolve(inners_, 1, _root, _index, _readyTypes, _static);
        }
        String joined_ = removeDottedSpaces(inners_.join(".."));
        boolean outer_ = inners_.size() == 1;
        RootBlock b_ = classes.getClassBody(joined_);
        if (b_ != null) {
            if (!access(_root, b_, outer_)) {
                return null;
            }
            if (!_readyTypes.containsStr(joined_) && _static) {
                return EMPTY_TYPE;
            }
            return joined_;
        }
        if (single_) {
            return localSolve(inners_, 0, _root, _index, _readyTypes, _static);
        }
        String res_ = removeDottedSpaces(lookupImportType(base_, _root));
        if (res_.isEmpty()) {
            return null;
        }
        b_ = classes.getClassBody(res_);
        if (!access(_root, b_, outer_)) {
            return null;
        }
        return getOtherParts(inners_,res_,0,_root, _readyTypes, _static, false);
    }

    private static boolean access(RootBlock _from, RootBlock _found, boolean _outer) {
        if (_found.getAccess().ordinal() > AccessEnum.PROTECTED.ordinal()) {
            if (_found.getAccess() == AccessEnum.PACKAGE) {
                if (!StringList.quickEq(_found.getPackageName(), _from.getPackageName())) {
                    return false;
                }
            } else {
                return false;
            }
        } else if (_found.getAccess() == AccessEnum.PROTECTED){
            if (!_outer) {
                if (!StringList.quickEq(_found.getPackageName(), _from.getPackageName())) {
                    return false;
                }
            }
        }
        return true;
    }

    private String localSolve(StringList _inners, int _first, RootBlock _root,
            int _index, StringList _readyTypes, boolean _static) {
        String fullName_ = _root.getFullName();
        String baseInn_ = _inners.get(_first).trim();
        RootBlock gType_ = classes.getClassBody(baseInn_);
        String res_;
        if (gType_ == null) {
            String name_ = EMPTY_TYPE;
            CustList<RootBlock> allAncestors_ = new CustList<RootBlock> ();
            RootBlock p_ = _root.getParentType();
            while (p_ != null) {
                allAncestors_.add(p_);
                p_ = p_.getParentType();
            }
            for (RootBlock a: allAncestors_) {
                String id_ = a.getFullName();
                if (!_readyTypes.containsStr(id_) && _static) {
                    return EMPTY_TYPE;
                }
            }
            int indexAncestor_ = 0;
            for (RootBlock a: allAncestors_) {
                String id_ = a.getFullName();
                StringList builtInners_ = TypeUtil.getBuiltInners(_inners.size() == _first + 1,fullName_, id_, baseInn_, _static, this);
                if (builtInners_.size() == 1) {
                    _root.getAncestorsIndexes().set(_index, indexAncestor_);
                    name_ = builtInners_.first();
                    break;
                }
                indexAncestor_++;
            }
            if (name_.isEmpty()) {
                String member_ = lookupImportMemberTypes(baseInn_, _root, _readyTypes);
                if (member_ == null) {
                    return null;
                }
                res_ = member_;
            } else {
                res_ = name_;
            }
        } else {
            res_ = gType_.getFullName();
        }
        return getOtherParts(_inners,res_,_first,_root, _readyTypes, _static, false);
    }

    private String lookupImportMemberTypes(String _type, RootBlock _root,
            StringList _readyTypes) {
        boolean single_ = options.isSingleInnerParts();
        String prefix_;
        if (single_) {
            prefix_ = getSinglePrefixedMemberType(_type, _root);
        } else {
            prefix_ = getPrefixedMemberType(_type, _root);
        }
        String trQual_ = removeDottedSpaces(prefix_);
        if (trQual_.isEmpty()) {
            return null;
        }
        String typeFound_ = trQual_;
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        boolean stQual_ = startsWithKeyWord(trQual_, keyWordStatic_);
        if (stQual_) {
            typeFound_ = typeFound_.substring(keyWordStatic_.length()).trim();
        }
        StringList inners_ = Templates.getAllInnerTypes(typeFound_);
        String res_ = inners_.first().trim();
        if (classes.getClassBody(res_) == null) {
            return null;
        }
        return getOtherParts(inners_,res_,0,_root, _readyTypes, null, false);
    }

    private String getOtherParts(StringList _inners, String _res, int _first,
            RootBlock _root, StringList _readyTypes, Boolean _static, boolean _qualifier) {
        String out_ = _res;
        String fullName_ = _root.getFullName();
        int index_ = _first + 1;
        int max_ = _inners.size() - 1;
        for (String i : _inners.mid(1 + _first)) {
            boolean staticLoc_ = _qualifier || index_ < max_;
            String name_ = i.trim();
            if (_static != null) {
                staticLoc_ = _static;
            }
            if (!_readyTypes.containsStr(out_) && staticLoc_) {
                return EMPTY_TYPE;
            }
            StringList built_ = TypeUtil.getBuiltInners(index_ + 1 == _inners.size(), fullName_, out_, name_, staticLoc_, this);
            if (built_.size() == 1) {
                out_ = built_.first();
                index_++;
                continue;
            }
            return null;
        }
        if (!_readyTypes.containsStr(out_)) {
            if (_static != null) {
                if (_static) {
                    return EMPTY_TYPE;
                }
            } else if (_qualifier){
                return EMPTY_TYPE;
            }
        }
        return out_;
    }

    public static String getPrefixedMemberType(String _type, AccessingImportingBlock _rooted) {
        String look_ = _type.trim();
        StringList types_ = new StringList();
        CustList<StringList> imports_ = new CustList<StringList>();
        CustList<AccessingImportingBlock> roots_ = new CustList<AccessingImportingBlock>();
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            imports_.add(r_.getImports());
            roots_.add(r_);
            for (RootBlock r: r_.getAllParentTypes()) {
                imports_.add(r.getImports());
                roots_.add(r);
            }
        } else {
            imports_.add(_rooted.getImports());
            roots_.add(_rooted);
        }
        for (StringList s: imports_) {
            for (String i: s) {
                if (!i.contains("..")) {
                    continue;
                }
                String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf("..")+2));
                String end_ = removeDottedSpaces(i.substring(i.lastIndexOf("..")+2));
                if (!StringList.quickEq(end_, look_)) {
                    continue;
                }
                String typeLoc_ = removeDottedSpaces(StringList.concat(begin_, look_));
                types_.add(typeLoc_);
            }
            if (types_.size() == 1) {
                return types_.first();
            }
            types_.clear();
        }
        for (String i: _rooted.getFile().getImports()) {
            if (!i.contains("..")) {
                continue;
            }
            String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf("..")+2));
            String end_ = removeDottedSpaces(i.substring(i.lastIndexOf("..")+2));
            if (!StringList.quickEq(end_, look_)) {
                continue;
            }
            String typeLoc_ = removeDottedSpaces(StringList.concat(begin_, look_));
            types_.add(typeLoc_);
        }
        if (types_.size() == 1) {
            return types_.first();
        }
        types_.clear();
        for (StringList s: imports_) {
            for (String i: s) {
                if (!i.contains("..")) {
                    continue;
                }
                String end_ = removeDottedSpaces(i.substring(i.lastIndexOf("..")+2));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf("..")));
                String typeLoc_ = StringList.concat(begin_,"..",look_);
                types_.add(typeLoc_);
            }
            if (types_.size() == 1) {
                return types_.first();
            }
            types_.clear();
        }
        for (String i: _rooted.getFile().getImports()) {
            if (!i.contains("..")) {
                continue;
            }
            String end_ = removeDottedSpaces(i.substring(i.lastIndexOf("..")+2));
            if (!StringList.quickEq(end_, "*")) {
                continue;
            }
            String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf("..")));
            String typeLoc_ = StringList.concat(begin_,"..",look_);
            types_.add(typeLoc_);
        }
        if (types_.size() == 1) {
            return types_.first();
        }
        return EMPTY_TYPE;
    }
    public String getSinglePrefixedMemberType(String _type, AccessingImportingBlock _rooted) {
        String look_ = _type.trim();
        StringList types_ = new StringList();
        CustList<StringList> imports_ = new CustList<StringList>();
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        CustList<AccessingImportingBlock> roots_ = new CustList<AccessingImportingBlock>();
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            imports_.add(r_.getImports());
            roots_.add(r_);
            for (RootBlock r: r_.getAllParentTypes()) {
                imports_.add(r.getImports());
                roots_.add(r);
            }
        } else {
            imports_.add(_rooted.getImports());
            roots_.add(_rooted);
        }
        for (StringList s: imports_) {
            for (String i: s) {
                if (!i.contains(".")) {
                    continue;
                }
                String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf(".")+1));
                String end_ = removeDottedSpaces(i.substring(i.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, look_)) {
                    continue;
                }
                String beginImp_ = begin_;
                String pre_ = EMPTY_PREFIX;
                if (startsWithKeyWord(begin_, keyWordStatic_)) {
                    beginImp_ = beginImp_.substring(keyWordStatic_.length()).trim();
                    pre_ = keyWordStatic_;
                }
                String typeInner_ = StringList.concat(beginImp_, look_);
                String foundCandidate_ = Templates.getAllInnerTypesSingleDotted(typeInner_, this).join("..");
                String typeLoc_ = removeDottedSpaces(StringList.concat(pre_," ", foundCandidate_));
                types_.add(typeLoc_);
            }
            if (types_.size() == 1) {
                return types_.first();
            }
            types_.clear();
        }
        for (String i: _rooted.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf(".")+1));
            String end_ = removeDottedSpaces(i.substring(i.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, look_)) {
                continue;
            }
            String beginImp_ = begin_;
            String pre_ = EMPTY_PREFIX;
            if (startsWithKeyWord(begin_, keyWordStatic_)) {
                beginImp_ = beginImp_.substring(keyWordStatic_.length()).trim();
                pre_ = keyWordStatic_;
            }
            String typeInner_ = StringList.concat(beginImp_, look_);
            String foundCandidate_ = Templates.getAllInnerTypesSingleDotted(typeInner_, this).join("..");
            String typeLoc_ = removeDottedSpaces(StringList.concat(pre_," ", foundCandidate_));
            types_.add(typeLoc_);
        }
        if (types_.size() == 1) {
            return types_.first();
        }
        types_.clear();
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            String type_ = removeDottedSpaces(StringList.concat(r_.getPackageName(),".",_type));
            if (classes.isCustomType(type_)) {
                return type_;
            }
        }
        for (StringList s: imports_) {
            for (String i: s) {
                if (!i.contains(".")) {
                    continue;
                }
                String end_ = removeDottedSpaces(i.substring(i.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf(".")));
                String beginImp_ = begin_;
                String pre_ = EMPTY_PREFIX;
                if (startsWithKeyWord(begin_, keyWordStatic_)) {
                    beginImp_ = beginImp_.substring(keyWordStatic_.length()).trim();
                    pre_ = keyWordStatic_;
                }
                String typeInner_ = StringList.concat(beginImp_, look_);
                String foundCandidate_ = Templates.getAllInnerTypesSingleDotted(typeInner_, this).join("..");
                String typeLoc_ = removeDottedSpaces(StringList.concat(pre_," ", foundCandidate_));
                types_.add(typeLoc_);
            }
            if (types_.size() == 1) {
                return types_.first();
            }
            types_.clear();
        }
        for (String i: _rooted.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            String end_ = removeDottedSpaces(i.substring(i.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, "*")) {
                continue;
            }
            String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf(".")));
            String beginImp_ = begin_;
            String pre_ = EMPTY_PREFIX;
            if (startsWithKeyWord(begin_, keyWordStatic_)) {
                beginImp_ = beginImp_.substring(keyWordStatic_.length()).trim();
                pre_ = keyWordStatic_;
            }
            String typeInner_ = StringList.concat(beginImp_, look_);
            String foundCandidate_ = Templates.getAllInnerTypesSingleDotted(typeInner_, this).join("..");
            String typeLoc_ = removeDottedSpaces(StringList.concat(pre_," ", foundCandidate_));
            types_.add(typeLoc_);
        }
        if (types_.size() == 1) {
            return types_.first();
        }
        String defPkg_ = standards.getDefaultPkg();
        String type_ = removeDottedSpaces(StringList.concat(defPkg_,".",_type));
        if (standards.getStandards().contains(type_) || classes.isCustomType(type_)) {
            return type_;
        }
        return EMPTY_TYPE;
    }
    @Override
    public String lookupImportType(String _type, AccessingImportingBlock _rooted) {
        String look_ = _type.trim();
        StringList types_ = new StringList();
        CustList<StringList> imports_ = new CustList<StringList>();
        CustList<AccessingImportingBlock> roots_ = new CustList<AccessingImportingBlock>();
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            imports_.add(r_.getImports());
            roots_.add(r_);
            for (RootBlock r: r_.getAllParentTypes()) {
                imports_.add(r.getImports());
                roots_.add(r);
            }
        } else {
            imports_.add(_rooted.getImports());
            roots_.add(_rooted);
        }
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        for (StringList s: imports_) {
            for (String i: s) {
                if (!i.contains(".")) {
                    continue;
                }
                if (startsWithKeyWord(i, keyWordStatic_)) {
                    continue;
                }
                String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf(".")+1));
                String end_ = removeDottedSpaces(i.substring(i.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, look_)) {
                    continue;
                }
                String typeLoc_ = removeDottedSpaces(StringList.concat(begin_, look_));
                if (!classes.isCustomType(typeLoc_)) {
                    continue;
                }
                types_.add(typeLoc_);
            }
            if (types_.size() == 1) {
                return types_.first();
            }
            types_.clear();
        }
        for (String i: _rooted.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            if (startsWithKeyWord(i, keyWordStatic_)) {
                continue;
            }
            String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf(".")+1));
            String end_ = removeDottedSpaces(i.substring(i.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, look_)) {
                continue;
            }
            String typeLoc_ = removeDottedSpaces(StringList.concat(begin_, look_));
            if (!classes.isCustomType(typeLoc_)) {
                continue;
            }
            types_.add(typeLoc_);
        }
        if (types_.size() == 1) {
            return types_.first();
        }
        types_.clear();
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            String type_ = removeDottedSpaces(StringList.concat(r_.getPackageName(),".",_type));
            if (classes.isCustomType(type_)) {
                return type_;
            }
        }
        for (StringList s: imports_) {
            for (String i: s) {
                if (!i.contains(".")) {
                    continue;
                }
                if (startsWithKeyWord(i, keyWordStatic_)) {
                    continue;
                }
                String end_ = removeDottedSpaces(i.substring(i.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf(".")));
                String typeLoc_ = StringList.concat(begin_,".",look_);
                if (!classes.isCustomType(typeLoc_)) {
                    continue;
                }
                types_.add(typeLoc_);
            }
            if (types_.size() == 1) {
                return types_.first();
            }
            types_.clear();
        }
        for (String i: _rooted.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            if (startsWithKeyWord(i, keyWordStatic_)) {
                continue;
            }
            String end_ = removeDottedSpaces(i.substring(i.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, "*")) {
                continue;
            }
            String begin_ = removeDottedSpaces(i.substring(0, i.lastIndexOf(".")));
            String typeLoc_ = StringList.concat(begin_,".",look_);
            if (!classes.isCustomType(typeLoc_)) {
                continue;
            }
            types_.add(typeLoc_);
        }
        if (types_.size() == 1) {
            return types_.first();
        }
        String defPkg_ = standards.getDefaultPkg();
        String type_ = removeDottedSpaces(StringList.concat(defPkg_,".",_type));
        if (standards.getStandards().contains(type_) || classes.isCustomType(type_)) {
            return type_;
        }
        return EMPTY_TYPE;
    }
    @Override
    public String lookupSingleImportType(String _type,
            AccessingImportingBlock _rooted) {
        String look_ = _type.trim();
        StringList types_ = new StringList();
        CustList<StringList> imports_ = new CustList<StringList>();
        CustList<AccessingImportingBlock> roots_ = new CustList<AccessingImportingBlock>();
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            imports_.add(r_.getImports());
            roots_.add(r_);
            for (RootBlock r: r_.getAllParentTypes()) {
                imports_.add(r.getImports());
                roots_.add(r);
            }
        } else {
            imports_.add(_rooted.getImports());
            roots_.add(_rooted);
        }
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        for (StringList s: imports_) {
            for (String i: s) {
                if (!i.contains(".")) {
                    continue;
                }
                String typeImp_ = i;
                if (startsWithKeyWord(i, keyWordStatic_)) {
                    typeImp_ = i.substring(keyWordStatic_.length()).trim();
                }
                String begin_ = removeDottedSpaces(typeImp_.substring(0, typeImp_.lastIndexOf(".")+1));
                String end_ = removeDottedSpaces(typeImp_.substring(typeImp_.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, look_)) {
                    continue;
                }
                String typeLoc_ = removeDottedSpaces(StringList.concat(begin_, look_));
                if (!classes.isCustomType(typeLoc_)) {
                    continue;
                }
                types_.add(typeLoc_);
            }
            if (types_.size() == 1) {
                return types_.first();
            }
            types_.clear();
        }
        for (String i: _rooted.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            String typeImp_ = i;
            if (startsWithKeyWord(i, keyWordStatic_)) {
                typeImp_ = i.substring(keyWordStatic_.length()).trim();
            }
            String begin_ = removeDottedSpaces(typeImp_.substring(0, typeImp_.lastIndexOf(".")+1));
            String end_ = removeDottedSpaces(typeImp_.substring(typeImp_.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, look_)) {
                continue;
            }
            String typeLoc_ = removeDottedSpaces(StringList.concat(begin_, look_));
            if (!classes.isCustomType(typeLoc_)) {
                continue;
            }
            types_.add(typeLoc_);
        }
        if (types_.size() == 1) {
            return types_.first();
        }
        types_.clear();
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            String type_ = removeDottedSpaces(StringList.concat(r_.getPackageName(),".",_type));
            if (classes.isCustomType(type_)) {
                return type_;
            }
        }
        for (StringList s: imports_) {
            for (String i: s) {
                if (!i.contains(".")) {
                    continue;
                }
                String typeImp_ = i;
                if (startsWithKeyWord(i, keyWordStatic_)) {
                    typeImp_ = i.substring(keyWordStatic_.length()).trim();
                }
                String end_ = removeDottedSpaces(typeImp_.substring(typeImp_.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String begin_ = removeDottedSpaces(typeImp_.substring(0, typeImp_.lastIndexOf(".")));
                String typeLoc_ = StringList.concat(begin_,".",look_);
                if (!classes.isCustomType(typeLoc_)) {
                    continue;
                }
                types_.add(typeLoc_);
            }
            if (types_.size() == 1) {
                return types_.first();
            }
            types_.clear();
        }
        for (String i: _rooted.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            String typeImp_ = i;
            if (startsWithKeyWord(i, keyWordStatic_)) {
                typeImp_ = i.substring(keyWordStatic_.length()).trim();
            }
            String end_ = removeDottedSpaces(typeImp_.substring(typeImp_.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, "*")) {
                continue;
            }
            String begin_ = removeDottedSpaces(typeImp_.substring(0, typeImp_.lastIndexOf(".")));
            String typeLoc_ = StringList.concat(begin_,".",look_);
            if (!classes.isCustomType(typeLoc_)) {
                continue;
            }
            types_.add(typeLoc_);
        }
        if (types_.size() == 1) {
            return types_.first();
        }
        String defPkg_ = standards.getDefaultPkg();
        String type_ = removeDottedSpaces(StringList.concat(defPkg_,".",_type));
        if (standards.getStandards().contains(type_) || classes.isCustomType(type_)) {
            return type_;
        }
        return EMPTY_TYPE;
    }
    @Override
    public ObjectMap<ClassMethodId,Integer> lookupImportStaticMethods(String _glClass,String _method, Block _rooted) {
        ObjectMap<ClassMethodId,Integer> methods_ = new ObjectMap<ClassMethodId,Integer>();
        if (!StringList.isWord(_method.trim())) {
            return methods_;
        }
        AccessingImportingBlock type_ = _rooted.getImporting();
        CustList<StringList> imports_ = new CustList<StringList>();
        if (type_ instanceof RootBlock) {
            RootBlock r_ = (RootBlock) type_;
            imports_.add(r_.getImports());
            for (RootBlock r: r_.getAllParentTypes()) {
                imports_.add(r.getImports());
            }
        } else {
            imports_.add(type_.getImports());
        }
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        int import_ = 1;
        for (StringList t: imports_) {
            for (String i: t) {
                if (!i.contains(".")) {
                    continue;
                }
                if (!startsWithKeyWord(i.trim(), keyWordStatic_)) {
                    continue;
                }
                String st_ = i.trim().substring(keyWordStatic_.length()).trim();
                String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf(".")));
                if (!classes.isCustomType(typeLoc_)) {
                    if (!standards.getStandards().contains(typeLoc_)) {
                        continue;
                    }
                }
                String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, _method.trim())) {
                    continue;
                }
                GeneType root_ = getClassBody(typeLoc_);
                StringList typesLoc_ = new StringList(typeLoc_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                for (String s: typesLoc_) {
                    GeneType super_ = getClassBody(s);
                    for (GeneMethod e: ContextEl.getMethodBlocks(super_)) {
                        if (!e.isStaticMethod()) {
                            continue;
                        }
                        if (!StringList.quickEq(end_, e.getId().getName())) {
                            continue;
                        }
                        if (!Classes.canAccess(typeLoc_, e, this)) {
                            continue;
                        }
                        if (!Classes.canAccess(_glClass, e, this)) {
                            continue;
                        }
                        methods_.add(new ClassMethodId(s, e.getId()),import_);
                    }
                }
            }
            import_++;
        }
        for (String i: type_.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            if (!startsWithKeyWord(i.trim(), keyWordStatic_)) {
                continue;
            }
            String st_ = i.trim().substring(keyWordStatic_.length()).trim();
            String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf(".")));
            if (!classes.isCustomType(typeLoc_)) {
                if (!standards.getStandards().contains(typeLoc_)) {
                    continue;
                }
            }
            String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, _method.trim())) {
                continue;
            }
            GeneType root_ = getClassBody(typeLoc_);
            StringList typesLoc_ = new StringList(typeLoc_);
            typesLoc_.addAllElts(root_.getAllSuperTypes());
            for (String s: typesLoc_) {
                GeneType super_ = getClassBody(s);
                for (GeneMethod e: ContextEl.getMethodBlocks(super_)) {
                    if (!e.isStaticMethod()) {
                        continue;
                    }
                    if (!StringList.quickEq(end_, e.getId().getName())) {
                        continue;
                    }
                    if (!Classes.canAccess(typeLoc_, e, this)) {
                        continue;
                    }
                    if (!Classes.canAccess(_glClass, e, this)) {
                        continue;
                    }
                    ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                    methods_.add(clMet_, import_);
                }
            }
        }
        import_++;
        for (StringList t: imports_) {
            for (String i: t) {
                if (!i.contains(".")) {
                    continue;
                }
                if (!startsWithKeyWord(i.trim(), keyWordStatic_)) {
                    continue;
                }
                String st_ = i.trim().substring(keyWordStatic_.length()).trim();
                String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf(".")));
                if (!classes.isCustomType(typeLoc_)) {
                    if (!standards.getStandards().contains(typeLoc_)) {
                        continue;
                    }
                }
                GeneType root_ = getClassBody(typeLoc_);
                StringList typesLoc_ = new StringList(typeLoc_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                for (String s: typesLoc_) {
                    GeneType super_ = getClassBody(s);
                    for (GeneMethod e: ContextEl.getMethodBlocks(super_)) {
                        if (!e.isStaticMethod()) {
                            continue;
                        }
                        if (!StringList.quickEq(_method.trim(), e.getId().getName())) {
                            continue;
                        }
                        if (!Classes.canAccess(typeLoc_, e, this)) {
                            continue;
                        }
                        if (!Classes.canAccess(_glClass, e, this)) {
                            continue;
                        }
                        ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                        methods_.add(clMet_, import_);
                    }
                }
            }
            import_++;
        }
        for (String i: type_.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            if (!startsWithKeyWord(i.trim(), keyWordStatic_)) {
                continue;
            }
            String st_ = i.trim().substring(keyWordStatic_.length()).trim();
            String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, "*")) {
                continue;
            }
            String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf(".")));
            if (!classes.isCustomType(typeLoc_)) {
                if (!standards.getStandards().contains(typeLoc_)) {
                    continue;
                }
            }
            GeneType root_ = getClassBody(typeLoc_);
            StringList typesLoc_ = new StringList(typeLoc_);
            typesLoc_.addAllElts(root_.getAllSuperTypes());
            for (String s: typesLoc_) {
                GeneType super_ = getClassBody(s);
                for (GeneMethod e: ContextEl.getMethodBlocks(super_)) {
                    if (!e.isStaticMethod()) {
                        continue;
                    }
                    if (!StringList.quickEq(_method.trim(), e.getId().getName())) {
                        continue;
                    }
                    if (!Classes.canAccess(typeLoc_, e, this)) {
                        continue;
                    }
                    if (!Classes.canAccess(_glClass, e, this)) {
                        continue;
                    }
                    ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                    methods_.add(clMet_, import_);
                }
            }
        }
        return methods_;
    }

    @Override
    public ObjectMap<ClassField,Integer> lookupImportStaticFields(String _glClass,String _method, Block _rooted) {
        ObjectMap<ClassField,Integer> methods_ = new ObjectMap<ClassField,Integer>();
        if (!StringList.isWord(_method.trim())) {
            return methods_;
        }
        int import_ = 1;
        AccessingImportingBlock type_ = _rooted.getImporting();
        CustList<StringList> imports_ = new CustList<StringList>();
        if (type_ instanceof RootBlock) {
            RootBlock r_ = (RootBlock) type_;
            imports_.add(r_.getImports());
            for (RootBlock r: r_.getAllParentTypes()) {
                imports_.add(r.getImports());
            }
        } else {
            imports_.add(type_.getImports());
        }
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        for (StringList t: imports_) {
            for (String i: t) {
                if (!i.contains(".")) {
                    continue;
                }
                if (!startsWithKeyWord(i.trim(), keyWordStatic_)) {
                    continue;
                }
                String st_ = i.trim().substring(keyWordStatic_.length()).trim();
                String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf(".")));
                if (!classes.isCustomType(typeLoc_)) {
                    if (!standards.getStandards().contains(typeLoc_)) {
                        continue;
                    }
                }
                String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, _method.trim())) {
                    continue;
                }
                GeneType root_ = getClassBody(typeLoc_);
                StringList typesLoc_ = new StringList(typeLoc_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                for (String s: typesLoc_) {
                    GeneType super_ = getClassBody(s);
                    for (GeneField e: ContextEl.getFieldBlocks(super_)) {
                        if (!e.isStaticField()) {
                            continue;
                        }
                        if (!e.getFieldName().containsStr(end_)) {
                            continue;
                        }
                        if (!Classes.canAccess(typeLoc_, e, this)) {
                            continue;
                        }
                        if (!Classes.canAccess(_glClass, e, this)) {
                            continue;
                        }
                        methods_.add(new ClassField(s, _method),import_);
                    }
                }
            }
            import_++;
        }
        for (String i: type_.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            if (!startsWithKeyWord(i.trim(), keyWordStatic_)) {
                continue;
            }
            String st_ = i.trim().substring(keyWordStatic_.length()).trim();
            String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf(".")));
            if (!classes.isCustomType(typeLoc_)) {
                if (!standards.getStandards().contains(typeLoc_)) {
                    continue;
                }
            }
            String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, _method.trim())) {
                continue;
            }
            GeneType root_ = getClassBody(typeLoc_);
            StringList typesLoc_ = new StringList(typeLoc_);
            typesLoc_.addAllElts(root_.getAllSuperTypes());
            for (String s: typesLoc_) {
                GeneType super_ = getClassBody(s);
                for (GeneField e: ContextEl.getFieldBlocks(super_)) {
                    if (!e.isStaticField()) {
                        continue;
                    }
                    if (!e.getFieldName().containsStr(end_)) {
                        continue;
                    }
                    if (!Classes.canAccess(typeLoc_, e, this)) {
                        continue;
                    }
                    if (!Classes.canAccess(_glClass, e, this)) {
                        continue;
                    }
                    ClassField field_ = new ClassField(s, _method);
                    methods_.add(field_, import_);
                }
            }
        }
        for (StringList t: imports_) {
            for (String i: t) {
                if (!i.contains(".")) {
                    continue;
                }
                if (!startsWithKeyWord(i.trim(), keyWordStatic_)) {
                    continue;
                }
                String st_ = i.trim().substring(keyWordStatic_.length()).trim();
                String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf(".")+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf(".")));
                if (!classes.isCustomType(typeLoc_)) {
                    if (!standards.getStandards().contains(typeLoc_)) {
                        continue;
                    }
                }
                GeneType root_ = getClassBody(typeLoc_);
                StringList typesLoc_ = new StringList(typeLoc_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                for (String s: typesLoc_) {
                    GeneType super_ = getClassBody(s);
                    for (GeneField e: ContextEl.getFieldBlocks(super_)) {
                        if (!e.isStaticField()) {
                            continue;
                        }
                        if (!e.getFieldName().containsStr(_method.trim())) {
                            continue;
                        }
                        if (!Classes.canAccess(typeLoc_, e, this)) {
                            continue;
                        }
                        if (!Classes.canAccess(_glClass, e, this)) {
                            continue;
                        }
                        ClassField field_ = new ClassField(s, _method);
                        methods_.add(field_, import_);
                    }
                }
            }
            import_++;
        }
        for (String i: type_.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            if (!startsWithKeyWord(i.trim(), keyWordStatic_)) {
                continue;
            }
            String st_ = i.trim().substring(keyWordStatic_.length()).trim();
            String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, "*")) {
                continue;
            }
            String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf(".")));
            if (!classes.isCustomType(typeLoc_)) {
                if (!standards.getStandards().contains(typeLoc_)) {
                    continue;
                }
            }
            GeneType root_ = getClassBody(typeLoc_);
            StringList typesLoc_ = new StringList(typeLoc_);
            typesLoc_.addAllElts(root_.getAllSuperTypes());
            for (String s: typesLoc_) {
                GeneType super_ = getClassBody(s);
                for (GeneField e: ContextEl.getFieldBlocks(super_)) {
                    if (!e.isStaticField()) {
                        continue;
                    }
                    if (!e.getFieldName().containsStr(_method.trim())) {
                        continue;
                    }
                    if (!Classes.canAccess(typeLoc_, e, this)) {
                        continue;
                    }
                    if (!Classes.canAccess(_glClass, e, this)) {
                        continue;
                    }
                    ClassField field_ = new ClassField(s, _method);
                    methods_.add(field_, import_);
                }
            }
        }
        return methods_;
    }

    public static boolean isDigit(char _char) {
        return _char >= '0' && _char <= '9';
    }

    public static String removeDottedSpaces(String _type) {
        StringBuilder b_ = new StringBuilder();
        for (String q: StringList.splitCharsSep(_type, Templates.SEP_CLASS_CHAR)) {
            b_.append(q.trim());
        }
        return b_.toString();
    }

    @Override
    public int getGlobalOffset() {
        return analyzing.getGlobalOffset();
    }

    public ClassMetaInfo getExtendedClassMetaInfo(String _name, String _variableOwner) {
        if (StringList.quickEq(_name, Templates.SUB_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, this, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.SUB_TYPE)) {
            StringList upperBounds_ = new StringList(_name.substring(Templates.SUB_TYPE.length()));
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, this, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.SUP_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList(_name.substring(Templates.SUB_TYPE.length()));
            return new ClassMetaInfo(_name, this, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.PREFIX_VAR_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, this, ClassCategory.VARIABLE,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.ARR_BEG_STRING)) {
            return new ClassMetaInfo(_name, this, ClassCategory.ARRAY, _variableOwner);
        }
        return getExtendedClassMetaInfo(_name);
    }
    @Override
    public ClassMetaInfo getExtendedClassMetaInfo(String _name) {
        if (StringList.quickEq(_name.trim(), getStandards().getAliasVoid())) {
            return new ClassMetaInfo(_name, this, ClassCategory.VOID,"");
        }
        if (PrimitiveTypeUtil.isPrimitive(_name, this)) {
            return new ClassMetaInfo(_name, this, ClassCategory.PRIMITIVE,"");
        }
        if (new ClassArgumentMatching(_name).isArray()) {
            return new ClassMetaInfo(_name, this, ClassCategory.ARRAY, "");
        }
        return getClassMetaInfo(_name);
    }

    @Override
    public FieldInfo getFieldInfo(ClassField _classField) {
        GeneType g_ = getClassBody(_classField.getClassName());
        String search_ = _classField.getFieldName();
        if (g_ instanceof RootBlock) {
            for (Block b: Classes.getDirectChildren((Block) g_)) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock) b;
                if (!i_.getFieldName().containsStr(search_)) {
                    continue;
                }
                String type_ = i_.getImportedClassName();
                boolean final_ = i_.isFinalField();
                boolean static_ = i_.isStaticField();
                return FieldInfo.newFieldMetaInfo(search_, g_.getFullName(), type_, static_, final_, i_ instanceof ElementBlock, this);
            }
        } else if (g_ instanceof StandardType) {
            for (EntryCust<String, StandardField> f: ((StandardType)g_).getFields().entryList()) {
                StandardField f_ = f.getValue();
                if (!f_.getFieldName().containsStr(search_)) {
                    continue;
                }
                String type_ = f_.getImportedClassName();
                boolean final_ = f_.isFinalField();
                boolean static_ = f_.isStaticField();
                return FieldInfo.newFieldMetaInfo(search_, g_.getFullName(), type_, static_, final_, false, this);
            }
        }
        return null;
    }
    public static boolean startsWithKeyWord(String _found, String _keyWord) {
        if (!_found.startsWith(_keyWord)) {
            return false;
        }
        String sub_ = _found.substring(_keyWord.length());
        if (sub_.isEmpty()) {
            return true;
        }
        char first_ = sub_.charAt(0);
        return !StringList.isDollarWordChar(first_);
    }

    @Override
    public boolean isDirectImport() {
        return analyzing.isDirectImport();
    }

    @Override
    public void setDirectImport(boolean _directImport) {
        analyzing.setDirectImport(_directImport);
    }

    @Override
    public StringList getAvailableVariables() {
        if (analyzing == null) {
            return new StringList();
        }
        return analyzing.getAvailableVariables();
    }

    @Override
    public StringList getVariablesNames() {
        return analyzing.getVariablesNames();
    }

    @Override
    public boolean isAssignedStaticFields() {
        return analyzing.isAssignedStaticFields();
    }

    @Override
    public void setAssignedStaticFields(boolean _assignedStaticFields) {
        analyzing.setAssignedStaticFields(_assignedStaticFields);
    }

    @Override
    public boolean isAssignedFields() {
        return analyzing.isAssignedFields();
    }

    @Override
    public void setAssignedFields(boolean _assignedFields) {
        analyzing.setAssignedFields(_assignedFields);
    }

    @Override
    public LoopVariable getMutableLoopVar(String _key, int _index) {
        return analyzing.getMutableLoopVar(_key, _index);
    }

    @Override
    public boolean containsMutableLoopVar(String _string) {
        return analyzing.containsMutableLoopVar(_string);
    }

    @Override
    public LoopVariable getMutableLoopVar(String _key) {
        return analyzing.getMutableLoopVar(_key);
    }

    @Override
    public void putMutableLoopVar(String _string, LoopVariable _loc) {
        analyzing.putMutableLoopVar(_string, _loc);
    }

    @Override
    public ForLoopPart getForLoopPartState() {
        return analyzing.getForLoopPartState();
    }

    @Override
    public void setForLoopPartState(ForLoopPart _state) {
        analyzing.setForLoopPartState(_state);
    }

    @Override
    public AnalyzingEl getAnalysisAss() {
        return analyzing.getAnalysisAss();
    }

    @Override
    public boolean isAnnotAnalysis() {
        return analyzing.isAnnotAnalysis();
    }

    @Override
    public void setAnnotAnalysis(boolean _ana) {
        analyzing.setAnnotAnalysis(_ana);
    }

    @Override
    public void putLocalVar(String _string) {
        analyzing.putLocalVar(_string);
    }

    @Override
    public StringList getInfersLocalVars() {
        return analyzing.getLastLocalVarsInfers();
    }

    @Override
    public StringList getInfersMutableLocalVars() {
        return analyzing.getLastMutableLoopVarsInfers();
    }

    @Override
    public void putMutableLoopVar(String _string) {
        analyzing.putMutableLoopVar(_string);
    }

    @Override
    public String getLookLocalClass() {
        return analyzing.getLookLocalClass();
    }

    @Override
    public void setLookLocalClass(String _lookLocalClass) {
        analyzing.setLookLocalClass(_lookLocalClass);
    }

    @Override
    public SortedClassField getCurrentInitizedField() {
        return analyzing.getCurrentInitizedField();
    }

    @Override
    public void setCurrentInitizedField(SortedClassField _currentInitizedField) {
        analyzing.setCurrentInitizedField(_currentInitizedField);
    }

    @Override
    public boolean isOkNumOp() {
        return analyzing.isOkNumOp();
    }

    @Override
    public void setOkNumOp(boolean _okNumOp) {
        analyzing.setOkNumOp(_okNumOp);
    }

    @Override
    public Numbers<Integer> getCurrentBadIndexes() {
        return analyzing.getCurrentBadIndexes();
    }

    @Override
    public int getCurrentLocationIndex() {
        return analyzing.getTraceIndex();
    }

    @Override
    public KeyWords getKeyWords() {
        return keyWords;
    }

    @Override
    public void setKeyWords(KeyWords _keyWords) {
        keyWords = _keyWords;
    }
}
