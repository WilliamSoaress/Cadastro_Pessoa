package screens;

import database.Database;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchScreen extends JFrame {

    private SysDefaultScreen telaChamadora;
    private DefaultTableModel dtm = new DefaultTableModel();

    private Object[][] dados = {};

    private JTable tabela = new JTable(dtm) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JScrollPane jsp = new JScrollPane(tabela);

    public SearchScreen(String nomeTela, String[] colunas, String sql, SysDefaultScreen telaChamadora) {
        this.telaChamadora = telaChamadora;
        setTitle(nomeTela);
        dtm.setDataVector(dados, colunas);
        getContentPane().add(jsp);
        setSize(600, 480);
        setVisible(true);
        List<String[]> dados = Database.consultaBanco(sql);
        for (int i = 0; i < dados.size(); i++) {
            dtm.addRow(dados.get(i));
        }
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    telaChamadora.setEstadoTela(4);
                    telaChamadora.preencherDados(Integer.parseInt((String) tabela.getValueAt(tabela.getSelectedRow(), 0)));
                    telaChamadora.habilitaBotoes();
                    dispose();
                }
            }

        });

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                telaChamadora.limpaCampos();
                telaChamadora.setEstadoTela(0);
                telaChamadora.habilitaBotoes();
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

    }
}
