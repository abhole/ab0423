# tool-rental

## What is this?
A Tool Rental Application REST API Microservice in Java with Postgres database

## Prerequisites
- Docker & Docker Compose
- Gradle
- Java 17

## Build and Test
The tests rely on a database so start the db container using this command:
```shell
docker compose up
```
The application can be packaged using:
```shell script
./gradlew clean build
```

This runs the Rest endpoint tests (that use RestAssured and Junit). The Test Reports are available under
`./build/reports/index.html`

And creates an executable jar located at
`./build/quarkus-app/quarkus-run.jar`

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

Examples
Note: `jq` is optional below. it is only used to prettyprint the JSON responses

``` shell
➜  ~ curl -s -X POST -H "Content-Type: application/json" -d '{"toolCode":"JAKR", "rentalDays": 5, "discountPercent": 101, "checkoutDate": "9/3/15"}' http://localhost:8080/rentals | jq

{
  "exception": null,
  "propertyViolations": [],
  "classViolations": [],
  "parameterViolations": [
    {
      "constraintType": "PARAMETER",
      "path": "createRental.rentalDto.discountPercent",
      "message": "must be less than or equal to 100",
      "value": "101"
    }
  ],
  "returnValueViolations": []
}
➜  ~ curl -s -X POST -H "Content-Type: application/json" -d '{"toolCode":"LADW", "rentalDays": 3, "discountPercent": 10, "checkoutDate": "7/2/20"}' http://localhost:8080/rentals | jq

{
  "id": "b5058a8f-9451-438e-94c3-94dab0fac19d",
  "toolCode": "LADW",
  "toolType": "Ladder",
  "brand": "Werner",
  "rentalDays": 3,
  "checkoutDate": "07/02/20",
  "dueDate": "07/05/20",
  "dailyRentalCharge": "$1.99",
  "chargeDays": 2,
  "preDiscountCharge": "$3.98",
  "discountPercent": 10,
  "discountAmount": "$0.40",
  "finalCharge": "$3.58"
}
➜  ~ curl -s -X POST -H "Content-Type: application/json" -d '{"toolCode":"CHNS", "rentalDays": 5, "discountPercent": 25, "checkoutDate": "7/2/15"}' http://localhost:8080/rentals | jq

{
  "id": "f581c08f-eeb4-4d6d-9eee-36988c5a2d1c",
  "toolCode": "CHNS",
  "toolType": "Chainsaw",
  "brand": "Stihl",
  "rentalDays": 5,
  "checkoutDate": "07/02/15",
  "dueDate": "07/07/15",
  "dailyRentalCharge": "$1.49",
  "chargeDays": 4,
  "preDiscountCharge": "$5.96",
  "discountPercent": 25,
  "discountAmount": "$1.49",
  "finalCharge": "$4.47"
}
➜  ~ curl -s -X POST -H "Content-Type: application/json" -d '{"toolCode":"CHNS", "rentalDays": 5, "discountPercent": 25, "checkoutDate": "7/2/15"}' http://localhost:8080/rentals | jq

{
  "id": "eafae6b6-ba95-4170-a1de-5f35ba502483",
  "toolCode": "CHNS",
  "toolType": "Chainsaw",
  "brand": "Stihl",
  "rentalDays": 5,
  "checkoutDate": "07/02/15",
  "dueDate": "07/07/15",
  "dailyRentalCharge": "$1.49",
  "chargeDays": 4,
  "preDiscountCharge": "$5.96",
  "discountPercent": 25,
  "discountAmount": "$1.49",
  "finalCharge": "$4.47"
}
➜  ~ curl -s -X POST -H "Content-Type: application/json" -d '{"toolCode":"JAKD", "rentalDays": 6, "discountPercent": 0, "checkoutDate": "9/3/15"}' http://localhost:8080/rentals | jq

{
  "id": "cae635c6-d6fc-4584-9039-6d9e7714609f",
  "toolCode": "JAKD",
  "toolType": "Jackhammer",
  "brand": "DeWalt",
  "rentalDays": 6,
  "checkoutDate": "09/03/15",
  "dueDate": "09/09/15",
  "dailyRentalCharge": "$2.99",
  "chargeDays": 3,
  "preDiscountCharge": "$8.97",
  "discountPercent": 0,
  "discountAmount": "$0.00",
  "finalCharge": "$8.97"
}
➜  ~ curl -s -X POST -H "Content-Type: application/json" -d '{"toolCode":"JAKR", "rentalDays": 9, "discountPercent": 0, "checkoutDate": "7/2/15"}' http://localhost:8080/rentals | jq

{
  "id": "4c11c4db-f9c6-4e45-a0d9-09389af8099a",
  "toolCode": "JAKR",
  "toolType": "Jackhammer",
  "brand": "Ridgid",
  "rentalDays": 9,
  "checkoutDate": "07/02/15",
  "dueDate": "07/11/15",
  "dailyRentalCharge": "$2.99",
  "chargeDays": 6,
  "preDiscountCharge": "$17.94",
  "discountPercent": 0,
  "discountAmount": "$0.00",
  "finalCharge": "$17.94"
}
➜  ~ curl -s -X POST -H "Content-Type: application/json" -d '{"toolCode":"JAKR", "rentalDays": 4, "discountPercent": 50, "checkoutDate": "7/2/20"}' http://localhost:8080/rentals | jq

{
  "id": "5771401a-0083-4885-b84d-26144e82ed94",
  "toolCode": "JAKR",
  "toolType": "Jackhammer",
  "brand": "Ridgid",
  "rentalDays": 4,
  "checkoutDate": "07/02/20",
  "dueDate": "07/06/20",
  "dailyRentalCharge": "$2.99",
  "chargeDays": 2,
  "preDiscountCharge": "$5.98",
  "discountPercent": 50,
  "discountAmount": "$2.99",
  "finalCharge": "$2.99"
}
```

