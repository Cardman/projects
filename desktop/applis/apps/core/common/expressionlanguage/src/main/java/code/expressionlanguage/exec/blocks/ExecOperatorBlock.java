package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneCustMethod;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ExecOperatorBlock extends ExecNamedFunctionBlock implements GeneCustMethod, ExecAccessingImportingBlock,ReturnableWithSignature {

    private StringList imports;

    private Ints importsOffset;

    public ExecOperatorBlock(OperatorBlock _offset) {
        super(_offset);
        imports = _offset.getImports();
        importsOffset = _offset.getImportsOffset();
    }

    public void buildImportedTypes(OperatorBlock _key) {
        setImportedReturnType(_key.getImportedReturnType());
        getImportedParametersTypes().addAllElts(_key.getImportedParametersTypes());
        setPartOffsetsParams(_key.getPartOffsetsParams());
        setPartOffsetsReturn(_key.getPartOffsetsReturn());
    }
    @Override
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(MethodAccessKind.STATIC, name_, pTypes_, isVarargs());
    }

    @Override
    public boolean isTypeHidden(ExecRootBlock _class, ContextEl _analyzable) {
        return _class.getAccess() != AccessEnum.PUBLIC;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_cont,_parts);
        int begName_ = getNameOffset();
        int endName_ = begName_ + getName().length();
        _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
        _parts.add(new PartOffset("</a>",endName_));
        _parts.addAllElts(getPartOffsetsReturn());
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

    public MethodModifier getModifier() {
        return MethodModifier.STATIC;
    }

    public String getDeclaringType() {
        return EMPTY_STRING;
    }

    @Override
    public StringList getFileImports() {
        return getFile().getImports();
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    @Override
    public StringList getImports() {
        return imports;
    }
}
