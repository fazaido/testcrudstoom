import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.stoom.crud.bean.Endereco;
import org.stoom.crud.service.EnderecoService;
import org.stoom.crud.utils.EnderecoDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoServiceTest {

    @InjectMocks
    EnderecoService enderecoService;

    @Mock
    EnderecoDAO dao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEnderecosTest() {
        final List<Endereco> list = new ArrayList<Endereco>();
        final Endereco end = new Endereco();
        end.setCity("Campinas");
        end.setCountry("Brasil");
        end.setDataCriacao(new Date());
        end.setId(1);
        end.setNeighbourhood("jd do lago");
        end.setNumber(666);
        end.setState("são paulo");
        end.setStreetName("av amoreiras");
        list.add(end);

        final Endereco end2 = new Endereco();
        end2.setCity("Campinas");
        end2.setCountry("Brasil");
        end2.setDataCriacao(new Date());
        end2.setId(2);
        end2.setNeighbourhood("jd do lago");
        end2.setNumber(777);
        end2.setState("são paulo");
        end2.setStreetName("av jose elas");
        list.add(end2);

        when(dao.getAllEndereco()).thenReturn(list);

        final List<Endereco> empList = enderecoService.getEnderecos();

        assertEquals(2, empList.size());
        verify(dao, times(1)).getAllEndereco();
    }

    @Test
    public void getEnderecoByRuaTest() {
        final Endereco endBusca = new Endereco();
        final String rua = "rua do bobos", bairro = "jardim do lago";
        final Integer numero = 222, id = 1;
        endBusca.setId(id);
        endBusca.setStreetName(rua);
        endBusca.setNumber(numero);
        endBusca.setNeighbourhood(bairro);

        final List<Endereco> listBusca = new ArrayList<Endereco>();
        listBusca.add(endBusca);

        when(dao.getByEndereco(rua)).thenReturn(listBusca);

        final Endereco end = enderecoService.getEndereco(rua).get(0);

        assertEquals(id, end.getId());
        assertEquals(rua, end.getStreetName());
        assertEquals(numero, end.getNumber());
        assertEquals(bairro, end.getNeighbourhood());
    }

    @Test
    public void updateEnderecoTest() {
        final Endereco endBusca = new Endereco();
        final String rua = "av senador antonio lacerda franco", bairro = "jardim do lago",
                pais = "Brasil", estado = "São Paulo",
                cidade = "Campinas", complemento = "ap 333";
        final Integer numero = 843, id = 1;
        final Date dataC = new Date();
        final Double lat = -22.934893, log = -47.088992;

        endBusca.setId(id);
        endBusca.setCity(cidade);
        endBusca.setCountry(pais);
        endBusca.setDataCriacao(dataC);
        endBusca.setNeighbourhood(bairro);
        endBusca.setNumber(numero);
        endBusca.setState(estado);
        endBusca.setStreetName(rua);
        endBusca.setLongitude(log);
        endBusca.setLatitude(lat);
        endBusca.setComplement(complemento);

        when(dao.getById(endBusca.getId())).thenReturn(endBusca);

        final Endereco endAtualizacao = new Endereco();
        endAtualizacao.setId(id);
        endAtualizacao.setCity(cidade);
        endAtualizacao.setCountry(pais);
        endAtualizacao.setDataCriacao(dataC);
        endAtualizacao.setNeighbourhood(bairro);
        final Integer novoNumero = 666;
        endAtualizacao.setNumber(novoNumero);
        endAtualizacao.setState(estado);
        endAtualizacao.setStreetName(rua);
        endAtualizacao.setLongitude(log);
        endAtualizacao.setLatitude(lat);
        endAtualizacao.setComplement(complemento);

        when(dao.update(endAtualizacao, endBusca)).thenReturn(endAtualizacao);

        final Endereco enderecoEncontrado = enderecoService.updateEndereco(endAtualizacao);

        assertEquals(id, enderecoEncontrado.getId());
        assertEquals(rua, enderecoEncontrado.getStreetName());
        assertEquals(novoNumero, enderecoEncontrado.getNumber());
        assertEquals(bairro, enderecoEncontrado.getNeighbourhood());
        assertEquals(cidade, enderecoEncontrado.getCity());
        assertEquals(estado, enderecoEncontrado.getState());
        assertEquals(pais, enderecoEncontrado.getCountry());
        assertEquals(lat, enderecoEncontrado.getLatitude());
        assertEquals(log, enderecoEncontrado.getLongitude());
        assertEquals(complemento, enderecoEncontrado.getComplement());
    }
    @Test
    public void updateEnderecoCoordenadasTest() {
        final Endereco endBusca = new Endereco();
        Integer cep = 13050030;
        String rua = "av senador antonio lacerda franco", bairro = "jardim do lago",
                pais = "Brasil", estado = "São Paulo",
                cidade = "Casmpinas", complemento = "ap 333";
        final Integer numero = 843, id = 1;
        final Date dataC = new Date();
        final Double lat = -22.934893, log = -47.088992;

        endBusca.setId(id);
        endBusca.setCity(cidade);
        endBusca.setCountry(pais);
        endBusca.setDataCriacao(dataC);
        endBusca.setNeighbourhood(bairro);
        endBusca.setNumber(numero);
        endBusca.setState(estado);
        endBusca.setStreetName(rua);
        endBusca.setLongitude(log);
        endBusca.setLatitude(lat);
        endBusca.setComplement(complemento);
        endBusca.setZipcode(cep);

        when(dao.getById(endBusca.getId())).thenReturn(endBusca);

        final Endereco endAtualizacao = new Endereco();
        final Integer novoNumero = 65;
        rua = "Av. Orlando Tamiosso";
        bairro = "Swiss Park";
        cep = 13049365;
        endAtualizacao.setId(id);
        endAtualizacao.setCity(cidade);
        endAtualizacao.setCountry(pais);
        endAtualizacao.setDataCriacao(dataC);
        endAtualizacao.setNeighbourhood(bairro);
        endAtualizacao.setNumber(novoNumero);
        endAtualizacao.setState(estado);
        endAtualizacao.setStreetName(rua);
        endAtualizacao.setLongitude(log);
        endAtualizacao.setLatitude(lat);
        endAtualizacao.setComplement(complemento);
        endAtualizacao.setZipcode(cep);
        endAtualizacao.setDataAtualizacao(new Date());

        when(dao.update(endAtualizacao, endBusca)).thenReturn(endAtualizacao);

        final Endereco enderecoEncontrado = enderecoService.updateEndereco(endAtualizacao);

        final Double novaLat = -22.934893, novaLog = -47.088992;
        assertEquals(id, enderecoEncontrado.getId());
        assertEquals(rua, enderecoEncontrado.getStreetName());
        assertEquals(novoNumero, enderecoEncontrado.getNumber());
        assertEquals(bairro, enderecoEncontrado.getNeighbourhood());
        assertEquals(cidade, enderecoEncontrado.getCity());
        assertEquals(estado, enderecoEncontrado.getState());
        assertEquals(pais, enderecoEncontrado.getCountry());
        assertEquals(complemento, enderecoEncontrado.getComplement());
        assertEquals(cep, enderecoEncontrado.getZipcode());
        assertEquals(lat, novaLat);
        assertEquals(log, novaLog);
        assertNotNull(enderecoEncontrado.getDataCriacao());
        assertNotNull(enderecoEncontrado.getDataAtualizacao());
    }

    @Test
    public void createEnderecoTest() {
        final Endereco end = new Endereco();
        end.setId(1);
        end.setStreetName("rua do bobos");
        end.setNumber(123);
        end.setNeighbourhood("jardim do lago");
        end.setCity("Campinas");
        end.setCountry("Brasil");
        end.setDataCriacao(new Date());
        end.setState("são paulo");
        end.setStreetName("av amoreiras");
        end.setLatitude(-44.44);
        end.setLongitude(-25.25);

        this.enderecoService.addEndereco(end);

        verify(dao, times(1)).create(end);
    }

    @Test
    public void deleteEnderecoTest() {
        final Endereco endereco = new Endereco();
        endereco.setId(1);

        this.enderecoService.deleteEndereco(endereco.getId());
        verify(this.dao, times(1)).delete(endereco.getId());
    }
}