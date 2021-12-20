# Этапы работы над итоговым проектом


### • Добавил и создал все необходимые для проекта элементы:

_1. CSV файл, из которого нам необходимо парсить данные_

![csv_file](https://github.com/mrrrkhl/finalProject/blob/master/images/CSV.png)

_2. Класс для хранения данных из CSV файла_

![data_class](https://github.com/mrrrkhl/finalProject/blob/master/images/transactionData.png) <br><br><br>


### • Распарсил необходимые данные из CSV файла в класс TransactionData

_Метод для парсинга данных_

![parser](https://github.com/mrrrkhl/finalProject/blob/master/images/parseCSV.png) <br><br><br>


### • Работа с БД

_1. Подключил Sqlite к проекту, установил соединение_

![connecting_db](https://github.com/mrrrkhl/finalProject/blob/master/images/connectingDB.png)

_2. Сделал запрос для создания таблицы Transactions с данными из TransactionData_

![create_table](https://github.com/mrrrkhl/finalProject/blob/master/images/createTable.png)

_3. Заполнил только что созданную таблицу данными из экземпляров класса TransactionData_

![put_data](https://github.com/mrrrkhl/finalProject/blob/master/images/putDataIntoTable.png) <br><br><br>

### • Реализация 3 задач по данным таблицы из CSV файла

_1. 'Выведите сумму всех переводов(где это возможно) в Долларах за 2020 год, сгрупированные по месяцам в виде графика'_

_Необходимый запрос в БД:_

![1query](https://github.com/mrrrkhl/finalProject/blob/master/images/1taskQuery.png)

_Класс для построения диаграммы:_

![diagram_class](https://github.com/mrrrkhl/finalProject/blob/master/images/diagram.png)

_Полученная диаграмма:_
![diagram](https://github.com/mrrrkhl/finalProject/blob/master/images/1taskResult.png) <br><br><br>

_2. 'Выведите в консоль средний размер перевода в долларах, а так же их количество, за каждый уникальный период'_

_Необходимый запрос в БД:_

![2query](https://github.com/mrrrkhl/finalProject/blob/master/images/2taskQuery.png)

_Результат:_

![2queryResult](https://github.com/mrrrkhl/finalProject/blob/master/images/2taskResult.png) <br><br><br>

_3. 'Выведите в консоль максимальный и минимальный перевод за период с 2014, 2016 и 2020 год'_

_Необходимый запрос в БД:_

![3query](https://github.com/mrrrkhl/finalProject/blob/master/images/3taskQuery.png)

_Результат:_

![3queryResult](https://github.com/mrrrkhl/finalProject/blob/master/images/3taskResult.png)
