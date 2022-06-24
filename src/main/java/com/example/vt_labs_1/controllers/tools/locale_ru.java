package com.example.vt_labs_1.controllers.tools;

import java.util.ListResourceBundle;

public class locale_ru extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
//                Buttons
                {"SignUpButton","Регистрация"},
                {"SignInButton","Авторизация"},
                {"UserNameLabel", "Имя пользователя:"},
                {"PasswordLabel", "Пароль:"},
                {"EnterPortRequest", "Пожалуйста, введите порт подключения:"},
                {"ConnectButton", "Подключиться"},
                {"ConnectionErrorNotificationLabel", "Ошибка подключения!"},
                {"TryAgainButton", "Попытаться ещё раз"},
                {"ExitButton", "Выход"},
                {"ConnectionSuccessfulLabel", "Подключение и авторизация успешны!"},
                {"NextButton", "ОК"},
                {"ChooseCommandRequestLabel", "Выберите команду:"},
                {"EnterArgumentRequestLabel", "Введите аргумент:"},
                {"ResultLabel", "Результат:"},
                {"HelloLabel", "Привет!"},
                {"ValueLabel", "Значение"},
                {"CreateMovieButton", "Создать объект Movie"},
                {"ExecuteButton", "Запустить"},
                {"CanvasButton", "Батон"},
                {"TableButton", "Таблица"},
                {"ExitButton", "Выход"},
                {"LoadButton", "Загрузить"},
                {"BackButton", "Назад"},
                {"GenerateRandomObjectButton","Сгенерировать случайный объект"},
                {"AccessExceptionLabel", "У вас недостаточно прав для редактирования этого объекта."},
                {"ChangeLanguage", "Изменить язык:"},
                {"rus", "Русский"},
                {"fin", "Финский"},
                {"ua", "Украинский"},
                {"en", "Английский"},
                {"changeButton","Изменить"}
        };
    }
}
