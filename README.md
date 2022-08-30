### Hexlet tests and linter status:
[![Actions Status](https://github.com/nik2704/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/nik2704/java-project-lvl3/actions)

![Java CI](https://github.com/nik2704/java-project-lvl2/actions/workflows/blank.yml/badge.svg)

<a href="https://codeclimate.com/github/nik2704/java-project-lvl3/maintainability"><img src="https://api.codeclimate.com/v1/badges/afb66a0d8d00ae36edc0/maintainability" /></a>

<a href="https://codeclimate.com/github/nik2704/java-project-lvl3/test_coverage"><img src="https://api.codeclimate.com/v1/badges/afb66a0d8d00ae36edc0/test_coverage" /></a>

# **Валидатор данных**

**Предназначен для проверки значений различных типов на соотвестствие требованиям.**

### Поддерживаемые типы данных:
* Строка
* Целое число
* Ассоциативный массив со строковыми ключами и значениями одного из поддерживаемых типов.

### Доступные методы:
* **Строки**
   * required – любая непустая строка
   * minLength – строка равна или длиннее указанного числа
   * contains – строка содержит определённую подстроку
* **Числа**
   * required – любое число включая ноль
   * positive – положительное число
   * range – диапазон, в который должны попадать числа включая границы
* **Ассоциативные массивы**
   * required – требуется тип данных Map
   * sizeof – количество пар ключ-значений в объекте Map должно быть равно заданному

### Примеры использования:

1) **Валидация строк**


    import hexlet.code.Validator;
    import hexlet.code.schemas.StringSchema;
    Validator v = new Validator();
    StringSchema schema = v.string();
    
    schema.isValid(""); // true
    schema.isValid(null); // true
    
    schema.required();
    
    schema.isValid("what does the fox say"); // true
    schema.isValid("hexlet"); // true
    schema.isValid(null); // false
    schema.isValid(""); // false
    
    schema.contains("wh").isValid("what does the fox say"); // true
    schema.contains("what").isValid("what does the fox say"); // true
    schema.contains("whatthe").isValid("what does the fox say"); // false
    
    schema.isValid("what does the fox say"); // false
    // уже false, так как добавлена ещё одна проверка contains("whatthe")


2) **Валидация ассоциативного массива**


    import hexlet.code.Validator;
    import hexlet.code.schemas.MapSchema;
    
    Validator v = new Validator();
    
    MapSchema schema = v.map();
    
    schema.isValid(null); // true
    
    schema.required();
    
    schema.isValid(null) // false
    schema.isValid(new HashMap()); // true
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");
    schema.isValid(data); // true
    
    schema.sizeof(2);
    
    schema.isValid(data);  // false
    data.put("key2", "value2");
    schema.isValid(data); // true