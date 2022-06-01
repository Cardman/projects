package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.ResultInput;
import code.sml.Element;
import code.util.CustList;
import code.util.StringMap;

public interface AnaRendInputInt {
    Element getElt();

    int getAttributeDelimiter(String _attr);

    StringMap<ResultExpression> getAttributesText();

    CustList<SegmentStringPart> getStringPartsElt(String _attr);

    ResultInput getResultInput();
}
