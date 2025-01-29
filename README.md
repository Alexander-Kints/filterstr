# Утилита для филтрации текстовых файлов
## Системные требования:
* Версия Java - 18
* Версия Maven - 3.9.9
* Библиотека Apache Commons CLI 1.9.0
(https://mvnrepository.com/artifact/commons-cli/commons-cli/1.9.0)
* Рекомендуемая IDE - Intelij idea
## Инструкция по запуску утилиты:
```bash
  # сборка проекта Maven
  mvn package
  # перемещение утилиты из target
  mv target/util.jar . 
  # запуск утилиты
  java -jar util.jar -s -a -p sample- in1.txt in2.txt
```
## Список опций утилиты:
* -o путь для сохранения результатов
* -p префикс имен выходных файлов
* -a режим добавления в существующие файлы
* -s краткая статистика
* -f полная статистика