package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
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
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ReachOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        checkDeadCode(chidren_.first(),getInfo(), _page);
        Argument argBool_ = arguments_.first();
        if (argBool_ == null) {
            return;
        }
        Struct str_ = argBool_.getStruct();
        if (!(str_ instanceof BooleanStruct)) {
            return;
        }
        Argument arg_;
        if (BooleanStruct.isTrue(str_)) {
            arg_ = arguments_.get(IndexConstants.SECOND_INDEX);
        } else {
            arg_ = arguments_.last();
        }
        if (arg_ == null) {
            return;
        }
        setSimpleArgumentAna(arg_);
    }

    static void checkDeadCode(ReachOperationNode _opOne, OperationNode _info, AnalyzedPageEl _page) {
        if (_page.isDisplayWarningTernary()&&_opOne.getArgument() != null) {
            FoundWarningInterpret d_ = new FoundWarningInterpret();
            d_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            d_.setFileName(_page.getLocalizer().getCurrentFileName());
            d_.buildWarning(_page.getAnalysisMessages().getDeadCodeTernary());
            _page.getLocalizer().addWarning(d_);
            _info.addWarn(d_.getBuiltWarning());
        }
    }
}
