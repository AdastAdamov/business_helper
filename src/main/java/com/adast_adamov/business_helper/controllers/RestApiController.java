package com.adast_adamov.business_helper.controllers;

import com.adast_adamov.business_helper.exceptions.ExpiredTokenException;
import com.adast_adamov.business_helper.exceptions.InvalidPasswordException;
import com.adast_adamov.business_helper.exceptions.UserInputValidatationException;
import com.adast_adamov.business_helper.exceptions.UserNotExistsException;
import com.adast_adamov.business_helper.models.Product;
import com.adast_adamov.business_helper.models.User;
import com.adast_adamov.business_helper.models.UserProduct;
import com.adast_adamov.business_helper.models.requests.AddUserCardRequest;
import com.adast_adamov.business_helper.models.requests.AuthorizationRequest;
import com.adast_adamov.business_helper.models.responses.AuthorizationResponse;
import com.adast_adamov.business_helper.services.interfaces.ProductService;
import com.adast_adamov.business_helper.services.interfaces.TokenService;
import com.adast_adamov.business_helper.services.interfaces.UserProductService;
import com.adast_adamov.business_helper.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserProductService userProductService;

    @PostMapping(value = "/login")
    public AuthorizationResponse login(@ModelAttribute("authorizationRequest") AuthorizationRequest authorizationRequest,
                                       HttpServletResponse response,
                                       Model model) {
        try {
            User user = userService.authorizeUser(authorizationRequest);
            response.addCookie(new Cookie("token", tokenService.getToken(user)));
        }
        catch (UserNotExistsException e) {
            // Extract error text from here to localization file and supply it with template
            return new AuthorizationResponse(false, 1,"Such user doesn't exist");
        }
        catch (InvalidPasswordException e) {
            return new AuthorizationResponse(false, 2,"Invalid password");
        }
        return new AuthorizationResponse(true, 0,null);
    }

    @GetMapping(value = "/user_products")
    public List<Product> getProducts(String bankName) {
        try {
            return productService.getProducts(bankName);
        }
        catch (UserInputValidatationException e) {
            //TODO Add exception processing
            return Collections.emptyList();
        }
    }

    @PostMapping(value = "/user_products/add_card")
    public void addUserCard(@RequestParam("productId") Long id,
                            @RequestParam("lastOperationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastOperationDate,
                            @CookieValue(value = "token") String token) {
        try {
            UserProduct userProduct = new UserProduct();
            userProduct.setUserId(tokenService.getUser(token).getId());
            userProduct.setProductId(id);
            userProduct.setLastOperationDate(lastOperationDate);
            userProductService.addUserCard(userProduct);
//        }
//        catch (MyResourceNotFoundException exc) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foo Not Found", exc);
        } catch (ExpiredTokenException e) {
            e.printStackTrace();
        }
    }
}
