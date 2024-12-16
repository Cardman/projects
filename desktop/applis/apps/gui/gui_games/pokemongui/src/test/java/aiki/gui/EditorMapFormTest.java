package aiki.gui;

import aiki.facade.*;
import aiki.gui.components.editor.*;
import code.mock.*;
import org.junit.Test;

public final class EditorMapFormTest extends InitEditorPkForm {
    @Test
    public void values() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        sub_.getFormDataMap().getScreenWidth().setValue(2);
        sub_.getFormDataMap().getScreenHeight().setValue(3);
        sub_.getFormDataMap().getSpaceBetweenLeftAndHeros().setValue(4);
        sub_.getFormDataMap().getSpaceBetweenTopAndHeros().setValue(5);
        sub_.getFormDataMap().getSideLength().setValue(6);
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(2,facade_.getData().getMap().getScreenWidth());
        assertEq(3,facade_.getData().getMap().getScreenHeight());
        assertEq(4,facade_.getData().getMap().getSpaceBetweenLeftAndHeros());
        assertEq(5,facade_.getData().getMap().getSpaceBetweenTopAndHeros());
        assertEq(6,facade_.getData().getMap().getSideLength());
    }
}
