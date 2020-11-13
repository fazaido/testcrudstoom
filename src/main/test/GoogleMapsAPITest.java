package org.stoom.crud.test;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.stoom.crud.utils.GoogleMapsAPI;

public class GoogleMapsAPITest {

    private static final GoogleMapsAPI googleMapsAPI = new GoogleMapsAPI();

    private final Double[] coordenadasStoom = {-22.8354045, -47.0787762};
    private final Double[] coordenadasSwissPark = {-22.9658558, -47.0648422};
    private final String enderecoStoom = "R. Zuneide Aparecida Marin, 43 - Jardim Santa Genebra II " +
            "(Barao Geraldo), Campinas - SP, 13084-780, Brazil";
    private final String enderecoSwissPark = "Av. Orlando Tamiosso, 65 - Swiss Park, Campinas - SP, " +
            "13049-552, Brazil";

    @Test
    public void getCoordenadasByEnderecoSwissParkOkTest() {
        final Double[] retArray = googleMapsAPI.getCoordenadasByEndereco(this.enderecoSwissPark);
        Assert.assertEquals(this.coordenadasSwissPark[0], retArray[0]);
        Assert.assertEquals(this.coordenadasSwissPark[1], retArray[1]);
    }

    @Test
    public void getCoordenadasByEnderecoSwissParkErroTest() {
        final Double[] retArray = googleMapsAPI.getCoordenadasByEndereco(this.enderecoSwissPark);
        Assert.assertNotEquals(this.coordenadasSwissPark[0]+1.0, retArray[0]);
        Assert.assertEquals(this.coordenadasSwissPark[1], retArray[1]);
    }

    @Test
    public void getEnderecoByCoordenadasSwissParkOkTest() {
        final String endTest = googleMapsAPI.getEnderecoByCoordenadas(this.coordenadasSwissPark[0] + ", " +
                this.coordenadasSwissPark[1]);
        Assert.assertEquals(this.enderecoSwissPark, endTest);
    }

    @Test
    public void getEnderecoByCoordenadasSwissParkErroTest() {
        final String endTest = googleMapsAPI.getEnderecoByCoordenadas(this.coordenadasSwissPark[0] + ", " +
                this.coordenadasSwissPark[1]+2.0);
        Assert.assertNotEquals(this.enderecoSwissPark, endTest);
    }

    @Test
    public void getEnderecoByCoordenadasErroEntradaTest() {
        Assert.assertNull(googleMapsAPI.getEnderecoByCoordenadas("-4646.5464,-6546.54654"));
    }

    @Test
    public void getCoordenadasByEnderecoErroEntradaTest() {
        Assert.assertNull(googleMapsAPI.getCoordenadasByEndereco("enderecoquenaoexiste"));
    }

    @Test
    public void getCoordenadasByEnderecoStoomOkTest() {
        final Double[] retArray = googleMapsAPI.getCoordenadasByEndereco(this.enderecoStoom);
        Assert.assertEquals(this.coordenadasStoom[0], retArray[0]);
        Assert.assertEquals(this.coordenadasStoom[1], retArray[1]);
    }

    @Test
    public void getCoordenadasByEnderecoStoomErroTest() {
        final Double[] retArray = googleMapsAPI.getCoordenadasByEndereco(this.enderecoStoom);
        Assert.assertNotEquals(this.coordenadasStoom[0]+1.0, retArray[0]);
        Assert.assertEquals(this.coordenadasStoom[1], retArray[1]);
    }

    @Test
    public void getEnderecoByCoordenadasStoomOkTest() {
        final String endTest = googleMapsAPI.getEnderecoByCoordenadas(this.coordenadasStoom[0] + ", " +
                this.coordenadasStoom[1]);
        Assert.assertEquals(this.enderecoStoom, endTest);
    }

    @Test
    public void getEnderecoByCoordenadasStoomErroTest() {
        final String endTest = googleMapsAPI.getEnderecoByCoordenadas(this.coordenadasStoom[0] + ", " +
                this.coordenadasStoom[1]+2.0);
        Assert.assertNotEquals(this.enderecoStoom, endTest);
    }

    @Test
    public void getEnderecoByCoordenadasErroEntrada2Test() {
        Assert.assertNull(googleMapsAPI.getEnderecoByCoordenadas("-22.835566 -47.078705"));
    }

    @Test
    public void getCoordenadasByEnderecoErroEntrada2Test() {
        Assert.assertNull(googleMapsAPI.getCoordenadasByEndereco("endereco que nao existe"));
    }
}
