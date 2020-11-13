package org.stoom.crud.utils;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;

public class GoogleMapsAPI {

    private static String KEY = "AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos";
    private final GeoApiContext context;


    public GoogleMapsAPI() {
        final GeoApiContext.Builder builder = new GeoApiContext.Builder().apiKey(KEY);
        this.context = builder.build();
    }

    public Double[] getCoordenadasByEndereco(final String endereco){
        System.out.println("GoogleMapsAPI getCoordenadasByEndereco - endereco: " + endereco);
        final Double[] latLongRet = new Double[2];
        try {
            final GeocodingResult[] request = GeocodingApi.newRequest(context).address(endereco).await();
            if(request.length >= 1){
                final LatLng location = request[0].geometry.location;
                latLongRet[0] = location.lat;
                latLongRet[1] = location.lng;
                System.out.println("GoogleMapsAPI getCoordenadasByEndereco - latLongRet: " +
                        latLongRet[0] + " " + latLongRet[1]);
                return latLongRet;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEnderecoByCoordenadas(final String coordenadas){
        System.out.println("GoogleMapsAPI getEnderecoByCoordenadas - coordenadas: " + coordenadas);
        String endereco = null;
        try {
            final LatLng latLng = new LatLng();
            final String[] arrayCoordenadas = coordenadas.split(",");
            if(arrayCoordenadas.length == 2){
                latLng.lat = Double.parseDouble(arrayCoordenadas[0].trim());
                latLng.lng = Double.parseDouble(arrayCoordenadas[1].trim());;
                endereco = GeocodingApi.newRequest(context).latlng(latLng).await()[0].formattedAddress;
                System.out.println("GoogleMapsAPI getEnderecoByCoordenadas - endereco: " + endereco);
                return endereco;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
