package com.github.jotask.tusk.online.server;

/**
 * Created by Jota on 27/05/2016.
 */
public class ServerUpdater implements Runnable {

    private final int TIME = 100;

    private boolean stop = false;

    private final TuskServer tuskServer;

    public ServerUpdater(TuskServer tuskServer) { this.tuskServer = tuskServer; }

    @Override
    public void run() {

        while (!stop){

            try {
                Thread.sleep(TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("exiting thread");

    }

    public void stop(){
        this.stop = true;
    }

}
