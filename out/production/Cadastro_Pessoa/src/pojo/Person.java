package pojo;

public class Person {
    private int id_person;
    private String name_person;
    private String address_person;
    private String phone_person;
    private String cpf_person;

    private String email_person;

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getName_person() {
        return name_person;
    }

    public void setName_person(String name_person) {
        this.name_person = name_person;
    }

    public String getAddress_person() {
        return address_person;
    }

    public void setAddress_person(String address_person) {
        this.address_person = address_person;
    }


    public String getEmail_person() {
        return email_person;
    }

    public void setEmail_person(String email_person) {
        this.email_person = email_person;
    }

    public String getPhone_person() {
        return phone_person;
    }

    public void setPhone_person(String phone_person) {
        this.phone_person = phone_person;
    }

    public String getCpf_person() {
        return cpf_person;
    }

    public void setCpf_person(String cpf_person) {
        this.cpf_person = cpf_person;
    }
}
