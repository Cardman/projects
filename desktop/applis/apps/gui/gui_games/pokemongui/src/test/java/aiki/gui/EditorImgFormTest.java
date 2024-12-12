package aiki.gui;

import aiki.db.DataBase;
import aiki.db.ImageArrayBaseSixtyFour;
import aiki.facade.*;
import aiki.fight.enums.Statistic;
import aiki.game.player.enums.Sex;
import aiki.gui.components.editor.*;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import aiki.sml.*;
import code.gui.*;
import code.gui.files.*;
import code.images.*;
import code.mock.*;
import code.sml.*;
import code.sml.core.*;
import code.stream.*;
import code.util.StringMap;
import org.junit.Test;

public final class EditorImgFormTest extends InitEditorPkForm {
    @Test
    public void imgForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMiniPk().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().get(0).getKey());
    }

    @Test
    public void imgForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelImg gSec_ = (GeneComponentModelImg)c_.getGene();
        assertEq(1,gSec_.getContent().getEdited().getImage().length);
    }
    @Test
    public void imgForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1, facade_.getData().getMiniPk().getVal(P_1).getImage().length);
    }
    @Test
    public void imgForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0, facade_.getData().getMiniPk().size());
        assertEq(0, c_.getList().size());
    }
    @Test
    public void imgForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAddEmptyDoc(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoadEmpty(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getMiniPk().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm6() {
        MockProgramInfos pr_ = initForms();
        messages(pr_);
        FacadeGame facade_ = core(pr_);
        facade_.getData().getTranslatedPokemon().addEntry(pr_.getLanguage(),new StringMap<String>());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getMiniPk().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAddEmpty(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoadEmpty(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getMiniPk().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getCancel());
        assertEq(0,facade_.getData().getMiniPk().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMaxiBackPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMaxiPkBack().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().get(0).getKey());
    }
    @Test
    public void imgForm10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMaxiFrontPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMaxiPkFront().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().get(0).getKey());
    }
    @Test
    public void imgForm11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniIt(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(I_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMiniItems().size());
        assertEq(1,c_.getList().size());
        assertEq(I_1,c_.getList().get(0).getKey());
    }
    @Test
    public void imgForm12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniSt(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getAnimStatus().size());
        assertEq(1,c_.getList().size());
        assertEq(S_1,c_.getList().get(0).getKey());
    }
    @Test
    public void imgForm13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniTy(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImg g_ = (GeneComponentModelImg)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(T_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getTypesImages().size());
        assertEq(1,c_.getList().size());
        assertEq(T_1,c_.getList().get(0).getKey());
    }
    @Test
    public void imgForm14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getLinks().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().get(0).getKey());
    }

    @Test
    public void imgForm15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_,"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryLoad(g_,"_2");
        GeneComponentModelImgFree gSec_ = (GeneComponentModelImgFree)c_.getGene();
        assertEq(1,gSec_.getContent().getEdited().getImage().length);
        assertEq(2,gSec_.getContent().getEdited().getImage()[0][0]);
    }
    @Test
    public void imgForm16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_,"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryLoad(g_,"_2");
        tryClick(c_.getValidAddEdit());
        assertEq(1, facade_.getData().getLinks().getVal(P_1).getImage().length);
        assertEq(1, facade_.getData().getLinks().getVal(P_1).getImage().length);
        assertEq(2, facade_.getData().getLinks().getVal(P_1).getImage()[0][0]);
    }
    @Test
    public void imgForm17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0, facade_.getData().getMiniPk().size());
        assertEq(0, c_.getList().size());
    }
    @Test
    public void imgForm18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAddEmptyDoc(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoadEmpty(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getMiniPk().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm19() {
        MockProgramInfos pr_ = initForms();
        messages(pr_);
        FacadeGame facade_ = core(pr_);
        facade_.getData().getTranslatedPokemon().addEntry(pr_.getLanguage(),new StringMap<String>());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getMiniPk().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm20() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAddEmpty(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoadEmpty(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getMiniPk().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm21() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_);
        tryClick(c_.getCancel());
        assertEq(0,facade_.getData().getMiniPk().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm22() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        ((GeneComponentModelImgFree)c_.getGene()).getKey().setText(P_2);
        tryClick(c_.getValidAddEdit());
        assertEq(1, facade_.getData().getLinks().size());
        assertEq(1, facade_.getData().getLinks().getVal(P_2).getImage().length);
        assertEq(1, facade_.getData().getLinks().getVal(P_2).getImage()[0][0]);
    }
    @Test
    public void imgForm23() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_,"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAdd());
        ((GeneComponentModelImgFree)c_.getGene()).getKey().setText(P_1);
        tryLoad(g_,"_2");
        tryClick(c_.getValidAddEdit());
        assertEq(1, facade_.getData().getLinks().size());
        assertEq(1, facade_.getData().getLinks().getVal(P_1).getImage().length);
        assertEq(1, facade_.getData().getLinks().getVal(P_1).getImage()[0][0]);
    }
    @Test
    public void imgForm24() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudLinks(sub_);
        tryClick(c_.getAdd());
        ((GeneComponentModelImgFree)c_.getGene()).getKey().setText(P_1);
        tryLoad((GeneComponentModelImgFree)c_.getGene(),"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAdd());
        ((GeneComponentModelImgFree)c_.getGene()).getKey().setText(P_2);
        tryLoad((GeneComponentModelImgFree)c_.getGene(),"_2");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(1));
        ((GeneComponentModelImgFree)c_.getGene()).getKey().setText(P_1);
        tryLoad(((GeneComponentModelImgFree)c_.getGene()),"_3");
        tryClick(c_.getValidAddEdit());
        assertEq(2, facade_.getData().getLinks().size());
        assertEq(1, facade_.getData().getLinks().getVal(P_1).getImage().length);
        assertEq(1, facade_.getData().getLinks().getVal(P_1).getImage()[0][0]);
        assertEq(1, facade_.getData().getLinks().getVal(P_2).getImage().length);
        assertEq(2, facade_.getData().getLinks().getVal(P_2).getImage()[0][0]);
    }
    @Test
    public void imgForm25() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudPeople(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPeople().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().get(0).getKey());
    }
    @Test
    public void imgForm26() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudTrainers(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getTrainers().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().get(0).getKey());
    }
    @Test
    public void imgForm27() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudBlocks(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getImages().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().get(0).getKey());
    }
    @Test
    public void imgForm28() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgFree c_ = crudMiniMap(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMiniMap().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().get(0).getKey());
    }
    @Test
    public void imgForm29() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgType c_ = crudTypeColor(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgType g_ = (GeneComponentModelImgType)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(T_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getTypesColors().size());
        assertEq(51+ DataBase.SEPARATOR_RGB+117+DataBase.SEPARATOR_RGB+167,facade_.getData().getTypesColors().getVal(T_1));
        assertEq(1,c_.getList().size());
        assertEq(T_1,c_.getList().get(0).getKey());
    }

    @Test
    public void imgForm30() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgType c_ = crudTypeColor(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgType g_ = (GeneComponentModelImgType)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(T_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelImgType gSec_ = (GeneComponentModelImgType)c_.getGene();
        assertEq(51,gSec_.getRed().getValue());
        assertEq(117,gSec_.getGreen().getValue());
        assertEq(167,gSec_.getBlue().getValue());
    }
    @Test
    public void imgForm31() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgType c_ = crudTypeColor(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgType g_ = (GeneComponentModelImgType)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(T_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        ((GeneComponentModelImgType)c_.getGene()).getBlue().setValue(180);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getTypesColors().size());
        assertEq(51+ DataBase.SEPARATOR_RGB+117+DataBase.SEPARATOR_RGB+180,facade_.getData().getTypesColors().getVal(T_1));
    }
    @Test
    public void imgForm32() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgType c_ = crudTypeColor(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgType g_ = (GeneComponentModelImgType)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0, facade_.getData().getTypesColors().size());
        assertEq(0, c_.getList().size());
    }
    @Test
    public void imgForm33() {
        MockProgramInfos pr_ = initForms();
        messages(pr_);
        FacadeGame facade_ = core(pr_);
        facade_.getData().getTranslatedTypes().addEntry(pr_.getLanguage(),new StringMap<String>());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgType c_ = crudTypeColor(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgType g_ = (GeneComponentModelImgType)c_.getGene();
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getTypesColors().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm34() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgType c_ = crudTypeColor(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelImgType g_ = (GeneComponentModelImgType)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getCancel());
        assertEq(0,facade_.getData().getTypesColors().size());
        assertEq(0,c_.getList().size());
    }
    @Test
    public void imgForm35() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgUniq c_ = crudOtherImg(sub_);
        tryLoad(c_.getFields().getValue(0), "_1");
        tryClick(c_.getButtons().getValue(0));
        assertEq(1,facade_.getData().getAnimAbsorb().getImage()[0][0]);
    }
    @Test
    public void imgForm36() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgUniq c_ = crudOtherImg(sub_);
        tryLoad(c_.getFields().getValue(1), "_1");
        tryClick(c_.getButtons().getValue(1));
        assertEq(1,facade_.getData().getEndGameImage().getImage()[0][0]);
    }
    @Test
    public void imgForm37() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgUniq c_ = crudOtherImg(sub_);
        tryLoad(c_.getFields().getValue(2), "_1");
        tryClick(c_.getButtons().getValue(2));
        assertEq(1,facade_.getData().getStorage().getImage()[0][0]);
    }
    @Test
    public void imgForm38() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgUniq c_ = crudOtherImg(sub_);
        tryLoad(c_.getFields().getValue(3), "_1");
        tryClick(c_.getButtons().getValue(3));
        assertEq(1,facade_.getData().getImageTmHm().getImage()[0][0]);
    }
    @Test
    public void imgForm39() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getAnimStatis().put(Statistic.ATTACK.getStatName(), ImageArrayBaseSixtyFour.instance());
        facade_.getData().getAnimStatis().put(Statistic.DEFENSE.getStatName(), ImageArrayBaseSixtyFour.instance());
        facade_.getData().getAnimStatis().put(Statistic.SPECIAL_ATTACK.getStatName(), ImageArrayBaseSixtyFour.instance());
        facade_.getData().getAnimStatis().put(Statistic.SPECIAL_DEFENSE.getStatName(), ImageArrayBaseSixtyFour.instance());
        facade_.getData().getAnimStatis().put(Statistic.SPEED.getStatName(), ImageArrayBaseSixtyFour.instance());
        facade_.getData().getAnimStatis().put(Statistic.ACCURACY.getStatName(), ImageArrayBaseSixtyFour.instance());
        facade_.getData().getAnimStatis().put(Statistic.EVASINESS.getStatName(), ImageArrayBaseSixtyFour.instance());
        facade_.getData().getAnimStatis().put(Statistic.CRITICAL_HIT.getStatName(), ImageArrayBaseSixtyFour.instance());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgCstList c_ = crudCstListImg(sub_);
        tryLoad(c_.getFields().getVal(Statistic.SPEED), "_1");
        tryClick(c_.getButtons().getVal(Statistic.SPEED));
        assertEq(1,facade_.getData().getAnimStatis().getVal(Statistic.SPEED.getStatName()).getImage()[0][0]);
    }
    @Test
    public void imgForm40() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosMiniImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.UP);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getOverWorldHeros().size());
    }
    @Test
    public void imgForm41() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosMiniImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.UP);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getOverWorldHeros().size());
    }
    @Test
    public void imgForm42() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosMiniImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.UP);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.UP);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getOverWorldHeros().size());
    }
    @Test
    public void imgForm43() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosMiniImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.UP);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_2");
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getOverWorldHeros().size());
    }
    @Test
    public void imgForm44() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosMiniImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.UP);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0,facade_.getData().getOverWorldHeros().size());
    }
    @Test
    public void imgForm45() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosFrontImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getFrontHeros().size());
    }
    @Test
    public void imgForm46() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosFrontImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_2");
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getFrontHeros().size());
    }
    @Test
    public void imgForm47() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosBackImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getBackHeros().size());
    }
    @Test
    public void imgForm48() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosBackImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_2");
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getBackHeros().size());
    }
    @Test
    public void imgForm49() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosMiniImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.UP);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.DOWN);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        assertEq(2,facade_.getData().getOverWorldHeros().size());
    }
    @Test
    public void imgForm50() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.setSexList(new MockLSexList());
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImgHeros c_ = crudHerosMiniImg(sub_);
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.UP);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAdd());
        c_.getGeneComponentModelImgHeros().getDirection().setupValue(Direction.DOWN);
        c_.getGeneComponentModelImgHeros().getSex().setupValue(Sex.NO);
        c_.getGeneComponentModelImgHeros().getEnvironment().setupValue(EnvironmentType.ROAD);
        tryLoad(c_.getGeneComponentModelImgHeros().getContent(),"_1");
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getOverWorldHeros().size());
    }
    private void tryLoad(GeneComponentModelImg _g) {
        tryLoad(_g, "_1");
    }
    private void tryLoadEmpty(GeneComponentModelImg _g) {
        tryLoad(_g, "_");
    }
    private void tryLoad(GeneComponentModelImg _g, String _f) {
        tryLoad(_g.getContent(), _f);
    }
    private void tryLoad(GeneComponentModelImgFree _g) {
        tryLoad(_g, "_1");
    }
    private void tryLoadEmpty(GeneComponentModelImgFree _g) {
        tryLoad(_g, "_");
    }
    private void tryLoad(GeneComponentModelImgFree _g, String _f) {
        tryLoad(_g.getContent(), _f);
    }

    private void tryLoad(ContentGeneComponentModelImg _g, String _f) {
        _g.getFileDialogContent().getFileName().setText(_f);
        ((MockAbstractAction) GuiBaseUtil.getAction(_g.getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        ((MockPlainButton) _g.getFileDialogContent().getButtons().getComponent(0)).getActionListeners().first().action();
    }
    private void tryLoad(GeneComponentModelImgType _g) {
        _g.getRed().setValue(51);
        _g.getGreen().setValue(117);
        _g.getBlue().setValue(167);
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        messages(_m);
        FacadeGame f_ = facade(_m);
        _m.getFileCoreStream().newFile("/__/").mkdirs();
        StreamTextFile.saveTextFile("/__/_1",imgDoc(new int[][]{new int[]{1}},_m),_m.getStreams());
        StreamTextFile.saveTextFile("/__/_2",imgDoc(new int[][]{new int[]{2}},_m),_m.getStreams());
        StreamTextFile.saveTextFile("/__/_3",imgDoc(new int[][]{new int[]{3}},_m),_m.getStreams());
        return f_;
    }
    private FacadeGame facadeAddEmpty(MockProgramInfos _m) {
        messages(_m);
        FacadeGame f_ = facade(_m);
        _m.getFileCoreStream().newFile("/__/").mkdirs();
        StreamTextFile.saveTextFile("/__/_","",_m.getStreams());
        return f_;
    }
    private FacadeGame facadeAddEmptyDoc(MockProgramInfos _m) {
        messages(_m);
        FacadeGame f_ = facade(_m);
        _m.getFileCoreStream().newFile("/__/").mkdirs();
        StreamTextFile.saveTextFile("/__/_","<_/>",_m.getStreams());
        return f_;
    }

    private void messages(MockProgramInfos _m) {
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(_m.getTranslations()));
        MessagesGuiFct.enTr(MessagesGuiFct.initAppliTr(_m.getTranslations().getMapping().getVal(EN)));
        MessagesGuiFct.frTr(MessagesGuiFct.initAppliTr(_m.getTranslations().getMapping().getVal(FR)));
    }

    private static String imgDoc(int[][] _img, MockProgramInfos _pr) {
//    private static String imgDoc(ImageArrayBaseSixtyFour _img, String _kind, String _name)
        String base_ = GamesPk.baseEncode(_pr.getTranslations());
        String res_ = BaseSixtyFourUtil.getStringByImage(_img, base_);
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(DocumentWriterCoreUtil.ANON_TAG);
//        element_.setAttribute(DocumentWriterCoreUtil.FIELD,_kind);
//        element_.setAttribute(DocumentWriterCoreUtil.VALUE,_name);
        element_.setAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG,res_);
        element_.setAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG_BASE, base_);
        doc_.appendChild(element_);
        return doc_.export();
    }

    private CrudGeneFormEntImg crudMiniPk(WindowPkEditor _crud) {
        tryClick(_crud.getImgMiniPkMenu());
        return _crud.getCrudGeneFormMiniPk();
    }

    private CrudGeneFormEntImg crudMaxiBackPk(WindowPkEditor _crud) {
        tryClick(_crud.getImgMaxiBackPkMenu());
        return _crud.getCrudGeneFormMaxiBackPk();
    }

    private CrudGeneFormEntImg crudMaxiFrontPk(WindowPkEditor _crud) {
        tryClick(_crud.getImgMaxiFrontPkMenu());
        return _crud.getCrudGeneFormMaxiFrontPk();
    }

    private CrudGeneFormEntImg crudMiniIt(WindowPkEditor _crud) {
        tryClick(_crud.getImgMiniItMenu());
        return _crud.getCrudGeneFormMiniIt();
    }

    private CrudGeneFormEntImg crudMiniSt(WindowPkEditor _crud) {
        tryClick(_crud.getImgMiniStMenu());
        return _crud.getCrudGeneFormMiniSt();
    }

    private CrudGeneFormEntImg crudMiniTy(WindowPkEditor _crud) {
        tryClick(_crud.getImgMiniTyMenu());
        return _crud.getCrudGeneFormMiniTy();
    }

    private CrudGeneFormEntImgFree crudLinks(WindowPkEditor _crud) {
        tryClick(_crud.getImgLinksMenu());
        return _crud.getCrudGeneFormLinks();
    }

    private CrudGeneFormEntImgFree crudPeople(WindowPkEditor _crud) {
        tryClick(_crud.getImgPeopleMenu());
        return _crud.getCrudGeneFormPeople();
    }

    private CrudGeneFormEntImgFree crudTrainers(WindowPkEditor _crud) {
        tryClick(_crud.getImgTrainersMenu());
        return _crud.getCrudGeneFormTrainers();
    }

    private CrudGeneFormEntImgFree crudBlocks(WindowPkEditor _crud) {
        tryClick(_crud.getImgBlocksMenu());
        return _crud.getCrudGeneFormBlocks();
    }

    private CrudGeneFormEntImgFree crudMiniMap(WindowPkEditor _crud) {
        tryClick(_crud.getImgMiniMapMenu());
        return _crud.getCrudGeneFormMiniMap();
    }

    private CrudGeneFormEntImgType crudTypeColor(WindowPkEditor _crud) {
        tryClick(_crud.getImgTypeColorMenu());
        return _crud.getCrudGeneFormTypeColor();
    }

    private CrudGeneFormEntImgUniq crudOtherImg(WindowPkEditor _crud) {
        tryClick(_crud.getImgOtherMenu());
        return _crud.getCrudGeneFormImgOther();
    }

    private CrudGeneFormEntImgCstList crudCstListImg(WindowPkEditor _crud) {
        tryClick(_crud.getImgCstListMenu());
        return _crud.getCrudGeneFormCstList();
    }
    private CrudGeneFormEntImgHeros crudHerosMiniImg(WindowPkEditor _crud) {
        tryClick(_crud.getImgHerosMiniMenu());
        return _crud.getCrudGeneFormEntImgHerosMini();
    }
    private CrudGeneFormEntImgHeros crudHerosFrontImg(WindowPkEditor _crud) {
        tryClick(_crud.getImgHerosFrontMenu());
        return _crud.getCrudGeneFormEntImgHerosFront();
    }
    private CrudGeneFormEntImgHeros crudHerosBackImg(WindowPkEditor _crud) {
        tryClick(_crud.getImgHerosBackMenu());
        return _crud.getCrudGeneFormEntImgHerosBack();
    }
}
