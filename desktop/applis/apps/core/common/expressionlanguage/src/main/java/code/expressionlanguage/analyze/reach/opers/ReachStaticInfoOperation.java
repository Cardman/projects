package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.StaticInfoOperation;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.util.CustList;

public final class ReachStaticInfoOperation extends ReachMethodOperation implements ReachCalculable {

    private final String className;
    ReachStaticInfoOperation(StaticInfoOperation _info) {
        super(_info);
        className = _info.getClassName();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (className.contains(AnaInherits.PREFIX_VAR_TYPE)) {
            return;
        }
        ClassMetaInfo candidate_ = new ClassMetaInfo(className);
        CustList<ClassMetaInfo> classMetaInfos_ = _page.getClassMetaInfos();
        for (ClassMetaInfo c: classMetaInfos_) {
            if (c.sameReference(candidate_)) {
                setSimpleArgumentAna(c);
                return;
            }
        }
        classMetaInfos_.add(candidate_);
        setSimpleArgumentAna(candidate_);
    }
}
