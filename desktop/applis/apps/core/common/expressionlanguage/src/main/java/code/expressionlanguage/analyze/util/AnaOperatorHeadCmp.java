package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.OperatorBlock;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class AnaOperatorHeadCmp implements Comparing<OperatorBlock> {
    @Override
    public int compare(OperatorBlock _one, OperatorBlock _two) {
        return StringUtil.compareStrings(head(_one), head(_two));
    }

    private String head(OperatorBlock _op) {
        FileBlock f_ = _op.getFile();
        Ints offs_ = f_.getImportsOffset();
        StringList ips_ = f_.getImports();
        int len_ = f_.getImports().size();
        String content_ = f_.getContent();
        StringBuilder pre_ = new StringBuilder();
        for (int i = 0; i < len_; i++) {
            pre_.append(content_, offs_.get(i), offs_.get(i)+ips_.get(i).length()+1);
        }
        return pre_+content_.substring(_op.getNameOffset(), _op.getBegin());
    }
}
