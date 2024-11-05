package aiki.gui;

import aiki.facade.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EditorItFormTest extends InitEditorPkForm {
    @Test
    public void itForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGeneValue();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq("p_2",facade_.getData().getTranslatedItems().firstValue().getVal(I_2));
    }
    @Test
    public void itForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(I_3);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getItems().contains(I_3));
    }
    @Test
    public void itForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(I_1);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getItems().contains(I_1));
        assertTrue(facade_.getData().getItems().contains(I_2));
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().completeQuickMembers(I_1, Instances.newBall());
        f_.getData().completeQuickMembers(I_2, Instances.newBall());
        f_.getData().setVarParamsMove(new StringMap<StringList>());
        return f_;
    }
    private CrudGeneFormTr crudTr(WindowPkEditor _crud) {
        tryClick(_crud.getTrsItMenu());
        return _crud.getCrudGeneFormItTr();
    }
}
