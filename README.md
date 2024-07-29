
# CartManager API

## Description
Backend application for managing carts in an e-commerce system. Allows creating, deleting, retrieving and adding products.
Carts are automatically deleted after 10 minutes of inactivity.

## Requirements
- Java 22
- Maven

## Setup

### Clone the Repository
```
git clone https://github.com/Pabulo-dev/cartmanagement.git
cd cartmanager
```

### Build & Run the project
```
mvn clean install
mvn spring-boot:run
```

## API Documentation

**Swagger UI:** http://localhost:8080/cart-api.html

### Create a Cart

**Method:** `POST`  
**URL:** `/cart`  
**Sample Response:**

    "id": 1,
    "products": [],
    "lastUpdate": "2024-07-29T20:15:30" 

### Get Cart Information

**Method:** `GET`  
**URL:** `/cart/{id}`  
**Path Parameters:**

-   `id` (Long): Cart ID

**Sample Response:**

    "id": 1,
    "products": [
        {
            "id": 101,
            "description": "Laptop",
            "amount": 2
        }
    ],
    "lastUpdate": "2024-07-29T20:15:30"
 

### Add a Product to a Cart

**Method:** `POST`  
**URL:** `/cart/{id}/product/{productId}/amount/{amount}`  
**Path Parameters:**

-   `id` (Long): Cart ID
-   `productId` (Long): Product ID
-   `amount` (Integer): Product quantity

**Sample Response:**

    "id": 1,
    "products": [
        {
            "id": 101,
            "description": "Keyboard",
            "amount": 5
        },
    ],
    "lastUpdate": "2024-07-29T20:16:30"
 

### Add Multiple Products to a Cart

**Method:** `POST`  
**URL:** `/cart/{id}/products`  
**Path Parameters:**   

- `id` (Long): Cart ID

**Sample Request Body (JSON):**

    [{
        "id": 101,
        "description": "Keyboard",
        "amount": 5
    },
    {
        "id": 102,
        "description": "Mouse",
        "amount": 10
    }]
 

**Sample Response:**

    "id": 1,
    "products": [
        {
            "id": 101,
            "description": "Laptop",
            "amount": 1
        },
        {
            "id": 102,
            "description": "Mouse",
            "amount": 1
        }
    ],
    "lastUpdate": "2024-07-29T20:16:30"
 

### Delete a Cart

**Method:** `DELETE`  
**URL:** `/cart/{id}`   
**Path Parameters:** `id` (Long): Cart ID

**Sample Response:**
`{"message": "Cart {id} deleted"}`
