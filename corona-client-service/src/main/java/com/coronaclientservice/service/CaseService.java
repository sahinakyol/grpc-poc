package com.coronaclientservice.service;

import com.protomodule.service.Case;
import com.protomodule.service.Corona;
import com.protomodule.service.CoronaRequest;
import com.protomodule.service.CoronaServiceGrpc;
import com.protomodule.service.CountryServiceGrpc;
import com.protomodule.service.Empty;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Iterator;

import static com.coronaclientservice.util.ProtoToJson.toJson;

@Service
public class CaseService {

    @GrpcClient("corona-service")
    private CoronaServiceGrpc.CoronaServiceBlockingStub coronaServiceBlockingStub;

    @GrpcClient("country-service")
    private CountryServiceGrpc.CountryServiceBlockingStub countryServiceBlockingStub;

    public Flux<String> getCases() {
        return Flux.create(fluxSink -> {
            countryServiceBlockingStub.getCountriesAsStream(Empty.newBuilder().build()).forEachRemaining(country -> {
                Iterator<Corona> coronaCasesByIdAsStream = coronaServiceBlockingStub.getCoronaCasesByIdAsStream(CoronaRequest.newBuilder().setId(country.getId()).build());
                if (coronaCasesByIdAsStream.hasNext()) {
                    coronaCasesByIdAsStream.forEachRemaining(corona -> {
                        Case coronaCountryCase = Case.newBuilder().setCountry(country).setCorona(corona).build();
                        try {
                            fluxSink.next(toJson(coronaCountryCase));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
            fluxSink.complete();
        });
    }
}
