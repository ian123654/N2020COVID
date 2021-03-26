package br.healthtrack.control;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static javax.swing.JOptionPane.*;
import br.healthtrack.atendimento.EsperaAtendimento;
import java.util.ArrayList;
import br.healthtrack.atendimento.Atendimento;
import br.healthtrack.atendimento.Internacao;
import br.healthtrack.paciente.Paciente;
import br.healthtrack.hospital.Hospital;
import br.healthtrack.paciente.Sexo;
import br.healthtrack.paciente.Status;

public class Main {
	public static void main(String[] args) {
		String entrada;
		Hospital hospital1 = new Hospital("Sirio Libanês", 2);
		EsperaAtendimento atendimento = new EsperaAtendimento();
		Atendimento atender = new Atendimento();
		Internacao internados = new Internacao(hospital1.getNumeroleitos());
		ArrayList<Paciente> listageral = new ArrayList<Paciente>();
		
		do {

			entrada = showInputDialog("Senha ou SAIR");

			if (entrada == null) {
				entrada = "sair";
			}

			if (entrada.equals("admin")) {
				moduloAdministrativo(atendimento, atender, internados, listageral);
			}

		} while (!entrada.equalsIgnoreCase("sair"));
	}

	public static void moduloAdministrativo(EsperaAtendimento atendimento, Atendimento atender, Internacao internados,	ArrayList<Paciente> listageral) {
		String resposta;
		int opcao = -1;
		do {
			while (opcao < 1 || opcao > 6) {
				resposta = showInputDialog(menuAdministrativo());
				if (resposta != null) {
					try {
						opcao = parseInt(resposta);
					} catch (NumberFormatException nfe) {

						System.err.println(
								"[" + resposta + "] não é um número inteiro" + " e por isso não pode ser usado.");

					}
				}
			}
			switch (opcao) {
			case 1:
				moduloCadastrarpaciente(atendimento,  listageral);
				opcao = 0;
				break;
			case 2:
				moduloAtendimentopaciente(atendimento, atender, internados);
				opcao = 0;
				break;
			case 3:
				moduloLiberarpaciente(internados);
				opcao = 0;
				break;
			case 4:
				moduloEncontrarpaciente(listageral);
				opcao = 0;
				break;
			case 5:
				String aux = moduloListarpacientes(listageral);
				showMessageDialog(null, aux);
				opcao = 0;
				break;
			}

		} while (opcao != 6);

	}

	public static String moduloListarpacientes(ArrayList<Paciente> listageral) {
		// TODO Auto-generated method stub
		String aux = "";
		if (!listageral.isEmpty()) {
			aux += "---lista de Pacientes---\n";
			for (Paciente paciente : listageral) {
				aux += paciente.toString();
				aux += "\n";
			}
			return aux;
		} else {
			aux += "Não há pacientes no sistema!!";
			return aux;
		}
	}

	public static void moduloEncontrarpaciente(ArrayList<Paciente> listageral) {
		// TODO Auto-generated method stub
		String resposta = null;
		long cpf = 0;
		if (!listageral.isEmpty()) {
			while (cpf == 0 || resposta == null) {
				resposta = showInputDialog(null, " Digite o CPF\n*apenas números sem pontuação nem espaços.");
				if (resposta != null) {
					try {
						cpf = parseLong(resposta);
					} catch (NumberFormatException nfe) {

						System.err.println(
								"[" + resposta + "] não é um número inteiro" + " e por isso não pode ser usado.");

					}
				}
			}
			boolean achei = false;
			for (Paciente paciente : listageral) {
				if (paciente.getCpf() == cpf) {
					showMessageDialog(null, paciente.toString());
					achei = true;
				}

			}
			if (achei == false) {
				showMessageDialog(null, "Este CPF não consta no sistema!!");
			}
		} else {
			showMessageDialog(null, "Não há nenhum Paciente cadastrado no sistema!!");
		}
	}

	public static void moduloLiberarpaciente(Internacao internados) {
		// TODO Auto-generated method stub
		String resposta = null;
		long cpf = 0;
		int motivo = -1;
		while (cpf == 0 || resposta == null) {
			resposta = showInputDialog(null, " Digite o CPF\n*apenas números sem pontuação nem espaços.");
			if (resposta != null) {
				try {
					cpf = parseLong(resposta);
				} catch (NumberFormatException nfe) {

					System.err
							.println("[" + resposta + "] não é um número inteiro" + " e por isso não pode ser usado.");

				}
			}
		}
		String aux = "Digite o motivo da liberação\n0-alta\n1-obito";
		while ((motivo != 0 && motivo != 1) || resposta == null) {
			resposta = showInputDialog(null, aux);
			if (resposta != null) {
				try {
					motivo = parseInt(resposta);
				} catch (NumberFormatException nfe) {

					System.err
							.println("[" + resposta + "] não é um número inteiro" + " e por isso não pode ser usado.");
				}
			}
		}

		boolean recebe = internados.liberarPaciente(cpf, motivo);
		if (recebe == true) {
			if (motivo == 0) {
				showMessageDialog(null, "O paciente teve sucesso no tratamento e foi liberado!!");
			} else {
				showMessageDialog(null, "O paciente infelizmente não resistiu!!");
			}
		} else {
			showMessageDialog(null, "O paciente que possui esse cpf não foi encontrado!!");
		}
	}

	public static void moduloAtendimentopaciente(EsperaAtendimento atendimento, Atendimento atender,	Internacao internados) {
		// TODO Auto-generated method stub
		Paciente paciente;
		boolean controle = atendimento.filatempacientes();
		boolean recebe;
		if (controle == true) {
			paciente = atendimento.RemoverPaciente();
			showMessageDialog(null, "O paciente " + paciente.getNome() + " Responderá sobre alguns possiveis sintomas");
			recebe = atender.atenderPaciente(paciente);
			if (recebe == true) {
				paciente.setStatus(Status.Liberado);
				showMessageDialog(null,
						"O paciente " + paciente.getNome() + " não apresentou sintomas e foi liberado!!");
			} else {
				boolean volta = internados.adicionarPaciente(paciente);
				if (volta == true) {
					paciente.setStatus(Status.Internado);
					showMessageDialog(null,
							"O paciente " + paciente.getNome() + " Apresentou sintomas de covid e foi internado!!");
				} else {
					paciente.setStatus(Status.FilaInternacao);
					showMessageDialog(null, "O paciente " + paciente.getNome()
							+ " Apresentou sintomas de covid e está na espera de um leito disponivel!!");
				}
			}
		} else {
			showMessageDialog(null, "não há nenhum paciente na espera para ser atendido.");
		}
	}

	public static void moduloCadastrarpaciente(EsperaAtendimento atendimento, 	ArrayList<Paciente> listageral) {
		// TODO Auto-generated method stub
		String nome = "", resposta = null;
		int idade = 0;
		long cpf = 0;
		int numerosexo = -1;
		while (nome.isEmpty()) {
			nome = showInputDialog("Digite o Nome do paciente");
		}
		while (idade == 0 || resposta == null) {
			resposta = showInputDialog(null, "informe a idade do paciente\n");
			if (resposta != null) {
				try {
					idade = parseInt(resposta);
				} catch (NumberFormatException nfe) {

					System.err
							.println("[" + resposta + "] não é um número inteiro" + " e por isso não pode ser usado.");
				}
			}
		}
		while (cpf == 0 || resposta == null) {
			resposta = showInputDialog(null, "CPF\n*apenas números sem pontuação nem espaços.");
			if (resposta != null) {
				try {
					cpf = parseLong(resposta);
				} catch (NumberFormatException nfe) {

					System.err
							.println("[" + resposta + "] não é um número inteiro" + " e por isso não pode ser usado.");

				}
			}
		}
		boolean retorno = verificar(cpf, listageral);
		if (retorno == true) {
			while (resposta == null || (numerosexo != 0 && numerosexo != 1)) {
				String aux = "qual o sexo?\n0-femininino\n1-masculino";
				resposta = showInputDialog(null, aux);
				if (resposta != null) {
					try {
						numerosexo = parseInt(resposta);
					} catch (NumberFormatException nfe) {

						System.err.println(
								"[" + resposta + "] não é um número inteiro" + " e por isso não pode ser usado.");

					}
				}
			}
			if (numerosexo == 0) {
				Paciente paciente = new Paciente(nome, cpf, idade, Status.FilaAtendimento, Sexo.Feminino);
				atendimento.adicionarPaciente(paciente);
				listageral.add(paciente);
			} else {
				Paciente paciente = new Paciente(nome, cpf, idade, Status.FilaAtendimento, Sexo.Masculino);
				atendimento.adicionarPaciente(paciente);
				listageral.add(paciente);
			}
		}
		else {
			showMessageDialog(null, "Esse Paciente já está cadastrado no sistema!!");
		}

	}

	public static boolean verificar(long cpf, ArrayList<Paciente> listageral) {
		if (!listageral.isEmpty()) {
			for (Paciente paciente : listageral) {
				if (paciente.getCpf() == cpf) {
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
	}

	public static String menuAdministrativo() {
		String aux = "---Escollha uma opção---\n";
		aux += "1- Cadastrar paciente e colocar na fila de Atendimento.\n";
		aux += "2- Atender próximo paciente da fila de espera.\n";
		aux += "3- Liberar vaga.\n";
		aux += "4- Consulta Registro do Paciente.\n";
		aux += "5- Listar Cadastro Geral de Pacientes.\n";
		aux += "6- Sair.";
		aux += "\n-> ";
		return aux;
	}
}
