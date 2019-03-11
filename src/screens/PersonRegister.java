package screens;

import components.FTFCPF;
import components.FTFPhone;
import components.TextField;
import dao.DAOPerson;
import pojo.Person;

public class PersonRegister extends SysDefaultScreen {

    Person person = new Person();
    DAOPerson daoPerson = new DAOPerson(person);

    private TextField tfId = new TextField(3,false,"ID",false);
    private TextField tfName = new TextField(40,true,"Nome",true);
    private TextField tfEndereco = new TextField(40,true,"Endereço",true);
    private FTFPhone ftfPhone = new FTFPhone(true,"Telefone");
    private FTFCPF ftfCpf = new FTFCPF(true,"CPF");
    private TextField tfEmail = new TextField(30,false,"E-Mail",true);

    public PersonRegister(){
        super("Cadastrar pessoa ...");

        adicionaComponente(1,1,1,1,tfId, true);
        adicionaComponente(2,1,1,2,tfName,true);
        adicionaComponente(3,1,1,2,tfEndereco,true);
        adicionaComponente(4,1,1,1,ftfPhone,true);
        adicionaComponente(5,1,1,1,ftfCpf,true);
        adicionaComponente(6,1,1,2,tfEmail,true);

        habilitaCampos(false);
        pack(); //Organiza os componentes na tela
    }

    @Override
    public void incluirBD() {
        person.setName_person(tfName.getText());
        person.setAddress_person(tfEndereco.getText());
        person.setPhone_person(ftfPhone.getText());
        person.setCpf_person(ftfCpf.getText());
        person.setEmail_person(tfEmail.getText());
        daoPerson.inserir();
    }

    public void alterarBD() {
        person.setId_person(Integer.parseInt(tfId.getText()));
        person.setName_person(tfName.getText());
        person.setAddress_person(tfEndereco.getText());
        person.setPhone_person(ftfPhone.getText());
        person.setCpf_person(ftfCpf.getText());
        person.setEmail_person(tfEmail.getText());
        daoPerson.alterar();
    }

    public void excluirBD() {
        person.setId_person(Integer.parseInt(tfId.getText()));
        daoPerson.excluir();
    }

    public void preencherDados(int id) {
       person.setId_person(id);
       daoPerson.consultar();
       tfId.setText("" + person.getId_person());
       tfName.setText(person.getName_person());
       tfEndereco.setText(person.getAddress_person());
       ftfPhone.setText(person.getPhone_person());
       ftfCpf.setText(person.getCpf_person());
       tfEmail.setText(person.getEmail_person());
    }

    public void consultar(){
        super.consultar();
        new SearchScreen("Consulta de Pessoa", new String[] {"Código", "Nome"}, DAOPerson.SQL_CONSULTAR, this);
    }
}
