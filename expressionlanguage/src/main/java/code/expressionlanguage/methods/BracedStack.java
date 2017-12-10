package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.sml.Element;

public abstract class BracedStack extends BracedBlock {

    BracedStack(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

}
