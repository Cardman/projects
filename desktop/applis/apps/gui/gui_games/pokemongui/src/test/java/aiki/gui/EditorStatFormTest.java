package aiki.gui;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.enums.*;
import aiki.gui.components.editor.*;
import aiki.map.levels.enums.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
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
    @Test
    public void statForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<EnvironmentType> cTr_ = crudTrEnvironmentType(sub_);
        cTr_.getFields().getVal(EnvironmentType.NOTHING).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedEnvironment().getVal(pr_.getLanguage()).getVal(EnvironmentType.NOTHING));
    }
    @Test
    public void strList1() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        tryClick(g_.crud().getAdd());
        g_.crud().getGenePair().getKey().setupValue(M_1);
        tryClick(g_.crud().getValidAddEdit());
        assertEq(1,g_.crud().getList().size());
        assertEq(M_1,g_.crud().getList().get(0));
    }
    @Test
    public void strList2() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        tryClick(g_.crud().getAdd());
        g_.crud().getGenePair().getKey().setupValue(M_1);
        tryClick(g_.crud().getValidAddEdit());
        tryClick(g_.crud().getAllButtons().get(0));
        g_.crud().getGenePair().getKey().setupValue(M_2);
        tryClick(g_.crud().getValidAddEdit());
        assertEq(1,g_.crud().getList().size());
        assertEq(M_2,g_.crud().getList().get(0));
    }
    @Test
    public void strList3() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        tryClick(g_.crud().getAdd());
        g_.crud().getGenePair().getKey().setupValue(M_1);
        tryClick(g_.crud().getValidAddEdit());
        tryClick(g_.crud().getAllButtons().get(0));
        tryClick(g_.crud().getValidRemove());
        assertEq(0,g_.crud().getList().size());
    }
    @Test
    public void strList4() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        g_.setupValue(new StringList(M_1));
        assertEq(1,g_.crud().getList().size());
        assertEq(M_1,g_.crud().getList().get(0));
    }

    @Test
    public void strList5() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        g_.setupValue(new StringList(M_1));
        StringList ls_ = g_.tryRet();
        assertEq(1,ls_.size());
        assertEq(M_1,ls_.get(0));
    }
    @Test
    public void strList6() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(0,0);
        g_.setupValue(new StringList(M_1));
        StringList ls_ = g_.tryRet();
        assertEq(1,ls_.size());
        assertEq(M_1,ls_.get(0));
        assertFalse(g_.getSubs().isEmpty());
    }
    private GeneComponentModelSubscribeStringList gene() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        SubscribedTranslationList sub_ = new SubscribedTranslationList(pr_,facade_);
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame();
        sub_.getSubscribedTranslations().addEntry(fr_,new IdList<SubscribedTranslation>());
        return new GeneComponentModelSubscribeStringList(pr_, facade_, sub_, fr_, sub_.getFactoryMv());
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
    private CrudGeneFormTrCst<EnvironmentType> crudTrEnvironmentType(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstEnvironmentTypeMenu());
        return _crud.getCrudGeneFormCstEnvironmentType();
    }
}
