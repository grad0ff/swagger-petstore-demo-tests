# Тесты веб-приложения Swagger Petstore

## Стек и технологии

<a href="https://www.java.com/"><img alt="Java" width="50" height="50" src="resources/technologies/java.png"/></a>
<a href="https://junit.org/junit5/"><img alt="Junit 5" width="50" height="50" src="resources/technologies/junit_5.png"/></a>
<a href="https://rest-assured.io/"><img alt="Rest Assured"  width="50" height="50" src="resources/technologies/rest_assured.png"/></a>
<a href="https://joel-costigliola.github.io/assertj/"><img alt="AssertJ" width="50" height="50" src="resources/technologies/assertj.png"/></a>
<a href="https://gatling.io/"><img alt="Gatling" width="50" height="50" src="resources/technologies/gatling.png"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" width="50" height="50" src="resources/technologies/allure_testops.svg"/></a>
<a href="https://allurereport.org/"><img alt="Allure Report" width="50" height="50" src="resources/technologies/allure.svg"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" width="50" height="50" src="resources/technologies/jenkins.svg"/></a>
<a href="https://docker.com/"><img alt="Docker" width="50" height="50" src="resources/technologies/docker.svg"/></a>
<a href="https://docker.com/"><img alt="Maven" width="50" height="50" src="resources/technologies/maven.png"/></a>
<a href="https://docker.com/"><img alt="Github" width="50" height="50" src="resources/technologies/github.svg"/></a>
<a href="https://docker.com/"><img alt="Telegram" width="50" height="50" src="resources/technologies/telegram.svg"/></a>
<a href="https://docker.com/"><img alt="Ubuntu" width="50" height="50" src="resources/technologies/ubuntu.png"/></a>

\* Java, Junit 5, REST Assured, AssertJ, Gatling, Allure TestOps, Allure Report, Jenkins, Docker, Maven, GitHub, Telegram, Ubuntu

## Структура

Проект автотестов является многомодульным и состоит из 2 модулей:

* [api-tests](api-tests) (тесты API)
* [performance-tests](performance-tests)  (тесты производительности)

## Тесты API

Разработаны тесты на компоненты:

* 🟢 user
* ⚪️ store
* ⚪️ pet

## Тесты производительности

Разработаны сценарии проверки:

* ⚪️ авторизация + поиск + покупка

## Тест-менеджмент

### Allure TestOps

TMS доступен по [ссылке](https://peachrey.testops.cloud/project/1/dashboards) (_пока действует триал_)

Реализованы возможности:

* 🟢 генерация тест-кейсов в Allure TestOps из кода
* ⚪️ интеграция c Jenkins (запуск проверок, выгрузка отчетов)

<div>
    <img src="resources/screens/test_case_1.png" width=65% alt="test_case_1"/>
    <br/>
    <img src="resources/screens/test_case_2.png" width=65% alt="test_case_1"/>
</div>

## Отчетность

### Allure Report

Реализованы возможности:

* 🟢 генерация Allure отчетов по каждому запуску

<div>
    <img src="resources/screens/jenkins_1.png" width=65% alt="test_case_1"/>
</div>

## CI

### Jenkins

Реализованы возможности:

* 🟢 интеграция с Allure TesOps
* ⚪️ рассылка уведомлений и отчетов в Telegram и Email

<div>
    <img src="resources/screens/allure_report_1.png" width=65% alt="test_case_1"/>
</div>

## VM

В Docker запущены контейнеры с приложениями:

* 🟢 Jenkins
* 🟢 Swagger Petstore

<div>
    <img src="resources/screens/docker_1.png" width=65% alt="test_case_1"/>
</div>

## Код

Код проекта в Github [swagger-petstore-demo-tests](https://github.com/grad0ff/swagger-petstore-demo-tests.git)

