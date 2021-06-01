package ru.johnnygomezzz.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.soap.products.GetAllProductsRequest;
import ru.johnnygomezzz.soap.products.GetProductByIdRequest;
import ru.johnnygomezzz.soap.products.GetProductByIdResponse;
import ru.johnnygomezzz.services.ProductService;
import ru.johnnygomezzz.soap.products.GetAllProductsResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.johnnygomezzz.ru/products";
    private final ProductService productService;

    public static final Function<Product, ru.johnnygomezzz.soap.products.Product>
            functionProductToSoap = product -> {
        ru.johnnygomezzz.soap.products.Product ps = new ru.johnnygomezzz.soap.products.Product();
        ps.setId(product.getId());
        ps.setTitle(product.getTitle());
        ps.setPrice(product.getPrice());
        ps.setCategory(product.getCategory().getTitle());
        return ps;
    };

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        Long id = (Long) request.getId();
        ru.johnnygomezzz.soap.products.Product productSoap =
                productService.findById(id).map(functionProductToSoap).get();
        response.setProduct(productSoap);
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.flamexander.com/spring/ws/students">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllStudentsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<ru.johnnygomezzz.soap.products.Product> productsSoap =
                productService.getAllProducts().stream().map(functionProductToSoap).collect(Collectors.toList());
        productsSoap.forEach(response.getProducts()::add);

        return response;
    }
}
