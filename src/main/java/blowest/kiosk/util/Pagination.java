package blowest.kiosk.util;

public class Pagination {

    public static long getTotalPages(long total, int pageSize) {
        if (total % pageSize != 0) return total / pageSize + 1;
        return total / pageSize;
    }
}
