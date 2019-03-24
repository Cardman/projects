package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.IndexerBlock;
import code.expressionlanguage.methods.MemberCallingsBlock;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;

public final class ValueOperation extends VariableLeafOperation {
    public ValueOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        MemberCallingsBlock fct_ = _conf.getAnalyzing().getCurrentFct();
        IndexerBlock indexer_ = (IndexerBlock) fct_;
        String gl_ = _conf.getGlobalClass();
        gl_ = Templates.getIdFromAllTypes(gl_);
        CustList<IndexerBlock> getIndexers_ = new CustList<IndexerBlock>();
        for (Block b: Classes.getDirectChildren(_conf.getClasses().getClassBody(gl_))) {
            if (!(b instanceof IndexerBlock)) {
                continue;
            }
            IndexerBlock i_ = (IndexerBlock) b;
            if (!i_.isIndexerGet()) {
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
        IndexerBlock matching_ = getIndexers_.first();
        setResultClass(new ClassArgumentMatching(matching_.getImportedReturnType()));
    }
}
