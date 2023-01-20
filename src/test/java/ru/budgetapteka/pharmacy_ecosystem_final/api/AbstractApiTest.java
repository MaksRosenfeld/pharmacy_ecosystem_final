package ru.budgetapteka.pharmacy_ecosystem_final.api;

import lombok.SneakyThrows;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class AbstractApiTest {

    // necessary to upload because of lazy initialization of bean in tests
    @Autowired
    private BankOpenStatementRequest bankOpenStatementRequest;

    @Autowired
    private Base1CFinanceRequest base1CFinanceRequest;

    static MockWebServer mockWebServer;
    static String mockBaseUrl;


    @BeforeAll
    static void initializeMockWebServer() {
        mockWebServer = new MockWebServer();
        mockBaseUrl = mockWebServer.url("/").toString();
        Dispatcher dispatcher = new Dispatcher() {
            @SneakyThrows
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
                return switch (recordedRequest.getPath()) {
                    case "/statement?from=2023-01-18&to=2023-02-18" -> new MockResponse()
                            .setBody(Files.readString(Path.of("src/test/resources/statement_order.json")))
                            .setHeader("Content-Type", "application/json");
                    case "/statement/373737" -> new MockResponse()
                            .setBody(Files.readString(Path.of("src/test/resources/statement_result.json")))
                            .setHeader("Content-Type", "application/json");
                    case "/finance?from=2023-01-18&to=2023-02-18" -> new MockResponse()
                            .setBody(Files.readString(Path.of("src/test/resources/finance_1C.json")))
                            .setHeader("Content-Type", "application/json");
                    default -> new MockResponse();
                };

            }
        };
        mockWebServer.setDispatcher(dispatcher);
    }




}
