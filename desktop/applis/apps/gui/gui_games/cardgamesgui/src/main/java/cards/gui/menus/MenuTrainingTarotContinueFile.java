package cards.gui.menus;

import cards.gui.WindowCards;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.enumerations.ChoiceTarot;
import code.gui.MenuItemUtils;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileSaveDialogContent;

public final class MenuTrainingTarotContinueFile implements AbsContinueFile {
    private final WindowCards window;
    private final ChoiceTarot choiceTarot;

    public MenuTrainingTarotContinueFile(WindowCards _w, ChoiceTarot _c) {
        this.window = _w;
        this.choiceTarot = _c;
    }
    @Override
    public void next(FileSaveDialogContent _content) {
        window.getCore().setContainerGame(new ContainerSingleTarot(window));
        MenuItemUtils.setEnabledMenu(window.getChange(),true);
        ((ContainerSingleTarot) window.getCore().getContainerGame()).jouerDonneEntrainement(choiceTarot);
    }
}
