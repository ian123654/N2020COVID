package br.healthtrack.hospital;

public class Hospital {
 private String nome;
 private int numeroleitos;
public Hospital(String nome, int numeroleitos) {
	super();
	this.nome = nome;
	this.numeroleitos = numeroleitos;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public int getNumeroleitos() {
	return numeroleitos;
}
public void setNumeroleitos(int numeroleitos) {
	this.numeroleitos = numeroleitos;
}
@Override
public String toString() {
	String aux="";
	aux+="Nome do hospital-->"+nome+"\n";
	aux+="Numero de leitos-->"+numeroleitos+"\n";
	return aux ;
}

 
}
