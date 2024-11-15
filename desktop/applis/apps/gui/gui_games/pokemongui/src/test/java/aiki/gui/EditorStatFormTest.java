package aiki.gui;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.enums.*;
import aiki.gui.components.editor.*;
import aiki.map.pokemon.enums.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EditorStatFormTest extends InitEditorPkForm {
    @Test
    public void statForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<Statistic> cTr_ = crudTrStat(sub_);
        cTr_.getFields().getVal(Statistic.SPEED).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedStatistics().getVal(pr_.getLanguage()).getVal(Statistic.SPEED));
    }
    @Test
    public void statForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<TargetChoice> cTr_ = crudTrTarget(sub_);
        cTr_.getFields().getVal(TargetChoice.ANY_FOE).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedTargets().getVal(pr_.getLanguage()).getVal(TargetChoice.ANY_FOE));
    }
    @Test
    public void statForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<Gender> cTr_ = crudTrGender(sub_);
        cTr_.getFields().getVal(Gender.NO_GENDER).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedGenders().getVal(pr_.getLanguage()).getVal(Gender.NO_GENDER));
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().setVarParamsMove(new StringMap<StringList>());
        return f_;
    }
    private CrudGeneFormTrCst<Statistic> crudTrStat(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstStatMenu());
        return _crud.getCrudGeneFormCstStat();
    }
    private CrudGeneFormTrCst<TargetChoice> crudTrTarget(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstTargetMenu());
        return _crud.getCrudGeneFormCstTarget();
    }
    private CrudGeneFormTrCst<Gender> crudTrGender(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstGenderMenu());
        return _crud.getCrudGeneFormCstGender();
    }
}
