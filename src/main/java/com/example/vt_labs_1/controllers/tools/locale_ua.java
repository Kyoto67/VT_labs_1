package com.example.vt_labs_1.controllers.tools;

import java.util.ListResourceBundle;

public class locale_ua extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
//                Buttons
                {"SignUpButton","Реєстрація"},
                {"SignInButton","Авторизація"},
                {"UserNameLabel", "Ім'я користувача:"},
                {"PasswordLabel", "Пароль:"},
                {"EnterPortRequest", "Будь ласка, введіть порт підключення:"},
                {"ConnectButton", "Підключитися"},
                {"ConnectionErrorNotificationLabel", "Помилка підключення!"},
                {"TryAgainButton", "Спробувати ще раз"},
                {"ExitButton", "Вихід"},
                {"ConnectionSuccessfulLabel", "Підключення та авторизація успішні!"},
                {"NextButton", "ОК"},
                {"ChooseCommandRequestLabel", "Виберіть команду:"},
                {"EnterArgumentRequestLabel", "Введіть аргумент:"},
                {"ResultLabel", "Результат:"},
                {"HelloLabel", "Привіт!"},
                {"ValueLabel", "Значення"},
                {"CreateMovieButton", "Створити об'єкт Movie"},
                {"ExecuteButton", "Запустити"},
                {"CanvasButton", "Візуалізація"},
                {"TableButton", "Таблиця"},
                {"ExitButton", "Вихід"},
                {"LoadButton", "Завантажити"},
                {"BackButton", "Назад"},
                {"GenerateRandomObjectButton","Сгенерувати випадковий об'єкт"},
                {"AccessExceptionLabel", "У вас недостатньо прав для редагування цього об'єкта."},
                {"ChangeLanguage", "Изменить язык:"},
                {"rus", "Російська"},
                {"fin", "Фінський"},
                {"ua", "Український"},
                {"en", "Англійська"},
                {"changeButton","Змінити"}
        };
    }
}
