package com.coronaclientservice.service;

import com.protomodule.service.CoronaResponse;
import com.protomodule.service.CoronaServiceGrpc;
import com.protomodule.service.Empty;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.coronaclientservice.util.ProtoToJson.toJson;

@Service
public class CoronaService {

    @GrpcClient("corona-service")
    private CoronaServiceGrpc.CoronaServiceBlockingStub coronaServiceBlockingStub;

    public String getCoronaCases() throws IOException {
        CoronaResponse coronaCases = coronaServiceBlockingStub.getCoronaCases(Empty.newBuilder().build());
        return toJson(coronaCases);
    }
}
