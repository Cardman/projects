package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneCustMethod;
import code.expressionlanguage.common.GeneCustModifierMethod;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.AccessibleBlock;
import code.expressionlanguage.methods.MethodKind;
import code.expressionlanguage.methods.OverridableBlock;
import code.expressionlanguage.methods.ReturnableWithSignature;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.StringList;

public final class ExecOverridableBlock extends ExecNamedFunctionBlock implements AccessibleBlock,GeneCustModifierMethod,ReturnableWithSignature {

    private final int modifierOffset;

    private final boolean staticMethod;
    private final boolean staticCallMethod;

    private final boolean finalMethod;
    private final boolean abstractMethod;

    private final MethodKind kind;
    public ExecOverridableBlock(OverridableBlock _offset) {
        super(_offset);
        modifierOffset = _offset.getModifierOffset();
        staticMethod = _offset.isStaticMethod();
        staticCallMethod = _offset.isStaticCallMethod();
        finalMethod = _offset.isFinalMethod();
        abstractMethod = _offset.isAbstractMethod();
        kind = _offset.getKind();
    }

    @Override
    public boolean isFinalMethod() {
        return finalMethod;
    }

    @Override
    public boolean hiddenInstance() {
        return staticCallMethod || staticMethod;
    }

    @Override
    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    @Override
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        if (kind == MethodKind.EXPLICIT_CAST || kind == MethodKind.IMPLICIT_CAST) {
            pTypes_.add(getImportedReturnType());
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(MethodId.getKind(getModifier()), name_, pTypes_, isVarargs());

    }

    public MethodModifier getModifier() {
        if (abstractMethod) {
            return MethodModifier.ABSTRACT;
        }
        if (finalMethod) {
            return MethodModifier.FINAL;
        }
        if (staticCallMethod) {
            return MethodModifier.STATIC_CALL;
        }
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        return MethodModifier.NORMAL;
    }
    @Override
    public boolean isStaticMethod() {
        return staticMethod;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_cont,_parts);
        int begName_ = getNameOffset();
        if (kind == MethodKind.OPERATOR) {
            _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
            int endName_ = begName_ + getName().length();
            _parts.add(new PartOffset("</a>",endName_));
            _parts.addAllElts(getPartOffsetsReturn());
            refParams(_cont, _parts);
            return;
        }
        _parts.addAllElts(getPartOffsetsReturn());
        if (kind == MethodKind.GET_INDEX) {
            _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
            int endName_ = begName_ + _cont.getKeyWords().getKeyWordThis().length();
            _parts.add(new PartOffset("</a>",endName_));
            refParams(_cont, _parts);
            return;
        }
        if (kind == MethodKind.SET_INDEX) {
            _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
            int endName_ = begName_ + _cont.getKeyWords().getKeyWordThis().length();
            _parts.add(new PartOffset("</a>",endName_));
            refParams(_cont, _parts);
            return;
        }
        int endName_ = begName_ + getName().length();
        _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
        _parts.add(new PartOffset("</a>",endName_));
        refParams(_cont, _parts);
    }

    private void refParams(ContextEl _cont, CustList<PartOffset> _parts) {
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

    public void buildImportedTypes(OverridableBlock _key) {
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

    public MethodKind getKind() {
        return kind;
    }
}
