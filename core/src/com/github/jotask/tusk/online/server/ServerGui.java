package com.github.jotask.tusk.online.server;


import com.github.jotask.tusk.online.util.Network;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Jota on 27/05/2016.
 */
public class ServerGui {

    private TuskServer tuskServer;

    private JFrame frame;

    public LinkedList<Label> labels;

    public ServerGui(TuskServer server) {
        this.tuskServer = server;
        frame = new JFrame(){
            @Override
            public void dispose() {
                super.dispose();
                tuskServer.dispose();
            }
        };
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Server");
        frame.setSize(new Dimension(400, 300));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        labels = new LinkedList<Label>();

        updatePane();

        frame.setVisible(true);

    }

    public void addLabel(int id, Network.Character character){

        Label label = null;
        for(Label labs: labels) {
            if (labs.id == id) {
                label = labs;
                break;
            }
        }
        if(label == null){
            label = new Label();
            label.id = id;
            label.name = character.name;
            label.label = new JLabel();
            labels.add(label);
        }

        label.label.setText( label.name + " " + character.position.toString());

        updatePane();
    }

    public void updatePane(){
        frame.getContentPane().removeAll();
        JLabel prev = null;
        for(Label l: labels){
            Rectangle rectangle;
            if(prev != null){
                rectangle = prev.getBounds();
                rectangle.y += 13;
            }else{
                rectangle = new Rectangle(0,0,1000,100);
            }
            l.label.setBackground(Color.black);
            l.label.setBounds(rectangle);
            frame.add(l.label);
            prev = l.label;
        }
        frame.repaint();
    }

    public void updateLabel(Network.Character character) {

        int id = character.id;

        boolean exist = false;
        for(Label l: labels){
            if(l.id == id){
                exist = true;
                break;
            }
        }

        if(!exist){
            addLabel(id, character );
        }else {
            for (Label l : labels) {
                if (l.id == id) {
                    l.label.setText(l.name + " " + character.position.toString());
                    break;
                }
            }
        }

        updatePane();

    }

    public void removeLabel(int id) {

        boolean exist = false;
        int index = -1;
        for(Label l: labels){
            if(l.id == id){
                index = labels.indexOf(l);
                exist = true;
                break;
            }
        }

        if(exist){
            labels.remove(index);
        }else {
            System.out.println("cant' eliminate not exist.");
        }

        updatePane();
    }

    public class Label {
        public int id;
        public String name;
        public JLabel label;
    }

}