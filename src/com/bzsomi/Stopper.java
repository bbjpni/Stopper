package com.bzsomi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Stopper extends JFrame {
    JPanel mainPanel;
    JLabel szamlap;
    JButton start, time;
    Timer ido;
    TimerTask muvelet;
    int x = 0;

    public Stopper(){
        init();
    }

    private void init(){
        this.setTitle("Stopper");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

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
                ido = new Timer();
                muvelet = new TimerTask(){
                    public void run(){
                        x ++;
                        szamlap.setText(x+"");
                    }
                };
                //ido.schedule(muvelet.run(),);
            }
        });

        this.setVisible(true);
    }
}
