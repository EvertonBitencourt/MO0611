package DATA;

import java.util.Date;


public class Piloto {
    //esse é um arquivo versionado
    //outra linha alterada
    private int idPiloto;
    private String nome;
    private float altura;
    private float peso;
    private Date dataNascimento;

    public Piloto(int idPiloto, String nome, float altura, float peso, Date dataNascimento) {
        this.idPiloto = idPiloto;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.dataNascimento = dataNascimento;
    }
    
    public Piloto(){
        
    }

    public int getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(int idPiloto) {
        this.idPiloto = idPiloto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    
}
