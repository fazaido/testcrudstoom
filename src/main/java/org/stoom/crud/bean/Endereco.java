package org.stoom.crud.bean;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "ENDERECO", schema = "PUBLIC", catalog = "TEST")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String streetName;
	private Integer number;
	private String complement;
	private String neighbourhood;
	private String city;
	private String state;
	private String country;
	private Integer zipcode;
	private Double latitude;
	private Double longitude;
	private Date dataCriacao;
	private Date dataAtualizacao;

	public Endereco() {
	}

	public Endereco(Integer id, String streetName, Integer number, String complement, String neighbourhood,
					String city, String state, String country, Integer zipcode, Double latitude,
					Double longitude, Date dataCriacao, Date dataAtualizacao) {
		this.id = id;
		this.streetName = streetName;
		this.number = number;
		this.complement = complement;
		this.neighbourhood = neighbourhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}

	@Id
	@Basic
	@GenericGenerator(name="nome_interno_do_gerador_de_id", strategy="org.hibernate.id.IncrementGenerator")
	@GeneratedValue(generator="nome_interno_do_gerador_de_id")
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	@Basic
	@Column(name = "STREETNAME")
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(final String streetName) {
		this.streetName = streetName;
	}

	@Basic
	@Column(name = "NUMBER")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(final Integer number) {
		this.number = number;
	}

	@Basic
	@Column(name = "COMPLEMENT")
	public String getComplement() {
		return complement;
	}

	public void setComplement(final String complement) {
		this.complement = complement;
	}

	@Basic
	@Column(name = "NEIGHBOURHOOD")
	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(final String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	@Basic
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@Basic
	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	@Basic
	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	@Basic
	@Column(name = "ZIPCODE")
	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(final Integer zipcode) {
		this.zipcode = zipcode;
	}

	@Basic
	@Column(name = "LATITUDE")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(final Double latitude) {
		this.latitude = latitude;
	}

	@Basic
	@Column(name = "LONGITUDE")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(final Double longitude) {
		this.longitude = longitude;
	}

    @Basic
    @Column(name = "DATACRIACAO")
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Basic
	@Column(name = "DATAATUALIZACAO")
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(final Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Endereco endereco = (Endereco) o;
		return id.equals(endereco.id) &&
				streetName.equals(endereco.streetName) &&
				number.equals(endereco.number) &&
				complement.equals(endereco.complement) &&
				neighbourhood.equals(endereco.neighbourhood) &&
				city.equals(endereco.city) &&
				state.equals(endereco.state) &&
				country.equals(endereco.country) &&
				zipcode.equals(endereco.zipcode) &&
				latitude.equals(endereco.latitude) &&
				longitude.equals(endereco.longitude) &&
				dataCriacao.equals(endereco.dataCriacao) &&
				dataAtualizacao.equals(endereco.dataAtualizacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, streetName, number, complement, neighbourhood, city, state, country, zipcode, latitude, longitude, dataCriacao, dataAtualizacao);
	}

	@Override
	public String toString() {
		return "Endereco{" +
				"id=" + id +
				", streetName='" + streetName + '\'' +
				", number=" + number +
				", complement='" + complement + '\'' +
				", neighbourhood='" + neighbourhood + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", country='" + country + '\'' +
				", zipcode=" + zipcode +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", dataCriacao=" + dataCriacao +
				", dataAtualizacao=" + dataAtualizacao +
				'}';
	}
}