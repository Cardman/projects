package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.QuickOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;


public abstract class RendQuickOperation extends RendMethodOperation implements RendCalculableOperation {

    public RendQuickOperation(QuickOperation _q) {
        super(_q);
    }

    @Override
    public final void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
        Argument a_ = chidren_.last().getArgument();
        setSimpleArgument(a_, _conf);
    }

    public abstract BooleanStruct absorbingStruct();
}
