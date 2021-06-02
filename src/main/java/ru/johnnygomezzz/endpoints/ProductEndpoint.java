package ru.johnnygomezzz.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.johnnygomezzz.services.ProductService;
import ru.johnnygomezzz.soap.products.GetAllProductsRequest;
import ru.johnnygomezzz.soap.products.GetAllProductsResponse;
import ru.johnnygomezzz.soap.products.GetProductByIdRequest;
import ru.johnnygomezzz.soap.products.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.johnnygomezzz.ru/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProductEntity(productService.getById(request.getId()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.johnnygomezzz.ru/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().forEach(response.getProductEntities()::add);
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.johnnygomezzz.ru/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByIdRequest>
                    <f:id>1</f:id>
                </f:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */
}
