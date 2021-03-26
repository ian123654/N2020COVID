package br.healthtrack.paciente;

public class Paciente {
 private String nome;
 private long cpf;
 private int idade;
 private Status status;
 private Sexo sexo;
 
public Paciente(String nome, long cpf, int idade, Status status, Sexo sexo) {
	super();
	this.nome = nome;
	this.cpf = cpf;
	this.idade = idade;
	this.status = status;
	this.sexo = sexo;
}
public int getIdade() {
	return idade;
}

public String getNome() {
	return nome;
}
public long getCpf() {
	return cpf;
}
public Sexo getSexo() {
	return sexo;
}
public Status getStatus() {
	return status;
}
public void setStatus(Status status) {
	this.status = status;
}
@Override
public String toString() {
	String aux="";
	aux="Nome--->"+nome+"\nCpf--->"+cpf+"\nSexo--->"+sexo+"\nidade--->"+idade+"\nStatus--->"+status+"\n";
	return aux;
}


}
