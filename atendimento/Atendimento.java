package br.healthtrack.atendimento;


import br.healthtrack.paciente.Paciente;
import static javax.swing.JOptionPane.*;

public class Atendimento {
	
	private String[] sintomas = { "Saturação de Oxigênio <= 93% e não obteve melhora com tratamento com oxigênio",
			"Febre ou Sinais e Sintomas de Doença Respiratória", "Raio-X mostrando Opacificação Pulmonar",
			"Idade >= 60 anos", "Massa Corporal Acima de 30", "Diabetes (HgbA1c >= 8.0)", "Doença Cardíaca Crônica",
			"Doença Pulmonar Crônica", "Imunossuprimido" };

	

	public boolean atenderPaciente(Paciente elem) {
		boolean liberado = false;
		boolean febreSintomasRespiratorios = false;
		String[] entrada = new String[sintomas.length];
		showMessageDialog(null, "Digite 0 para não e 1 para sim");
		for (int i = 0; i < entrada.length; i++) {
			entrada[i]="3";   
			while(!entrada[i].equals("0") && !entrada[i].equals("1") )
				entrada[i] = showInputDialog(null, sintomas[i]);
			System.out.println(entrada[i]);
		}
		if(entrada[0].equalsIgnoreCase("1")) {
			return liberado;
		}
		if ( entrada[1].equalsIgnoreCase("1")|| entrada[2].equalsIgnoreCase("1")) {
			febreSintomasRespiratorios = true;
		}
		if (febreSintomasRespiratorios && (entrada[3].equalsIgnoreCase("1") || entrada[4].equalsIgnoreCase("1") || entrada[5].equalsIgnoreCase("1") || entrada[6].equalsIgnoreCase("1") || entrada[7].equalsIgnoreCase("1") || entrada[8].equalsIgnoreCase("1"))) {
			return liberado;
		}
		else {
			liberado=true;
			return true;
		}
	}
}
