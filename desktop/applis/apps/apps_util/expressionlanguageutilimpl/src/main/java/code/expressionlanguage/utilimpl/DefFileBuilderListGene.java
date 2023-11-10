package code.expressionlanguage.utilimpl;

import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.DefAliasFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;

public final class DefFileBuilderListGene implements AbsFileBuilderListGene {
    @Override
    public CustList<AbsAliasFileBuilder> build(LgNamesGui _stds) {
        CustList<AbsAliasFileBuilder> builders_ = new CustList<AbsAliasFileBuilder>();
        builders_.add(new DefAliasFileBuilder());
        builders_.add(_stds.getExecContent().getCustAliases());
        builders_.add(_stds.getExecContent().getCustAliases().getStringViewReplaceAliases());
        builders_.add(_stds.getGuiAliases());
        return builders_;
    }

    @Override
    public AbsAdvContextGenerator gene(AbstractAtomicBoolean _st) {
        return new AdvContextGenerator(_st);
    }
}
