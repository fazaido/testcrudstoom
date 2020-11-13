package org.stoom.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stoom.crud.bean.Endereco;
import org.stoom.crud.utils.GoogleMapsAPI;
import org.stoom.crud.utils.EnderecoDAO;

@Service
public class EnderecoService {

	public EnderecoDAO dao;

	public EnderecoService(final EnderecoDAO dao) {
		super();
		System.out.println("INICIALIZANDO EnderecoService");
		this.dao = dao;
		System.out.println("FINALIZANDO EnderecoService");
	}

	private void autoCompletaEndereco(final Endereco end, final String enderecoBusca) {
		System.out.println("INICIANDO autoCompletaEndereco - end: " + end);

		final GoogleMapsAPI googleMapsAPI = new GoogleMapsAPI();
		final String enderecoCompleto;
		if(enderecoBusca != null){
			enderecoCompleto = enderecoBusca;
		} else {
			enderecoCompleto = end.getStreetName() + ", " + end.getNumber() + " - " + end.getNeighbourhood() +
					", " + end.getCity() + " - " + end.getState() + ", " + end.getZipcode() + ", " + end.getCountry();
		}
		final Double[] coordenadas = googleMapsAPI.getCoordenadasByEndereco(enderecoCompleto);
		end.setLatitude(coordenadas[0]);
		end.setLongitude(coordenadas[1]);
		System.out.println(coordenadas[0] + " " + coordenadas[1]);

		System.out.println("FINALIZANDO autoCompletaEndereco");
	}

	public List<Endereco> getEnderecos() {
		final List<Endereco> todosEnderecos = dao.getAllEndereco();
		System.out.println("getAllEnderecos - todosEnderecos: " + todosEnderecos);
		return todosEnderecos;
	}

	public List<Endereco> getEndereco(final String rua) {
		return dao.getByEndereco(rua);
	}

	public Endereco addEndereco(final Endereco endereco) {
		System.out.println("addEndereco - id: " + endereco.getId());
		if(endereco.getLatitude() == null || Double.compare(endereco.getLatitude(), 0) >= 0 ||
			endereco.getLongitude() == null || Double.compare(endereco.getLongitude(),0) >= 0) {
			this.autoCompletaEndereco(endereco, null);
		}
		if(!dao.create(endereco)) {
			System.err.println("addEndereco - nao foi criado");
			return null;
		}
		return endereco;
	}
	
	public Endereco updateEndereco(final Endereco end) {
		final Endereco endOld = dao.getById(end.getId());
		if(endOld == null){
			System.out.println("updateEndereco - ENDERECO NAO ENCONTRADO PARA ATUALIZACAO - id: " + end.getId());
			return null;
		}
		System.out.println("updateEndereco - endOld: " + endOld);

		Boolean autoCompletaCoordenadas = Boolean.FALSE;
		final StringBuffer sbBuscaCoord = new StringBuffer();

		if(end.getLatitude() == null || Double.compare(end.getLatitude(), 0) >= 0 ||
				end.getLongitude() == null || Double.compare(end.getLongitude(),0) >= 0) {

			final String stName = end.getStreetName();
			if (stName != null && !stName.isEmpty() && !endOld.getStreetName().equalsIgnoreCase(stName)) {
				sbBuscaCoord.append(stName);
				autoCompletaCoordenadas = Boolean.TRUE;
			}
			final Integer num = end.getNumber();
			if (num != null && !endOld.getNumber().equals(num)) {
				sbBuscaCoord.append(", " + num);
				autoCompletaCoordenadas = Boolean.TRUE;
			}
			final String bairro = end.getNeighbourhood();
			if (bairro != null && !bairro.isEmpty() && !endOld.getNeighbourhood().equalsIgnoreCase(bairro)) {
				sbBuscaCoord.append(" - " + bairro);
				autoCompletaCoordenadas = Boolean.TRUE;
			}
			final String cidade = end.getCity();
			if (cidade != null && !cidade.isEmpty() && !endOld.getCity().equalsIgnoreCase(cidade)) {
				sbBuscaCoord.append(", " + cidade);
				autoCompletaCoordenadas = Boolean.TRUE;
			}
			final String estado = end.getState();
			if (estado != null && !estado.isEmpty() && !endOld.getState().equalsIgnoreCase(estado)) {
				sbBuscaCoord.append(" - " + estado);
				autoCompletaCoordenadas = Boolean.TRUE;
			}
			final Integer cep = end.getZipcode();
			if (cep != null && !endOld.getZipcode().equals(cep)) {
				sbBuscaCoord.append(" - " + cep);
				autoCompletaCoordenadas = Boolean.TRUE;
			}
			final String pais = end.getCountry();
			if (pais != null && !pais.isEmpty() && !endOld.getCountry().equalsIgnoreCase(pais)) {
				sbBuscaCoord.append(", " + pais);
				autoCompletaCoordenadas = Boolean.TRUE;
			}
		}
		if(autoCompletaCoordenadas){
			this.autoCompletaEndereco(end, sbBuscaCoord.toString());
		}
		return dao.update(end, endOld);
	}

	public Boolean deleteEndereco(final Integer id) {
		return dao.delete(id);
	}
}
