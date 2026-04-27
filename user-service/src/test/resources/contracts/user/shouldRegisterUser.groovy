package contracts.user

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should register a new user")
    request {
        method 'POST'
        url '/api/v1/users/register'
        headers {
            contentType('application/x-www-form-urlencoded')
        }
        body([
            username: 'testuser',
            password: 'password123',
            phone: '13800138000'
        ])
    }
    response {
        status 200
        body([
            userId: 1,
            token: "mock-token-testuser"
        ])
        headers {
            contentType('application/json')
        }
    }
}