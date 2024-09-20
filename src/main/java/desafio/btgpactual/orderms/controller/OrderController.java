package desafio.btgpactual.orderms.controller;

import desafio.btgpactual.orderms.controller.dto.ApiResponse;
import desafio.btgpactual.orderms.controller.dto.OrderResponse;
import desafio.btgpactual.orderms.controller.dto.PaginationResponse;
import desafio.btgpactual.orderms.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*") // Permite todas as origens
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers")
    public ResponseEntity<?> ola(){
        System.out.println("ola mundo");
        return ResponseEntity.ok("Encontrou o método");
    }

    @GetMapping("/customers/{customerid}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(@PathVariable("customerid") Long customerId,
                                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));

        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalOnOrders", totalOnOrders),
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));
    }
}

