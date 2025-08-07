package applications.main;

import aiki.gui.components.editor.*;
import aiki.sml.*;
import applications.code.gui.*;
import applications.gui.*;
import code.gui.*;
import code.gui.images.*;

public class LaunchingPokemonEditor extends AdvSoftApplicationCore {

    public LaunchingPokemonEditor(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowPkEditor(DocumentReaderAikiCoreUtil.getEditingRom(_args.getDocument()), getFrames(),ConverterGraphicBufferedImage.decodeToImage(getFrames().getImageFactory(), MessagesApplications.pk()), getAppFactories().getAikiFactory().getFacade().getSexList(), _pair));
    }

}
