package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextField extends JTextField implements ComponentInterface {

    private boolean obrigatorio;
    private String dica;
    private boolean podeHabilitar;
    private String dicaCursor;


    public TextField(int colunas, boolean obrigatorio, String dica, boolean podeHabilitar) {
        super(colunas);
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        this.podeHabilitar = podeHabilitar;
        addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent fe) {
                setBackground(Color.white);
            }

            public void focusGained(FocusEvent fe) {
                setBackground(Color.yellow);
            }
        });

    }

    public TextField(int colunas, boolean obrigatorio, String dica, boolean podeHabilitar, String dicaCursor) {
        super(colunas);
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        this.podeHabilitar = podeHabilitar;
        this.dicaCursor = dicaCursor;
        addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent fe) {
                setBackground(Color.white);
            }

            public void focusGained(FocusEvent fe) {
                setBackground(Color.yellow);
            }
        });
        setToolTipText(this.dicaCursor);
    }

    @Override
    public void limpar() {
        setText("");
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(podeHabilitar && status);
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public boolean eVazio() {
        return getText().trim().isEmpty();
        //String texto = getText();
//        String textoSemEspacos = texto.trim;
//        boolean vazio = textoSemEspacos.isEmpty();
//        return vazio.
    }

    @Override
    public String getDica() {
        return dica;
    }

    public String getDicaCursor() {
        return dicaCursor;
    }
}
