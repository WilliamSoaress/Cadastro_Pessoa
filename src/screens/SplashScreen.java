package screens;

/*Implementado por Dossi*/

import javax.swing.*;

import java.awt.*;

import static screens.InitScreen.jdp;

public class SplashScreen extends JInternalFrame {

    JPanel dados = new JPanel();

    public SplashScreen(){
        super("Sobre...", false, true, false, false);
        setSize(500,200);
        getContentPane().add("Center",dados);
        dados.setLayout(new GridLayout(4,1));
        InitScreen.jdp.add(this);
        setVisible(true);
        adicionaLabels();
    }

    public void adicionaLabels(){
        dados.add(new JLabel("Permuta 1"));
        dados.add(new JLabel("Halef Augusto Dorigan de Almeida - 00194461"));
        dados.add(new JLabel("William Soares da Silva - 00189731"));
        dados.add(new JLabel("Gustavo de Souza Dossi - 00167906"));
    }
}
