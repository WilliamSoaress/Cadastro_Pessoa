package components;

/*Esta interface definirá o que deve ser implementado pelo componente
 * a ela vinculado*/
public interface ComponentInterface {
    public void limpar();

    public void habilitar(boolean status);

    public boolean eObrigatorio();

    public boolean eValido();

    public boolean eVazio();

    public String getDica();
}
