package logging;

import com.epam.reportportal.listeners.LogLevel;
import com.utils.ReportPortalLogging;

import context.ScenarioContext;

import enums.GlobalVariables;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.HashSet;

public class RestAssuredRequestFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestAssuredRequestFilter.class);
    protected ScenarioContext scenarioContext;

    public RestAssuredRequestFilter() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {
        String uri = requestSpec.getURI();
        uri =
                UrlDecoder.urlDecode(
                        uri,
                        Charset.forName(
                                requestSpec
                                        .getConfig()
                                        .getEncoderConfig()
                                        .defaultQueryParameterCharset()),
                        true);
        // NOSONAR
        String requestLog =
                RequestPrinter.print(
                        requestSpec,
                        requestSpec.getMethod(),
                        uri,
                        LogDetail.ALL,
                        new HashSet<>(),
                        generateStream(),
                        true);
        String logMessage = String.format("REQUEST DATA:%n%s", requestLog);
        scenarioContext.setContext(GlobalVariables.DATA_REQUEST, logMessage);
        //        LOGGER.info(logMessage);
        //        ReportPortalLogging.dispatchLog(logMessage, LogLevel.INFO.toString());
        ReportPortalLogging.dispatchLaunchLog(logMessage, LogLevel.INFO.toString());

        return ctx.next(requestSpec, responseSpec);
    }

    private PrintStream generateStream() {
        return new PrintStream(System.out) {
            @Override
            public void print(final String string) {
                //                System.out.println(string);
                LOGGER.info(string);
            }
        };
    }
}
