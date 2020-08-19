import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        ParallelReadFilter parallelReadFilter = new ParallelReadFilter();
        List<File> fileList = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            fileList.add(new File(args[i]));
        }

        parallelReadFilter.parallelReadAndFilter(fileList, "r");
    }
}
