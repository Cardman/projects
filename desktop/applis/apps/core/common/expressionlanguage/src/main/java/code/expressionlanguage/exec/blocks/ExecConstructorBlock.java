package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.AccessibleBlock;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.ReturnableWithSignature;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;

public final class ExecConstructorBlock extends ExecNamedFunctionBlock implements AccessibleBlock,GeneConstructor,ReturnableWithSignature {

    private ConstructorId constIdSameClass;

    private boolean implicitCallSuper;

    private int leftPar;
    public ExecConstructorBlock(ConstructorBlock _offset) {
        super(_offset);
        leftPar=_offset.getLeftPar();
    }

    @Override
    public ConstructorId getId() {
        ExecRootBlock clBlock_ = (ExecRootBlock) getParent();
        String name_ = clBlock_.getFullName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }


    public String getDeclaringType() {
        ExecRootBlock clBlock_ = (ExecRootBlock) getParent();
        return clBlock_.getFullName();
    }
    public ConstructorId getGenericId() {
        ExecRootBlock clBlock_ = (ExecRootBlock) getParent();
        String name_ = clBlock_.getGenericString();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }
    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_cont,_parts);
        int begName_ = getNameOffset();
        _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
        _parts.add(new PartOffset("</a>",leftPar));
        int len_ = getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            buildAnnotationsReport(i,_cont,_parts);
            _parts.addAllElts(getPartOffsetsParams().get(i));
            Integer off_ = getParametersNamesOffset().get(i);
            String param_ = getParametersNames().get(i);
            _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            _parts.add(new PartOffset("</a>",off_+param_.length()));
            _cont.getCoverage().getParamVars().put(param_,off_);
        }
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    @Override
    public String getName() {
        return EMPTY_STRING;
    }

    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }

    public boolean implicitConstr() {
        return implicitCallSuper;
    }

    public void buildImportedTypes(ConstructorBlock _key) {
        setImportedReturnType(_key.getImportedReturnType());
        getImportedParametersTypes().addAllElts(_key.getImportedParametersTypes());
        setPartOffsetsParams(_key.getPartOffsetsParams());
        setPartOffsetsReturn(_key.getPartOffsetsReturn());
    }

    @Override
    public String getPackageName() {
        return ((ExecRootBlock)getParent()).getPackageName();
    }

    @Override
    public String getFullName() {
        return ((ExecRootBlock)getParent()).getFullName();
    }

    @Override
    public String getOuterFullName() {
        return ((ExecRootBlock)getParent()).getOuter().getFullName();
    }

    public void setConstIdSameClass(ConstructorId constIdSameClass) {
        this.constIdSameClass = constIdSameClass;
    }

    public void setImplicitCallSuper(boolean implicitCallSuper) {
        this.implicitCallSuper = implicitCallSuper;
    }
}
