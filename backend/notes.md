# Potential Future Projects
## OpenAPI Specification
https://springfox.github.io/springfox/docs/current/

-----------------------------------------------------------------------

# JWT

Authorization: This is the most common scenario for using JWT. Once the user is logged in, each subsequent request will include the JWT, allowing the user to access routes, services, and resources that are permitted with that token. Single Sign On is a feature that widely uses JWT nowadays, because of its small overhead and its ability to be easily used across different domains.

In its compact form, JSON Web Tokens consist of three parts separated by dots (.), which are:

    Header
    Payload
    Signature

Therefore, a JWT typically looks like the following.

xxxxx.yyyyy.zzzzz

## Header
{
  "alg": "HS256",
  "typ": "JWT"
}


this JSON is Base64Url encoded to form the first part of the JWT.

## Payload
The second part of the token is the payload, which contains the claims. Claims are statements about an entity (typically, the user) and additional data. There are three types of claims: registered, public, and private claims.

{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true
}

## Signature 

HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret)



In authentication, when the user successfully logs in using their credentials, a JSON Web Token will be returned.

Whenever the user wants to access a protected route or resource, the user agent should send the JWT, typically in the Authorization header using the Bearer schema. The content of the header should look like the following:

Authorization: Bearer <token>

Logging in:
typically:
/oauth/authorize 



https://docs.spring.io/spring-data/rest/docs/current/reference/html/

## JWT @ Innovonto
### Moderator Flow
Login via "/auth" with username & password:

Gets a token with:

    sub	-> <Username>
    iat	-> <Creation Date>
    exp	-> <Expiration Date>

This is validated in:

de.fuberlin.innovonto.brainstormingapp.security.JwtAuthorizationTokenFilter.doFilterInternal

### Ideator Flow
Create Session via "/create-session" with ChallengeID

de.fuberlin.innovonto.brainstormingapp.security.ideationsession.IdeationSessionController.createAuthenticationToken

Gets a token with:

* sub is the anonymous ideator-id
* s is the session id
* c is the challenge id
* t is the type "ids" -> ideationSession

This is validated in:

de.fuberlin.innovonto.brainstormingapp.security.JwtAuthorizationTokenFilter.doFilterInternal