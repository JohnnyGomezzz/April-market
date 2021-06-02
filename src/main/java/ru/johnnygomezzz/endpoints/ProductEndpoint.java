package ru.johnnygomezzz.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.soap.products.*;
import ru.johnnygomezzz.services.ProductService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.johnnygomezzz.ru/products";
    private final ProductService productService;

    public static final Function<Product, ProductEntity>
            functionProductToSoap = product -> {
        ProductEntity pe = new ProductEntity();
        pe.setId(product.getId());
        pe.setTitle(product.getTitle());
        pe.setPrice(product.getPrice());
        pe.setCategory(product.getCategory().getTitle());
        return pe;
    };

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        Long id = (Long) request.getId();
        ProductEntity productEntitySoap =
                productService.findById(id).map(functionProductToSoap).get();
        response.setProduct(productEntitySoap);
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
        List<ProductEntity> productsSoap =
                productService.getAllProducts().stream().map(functionProductToSoap).collect(Collectors.toList());
        productsSoap.forEach(response.getProducts()::add);

        return response;
    }
}
