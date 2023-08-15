# SB3 Security Revision
    Just a normal revision...


## Tried JWT Configuration: Fake Login
    Just issued a jwt token out of email and other contents provided in post request....

    Here, Fake Login means we are providing any username or email and password but instead of authenticating 
    with any Authentication Provider, it provides a "JWT" as if authentication was successful.

    Following Description will be about "Creating To Using JWT token...."

## Creating to Using JWT: All of its configuration and different classes associated with it.
    It can be further divided into 
    - Creating and
    - Using (All Configuration)


### Creation
    As soon as "/auth/login" gets hit with email and password, new token is issued using jwtIssuer
    jwtIssuer issues the token with the information obtained from the request.
    and the issued token is responsed back to browser or client...

    Classes used here
        - models/LoginRequest
        - models/LoginResponse
        - security/JwtIssuer

    With basic knowledge of Spring Security, Java, OAuth, JWT everthing must be crystal clear after
    viewing each classes.


### Using
    Here, thing is bit complicated as i used a lot of classes to get things happen

    Basic
        - Default Filter is override
        - JWT token is extracted from Authorization header
        - This JWT token is decoded.
        - Decoded information is converted to principal (we'll obtain values of principal; like username)
        - all values of principal is used to make new principal with new expiry date.


    Description according to all used classes
        - security/JwtAuthenticationFilter is invoked first from security/SecurityConfiguration: addFilterBefore
            - This class uses all classes to make all the things noted in Basic make happen.
            - AuthorizationHeader is extracted here and JWT is extracted from that Header and provided to
            Decoder
        - security/JwtDecoder decodes the payload of JWT and sends it to JwtToPrincipalConverter
        - security/JwtToPrincipalConverter converts the payload to principal's values which will be used to
        make new principal
            - this uses UserPrincipal which is child class of UserDetails to extract principal values
        - security/UserPrincipalAuthentication takes necessary information required to make new prinicpal,
        keeps principal authentiated with new Expiry date.. and returns the prinicipal


    This is it all the classes can be seen and understood... Reference of this configuration is kept at last.

## Real Login
    Still this is not a actual login method where we obtain user from database. Here i use user
    created user or hardcoded the user in backend.

    So first of all, i a UserEntity Class which is used to access the users from the database, or
    table is formed with this entity.

    About UserService
    Then Created a service/UserService which acts as a repository to extract data form the database,
    it is basically an interface to access data from database.

    This service will retuen details of user accourding to username provided to find the user.

    About CustomUserDetailsService
    This will use UserService or Repository or its method to get the user details and return this
    details in format of built in UserDetails or in format of Class which extends UserDetails.
    Here, I used Prinicipal User as return format.

    Security Configuration
    Two Beans must be added here
        - Authentication Manager
            - takes CustomUserDetailsService returned info
            - Password Encoder
        - Password Encoder

    In Auth Controller
        Inside Login controller method or endpoint we use Authentication manager,
        Use its default UsernamePasswordAuthToken method passing username and password
        the prinicpal is generated which will be used to generate the JWT



    This much for Login, I will update Database Configuration in this very repository soon.
    Although i'd already configured database in previous repos..


## Added Roles
    In this commit i did few things, refactored AuthController.
    Made a Service for Authentication purpose. -> service/AuthService.

    Created new user with role other than ADMIN

    And configured permission for new endpoint /admin to have access only for admin.

    That's it for this time...


    Soon, exception handling and dtabase integration will be done...


[Reference](https://www.youtube.com/playlist?list=PLVuqGBBX_tP3KmownF68ifFmgPQt-ujBg)


[Manandhar Sudip](https://manandharsudip.com.np)