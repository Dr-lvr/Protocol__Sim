package logic;

import java.util.Vector;

/*
    This configuration provide the manage of the computer network according by the algorithm inserted by enum triggering the passing
    of a particular animation config at the win.

    It is important to notice that with this abstraction and message passing is now possible to implement rapresentation of variuos
    type of network algorithm each-other independent by, in form of enumerated instruction | Controller->trigger ALGO::TOKEN RING
    setting the rappresentation by real data of the network
     */
    public class Controller {

        private Controller theController;
        private Vector<ComputerUnit> myNetwork;//data structure for mapping the network, represent the logic network

        public Controller() {
            //at least initialize data structures
            myNetwork = new Vector<ComputerUnit>();
        }
        public Controller getControllerInstance() {
            if (theController == null) {
                theController = new Controller();
            }
            return theController;
        }
        public void add(ComputerUnit aComputer){
            myNetwork.add(aComputer);
        }
        public int getNetworkSize(){
            return myNetwork.size();
        }
        public boolean testNetwork(){
            for (ComputerUnit cp : myNetwork){
                if(cp.isRequest()){
                    //manage requests
                };
            }
            return false;
        }
        public boolean testIsWriting(){
            for (ComputerUnit cp : myNetwork){
                if(cp.isWriting()){
                    //manage busy
                };
            }
            return false;
        }
    }

