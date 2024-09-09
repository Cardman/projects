package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ReachTernaryOperation extends ReachMethodOperation implements ReachCalculable {
    ReachTernaryOperation(OperationNode _info) {
        super(_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        CustList<ReachOperationNode> chidren_ = getChildrenNodes();
        checkDeadCode(chidren_.first(),getInfo(), _page);
        Struct argBool_ = chidren_.first().getArgument();
        if (argBool_ == null) {
            return;
        }
        if (!(argBool_ instanceof BooleanStruct)) {
            return;
        }
        Struct arg_;
        if (BooleanStruct.isTrue(argBool_)) {
            arg_ = chidren_.get(IndexConstants.SECOND_INDEX).getArgument();
        } else {
            arg_ = chidren_.last().getArgument();
        }
        if (arg_ == null) {
            return;
        }
        setSimpleArgumentAna(arg_);
    }

    static void checkDeadCode(ReachOperationNode _opOne, OperationNode _info, AnalyzedPageEl _page) {
        if (_page.isDisplayWarningTernary()&&_opOne.getArgument() != null) {
            FoundWarningInterpret d_ = new FoundWarningInterpret();
            d_.setIndexFile(_page);
            d_.setFile(_page.getCurrentFile());
            d_.buildWarning(_page.getAnalysisMessages().getDeadCodeTernary());
            _page.getLocalizer().addWarning(d_);
            _info.addWarn(d_.getBuiltWarning());
        }
    }
}
