package aiki.network;
import java.net.Socket;

import code.network.AddingPlayer;
import code.network.BasicServer;
import code.util.CustList;
import code.util.NatTreeMap;
import aiki.map.pokemon.PokemonPlayer;
import aiki.network.stream.Bye;
import aiki.network.stream.CheckCompatibility;
import aiki.network.stream.InitTrading;
import aiki.network.stream.NetPokemon;
import aiki.network.stream.NewPlayer;
import aiki.network.stream.Ok;
import aiki.network.stream.Quit;
import aiki.network.stream.Ready;
import aiki.network.stream.SentPokemon;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SendReceiveServer extends BasicServer {

    /**This class thread is independant from EDT*/
    public SendReceiveServer(Socket _socket) {
        super(_socket);
    }

//    public void run() {
//        try {
//            InputStreamReader isr_ = new InputStreamReader(getSocket().getInputStream());
//            BufferedReader in_ = new BufferedReader(isr_);
//            String input_;
//
//            while (true) {
//                input_ = in_.readLine();
//                if (input_ == null) {
//                    break;
//                }
//                Object readObject_ = null;
//                try {
//                    readObject_ = SerializeXmlObject.fromXmlStringObject(input_);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    continue;
//                }
//                loop(input_, readObject_);
//            }
//            in_.close();
//            isr_.close();
//            getSocket().close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

    @Override
    public void loopServer(String _input, Object _object) {
        loop(_input, _object);
    }

    private static synchronized void loop(String _input, Object _readObject) {
        if (_readObject instanceof AddingPlayer) {
            AddingPlayer newPlayer_ = (AddingPlayer)_readObject;
            if (!newPlayer_.isAcceptable()) {
                Bye forcedBye_ = new Bye();
                forcedBye_.setBusy(true);
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                forcedBye_.setTooManyPlayers(true);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                removePlayer(newPlayer_.getIndex(), forcedBye_);
                return;
            }
        }
        if (_readObject instanceof NewPlayer) {
            NewPlayer newPlayer_ = (NewPlayer)_readObject;
            if (Net.getNicknames().size() == Net.NB_PLAYERS) {
                Bye forcedBye_ = new Bye();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                forcedBye_.setTooManyPlayers(true);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                removePlayer(newPlayer_.getIndex(), forcedBye_);
                return;
            }
            if (newPlayer_.getIndex() == CustList.SECOND_INDEX) {
                InitTrading init_ = new InitTrading();
                Net.sendObject(Net.getSockets().getVal((int)CustList.SECOND_INDEX),init_);
                return;
            }
            if (newPlayer_.getIndex() == CustList.FIRST_INDEX) {
                //init trading condition
                InitTrading init_ = new InitTrading();
                Net.sendObject(Net.getSockets().getVal((int)CustList.FIRST_INDEX),init_);
                return;
            }
            return;
        }
        if (_readObject instanceof CheckCompatibility) {
            CheckCompatibility check_ = (CheckCompatibility) _readObject;
            if (check_.getIndex() == CustList.FIRST_INDEX) {
                Net.getCheckCompatibility().put((int)CustList.FIRST_INDEX, check_);
                return;
            }
            if (check_.getIndex() == CustList.SECOND_INDEX) {
                CheckCompatibility first_ = Net.getCheckCompatibility().getVal((int)CustList.FIRST_INDEX);
                NatTreeMap<Byte, PokemonPlayer> pkFirst_ = check_.getData().getTeam(first_.getTeam());
                NatTreeMap<Byte, PokemonPlayer> pkSecond_ = first_.getData().getTeam(check_.getTeam());
                if (pkFirst_.isEmpty() || pkSecond_.isEmpty()) {
                    Bye forcedBye_ = new Bye();
                    forcedBye_.setForced(true);
                    forcedBye_.setClosing(false);
                    forcedBye_.setTooManyPlayers(false);
//                    Socket socket_ = Net.getSockets().getVal(CustList.SECOND_INDEX);
//                    Net.getSockets().removeKey(CustList.SECOND_INDEX);
//                    Net.getConnectionsServer().removeKey(CustList.SECOND_INDEX);
//                    Net.getReadyPlayers().removeKey(CustList.SECOND_INDEX);
//                    Net.getPlacesPlayers().removeKey(CustList.SECOND_INDEX);
//                    Net.sendObject(socket_,forcedBye_);
                    removePlayer(CustList.SECOND_INDEX, forcedBye_);
                    return;
                }
                NetPokemon net_ = new NetPokemon();
                net_.setTradablePokemon(pkFirst_);
                Net.sendObject(Net.getSockets().getVal((int)CustList.FIRST_INDEX),net_);
                net_ = new NetPokemon();
                net_.setTradablePokemon(pkSecond_);
                Net.sendObject(Net.getSockets().getVal((int)CustList.SECOND_INDEX),net_);
                return;
            }
        }
        if (_readObject instanceof SentPokemon) {
            SentPokemon sent_ = (SentPokemon) _readObject;
            if (sent_.getIndex() == CustList.FIRST_INDEX) {
                Net.sendObject(Net.getSockets().getVal((int)CustList.SECOND_INDEX),sent_.getPokemon());
            }
            if (sent_.getIndex() == CustList.SECOND_INDEX) {
                Net.sendObject(Net.getSockets().getVal((int)CustList.FIRST_INDEX),sent_.getPokemon());
            }
            return;
        }
        if (_readObject instanceof Ready) {
            int noClient_ = ((Ready)_readObject).getIndex();
            Net.getReadyPlayers().put(noClient_, (( Ready)_readObject).isReady());
            return;
        }
        if (_readObject instanceof Ok) {
            if (Net.allReady()) {
                for(Socket so_:Net.getSockets().values()){
                    Net.sendText(so_,_input);
                }
                for (int i: Net.getReadyPlayers().getKeys()) {
                    Net.getReadyPlayers().put(i, false);
                }
            }
            return;
        }
        if (_readObject instanceof Quit) {
            Quit bye_ = (Quit)_readObject;
            Net.quit(CustList.SECOND_INDEX);
            Net.quit(CustList.FIRST_INDEX);
            Bye forcedBye_ = new Bye();
            forcedBye_.setForced(false);
            forcedBye_.setServer(false);
            forcedBye_.setTooManyPlayers(false);
            Socket socket_;
            socket_ = Net.getSockets().getVal((int)CustList.FIRST_INDEX);
            Net.getConnectionsServer().removeKey((int)CustList.FIRST_INDEX);
            Net.getReadyPlayers().removeKey((int)CustList.FIRST_INDEX);
            Net.getPlacesPlayers().removeKey((int)CustList.FIRST_INDEX);
            if (bye_.getPlace() == CustList.FIRST_INDEX) {
                forcedBye_.setClosing(bye_.isClosing());
            } else {
                forcedBye_.setClosing(false);
            }
            if (socket_ != null) {
                Net.sendObject(socket_,forcedBye_);
            }
            socket_ = Net.getSockets().getVal((int)CustList.SECOND_INDEX);
            Net.getConnectionsServer().removeKey((int)CustList.SECOND_INDEX);
            Net.getReadyPlayers().removeKey((int)CustList.SECOND_INDEX);
            Net.getPlacesPlayers().removeKey((int)CustList.SECOND_INDEX);
            if (bye_.getPlace() == CustList.SECOND_INDEX) {
                forcedBye_.setClosing(bye_.isClosing());
            } else {
                forcedBye_.setClosing(false);
            }
            if (socket_ != null) {
                Net.sendObject(socket_,forcedBye_);
            }
            Net.getSockets().clear();
        }
    }

    static void removePlayer(int _player, Bye _bye) {
        Socket socket_ = Net.getSockets().getVal(_player);
        Net.getSockets().removeKey(_player);
        Net.getConnectionsServer().removeKey(_player);
        Net.getReadyPlayers().removeKey(_player);
        Net.getPlacesPlayers().removeKey(_player);
        Net.sendObject(socket_,_bye);
    }
}
