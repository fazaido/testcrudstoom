package org.stoom.crud.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import org.stoom.crud.bean.Endereco;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;

@Repository
public class EnderecoDAO {

    private Session session;

    public EnderecoDAO() {
        System.out.println("INICIALIZANDO EnderecoDAO");
        final Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        this.session = cfg.buildSessionFactory().openSession();
        System.out.println("FINALIZANDO EnderecoDAO");
    }

    public Boolean create(final Endereco endereco){
        try {
            this.session.beginTransaction();
            endereco.setId(null);
            endereco.setDataCriacao(new Date());

            this.session.save(endereco);
            this.session.getTransaction().commit();
            return Boolean.TRUE;
        } catch (final HibernateException e) {
            e.printStackTrace();
            this.session.getTransaction().rollback();
        }
        return Boolean.FALSE;
    }

    public Endereco update(final Endereco end, final Endereco endOld){
        System.out.println("INICIANDO - Utils.update - " + end);
        try {
            this.session.beginTransaction();

            final String stName = end.getStreetName();
            if(stName != null && !stName.isEmpty() && !endOld.getStreetName().equalsIgnoreCase(stName)){
                endOld.setStreetName(end.getStreetName());
            } else {
                final Integer num = end.getNumber();
                if(num != null && !endOld.getNumber().equals(num)){
                    endOld.setNumber(end.getNumber());
                } else {
                    final String bairro = end.getNeighbourhood();
                    if(bairro != null && !bairro.isEmpty() && !endOld.getNeighbourhood().equalsIgnoreCase(bairro)) {
                        endOld.setNeighbourhood(end.getNeighbourhood());
                    } else {
                        final String cidade = end.getCity();
                        if(cidade != null && !cidade.isEmpty() && !endOld.getCity().equalsIgnoreCase(cidade)) {
                            endOld.setCity(end.getCity());
                        } else {
                            final String estado = end.getState();
                            if(estado != null && !estado.isEmpty() && !endOld.getState().equalsIgnoreCase(estado)) {
                                endOld.setState(end.getState());
                            } else {
                                final String pais = end.getCountry();
                                if(pais != null && !pais.isEmpty() && !endOld.getCountry().equalsIgnoreCase(pais)) {
                                    endOld.setCountry(end.getCountry());
                                } else {
                                    final String compl = end.getComplement();
                                    if(compl != null && !compl.isEmpty() && !endOld.getComplement().equalsIgnoreCase(compl)) {
                                        endOld.setComplement(end.getComplement());
                                    } else {
                                        final Integer cep = end.getZipcode();
                                        if (cep != null && !endOld.getZipcode().equals(pais)) {
                                            endOld.setZipcode(end.getZipcode());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            endOld.setLatitude(end.getLatitude());
            endOld.setLongitude(end.getLongitude());
            endOld.setDataAtualizacao(new Date());

            this.session.update(endOld);
            this.session.getTransaction().commit();
        } catch (final HibernateException e) {
            e.printStackTrace();
            this.session.getTransaction().rollback();
            return null;
        }
        return end;
    }

    public Boolean delete(final Integer id){
        try {
            this.session.beginTransaction();

            final Endereco endereco = this.session.load(Endereco.class, id);
            if (endereco != null) {
                this.session.delete(endereco);
                this.session.getTransaction().commit();
                return Boolean.TRUE;
            }
        } catch(final Exception ex) {
            ex.printStackTrace();
            this.session.getTransaction().rollback();
        }
        return Boolean.FALSE;
    }


    public List<Endereco> getByEndereco(final String rua) {
        System.out.println("INICIANDO getByEndereco - rua: " + rua);
        try {
            final List<Endereco> retEnd = new ArrayList<Endereco>();
            final StringBuffer consulta = new StringBuffer();
            consulta.append("SELECT id, streetName, number, complement, ")
                    .append("neighbourhood, city, state, country, zipcode, latitude, longitude, dataCriacao, ")
                    .append("dataAtualizacao FROM Endereco WHERE lower(streetName) LIKE :st");
            final Query query= this.session.createNativeQuery(consulta.toString());
            query.setParameter("st", "%" + rua.toLowerCase() + "%");
            final List<Object[]> ret = query.getResultList();
            Endereco end;
            for (final Object[] e : ret){
                end = new Endereco();
                end.setId(((BigInteger) e[0]).intValue());
                end.setStreetName((String) e[1]);
                end.setNumber((Integer) e[2]);
                end.setComplement((String) e[3]);
                end.setNeighbourhood((String) e[4]);
                end.setCity((String) e[5]);
                end.setState((String) e[6]);
                end.setCountry((String) e[7]);
                end.setZipcode((Integer) e[8]);
                end.setLatitude((Double) e[9]);
                end.setLongitude((Double) e[10]);
                end.setDataCriacao((Date) e[11]);
                end.setDataAtualizacao((Date) e[12]);
                retEnd.add(end);
            }
            System.out.println("FINALIZANDO getByEndereco - rua: " + rua);
            return retEnd;
        } catch (final Exception e) {
            e.printStackTrace();
            System.out.println("FINALIZANDO getByEndereco - rua: " + rua);
            return new ArrayList<Endereco>();
        }
    }

    public Endereco getById(final Integer id) {
        try {
            return this.session.get(Endereco.class, id);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Endereco> getAllEndereco() {
        System.out.println("INICIANDO getAllEndereco");
        List<Endereco> ret = new ArrayList<Endereco>();
        try {
            ret = this.session.createCriteria(Endereco.class).list();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        System.out.println("FINALIZANDO getAllEndereco");
        return ret;
    }
}