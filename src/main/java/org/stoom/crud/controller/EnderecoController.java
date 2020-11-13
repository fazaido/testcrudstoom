package org.stoom.crud.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.stoom.crud.bean.Endereco;
import org.stoom.crud.service.EnderecoService;
import org.stoom.crud.utils.EnderecoDAO;


@Path("/enderecos")
public class EnderecoController {
	
	private final EnderecoService enderecoService = new EnderecoService(new EnderecoDAO());
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Endereco> getEnderecos(){
		return enderecoService.getEnderecos();
	}

    @GET
    @Path("/{rua}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Endereco> getEnderecoByRua(final @PathParam("rua") String rua) {
		if(rua != null && !rua.trim().isEmpty()){
			return enderecoService.getEndereco(rua);
		} else {
			System.out.println("getEnderecoByRua - RUA NAO FOI ENVIADA");
		}
		return null;
	}
   
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public Endereco addEndereco(final Endereco endereco){
		if(endereco != null && endereco.getStreetName() != null && !endereco.getStreetName().isEmpty() &&
			endereco.getNumber() != null && Integer.compare(endereco.getNumber(),0) > 0 &&
				endereco.getNeighbourhood() != null && !endereco.getNeighbourhood().isEmpty() &&
				endereco.getCity() != null && !endereco.getCity().isEmpty() &&
				endereco.getState() != null && !endereco.getState().isEmpty() &&
				endereco.getCountry() != null && !endereco.getCountry().isEmpty() &&
				endereco.getZipcode() != null && Integer.compare(endereco.getZipcode(),0) > 0) {
			final Endereco enderecoAdicionado = enderecoService.addEndereco(endereco);
			if(enderecoAdicionado == null) {
				System.out.println("addEndereco - ENDERECO NAO FOI ADICIONADO");
				return null;
			}
			return enderecoAdicionado;
		} else {
			System.out.println("addEndereco - ENDERECO COM CAMPOS FALTANDO");
		}
		return null;
	}

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
	public Endereco updateEndereco(final Endereco endereco) {
		if(endereco != null && endereco.getId() != null && Integer.compare(endereco.getId(),0) > 0){
			final Endereco enderecoAtualizado = enderecoService.updateEndereco(endereco);
			if(enderecoAtualizado == null){
				System.out.println("updateEndereco - NAO CONSEGUIU ATUALIZAR ENDERECO - ID: " + endereco.getId());
				return null;
			}
		} else {
			System.out.println("updateEndereco - PRECISA DO ID DO ENDERECO PARA FAZER ATUALIZACAO");
			return null;
		}
		return endereco;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteContato(final @PathParam("id") Integer id) {
    	if(id != null && Integer.compare(id,0) > 0) {	//se id nao eh nulo e positivo
			if(!enderecoService.deleteEndereco(id)) {
				System.err.println("deleteContato - NAO CONSEGUIU DELETAR ENDERECO - ID: " + id);
				//TODO responder erro pro cliente
			} else {
				System.out.println("deleteContato - DELETADO COM SUCESSO - ID: " + id);
				//TODO responder erro pro cliente
			}
		} else {
			System.err.println("deleteContato - ID NULO OU MENOR OU IGUAL A ZERO - ID: " + id);
			//TODO responder erro pro cliente
		}
	}
}
