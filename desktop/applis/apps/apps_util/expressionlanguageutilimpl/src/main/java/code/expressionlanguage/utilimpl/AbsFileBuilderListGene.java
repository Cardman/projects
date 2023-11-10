package code.expressionlanguage.utilimpl;

import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;

public interface AbsFileBuilderListGene {
    CustList<AbsAliasFileBuilder> build(LgNamesGui _stds);
    AbsAdvContextGenerator gene(AbstractAtomicBoolean _st);
}
