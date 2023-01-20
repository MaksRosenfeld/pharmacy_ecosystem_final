package ru.budgetapteka.pharmacy_ecosystem_final.api;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
@Getter
@Component
public class RequestFactory {

    private final Map<RequestType, Request> requests = new EnumMap<>(RequestType.class);

    public Request createRequest(RequestType requestType) {
        return requests.get(requestType);
    }

    protected void register(Request request) {
        requests.put(request.getType(), request);
    }
}
