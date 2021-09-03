# Курсы валют gif
Сервис обращается к [сервису курсов валют](https://docs.openexchangerates.org/), и отдает в ответ gif, полученный с [сервиса gif](https://developers.giphy.com/docs/api#quick-start-guide).
Если курс сегодняшний курс валюты увеличился по отношению ко вчерашнему, то показывается gif по запросу rich. Если уменьшился, то по запросу broke.

## Запуск

### Использование готового образа Docker
Для запуска требуется
- Docker
```console
docker pull alexeykondratyev/currency-gif
docker run -p 8080:8080 alexeykondratyev/currency-gif
```

### Локальная сборка
Для запуска требуется
- Java 11
- Gradle

```console
git clone https://github.com/kondratyev-a/currency-gif.git  
cd currency-gif  
.\gradlew bootRun  
```

### Локальная сборка образа Docker
Для запуска требуется
- Java 11
- Gradle
- Docker
```console
git clone https://github.com/kondratyev-a/currency-gif.git  
cd currency-gif
./gradlew bootBuildImage --imageName=alexeykondratyev/currency-gif
docker run -p 8080:8080 alexeykondratyev/currency-gif
```

### Запуск через Heroku
Запустить [https://currency-gif.herokuapp.com/](https://currency-gif.herokuapp.com/)

## Использование
Запустить в браузере [localhost:8080/](localhost:8080/).
По умолчанию проверятся курс доллара по отношению к рублю.
Для того чтобы изменить валюту по отношению к которой проверяется курс нужно добавить ?currency=XXX, где XXX это трехсимвольный [код валюты](https://www.iban.ru/currency-codes).
Например, [http://localhost:8080/?currency=EUR](http://localhost:8080/?currency=EUR) для просмотра курса доллара по отношению к рублю.

Базовую валюту (USD) можно изменять только при наличии платного аккаунта на Open Exchange Rates.
Для этого нужно указать в настройках подходящий API key и базовую валюту в соответствующем параметре.

### Другие варианты
Сервис так же может отображать gif на сайте gipfy. Для этого можно воспользоваться адресом [localhost:8080/redirect](localhost:8080/redirect).
Или возвращать json в качестве ответа по адресу [localhost:8080/rest](localhost:8080/rest). По этим адресам так же доступно указание валюты, по отношению к которой проверяется курс. По умолчанию RUB.