package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;

public final class ValueOperation extends LeafOperation {
    private int off;
    public ValueOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
    }

    @Override
    public void analyze(Analyzable _conf) {
        FunctionBlock fct_ = _conf.getAnalyzing().getCurrentFct();
        OverridableBlock indexer_ = (OverridableBlock) fct_;
        String gl_ = _conf.getGlobalClass();
        gl_ = Templates.getIdFromAllTypes(gl_);
        CustList<OverridableBlock> getIndexers_ = new CustList<OverridableBlock>();
        for (Block b: Classes.getDirectChildren(_conf.getClasses().getClassBody(gl_))) {
            if (!(b instanceof OverridableBlock)) {
                continue;
            }
            OverridableBlock i_ = (OverridableBlock) b;
            if (i_.getKind() != MethodKind.GET_INDEX) {
                continue;
            }
            if (!i_.getId().eqPartial(indexer_.getId())) {
                continue;
            }
            getIndexers_.add(i_);
        }
        if (getIndexers_.size() != 1) {
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        OverridableBlock matching_ = getIndexers_.first();
        setResultClass(new ClassArgumentMatching(matching_.getImportedReturnType()));
    }

    public int getOff() {
        return off;
    }
}
