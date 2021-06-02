package ru.johnnygomezzz.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.johnnygomezzz.services.CategoryService;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {
    private static final String NAMESPACE_URI = "http://www.johnnygomezzz.ru/categories";
    private final CategoryService categoryService;

    /*
        Пример запроса: POST http://localhost:8189/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.johnnygomezzz.ru/groups">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getCategoryByTitleRequest>
                    <f:title>Продукты питания</f:title>
                </f:getCategoryByTitleRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByTitleRequest")
//    @ResponsePayload
//    @Transactional
//    public GetCategoryByTitleResponse getGroupByTitle(@RequestPayload GetCategoryByTitleRequest request) {
//        GetCategoryByTitleResponse response = new GetCategoryByTitleResponse();
//        response.setGroup(categoryService.getByTitle(request.getTitle()));
//        return response;
//    }
}
