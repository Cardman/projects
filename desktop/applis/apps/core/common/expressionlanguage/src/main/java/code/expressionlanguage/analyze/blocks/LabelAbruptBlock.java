package code.expressionlanguage.analyze.blocks;

import code.util.StringList;

public interface LabelAbruptBlock {
    int getBegin();
    int getLabelOffset();
    String getLabel();
    int getLabelOffsetRef();
    StringList getErrorsRefLabels();

    int getLengthHeader();
}
