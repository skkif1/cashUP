
Задание было выполнено в среде IDEA;
В ходе выполнения задания использывал Spring Boot 1.5.7 некоторые возможноси из java 8 (Stream API, DateTime API).
В качестве СУБД MySQl.
Код  также можна найти в моем репозитории на GitHub https://github.com/skkif1/cashup 


SERVICE API ENDPOINTS

ORDER

http://localhost:8080/order/create
http метод POST
header (Content-Type : application/xml) запрашивет ответ в формате xml
при успешном выполнении возврощает json обект только что созданого заказа с полем id Статус овета 201 (CREATED)
поле currency должно соответсвовать спецификации ISO 4217

пример запроса: http://localhost:8080/order/create
{
	"orderSum": 100.100,
	"currency":	"RUR",
	"customerId": 14
}

ответ:
{
  "id": 14,
  "orderDate": "2017-10-01",
  "orderStatus": "ACCEPTED",
  "orderSum": 100.1,
  "currency": "RUR",
  "customerId": 14
}
может возвращать статус овета 404 (NOT FOUND) с сообщением "NO SUCH USER" если не нашлось покупателя с таким id
может возвращать статус овета 400 (BAD REQUEST) с сообщением о возможных ошибках в теле запроса


http://localhost:8080/order/submit/{orderId}
http метод POST
параметр orderId являеться обезательным
Статус овета 201 (CREATED)
может возвращать статус овета 404 (NOT FOUND) с сообщением "NO SUCH ORDER" если не нашлось заказа с таким id
или 400 (BAD REQUEST) если не был указан orderID



http://localhost:8080/order/{id}
http метод GET
header (Content-Type : application/xml) запрашивет ответ в формате xml
при успешном выполнении возврощает все доступные заказы клиента. Статус овета 200 (ОК)
Параметр id являеться обезательным в противном случае будет получен статус ответа 404 (NOT FOUND);

пример ответа: http://localhost:8080/order/2
[
  {
    "id": 1,
    "orderDate": "2017-09-30",
    "orderStatus": "ACCEPTED",
    "orderSum": 10,
    "currency": "RUR",
    "customerId": 2
  },
]


http://localhost:8080/order/all/{limit}
http метод GET
header (Content-Type : application/xml) запрашивет ответ в формате xml
при успешном выполнении возврощает все доступные заказы ограничены параметром limit. Статус овета 200 (ОК)
параметр limit являеться опциональным

пример ответа:
[
  {
    "id": 1,
    "orderDate": "2017-09-30",
    "orderStatus": "ACCEPTED",
    "orderSum": 10,
    "currency": "RUR",
    "customerId": 2
  },
  {
    "id": 3,
    "orderDate": "2017-09-30",
    "orderStatus": "ACCEPTED",
    "orderSum": 100,
    "currency": "RUR",
    "customerId": 2
  }
]


CUSTOMER

http://localhost:8080/customer/add/
http метод POST
при успешном выполнении возврощает только что соданого клиента. Статус овета 201 (CREATED)
может возвращать Статус овета 409 (СONFLICT) если клиент уже был создан
								400 (BAD REQUEST) с возможными ошибками в теле запроса

пример запроса: http://localhost:8080/customer/add
{
	"firstName": "ALEX",
	"lastName": "CROSS",
	"birthDate": "08-01-1993",
	"gender": "MALE",
	"inn" : "7234467891"
}

пример ответа:
{
  "id": 23,
  "firstName": "ALEX",
  "lastName": "CROSS",
  "birthDate": "08-01-1993",
  "gender": "MALE",
  "inn": 7234467891
}



http://localhost:8080/customer/update/{customerId}
http метод POST
параметр customerId являеться обезательным
при успешном выполнении возврощает Статус овета 200 (OK) поля id inn будуд игнорирываться
может возвращать статус овета 404 (NOT FOUND) с сообщением "NO SUCH USER" если не нашлось клиента с таким id
								400 (BAD REQUEST) с возможными ошибками в теле запроса

пример запроса: http://localhost:8080/customer/update/{userId}
{
	"firstName": "ALEX",
	"lastName": "CROSS"
}



http://localhost:8080/customer/{customerId}
http метод GET
header (Content-Type : application/xml) запрашивет ответ в формате xml
параметр customerId являеться обязательным
возвращает обект клиента Статус овета 200 (OK)
					Статус овета 404 (NOT FOUND) с сообщением "NO SUCH USER" если не нашлось клиента с таким id

http://localhost:8080/customer/23
пример ответа:
{
  "id": 23,
  "firstName": "ALEX",
  "lastName": "CROSS",
  "birthDate": "08-01-1993",
  "gender": "MALE",
  "inn": 7234467891
}




http://localhost:8080/customer/all/{limit}
http метод GET
header (Content-Type : application/xml) запрашивет ответ в формате xml
параметр customerId являеться опциональным
возвращает массив клиентов Статус овета 200 (OK)

http://localhost:8080/customer/all/2
пример ответа:
[
  {
    "id": 1,
    "firstName": "adsjfb",
    "lastName": "sodmf'p",
    "birthDate": "30-09-2017",
    "gender": "MALE",
    "inn": 123435
  },
  {
    "id": 2,
    "firstName": "first",
    "lastName": "sdfgdhj",
    "birthDate": "30-09-2017",
    "gender": "MALE",
    "inn": 12345
  }
 ]
