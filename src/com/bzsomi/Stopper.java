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
import java.util.ArrayList;
import java.util.Date;

public class Stopper extends JFrame {
    JPanel mainPanel;
    JLabel szamlap, pillanat;
    JButton start, time;
    LocalTime idopont;
    boolean elinditva;
    Duration elteltIdo;
    ArrayList<Duration> reszidok;

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
        elteltIdo = Duration.ZERO;
        reszidok = new ArrayList<>();

        start = new JButton("Start");
        start.setBounds(20,20,150,30);

        time = new JButton("Reset");
        time.setBounds(20,70,150,30);
        time.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (time.getText() == "Részidő") {
                    reszidok.add(elteltIdo.plus( (Duration.between(idopont, LocalTime.now())) ));
                    String text = "<html>";
                    for (Duration ido : reszidok){
                        text += "<p>" + String.format("%02d:%02d.%03d",
                                ido.toMinutesPart(), ido.toSecondsPart(), ido.toMillisPart()) + "</p>";
                    }
                    text += "</html>";
                    pillanat.setText(text);
                }
                else {
                    szamlap.setText("00:00.000");
                    elteltIdo = Duration.ZERO;
                    elinditva = false;
                    reszidok = new ArrayList<>();
                    pillanat.setText("");
                    //start.setText("Start");
                    //time.setText("Reset");
                }
            }
        });

        szamlap = new JLabel("00:00.000");
        szamlap.setBounds(190, 20, 370, 100);
        szamlap.setFont(new Font(Font.SERIF, Font.BOLD, 70));

        pillanat = new JLabel("");
        pillanat.setBounds(190, 140, 370, 440);
        pillanat.setFont(new Font(Font.SERIF, Font.BOLD, 12));

        mainPanel = (JPanel) (this.getContentPane());
        mainPanel.add(start);
        mainPanel.add(time);
        mainPanel.add(szamlap);
        mainPanel.add(pillanat);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!t.isRunning()) {
                    t.start();
                    start.setText("Stop");
                    time.setText("Részidő");
                }
                else {
                    t.stop();
                    Duration temp = (Duration.between(idopont, LocalTime.now()));
                    elteltIdo = elteltIdo.plus(temp);
                    start.setText("Start");
                    time.setText("Reset");
                    elinditva = !elinditva;
                }
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
                else {

                    Duration temp = Duration.between(idopont, LocalTime.now());
                    temp = temp.plus(elteltIdo);
                    int p =  temp.toMinutesPart();
                    int mp =  temp.toSecondsPart();
                    int szmp = temp.toMillisPart();
                    szamlap.setText(String.format("%02d:%02d.%03d", p, mp, szmp));
                }

        }
    });
}
