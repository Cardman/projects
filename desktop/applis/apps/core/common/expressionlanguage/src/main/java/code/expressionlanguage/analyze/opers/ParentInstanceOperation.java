package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;

public final class ParentInstanceOperation extends LeafOperation implements PossibleIntermediateDotted {
    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private int off;
    protected ParentInstanceOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        off = _op.getOffset();
    }

    @Override
    public void analyze(ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        StringList converted_ = new StringList();
        if (isIntermediateDottedOperation()) {
            for (String p:previousResultClass.getNames()) {
                if (p.startsWith(Templates.ARR_BEG_STRING)) {
                    continue;
                }
                converted_.addAllElts(InvokingOperation.getBounds(p,_conf));
            }
        } else {
            converted_.add(_conf.getAnalyzing().getGlobalClass());
        }
        StringList rs_ = getParentTypeList(_conf,converted_);
        setResultClass(new ClassArgumentMatching(rs_));
    }

    static StringList getParentTypeList(ContextEl _conf, StringList _converted) {
        StringList rs_ = getParentType(_converted);
        if (rs_.isEmpty()) {
            return new StringList(_conf.getStandards().getAliasObject());
        }
        return rs_;
    }

    private static StringList getParentType(StringList _converted) {
        StringList pars_ = new StringList();
        for (String p: _converted) {
            StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(p);
            String outer_ = StringList.join(innerParts_.mid(0, innerParts_.size() - 2), "");
            if (StringList.contains(pars_,outer_)) {
                continue;
            }
            pars_.add(outer_);
        }
        StringList rs_ = new StringList();
        for (String p:pars_) {
            if (p.trim().isEmpty()) {
                continue;
            }
            rs_.add(p);
        }
        return rs_;
    }


    @Override
    public void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public void setPreviousResultClass(ClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        previousResultClass = _previousResultClass;
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public int getOff() {
        return off;
    }
}
