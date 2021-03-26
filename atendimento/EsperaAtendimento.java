package br.healthtrack.atendimento;

import java.util.LinkedList;

import br.healthtrack.paciente.Paciente;

public class EsperaAtendimento implements listas {
	private LinkedList<Paciente> filaespera = new LinkedList<Paciente>();

	public boolean adicionarPaciente(Paciente elem) {
		filaespera.add(elem);
		return true;
	}

	public String percorrerlistaPaciente() {
		if (!filaespera.isEmpty()) {
			String aux = "Espera para Atendimento \n";
			aux += filaespera.toString() + "\n";
			return aux;
		} else {
			String aux = "";
			aux = "A lista de espera para atendimento está vazia ";
			return aux;
		}
	}

	public Paciente RemoverPaciente() {
		Paciente elemento;
		elemento = filaespera.getFirst();
		filaespera.removeFirst();
		return elemento;
	}

	public int tamanhoLista() {
		return filaespera.size();
	}

	public String encontrarPaciente(long cpf) {
		String aux = "";
		if (!filaespera.isEmpty()) {
			for (Paciente paciente : filaespera) {
				if (paciente.getCpf() == cpf) {
					aux = "O paciente foi achado na lista de espera para atendimento \n" + paciente.toString();
					return aux;

				}
			}
			aux = "Paciente não achado na fila de espera para atendimento";
			return aux;
		}
		aux = "A lista de espera de pacientes está vazia";
		return aux;
	}
	public boolean filatempacientes() {
		if(filaespera.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
}
