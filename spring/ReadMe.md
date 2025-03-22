spring-doc

    It has implementation is using Spring Doc i.e. Swagger using Spring Doc since swagger is not more supported after Spring Boot 3.x

spring-security-jwt 
    
    It has implementation using JWT in Spring Rest API

spring-webflux 

    It has implementation using reactive controller and using jwt along with ReactiveAuthenticationManager
    In this JWTFilter is extending AuthenticationWebFilter which uses ReactiveAuthenticationManager needs ReactiveUserDetailsService implementation for user authentication

spring-webflux-jwt 
    
    It has implementation using filter() method in Router which does not need any Filter implementation for authentication and authorization
    Inside filter() we can create our custom was for authentication and authorization and does not depend on any Filter