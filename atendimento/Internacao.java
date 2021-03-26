package br.healthtrack.atendimento;
import java.util.ArrayList;
import br.healthtrack.paciente.Paciente;
import br.healthtrack.atendimento.EsperaInternacao;
import br.healthtrack.paciente.Status;
public class Internacao implements listas{
    private ArrayList<Paciente> pacientesinternados= new ArrayList<Paciente>();
	private int tamanho;
	private EsperaInternacao listaespera=new EsperaInternacao();
	public Internacao(int tamanho) {
		super();
		this.tamanho = tamanho;
	}
	public EsperaInternacao getListaespera() {
		return listaespera;
	}
  
	public int getTamanho() {
		return tamanho;
	}
public boolean adicionarPaciente(Paciente paciente) {
	   if(pacientesinternados.size()<tamanho) {
		   pacientesinternados.add(paciente);
		   return true;
	   }
	   else {
		   listaespera.adicionarPaciente(paciente);
		   return false;
	   }
	  
   }
public String percorrerlistaPaciente() {
	  if(!pacientesinternados.isEmpty()) {
	     String aux="________Pacientes internados________";
	     for(Paciente paciente: pacientesinternados) {
	    	 aux+=paciente.toString();
	     }
	   
	   return aux;
	  }
	  else {
		  String aux="nenhum leito ocupado!!";
	      return aux;
	  }
}
public boolean liberarPaciente(long cpf, int motivo) {
	for(Paciente paciente: pacientesinternados) {
		if(paciente.getCpf()==cpf) {
			Paciente aux=paciente;
			pacientesinternados.remove(paciente);
            if (motivo==0) {
            	aux.setStatus(Status.Alta);
            }
            else {
            	aux.setStatus(Status.Obito);
            }
			if(!listaespera.getEsperainternacao().isEmpty()) {
				Paciente recebe=listaespera.removerPaciente();
				recebe.setStatus(Status.Internado);
               pacientesinternados.add(recebe);
			}
			return true;
		}
	}
	return false;
}
public String encontrarPaciente(long cpf) {
	String aux="";
	if(!pacientesinternados.isEmpty()) {
		for(Paciente paciente: pacientesinternados) {
			if(paciente.getCpf()==cpf) {
				aux="O paciente está internado \n"+paciente.toString();
				return aux;
				
			}
		}
		aux="Paciente não encontrado na lista de pacientes internados";
		return aux;
	}
	aux="Nenhum Leito está ocupado";
	return aux;
}

}
