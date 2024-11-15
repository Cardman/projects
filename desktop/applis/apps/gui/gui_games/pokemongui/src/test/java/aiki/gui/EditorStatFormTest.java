package aiki.gui;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.gui.components.editor.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EditorStatFormTest extends InitEditorPkForm {
    @Test
    public void statForm() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<Statistic> cTr_ = crudTr(sub_);
        cTr_.getFields().getVal(Statistic.SPEED).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedStatistics().getVal(pr_.getLanguage()).getVal(Statistic.SPEED));
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().setVarParamsMove(new StringMap<StringList>());
        return f_;
    }
    private CrudGeneFormTrCst<Statistic> crudTr(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstStatMenu());
        return _crud.getCrudGeneFormCstStat();
    }
}
