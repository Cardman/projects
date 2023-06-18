package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.util.StringList;

public interface InnerTypeOrElement extends InfoBlock {
    String getUniqueFieldName();
    int getFieldNameOffset();

    AnaElementContent getElementContent();

    StringList getNameErrors();

    void addNameErrors(String _err);
}
