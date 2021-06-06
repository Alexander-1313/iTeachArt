<h1>1. Как запустить проект</h1><br>
	<ol>
		<li>Скачать проект и разархировать его</li>
		<li>Перейти в корневой каталог и выполнить команду mvn clean package</li>
		<li>Перейти в папку target модуля web командой cd web/target</li>
		<li>Выполнить команду java -jar iteachart-0.0.1-SNAPSHOT.jar</li>
	</ol>
<h1>2. Как запустить тесты</h1>
	<ol>
		<li>В корневом каталоге выполнить команду mvn test</li>
	</ol>
<h1>3. Описание проекта и его частей</h1>
	<p>
				Проект - это простое Restfull приложение, которое пердоставляет возможность следить за изменениями на фондовом рынке, 
		собирать и анализировать информацию по интересующим акциям компаний. Приложение обеспечивает получение актуальных данных и сохранение их в бд,
		доступ к приложению с различным уровнем доступа. Есть возможность создать/удалить, блокировать/разблокировать пользователя, изменить статус подписки, обновить ее.
		Приложение осуществляет валилацию срока действия подписки у пользователя и если она истекла, то уведомляет пользователя по почте. Загурзка результатов торгов - это отдельный сервис(директория load в корне),
         которая позволяет обмениваться данными по REST между приложением и данным сервисом.
	</p>
<h1>4. Как проверить работоспособность, примеры запросов-ответов</h1>
		Проверить работосособность можно в программе postman используя эндпоинты приложения.<br>
		<strong>Endpoints:</strong><br>
		1) запрос:GET localhost:8080/user/financialReports?company=название_компании<br>
		<pre>ответ:[
    {
        "cik": "",
        "financialReportCompany": {
            "ticker": "1113.HK",
            "country": "HK",
            "currency": "HKD",
            "name": "CK Asset Holdings Ltd",
            "phone": "85221288888.0",
            "weburl": "http://www.ckah.com",
            "cik": null
        },
        "id": 6
    }
		]
		</pre><br>
		2) запрос:GET localhost:8080/shares?symbol=FVIV<br>
		ответ: 
		<pre>[
    {
        "exchange": "NEW YORK STOCK EXCHANGE, INC.",
        "marketCapitalization": 795,
        "shareOutstanding": 75,
        "companySharesCompany": {
            "ticker": "FVIV",
            "country": "US",
            "currency": "USD",
            "name": "Fortress Value Acquisition Corp IV",
            "phone": "12127986100.0",
            "weburl": "",
            "cik": null
        },
        "id": 8
    }
		]</pre><br>
		3) запрос:POST localhost:8080/admin/changeSubscribe?user=alexander.rybak2020@mail.ru&status=false<br>
		ответ:
		<pre>
			{
    "id": 1,
    "firstName": "Alexander",
    "secondName": "Rybak",
    "email": "alexander.rybak2020@mail.ru",
    "password": "$2y$12$BBQP2SnY0y..oidNkb.iyOvADyM/HlmmDWqkGrppOEzgijeabLhnK",
    "createdAt": null,
    "isBlocked": false,
    "subscribeEnabled": false,
    "subscribeExpireDate": "2020-12-12",
    "role": {
        "id": 2,
        "role": "ROLE_ADMIN",
        "authority": "ROLE_ADMIN"
    },
    "subscribe": null,
    "companies": [
        {
            "ticker": "ARIS.TO",
            "country": "CA",
            "currency": "USD",
            "name": "Aris Gold Corp",
            "phone": "14163604653.0",
            "weburl": "https://www.caldasgold.ca/",
            "cik": null
        },
        {
            "ticker": "FVIV",
            "country": "US",
            "currency": "USD",
            "name": "Fortress Value Acquisition Corp IV",
            "phone": "12127986100.0",
            "weburl": "",
            "cik": null
        },
        {
            "ticker": "1113.HK",
            "country": "HK",
            "currency": "HKD",
            "name": "CK Asset Holdings Ltd",
            "phone": "85221288888.0",
            "weburl": "http://www.ckah.com",
            "cik": null
        }
    ],
    "enabled": true,
    "authorities": [
        {
            "id": 2,
            "role": "ROLE_ADMIN",
            "authority": "ROLE_ADMIN"
        }
    ],
    "username": "alexander.rybak2020@mail.ru",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
}
		</pre>
