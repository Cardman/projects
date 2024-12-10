package aiki.gui;

import aiki.facade.FacadeGame;
import aiki.gui.components.editor.CrudGeneFormTr;
import aiki.gui.components.editor.GeneComponentModelTr;
import aiki.gui.components.editor.WindowPkEditor;
import code.mock.MockProgramInfos;
import code.mock.MockTextField;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class EditorCaFormTest extends InitEditorPkForm {
    @Test
    public void tyForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq("p_2",facade_.getData().getTranslatedCategories().firstValue().getVal(C_2));
    }
    @Test
    public void tyForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(C_3);
        enterTextField(cTr_.getDestination());
        assertTrue(facade_.getData().getTranslatedCategories().firstValue().contains(C_3));
    }
    @Test
    public void tyForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(C_1);
        enterTextField(cTr_.getDestination());
        assertTrue(facade_.getData().getTranslatedCategories().firstValue().contains(C_1));
        assertTrue(facade_.getData().getTranslatedCategories().firstValue().contains(C_2));
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().setVarParamsMove(new StringMap<StringList>());
        return f_;
    }
    private CrudGeneFormTr crudTr(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCaMenu());
        return _crud.getCrudGeneFormCaTr();
    }
}
