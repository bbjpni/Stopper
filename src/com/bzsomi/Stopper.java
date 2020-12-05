package com.bzsomi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Stopper extends JFrame {
    JPanel mainPanel;
    JLabel szamlap;
    JButton start, time;
    LocalTime idopont;
    boolean elinditva;

    public Stopper(){
        init();
    }

    private void init(){
        this.setTitle("Stopper");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        elinditva = false;

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

    Timer t = new Timer(0, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                if (!elinditva){
                    idopont = LocalTime.now();
                    elinditva = !elinditva;
                }
                Duration temp = Duration.between(idopont, LocalTime.now());
                int p =  temp.toMinutesPart();
                int mp =  temp.toSecondsPart();
                int szmp = temp.toMillisPart();
                szamlap.setText(String.format("%02d:%02d.%03d", p, mp, szmp));
        }
    });

    private void start(){
        t.start();
    }
}
