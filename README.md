# checkRun
## Тестовое задание от Clevertec для Backend development course
консольное приложение, реализующее функционал формирования чека в магазине

## Используемый стек
Java 17    
Gradle 7.5

## Инструкция по запуску
Собранный файл приложения находится по пути build/libs/checkrun-0.1.0.jar    
Запустить из командной строки: java -jar checkrun-0.1.0.jar 1-1.5 2-2 3-3 4-6 5-5 6-6 card-2a products.txt discountCards.txt    
Для запуска приложения используются следующие параметры:
```sh
  1-2 2-3 3-4 card-1234 products.txt discountCards.txt
```
где
* 1-2, 2-3, 3-4 - id товаров и их колличество, 
* card-.. -номер дисконтной карты,
* products.txt - название файла в корне с расширением .txt содержащим данные товаров,
* discountCards.txt - название файла в корне с расширением .txt содержащим данные дисконтных карт.
    
Если дисконтная карта не предъявлена, набор параметров должен быть следующий:
```sh
  1-2 2-3 3-4 products.txt
```
-------- 
products.txt содержит данные в формате:
```sh
Id Description Price VAT Discount QuantityOfProductsForDiscount PercentageDiscounts
```
Первые 4 столбца являются обязательными, пример файла в корне проекта.    
Т.к. VAT может быть разным для разных видов товаров(продуктов), было принято решение вынести его в файл с товарами.

--------     
discountCards.txt содержит данные в формате:
```sh
Id PercentageDiscount
```
Пример файла в корне проекта.

--------     
Программа выводит чек в консоль и в файл check.txt(пример в корне проекта)
