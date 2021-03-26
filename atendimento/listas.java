package br.healthtrack.atendimento;

import br.healthtrack.paciente.Paciente;

public interface listas {
	public boolean adicionarPaciente(Paciente paciente);
	public String percorrerlistaPaciente();
	public String encontrarPaciente(long cpf);
	
}
