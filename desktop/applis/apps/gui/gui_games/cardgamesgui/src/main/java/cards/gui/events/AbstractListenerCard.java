package cards.gui.events;

import cards.gui.WindowCards;
import cards.gui.containers.ContainerPlayableGame;
import cards.gui.containers.ContainerSin;
import cards.gui.containers.ContainerSingleImpl;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public abstract class AbstractListenerCard<T> implements AbsMouseListenerIntRel {

    private final ContainerPlayableGame containerBase;
    private final T card;
    private final byte index;

    protected AbstractListenerCard(ContainerPlayableGame _container, T _c) {
        this(_container,_c,(byte)1);
    }
    protected AbstractListenerCard(ContainerPlayableGame _container, T _c, byte _i) {
        containerBase = _container;
        this.card = _c;
        index = _i;
    }

    public T getCard() {
        return card;
    }

    public byte getIndex() {
        return index;
    }

    //protected abstract void affecterCarteSurvolee();
//    protected abstract void jeuCarte(boolean _carteSurvolee);
    protected abstract void verifierRegles();
//    protected abstract boolean playCardExited(AbsMouseLocation _event);
//    protected void testEntreeSortie() {
//        if(!containerBase.isThreadAnime()&& containerBase.isCarteSortie()&&!containerBase.isCarteEntree()) {
//            jeuCarte(true);
//        }
//    }
//    protected boolean clicCarte() {
//        return containerBase.getParametres().getJeuCarteClic();
//    }
//    @Override
//    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        if (!enabledEvents(containerBase)) {
//            return;
//        }
//        if(!clicCarte()) {
//            containerBase.setCarteEntree(true);
//            containerBase.setCarteSortie(false);
//            affecterCarteSurvolee();
//            testEntreeSortie();
//        }
//    }
//
//    @Override
//    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        if (!enabledEvents(containerBase)) {
//            return;
//        }
//        if(!clicCarte()) {
//            if (!playCardExited(_location)) {
//                return;
//            }
//            containerBase.setCarteSortie(true);
//            containerBase.setCarteEntree(false);
//            affecterCarteSurvolee();
//            testEntreeSortie();
//        }
//    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!enabledEvents(containerBase)) {
            return;
        }
        verifierRegles();
//        jeuCarte(false);
//        if(clicCarte()) {
//            jeuCarte(false);
//        }
//        if(containerBase.isaJoueCarte()&&clicCarte()) {
//            containerBase.setRaisonCourante(containerBase.getMessages().getVal(WindowCards.ALREADY_PLAYED));
//            containerBase.setaJoueCarte(false);
//        }
    }

//    public static boolean enabledEvents(Containable _c,AbstractListenerCardList _a) {
//        return enabledEvents(_c);
////        return enabledEvents(_c)&&_a.canListen();
//    }

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
//    @Override
//    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }
}
