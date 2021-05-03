package com.coronaclientservice.service;

import com.protomodule.service.CountryResponse;
import com.protomodule.service.CountryServiceGrpc;
import com.protomodule.service.Empty;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.coronaclientservice.util.ProtoToJson.toJson;

@Service
public class CountryService {

    @GrpcClient("country-service")
    private CountryServiceGrpc.CountryServiceBlockingStub countryServiceBlockingStub;

    public String getCountries() throws IOException {
        CountryResponse countries = countryServiceBlockingStub.getCountries(Empty.newBuilder().build());
        return toJson(countries);
    }
}
