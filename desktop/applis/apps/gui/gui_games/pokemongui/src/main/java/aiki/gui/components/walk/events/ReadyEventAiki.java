//package aiki.gui.components.walk.events;
//
//import aiki.gui.WindowAiki;
//import aiki.network.stream.ReadyAiki;
//import code.gui.AbsCustCheckBox;
//import code.gui.events.AbsActionListener;
//
//public class ReadyEventAiki implements AbsActionListener {
//
//    private WindowAiki window;
//
//    private AbsCustCheckBox ready;
//
//    public ReadyEventAiki(WindowAiki _window, AbsCustCheckBox _ready) {
//        window = _window;
//        ready = _ready;
//    }
//
//    @Override
//    public void action() {
//        ReadyAiki choice_ = new ReadyAiki();
//        choice_.setIndex(window.getIndexInGame());
//        choice_.setReady(ready.isSelected());
//        window.sendObject(choice_);
//    }
//}
//