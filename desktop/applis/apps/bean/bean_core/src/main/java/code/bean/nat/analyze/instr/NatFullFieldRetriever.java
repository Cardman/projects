package code.bean.nat.analyze.instr;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.analyze.instr.VariableInfo;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;

public final class NatFullFieldRetriever {

    private final Delimiters delimiters;
    private final NatAnalyzedCode context;

    public NatFullFieldRetriever(Delimiters _delimiters, NatAnalyzedCode _context) {
        this.delimiters = _delimiters;
        this.context = _context;
    }

    public void processFieldsStaticAccess(int _begin, String _word, int _to) {
        AnaLocalVariable val_ = context.getInfosVars().getVal(_word);
        if (val_ != null) {
            ConstType type_;
            type_ = val_.getConstType();
            VariableInfo info_ = new VariableInfo();
            info_.setKind(type_);
            info_.setFirstChar(_begin);
            info_.setLastChar(_to);
            info_.setName(_word);
            delimiters.getVariables().add(info_);
            return;
        }
        VariableInfo info_ = new VariableInfo();
        ConstType type_ = ConstType.WORD;
        info_.setKind(type_);
        info_.setFirstChar(_begin);
        info_.setLastChar(_to);
        info_.setName(_word);
        delimiters.getVariables().add(info_);
    }

}
