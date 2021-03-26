package br.healthtrack.atendimento;

import java.util.LinkedList;

import br.healthtrack.paciente.Paciente;

public class EsperaInternacao implements listas {
	private LinkedList<Paciente> esperainternacao = new LinkedList<Paciente>();

	public LinkedList<Paciente> getEsperainternacao() {
		return esperainternacao;
	}

	public void setEsperainternacao(LinkedList<Paciente> esperainternacao) {
		this.esperainternacao = esperainternacao;
	}

	public boolean adicionarPaciente(Paciente paciente) {
		esperainternacao.add(paciente);
		return true;
	}

	public Paciente removerPaciente() {
		Paciente paciente;
		paciente = esperainternacao.getFirst();
		esperainternacao.removeFirst();
		return paciente;

	}

	public String percorrerlistaPaciente() {
		if (!esperainternacao.isEmpty()) {
			String aux = "lista de espera para internação \n";
			for (Paciente paciente : esperainternacao) {
             aux+=paciente.toString();
			}
			return aux;
		}
		else {
			String aux="A lista de espera para internação está vazia";
			return aux;
		}
	}
	public String encontrarPaciente(long cpf) {
		String aux="";
		if(!esperainternacao.isEmpty()) {
			for(Paciente paciente: esperainternacao) {
				if(paciente.getCpf()==cpf) {
					aux="O paciente está a espera de um leito disponivel \n"+paciente.toString();
					return aux;
					
				}
			}
			aux="Paciente não encontrado na lista de espera para leitos";
			return aux;
		}
		aux="Não há ninguem a espera de leitos ";
		return aux;
	}
}
