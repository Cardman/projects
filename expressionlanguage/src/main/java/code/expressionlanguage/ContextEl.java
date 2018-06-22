package code.expressionlanguage;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.CustomFoundBlock;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.CustomReflectMethod;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.methods.InitBlock;
import code.expressionlanguage.methods.InstanceBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.NotInitializedFields;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.ReflectingType;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.StaticBlock;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.methods.util.CallConstructor;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.LocalThrowing;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.EnumerableStruct;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.FieldableStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.ElementOffsetsNext;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.MathFactory;

public final class ContextEl implements FieldableStruct, EnumerableStruct,Runnable,ExecutableCode {

    private static final String RETURN_LINE = "\n";
    private static final int DEFAULT_TAB_WIDTH = 4;

    private MathFactory mathFactory;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private String filesConfName;

    private int stackOverFlow;

    private Options options;

    private Struct exception;

    private Struct memoryError;

    private LocalThrowing throwing = new LocalThrowing();

    private CustomFoundConstructor callCtor;

    private NotInitializedFields initFields;
    private CustomFoundBlock foundBlock;

    private CustomFoundMethod callMethod;

    private CustomReflectMethod reflectMethod;

    private NotInitializedClass initClass;

    private transient LgNames standards;

    private transient AnalyzedPageEl analyzing;

    private transient ElementOffsetsNext elements;

    private transient Classes classes;

    private transient CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private transient String html;

    private transient ContextEl parentThread;

    private transient CustList<ContextEl> children = new CustList<ContextEl>();

    private transient Struct parent;

    private transient String className = "";
    private transient ObjectMap<ClassField, Struct> fields = new ObjectMap<ClassField, Struct>();
    private transient Initializer init;
    private transient String name;
    private transient int ordinal;

    public ContextEl() {
        this(CustList.INDEX_NOT_FOUND_ELT);
    }

    public ContextEl(int _stackOverFlow) {
        this(_stackOverFlow, new DefaultLockingClass(),new DefaultInitializer(), new Options());
    }
    public ContextEl(DefaultLockingClass _lock,Initializer _init, Options _options) {
        this(CustList.INDEX_NOT_FOUND_ELT, _lock,_init, _options);
    }

    public ContextEl(int _stackOverFlow, DefaultLockingClass _lock,Initializer _init, Options _options) {
        options = _options;
        stackOverFlow = _stackOverFlow;
        init = _init;
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
        _context.children.add(this);
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
        } else if (callMethod != null) {
            return createCallingMethod(callMethod);
        } else if (reflectMethod != null) {
            return createReflectMethod(reflectMethod);
        } else if (initClass != null) {
            return createInstancingClass(initClass);
        } else if (initFields != null) {
            return createInitFields(initFields.getClassName(), initFields.getCurrentObject());
        } else if (foundBlock != null) {
            return createBlockPageEl(foundBlock.getClassName(), foundBlock.getCurrentObject(), foundBlock.getBlock());
        } else if (exception != null) {
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
                } else {
                    return null;
                }
            } else {
                return true;
            }
        }
        return false;
    }
    private AbstractPageEl createInstancingClass(NotInitializedClass _e) {
        return createInstancingClass(_e.getClassName());
    }
    public AbstractPageEl createInstancingClass(String _class) {
        setInitClass(null);
        classes.preInitializeStaticFields(_class, this);
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
        MethodBlock methodLoc_ = Classes.getMethodBodiesById(this, _class, id_).first();
        StringList paramsLoc_ = methodLoc_.getParametersNames();
        StringList typesLoc_ = methodLoc_.getParametersTypes(this);
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
        CustList<GeneConstructor> methods_ = Classes.getConstructorBodiesById(this, _class, id_);
        ConstructorBlock method_ = null;
        Argument argGl_ = new Argument();
        page_ = new NewInstancingPageEl();
        Struct str_ = null;
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
            StringList types_ = method_.getParametersTypes(this);
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
        return page_;
    }
    public AbstractPageEl createInstancing(String _class, CallConstructor _call, InstancingStep _in,CustList<Argument> _args) {
        setCallCtor(null);
        AbstractPageEl page_;
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
            StringList types_ = method_.getParametersTypes(this);
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
        return page_;
    }
    public BlockPageEl createBlockPageEl(String _class, Argument _current, InitBlock _block) {
        setFoundBlock(null);
        BlockPageEl page_ = new BlockPageEl();
        page_.setTabWidth(tabWidth);
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(_current);
        ReadWrite rw_ = new ReadWrite();
        Block firstChild_ = _block.getFirstChild();
        rw_.setBlock(firstChild_);
        page_.setReadWrite(rw_);
        page_.setBlockRoot(_block);
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
        } else {
            pageLoc_ = new ReflectSetFieldPageEl();
        }
        pageLoc_.setTabWidth(tabWidth);
        pageLoc_.setGlobalArgument(_gl);
        pageLoc_.setArguments(_args);
        ReadWrite rwLoc_ = new ReadWrite();
        pageLoc_.setReadWrite(rwLoc_);
        return pageLoc_;
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
        memoryError = new StdStruct(new CustomError(), standards.getAliasError());
    }
    @Override
    public ClassMetaInfo getClassMetaInfo(String _name) {
        if (!classes.isCustomType(_name)) {
            String base_ = Templates.getIdFromAllTypes(_name);
            LgNames stds_ = getStandards();
            for (EntryCust<String, StandardType> c: stds_.getStandards().entryList()) {
                ObjectNotNullMap<MethodId, MethodMetaInfo> infos_;
                infos_ = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
                StringMap<FieldMetaInfo> infosFields_;
                infosFields_ = new StringMap<FieldMetaInfo>();
                ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> infosConst_;
                infosConst_ = new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>();
                String k_ = c.getKey();
                if (!StringList.quickEq(k_, base_)) {
                    continue;
                }
                StandardType clblock_ = c.getValue();
                for (StandardField f: clblock_.getFields().values()) {
                    String m_ = f.getFieldName();
                    String ret_ = f.getClassName();
                    boolean staticElement_ = f.isStaticField();
                    boolean finalElement_ = f.isFinalField();
                    FieldMetaInfo met_ = new FieldMetaInfo(k_, m_, ret_, staticElement_, finalElement_, false);
                    infosFields_.put(m_, met_);
                }
                for (StandardMethod m: clblock_.getMethods().values()) {
                    MethodId id_ = m.getId();
                    String ret_ = m.getReturnType(this);
                    MethodMetaInfo met_ = new MethodMetaInfo(m.getDeclaringType(), id_, m.getModifier(), ret_);
                    infos_.put(id_, met_);
                }
                for (StandardConstructor d: clblock_.getConstructors()) {
                    ConstructorId id_ = d.getGenericId();
                    ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, id_);
                    infosConst_.put(id_, met_);
                }
                if (clblock_ instanceof StandardInterface) {
                    return new ClassMetaInfo(_name, ((StandardInterface)clblock_).getDirectGenericSuperClasses(this), infosFields_,infos_, infosConst_, ClassCategory.INTERFACE);
                }
                ClassCategory cat_ = ClassCategory.CLASS;
                if (clblock_ instanceof StandardInterface) {
                    cat_ = ClassCategory.INTERFACE;
                }
                boolean abs_ = clblock_.isAbstractType();
                boolean final_ = clblock_.isFinalType();
                return new ClassMetaInfo(_name, ((StandardClass) clblock_).getGenericSuperClass(this), infosFields_,infos_, infosConst_, cat_, abs_, final_);
            }
            return null;
        }
        return classes.getClassMetaInfo(_name, this);
    }
    @Override
    public CustList<GeneType> getClassBodies() {
        CustList<GeneType> types_ = new CustList<GeneType>();
        if (classes == null) {
            for (StandardType t: standards.getStandards().values()) {
                types_.add(t);
            }
            return types_;
        }
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
        if (classes == null || !classes.isCustomType(_genericClassName)) {
            for (EntryCust<MethodId, StandardMethod> m: ((StandardType)r_).getMethods().entryList()) {
                if (m.getKey().eq(_id)) {
                    methods_.add(m.getValue());
                    break;
                }
            }
            return methods_;
        }
        for (MethodBlock m: Classes.getMethodBlocks((RootBlock) r_)) {
            if (m.getId(this).eq(_id)) {
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
                    methods_.add((MethodBlock) b);
                }
            }
        } else {
            for (StandardMethod m: ((StandardType)_element).getMethods().values()) {
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

    public ElementOffsetsNext getElements() {
        return elements;
    }

    public void setElements(ElementOffsetsNext _elements) {
        elements = _elements;
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
            exception = new StdStruct(new CustomError(joinPages()),sof_);
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
    public RowCol getCurrentLocation() {
        return analyzing.getTrace();
    }
    public String getInfos() {
        return analyzing.getInfos(this);
    }

    @Override
    public String joinPages() {
        StringList l_ = new StringList();
        for (AbstractPageEl p: importing) {
            l_.add(p.getInfos(this));
        }
        return l_.join(RETURN_LINE);
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
        if (initClass != null) {
            return true;
        }
        return false;
    }
    @Override
    public Struct getException() {
        return exception;
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
    public boolean isRootAffect() {
        return analyzing.isRootAffect();
    }

    @Override
    public void setRootAffect(boolean _rootAffect) {
        analyzing.setRootAffect(_rootAffect);
    }

    @Override
    public boolean isAnalyzingRoot() {
        return analyzing.isAnalyzingRoot();
    }

    @Override
    public void setAnalyzingRoot(boolean _analyzingRoot) {
        analyzing.setAnalyzingRoot(_analyzingRoot);
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
    public String resolveType(String _in, boolean _correct) {
        Block bl_ = getCurrentBlock();
        RowCol rc_ = getCurrentLocation();
        return resolveType(_in, bl_,rc_, _correct, true, false);
    }
    @Override
    public String resolveDynamicType(String _in, RootBlock _file) {
        StringList parts_ = StringList.splitCharsSep(_in, Templates.LT, Templates.GT, Templates.ARR_BEG, Templates.COMMA, Templates.SEP_BOUNDS, Templates.EXTENDS_DEF);
        StringBuilder str_ = new StringBuilder();
        for (String p: parts_) {
            String tr_ = p.trim();
            if (tr_.isEmpty()) {
                continue;
            }
            if (StringList.quickEq(tr_, Templates.TEMPLATE_BEGIN)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.TEMPLATE_END)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.TEMPLATE_SEP)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.ARR_BEG_STRING)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.SEP_BOUNDS_STRING)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.EXTENDS_DEF_STRING)) {
                str_.append(tr_);
                continue;
            }
            if (tr_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                String n_ = tr_.substring(Templates.PREFIX_VAR_TYPE.length()).trim();
                str_.append(Templates.PREFIX_VAR_TYPE);
                str_.append(n_);
                continue;
            }
            String bs_ = removeDottedSpaces(tr_);
            if (classes.isCustomType(bs_)) {
                str_.append(bs_);
                continue;
            }
            if (standards.getStandards().contains(bs_)) {
                str_.append(bs_);
                continue;
            }
            if (PrimitiveTypeUtil.isPrimitive(bs_, this)) {
                str_.append(bs_);
                continue;
            }
            if (_file == null) {
                str_.append(standards.getAliasObject());
                continue;
            }
            str_.append(lookupImports(bs_, _file.getRooted(), false));
        }
        return str_.toString();
    }
    @Override
    public String resolveType(String _in, Block _currentBlock,RowCol _location, boolean _correct, boolean _checkSimpleCorrect, boolean _checkOnlyExistence) {
        if (_currentBlock == null) {
            return resolveDynamicType(_in, null);
        }
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setRc(_location);
            un_.setType(_in);
            classes.getErrorsDet().add(un_);
            return standards.getAliasObject();
        }
        RootBlock r_ = _currentBlock.getRooted();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        if (_checkSimpleCorrect) {
            if (_correct) {
                boolean static_;
                if (_currentBlock instanceof InfoBlock) {
                    static_ = ((InfoBlock)_currentBlock).isStaticField();
                } else {
                    FunctionBlock fct_ = _currentBlock.getFunction();
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
                if (!Templates.correctClassParts(_in, vars_, this)) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(_in);
                    un_.setFileName(r_.getFile().getFileName());
                    un_.setRc(_location);
                    classes.getErrorsDet().add(un_);
                    return standards.getAliasObject();
                }
            } else if (!Templates.isCorrectWrite(_in, this)) {
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(_in);
                un_.setFileName(r_.getFile().getFileName());
                un_.setRc(_location);
                classes.getErrorsDet().add(un_);
                return standards.getAliasObject();
            }
        } else {
            StringList variables_ = new StringList();
            for (TypeVar t: r_.getParamTypes()) {
                variables_.add(t.getName());
            }
            for (TypeVar t: r_.getParamTypes()) {
                StringList const_ = new StringList();
                for (String c: t.getConstraints()) {
                    if (!Templates.existAllClassParts(c, variables_, this)) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(_in);
                        un_.setFileName(r_.getFile().getFileName());
                        un_.setRc(_location);
                        classes.getErrorsDet().add(un_);
                    }
                    const_.add(c);
                }
                vars_.put(t.getName(), const_);
            }
            if (!Templates.correctClassPartsBuild(_in, vars_, this) && !_checkOnlyExistence) {
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(_in);
                un_.setFileName(r_.getFile().getFileName());
                un_.setRc(_location);
                classes.getErrorsDet().add(un_);
                return standards.getAliasObject();
            }
        }
        String className_ = r_.getFullName();
        StringList parts_ = StringList.splitCharsSep(_in, Templates.LT, Templates.GT, Templates.ARR_BEG, Templates.COMMA, Templates.SEP_BOUNDS, Templates.EXTENDS_DEF);
        StringBuilder str_ = new StringBuilder();
        for (String p: parts_) {
            String tr_ = p.trim();
            if (tr_.isEmpty()) {
                continue;
            }
            if (StringList.quickEq(tr_, Templates.TEMPLATE_BEGIN)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.TEMPLATE_END)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.TEMPLATE_SEP)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.ARR_BEG_STRING)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.SEP_BOUNDS_STRING)) {
                str_.append(tr_);
                continue;
            }
            if (StringList.quickEq(tr_, Templates.EXTENDS_DEF_STRING)) {
                str_.append(tr_);
                continue;
            }
            if (tr_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                String n_ = tr_.substring(Templates.PREFIX_VAR_TYPE.length()).trim();
                str_.append(Templates.PREFIX_VAR_TYPE);
                str_.append(n_);
                continue;
            }
            String bs_ = removeDottedSpaces(tr_);
            if (classes.isCustomType(bs_)) {
                if (!Classes.canAccessClass(className_, bs_, this)) {
                    BadAccessClass err_ = new BadAccessClass();
                    err_.setFileName(r_.getFile().getFileName());
                    err_.setRc(_location);
                    err_.setId(_in);
                    classes.getErrorsDet().add(err_);
                }
            }
            if (classes.isCustomType(bs_)) {
                str_.append(bs_);
                continue;
            }
            if (standards.getStandards().contains(bs_)) {
                str_.append(bs_);
                continue;
            }
            if (PrimitiveTypeUtil.isPrimitive(bs_, this)) {
                str_.append(bs_);
                continue;
            }
            str_.append(lookupImports(bs_, _currentBlock.getRooted(), false));
        }
        return str_.toString();
    }
    @Override
    public String resolveBaseType(String _in, Block _currentBlock,RowCol _location) {
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in, void_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setRc(_location);
            un_.setType(_in);
            classes.getErrorsDet().add(un_);
            return standards.getAliasObject();
        }
        RootBlock r_ = _currentBlock.getRooted();
        RootBlock b_ = classes.getClassBody(_in);
        if (b_ != null) {
            if (b_.getAccess().ordinal() > AccessEnum.PROTECTED.ordinal()) {
                if (b_.getAccess() == AccessEnum.PACKAGE) {
                    if (!StringList.quickEq(b_.getPackageName(), r_.getPackageName())) {
                        BadAccessClass err_ = new BadAccessClass();
                        err_.setFileName(r_.getFile().getFileName());
                        err_.setRc(_location);
                        err_.setId(_in);
                        classes.getErrorsDet().add(err_);
                    }
                } else {
                    BadAccessClass err_ = new BadAccessClass();
                    err_.setFileName(r_.getFile().getFileName());
                    err_.setRc(_location);
                    err_.setId(_in);
                    classes.getErrorsDet().add(err_);
                }
            }
        }
        if (classes.isCustomType(_in)) {
            return _in;
        }
        if (standards.getStandards().contains(_in)) {
            return _in;
        }
        if (PrimitiveTypeUtil.isPrimitive(_in, this)) {
            return _in;
        }
        return lookupImports(_in, _currentBlock.getRooted(), true);
    }
    public String lookupImports(String _type, RootBlock _rooted, boolean _directSuperType) {
        if (!StringList.isWord(_type.trim())) {
            return standards.getAliasObject();
        }
        String type_ = removeDottedSpaces(StringList.concat(_rooted.getPackageName(),".",_type));
        if (classes.isCustomType(type_)) {
            if (!_directSuperType) {
                if (Classes.canAccessClass(_rooted.getFullName(), type_, this)) {
                    return type_;
                }
            } else {
                if (_rooted.isAccessibleType(type_, this)) {
                    return type_;
                }
            }
            
        }
        StringList types_ = new StringList();
        for (String i: _rooted.getFile().getImports()) {
            if (!i.contains(".")) {
                continue;
            }
            String end_ = removeDottedSpaces(i.substring(i.lastIndexOf(".")+1));
            if (!StringList.quickEq(end_, _type.trim())) {
                continue;
            }
            String typeLoc_ = removeDottedSpaces(i);
            if (!StringList.isWord(end_)) {
                continue;
            }
            if (!classes.isCustomType(typeLoc_)) {
                continue;
            }
            if (!_directSuperType) {
                if (Classes.canAccessClass(_rooted.getFullName(), typeLoc_, this)) {
                    types_.add(typeLoc_);
                }
            } else {
                if (_rooted.isAccessibleType(typeLoc_, this)) {
                    types_.add(typeLoc_);
                }
            }
        }
        if (types_.size() != 1) {
            return standards.getAliasObject();
        }
        return types_.first();
    }
    public static String removeDottedSpaces(String _type) {
        StringBuilder b_ = new StringBuilder();
        for (String q: StringList.splitCharsSep(_type, Templates.SEP_CLASS_CHAR)) {
            b_.append(q.trim());
        }
        return b_.toString();
    }
    @Override
    public MethodId getId(GeneMethod _m) {
        if (_m instanceof MethodBlock) {
            return ((MethodBlock)_m).getId(this);
        }
        return ((StandardMethod)_m).getId();
    }

    @Override
    public ConstructorId getId(GeneConstructor _m) {
        if (_m instanceof ConstructorBlock) {
            return ((ConstructorBlock)_m).getId(this);
        }
        return ((StandardConstructor)_m).getId();
    }

    @Override
    public int getGlobalOffset() {
        return analyzing.getGlobalOffset();
    }

    @Override
    public ClassMetaInfo getExtendedClassMetaInfo(String _name) {
        if (StringList.quickEq(_name.trim(), getStandards().getAliasVoid())) {
            return new ClassMetaInfo(_name, this, ClassCategory.VOID);
        }
        if (PrimitiveTypeUtil.isPrimitive(_name, this)) {
            return new ClassMetaInfo(_name, this, ClassCategory.PRIMITIVE);
        }
        if (new ClassArgumentMatching(_name).isArray()) {
            return new ClassMetaInfo(_name, this, ClassCategory.ARRAY);
        }
        return getClassMetaInfo(_name);
    }

    @Override
    public FieldInfo getFieldInfo(ClassField _classField) {
        GeneType g_ = getClassBody(_classField.getClassName());
        if (g_ instanceof RootBlock) {
            for (Block b: Classes.getDirectChildren((Block) g_)) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock) b;
                String name_ = i_.getFieldName();
                if (!StringList.quickEq(name_, _classField.getFieldName())) {
                    continue;
                }
                String type_ = i_.getClassName();
                type_ = resolveType(type_, false);
                boolean final_ = i_.isFinalField();
                boolean static_ = i_.isStaticField();
                return FieldInfo.newFieldInfo(name_, g_.getFullName(), type_, static_, final_, i_ instanceof ElementBlock, this, true);
            }
        } else if (g_ instanceof StandardType) {
            for (EntryCust<String, StandardField> f: ((StandardType)g_).getFields().entryList()) {
                StandardField f_ = f.getValue();
                String name_ = f_.getFieldName();
                if (!StringList.quickEq(name_, _classField.getFieldName())) {
                    continue;
                }
                String type_ = f_.getClassName();
                type_ = resolveType(type_, false);
                boolean final_ = f_.isFinalField();
                boolean static_ = f_.isStaticField();
                return FieldInfo.newFieldInfo(name_, g_.getFullName(), type_, static_, final_, false, this, true);
            }
        }
        return null;
    }
}
