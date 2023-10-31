package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static int getCountOfFreeEmails(List<String> emailsList){
        List<String> onlyFreeware = List.of("gmail.com", "yandex.ru", "hotmail.com");
        if (emailsList.isEmpty()) {
            return 0;
        }
        int count = (int) emailsList.stream()
                .filter(email -> email.contains("@"))
                .filter(email -> onlyFreeware.contains(getDomain(email)))
                .count();
        return count;
    }
    public static String getDomain(String email) {
        return email.substring(email.indexOf('@') + 1);
    }
}
// END
