package aiki.gui.dialogs;

import aiki.db.ImageHeroKey;
import aiki.facade.FacadeGame;
import aiki.game.player.enums.Sex;
import aiki.gui.WindowAiki;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.components.labels.HeroLabel;
import aiki.gui.dialogs.events.ClosingDialogHerosEvent;
import aiki.gui.events.ConfirmNewGameEvent;
import aiki.gui.listeners.HeroSelect;
import aiki.map.levels.enums.EnvironmentType;
import aiki.sml.MessagesPkGame;
import aiki.sml.MessagesRenderHeros;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class DialogHeros {

    private final AbsCommonFrame frame;
    private final AbsPanel beginGame;
    private final CustList<AbsMetaLabelPk> labsBegin = new CustList<AbsMetaLabelPk>();

    private final IdMap<Sex,HeroLabel> herosLabels = new IdMap<Sex,HeroLabel>();

    private final AbsTextField nickname;

    private AbsButton confirmNewGame;
    private final AbstractProgramInfos api;
    private final WindowAiki window;


    public DialogHeros(AbstractProgramInfos _programInfos, WindowAiki _win) {
        api = _programInfos;
        window = _win;
        beginGame = _programInfos.getCompoFactory().newPageBox();
        nickname = _programInfos.getCompoFactory().newTextField(16);
        frame = _programInfos.getFrameFactory().newCommonFrame();
        frame.addWindowListener(new ClosingDialogHerosEvent(_win));
    }

    public void display(WindowAiki _window) {
        _window.getModal().set(true);
        FacadeGame facade_ = _window.getFacade();
        beginGame.removeAll();
        labsBegin.clear();
        AbsPanel heros_ = api.getCompoFactory().newLineBox();
        for (Sex s: facade_.getSexList().all()) {
            ImageHeroKey i_;
            i_ = new ImageHeroKey(EnvironmentType.ROAD, s);
            int[][] imgTxt_ = facade_.getData().getFrontHeros().getVal(i_);
            HeroLabel label_ = new HeroLabel(api.getImageFactory(),imgTxt_, api.getCompoFactory(),_window.getTileRender());
            label_.setPreferredSize(new MetaDimension(imgTxt_[0].length, imgTxt_.length));
            label_.addMouseListener(new HeroSelect(this, s));
            herosLabels.put(s, label_);
            labsBegin.add(label_);
            heros_.add(label_.getPaintableLabel());
        }
        beginGame.add(heros_);
        TranslationsLg translationsLg_ = api.currentLg();
        StringMap<String> mapping_ = file(translationsLg_);
        beginGame.add(api.getCompoFactory().newPlainLabel(mapping_.getVal(MessagesRenderHeros.NICKNAME)));
        beginGame.add(nickname);
        confirmNewGame = api.getCompoFactory().newPlainButton(mapping_.getVal(MessagesRenderHeros.VALIDATE_NICKNAME));
        confirmNewGame.addActionListener(new ConfirmNewGameEvent(_window, nickname));
        confirmNewGame.setEnabled(false);
        beginGame.add(confirmNewGame);
        AbsMetaLabelPk.repaintChildren(labsBegin,api.getImageFactory());
        frame.setContentPane(beginGame);
        frame.pack();
        frame.setVisible(true);
    }

    public static StringMap<String> file(TranslationsLg _lg) {
        return MessagesPkGame.getHerosContentTr(MessagesPkGame.getAppliTr(_lg)).getMapping();
    }
    public void changeSex(Sex _sex) {
        window.setChosenSex(_sex);
        confirmNewGame.setEnabled(true);
        herosLabels.getVal(_sex.getOppositeSex()).setSelected(false);
        herosLabels.getVal(_sex).setSelected(true);
        AbsMetaLabelPk.repaintChildren(labsBegin,api.getImageFactory());
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public CustList<AbsMetaLabelPk> getLabsBegin() {
        return labsBegin;
    }

    public AbsTextField getNickname() {
        return nickname;
    }

    public AbsButton getConfirmNewGame() {
        return confirmNewGame;
    }
}
