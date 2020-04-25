package code.expressionlanguage;

import code.expressionlanguage.calls.*;
import code.expressionlanguage.calls.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.Coverage;
import code.expressionlanguage.methods.util.LocalThrowing;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.AnnotationInstanceOperation;
import code.expressionlanguage.opers.AssocationOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.types.AlwaysReadyTypes;
import code.expressionlanguage.types.InheritReadyTypes;
import code.expressionlanguage.types.PartTypeUtil;
import code.expressionlanguage.types.ReadyTypes;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.*;

public abstract class ContextEl implements ExecutableCode {

    private static final int DEFAULT_TAB_WIDTH = 4;
    private static final String EMPTY_TYPE = "";

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private int stackOverFlow;

    private Options options;

    private Struct memoryError;

    private LocalThrowing throwing;

    private CallingState callingState;

    private LgNames standards;

    private AnalyzedPageEl analyzing;

    private Classes classes;

    private CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private AnalysisMessages analysisMessages;
    private KeyWords keyWords;
    private InitPhase initEnums = InitPhase.READ_ONLY_OTHERS;
    private boolean failInit;
    private IdList<Struct> sensibleFields = new IdList<Struct>();
    private boolean covering;
    private Coverage coverage;
    private ExecutableCode executingInstance;

    public ContextEl(boolean _covering, int _stackOverFlow,
                     DefaultLockingClass _lock,Options _options,
                     AnalysisMessages _mess,
                     KeyWords _keyWords, LgNames _stds, int _tabWitdth) {
        this();
        setCovering(_covering);
        setOptions(_options);
        setStackOverFlow(_stackOverFlow);
        setStandards(_stds);
        setTabWidth(_tabWitdth);
        setAnalysisMessages(_mess);
        setKeyWords(_keyWords);
        setClasses(new Classes());
        setThrowing(new LocalThrowing());
        setCoverage(new Coverage());
        classes.setLocks(_lock);
    }
    protected ContextEl() {
        setExecutingInstance(this);
    }
    public boolean isSensibleField(String _clName) {
        if (!isInitEnums()) {
            return false;
        }
        String curr_ = getCurInitType();
        return !StringList.quickEq(curr_, _clName);
    }
    public boolean isContainedSensibleFields(Struct _array) {
        if (!isInitEnums()) {
            return false;
        }
        return sensibleFields.containsObj(_array);
    }
    public void addSensibleField(String _fc, Struct _container) {
        if (!isInitEnums()) {
            return;
        }
        if (!isPossibleSensible(_container)) {
            return;
        }
        String curr_ = getCurInitType();
        if (!StringList.quickEq(curr_, _fc)) {
            sensibleFields.add(_container);
        }
    }
    public void addSensibleField(Struct _container, Struct _owned) {
        if (!isInitEnums()) {
            return;
        }
        if (!isPossibleSensible(_owned)) {
            return;
        }
        if (sensibleFields.containsObj(_container)) {
            sensibleFields.add(_owned);
        }
    }
    public void addSensibleElementsFromClonedArray(Struct _array, ArrayStruct _cloned) {
        if (!isInitEnums()) {
            return;
        }
        if (!sensibleFields.containsObj(_array)) {
            return;
        }
        for (Struct s: _cloned.getInstance()) {
            if (!isPossibleSensible(s)) {
                continue;
            }
            sensibleFields.add(s);
        }
    }
    private static boolean isPossibleSensible(Struct _s) {
        if (_s == NullStruct.NULL_VALUE) {
            return false;
        }
        if (_s instanceof BooleanStruct) {
            return false;
        }
        if (_s instanceof NumberStruct) {
            return false;
        }
        if (_s instanceof StringStruct) {
            return false;
        }
        if (_s instanceof ReplacementStruct) {
            return false;
        }
        if (_s instanceof AnnotationStruct) {
            return false;
        }
        return true;
    }
    public void failInitEnums() {
        if (!isInitEnums()) {
            return;
        }
        failInit = true;
    }
    public boolean isInitEnums() {
        return initEnums == InitPhase.READ_ONLY_OTHERS;
    }
    public boolean isWideInitEnums() {
        return initEnums != InitPhase.NOTHING;
    }

    public void setInitEnums(InitPhase _initEnums) {
        initEnums = _initEnums;
    }


    public void resetInitEnums() {
        failInit = false;
        callingState = null;
        sensibleFields.clear();
        clearPages();
    }
    private String getCurInitType() {
        return importing.first().getGlobalClass();
    }
    public void processException() {
        if (!failInit && callingState instanceof Struct) {
            getThrowing().removeBlockFinally(this);
        }
    }
    protected void processTags() {
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
    AbstractPageEl processAfterOperation() {
        if (callingState instanceof CustomFoundConstructor) {
            return createInstancing((CustomFoundConstructor)callingState);
        }
        if (callingState instanceof CustomFoundAnnotation) {
            CustomFoundAnnotation c_ = (CustomFoundAnnotation) callingState;
            return createAnnotation(c_.getClassName(),c_.getType(), c_.getId(), c_.getArguments());
        }
        if (callingState instanceof CustomFoundMethod) {
            return createCallingMethod((CustomFoundMethod)callingState);
        }
        if (callingState instanceof CustomFoundCast) {
            return createCallingCast((CustomFoundCast)callingState);
        }
        if (callingState instanceof CustomReflectMethod) {
            return createReflectMethod((CustomReflectMethod)callingState);
        }
        if (callingState instanceof NotInitializedClass) {
            return createInstancingClass((NotInitializedClass)callingState);
        }
        if (callingState instanceof NotInitializedFields) {
            NotInitializedFields i_ = (NotInitializedFields) callingState;
            return createInitFields(i_.getClassName(), i_.getCurrentObject());
        }
        if (callingState instanceof CustomFoundBlock) {
            CustomFoundBlock b_ = (CustomFoundBlock) callingState;
            return createBlockPageEl(b_.getClassName(), b_.getCurrentObject(), b_.getBlock());
        }
        return null;
    }

    protected EndCallValue removeCall() {
        AbstractPageEl p_ = getLastPage();
        if (p_.getReadWrite() == null) {
            if (p_ instanceof StaticInitPageEl) {
                ((StaticInitPageEl)p_).sucessClass(this);
            }
            removeLastPage();
            if (nbPages() == 0) {
                return EndCallValue.EXIT;
            }
            if (p_ instanceof ForwardPageEl) {
                ((ForwardPageEl)p_).forwardTo(getLastPage(), this);
            }
            if (callsOrException()) {
                return EndCallValue.NEXT;
            }
            return EndCallValue.FORWARD;
        }
        return EndCallValue.NEXT;
    }
    private AbstractPageEl createInstancingClass(NotInitializedClass _e) {
        return createInstancingClass(_e.getClassName());
    }
    public AbstractPageEl createInstancingClass(String _class) {
        setCallingState(null);
        String baseClass_ = Templates.getIdFromAllTypes(_class);
        RootBlock class_ = classes.getClassBody(baseClass_);
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

    private AbstractPageEl createCallingMethod(CustomFoundMethod _e) {
        String cl_ = _e.getClassName();
        MethodId id_ = _e.getId();
        CustList<Argument> args_ = _e.getArguments();
        Argument gl_ = _e.getGl();
        Argument right_ = _e.getRight();
        return createCallingMethod(gl_, cl_, id_, args_, right_);
    }
    public MethodPageEl createCallingMethod(Argument _gl, String _class, MethodId _method, CustList<Argument> _args,Argument _right) {
        setCallingState(null);
        NamedFunctionBlock methodLoc_;
        if (!StringList.isDollarWord(_method.getName()) && !_method.getName().startsWith("[]")) {
            methodLoc_ = Classes.getOperatorsBodiesById(this, _method).first();
            coverage.passCalls(this,"",methodLoc_);
        } else {
            methodLoc_ = Classes.getMethodBodiesById(this, _class, _method).first();
            String idCl_ = Templates.getIdFromAllTypes(_class);
            coverage.passCalls(this,idCl_,methodLoc_);
        }
        String ret_ = methodLoc_.getImportedReturnType();
        MethodPageEl pageLoc_ = new MethodPageEl(this,ret_,_gl,_class,_right);
        setMethodInfos(pageLoc_,methodLoc_, _args);
        return pageLoc_;
    }

    private AbstractPageEl createCallingCast(CustomFoundCast _e) {
        String cl_ = _e.getClassName();
        MethodId id_ = _e.getId();
        CustList<Argument> args_ = _e.getArguments();
        return createCallingCast(cl_, id_, args_);
    }
    public CastPageEl createCallingCast(String _class, MethodId _method, CustList<Argument> _args) {
        setCallingState(null);
        NamedFunctionBlock methodLoc_;
        methodLoc_ = Classes.getMethodBodiesById(this, _class, _method).first();
        String idCl_ = Templates.getIdFromAllTypes(_class);
        coverage.passCalls(this,idCl_,methodLoc_);
        String ret_ = methodLoc_.getImportedReturnType();
        CastPageEl pageLoc_ = new CastPageEl(this,ret_,Argument.createVoid(),_class);
        setMethodInfos(pageLoc_,methodLoc_, _args);
        return pageLoc_;
    }
    private void setMethodInfos(AbstractMethodPageEl _page, NamedFunctionBlock _block, CustList<Argument> _args) {
        StringList paramsLoc_ = _block.getParametersNames();
        int lenLoc_ = paramsLoc_.size();
        for (int i = CustList.FIRST_INDEX; i < lenLoc_; i++) {
            String p_ = paramsLoc_.get(i);
            LocalVariable lv_ = LocalVariable.newLocalVariable(_args.get(i).getStruct(),this);
            _page.getParameters().put(p_, lv_);
        }
        ReadWrite rwLoc_ = new ReadWrite();
        rwLoc_.setBlock(_block.getFirstChild());
        _page.setReadWrite(rwLoc_);
        _page.setBlockRoot(_block);
        _page.setFile(_block.getFile());
    }
    private AbstractPageEl createInstancing(CustomFoundConstructor _e) {
        String cl_ = _e.getClassName();
        CustList<Argument> args_ = _e.getArguments();
        InstancingStep in_ = _e.getInstanceStep();
        if (in_ == InstancingStep.NEWING) {
            return createInstancing(cl_, _e.getType(),_e.getCall(), args_);
        }
        return createForwardingInstancing(cl_, _e.getType(),_e.getCall(), args_);
    }
    public CallConstructorPageEl createInstancing(String _class, RootBlock _type,CallConstructor _call, CustList<Argument> _args) {
        setCallingState(null);
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
        argGl_.setStruct(getInit().processInit(this, str_, _class, fieldName_, ordinal_));
        page_.setGlobalArgument(argGl_);
        setInstanciationInfos(page_,_class,_type,_call,_args);
        return page_;
    }
    private NewAnnotationPageEl createAnnotation(String _class,RootBlock _type,
                                                 StringMap<AnnotationTypeInfo> _id,
                                                 CustList<Argument> _args) {
        setCallingState(null);
        NewAnnotationPageEl page_;
        FileBlock file_ = _type.getFile();
        Argument argGl_ = new Argument();
        page_ = new NewAnnotationPageEl();
        page_.setArgs(_args);
        page_.setNames(_id);
        argGl_.setStruct(getInit().processInitAnnot(this, _class));
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        page_.setReadWrite(rw_);
        page_.setFile(file_);
        return page_;
    }
    private AbstractPageEl createForwardingInstancing(String _class, RootBlock _type,CallConstructor _call, CustList<Argument> _args) {
        setCallingState(null);
        AbstractPageEl page_ = new CallConstructorPageEl();
        Argument global_ = _call.getArgument();
        Argument argGl_ = new Argument();
        argGl_.setStruct(global_.getStruct());
        page_.setGlobalArgument(argGl_);
        setInstanciationInfos(page_,_class,_type,_call,_args);
        return page_;
    }
    private void setInstanciationInfos(AbstractPageEl _page,String _class, RootBlock _type,CallConstructor _call, CustList<Argument> _args) {
        ConstructorId id_ = _call.getId();
        FileBlock file_ = _type.getFile();
        CustList<ConstructorBlock> methods_ = Classes.getConstructorBodiesById(this, _class, id_);
        ConstructorBlock method_ = null;
        _page.setGlobalClass(_class);
        ReadWrite rw_ = new ReadWrite();
        if (!methods_.isEmpty()) {
            String idCl_ = Templates.getIdFromAllTypes(_class);
            method_ = methods_.first();
            coverage.passCalls(this,idCl_,method_);
            StringList params_ = method_.getParametersNames();
            int len_ = params_.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                String p_ = params_.get(i);
                LocalVariable lv_ = LocalVariable.newLocalVariable(_args.get(i).getStruct(),this);
                _page.getParameters().put(p_, lv_);
            }
            Block firstChild_ = method_.getFirstChild();
            rw_.setBlock(firstChild_);
        }
        _page.setReadWrite(rw_);
        _page.setBlockRoot(method_);
        _page.setFile(file_);
    }
    private FieldInitPageEl createInitFields(String _class, Argument _current) {
        setCallingState(null);
        String baseClass_ = Templates.getIdFromAllTypes(_class);
        RootBlock class_ = classes.getClassBody(baseClass_);
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
    private BlockPageEl createBlockPageEl(String _class, Argument _current, InitBlock _block) {
        setCallingState(null);
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
    private AbstractReflectPageEl createReflectMethod(CustomReflectMethod _e) {
        ReflectingType r_ = _e.getReflect();
        CustList<Argument> args_ = _e.getArguments();
        Argument gl_ = _e.getGl();
        boolean l_ = _e.isLambda();
        return createReflectMethod(gl_, args_, r_, l_);
    }
    public AbstractReflectPageEl createReflectMethod(Argument _gl, CustList<Argument> _args, ReflectingType _reflect, boolean _lambda) {
        setCallingState(null);
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

    public abstract void initError();

    private ClassMetaInfo getClassMetaInfo(String _name) {
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
        return classes.getClassMetaInfo(_name, this);
    }
    public final ClassMetaInfo getClassMetaInfo(StandardType _type,String _name) {
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
            String ret_ = getStandards().getAliasVoid();
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

    public static CustList<AnnotationMethodBlock> getAnnotationMethods(GeneType _element) {
        CustList<AnnotationMethodBlock> methods_ = new CustList<AnnotationMethodBlock>();
        for (Block b: Classes.getDirectChildren((RootBlock)_element)) {
            if (b instanceof AnnotationMethodBlock) {
                methods_.add((AnnotationMethodBlock) b);
            }
        }
        return methods_;
    }
    public static CustList<GeneMethod> getMethodBlocks(GeneType _element) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        if (_element == null) {
            return methods_;
        }
        if (_element instanceof RootBlock) {
            for (GeneCustMethod m:Classes.getMethodBlocks((RootBlock) _element)) {
                methods_.add(m);
            }
        } else {
            for (StandardMethod m: ((StandardType)_element).getMethods().values()) {
                methods_.add(m);
            }
        }
        return methods_;
    }

    public static CustList<InfoBlock> getFieldBlocks(RootBlock _element){
        CustList<InfoBlock> methods_ = new CustList<InfoBlock>();
        for (Block b: Classes.getDirectChildren(_element)) {
            if (b instanceof InfoBlock) {
                methods_.add((InfoBlock) b);
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

    public int getStackOverFlow() {
        return stackOverFlow;
    }
    public void setStackOverFlow(int _stackOverFlow) {
        stackOverFlow = _stackOverFlow;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public void addErrorIfNoMatch(String _generic, String _base, Block _currentBlock, int _location) {
        String id_ = Templates.getIdFromAllTypes(_generic);
        if (!StringList.quickEq(id_,_base)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //one full direct super type
            un_.buildError(getAnalysisMessages().getUnknownType(),
                    _generic);
            addError(un_);
        }
    }

    private String getLocationFile(String _fileName, int _sum) {
        FileBlock file_ = classes.getFileBody(_fileName);
        int r_ = file_.getRowFile(_sum);
        int c_ = file_.getColFile(_sum,r_);
        return StringList.concat( Integer.toString(r_),",",Integer.toString(c_),",",Integer.toString(_sum));
    }

    public void addWarning(FoundWarningInterpret _warning) {
        _warning.setLocationFile(getLocationFile(_warning.getFileName(),_warning.getIndexFile()));
        classes.addWarning(_warning);
    }

    public boolean isEmptyErrors() {
        return classes.isEmptyErrors() && classes.isEmptyStdError() && classes.isEmptyMessageError();
    }

    @Override
    public void addError(FoundErrorInterpret _error) {
        _error.setLocationFile(getLocationFile(_error.getFileName(),_error.getIndexFile()));
        classes.addError(_error);
    }
    @Override
    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes _classes) {
        classes = _classes;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public void setCoverage(Coverage _coverage) {
        coverage = _coverage;
    }

    public boolean isCovering() {
        return covering;
    }

    public void setCovering(boolean _covering) {
        covering = _covering;
    }

    private void clearPages() {
        importing.clear();
    }
    public boolean hasPages() {
        return !importing.isEmpty();
    }

    private AbstractPageEl getCall(int _index) {
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
        if (getStackOverFlow() >= CustList.FIRST_INDEX && getStackOverFlow() <= importing.size()) {
            callingState = new ErrorStruct(this,sof_);
        } else {
            importing.add(_page);
        }
    }

    @Override
    public String getCurrentFileName() {
        return analyzing.getCurrentBlock().getFile().getFileName();
    }

    @Override
    public AnalyzedBlock getCurrentAnaBlock() {
        return getCurrentBlock();
    }

    @Override
    public Block getCurrentBlock() {
        return analyzing.getCurrentBlock();
    }

    @Override
    public boolean hasDeclarator() {
        Block bl_ = getCurrentBlock();
        return bl_.getPreviousSibling() instanceof DeclareVariable;
    }

    @Override
    public void setupDeclaratorClass(String _className) {
        Block bl_ = getCurrentBlock();
        Block previousSibling_ = bl_.getPreviousSibling();
        ((DeclareVariable)previousSibling_).setImportedClassName(_className);
    }

    @Override
    public boolean hasLoopDeclarator() {
        Block bl_ = getCurrentBlock();
        return bl_ instanceof ForMutableIterativeLoop;
    }

    @Override
    public void setupLoopDeclaratorClass(String _className) {
        Block bl_ = getCurrentBlock();
        ((ForMutableIterativeLoop)bl_).setImportedClassName(_className);
    }

    @Override
    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public boolean isStaticAccess() {
        if (isAnnotAnalysis()) {
            return true;
        }
        Block bl_ = getCurrentBlock();
        if (bl_ instanceof InfoBlock) {
            return ((InfoBlock)bl_).isStaticField();
        }
        if (bl_ instanceof RootBlock) {
            return ((RootBlock)bl_).isStaticType();
        }
        FunctionBlock fct_ = analyzing.getCurrentFct();
        return fct_.getStaticContext() == MethodAccessKind.STATIC;
    }

    public void setAnalyzing() {
        analyzing = new AnalyzedPageEl();
    }

    public void setNullAnalyzing() {
        analyzing = null;
    }

    public String getNextTempVar() {
        return analyzing.getNextTempVar();
    }

    public AbstractPageEl getLastPage() {
        return importing.last();
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

    public boolean callsOrException() {
        if (callingState != null) {
            return true;
        }
        return isFailInit();
    }

    public boolean calls() {
        return !hasException();
    }

    public boolean hasException() {
        return callingState instanceof Struct;
    }
    public boolean isFailInit() {
        return failInit;
    }

    @Override
    public void setException(Struct _exception) {
        callingState = _exception;
    }

    public LocalThrowing getThrowing() {
        return throwing;
    }
    public void setThrowing(LocalThrowing _throwing) {
        throwing = _throwing;
    }

    public CallingState getCallingState() {
        return callingState;
    }

    public void setCallingState(CallingState _callingState) {
        callingState = _callingState;
    }

    public Struct getMemoryError() {
        return memoryError;
    }

    public void setMemoryError(Struct _memoryError) {
        memoryError = _memoryError;
    }
    public abstract Initializer getInit();

    @Override
    public int getCurrentChildTypeIndex(OperationNode _op, GeneType _type, String _fieldName, String _realClassName) {
        if (isEnumType(_type)) {
            if (_fieldName.isEmpty()) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(getCurrentFileName());
                call_.setIndexFile(getCurrentLocationIndex());
                //type len
                call_.buildError(getAnalysisMessages().getIllegalCtorEnum());
                addError(call_);
                _op.setResultClass(new ClassArgumentMatching(_realClassName));
                return -2;
            }
            return analyzing.getIndexChildType();
        }
        return -1;
    }

    public static boolean isAbstractType(GeneType _type) {
        if (_type instanceof StandardInterface) {
            return true;
        }
        if (_type instanceof RootBlock) {
            return ((RootBlock)_type).isAbstractType();
        }
        return ((StandardClass)_type).isAbstractStdType();
    }
    public static boolean isEnumType(GeneType _type) {
        return _type instanceof EnumBlock || _type instanceof InnerElementBlock;
    }

    public void setCurrentChildTypeIndex(int _index) {
        analyzing.setIndexChildType(_index);
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
    public boolean isAcceptCommaInstr() {
        return analyzing.isAcceptCommaInstr();
    }

    @Override
    public void setAcceptCommaInstr(boolean _merged) {
        analyzing.setAcceptCommaInstr(_merged);
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

    public AssignedVariablesBlock getAssignedVariables() {
        return analyzing.getAssignedVariables();
    }

    @Override
    public CustList<StringMap<LocalVariable>> getLocalVariables() {
        return analyzing.getLocalVars();
    }

    public boolean isFinalLocalVar(String _key, int _index) {
        return analyzing.isFinalLocalVar(_key, _index);
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
    public ExecutableCode getExecutingInstance() {
        return executingInstance;
    }

    public void setExecutingInstance(ExecutableCode _executingInstance) {
        executingInstance = _executingInstance;
    }

    @Override
    public ArrayStruct newStackTraceElementArray() {
        int count_ = nbPages();
        Struct[] arr_ = new Struct[count_];
        for (int i = 0; i < count_; i++) {
            arr_[i] = newStackTraceElement(i);
        }
        String cl_ = getStandards().getAliasStackTraceElement();
        cl_ = PrimitiveTypeUtil.getPrettyArrayType(cl_);
        return new ArrayStruct(arr_, cl_);
    }

    @Override
    public StackTraceElementStruct newStackTraceElement(int _index) {
        AbstractPageEl call_ = getCall(_index);
        int indexFileType = call_.getTraceIndex();
        FileBlock f_ = call_.getFile();
        String fileName;
        int row;
        int col;
        if (f_ != null) {
            fileName = f_.getFileName();
            row = f_.getRowFile(indexFileType);
            col = f_.getColFile(indexFileType,row);
        } else {
            fileName = "";
            row = 0;
            col = 0;
        }
        String currentClassName = call_.getGlobalClass();
        Block bl_ = call_.getBlockRoot();
        if (bl_ != null) {
            FunctionBlock fct_ = bl_.getFunction();
            if (fct_ instanceof ReturnableWithSignature) {
                String signature =((ReturnableWithSignature)fct_).getSignature(this);
                return new StackTraceElementStruct(fileName,row,col,indexFileType,currentClassName,signature);
            }
        }
        String signature = "";
        return new StackTraceElementStruct(fileName,row,col,indexFileType,currentClassName,signature);
    }

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
    public String resolveAccessibleIdType(int _loc,String _in) {
        Block bl_ = getCurrentBlock();
        int rc_ = getCurrentLocationIndex();
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(getAnalysisMessages().getVoidType(),
                    void_);
            addError(un_);
            return EMPTY_TYPE;
        }
        AccessingImportingBlock r_ = analyzing.getImporting();
        StringList inners_ = getParts(_in);
        String firstFull_ = inners_.first();
        int firstOff_ = StringList.getFirstPrintableCharIndex(firstFull_);
        String base_ = firstFull_.trim();
        String res_ = removeDottedSpaces(base_);
        if (standards.getStandards().contains(res_)) {
            return res_;
        }
        CustList<PartOffset> partOffsets_ = coverage.getCurrentParts();
        partOffsets_.clear();
        RootBlock b_ = classes.getClassBody(res_);
        if (b_ == null) {
            String id_ = lookupImportType(base_,r_, new AlwaysReadyTypes());
            if (id_.isEmpty()) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(bl_.getFile().getFileName());
                undef_.setIndexFile(rc_);
                //_in len
                undef_.buildError(getAnalysisMessages().getUnknownType(),
                        _in);
                addError(undef_);
                return EMPTY_TYPE;
            }
            appendParts(firstOff_+_loc,firstOff_+_loc + base_.length(),id_,partOffsets_);
            res_ = id_;
        } else {
            appendParts(firstOff_+_loc,firstOff_+_loc + res_.length(),res_,partOffsets_);
        }
        int offset_ = _loc;
        offset_ += inners_.first().length();
        offset_ ++;
        for (String i: inners_.mid(1)) {
            String resId_ = StringList.concat(res_,"..",i.trim());
            RootBlock inner_ = classes.getClassBody(resId_);
            if (inner_ == null) {
                //ERROR
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(bl_.getFile().getFileName());
                undef_.setIndexFile(rc_);
                //_in len
                undef_.buildError(getAnalysisMessages().getUnknownType(),
                        _in);
                addError(undef_);
                return EMPTY_TYPE;
            }
            appendParts(offset_,offset_ + i.trim().length(),resId_,partOffsets_);
            res_ = resId_;
            offset_ += i.length() + 1;
        }
        return res_;
    }

    @Override
    public String resolveAccessibleIdTypeWithoutError(int _loc,String _in) {
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            return EMPTY_TYPE;
        }
        AccessingImportingBlock r_ = analyzing.getImporting();
        int rc_ = getCurrentLocationIndex()+_loc;
        String gl_ = getGlobalClass();
        String curr_ = ((Block)r_).getFile().getRenderFileName();
        CustList<PartOffset> offs_ = coverage.getCurrentParts();
        offs_.clear();
        return PartTypeUtil.processAnalyzeLine(_in,new AlwaysReadyTypes(),false,gl_,this,r_,r_,curr_,rc_, offs_);
    }

    @Override
    public String resolveCorrectType(int _loc, String _in) {
        return resolveCorrectType(_loc,_in, true);
    }

    @Override
    public String resolveCorrectType(String _in) {
        return resolveCorrectType(0,_in, true);
    }

    /**Used at analyzing instructions*/
    @Override
    public String resolveCorrectAccessibleType(int _loc,String _in, String _fromType) {
        Block bl_ = getCurrentBlock();
        int rc_ = getCurrentLocationIndex()+_loc;
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(getAnalysisMessages().getVoidType(),
                    void_);
            addError(un_);
            return standards.getAliasObject();
        }
        AccessingImportingBlock r_ = analyzing.getImporting();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        String idFromType_ = Templates.getIdFromAllTypes(_fromType);
        GeneType from_ = getClassBody(idFromType_);
        String curr_ = ((Block)r_).getFile().getRenderFileName();
        String ref_ = "";
        if (ElUtil.isFromCustFile(from_)) {
            ref_ = ((Block)from_).getFile().getRenderFileName();
        }
        getAvailableVariables().clear();
        if (from_ != null) {
            for (TypeVar t: from_.getParamTypesMapValues()) {
                getAvailableVariables().addEntry(t.getName(),t.getOffset());
                vars_.addEntry(t.getName(), t.getConstraints());
            }
        }
        CustList<PartOffset> partOffsets_ = coverage.getCurrentParts();
        partOffsets_.clear();
        String resType_ = PartTypeUtil.processAnalyzeAccessibleId(_in, this, r_,curr_,ref_,rc_,partOffsets_);
        if (resType_.trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(getAnalysisMessages().getUnknownType(),
                    _in);
            addError(un_);
            return standards.getAliasObject();
        }
        if (!Templates.isCorrectTemplateAll(resType_, vars_, this)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(getAnalysisMessages().getBadParamerizedType(),
                    _in);
            addError(un_);
            return standards.getAliasObject();
        }
        return resType_;
    }

    @Override
    public String checkExactType(int _loc, String _in, String _orig) {
        if (!_in.isEmpty()) {
            return _in;
        }
        Block bl_ = getCurrentBlock();
        int rc_ = getCurrentLocationIndex() + _loc;
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(bl_.getFile().getFileName());
        un_.setIndexFile(rc_);
        //original type len
        un_.buildError(getAnalysisMessages().getUnknownType(),
                _orig);
        addError(un_);
        return standards.getAliasObject();
    }

    /**Used at analyzing instructions*/
    @Override
    public String resolveCorrectType(int _loc,String _in, boolean _exact) {
        Block bl_ = getCurrentBlock();
        int rc_ = getCurrentLocationIndex() + _loc;
        String void_ = standards.getAliasVoid();
        String tr_ = _in.trim();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(getAnalysisMessages().getVoidType(),
                    void_);
            addError(un_);
            return standards.getAliasObject();
        }
        AccessingImportingBlock r_ = analyzing.getImporting();
        String curr_ = ((Block)r_).getFile().getRenderFileName();
        StringMap<StringList> varsCt_ = getCurrentConstraints();
        StringMap<TypeVar> vars_ = getCurrentConstraintsFull();
        getAvailableVariables().clear();
        for (EntryCust<String,TypeVar> e: vars_.entryList()) {
            getAvailableVariables().addEntry(e.getKey(),e.getValue().getOffset());
        }
        CustList<PartOffset> partOffsets_ = coverage.getCurrentParts();
        partOffsets_.clear();
        String gl_ = getGlobalClass();
        String resType_;
        getCurrentBadIndexes().clear();
        if (_exact) {
            resType_ = PartTypeUtil.processAnalyze(tr_, false,gl_, this, r_,r_,curr_,rc_,partOffsets_);
        } else {
            resType_ = PartTypeUtil.processAnalyzeLine(tr_,new AlwaysReadyTypes(),false, gl_, this,r_,r_,curr_,rc_,partOffsets_);
        }
        for (int i: getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_+i);
            //part len
            un_.buildError(getAnalysisMessages().getInaccessibleType(),
                    resType_,gl_);
            addError(un_);
        }
        if (resType_.trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(getAnalysisMessages().getUnknownType(),
                    _in);
            addError(un_);
            return standards.getAliasObject();
        }
        if (!Templates.isCorrectTemplateAll(resType_, varsCt_, this, _exact)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(bl_.getFile().getFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(getAnalysisMessages().getBadParamerizedType(),
                    _in);
            addError(un_);
            return standards.getAliasObject();
        }
        return resType_;
    }
    @Override
    public StringMap<StringList> getCurrentConstraints() {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (EntryCust<String,TypeVar> e: getCurrentConstraintsFull().entryList()) {
            vars_.addEntry(e.getKey(), e.getValue().getConstraints());
        }
        return vars_;
    }

    private StringMap<TypeVar> getCurrentConstraintsFull() {
        if (isAnnotAnalysis()) {
            return new StringMap<TypeVar>();
        }
        Block bl_ = getCurrentBlock();
        AccessingImportingBlock r_ = analyzing.getImporting();
        StringMap<TypeVar> vars_ = new StringMap<TypeVar>();

        boolean static_;
        if (bl_ instanceof InfoBlock) {
            static_ = ((InfoBlock)bl_).isStaticField();
        } else {
            FunctionBlock fct_ = analyzing.getCurrentFct();
            if (fct_ == null) {
                static_ = true;
            } else if (isExplicitFct(fct_)){
                static_ = false;
            } else {
                static_ = fct_.getStaticContext() == MethodAccessKind.STATIC;
            }
        }
        if (r_ instanceof RootBlock && !static_) {
            for (TypeVar t: ((RootBlock)r_).getParamTypesMapValues()) {
                vars_.put(t.getName(), t);
            }
        }
        return vars_;
    }

    @Override
    public void setAccessStaticContext(MethodAccessKind _staticContext) {
        analyzing.setAccessStaticContext(_staticContext);
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return analyzing.getStaticContext();
    }

    private boolean isExplicitFct(FunctionBlock _fct) {
        return _fct instanceof OverridableBlock && StringList.quickEq(((OverridableBlock) _fct).getName(),keyWords.getKeyWordExplicit());
    }

    @Override
    public void appendParts(int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        GeneType g_ = getClassBody(_in);
        if (!ElUtil.isFromCustFile(g_)) {
            return;
        }
        AccessingImportingBlock r_ = analyzing.getImporting();
        int rc_ = getCurrentLocationIndex();
        String curr_ = ((Block)r_).getFile().getRenderFileName();
        String ref_ = ((RootBlock) g_).getFile().getRenderFileName();
        String rel_ = ElUtil.relativize(curr_,ref_);
        int id_ = ((RootBlock) g_).getIdRowCol();
        _parts.add(new PartOffset("<a title=\""+g_.getFullName()+"\" href=\""+rel_+"#m"+id_+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    @Override
    public void appendTitleParts(int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        int rc_ = getCurrentLocationIndex();
        _parts.add(new PartOffset("<a title=\""+ElUtil.transform(_in)+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    /**Used at analyzing instructions*/
    @Override
    public String resolveCorrectTypeWithoutErrors(int _loc,String _in, boolean _exact) {
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            return EMPTY_TYPE;
        }
        AccessingImportingBlock r_ = analyzing.getImporting();
        String curr_ = ((Block)r_).getFile().getRenderFileName();
        StringMap<StringList> varsCt_ = getCurrentConstraints();
        StringMap<TypeVar> vars_ = getCurrentConstraintsFull();
        getAvailableVariables().clear();
        for (EntryCust<String,TypeVar> e: vars_.entryList()) {
            getAvailableVariables().addEntry(e.getKey(),e.getValue().getOffset());
        }
        CustList<PartOffset> partOffsets_ = coverage.getCurrentParts();
        partOffsets_.clear();
        String gl_ = getGlobalClass();
        int rc_ = getCurrentLocationIndex() + _loc;
        getCurrentBadIndexes().clear();
        String resType_;
        if (_exact) {
            resType_ = PartTypeUtil.processAnalyze(_in, false,gl_, this, r_,r_,curr_,rc_,partOffsets_);
        } else {
            resType_ = PartTypeUtil.processAnalyzeLine(_in, new AlwaysReadyTypes(),false,gl_, this, r_,r_,curr_,rc_,partOffsets_);
        }
        if (resType_.trim().isEmpty()) {
            partOffsets_.clear();
            return EMPTY_TYPE;
        }
        if (!getCurrentBadIndexes().isEmpty()) {
            partOffsets_.clear();
            return EMPTY_TYPE;
        }
        if (!Templates.isCorrectTemplateAll(resType_, varsCt_, this, _exact)) {
            partOffsets_.clear();
            return EMPTY_TYPE;
        }
        return resType_;
    }

    /**Used at building mapping constraints*/
    public String resolveTypeMapping(String _in, RootBlock _currentBlock,
            int _location) {
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(getAnalysisMessages().getVoidType(),
                    void_);
            addError(un_);
            return standards.getAliasObject();
        }
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r: _currentBlock.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.addEntry(t.getName(),t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        getAvailableVariables().clear();
        getAvailableVariables().putAllMap(variables_);
        String gl_ = _currentBlock.getGenericString();
        CustList<PartOffset> partOffsets_ = coverage.getCurrentParts();
        String curr_ = _currentBlock.getFile().getRenderFileName();
        getCurrentBadIndexes().clear();
        String resType_ = PartTypeUtil.processAnalyze(_in, false,gl_, this, _currentBlock,_currentBlock,curr_,_location,partOffsets_);
        for (int i: getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location+i);
            //part len
            un_.buildError(getAnalysisMessages().getInaccessibleType(),
                    resType_,gl_);
            addError(un_);
        }
        if (resType_.trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(getAnalysisMessages().getUnknownType(),
                    _in);
            addError(un_);
            return standards.getAliasObject();
        }
        return resType_;
    }
    /**Used at building mapping constraints*/
    public String resolveTypeInherits(String _in, RootBlock _currentBlock,
                                      int _location) {
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r: _currentBlock.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.addEntry(t.getName(),t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        getAvailableVariables().clear();
        getAvailableVariables().putAllMap(variables_);
        String gl_ = _currentBlock.getGenericString();
        CustList<PartOffset> partOffsets_ = _currentBlock.getSuperTypesParts();
        String curr_ = _currentBlock.getFile().getRenderFileName();
        RootBlock scope_ = _currentBlock.getParentType();
        getCurrentBadIndexes().clear();
        String resType_ = PartTypeUtil.processAnalyze(_in,true,gl_,this,scope_,_currentBlock, curr_,_location,partOffsets_);
        for (int i: getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location+i);
            //part len
            un_.buildError(getAnalysisMessages().getInaccessibleType(),
                    resType_,gl_);
            addError(un_);
        }
        if (resType_.trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(getAnalysisMessages().getUnknownType(),
                    _in);
            addError(un_);
            return standards.getAliasObject();
        }
        for (String p:Templates.getAllTypes(resType_).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_currentBlock.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_);
                addError(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_currentBlock.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_);
                addError(call_);
            }
        }
        return resType_;
    }

    @Override
    public String lookupImportType(String _type, AccessingImportingBlock _rooted, ReadyTypes _ready) {
        String prefixedType_;
        prefixedType_ = getRealSinglePrefixedMemberType(_type, _rooted,_ready);
        return prefixedType_;
    }

    public String resolveBaseInherits(String _idSup, RootBlock _root, StringList _readyTypes) {
        String id_ = Templates.getIdFromAllTypes(_idSup);
        String gl_ = _root.getGenericString();
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        RootBlock scope_ = _root.getParentType();
        InheritReadyTypes inh_ = new InheritReadyTypes(_readyTypes);
        return PartTypeUtil.processAnalyzeLine(id_, inh_,true,gl_,this,scope_,_root,"",-1,partOffsets_);
    }

    private String getRealSinglePrefixedMemberType(String _type, AccessingImportingBlock _rooted, ReadyTypes _ready) {
        String look_ = _type.trim();
        StringList types_ = new StringList();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(_rooted, imports_);
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                String end_ = removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, look_)) {
                    continue;
                }
                if (stopLookup(types_,tr_,look_,_ready)) {
                    return EMPTY_TYPE;
                }
            }
        }
        if (types_.onlyOneElt()) {
            return types_.first();
        }
        if (!types_.isEmpty()) {
            return EMPTY_TYPE;
        }
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            String type_ = removeDottedSpaces(StringList.concat(r_.getPackageName(),".",_type));
            if (classes.isCustomType(type_)) {
                return type_;
            }
        }
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                String end_ = removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                if (stopLookup(types_,tr_,look_,_ready)) {
                    return EMPTY_TYPE;
                }
            }
        }
        if (types_.onlyOneElt()) {
            return types_.first();
        }
        if (!types_.isEmpty()) {
            return EMPTY_TYPE;
        }
        String defPkg_ = standards.getDefaultPkg();
        String type_ = removeDottedSpaces(StringList.concat(defPkg_,".",_type));
        if (getClassBody(type_) != null) {
            return type_;
        }
        return EMPTY_TYPE;
    }

    private boolean stopLookup(StringList _types, String _import, String _look, ReadyTypes _ready) {
        String beginImp_ = removeDottedSpaces(_import.substring(0, _import.lastIndexOf('.')+1));
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        boolean stQualifier_ = false;
        if (StringExpUtil.startsWithKeyWord(beginImp_, keyWordStatic_)) {
            beginImp_ = beginImp_.substring(keyWordStatic_.length()).trim();
            stQualifier_ = true;
        }
        String typeInner_ = StringList.concat(beginImp_, _look);
        String foundCandidate_ = StringList.join(Templates.getAllInnerTypes(typeInner_, this), "..");
        StringList allInnerTypes_ = Templates.getAllInnerTypes(foundCandidate_);
        String owner_ = allInnerTypes_.first();
        GeneType cl_ = getClassBody(owner_);
        String res_ = owner_;
        boolean addImport_ = true;
        if (cl_ != null) {
            for (String i: allInnerTypes_.mid(1)) {
                String i_ = i.trim();
                if (!_ready.isReady(res_)) {
                    return true;
                }
                StringList builtInners_ = TypeUtil.getInners(res_, i_, stQualifier_, this);
                if (builtInners_.onlyOneElt()) {
                    res_ = builtInners_.first();
                    continue;
                }
                if (!builtInners_.isEmpty()) {
                    return true;
                }
                addImport_ = false;
                break;
            }
        } else {
            addImport_ = false;
        }
        if (addImport_) {
            _types.add(res_);
        }
        return false;
    }

    private static void fetchImports(AccessedBlock _rooted, CustList<StringList> _imports) {
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            _imports.add(r_.getImports());
            for (RootBlock r: r_.getAllParentTypes()) {
                _imports.add(r.getImports());
            }
        } else {
            _imports.add(_rooted.getImports());
        }
        _imports.add(_rooted.getFileImports());
    }

    @Override
    public ObjectMap<ClassMethodId,ImportedMethod> lookupImportStaticMethods(String _glClass,String _method, Block _rooted) {
        ObjectMap<ClassMethodId,ImportedMethod> methods_ = new ObjectMap<ClassMethodId,ImportedMethod>();
        AccessingImportingBlock type_ = analyzing.getImporting();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(type_, imports_);
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        int import_ = 1;
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(typeLoc_);
                GeneType root_ = getClassBody(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, _method.trim())) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
            }
            import_++;
        }
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(typeLoc_);
                GeneType root_ = getClassBody(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
            }
            import_++;
        }
        return methods_;
    }

    private void fetchImportStaticMethods(String _glClass, String _method, ObjectMap<ClassMethodId, ImportedMethod> _methods, int _import, String _typeLoc, StringList _typesLoc) {
        for (String s: _typesLoc) {
            GeneType super_ = getClassBody(s);
            for (GeneMethod e: ContextEl.getMethodBlocks(super_)) {
                if (!e.isStaticMethod()) {
                    continue;
                }
                if (!StringList.quickEq(_method.trim(), e.getId().getName())) {
                    continue;
                }
                if (e instanceof AccessibleBlock) {
                    if (!Classes.canAccess(_typeLoc, (AccessibleBlock)e, this)) {
                        continue;
                    }
                    if (!Classes.canAccess(_glClass, (AccessibleBlock)e, this)) {
                        continue;
                    }
                }
                ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                _methods.add(clMet_, new ImportedMethod(_import,e.getImportedReturnType()));
            }
        }
    }

    @Override
    public ObjectMap<ClassField,ImportedField> lookupImportStaticFields(String _glClass,String _method, Block _rooted) {
        ObjectMap<ClassField,ImportedField> methods_ = new ObjectMap<ClassField,ImportedField>();
        int import_ = 1;
        AccessingImportingBlock type_ = analyzing.getImporting();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(type_, imports_);
        String keyWordStatic_ = keyWords.getKeyWordStatic();
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(typeLoc_);
                GeneType root_ = getClassBody(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, _method.trim())) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFields(_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
            }
            import_++;
        }
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(typeLoc_);
                GeneType root_ = getClassBody(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFields(_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
            }
            import_++;
        }
        return methods_;
    }

    public String resolveCandidate(String _c) {
        StringList allInnerTypes_ = getParts(_c);
        String owner_ = allInnerTypes_.first();
        GeneType cl_ = getClassBody(owner_);
        String res_ = owner_;
        if (cl_ != null) {
            for (String i: allInnerTypes_.mid(1)) {
                String i_ = i.trim();
                StringList builtInners_ = TypeUtil.getOwners(res_, i_, false, this);
                if (builtInners_.onlyOneElt()) {
                    res_ = StringList.concat(builtInners_.first(),"..",i_);
                    continue;
                }
                break;
            }
        }
        return res_;
    }

    public StringList getParts(String _c) {
        StringList allInnerTypes_;
        allInnerTypes_ = Templates.getAllInnerTypes(_c, this);
        return allInnerTypes_;
    }

    private void fetchImportStaticFields(String _glClass, String _method, ObjectMap<ClassField, ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc) {
        fetchImportStaticFields(this,_glClass,_method,_methods,_import,_typeLoc,_typesLoc);
    }

    public static void fetchImportStaticFields(Analyzable _analyzable,String _glClass, String _method, ObjectMap<ClassField, ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc) {
        for (String s: _typesLoc) {
            GeneType super_ = _analyzable.getClassBody(s);
            if (super_ instanceof StandardType) {
                for (StandardField m: ((StandardType)super_).getFields().values()) {
                    if (notMatch(_method, m)) {
                        continue;
                    }
                    ClassField field_ = new ClassField(s, _method);
                    _methods.add(field_, new ImportedField(_import,m));
                }
            } else {
                for (InfoBlock e: ContextEl.getFieldBlocks((RootBlock) super_)) {
                    if (notMatch(_method, e)) {
                        continue;
                    }
                    if (e instanceof AccessibleBlock) {
                        if (!Classes.canAccess(_typeLoc, (AccessibleBlock)e, _analyzable)) {
                            continue;
                        }
                        if (!Classes.canAccess(_glClass, (AccessibleBlock)e, _analyzable)) {
                            continue;
                        }
                    }
                    ClassField field_ = new ClassField(s, _method);
                    _methods.add(field_, new ImportedField(_import,e));
                }
            }
        }
    }
    private static boolean notMatch(String _method, GeneField _field) {
        if (!_field.isStaticField()) {
            return true;
        }
        return !StringList.contains(_field.getFieldName(), _method.trim());
    }
    public static boolean isDigit(char _char) {
        return _char >= '0' && _char <= '9';
    }

    public static String removeDottedSpaces(String _type) {
        StringBuilder b_ = new StringBuilder();
        for (String q: StringList.splitCharSep(_type, Templates.SEP_CLASS_CHAR)) {
            b_.append(q.trim());
        }
        return b_.toString();
    }

    public final ClassMetaInfo getExtendedClassMetaInfo(String _name, String _variableOwner) {
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
            return new ClassMetaInfo(_name, this, ClassCategory.ARRAY, _variableOwner);
        }
        return getExtendedClassMetaInfo(_name);
    }
    @Override
    public final ClassMetaInfo getExtendedClassMetaInfo(String _name) {
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
                if (!StringList.contains(i_.getFieldName(), search_)) {
                    continue;
                }
                String type_ = i_.getImportedClassName();
                boolean final_ = i_.isFinalField();
                boolean static_ = i_.isStaticField();
                return FieldInfo.newFieldMetaInfo(search_, g_.getFullName(), type_, static_, final_,i_);
            }
        } else if (g_ instanceof StandardType) {
            for (EntryCust<String, StandardField> f: ((StandardType)g_).getFields().entryList()) {
                StandardField f_ = f.getValue();
                if (!StringList.contains(f_.getFieldName(), search_)) {
                    continue;
                }
                String type_ = f_.getImportedClassName();
                boolean final_ = f_.isFinalField();
                boolean static_ = f_.isStaticField();
                return FieldInfo.newFieldMetaInfo(search_, g_.getFullName(), type_, static_, final_, f_);
            }
        }
        return null;
    }

    @Override
    public StringMap<Integer> getAvailableVariables() {
        return analyzing.getAvailableVariables();
    }

    @Override
    public StringList getVariablesNamesLoopToInfer() {
        return analyzing.getVariablesNamesLoopToInfer();
    }

    @Override
    public StringList getVariablesNamesToInfer() {
        return analyzing.getVariablesNamesToInfer();
    }

    @Override
    public StringList getVariablesNames() {
        return analyzing.getVariablesNames();
    }

    public boolean isAssignedStaticFields() {
        return analyzing.isAssignedStaticFields();
    }

    public void setAssignedStaticFields(boolean _assignedStaticFields) {
        analyzing.setAssignedStaticFields(_assignedStaticFields);
    }

    public boolean isAssignedFields() {
        return analyzing.isAssignedFields();
    }

    public void setAssignedFields(boolean _assignedFields) {
        analyzing.setAssignedFields(_assignedFields);
    }

    public boolean isFinalMutableLoopVar(String _key, int _index) {
        return analyzing.isFinalMutableLoopVar(_key,_index);
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
    public String getIndexClassName() {
        return ((ForMutableIterativeLoop)getCurrentBlock()).getImportedClassIndexName();
    }

    @Override
    public void setForLoopPartState(ForLoopPart _state) {
        analyzing.setForLoopPartState(_state);
    }

    public AnalyzingEl getAnalysisAss() {
        return analyzing.getAnalysisAss();
    }

    @Override
    public boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq) {
        boolean ok_ = false;
        if (getCurrentBlock() instanceof AnnotationMethodBlock && _op == null) {
            ok_ = true;
        } else if (_op instanceof AssocationOperation){
            ok_ = true;
        } else if (_op instanceof AnnotationInstanceOperation){
            ok_ = true;
        }
        if (!ok_) {
            return false;
        }
        String op_ = _seq.getOperators().firstValue();
        return StringList.quickEq(op_, String.valueOf('{'));
    }

    public boolean isAnnotAnalysis() {
        return analyzing.isAnnotAnalysis();
    }

    public void setAnnotAnalysis(boolean _ana) {
        analyzing.setAnnotAnalysis(_ana);
    }
    public boolean isAnnotAnalysisField() {
        return analyzing.isAnnotAnalysisField();
    }

    public void setAnnotAnalysisField(boolean _ana) {
        analyzing.setAnnotAnalysisField(_ana);
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
    public boolean isOkNumOp() {
        return analyzing.isOkNumOp();
    }

    @Override
    public void setOkNumOp(boolean _okNumOp) {
        analyzing.setOkNumOp(_okNumOp);
    }

    @Override
    public Ints getCurrentBadIndexes() {
        return analyzing.getCurrentBadIndexes();
    }

    @Override
    public int getCurrentLocationIndex() {
        return analyzing.getTraceIndex();
    }

    public AnalysisMessages getAnalysisMessages() {
        return analysisMessages;
    }

    public void setAnalysisMessages(AnalysisMessages _analysisMessages) {
        analysisMessages = _analysisMessages;
    }

    @Override
    public KeyWords getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(KeyWords _keyWords) {
        keyWords = _keyWords;
    }

	@Override
    public boolean isValidSingleToken(String _id) {
        if (!isValidToken(_id)) {
            return false;
        }
        return idDisjointToken(_id);
    }

    public boolean idDisjointToken(String _id) {
        return isNotVar(_id);
    }

    public boolean isNotVar(String _id) {
        if (containsLocalVar(_id)) {
            return false;
        }
        if (analyzing.containsCatchVar(_id)) {
            return false;
        }
        if (analyzing.containsMutableLoopVar(_id)) {
            return false;
        }
        if (analyzing.containsVar(_id)) {
            return false;
        }
        return !getParameters().contains(_id);
    }

    @Override
    public boolean isValidToken(String _id) {
        Block b_ = getCurrentBlock();
        boolean pred_ = b_.getFile().isPredefined();
        return isValidToken(_id, pred_);
    }

    @Override
    public boolean isHidden(AccessingImportingBlock _global, RootBlock _type) {
        return _global.isTypeHidden(_type,this);
    }

    public boolean isValidToken(String _id, boolean _pred) {
        if (_pred) {
            if (!StringList.isDollarWord(_id)) {
                return false;
            }
        } else {
            if (!StringList.isWord(_id)) {
                return false;
            }
        }
        if (PrimitiveTypeUtil.isPrimitive(_id, this)) {
            return false;
        }
        if (keyWords.isKeyWordNotVar(_id)) {
            return false;
        }
        if (StringList.quickEq(_id, standards.getAliasVoid())) {
            return false;
        }
        return !isDigit(_id.charAt(0));
    }

    @Override
    public void processInternKeyWord(String _exp, int _fr, ResultAfterInstKeyWord _out) {
        //because of looping on characters
    }

    @Override
    public boolean hasToExit(String _className) {
        Classes classes_ = getClasses();
        String idClass_ = Templates.getIdFromAllTypes(_className);
        ContextEl cont_ = getContextEl();
        String curClass_ = cont_.getLastPage().getGlobalClass();
        curClass_ = Templates.getIdFromAllTypes(curClass_);
        if (StringList.quickEq(curClass_, idClass_)) {
            return false;
        }
        if (classes_.isCustomType(_className)) {
            DefaultLockingClass locks_ = classes_.getLocks();
            if (cont_.isInitEnums()) {
                InitClassState res_ = locks_.getState(idClass_);
                if (res_ != InitClassState.SUCCESS) {
                    getContextEl().failInitEnums();
                    return true;
                }
                return false;
            }
            InitClassState res_ = locks_.getState(getContextEl(), _className);
            if (res_ == InitClassState.NOT_YET) {
                getContextEl().setCallingState(new NotInitializedClass(_className));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(_className, this);
                setException(causing_);
                return true;
            }
        }
        return false;
    }

}
