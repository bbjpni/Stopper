package com.bzsomi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopper extends JFrame {
    JPanel mainPanel;
    JLabel szamlap;
    JButton start, time;
    int passing, p, mp, szmp;

    public Stopper(){
        init();
    }

    private void init(){
        this.setTitle("Stopper");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        passing = 0;

        start = new JButton("Start");
        start.setBounds(20,20,150,30);

        time = new JButton("Reset");
        time.setBounds(20,70,150,30);

        szamlap = new JLabel("00:00.000");
        szamlap.setBounds(190, 20, 370, 100);
        szamlap.setFont(new Font(Font.SERIF, Font.BOLD, 70));

        mainPanel = (JPanel) (this.getContentPane());
        mainPanel.add(start);
        mainPanel.add(time);
        mainPanel.add(szamlap);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        this.setVisible(true);
    }

    Timer t = new Timer(1, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            passing=passing+1000;
            p=(passing/60000)%60;
            mp=(passing/99000)%60;
            szmp=(passing/1000)%100;
            szamlap.setText(String.format("%02d",p)+":"+String.format("%02d",mp)+"."+String.format("%03d",szmp));

        }
    });

    private void start(){
        t.start();
    }
}
