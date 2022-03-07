package ru.gb.springShop.core.endpointsSOAP;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.springShop.core.services.soap.ProductSoapService;
import ru.gb.springShop.core.soap.products.GetAllProductSOAPRequest;
import ru.gb.springShop.core.soap.products.GetAllProductSOAPResponse;


@Slf4j
@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.flamexander.com/spring/soap/products";
    private final ProductSoapService productSoapService;



    /*
        Пример : POST http://localhost:8189/soap
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.flamexander.com/spring/soap/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductSOAPRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductSOAPRequest")
    @ResponsePayload
    public GetAllProductSOAPResponse getAllProduct(@RequestPayload GetAllProductSOAPRequest request) {
        log.info(request+"!!!!ProductEndpoint!!!!");/// до сюда даже не доходит.
        GetAllProductSOAPResponse response = new GetAllProductSOAPResponse();
        productSoapService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
