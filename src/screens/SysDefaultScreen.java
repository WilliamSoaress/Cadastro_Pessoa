package screens;

import components.ComponentInterface;
import database.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Halef Dorigan
 */
public class SysDefaultScreen extends JInternalFrame implements ActionListener,DatabaseInterface{

    private final int PADRAO = 0;
    private final int INCLUINDO = 1;
    private final int ALTERANDO = 2;
    private final int EXCLUINDO = 3;
    private final int CONSULTANDO = 4;
    private int estadoTela = PADRAO;
    private boolean temDadosNaTela = false;

    protected JPanel jpComponentes = new JPanel();
    private JPanel jpBotoes = new JPanel();
    private JPanel jpTabela = new JPanel();
    private JButton jbIncluir = new JButton("Incluir");
    private JButton jbAlterar = new JButton("Alterar");
    private JButton jbExcluir = new JButton("Excluir");
    //private JButton jbConsultar = new JButton("Consultar");
    private JButton jbConfirmar = new JButton("Confirmar");
    private JButton jbCancelar = new JButton("Cancelar");
    private List<ComponentInterface> campos = new ArrayList();

//    Váriáveis para a tabela abaixo dos botões
    DefaultTableModel dtm = new DefaultTableModel();

    private Object[][] dados = {};
    private JTable jTable= new JTable(dtm) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };;
    private JScrollPane jsp = new JScrollPane(jTable);

    public SysDefaultScreen(String titulo) {
        super(titulo, false, true, false, false);
        getContentPane().add("North", jpComponentes);
        getContentPane().add("South", jpBotoes);

        jpComponentes.setLayout(new GridBagLayout());
        jpBotoes.setLayout(new GridLayout(1, 6));
        jpBotoes.add("South",jpTabela);
        adicionarBotoes();
        pack();
        InitScreen.jdp.add(this);
        setVisible(true);
        habilitaBotoes();
    }

    public void criarTabela(String[] colunas, String sql,
                            SysDefaultScreen ss, int[] tamColunas){
        dtm.setDataVector(dados, colunas);
        getContentPane().add(jsp);
        setSize(800, 600);
        dimensionaTabela(jTable,tamColunas);
        setDefaultLocale(null);
        setVisible(true);
        List<String[]> dados = Database.consultaBanco(sql);
        for (int i = 0; i < dados.size(); i++) {
            dtm.addRow(dados.get(i));
        }
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    ss.setEstadoTela(4);
                    ss.preencherDados(Integer.parseInt((String) jTable.getValueAt(jTable.getSelectedRow(), 0)));
                    ss.habilitaBotoes();
                }
            }

        });
    }

    public void dimensionaTabela(JTable table, int [] tamCol){

        for (int i = 0; i < tamCol.length; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(tamCol[i]);
        }

    }

    public void adicionaComponente(int linha, int coluna, int linhas, int colunas, ComponentInterface componente, boolean dicaAtiva) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = coluna;
        gbc.gridy = linha;
        String texto = componente.getDica();
        if (componente.eObrigatorio()) {
            texto = "<html><body>" + texto + "<font color=\"red\">*</font>: ";

        } else {
            texto = texto + ":  ";
        }
        if (dicaAtiva) {
            JLabel rotulo = new JLabel(texto);
            jpComponentes.add(rotulo, gbc);
        } else {
            texto = "";
            JLabel rotulo = new JLabel(texto);
            jpComponentes.add(rotulo, gbc);
        }
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx++;
        gbc.gridwidth = colunas;
        gbc.gridheight = linhas;
        jpComponentes.add((JComponent) componente, gbc);
        campos.add(componente);
    }

    public void adicionarBotoes() {
        adicionarBotao(jbIncluir);
        adicionarBotao(jbAlterar);
        adicionarBotao(jbExcluir);
        //adicionarBotao(jbConsultar);
        adicionarBotao(jbConfirmar);
        adicionarBotao(jbCancelar);
    }

    public void habilitaCampos(boolean status) {
        for (int i = 0; i < campos.size(); i++) {
            campos.get(i).habilitar(status);
        }

    }

    public void limpaCampos() {
        for (int i = 0; i < campos.size(); i++) {
            campos.get(i).limpar();
        }

    }

    public void adicionarBotao(JButton botao) {
        jpBotoes.add(botao);
        botao.addActionListener(this);
    }

    public void habilitaBotoes() {
        System.out.println(">>>");
        //aqui ele etá comparando estado de tela se ele é padrão ou não, vai retornar true ou flse
        jbIncluir.setEnabled(estadoTela == PADRAO);
        //tem dados na tela ja é logico pois ele ja é bollean então ele está se auto comparando
        jbAlterar.setEnabled(estadoTela == PADRAO && temDadosNaTela || estadoTela == CONSULTANDO);
        jbExcluir.setEnabled(estadoTela == PADRAO && temDadosNaTela || estadoTela == CONSULTANDO);
        //jbConsultar.setEnabled(estadoTela == PADRAO);
        jbConfirmar.setEnabled(estadoTela != PADRAO);
        jbCancelar.setEnabled(estadoTela != PADRAO && estadoTela != CONSULTANDO);

    }

    public boolean verificaCampos() {
        String errosObrigatorios = "";
        String errosValidacao = "";
        for (int i = 0; i < campos.size(); i++) {
            if (campos.get(i).eObrigatorio()
                    && campos.get(i).eVazio()) {
                errosObrigatorios = errosObrigatorios
                        + campos.get(i).getDica() + "\n";
            }
            if (!campos.get(i).eValido()) {
                errosValidacao = errosValidacao
                        + campos.get(i).getDica() + "\n";
            }
        }
        if (!errosObrigatorios.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campos obrigatórios não preenchidos: \n \n" + errosObrigatorios);
        }
        return errosObrigatorios.isEmpty() && errosValidacao.isEmpty();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jbIncluir) {
            incluir();
        } else if (ae.getSource() == jbAlterar) {
            alterar();
        } else if (ae.getSource() == jbExcluir) {
            excluir();
        //} else if (ae.getSource() == jbConsultar) {
            consultar();
        } else if (ae.getSource() == jbConfirmar) {
            confirmar();
        } else if (ae.getSource() == jbCancelar) {
            cancelar();
        } else {
            System.out.println("Fonte de actionListiner não reconhecida");
        }

    }

    public void incluir() {
        estadoTela = INCLUINDO;
        limpaCampos();
        habilitaCampos(true);
        habilitaBotoes();
    }

    public void alterar() {
        estadoTela = ALTERANDO;
        habilitaCampos(true);
        habilitaBotoes();
    }

    public void excluir() {
        estadoTela = EXCLUINDO;
        habilitaBotoes();
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            excluirBD();
            limpaCampos();
            temDadosNaTela = false;
            estadoTela = PADRAO;
            habilitaBotoes();
        }
        limpaCampos();
        temDadosNaTela = false;
        estadoTela = PADRAO;
        habilitaBotoes();
    }

    public void consultar() {
//Código implementado na tela de consulta
    }

    public void confirmar() {
        if (!verificaCampos()) {
            return;
        }
        if (estadoTela == INCLUINDO) {
            incluirBD();

            temDadosNaTela = true;
        }
        if (estadoTela == ALTERANDO) {
            alterarBD();
            temDadosNaTela = true;
        }
        if (estadoTela == CONSULTANDO) {
            limpaCampos();
            temDadosNaTela = false;
        }
        estadoTela = PADRAO;
        habilitaCampos(false);
        habilitaBotoes();

    }

    public void cancelar() {
        estadoTela = PADRAO;
        limpaCampos();
        habilitaCampos(false);
        habilitaBotoes();
    }

    public void incluirBD() {

    }

    public void alterarBD() {

    }

    public void excluirBD() {

    }

    public void preencherDados(int id) {

    }


    public int getEstadoTela() {
        return estadoTela;
    }

    public void setEstadoTela(int estadoTela) {
        this.estadoTela = estadoTela;
    }

}