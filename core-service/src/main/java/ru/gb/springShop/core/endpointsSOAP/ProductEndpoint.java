package ru.gb.springShop.core.endpointsSOAP;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.springShop.core.services.soap.GetAllProductSOAPRequest;
import ru.gb.springShop.core.services.soap.GetAllProductSOAPResponse;
import ru.gb.springShop.core.services.soap.ProductSoapService;

@Slf4j
@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.gb.ru/springShop/soap/products";
    private final ProductSoapService productSoapService;



    /*
        Пример : POST http://localhost:8189/shop-core/soap
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.gb.ru/springShop/soap/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductSOAPRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductSOAPRequest")
    @ResponsePayload
    public GetAllProductSOAPResponse getAllProduct(@RequestPayload GetAllProductSOAPRequest request) {
         log.info("!!!!ProductEndpoint!!!!"+request);/// до сюда даже не доходит.
        GetAllProductSOAPResponse response = new GetAllProductSOAPResponse();
        log.info("!!!!ProductEndpoint!!!!"+request);/// до сюда даже не доходит.
        productSoapService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
