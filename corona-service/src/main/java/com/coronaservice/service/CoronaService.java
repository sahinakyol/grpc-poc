package com.coronaservice.service;

import com.protomodule.service.Corona;
import com.protomodule.service.CoronaRequest;
import com.protomodule.service.CoronaResponse;
import com.protomodule.service.CoronaServiceGrpc;
import com.protomodule.service.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@GrpcService
public class CoronaService extends CoronaServiceGrpc.CoronaServiceImplBase {

    @Override
    public void getCoronaCases(Empty request, StreamObserver<CoronaResponse> responseObserver) {
        List<Corona> coronaCases = getCoronas();

        responseObserver.onNext(CoronaResponse.newBuilder().addAllCorona(coronaCases).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getCoronaCasesAsStream(Empty request, StreamObserver<Corona> responseObserver) {
        getCoronas().forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void getCoronaCasesByIdAsStream(CoronaRequest request, StreamObserver<Corona> responseObserver) {
        Optional<Corona> corona = getCoronas().stream().filter(coronaObj -> coronaObj.getCountryId() == request.getId()).findFirst();
        corona.ifPresent(coronaObj -> {
            responseObserver.onNext(corona.get());
            responseObserver.onCompleted();
        });
        responseObserver.onCompleted();
    }

    private List<Corona> getCoronas() {
        Corona coronaCase1 = Corona.newBuilder().setCountryId(3).setTestNumber(1111).setCaseNumber(4444).setPatientNumber(5555).setDeathNumber(66666).setRecoveringNumber(999999).build();
        Corona coronaCase2 = Corona.newBuilder().setCountryId(2).setTestNumber(22222).setCaseNumber(4444).setPatientNumber(5555).setDeathNumber(66666).setRecoveringNumber(999999).build();
        Corona coronaCase3 = Corona.newBuilder().setCountryId(1).setTestNumber(44444).setCaseNumber(4444).setPatientNumber(5555).setDeathNumber(66666).setRecoveringNumber(999999).build();
        Corona coronaCase4 = Corona.newBuilder().setCountryId(6).setTestNumber(44444).setCaseNumber(4444).setPatientNumber(5555).setDeathNumber(66666).setRecoveringNumber(999999).build();

        List<Corona> coronaCases = Arrays.asList(coronaCase1, coronaCase2, coronaCase3, coronaCase4);
        return coronaCases;
    }
}
