package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> bookFind) {
        List<Map<String, String>> result = new ArrayList<>();

        // 1 раз извлекаем значения атрибутов искомой книги
        String bookFindAuthor = bookFind.getOrDefault("author", "");
        String bookFindYear = bookFind.getOrDefault("year", "");
        String bookFindTitle = bookFind.getOrDefault("title", "");

        for (Map<String, String> book : books) {
            // извлекаем значения атрибутов книги из списка
            String bookAuthor = book.getOrDefault("author", "");
            String bookYear = book.getOrDefault("year", "");
            String bookTitle = book.getOrDefault("title", "");

            // сравниваем атрибуты искомой книги и книги из списка
            // здесь же определяем, участвует ли тот или иной атрибут в проверке соответствия
            // если в искомой книге отсутствует атрибут, то флаг выставляется в true и по нему сравнение не идёт
            boolean isEqualAuthor = bookFindAuthor.isEmpty() | bookAuthor.equals(bookFindAuthor);
            boolean isEqualYear = bookFindYear.isEmpty() | bookYear.equals(bookFindYear);
            boolean isEqualTitle = bookFindTitle.isEmpty() | bookTitle.equals(bookFindTitle);

            if (isEqualAuthor && isEqualYear && isEqualTitle) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
