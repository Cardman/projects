package cards.gui.dialogs;

import code.formathtml.render.*;
import code.gui.*;
import code.gui.document.*;
import code.gui.events.*;
import code.gui.initialize.*;

public final class FindBeanCardsEvent implements AbsActionListener {
    private final FindBeanEventContent content;

    public FindBeanCardsEvent(AbsTextField _field, AbstractProgramInfos _a) {
        content = new FindBeanEventContent(_field, _a);
    }
    public void setFinding(BeanBuilderHelperCards _document) {
        content.setRefsSearch(_document.getRefsSearch());
        content.setFonts(_document.getFonts());
        content.setScrollPane(_document.getScrollPane());
        content.setParents(_document.getParentsCards());
        content.setFinding(new FindNextElement(_document.getMetaSearchableContents()));
    }
    @Override
    public void action() {
        content.action();
    }
}
