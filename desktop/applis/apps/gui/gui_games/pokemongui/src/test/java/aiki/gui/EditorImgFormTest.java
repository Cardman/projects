package aiki.gui;

import aiki.facade.*;
import aiki.gui.components.editor.*;
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
        GeneComponentModelMiniPk g_ = (GeneComponentModelMiniPk)c_.getGene();
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
        GeneComponentModelMiniPk g_ = (GeneComponentModelMiniPk)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMiniPk gSec_ = (GeneComponentModelMiniPk)c_.getGene();
        assertEq(1,gSec_.getEdited().getImage().length);
    }
    @Test
    public void imgForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEntImg c_ = crudMiniPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMiniPk g_ = (GeneComponentModelMiniPk)c_.getGene();
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
        GeneComponentModelMiniPk g_ = (GeneComponentModelMiniPk)c_.getGene();
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
        GeneComponentModelMiniPk g_ = (GeneComponentModelMiniPk)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
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
        GeneComponentModelMiniPk g_ = (GeneComponentModelMiniPk)c_.getGene();
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
        GeneComponentModelMiniPk g_ = (GeneComponentModelMiniPk)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
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
        GeneComponentModelMiniPk g_ = (GeneComponentModelMiniPk)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryLoad(g_);
        tryClick(c_.getCancel());
        assertEq(0,facade_.getData().getMiniPk().size());
        assertEq(0,c_.getList().size());
    }
    private void tryLoad(GeneComponentModelImg _g) {
        _g.getFileDialogContent().getFileName().setText("_");
        ((MockAbstractAction) GuiBaseUtil.getAction(_g.getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        ((MockPlainButton) _g.getFileDialogContent().getButtons().getComponent(0)).getActionListeners().first().action();
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        messages(_m);
        FacadeGame f_ = facade(_m);
        _m.getFileCoreStream().newFile("/__/").mkdirs();
        StreamTextFile.saveTextFile("/__/_",imgDoc(new int[][]{new int[]{1}},_m),_m.getStreams());
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
}
