package com.countryservice.service;

import com.countryservice.dto.CountryDto;
import com.protomodule.service.Country;
import com.protomodule.service.CountryRequest;
import com.protomodule.service.CountryResponse;
import com.protomodule.service.CountryServiceGrpc;
import com.protomodule.service.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@GrpcService
public class CountryService extends CountryServiceGrpc.CountryServiceImplBase {


    @Override
    public void getCountries(Empty request, StreamObserver<CountryResponse> responseObserver) {
        List<Country> countries = countries()
                .stream()
                .map(countryDto ->
                        Country.newBuilder()
                                .setId(countryDto.getId())
                                .setName(countryDto.getName())
                                .setPopulation(countryDto.getPopulation())
                                .build()).collect(Collectors.toList());
        responseObserver.onNext(CountryResponse.newBuilder().addAllCountry(countries).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getCountriesAsStream(Empty request, StreamObserver<Country> responseObserver) {
        countries().forEach(countryDto -> {
            Country country =
                    Country.newBuilder()
                            .setId(countryDto.getId())
                            .setName(countryDto.getName())
                            .setPopulation(countryDto.getPopulation())
                            .build();
            responseObserver.onNext(country);
        });
        responseObserver.onCompleted();
    }

    @Override
    public void getCountryByIdAsStream(CountryRequest request, StreamObserver<Country> responseObserver) {
        Optional<CountryDto> country = countries().stream().filter(countryObj -> countryObj.getId() == request.getId()).findFirst();
        responseObserver.onNext(Country.newBuilder().setId(country.get().getId()).setName(country.get().getName()).setPopulation(country.get().getPopulation()).build());
        responseObserver.onCompleted();
    }

    private List<CountryDto> countries() {
        return Arrays.asList(
                new CountryDto(1, "NORWAY", 33333333),
                new CountryDto(2, "USA", 5555555),
                new CountryDto(3, "CHINA", 66666666),
                new CountryDto(4, "GERMANY", 66666666),
                new CountryDto(5, "TURKEY", 888888888),
                new CountryDto(6, "FINLAND", 999999999)
        );
    }

}
