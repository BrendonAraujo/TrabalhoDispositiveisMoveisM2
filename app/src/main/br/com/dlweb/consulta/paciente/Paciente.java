package br.com.dlweb.consulta.paciente;

public class Paciente implements java.io.Serializable {

    private int id;
    private String nome;
    private String grp_sanguineo;
    private String celular;
    private String fixo;

    public Paciente () { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getGrp_sanguineo() {
        return grp_sanguineo;
    }

    public void setGrp_sanguineo(String grp_sanguineo) {
        this.grp_sanguineo = grp_sanguineo;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFixo() {
        return fixo;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }
}
