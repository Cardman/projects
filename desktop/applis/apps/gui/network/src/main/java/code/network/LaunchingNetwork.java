package code.network;

import aiki.main.AikiFactory;
import cards.main.CardFactories;
import code.gui.AbsButton;
import code.gui.AdvSoftApplicationCore;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractBaseExecutorService;

public final class LaunchingNetwork extends AdvSoftApplicationCore {

    private final CardFactories cardFactories;
    private final AikiFactory aikiFactory;
    private final AbsButton button;
    private final AbstractBaseExecutorService serv;
    public LaunchingNetwork(AbstractProgramInfos _frames, CardFactories _c, AikiFactory _a, AbsButton _b, AbstractBaseExecutorService _service) {
        super(_frames);
        cardFactories = _c;
        aikiFactory = _a;
        button = _b;
        serv = _service;
    }

    @Override
    protected String getApplicationName() {
        return getMainWindowClass();
    }

    @Override
    protected void launch(String _language, String[] _args) {
        serv.execute(new LaunchNetwork(_language,getFrames(),cardFactories,aikiFactory,button));
    }

    public static String getMainWindowClass() {
        return "network";
    }
}
