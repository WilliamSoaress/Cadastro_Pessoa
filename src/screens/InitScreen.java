package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InitScreen extends JFrame implements ActionListener{
        public static JDesktopPane jdp = new JDesktopPane();
        public JMenu jmCadastros = new JMenu("Cadastros");
        public JMenu jmArquivo = new JMenu("Arquivo");
        public JMenuItem jmiPrint = new JMenuItem("Imprimir cadastros");
        public JMenuItem jmiCadPessoa = new JMenuItem("Cadastrar Pessoa");
        public JMenuItem jmiSair = new JMenuItem("Sair da aplicação");

        public JMenuBar jmb = new JMenuBar();

        public InitScreen() {
            setTitle("Cadastro de Pessoas");
            setSize(600,600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            getContentPane().add(jdp);
            setJMenuBar(jmb);
            setLocationRelativeTo(null);    //Tela será criada no centro do monitor

            jmb.add(jmArquivo);
            jmb.add(jmCadastros);

            adicionaItemMenu(jmCadastros, jmiCadPessoa);
            adicionaItemMenu(jmArquivo, jmiPrint);
            adicionaItemMenu(jmArquivo, jmiSair);

            setVisible(true);
        }

        /**
         * Adiciona o item de menu a seu devido menu e adiciona um ActionListener
         * @param menu  Menu onde será alocado o item
         * @param itemMenu  Item de menu a ser alocado
         */
        public void adicionaItemMenu(JMenu menu, JMenuItem itemMenu) {
            menu.add(itemMenu);
            itemMenu.addActionListener(this);
        }

        /**
         * Recebe uma ação, avisando a tela que foi chamada
         * @param ae Recebe uma ação de clique (por exemplo)
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == jmiCadPessoa) {
                PersonRegister personRegister = new PersonRegister();
            } else if (ae.getSource() == jmiPrint){

            } else if (ae.getSource() == jmiSair) {
                int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }

    }
