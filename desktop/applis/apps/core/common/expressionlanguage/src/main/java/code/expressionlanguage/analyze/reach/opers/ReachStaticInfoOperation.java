package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.StaticInfoOperation;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.util.CustList;

public final class ReachStaticInfoOperation extends ReachMethodOperation implements ReachCalculable {

    private String className;
    ReachStaticInfoOperation(StaticInfoOperation _info) {
        super(_info);
        className = _info.getClassName();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (className.contains(AnaTemplates.PREFIX_VAR_TYPE)) {
            return;
        }
        ClassMetaInfo candidate_ = new ClassMetaInfo(className);
        CustList<ClassMetaInfo> classMetaInfos_ = _page.getClassMetaInfos();
        for (ClassMetaInfo c: classMetaInfos_) {
            if (c.sameReference(candidate_)) {
                Argument a_ = new Argument(c);
                setSimpleArgumentAna(a_);
                return;
            }
        }
        classMetaInfos_.add(candidate_);
        Argument a_ = new Argument(candidate_);
        setSimpleArgumentAna(a_);
    }
}
