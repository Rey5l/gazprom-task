# Gazprom Weather App

Приложение для просмотра погоды в различных городах, разработанное в рамках тестового задания.

## Функциональность
- **Список городов**: Загрузка актуального списка городов из внешнего источника (GitHub Gist).
- **Сортировка и группировка**: Города отсортированы по алфавиту и сгруппированы по первой букве с использованием эффекта "липких заголовков" (Sticky Headers).
- **Информация о погоде**: При выборе города открывается экран с текущей температурой, получаемой через OpenWeatherMap API.
- **Обработка состояний**: Реализованы экраны загрузки и обработки ошибок с возможностью повторной попытки запроса.

## Стек технологий
- **Язык**: Kotlin
- **Архитектура**: Android SDK (Activities)
- **Сеть**: Retrofit 2, OkHttp
- **JSON**: Gson Converter
- **UI**: RecyclerView, Custom ItemDecoration (для Sticky Headers)

## Структура проекта
- `com.reysl.gazprom_task.model` — модели данных для API.
- `com.reysl.gazprom_task.network` — конфигурация Retrofit и интерфейсы API.
- `com.reysl.gazprom_task.ui.main` — логика главного экрана и список городов.
- `com.reysl.gazprom_task.ui.weather` — логика экрана с погодой.

## Используемые API
1. **Список городов**: [GitHub Gist](https://gist.githubusercontent.com/Stronger197/764f9886a1e8392ddcae2521437d5a3b/raw/65164ea1af958c75c81a7f0221bead610590448e/cities.json)
2. **Погода**: [OpenWeatherMap API](https://openweathermap.org/api)

## Как запустить
1. Склонируйте репозиторий:
   ```bash
   git clone <repository_url>
   ```
2. Откройте проект в **Android Studio**.
3. Дождитесь синхронизации Gradle.
4. Соберите и запустите проект на эмуляторе или реальном устройстве.
