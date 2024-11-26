package cards.main;

import cards.gui.WindowCards;
import cards.gui.containers.*;
import code.gui.events.AbsActionListenerAct;

public final class CardsNonModalEvent implements AbsActionListenerAct {
    private final WindowCards window;
    private ContainerSin containerSingle;

    public CardsNonModalEvent(ContainerSin _c) {
        this(_c.window());
        containerSingle = _c;
    }

    public CardsNonModalEvent(WindowCards _w) {
        this.window = _w;
    }

    public static boolean enabledEvents(ContainerPlayableGame _c) {
        return aliveEvents(asContainerSingle(_c), null);
        //return !(_c instanceof ContainerSingle) || aliveEvents((ContainerSingle)_c, ((ContainerSingle)_c).window());
//        return !(_c instanceof ContainerSingle)||(aliveEvents((ContainerSingle)_c) &&!((ContainerSingle)_c).window().getModal().get());
    }

    private static ContainerSin asContainerSingle(ContainerPlayableGame _c) {
        if (!(_c instanceof ContainerSin)) {
            return null;
        }
        return (ContainerSin)_c;
    }

    public static boolean aliveEvents(ContainerSin _c, WindowCards _wc) {
        if (_c == null) {
            return _wc == null || !_wc.getModal().get();
        }
        if (_c.window().getPausingCardsAnims().stateChecked(_c) != ContainerSingleImpl.PAUSE_STOPPED) {
            return !_c.window().getModal().get();
        }
        _c.getEvents().append("||");
        return false;
    }

    @Override
    public boolean act() {
        return aliveEvents(containerSingle, window);
//        if (containerSingle != null) {
//            return AbstractListenerCard.aliveEvents(containerSingle, window);
//        }
//        return !window.getModal().get();
    }
}
